package cn.zerry.baeldung.core;

import cn.zerry.baeldung.core.ResponseData;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * 返回数据 加密
 *
 * @Author: linzengrui
 * @Date: 2020/5/6 23:56
 */
public class ResponseEncoder extends MessageToByteEncoder<ResponseData> {
    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, ResponseData responseData, ByteBuf byteBuf) throws Exception {
        byteBuf.writeInt(responseData.getIntValue());
    }
}
