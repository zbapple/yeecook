package com.platform.api;

import java.text.DecimalFormat;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.platform.annotation.IgnoreAuth;
import com.platform.service.ApiStroeService;
import com.platform.util.ApiBaseAction;
import com.platform.util.MapUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.platform.entity.StroeEntity;
import com.platform.utils.PageUtils;
import com.platform.utils.Query;
import com.platform.utils.R;

/**
 * Controller
 *
 * @author zoubin
 * @email 9379248@qq.com
 * @date 2019-09-19 10:11:30
 */
@Api(tags = "门店")
@RestController
@RequestMapping("/api/stroe")
public class ApiStroeController extends ApiBaseAction {
    @Autowired
    private ApiStroeService stroeService;

    @ApiOperation(value = "门店信息")
    @IgnoreAuth
    @PostMapping("stroelist")
    public Object stroelist(){
        Map<String,Object> result=new HashMap<>();
        JSONObject listjson=this.getJsonRequest();
        try {
            double lat=listjson.getDouble("lat");
            double lon=listjson.getDouble("lon");
            Integer storeType=listjson.getInteger("storeType");
            Map<String,Double> json=MapUtils.getAround(lon,lat,6000.0);
            Double minLng=json.get("minLng");
            Double maxLng=json.get("maxLng");
            Double minLat=json.get("minLat");
            Double maxLat=json.get("maxLat");
            Map listmap=new HashMap();
            listmap.put("minLng",minLng);
            listmap.put("minLat",minLat);
            listmap.put("maxLng",maxLng);
            listmap.put("maxLat",maxLat);
            listmap.put("storeType",storeType);
            List<StroeEntity> stroeEntityList=stroeService.queryList(listmap);
            Double lon2=0.0;
            Double lat2=0.0;
            Double s=0.0;
            if(stroeEntityList.size()==0){
                result.put("flg",0);
                return result;
            }else{
                DecimalFormat df = new DecimalFormat( "0.00");
                for(StroeEntity stroeEntity:stroeEntityList){
                    lon2=stroeEntity.getLongitude();
                    lat2=stroeEntity.getLatitude();
                    s=MapUtils.Distance(lon,lat,lon2,lat2);
                    Double  distance=s;
                    Double  distance1=Math.round(distance*10)/10.0;
                    if(distance1>1000){
                        Double dstance3=distance1/1000;
                        Double distance2=(new Double(df.format(dstance3)));
                        String km="km";
                        String dstance4=distance2+km;
                        stroeEntity.setJuli(dstance4);
                        result.put("flg",1);
                        result.put("stroeEntityList",stroeEntityList);
                    }else if(distance1<1000){
                        Double dstance2=(new Double(df.format(distance1)));
                        String m="m";
                        String dstance4=dstance2+m;
                        stroeEntity.setJuli(dstance4);
                        result.put("flg",1);
                        result.put("stroeEntityList",stroeEntityList);
                    }
                }
            }
        }catch (Exception e) {
            return toResponsMsgSuccess("请联系管理员");
        }
        return result;
    }

    @ApiOperation(value = "类型门店内信息")
    @IgnoreAuth
    @PostMapping("typestroelist")
    public Object typestroelist(){
        Map<String,Object> result=new HashMap<>();
        JSONObject typestjson=this.getJsonRequest();
        try {
            double lat=typestjson.getDouble("lat");
            double lon=typestjson.getDouble("lon");
            Map<String,Double> json=MapUtils.getAround(lon,lat,5000.0);
            Double minLng=json.get("minLng");
            Double maxLng=json.get("maxLng");
            Double minLat=json.get("minLat");
            Double maxLat=json.get("maxLat");
            Map typest=new HashMap();
            typest.put("minLng",minLng);
            typest.put("minLat",minLat);
            typest.put("maxLng",maxLng);
            typest.put("maxLat",maxLat);
            typest.put("stroeid",typestjson.getInteger("stroeid"));
            List<StroeEntity> stroeentityList=stroeService.queryList(typest);
            Double lon2=0.0;
            Double lat2=0.0;
            Double s=0.0;
            if(stroeentityList.size()==0){
                result.put("flg",0);
                return result;
            }else{
                DecimalFormat df = new DecimalFormat( "0.00");
                for(StroeEntity stroeEntity:stroeentityList){
                    lon2=stroeEntity.getLongitude();
                    lat2=stroeEntity.getLatitude();
                    s=MapUtils.Distance(lon,lat,lon2,lat2);
                    Double  distance=s;
                    Double  distance1=Math.round(distance*10)/1.0;
                    if(distance1>1000){
                        Double dstance3=distance1/1000;
                        Double distance2=(new Double(df.format(dstance3)));
                        String km="km";
                        String dstance4=distance2+km;
                        stroeEntity.setJuli(dstance4);
                        result.put("flg",1);
                        result.put("stroeentityList",stroeentityList);
                    }else if(distance1<1000){
                        Double dstance2=(new Double(df.format(distance1)));
                        String m="m";
                        String dstance4=dstance2+m;
                        stroeEntity.setJuli(dstance4);
                        result.put("flg",1);
                        result.put("stroeentityList",stroeentityList);
                    }
                }
            }
        }catch (Exception e){
            return  toResponsMsgSuccess("请联系管理员");
        }
        return result;
    }
    @ApiOperation(value = "门店地址")
    @PostMapping("stroeaddress")
    public Object stroeaddress(){
        JSONObject addressjson=this.getJsonRequest();
        Integer stroid=addressjson.getInteger("id");
        StroeEntity stroeinfo=stroeService.queryObject(stroid);
        return stroeinfo;
    }
}
