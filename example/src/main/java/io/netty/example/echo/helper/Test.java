package io.netty.example.echo.helper;

import io.netty.util.NettyRuntime;
import lombok.extern.slf4j.Slf4j;

/**
 * @author stone
 * @createTime 2022年07月01日 19:21:00
 */
@Slf4j
public class Test {
    public static void main(String[] args) {
        System.out.println(NettyRuntime.availableProcessors());
        System.out.println(Runtime.getRuntime().availableProcessors());
        log.info(String.valueOf(new Test().getClass()));
    }
}
