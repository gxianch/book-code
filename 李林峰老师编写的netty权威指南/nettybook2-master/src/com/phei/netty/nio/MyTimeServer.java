package com.phei.netty.nio;

import java.nio.channels.Selector;

import io.netty.channel.socket.ServerSocketChannel;

public class MyTimeServer {
	public static void main(String[] args) {
		MyMultiplexerTimeServer timeServer = new MyMultiplexerTimeServer(8080);
		new Thread(timeServer, "NIO-MultiplexerTimeServer-001").start();
	 
	}
}
