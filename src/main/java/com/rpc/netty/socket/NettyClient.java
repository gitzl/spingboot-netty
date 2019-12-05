package com.rpc.netty.socket;

import com.rpc.netty.proto.MessageBase;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.concurrent.TimeUnit;

@Component
public class NettyClient {
    Logger logger = LoggerFactory.getLogger(NettyClient.class);

    private EventLoopGroup group = new NioEventLoopGroup();

    @Value("${netty.port}")
    private int port;

    @Value("${netty.host}")
    private String host;

    private SocketChannel channel;

    public void sendMsg(MessageBase.Message message) {
        channel.writeAndFlush(message);
    }


    @PostConstruct
    public void start() {
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(group)
                    .channel(NioSocketChannel.class)
                    .option(ChannelOption.SO_KEEPALIVE, true)
                    .handler(new ClientHandlerInitializer());
            logger.info("连接的host:" + host + "  port is : " + port);
            ChannelFuture future = bootstrap.connect(host, port);

            future.addListener((ChannelFutureListener) future1 -> {
                if (future1.isSuccess()) {
                    logger.info("连接Netty服务端成功");
                } else {
                    logger.info("连接失败，进行断线重连");
                    future1.channel().eventLoop().schedule(() -> start(), 20, TimeUnit.SECONDS);
                }
            });

            channel = (SocketChannel) future.channel();
        } catch (Exception e) {
            logger.info("连接异常：" + e.getMessage());
        }
    }
}
