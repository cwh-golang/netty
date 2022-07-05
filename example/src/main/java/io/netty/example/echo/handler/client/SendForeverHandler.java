package io.netty.example.echo.handler.client;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.example.echo.EchoClient;
import lombok.extern.slf4j.Slf4j;

/**
 * @author stone
 * @createTime 2022年07月04日 10:22:00
 */
@Slf4j
public class SendForeverHandler extends ChannelInboundHandlerAdapter {

    private final ByteBuf firstMessage;

    /**
     * Creates a client-side handler.
     */
    public SendForeverHandler() {
        firstMessage = Unpooled.buffer(EchoClient.SIZE);
        for (int i = 0; i < firstMessage.capacity(); i++) {
            firstMessage.writeByte((byte) i);
        }
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        log.info("channelActive");
        int i = 0;
        while (true) {
            String msg = "send message count ".concat(String.valueOf(i++));
            log.info(msg);
            ctx.writeAndFlush(firstMessage);

            try {
                Thread.sleep(1000);
            } catch (Exception e) {
                log.info("exception :", e);
            }

        }
    }
}
