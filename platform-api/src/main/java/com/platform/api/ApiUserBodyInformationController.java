package com.platform.api;


import com.alibaba.fastjson.JSONObject;
import com.platform.annotation.IgnoreAuth;
import com.platform.annotation.LoginUser;
import com.platform.entity.UserBodyInformationVo;
import com.platform.entity.UserHealthReportVo;
import com.platform.entity.UserVo;
import com.platform.service.ApiUserBodyInformationService;
import com.platform.service.ApiUserHealthReportService;
import com.platform.util.ApiBaseAction;
import com.platform.utils.DateUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.text.DateFormat;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.*;


/**
 * 用户身体信息表
 id
 用户id
 身高
 生日
 目标体重Controller
 *
 * @author zoubin
 * @email 9379248@qq.com
 * @date 2019-06-15 17:43:02
 */
@Api(tags = "用户身体信息")
@RestController
@RequestMapping("/api/userbodyinfo")
public class ApiUserBodyInformationController extends ApiBaseAction {
    @Autowired
    private ApiUserBodyInformationService userBodyInformationservice;
    @Autowired
    private ApiUserHealthReportService userHealthReportService;
    @ApiOperation(value = "保存用户信息")
    @PostMapping("add")
    public Object add(@LoginUser UserVo loginUser ){
        JSONObject userbodyjsonparam=this.getJsonRequest();
        UserBodyInformationVo userBodyInformationVo=new UserBodyInformationVo();
        if(userbodyjsonparam!=null){
            userBodyInformationVo.setUserHeight(userbodyjsonparam.getDouble("userheight"));
            userBodyInformationVo.setGoalWeight(userbodyjsonparam.getDouble("goalweight"));
            userBodyInformationVo.setUserBirthday(userbodyjsonparam.getDate("userbirthday"));
            userBodyInformationVo.setNideshopUserId(loginUser.getUserId());
            userBodyInformationservice.save(userBodyInformationVo);
        }
        return toResponsMsgSuccess("添加成功");
    }
    @ApiOperation(value = "获取身体数据")
    @PostMapping("info")
    public Object info(@LoginUser  UserVo loginUser) {
        Map<String, Object> result = new HashMap<>();
        UserBodyInformationVo userBodhyInformationVo = new UserBodyInformationVo();
        Long nideshopUserid = loginUser.getUserId();
        Map bodyinfo = new HashMap();
        bodyinfo.put("nideshopUserid", nideshopUserid);
        List<UserBodyInformationVo> userBodyInfo = userBodyInformationservice.queryList(bodyinfo);
        List<UserHealthReportVo> userHealthReportVoList = userHealthReportService.queryList(bodyinfo);
        Double bmi = 0.0;
        Double weight = 0.0;
        Double goalweight = 0.0;
        Double userheight = 0.0;
        Map bodyinfolist = new HashMap();
        if (userBodyInfo != null) {
            if (userHealthReportVoList == null) {
                result.put("userBodyInfo", userBodyInfo);
                return toResponsSuccess(result);
            }
            else {
                for (UserHealthReportVo userHealthReportVoItem : userHealthReportVoList) {
                    bmi = userHealthReportVoItem.getBmi();
                    weight = userHealthReportVoItem.getWeight();
                }
                bodyinfolist.put("bmi",bmi);
                bodyinfolist.put("weight",weight);
                for (UserBodyInformationVo userBodyInformationVoItem : userBodyInfo) {
                    goalweight = userBodyInformationVoItem.getGoalWeight();
                    userheight = userBodyInformationVoItem.getUserHeight();
                }
                bodyinfolist.put("goalweight",goalweight);
                bodyinfolist.put("userheight",userheight);
                result.put("bodyinfolist", bodyinfolist);
                result.put("userBodyInfo",userBodyInfo);
                return toResponsSuccess(result);
            }
        }else{
            return toResponsMsgSuccess("没有数据");
        }
    }
    @ApiOperation(value = "修改身体信息")
    @PostMapping("update")
    public Object update(@LoginUser UserVo loginUser,Integer id) {
        JSONObject updateparam = this.getJsonRequest();
        UserBodyInformationVo userBodyInformationVo = new UserBodyInformationVo();
        if (updateparam!=null) {
            userBodyInformationVo.setUserHeight(updateparam.getDouble("userHeight"));
            userBodyInformationVo.setGoalWeight(updateparam.getDouble("goalWeight"));
            userBodyInformationVo.setUserBirthday(updateparam.getDate("userBirthday"));
            userBodyInformationVo.setNideshopUserId(loginUser.getUserId());
            userBodyInformationservice.update(userBodyInformationVo);
        }else{
            return toResponsFail("参数为空");
        }        return  toResponsMsgSuccess("修改成功");
    }
}