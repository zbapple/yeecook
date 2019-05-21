package com.platform.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.platform.annotation.IgnoreAuth;


import com.platform.service.ApiNewMenussService;
import com.platform.util.ApiBaseAction;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import com.platform.entity.ApiNewMenuListVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


/**
 * Controller
 *
 * @author zoubin
 * @email 9379248@qq.com
 * @date 2019-05-18 10:28:00
 */
@Api(tags = "食谱")
@RestController
@RequestMapping("/api/newmenulist")

public class ApiNewMenussController extends ApiBaseAction {
    @Autowired
    private ApiNewMenussService apiNewMenuListService;

    @ApiOperation(value = "食谱")
    @PostMapping(value = "menuinfo")
    @IgnoreAuth
    public Object menuinfo(Integer postpartumTime, String mlevel){
        Map<String,Object> resultobj=new HashMap<>();
        resultobj.put("postpartumTime",postpartumTime);
        resultobj.put("mlevel",mlevel);

        List<ApiNewMenuListVo> newMenuListVo=apiNewMenuListService.queryList(resultobj);

        return toResponsSuccess(newMenuListVo);

    }

}
