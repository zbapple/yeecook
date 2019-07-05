package com.platform.api;
import com.alibaba.fastjson.JSONObject;
import com.platform.annotation.LoginUser;
import com.platform.dao.ApiUserDetectionCycleMapper;
import com.platform.entity.UserDetectionCycleVo;
import com.platform.entity.UserVo;
import com.platform.util.ApiBaseAction;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用户检测周期表
 id
 检测开始日期
 检测结束日期
 已检测次数
 检测执行日期
 检测周期
 用户idController
 *
 * @author zoubin
 * @email 9379248@qq.com
 * @date 2019-06-16 11:07:46
 */
@Api(tags = "用户周期检查")
@RestController
@RequestMapping("/api/userdetectioncycle")
public class ApiUserDetectionCycleController extends ApiBaseAction {
    @Autowired
    private ApiUserDetectionCycleMapper userDetectionCycleService;

    @ApiOperation(value = "用户检测")
    @PostMapping("info")
    public Object info(@LoginUser UserVo loginUser) {
        Map<String, Object> result = new HashMap<>();
        if (loginUser.getUserId() != null) {
            JSONObject infojson = this.getJsonRequest();
            Long userid = loginUser.getUserId();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date datetime = infojson.getDate("datetime");
            String datetime1 = dateFormat.format(datetime);
            Date jiancedate = null;
            Integer num = 0;
            Map infomap = new HashMap();
            infomap.put("nideshopUserid", userid);
            List<UserDetectionCycleVo> userDetectionCycleVoList = userDetectionCycleService.queryList(infomap);
            if (userDetectionCycleVoList != null) {
                for (UserDetectionCycleVo userDetectionCycleVoItem : userDetectionCycleVoList) {
                    jiancedate = userDetectionCycleVoItem.getInspectionTime();
                    num = userDetectionCycleVoItem.getInspectionNum();
                }
                String jiancedate1 = dateFormat.format(jiancedate);
                if (datetime1.equals(jiancedate1)) {
                    Integer cout = num + 1;
                    result.put("state", 1);
                    result.put("cout", cout);
                    result.put("flag", 1);
                    return toResponsSuccess(result);
                } else {
                    result.put("state", 0);
                    result.put("flag", 1);
                    return toResponsSuccess(result);
                }
            } else {
                result.put("flag", 0);
                return toResponsSuccess(result);
            }
        }else{
            return toResponsMsgSuccess("请登入" );
        }
    }
}



