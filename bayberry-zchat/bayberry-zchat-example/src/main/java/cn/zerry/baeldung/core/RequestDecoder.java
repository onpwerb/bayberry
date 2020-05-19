package cn.zerry.baeldung.core;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ReplayingDecoder;

import java.nio.charset.Charset;
import java.util.List;

/**
 * 请求数据 解码器
 *
 * @Author: linzengrui
 * @Date: 2020/5/6 23:53
 */
public class RequestDecoder extends ReplayingDecoder<RequestData> {

    private final Charset charset = Charset.forName("UTF-8");

    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        RequestData requestData = new RequestData();

        int strLen = byteBuf.readInt();
        requestData.setIntValue(strLen)
                .setStringValue(byteBuf.readCharSequence(strLen, charset).toString());

        list.add(requestData);
    }
}
