package com.platform.api;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import com.alibaba.fastjson.JSONObject;
import com.platform.annotation.IgnoreAuth;
import com.platform.entity.FootprintVo;
import com.platform.entity.StroeVo;
import com.platform.service.ApiFootprintService;
import com.platform.service.ApiStroeService;
import com.platform.util.ApiBaseAction;
import com.platform.util.MapUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    @Autowired
    private ApiFootprintService footprintService;

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
            Map<String,Double> json=MapUtils.getAround(lon,lat,7000.0);
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
//            listmap.put("",);
            List<StroeVo> stroeVoList =stroeService.queryList(listmap);
            Double lon2=0.0;
            Double lat2=0.0;
            Double s=0.0;
            if(stroeVoList.size()==0){
                result.put("flg",0);
                return result;
            }else{
                DecimalFormat df = new DecimalFormat( "0.00");
                for(StroeVo stroeVo : stroeVoList){
                    lon2= stroeVo.getLongitude();
                    lat2= stroeVo.getLatitude();
                    Integer store_status=stroeVo.getStoreStatus();
                    String time=stroeVo.getStoretime();
                    String[] stime=time.split("-");
                    String[] list=new String[2];
                    for(int i=0;i<stime.length;i++){
                        String re=stime[i];
                        list[i]=re;
                    }
                    String one=list[0];
                    String two=list[1];
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    Date date = new Date();
                    String datestr=sdf.format(date);
                    String newone=datestr+" "+one+":00";
                    String newtwo=datestr+" "+two+":00";
                    SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    Date date2 = new Date();
                    String sde2=sdf2.format(date2);
                    Date d2=null;
                    Date d3=null;
                    Date d4=null;
                    Long dd1=null;
                    Long dd2=null;
                    Long dd3=null;
                            try{
                                d2=sdf2.parse(newone);
                                d3=sdf2.parse(newtwo);
                                d4=sdf2.parse(sde2);
                                dd1=d2.getTime();
                                dd2=d3.getTime();
                                dd3=d4.getTime();
                            }catch (Exception e){
                                e.printStackTrace();
                            }

                    s=MapUtils.Distance(lon,lat,lon2,lat2);
                    Double  distance=s;
                    Double  distance1=Math.round(distance*10)/10.0;
                    if(distance1>1000){
                        Double dstance3=distance1/1000;
                        Double distance2=(new Double(df.format(dstance3)));
                        String km="km";
                        String dstance4=distance2+km;
                        stroeVo.setJuli(dstance4);
                        if(store_status==1){
                            if(dd3 > dd1 && dd3 < dd2){
                                stroeVo.setBusiness(1);
                                result.put("flg",1);
                                result.put("stroeEntityList", stroeVoList);
                            }else {
                                stroeVo.setBusiness(2);
                                result.put("flg",1);
                                result.put("stroeEntityList", stroeVoList);
                            }
                        }
                    }else if(distance1<1000){
                        Double dstance2=(new Double(df.format(distance1)));
                        String m="m";
                        String dstance4=dstance2+m;
                        stroeVo.setJuli(dstance4);
                        if(store_status==1){
                            if(dd3 > dd1 && dd3 < dd2){
                                stroeVo.setBusiness(1);
                                result.put("flg",1);
                                result.put("stroeEntityList", stroeVoList);
                            }else {
                                stroeVo.setBusiness(2);
                                result.put("flg",1);
                                result.put("stroeEntityList", stroeVoList);
                            }
                        }
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
            Map<String,Double> json=MapUtils.getAround(lon,lat,7000.0);
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
            List<StroeVo> stroeentityList=stroeService.queryList(typest);
            Double lon2=0.0;
            Double lat2=0.0;
            Double s=0.0;
            if(stroeentityList.size()==0){
                result.put("flg",0);
                return result;
            }else{
                DecimalFormat df = new DecimalFormat( "0.00");
                for(StroeVo stroeVo :stroeentityList){
                    lon2= stroeVo.getLongitude();
                    lat2= stroeVo.getLatitude();
                    Integer storeid=stroeVo.getId();
                    Integer store_status=stroeVo.getStoreStatus();
                    String time=stroeVo.getStoretime();
                    String[] stime=time.split("-");
                    String[] list=new String[2];

                    for(int i=0;i<stime.length;i++){
                        String re=stime[i];
                        list[i]=re;
                    }
                    String one=list[0];
                    String two=list[1];
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    Date date = new Date();
                    String datestr=sdf.format(date);
                    String newone=datestr+" "+one+":00";
                    String newtwo=datestr+" "+two+":00";
                    SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    Date date2 = new Date();
                    String sde2=sdf2.format(date2);
                    Date d2=null;
                    Date d3=null;
                    Date d4=null;
                    Long dd1=null;
                    Long dd2=null;
                    Long dd3=null;
                    try{
                        d2=sdf2.parse(newone);
                        d3=sdf2.parse(newtwo);
                        d4=sdf2.parse(sde2);
                        dd1=d2.getTime();
                        dd2=d3.getTime();
                        dd3=d4.getTime();
                    }catch (Exception e){
                        e.printStackTrace();
                    }

                    s=MapUtils.Distance(lon,lat,lon2,lat2);
                    Double  distance=s;
                    Double  distance1=Math.round(distance*10)/10.0;
                    if(distance1>1000){
                        Double dstance3=distance1/1000;
                        Double distance2=(new Double(df.format(dstance3)));
                        String km="km";
                        String dstance4=distance2+km;
                        stroeVo.setJuli(dstance4);
                        if(store_status==1){
                            if(dd3 > dd1 && dd3 < dd2){
                                stroeVo.setBusiness(1);
                                result.put("flg",1);
                                result.put("stroeEntityList", stroeentityList);
                            }else {
                                stroeVo.setBusiness(2);
                                result.put("flg",1);
                                result.put("stroeEntityList", stroeentityList);
                            }
                        }
                    }else if(distance1<1000){
                        Double dstance2=(new Double(df.format(distance1)));
                        String m="m";
                        String dstance4=dstance2+m;
                        stroeVo.setJuli(dstance4);
                        if(store_status==1){
                            if(dd3 > dd1 && dd3 < dd2){
                                stroeVo.setBusiness(1);
                                result.put("flg",1);
                                result.put("stroeEntityList", stroeentityList);
                            }else {
                                stroeVo.setBusiness(2);
                                result.put("flg",1);
                                result.put("stroeEntityList", stroeentityList);
                            }
                        }
                    }
//                    if(store_status==1){
//                        result.put("flag",2);
//                        return result;
//                     }else{
//                        result.put("flag", 3);
//                        return result;
//                    }
                }
            }
        }catch (Exception e){
            return  toResponsMsgSuccess("请联系管理员");
        }
        return result;
    }
    @ApiOperation(value = "门店地址")
    @IgnoreAuth
    @PostMapping("stroeaddress")
    public Object stroeaddress(){
        JSONObject addressjson=this.getJsonRequest();
        Integer stroid=addressjson.getInteger("id");
        StroeVo stroeinfo=stroeService.queryObject(stroid);
        return stroeinfo;
    }

    @ApiOperation(value = "商家详情")
    @IgnoreAuth
    @PostMapping("stroeinfo")
    public Object stroeinfo(){
    Map<String,Object> result=new HashMap<>();
    JSONObject infojson=this.getJsonRequest();
    Map info=new HashMap();
    info.put("stroeid",infojson.getInteger("stroeid"));
    List<StroeVo> stroeList=stroeService.queryList(info);
    Map  stroe=new HashMap();
    if(stroeList.size()>0){
        for(StroeVo stroeVoItem :stroeList){
            Integer id= stroeVoItem.getId();
            String  name= stroeVoItem.getStoreName();
            String address1= stroeVoItem.getDistrct();
            String address2= stroeVoItem.getAddress();
            String address=address1+address2;
            String phone= stroeVoItem.getStorePhone();
            String stroetime= stroeVoItem.getStoretime();
            String realisticPicture=stroeVoItem.getRealisticPicture();
            String[] realistp=realisticPicture.split(";");
            List list=new ArrayList();
            for(int i=0;i<realistp.length;i++){
                    Map relismap=new HashMap();
                    String re=realistp[i];
                relismap.put("realistp",re);
                list.add(relismap);
            }
            Map listpic=new HashMap();
            stroe.put("name",name);
            stroe.put("address",address);
            stroe.put("phone",phone);
            stroe.put("stroetime",stroetime);
            listpic.put("listcensepic",stroeVoItem.getLicensePic());
            listpic.put("businesslicense",stroeVoItem.getBusinesslicense());
            stroe.put("list",list);
            stroe.put("listpic",listpic);
            result.put("flag",1);
            result.put("stroe",stroe);
        }
        result.put("flag",1);
    }else{
        result.put("flag",0);
        return result;
    }
    return result;
    }
    @ApiOperation(value = "综合排序")
    @IgnoreAuth
    @PostMapping("sortinfo")
    public Object sortinfo(){
        Map<String,Object> result=new HashMap<>();
        JSONObject listjson=this.getJsonRequest();

            double lat=listjson.getDouble("lat");
            double lon=listjson.getDouble("lon");
            Integer storeType=listjson.getInteger("storeType");
            Map<String,Double> json=MapUtils.getAround(lon,lat,7000.0);
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
            Integer sendingfee=listjson.getInteger("sendingfee");
            Integer deliveryfee=listjson.getInteger("deliveryfee");
            Integer grade=listjson.getInteger("grade");
            Integer number=listjson.getInteger("number");
            Integer dist=listjson.getInteger("dist");
            if(sendingfee!=null){
                listmap.put("sidx","sendingfee");
                listmap.put("order","asc");
            }else if(deliveryfee!=null){
                listmap.put("sidx","deliveryfee");
                listmap.put("order","asc");
            }else  if(grade!=null){
                listmap.put("sidx","stroe_grade");
                listmap.put("order","desc");
            }else  if(number!=null){
                listmap.put("sidx","sales_volume");
                listmap.put("order","desc");
            }
            List<StroeVo> stroeVoList =stroeService.querysort(listmap);
            Double lon2=0.0;
            Double lat2=0.0;
            Double s=0.0;
            if(stroeVoList.size()==0){
                result.put("flg",0);
                return result;
            }else{
                DecimalFormat df = new DecimalFormat( "0.00");
                List listst=new ArrayList();
                for(StroeVo stroeVo : stroeVoList){
                    lon2= stroeVo.getLongitude();
                    lat2= stroeVo.getLatitude();
                    Integer store_status=stroeVo.getStoreStatus();
                    String time=stroeVo.getStoretime();
                    String[] stime=time.split("-");
                    String[] list=new String[2];
                    for(int i=0;i<stime.length;i++){
                        String re=stime[i];
                        list[i]=re;
                    }
                    String one=list[0];
                    String two=list[1];
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    Date date = new Date();
                    String datestr=sdf.format(date);
                    String newone=datestr+" "+one+":00";
                    String newtwo=datestr+" "+two+":00";
                    SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    Date date2 = new Date();
                    String sde2=sdf2.format(date2);
                    Date d2=null;
                    Date d3=null;
                    Date d4=null;
                    Long dd1=null;
                    Long dd2=null;
                    Long dd3=null;
                    try{
                        d2=sdf2.parse(newone);
                        d3=sdf2.parse(newtwo);
                        d4=sdf2.parse(sde2);
                        dd1=d2.getTime();
                        dd2=d3.getTime();
                        dd3=d4.getTime();
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    s=MapUtils.Distance(lon,lat,lon2,lat2);
                    Double  distance=s;
                    Double  distance1=Math.round(distance*10)/10.0;
                    listst.add(distance1);
                    stroeVo.setJuli2(distance1);
                    if(distance1>1000){
                        Double dstance3=distance1/1000;
                        Double distance2=(new Double(df.format(dstance3)));
                        String km="km";
                        String dstance4=distance2+km;
                        stroeVo.setJuli(dstance4);

                        if(store_status==1){
                            if(dd3 > dd1 && dd3 < dd2){
                                stroeVo.setBusiness(1);
                                result.put("flg",1);
                                result.put("stroeEntityList", stroeVoList);
                            }else {
                                stroeVo.setBusiness(2);
                                result.put("flg",1);
                                result.put("stroeEntityList", stroeVoList);
                            }
                        }
                    }else if(distance1<1000){
                        Double dstance2=(new Double(df.format(distance1)));
                        String m="m";
                        String dstance4=dstance2+m;
                        stroeVo.setJuli(dstance4);
                        if(store_status==1){
                            if(dd3 > dd1 && dd3 < dd2){
                                stroeVo.setBusiness(1);
                                result.put("flg",1);
                                result.put("stroeEntityList", stroeVoList);

                            }else {
                                stroeVo.setBusiness(2);
                                result.put("flg",1);
                                result.put("stroeEntityList", stroeVoList);

                            }
                        }
                    }
                }
                if(dist!=null){
                    Collections.sort(stroeVoList, new Comparator<StroeVo>(){
                        public int  compare(StroeVo s1,StroeVo s2){
                            if(s1.getJuli2()!=s2.getJuli2()){
                                int d1=Integer.parseInt(new DecimalFormat("0").format(s1.getJuli2()));
                                int d2=Integer.parseInt(new DecimalFormat("0").format(s2.getJuli2()));
                                int flag=d1-d2;
                                return flag;
                            }else {
//                                int d1=Integer.parseInt(new java.text.DecimalFormat("0").format(s1.getJuli2()));
//                                int d2=Integer.parseInt(new java.text.DecimalFormat("0").format(s2.getJuli2()));
                                return s1.getJuli2().compareTo(s2.getJuli2());
                            }
                        }
                    });
                    Iterator<StroeVo> iterator=stroeVoList.iterator();
                    while (iterator.hasNext()){
                        StroeVo stoe=iterator.next();
                        result.put("stoe",stoe);
                        return result;
                    }
                }
                }
        return result;
    }
}
