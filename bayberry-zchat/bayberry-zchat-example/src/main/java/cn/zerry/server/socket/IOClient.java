package cn.zerry.server.socket;

import java.net.Socket;
import java.util.Date;

/**
 * 客户端
 *
 * @Author: linzengrui
 * @Date: 2020/4/21 16:53
 */
public class IOClient {
    public static void main(String[] args) {
        new Thread(() -> {
            try {
                Socket socket = new Socket("127.0.0.1", 8000);
                while (true){
                    try {
                        socket.getOutputStream().write((new Date() + ": hello world").getBytes());
                        Thread.sleep(2000);
                    }catch (Exception e){

                    }
                }
            }catch (Exception e){

            }
        }).start();
    }
}
