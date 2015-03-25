package com.ese.server.websocket.handler;

import org.apache.log4j.Logger;
import org.springframework.web.context.WebApplicationContext;

import io.netty.channel.AdaptiveRecvByteBufAllocator;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.RecvByteBufAllocator;

import com.ese.domain.GeneralMessage;
import com.ese.server.websocket.NettyServer;
import com.ese.server.websocket.SocketServer;

public class NettyServerHandler extends ChannelInboundHandlerAdapter  {
	
	static Logger logger = Logger.getLogger(NettyServerHandler.class);
	
	private String localPort;
	private SocketServer socketServer;
	
	WebApplicationContext wac = null;
	
	public NettyServerHandler(WebApplicationContext wac) {
		this.wac = wac;
		socketServer = (SocketServer) wac.getBean("socketServer");
	}
	
	@Override
	public void channelActive(ChannelHandlerContext ctx) {	// ChannelHandlerContext 오픈시 실행 ( 처음 접속시 )
		localPort = ctx.channel().localAddress().toString().split(":")[1];
		String remoteIp = ctx.channel().remoteAddress().toString().substring(1).split(":")[0];
	}
	
	//ByteBuf 싸이즈 변경 replaying decoder 사용시 replay 횟수 감소
	private static final RecvByteBufAllocator recvByteBufAllocator = new AdaptiveRecvByteBufAllocator(1024, 8192, 32768);
	@Override
	public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
		// TODO Auto-generated method stub
		ctx.channel().config().setRecvByteBufAllocator(recvByteBufAllocator); 
	}
	
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		
		GeneralMessage message = (GeneralMessage) msg;
		logger.debug("[MESSAGE RECEIVE SUCCESS]");
		logger.debug("body = "+ new String(message.getBody(), "UTF-8"));
		
		socketServer.handleMessage(new String(message.getBody(), "UTF-8"));
	}
	
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
    	logger.debug(cause.getMessage());
        ctx.close();
    }    
    
}