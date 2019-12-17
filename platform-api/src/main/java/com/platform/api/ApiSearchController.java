package com.platform.api;

import com.alibaba.fastjson.JSONObject;
import com.platform.annotation.IgnoreAuth;
import com.platform.annotation.LoginUser;
import com.platform.entity.*;
import com.platform.service.*;
import com.platform.util.ApiBaseAction;
import com.platform.util.MapUtils;
import com.platform.utils.Query;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * API登录授权
 *
 * @author lipengjun
 * @email 939961241@qq.com
 * @date 2017-03-23 15:31
 */
@Api(tags = "商品搜索")
@RestController
@RequestMapping("/api/search")
public class ApiSearchController extends ApiBaseAction {
    @Autowired
    private ApiKeywordsService keywordsService;
    @Autowired
    private ApiSearchHistoryService searchHistoryService;
    @Autowired
    private ApiStroeService stroeService;
    @Autowired
    private ApiGoodsService goodsService;
    @Autowired
    private ApiStroeTypeService stroeTypeService;
    /**
     * 　　index
     */
    @ApiOperation(value = "搜索商品列表")
    @PostMapping("index")
    public Object index(@LoginUser UserVo loginUser) {
        Map<String, Object> resultObj = new HashMap();
        Map param = new HashMap();
        param.put("is_default", 1);
        param.put("page", 1);
        param.put("limit", 1);
        param.put("sidx", "id");
        param.put("order", "asc");
        List<KeywordsVo> keywordsEntityList = keywordsService.queryList(param);
        //取出输入框默认的关键词
        KeywordsVo defaultKeyword = null != keywordsEntityList && keywordsEntityList.size() > 0 ? keywordsEntityList.get(0) : null;
        //取出热闹关键词
        param = new HashMap();
        param.put("fields", "distinct keyword,is_hot");
        param.put("page", 1);
        param.put("limit", 10);
        param.put("sidx", "id");
        param.put("order", "asc");
        Query query = new Query(param);
        List<Map> hotKeywordList = keywordsService.hotKeywordList(query);
        //
        param = new HashMap();
        param.put("user_id", loginUser.getUserId());
        param.put("fields", "distinct keyword,id");
        param.put("page", 1);
        param.put("limit", 10);
        param.put("sidx", "id");
        param.put("order", "asc");
        List<SearchHistoryVo> historyVoList = searchHistoryService.queryList(param);
        String[] historyKeywordList = new String[historyVoList.size()];
        if (null != historyVoList) {
            int i = 0;
            for (SearchHistoryVo historyVo : historyVoList) {
                historyKeywordList[i] = historyVo.getKeyword();
                i++;
            }
        }
        //
        resultObj.put("defaultKeyword", defaultKeyword);
        resultObj.put("historyKeywordList", historyKeywordList);
        resultObj.put("hotKeywordList", hotKeywordList);
        return toResponsSuccess(resultObj);
    }

    /**
     * 　　helper
     */
    @ApiOperation(value = "搜索商品")
    @ApiImplicitParams({@ApiImplicitParam(name = "keyword", value = "关键字", paramType = "path", required = true)})
    @PostMapping("helper")
    public Object helper(@LoginUser UserVo loginUser) {
        JSONObject jsonhelper=this.getJsonRequest();
        String keyword=jsonhelper.getString("keyword");
        Map<String,Object> result=new HashMap<>();
        Map param = new HashMap();
        param.put("fields", "distinct keyword");
        param.put("keyword", keyword);
        param.put("limit", 10);
        param.put("offset", 0);
        Map prm=new HashMap();
        prm.put("name",keyword);
        List<KeywordsVo> keywords = keywordsService.queryList(param);
        String[] keys = new String[keywords.size()];
        if (null != keywords) {
            int i = 0;
            for (KeywordsVo keywordsVo : keywords) {
                keys[i] = keywordsVo.getKeyword();
                i++;
            }
        }
//        prm.put("is_on_sale",1);
        List<GoodsVo> goodsVos=goodsService.querylistgood(prm);
        if(goodsVos!=null){
            Integer sale=0;
            for(GoodsVo goodsVo:goodsVos){
                sale=goodsVo.getIs_on_sale();
            }
            if(sale==1){
                SearchHistoryVo region=new SearchHistoryVo();
                Date date=new Date();
                SimpleDateFormat simpleDateFormat=new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
                region.setAdd_time(simpleDateFormat.format(date));
                region.setKeyword(keyword);
                region.setFrom("小程序");
                region.setUser_id(String.valueOf(loginUser.getUserId()));
                searchHistoryService.save(region);
                result.put("goodsVos",goodsVos);
                result.put("flag",1);
                return result;
            }else{
                result.put("flag",0);
                return result;
            }
        }
        //
        return toResponsSuccess("成功");
    }

    /**
     * 　　clearhistory
     */
        @PostMapping("clearhistory")
    public Object clearhistory(@LoginUser UserVo loginUser) {
        searchHistoryService.deleteByUserId(loginUser.getUserId());
        //
        return toResponsSuccess("");
    }


    @ApiOperation(value = "搜索门店")
    @PostMapping("seachstroe")
    public  Object seachestroe(@LoginUser UserVo loginUser){
        Map<String,Object> result=new HashMap<>();
        JSONObject jsonseach=this.getJsonRequest();
        String keyword=jsonseach.getString("keyword");
        Map pram=new HashMap();
        pram.put("name",keyword);
        List<StroeVo> stroeList=stroeService.queryList(pram);
        List<StroeTypeVo> stroeTypeVos=stroeTypeService.queryList(pram);
        if(stroeList.size()>0||stroeTypeVos.size()>0){
        try {
            double lat=jsonseach.getDouble("lat");
            double lon=jsonseach.getDouble("lon");
            Integer stroeType=0;

            if(stroeTypeVos.size()>0){
                for(StroeTypeVo stroeTypeVo:stroeTypeVos){
                    stroeType=stroeTypeVo.getId();
                }
            }
            Integer stroetype=0;
            if(stroeList.size()>0){
                for(StroeVo stroeVo:stroeList){
                    stroetype=stroeVo.getStoreType();
                }
            }
            Map<String,Double> json= MapUtils.getAround(lon,lat,7000.0);
            Double minLng=json.get("minLng");
            Double maxLng=json.get("maxLng");
            Double minLat=json.get("minLat");
            Double maxLat=json.get("maxLat");
            Map listmap=new HashMap();
            listmap.put("minLng",minLng);
            listmap.put("minLat",minLat);
            listmap.put("maxLng",maxLng);
            listmap.put("maxLat",maxLat);
            if (stroeTypeVos.size()!=0){
//                if(stroetype==stroeType){
                    listmap.put("stroeType",stroeType);
//                }
            }
            if(stroeList.size()!=0){
                listmap.put("name",keyword);
            }
            SearchHistoryVo region=new SearchHistoryVo();
            Date date=new Date();
            SimpleDateFormat simpleDateFormat=new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
            region.setAdd_time(simpleDateFormat.format(date));
            region.setKeyword(keyword);
            region.setFrom("小程序");
            region.setUser_id(String.valueOf(loginUser.getUserId()));
            searchHistoryService.save(region);
            List<StroeVo> stroeVoList =stroeService.queryList(listmap);
            Double lon2=0.0;
            Double lat2=0.0;
            Double s=0.0;
            Map stroemap=new HashMap();
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
                    Date date1 = new Date();
                    String datestr=sdf.format(date1);
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
                        return result;
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
                        return result;
                    }
                }
            }
        }catch (Exception e) {
                return toResponsMsgSuccess("请联系管理员");
        }
        }else{
            result.put("flg",0);
            return result;
        }

        return result;
    }
}
