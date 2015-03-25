package com.ese.server.websocket;

import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.jboss.logging.Logger;
import org.springframework.stereotype.Component;

import net.sf.json.JSONObject;

/**
 * Tomcat 7.0.4 버전 이후에 포함되어 있는 tomcat-socket 및 websocket-api를 통해서
 * 실행되는 @ServerEndpoing 및 기타 어노테이션
 * 해당 버전 이전의 tomcat 서버에서 구동할 경우 실행되지 않으며 코드 에러 발생
 * 
 * @author GOEDOKID
 * @since 2015.03.17
 * @category class
 * @version 0.1
 */
@Component
@ServerEndpoint("/echo")
public class SocketServer {

	public static Logger logger = Logger.getLogger(SocketServer.class);
	
	/**
	 * 클라이언트 접속 정보
	 */
	static Set<Session> sessionUsers = Collections.synchronizedSet(new HashSet<Session>());
	
	/**
	 * 클라이언트 접속 시 session open
	 * session 정보 저장
	 *
	 * @author GOEDOKID
	 * @since 2015. 3. 18. 
	 * @param 
	 * @return
	 */
	@OnOpen
	public void handleOpen(Session userSession) {
		logger.debug("WebSocket Client open and add session."+ userSession.getId());
		sessionUsers.add(userSession);
	}

	/**
	 * 클라이언트에서 send 할 경우 모든 클라이언트에게 브로드케스팅 
	 *
	 * @author GOEDOKID
	 * @since 2015. 3. 18. 
	 * @param 
	 * @return
	 */
	@OnMessage
	public void handleMessage(String message) throws IOException {
		Iterator<Session> iterator = sessionUsers.iterator();
		logger.debug("Send message to all.");
		if(sessionUsers.size() > 0) {
			while(iterator.hasNext()) {
				iterator.next().getBasicRemote().sendText(JSONConverter(message, "message", "event"));
			}	
		} else {
			logger.debug("No Session List. No send destination.");
		}
		
	}

	/**
	 * 브라우저 종료 및 코드에 의해서 소켓이 종료될 경우 session에서 삭제
	 *
	 * @author GOEDOKID
	 * @since 2015. 3. 18. 
	 * @param 
	 * @return
	 */
	@OnClose
	public void handleClose(Session session) {
		logger.debug("Session remove complete."+session.getId());
		sessionUsers.remove(session);
	}
	
	/**
	 * 특정 내용에 대한 정보 JSON 변경
	 *
	 * @author GOEDOKID
	 * @since 2015. 3. 18. 
	 * @param 
	 * @return
	 */
	public String JSONConverter(String message, String command, String type) {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("type", type);
		jsonObject.put("command", command);
		jsonObject.put("message", message);
		return jsonObject.toString();
	}
}