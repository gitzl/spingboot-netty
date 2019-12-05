package com.rpc.netty;

import com.rpc.netty.proto.MessageBase;
import org.junit.Test;

import java.util.UUID;

public class MessageBaseTest {


    @Test
   public void testMsg() {
        MessageBase.Message message = MessageBase.Message.newBuilder()
                .setRequestId(UUID.randomUUID().toString())
                .setContent("hello netty and protoBuf").build();
        System.out.println("message: " + message.toString());
    }
}
