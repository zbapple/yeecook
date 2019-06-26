package com.platform.websocket;

import javax.servlet.http.HttpSession;
import javax.websocket.HandshakeResponse;
import javax.websocket.server.HandshakeRequest;
import javax.websocket.server.ServerEndpointConfig;

/**
 * @Author: 17
 * @Email: inaoie@163.com
 * @Date: 2019-05-29
 * @Time: 16:57
 */
public class HttpSessionConfigurator extends ServerEndpointConfig.Configurator {
    public void modifyHandshake(ServerEndpointConfig sec, HandshakeRequest request, HandshakeResponse response) {
             HttpSession httpSession = (HttpSession) request.getHttpSession();
             sec.getUserProperties().put(HttpSession.class.getName(), httpSession);
        }

}
