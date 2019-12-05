package com.rpc.netty.socket;

import com.rpc.netty.proto.MessageBase;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NettyServerHandler  extends SimpleChannelInboundHandler<MessageBase.Message> {

    Logger logger = LoggerFactory.getLogger(NettyServer.class);

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageBase.Message message) throws Exception {
        if (message.getCmd().equals(MessageBase.Message.CommandType.NORMAL)) {
                logger.info("收到客户端信息： " + message.toString());
        }
    }
}
