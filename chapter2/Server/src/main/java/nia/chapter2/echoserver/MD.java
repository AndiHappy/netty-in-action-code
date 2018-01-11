package nia.chapter2.echoserver;

import java.util.List;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

public final class MD extends ByteToMessageDecoder {

	@Override
	protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
		byte[] bytes = new byte[in.readableBytes()];
		in.readBytes(bytes);
		String value = new String(bytes);
		System.out.println("MessageDecoder received: "+value);
		// out中是否有值，决定是否向下继续运行
		out.add(value);
	}
}
