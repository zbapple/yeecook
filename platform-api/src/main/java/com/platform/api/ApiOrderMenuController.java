package com.platform.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.platform.annotation.LoginUser;
import com.platform.entity.UserVo;
import com.platform.service.ApiOrderMenuService;
import com.platform.util.ApiBaseAction;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.platform.entity.OrderMenuEntity;
import com.platform.utils.PageUtils;
import com.platform.utils.Query;
import com.platform.utils.R;

/**
 * Controller
 *
 * @author zoubin
 * @email 9379248@qq.com
 * @date 2019-09-24 10:28:47
 */
@Api(tags = "餐单订单")
@RestController
@RequestMapping("/api/ordermenu")
public class ApiOrderMenuController extends ApiBaseAction {
    @Autowired
    private ApiOrderMenuService orderMenuService;

    @ApiOperation(value = "获取订单详情")
    @RequestMapping("detail")
    public Object detail(@LoginUser UserVo logiuser){
        Map result=new HashMap();

        return result;
    }
}
