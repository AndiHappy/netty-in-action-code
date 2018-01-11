package nia.chapter2.echoclient;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;
import io.netty.util.ReferenceCountUtil;

/**
 * Listing 2.3 ChannelHandler for the client
 *
 * @author <a href="mailto:norman.maurer@gmail.com">Norman Maurer</a>
 */
@Sharable
public class EchoClientHandler extends SimpleChannelInboundHandler<ByteBuf> {
    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        ctx.writeAndFlush(Unpooled.copiedBuffer("Netty rocks!",
                CharsetUtil.UTF_8));
    }

    @Override
    public void channelRead0(ChannelHandlerContext ctx, ByteBuf in) {
        System.out.println(
                "Client received: " + in.toString(CharsetUtil.UTF_8));
    }
    
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
         try {
        	 if(msg instanceof ByteBuf ){
        		 channelRead0(ctx, (ByteBuf) msg);
        	 }
        	 
        	 if( msg instanceof String){
        		 System.out.println("Client received: " +msg);
        	 }
         } finally {
        	 ReferenceCountUtil.release(msg);
         }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx,
        Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}
