package io.netty.example.echo;

import java.util.concurrent.atomic.AtomicLong;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.AttributeKey;
import lombok.extern.slf4j.Slf4j;

/**
 * @author stone
 * @createTime 2022年06月30日 14:56:00
 */
@Slf4j
public class WorkerLogHandler extends ChannelInboundHandlerAdapter {

    private final AttributeKey<byte[]> srcdataAttrKey = AttributeKey.valueOf("srcdata");
    private static AtomicLong count = new AtomicLong(0);
    private Boolean pass = true;

    private boolean isLimited() {
        //可以根据设备的在线数量，进行动态阈值调整
        //在机器刚加入到elb里面去，这时，由于外部的流量巨大，这时应该限制连接数量为一个较小的值，比如 1000，这样能有效的保护
        //后端业务处理的线程和后端模块
        //随着连接的建立，
        //主要的目的上为了让设备连接的数量平滑增长，不会导致后端有洪峰，因为在建立连接的时候，业务处理相对比较复杂
        if (count.getAndIncrement() % 2 == 1) {
            return true;
        }
        return false;
    }

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        if (isLimited()) {
            System.out.println("andIncrement = " + count.get());
            log.info("channel closed");
            ctx.channel().attr(srcdataAttrKey);
            pass = false;
            ctx.channel().close();
        } else {
            log.info("channelRegistered");
            super.channelRegistered(ctx);
        }
    }

    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
//        if (ctx.channel().hasAttr(srcdataAttrKey)) {
        if (!pass) {
            log.info("skip super.channelUnregistered");
        } else {
            log.info("channelUnregistered");
            super.channelUnregistered(ctx);
        }
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
//        if (ctx.channel().hasAttr(srcdataAttrKey)) {
        if (!pass) {
            log.info("skip super.channelActive");
        } else {
            log.info("channelActive");
            super.channelActive(ctx);
        }
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
//        if (ctx.channel().hasAttr(srcdataAttrKey)) {
        if (!pass) {
            log.info("skip super.channelInactive");
        } else {
            log.info("channelInactive");
            super.channelInactive(ctx);
        }
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
//        if (ctx.channel().hasAttr(srcdataAttrKey)) {
        if (!pass) {
            log.info("skip super.channelRead");
        } else {
            log.info("channelRead");
            super.channelRead(ctx, msg);
        }
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
//        if (ctx.channel().hasAttr(srcdataAttrKey)) {
        if (!pass) {
            log.info("skip super.channelReadComplete");
        } else {
            log.info("channelReadComplete");
            super.channelReadComplete(ctx);
        }
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
//        if (ctx.channel().hasAttr(srcdataAttrKey)) {
        if (!pass) {
            log.info("skip super.userEventTriggered");
        } else {
            log.info("userEventTriggered");
            super.userEventTriggered(ctx, evt);
        }
    }

    @Override
    public void channelWritabilityChanged(ChannelHandlerContext ctx) throws Exception {
//        if (ctx.channel().hasAttr(srcdataAttrKey)) {
        if (!pass) {
            log.info("skip super.channelWritabilityChanged");
        } else {
            log.info("channelWritabilityChanged");
            super.channelWritabilityChanged(ctx);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
//        if (ctx.channel().hasAttr(srcdataAttrKey)) {
        if (!pass) {
            log.info("skip super.exceptionCaught");
        } else {
            log.info("exceptionCaught");
            super.exceptionCaught(ctx, cause);
        }
    }
}
