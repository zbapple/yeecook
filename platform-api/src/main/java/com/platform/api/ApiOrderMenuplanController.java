package com.platform.api;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.platform.annotation.LoginUser;
import com.platform.entity.OrderMenuplanEntity;
import com.platform.entity.UserVo;
import com.platform.service.ApiOrderMenuplanService;
import com.platform.util.ApiBaseAction;
import com.platform.util.CommonUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller
 *
 * @author zoubin
 * @email 9379248@qq.com
 * @date 2019-09-25 15:35:28
 */
@Api(tags="团购订餐")
@RestController
@RequestMapping("/api/ordermenuplan")
public class ApiOrderMenuplanController extends ApiBaseAction {
    @Autowired
    private ApiOrderMenuplanService orderMenuplanService;

    @ApiOperation(value = "添加团餐订单")
    @RequestMapping("add")
    public Object add(@LoginUser UserVo logiuser){
        JSONObject addjson=this.getJsonRequest();
        Map addmap=new HashMap();
        addmap.put("userid",logiuser.getUserId());
        List<OrderMenuplanEntity> orderplanlist=orderMenuplanService.queryList(addmap);
        OrderMenuplanEntity ordeplan=new OrderMenuplanEntity();
        if(orderplanlist.size()==0 || orderplanlist==null){
            ordeplan.setStroeid(addjson.getInteger("stroeid"));
            ordeplan.setDeliveryTime(addjson.getDate("deliverytime"));
            ordeplan.setPopulation(addjson.getInteger("population"));
            ordeplan.setSpecification(addjson.getString("specification"));
            ordeplan.setRemark(addjson.getString("remark"));
            ordeplan.setFate(addjson.getInteger("fate"));
            ordeplan.setMenuSn(CommonUtil.generateOrderNumber());
            ordeplan.setAddTime(new Date());
            orderMenuplanService.save(ordeplan);
        }
        return toResponsMsgSuccess("成功!");
    }
}
