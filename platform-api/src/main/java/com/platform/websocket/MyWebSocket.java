package com.platform.websocket;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;


@ServerEndpoint("/websocket")
public class MyWebSocket {
    //静态变量，表示连接数
    private  static  int onlineCount=0;
    //concurrent线程安全，set用来存放MyWebSocket的对象
    private  static CopyOnWriteArraySet<MyWebSocket> webSocketSet=new CopyOnWriteArraySet<MyWebSocket>();
    //与客户端的连接会话
    private Session session;

    /**
     * 连接调用
     * @param session
     */
    @OnOpen
    public void  onOpen(Session session){
        this.session=session;
        webSocketSet.add(this);
        addonlineCount();
        System.out.println("有新连接加入，当前在线人数为："+getonlieCount());
    }
    /**
     * 关闭连接
     */
    @OnClose
    public void onClose(){
        webSocketSet.remove(this);
        subonlineCount();
        System.out.println("连接关闭，当前在线人数为:"+getonlieCount());
    }

    /**
     *
     * @param message
     * @param session
     */
    @OnMessage
    public  void  onMessage(String message,Session session){
       System.out.println("来自客户端"+getonlieCount()+"的消息："+message);
       for (MyWebSocket ms:webSocketSet){
           try {
               ms.sendMessage(message);
           } catch (IOException e) {
               e.printStackTrace();
               continue;
           }
       }
    }



    /**
     * 异常处理
     * @param session
     * @param T
     */
    @OnError
    public void onError(Session session,Throwable T){
        System.out.println("有错误信息");
        T.printStackTrace();

    }

    /**
     * 发送消息
     * @param message
     * @throws IOException
     */
    public void sendMessage(String message) throws IOException {
        this.session.getBasicRemote().sendText(message);

    }
    //人数增加
    public static synchronized  void addonlineCount(){
        MyWebSocket.onlineCount++;
    }
    //人数减少
    public static synchronized  void subonlineCount(){
        MyWebSocket.onlineCount--;
    }
    //返回所求人数{
    public static synchronized  int getonlieCount(){
        return onlineCount;
    }
}
