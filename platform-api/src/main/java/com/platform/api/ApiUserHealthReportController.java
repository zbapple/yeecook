package com.platform.api;



import com.alibaba.fastjson.JSONObject;
import com.platform.annotation.LoginUser;
import com.platform.entity.ApiUserhealReportVo;
import com.platform.entity.UserHealthReportVo;
import com.platform.entity.UserVo;
import com.platform.service.ApiUserHealthReportService;
import com.platform.util.ApiBaseAction;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;


/**
 * 用户健康报告表
 id
 用户id
 检测时间
 更新时间
 体重
 BMI
 体脂率
 皮下脂肪率
 基础代谢量
 内脏脂肪等级
 体水分率
 蛋白质
 骨量
 骨骼肌率Controller
 *
 * @author zoubin
 * @email 9379248@qq.com
 * @date 2019-06-16 11:08:30
 */
@Api(tags = "用户健康报表")
@RestController
@RequestMapping("/api/userhealthreport")
public class ApiUserHealthReportController extends ApiBaseAction {
    @Autowired
    private ApiUserHealthReportService userHealthReportService;
    @ApiOperation(value = "健康报告用户身体数据的录入")
    @PostMapping("add")
    public  Object add(@LoginUser UserVo loginuser){
        Map<String,Object> result=new HashMap<>();
        JSONObject addjsonparam=this.getJsonRequest();
        UserHealthReportVo userHealthReportVo=new UserHealthReportVo();
        if(addjsonparam!=null){
            userHealthReportVo.setNideshopUserId(loginuser.getUserId());
            //基础代谢量
            userHealthReportVo.setBasicMetabolism(addjsonparam.getDouble("basicmetabolism"));
            //BMI
            userHealthReportVo.setBmi(addjsonparam.getDouble("BMI"));
            //体脂率
            userHealthReportVo.setBodyFatRade(addjsonparam.getDouble("bodyfatrade"));
            //体水分率
            userHealthReportVo.setBodyWaterRate(addjsonparam.getDouble("waterrate"));
            //骨量
            userHealthReportVo.setBoneMass(addjsonparam.getDouble("bonemass"));
            //检测时间
            userHealthReportVo.setDetectionTime(addjsonparam.getDate("detectiontime"));
            //蛋白质
            userHealthReportVo.setProtein(addjsonparam.getString("protein"));
            //骨骼肌率
            userHealthReportVo.setSkeletalMuscle(addjsonparam.getString("skeletalmuscle"));
            //皮下脂肪率
            userHealthReportVo.setSubFatPercentage(addjsonparam.getDouble("subfatpercentage"));
            //更新时间
            userHealthReportVo.setUpdateTime(addjsonparam.getDate("updatetime"));
            //内脏脂肪等级
            userHealthReportVo.setVisFatGrade(addjsonparam.getString("visfatgrade"));
            //体重
            userHealthReportVo.setWeight(addjsonparam.getDouble("weight"));
            userHealthReportService.save(userHealthReportVo);
            result.put("flag",1);
            return toResponsSuccess(result);
        }else{
            result.put("flag",0);
            return toResponsSuccess(result);
        }

    }
    @ApiOperation(value = "用户身体健康报告记录详情")
    @PostMapping("userbodyinfo")
    public  Object userbodyinfo(){
        JSONObject body=this.getJsonRequest();
        Integer id=body.getInteger("id");
        UserHealthReportVo userHealthReportVos=userHealthReportService.queryObject(id);
        return  toResponsSuccess(userHealthReportVos);
    }
    @ApiOperation(value = "获取用户月份报告信息")
    @PostMapping("userdateinfo")
    public Object userdateinfo(@LoginUser UserVo loginuser) {
        JSONObject userdateinfojson = this.getJsonRequest();
        Long userid = loginuser.getUserId();
        Map datemap = new HashMap();
        datemap.put("nideshopUserid", userid);
        List<UserHealthReportVo> userHealthReportVoList = userHealthReportService.queryList(datemap);
        List<ApiUserhealReportVo> apiUserhealReportVoList=new ArrayList<>();
        UserHealthReportVo userHealthReportVo = new UserHealthReportVo();
        Map<Date,List<UserHealthReportVo>> map = userHealthReportVoList.stream().collect(
                Collectors.groupingBy(UserHealthReportVo::getDetetionTime
                ));
            for(Date key:map.keySet()){
                ApiUserhealReportVo apiUserhealReportVo=new ApiUserhealReportVo();
                apiUserhealReportVo.setCount(map.get(key).size());
                apiUserhealReportVo.setTime(key);
                apiUserhealReportVo.setHealportlistmap(map.get(key));
                apiUserhealReportVoList.add(apiUserhealReportVo);
            }
    return  toResponsSuccess(apiUserhealReportVoList);
    }
    @ApiOperation(value = "获取用户本月的最值")
    @PostMapping("maxuser")
    public Object maxuser(@LoginUser UserVo logiUser){
        Map<String,Object> result1=new HashMap<>();
        Map<String,Object> result=new HashMap<>();
        JSONObject maxuserjson=this.getJsonRequest();
        String datemin=maxuserjson.getString("datemm");
        Long nideshopuserid=logiUser.getUserId();
        SimpleDateFormat formats=new SimpleDateFormat("M");
        Map maxmap=new HashMap();
        maxmap.put("datemm",datemin);
        maxmap.put("nideshopUserid",nideshopuserid);
        List<UserHealthReportVo> userHealthReportVoL=userHealthReportService.queryvalue(maxmap);
            Integer max=0;
            Integer min=0;
            Double avg=0.0;
            if(userHealthReportVoL!=null){
                for(UserHealthReportVo userHealthReportVoitem:userHealthReportVoL){
                    max=userHealthReportVoitem.getMax();
                    min=userHealthReportVoitem.getMin();
                    avg=userHealthReportVoitem.getAvg();
                }
                result.put("max",max);
                result.put("min",min);
                result.put("avg",avg);
                result1.put("reslut",result);
                result1.put("flag",1);
                return toResponsSuccess(result1);
            }else{
                result1.put("flag",0);
                return toResponsSuccess(result1);
            }


    }
}
