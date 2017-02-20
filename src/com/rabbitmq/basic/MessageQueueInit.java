package com.rabbitmq.basic;

import java.io.IOException;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class MessageQueueInit {
	private static ConnectionFactory factory;
	private static String host = "127.0.0.1";
	private static String virtualHost = "/";
	private static String userName = "guest";
	private static String password = "guest";

	public static Connection getConnection() throws IOException {
		if (factory == null) {
			factory = new ConnectionFactory();
			factory.setHost(host);
			factory.setVirtualHost(virtualHost);
			factory.setUsername(userName);
			factory.setPassword(password);
		}
		return factory.newConnection();
	}
}
