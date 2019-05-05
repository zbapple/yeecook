package com.platform.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.platform.annotation.IgnoreAuth;
import com.platform.service.ApiApplicationRecordService;
import com.platform.utils.StringUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.platform.entity.ApiApplicationRecordVo;
import com.platform.utils.R;

/**
 * Controller
 *
 * @author zoubin
 * @email 9379248@qq.com
 * @date 2019-03-06 02:42:19
 */
@Api(tags = "食材申请")
@RestController
@RequestMapping("/api/applicationrecord")
public class ApiApplicationRecordController {
    @Autowired
    private ApiApplicationRecordService applicationRecordService;


    /**
     * 保存
     */
    @ApiOperation(value = "新增")
    @IgnoreAuth
    @PostMapping("save")
    public R save(  Integer newFoodId,

             String province,
            //申请人所在城市
             String city,
            //申请人姓名
             String name,
            //手机号码
             String phone,
            //申请食材名称
             String newFoodName) {
        ApiApplicationRecordVo  applicationRecord=new ApiApplicationRecordVo();

        if(StringUtils.isNullOrEmpty(name)){
            return R.error();
        }
        if(StringUtils.isNullOrEmpty(city)){
            return R.error();
        }
        if(StringUtils.isNullOrEmpty(phone)){
            return R.error();
        }
        if(StringUtils.isNullOrEmpty(newFoodName)){
            return R.error();
        }

        Map map=new HashMap();
        map.put("newFoodId",newFoodId);
        map.put("name",name);
        map.put("phone",phone);
       List list= applicationRecordService.queryList(map);
        if(list.size()>0){
            return R.error("请不要重复申请！");
        }
        applicationRecord.setProvince(province);
        applicationRecord.setNewFoodId(newFoodId);
        applicationRecord.setCity(city);
        applicationRecord.setName(name);
        applicationRecord.setPhone(phone);
        applicationRecord.setNewFoodName(newFoodName);
        applicationRecordService.save(applicationRecord);

        return R.ok();
    }

}
