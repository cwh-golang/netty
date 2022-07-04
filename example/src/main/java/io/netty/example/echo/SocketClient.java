package io.netty.example.echo;


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Date;

import com.google.common.base.Strings;
import lombok.extern.slf4j.Slf4j;


/**
 * @author Stone
 * @date 2022/6/26
 */

@Slf4j
//@Slf4j
public class SocketClient {

    private String ip;
    private int port;
    private Socket socket;
    private DataOutputStream dataOutputStream;
    private DataInputStream dataInputStream;
    private Date useDate; //连接使用时间,用于判断session失效

    public SocketClient(String ip, int port) {
        this.ip = ip;
        this.port = port;
        useDate = new Date();
    }

    public void connect() throws Exception {

        try {
            socket = new Socket();
            socket.setKeepAlive(true);
//            SocketAddress socketAddress = new InetSocketAddress(ip, port);
//            socket.connect(socketAddress, 1000); //某些服务器ping延迟高时要增加,否则会报错connect timeout
//            socket.close();
//            new Machine(new DataOutputStream(socket.getOutputStream())).start();
            new SocketTest(socket).start();

        } catch (Exception e) {
            e.printStackTrace();
//            socket = null;
//            throw new Exception("socket connect error ip:" + ip + ",port:" + port + ",Exception:" + e.getMessage());
        }
    }

    private static class Machine extends Thread {
        private DataOutputStream dataOutput;

        public Machine(DataOutputStream dataOutput) {
            super();
            this.dataOutput = dataOutput;
        }

        public void run() {
            try {
                while (true) {
                    dataOutput.write(Strings.repeat("hello server, ".concat(Thread.currentThread().getName()), 1000).getBytes());
                    dataOutput.flush();
                    Thread.sleep(10);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private static class SocketTest extends Thread {
        private Socket socket;

        public SocketTest(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try {
                int i = 0;
                socket.connect(new InetSocketAddress("localhost", 8007), 100);
                while (true) {
                    DataOutputStream dataOutputStream1 = new DataOutputStream(socket.getOutputStream());
                    byte[] bytes = Strings.repeat("hello server, ".concat(String.valueOf(i++)), 1000).getBytes();
                    dataOutputStream1.write(bytes);
                    dataOutputStream1.flush();
                    log.info("send msg content :{}", new String(bytes));
//                    socket.close();
                    Thread.sleep(10);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }


    public static void main(String[] args) throws InterruptedException {

        System.out.println("SocketClient main start");
        try {
            for (int i = 0; i < 1; i++) {
                System.out.println("----------try start----------");
                SocketClient socketClient = new SocketClient("localhost", 8007);
                socketClient.connect();
//                String strInput = "hello server !";
//                socketClient.write(strInput.getBytes(), strInput.length());
//                Thread.sleep(1000);
            }
        } catch (Exception e) {
            System.out.println("catch error:" + e.getMessage());
            e.printStackTrace();
        }
        System.out.println("SocketClient main end");
        while (true) {
            Thread.sleep(1000);
        }
    }

}