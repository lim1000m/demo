package com.ese.server.websocket.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

import java.io.UnsupportedEncodingException;

import com.ese.comm.util.log.COMLog;
import com.ese.domain.GeneralMessage;

public class ServerMessageEncoder extends MessageToByteEncoder<GeneralMessage> {
    
	@Override
    protected void encode(final ChannelHandlerContext ctx,GeneralMessage gm, ByteBuf out) {
		try {
			COMLog.trace("ACK RETURN [{}]", new String(gm.getHeader(),"UTF-8"));
			out.writeBytes(gm.getHeader());
			out.release();
			ctx.flush();
//			out.writeBytes(gm.getBody());
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}