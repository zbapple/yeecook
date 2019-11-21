package com.platform.util;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.codec.binary.Base64;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.*;

public class UrlUtil {
    /**
     * 向指定 URL 发送POST方法的请求
     *
     * @param url
     *            发送请求的 URL
     * @param
     *
     * @return 所代表远程资源的响应结果
     */
    public static String sendPost(String url, Map<String, ?> paramMap) {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";

        String param = "";
        Iterator<String> it = paramMap.keySet().iterator();

        while (it.hasNext()) {
            String key = it.next();
            param += key + "=" + paramMap.get(key) + "&";
        }

        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("Accept-Charset", "utf-8");
            conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            // 发送请求参数
            out.print(param);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        // 使用finally块来关闭输出流、输入流
        finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return result;
    }
    public static String getToken() {
        try {
            Map<String, String> map = new LinkedHashMap<String, String>();
            map.put("grant_type", "client_credential");
            map.put("appid", "wxa2566230db99c9ab");// 改成自己的appid
            map.put("secret", "4c7a1efe2c6d45c03c5d47a53685923b"); //改成自己的secret
            String rt = UrlUtil.sendPost("https://api.weixin.qq.com/cgi-bin/token", map);
            JSONObject json = JSONObject.parseObject(rt);
            if (json.getString("access_token") != null || json.getString("access_token") != "") {
                System.out.println("token:" + json.getString("access_token"));
                return json.getString("access_token");
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }
    public static Map getminiqrQr(String sceneStr, String accessToken) {
        Map res=new HashMap();
        PrintWriter out = null;
        String result = "";
        InputStream inputStream=null;
        try {
            String url = "https://api.weixin.qq.com/wxa/getwxacodeunlimit?access_token=" + accessToken;
            Map<String, Object> param = new HashMap<>();
            param.put("scene", sceneStr);
            param.put("page", "pages/takeoutindex/takeoutindex");
            param.put("width", 280);
            param.put("auto_color", false);
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
//			conn.setRequestProperty("accept", "*/*");
//			conn.setRequestProperty("connection", "Keep-Alive");
//			conn.setRequestProperty("user-agent","Mozilla/4.0
//        (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
//			conn.setCharacterEncoding("gbk");
            conn.setRequestProperty("Content-Type", "application/json;charset-gbk");
            conn.setRequestProperty("responseType", "arraybuffer");


            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            // 发送请求参数
            out.print(param);
            // flush输出流的缓冲
            out.flush();
            //获取流数据
            inputStream = conn.getInputStream();


            // 将获取流转为base64格式
            byte[] data = null;
            ByteArrayOutputStream swapStream = new ByteArrayOutputStream();
            byte[] buff = new byte[100];
            int rc = 0;
            while ((rc = inputStream.read(buff, 0, 100)) > 0) {
                swapStream.write(buff, 0, rc);
            }
            data = swapStream.toByteArray();

//            result = new String(Base64.getEncoder().encode(data));
//			当import java.util.Base64;无法导入时，只能在网上找找其
//        他的jar包，写法换成下面这种
            result = new String(Base64.encodeBase64(data));


        }catch (Exception e) {
            System.out.println("发送 POST 请求出现异常！"+e);
            e.printStackTrace();
        }
        //使用finally块来关闭输出流、输入流
        finally{
            try{
                if(out!=null){
                    out.close();
                }
                if(inputStream!=null){
                    inputStream.close();
                }

            }
            catch(IOException ex){
                ex.printStackTrace();
            }
        }
        res.put("result",result);
        return res ;
    }
}
