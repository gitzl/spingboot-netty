package com.rpc.netty.controller;


import com.rpc.netty.proto.MessageBase;
import com.rpc.netty.socket.NettyClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class TestController {

    @Autowired
    private NettyClient nettyClient;

    @GetMapping("/send")
    public String send() {
        nettyClient.start();
        MessageBase.Message message = new MessageBase.Message()
                .toBuilder().setCmd(MessageBase.Message.CommandType.NORMAL)
                .setContent("hello netty")
                .setRequestId(UUID.randomUUID().toString()).build();
        nettyClient.sendMsg(message);
        return "send message success!";
    }

}
