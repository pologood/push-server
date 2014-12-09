package com.easou.novelpush;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * 主启动程序
 */
public class Bootstrap {
    private AbstractApplicationContext ac;
    private ServerSocket serverSocket;
    public static final AtomicBoolean running = new AtomicBoolean(false);
    private int port = 9532;
    private static class SingletonHolder {
        static Bootstrap instatnce = new Bootstrap();
    }

    public static Bootstrap getInstance(){
        return SingletonHolder.instatnce;
    }

    public void start() {
        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            throw new RuntimeException("bind port error.", e);
        }
        ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        ac.registerShutdownHook();
    }

    public Object getBean(String beanId) {
        if (ac != null) {
            return ac.getBean(beanId);
        }else {
            return null;
        }
    }

    public void waitForStop() {
        while (true) {
            Socket socket = null;
            try {
                socket = serverSocket.accept();
                socket.setSoTimeout(6000);
                BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                if ("shutdown".equalsIgnoreCase(reader.readLine())) {
                    break;
                }
            } catch (SocketTimeoutException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
                break;
            } finally{
                if(socket != null){
                    try {
                        socket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

        }
    }

    public void stop() {
        ac.close();
    }

    public static void main(String[] args) {
        Bootstrap bootstrap = getInstance();
        bootstrap.start();
        bootstrap.waitForStop();
        bootstrap.stop();
    }

}
