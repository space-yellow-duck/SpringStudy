package kr.cloud.web.controller;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

// webSocket 환경설정 클래스
@Configuration // application이 미리 읽어서 객체 생성할 수 있도록 추가
@EnableWebSocket // webSocket 사용을 명시(사용하겠다)
public class WebSocketConfig implements WebSocketConfigurer {

	@Override
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
		// 레지스트리 : 컴퓨터의 환경설정을 하는 공간
		registry.addHandler(new WebSocketHandler(), "/websocket")
		.setAllowedOrigins("*");
		// setAllowedOrigins : 동일 출처 정책에 대한 설정
		//	동일 출처 정책 : 다른 출처에서 가져온 resource 공유 제한
		// (*) => 출처가 동일하지 않아도 그냥 전부 승인하겠다.
		// 출처 : http://localhost:8081인데 8087포트로 요청해도 무조건 실행하겠다.
	}
	
}
