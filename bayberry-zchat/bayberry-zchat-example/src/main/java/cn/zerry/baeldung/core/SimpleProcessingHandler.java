package cn.zerry.baeldung.core;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * 处理器
 *
 * @Author: linzengrui
 * @Date: 2020/5/6 20:41
 */
public class SimpleProcessingHandler extends ChannelInboundHandlerAdapter {

    private ByteBuf buffer;

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        System.out.println("Handler added");
        buffer = ctx.alloc().buffer(4);
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        System.out.println("Handler removed");
        buffer.release();
        buffer = null;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf m = (ByteBuf) msg;
        buffer.writeBytes(m);
        m.release();
        if(buffer.readableBytes() >= 4){
            RequestData requestData = new RequestData();
            requestData.setIntValue(buffer.readInt());

            ResponseData responseData = new ResponseData();
            responseData.setIntValue(requestData.getIntValue() * 2);

            ChannelFuture future = ctx.writeAndFlush(responseData);
            future.addListener(ChannelFutureListener.CLOSE);
        }

    }
}
