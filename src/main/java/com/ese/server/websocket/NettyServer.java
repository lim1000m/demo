package com.ese.server.websocket;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.WebApplicationContext;

import com.ese.server.websocket.codec.ServerMessageDecoder;
import com.ese.server.websocket.codec.ServerMessageEncoder;
import com.ese.server.websocket.handler.NettyServerHandler;

public class NettyServer {
	
	public static Logger logger = LoggerFactory.getLogger(NettyServer.class);
	
	public static NettyServer instance = null;
	private int serverPort = 0;
	ServerBootstrap serverBootstrap = null;
	EventLoopGroup bossGroup = null; 
	EventLoopGroup workerGroup = null;
	private WebApplicationContext wac = null;
	
	public static synchronized NettyServer getinstance(String serverPortStr, WebApplicationContext wac) {
		return getinstance(Integer.parseInt(serverPortStr), wac);
	}
	public static synchronized NettyServer getinstance(int serverPort, WebApplicationContext wac) {
		if (instance == null) {
			instance = new NettyServer(serverPort, wac);
		}
		return instance;
	}
	
	public NettyServer() {
	}

	public NettyServer(int serverPort, WebApplicationContext wac) {
		this.serverPort = serverPort;
		this.wac = wac;
	}

	public void init() {
		// NioEventLoopGroup 는 멀티 쓰레드 이벤트 루프
		// bossGroup는 들어오는 연결을 수락
		// workerGroup는 연결의 트레픽 처리
		bossGroup = new NioEventLoopGroup(); 
		workerGroup = new NioEventLoopGroup();
		try {
			// ServerBootstrap는 서버설정 도우미 클래스
			serverBootstrap = new ServerBootstrap(); 
			serverBootstrap.group(bossGroup, workerGroup)
			// NioServerSocketChannel클래스를 이용하여 새로운 채널 생성 (인스턴스화)
					.channel(NioServerSocketChannel.class) 
					.childHandler(new ChannelInitializer<SocketChannel>() {
								@Override
								public void initChannel(SocketChannel ch)throws Exception {
									ChannelPipeline channelPipeline = ch.pipeline();
									channelPipeline.addLast("encoder", new ServerMessageEncoder() )		
													.addLast("decoder", new ServerMessageDecoder() )
													.addLast("handler", new NettyServerHandler(wac));
								}
							}).option(ChannelOption.SO_BACKLOG, 128) // worker thread 최대 겟수 
					.childOption(ChannelOption.SO_KEEPALIVE, true); // 연결 유지 셋팅

			// 바인딩 해서 들어오는 연결을 수락
			serverBootstrap.bind(this.serverPort).sync(); // 서버 시작
			logger.debug("Socket Server Bind "+ this.serverPort);
		} catch (Exception e) {
			// 소켓 바인드 에러
			logger.debug("SOCKET BIND ERROR "+ serverPort);
			e.getStackTrace();
			System.exit(1);
		} finally {
			// workerGroup.shutdownGracefully();
			// bossGroup.shutdownGracefully();
		}
	}

	/**
	 * 소켓서버 언바인드
	 */
	public void socServerStop() {
		//TODO 소켓 서버 stop 메소드 
		 workerGroup.shutdownGracefully();
		 bossGroup.shutdownGracefully();
	}

	public void destroy() {
		//TODO 소켓 서버 Destroy 메소드 
		workerGroup.shutdownGracefully();
		bossGroup.shutdownGracefully();
	}

}
