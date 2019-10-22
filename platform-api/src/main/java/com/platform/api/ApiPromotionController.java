package com.platform.api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.platform.annotation.LoginUser;
import com.platform.entity.PromotionVo;
import com.platform.entity.UserVo;
import com.platform.service.ApiPromotionInfoService;
import com.platform.service.ApiPromotionService;
import com.platform.service.ApiUserService;
import com.platform.util.ApiBaseAction;
import com.platform.util.HttpXmlClient;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Controller
 *
 * @author zoubin
 * @email 9379248@qq.com
 * @date 2019-10-18 10:26:51
 */
@Api(tags = "推广码")
@RestController
@RequestMapping("api/promotion")
public class ApiPromotionController extends ApiBaseAction {
    @Autowired
    private ApiPromotionService promotionService;
    @Autowired
    private ApiUserService userService;
    @Autowired
    private ApiPromotionInfoService promotionInfoService;
    @ApiOperation(value = "二维码")
    @PostMapping("codeinfo")
    public Object codeinfo(@LoginUser UserVo loginUser  ){
        Map<String,Object> result=new HashMap<>();
        Map codemap=new HashMap();
        codemap.put("promotiontelphone",loginUser.getMobile());
        List<PromotionVo> promotionlist=promotionService.queryList(codemap);
        UserVo userinfo=userService.queryObject(loginUser.getUserId());
        List codelist=new ArrayList();
        if(promotionlist.size()>0){
            for(PromotionVo promotionVo:promotionlist){
                String id=String.valueOf(promotionVo.getId());
                String requestStr = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential"
                        + "&appid=wxa2566230db99c9ab&secret=4c7a1efe2c6d45c03c5d47a53685923b";
                String tok="";
                //截取微信返回的token值
                String[] token=HttpXmlClient.sendGet(requestStr,"").split("\"");
                for(int i=0;i<=token.length-1;i++){
                    System.out.println("---"+token[i]);
                    if(i==3){
                        tok=token[i];
                    }
                }
                String requeststr2="https://api.weixin.qq.com/wxa/getwxacodeunlimit?access_token="+tok;
                String url=HttpXmlClient.sendPost3(requeststr2,id);
                codelist.add(url);
                codelist.add(id);
//                PromotionVo prom=new PromotionVo();
//                prom.setId(promotionVo.getId());
//                prom.setPromotionYard(url);
//                promotionService.update(prom);
            }
            result.put("codelist",codelist);
            result.put("flag",0);
        }else{
            result.put("flag",1);
            return result;
        }
        return result;
    }
//    @ApiOperation(value = "关联到用户表")
//    @PostMapping("add")
//    public Object add(@LoginUser UserVo loginuser){
//        Map reslut=new HashMap();
//        JSONObject addjson=this.getJsonRequest();
//        Integer promoid=addjson.getInteger("id");
//        Long userid=loginuser.getUserId();
//        if(promoid !=null){
//            PromotionInfoEntity prom=new PromotionInfoEntity();
//            prom.setParentId(new Long(promoid));
//            prom.setUserId(userid);
//            reslut.put("flag",1);
//        }else{
//            reslut.put("flag",0);
//            return reslut;
//        }
//        return reslut;
//    }
}
