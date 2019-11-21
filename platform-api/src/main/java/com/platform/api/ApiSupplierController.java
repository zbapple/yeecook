package com.platform.api;

import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.platform.annotation.IgnoreAuth;
import com.platform.entity.SupplierVo;
import com.platform.service.ApiSupplierService;
import com.platform.util.ApiBaseAction;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Controller
 *
 * @author zoubin
 * @email 9379248@qq.com
 * @date 2019-11-06 16:55:52
 */
@Api(tags ="供应商" )
@RestController
@RequestMapping("/api/supplier")
public class ApiSupplierController extends ApiBaseAction {
    @Autowired
    private ApiSupplierService supplierService;

    @ApiOperation(value = "供应商信息")
    @IgnoreAuth
    @PostMapping("info")
    public Object info(){
        Map<String,Object> result=new HashMap<>();
        JSONObject jsonObject=this.getJsonRequest();
        Integer id=jsonObject.getInteger("supplier_id");
//        Map param=new HashMap();
//        param.put("supperlierid",jsonObject.getInteger("supplier_id"));
        SupplierVo supplierVo=supplierService.queryObject(Long.valueOf(id));
        if(supplierVo!=null){
            Map map=new HashMap();
            map.put("name",supplierVo.getCompanyName());
            map.put("Address",supplierVo.getAddress());
            map.put("mobile",supplierVo.getMobile());
            result.put("flag",1);
            result.put("map",map);
        }else {
            result.put("flag",0);
            return result;
        }
        return result;
    }
}
