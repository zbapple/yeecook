package com.platform.api;

import com.platform.annotation.IgnoreAuth;
import com.platform.entity.ApiNewMenuVo;
import com.platform.service.ApiNewMenuService;
import com.platform.util.ApiBaseAction;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api(tags = "月子餐单")
@RestController
@RequestMapping("/api/newmenu")
public class ApiNewMenuController extends ApiBaseAction {
    @Autowired
    private ApiNewMenuService newMenuService;
    @ApiOperation(value = "首页")
    @PostMapping(value = "index")
    @IgnoreAuth
    public Object index(String deliveryWay,String feedingWay){
        Map<String,Object> param=new HashMap<>();
        param.put("deliveryWay",deliveryWay);
        param.put("feedingWay",feedingWay);
        List<ApiNewMenuVo> apinewMenuVoList=newMenuService.queryList(param);
        return toResponsSuccess(apinewMenuVoList);
    }

}
