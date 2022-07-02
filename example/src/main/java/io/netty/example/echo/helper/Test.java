package io.netty.example.echo.helper;

import io.netty.util.NettyRuntime;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Stone
 * @date 2022/7/2
 */
@Slf4j
public class Test {
    public static void main(String[] args) {
        System.out.println(NettyRuntime.availableProcessors());
        System.out.println(Runtime.getRuntime().availableProcessors());
        log.info(String.valueOf(new Test().getClass()));
    }
}
