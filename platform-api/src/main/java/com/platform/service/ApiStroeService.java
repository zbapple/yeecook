package com.platform.service;

import com.platform.dao.ApiStroeMapper;
import com.platform.entity.StroeVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.URL;
import java.util.List;
import java.util.Map;

/**
 * Service实现类
 *
 * @author zoubin
 * @email 9379248@qq.com
 * @date 2019-09-19 10:11:30
 */
@Service
public class ApiStroeService  {
    @Autowired
    private ApiStroeMapper stroeDao;

    public StroeVo queryObject(Integer id) {
        return stroeDao.queryObject(id);
    }

    public List<StroeVo> queryList(Map<String, Object> map) {
        return stroeDao.queryList(map);
    }

    public List<StroeVo> querysort(Map<String,Object> map){return  stroeDao.querysort(map);}

    public int queryTotal(Map<String, Object> map) {
        return stroeDao.queryTotal(map);
    }

    public int save(StroeVo stroe) {
        return stroeDao.save(stroe);
    }


    public int update(StroeVo stroe) {
        return stroeDao.update(stroe);
    }


    public int delete(Integer id) {
        return stroeDao.delete(id);
    }

    public int deleteBatch(Integer[] ids) {
        return stroeDao.deleteBatch(ids);
    }


    public static String getAdd(String log, String lat ){
        //lat 小 log 大
        //参数解释: 纬度,经度 type 001 (100代表道路，010代表POI，001代表门址，111可以同时显示前三项)
        String urlString = "http://gc.ditu.aliyun.com/regeocoding?l="+lat+","+log+"&type=010";
        String res = "";
        try {
            URL url = new URL(urlString);
            java.net.HttpURLConnection conn = (java.net.HttpURLConnection)url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            java.io.BufferedReader in = new java.io.BufferedReader(new java.io.InputStreamReader(conn.getInputStream(),"UTF-8"));
            String line;
            while ((line = in.readLine()) != null) {
                res += line+"\n";
            }
            in.close();
        } catch (Exception e) {
            System.out.println("error in wapaction,and e is " + e.getMessage());
        }
        System.out.println(res);
        return res;
    }

}
