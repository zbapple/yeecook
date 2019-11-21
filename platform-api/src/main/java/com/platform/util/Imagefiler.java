package com.platform.util;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.sql.Blob;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class Imagefiler {
    private  String calssName="com.mysql.jdbc.Driver";
    private  String url="jdbc:mysql://www.yeecook.com:3306/yeecook-shop";
    private  String user="root";
    private  String password="Yeecookadmin@!2018";
    private Connection conn=null;

    public void before() throws Exception{
        Class.forName(calssName);
        conn = DriverManager.getConnection(url, user, password);
    }
    public void after() throws Exception{
        conn.close();
    }
    public void  inset(String imageFilePath,Integer porid) throws Exception{
        FileInputStream fils=new FileInputStream(imageFilePath);
        ByteArrayOutputStream baos=new ByteArrayOutputStream();
        int let=-1;
        byte[] buf=new byte[1024];
        while ((let=fils.read(buf)) !=-1){
            baos.write(buf,0,let);
        }
        baos.close();
        fils.close();
        byte[] bytes=baos.toByteArray();
        Blob pic=conn.createBlob();
        pic.setBytes(1,bytes);
        String sql="Update promotion set promotion_yard= "+pic+"where id= "+porid+"";
        PreparedStatement ppst = conn.prepareStatement(sql);
//        ppst.setBlob(1,pic);
        ppst.execute();
        ppst.close();
    }
}
