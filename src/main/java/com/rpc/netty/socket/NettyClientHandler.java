package com.rpc.netty.socket;

import com.rpc.netty.proto.MessageBase;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NettyClientHandler extends SimpleChannelInboundHandler<MessageBase.Message> {

    Logger logger = LoggerFactory.getLogger(NettyServer.class);

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, MessageBase.Message message) throws Exception {
        logger.info("客户端收到消息：" + message.toString());
    }
}
