package cn.zerry.server.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;

/**
 * TODO
 *
 * @Author: linzengrui
 * @Date: 2020/5/3 14:44
 */
public class NettyServer {
    public static void main(String[] args) {
        ServerBootstrap serverBootstrap = new ServerBootstrap();

        // 线程组，监听端口，accept新连接的线程组
        NioEventLoopGroup boss = new NioEventLoopGroup();
        // 处理每一条连接的数据读写的线程组
        NioEventLoopGroup worker = new NioEventLoopGroup();

        serverBootstrap
                // 配置两大线程组
                .group(boss, worker)
                // 指定IO模型为 NIO
                .channel(NioServerSocketChannel.class)
                // 定义后续每条连接的数据读写，业务逻辑
                // NIO : NioServerSocketChannel, NioSocketChannel
                // BIO : ServerSocket, Socket
                .childHandler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel nioSocketChannel) throws Exception {
                        nioSocketChannel.pipeline().addLast(new StringDecoder());
                        nioSocketChannel.pipeline().addLast(new SimpleChannelInboundHandler<String>() {
                            @Override
                            protected void channelRead0(ChannelHandlerContext channelHandlerContext, String s) throws Exception {
                                System.out.println(s);
                            }
                        });
                    }
                })
                //
                .bind(8000)
//                .bind(serverBootstrap, 8000)
        ;
    }

    private static void bind(final ServerBootstrap serverBootstrap, final int port){
        serverBootstrap
                .bind(port)
                // 监测端口是否绑定成功
                .addListener(new GenericFutureListener<Future<? super Void>>() {
                    @Override
                    public void operationComplete(Future<? super Void> future) throws Exception {
                        if(future.isSuccess()){
                            System.out.println("端口绑定成功");
                        }else {
                            System.err.println("端口绑定失败");
                        }
                    }
                });
    }
}
