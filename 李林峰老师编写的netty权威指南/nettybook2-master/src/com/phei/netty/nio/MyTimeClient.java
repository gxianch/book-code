package com.phei.netty.nio;

public class MyTimeClient {
	public static void main(String[] args) {
		new Thread(new MyTimeClientHandler("127.0.0.1", 8080), "TimeClient-001")
		.start();
	}
}
