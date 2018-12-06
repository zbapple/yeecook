package com.platform.charles.xcf;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class XcfCharles {

    public static void main(String[] args) {

    }

    public List<JsonRootBean> getData(){
        Map map =new HashMap();
        map.put("cursor","");
        map.put("size","20");
        Gson gson=new Gson();
        List<JsonRootBean> list =new ArrayList();
        JsonRootBean jsonRootBean=new JsonRootBean();
        String  r= XcfHttpUtil.URLGet("https://www.xiachufang.com/juno/api/v2/courses/all.json?",map, XcfHttpUtil.URL_PARAM_DECODECHARSET_UTF8);
        jsonRootBean=gson.fromJson(r, JsonRootBean.class);
        if(null!=jsonRootBean&&jsonRootBean.getStatus().equals("ok")){
            list.add(jsonRootBean);
        }

        while (jsonRootBean.getStatus().equals("ok")&&jsonRootBean.getContent().getCursor().getHas_next()){
            map.put("cursor",jsonRootBean.getContent().getCursor().getNext());
            r= XcfHttpUtil.URLGet("https://www.xiachufang.com/juno/api/v2/courses/all.json?",map, XcfHttpUtil.URL_PARAM_DECODECHARSET_UTF8);
            jsonRootBean=gson.fromJson(r, JsonRootBean.class);
            if(null!=jsonRootBean&&jsonRootBean.getStatus().equals("ok")){
                list.add(jsonRootBean);
                System.out.println(jsonRootBean.getStatus());
            }
        }

        return list;
    }

}