package com.ese.server.websocket.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.nio.ByteOrder;
import java.util.List;

import com.ese.config.init.DWGConstants;
import com.ese.domain.GeneralMessage;
import com.ese.util.CommUtil;
import com.ese.util.HeaderUtil;

/**
 * Class Name 	: ServerMessageDecoder.java
 * Description 	: 서버 메시지 디코더
 * Modification Information
 *
 *       수정일			수정자			수정내용
 *    -----------      -----------     	---------------------
 *    
 * 
 * @author 
 * @since 2014.10
 * @version 1.0
 */
public class ServerMessageDecoder extends ByteToMessageDecoder {
	
	
	@Override
	protected void decode(ChannelHandlerContext ctx, ByteBuf in,
			List<Object> out) throws Exception {
		
		try {
			// 수신된 메시지가 최소한 헤더 사이즈 크기
			if (in.readableBytes() < HeaderUtil.getHeaderTotalLen()) {
//				COMLog.error("MESSIGE SIZE ERROR");
				return;
			}
			
//			 읽는 위치 마크
//			in.markReaderIndex();
			
			// 헤더 읽기 
			byte[] headerArr = new byte[HeaderUtil.getHeaderTotalLen()];
			in.readBytes(headerArr);
			
			// 헤더 타입 코드
			byte[] headerTypCdByte = new byte[HeaderUtil.getHeaderItemLength(HeaderUtil.HEDR_TYP_CD)];
			System.arraycopy(headerArr, HeaderUtil.getHeaderItemStartPos(HeaderUtil.HEDR_TYP_CD), headerTypCdByte, 0, HeaderUtil.getHeaderItemLength(HeaderUtil.HEDR_TYP_CD));
			String headerTypCd = new String(headerTypCdByte,"UTF-8");
			
			// MEP
			byte[] mepArr = new byte[HeaderUtil.getHeaderItemLength(HeaderUtil.MSG_EXCH_PATRN)];
			System.arraycopy(headerArr, HeaderUtil.getHeaderItemStartPos(HeaderUtil.MSG_EXCH_PATRN), mepArr, 0, HeaderUtil.getHeaderItemLength(HeaderUtil.MSG_EXCH_PATRN));
			String mep = new String(mepArr, "UTF-8");
			
			// 바디 사이즈 읽기
			byte[] bodyLenArr = new byte[HeaderUtil.getHeaderItemLength(HeaderUtil.BODY_LEN)];
			System.arraycopy(headerArr, HeaderUtil.getHeaderItemStartPos(HeaderUtil.BODY_LEN), bodyLenArr,0 , HeaderUtil.getHeaderItemLength(HeaderUtil.BODY_LEN));
			int bodyLen = CommUtil.byteToInt(bodyLenArr, ByteOrder.LITTLE_ENDIAN);	// byte[] - > int  (little endian)
			
			// 메시지 교환 방식에 따른 로직
			if (mep.equals(DWGConstants.MSG_EXCH_PTRN.ONE_WAY)) {	// one way
				
			} else if (mep.equals(DWGConstants.MSG_EXCH_PTRN.ONE_WAY_ACK)) {	// one way ack
				GeneralMessage gm = new GeneralMessage();
				gm.setHeader(headerArr);
				ctx.writeAndFlush(gm);
			}
			
			// 남은 길이와 바디사이즈 일치 체크
			if (in.readableBytes() == bodyLen) {
				// 바디 읽기
				byte[] bodyArr = new byte[bodyLen];
				in.readBytes(bodyArr);
				
				// GeneralMessage 셋팅
				GeneralMessage message = new GeneralMessage();
				message.setHeaderTyp(headerTypCdByte);
				message.setHeader(headerArr);
				message.setBody(bodyArr);
				
				// 핸들러로 전달
				out.add(message);
				
				return;
			} else {
				return;
			}
			
			
		} catch(Exception e) {
			e.getStackTrace();
			return;
		}
		
	}
	
 

}
