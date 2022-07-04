package io.netty.example.echo.handler.server;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

/**
 * @author stone
 * @createTime 2022年07月04日 10:16:00
 */
@ChannelHandler.Sharable
@Slf4j
public class BlockHandler extends SimpleChannelInboundHandler<String> {
//    @Override
//    public void channelRead(ChannelHandlerContext ctx, Object msg) {
//        log.info("channelRead, ctx:{} , thread :{}", ctx, Thread.currentThread().getName());
//        try {
//            Thread.sleep(1000);
//        } catch (Exception e) {
//            log.info("exception :", e);
//        }
//    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        log.info("channelRead, msg:{} , thread :{}", msg, Thread.currentThread().getName());
        try {
            Thread.sleep(1000);
        } catch (Exception e) {
            log.info("exception :", e);
        }
    }

//    @Override
//    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
//        log.info("channelReadComplete");
//        super.channelReadComplete(ctx);
//    }
//
//    @Override
//    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
//        // Close the connection when an exception is raised.
//        cause.printStackTrace();
//        ctx.close();
//    }

}
