package com.platform.util;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HttpXmlClient {
    /*
     * get请求获取toker值
     * */
    public static String sendGet(String url, String param){
        String result = "";
        BufferedReader in = null;
        String urlNameString = url;
        try {
            URL realUrl = new URL(urlNameString);
            URLConnection connection = realUrl.openConnection();
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent","Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 建立实际的连接
            connection.connect();
            // 获取所有响应头字段
            Map<String, List<String>> map = connection.getHeaderFields();
            // 遍历所有的响应头字段
            for (String key : map.keySet()) {
                System.out.println(key + "--->" + map.get(key));
            }
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            System.out.println(in);

            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }

    /*
     * post请求返回base64的图片数据
     * */
    //
    public static String sendPost3(String url,String sceneStr) {
        PrintWriter out = null;
        String result = "";
        InputStream inputStream=null;
        //请求数据，自行拼接
        String param="{ \"scene\":\""+sceneStr+"\" ,\"page\":\"pages/cookbook/cookbook\",\"width\":280}";
        System.out.print(param);
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
//			conn.setRequestProperty("accept", "*/*");
//			conn.setRequestProperty("connection", "Keep-Alive");
//			conn.setRequestProperty("user-agent","Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
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
//            result=new String(data);
            result = new String(Base64.getEncoder().encode(data));
//			当import java.util.Base64;无法导入时，只能在网上找找其他的jar包，写法换成下面这种
//			result = new String(Base64.encodeBase64(data));


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
        return result;
    }

}
