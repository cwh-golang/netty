package io.netty.example.echo.helper;

import io.netty.util.NettyRuntime;

/**
 * @author Stone
 * @date 2022/7/2
 */
public class Test {
    public static void main(String[] args) {
        System.out.println(NettyRuntime.availableProcessors());
        System.out.println(Runtime.getRuntime().availableProcessors());
    }
}
