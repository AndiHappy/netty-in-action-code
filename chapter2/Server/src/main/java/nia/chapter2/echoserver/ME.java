package nia.chapter2.echoserver;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public final class ME extends MessageToByteEncoder<String> {

	@Override
	protected void encode(ChannelHandlerContext ctx, String packet, ByteBuf out) throws Exception {
		packet = packet + " encode";
		out.writeBytes(packet.getBytes());
	}
}