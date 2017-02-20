package com.rabbitmq.basic;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

public class Sender {

	private final static String QUEUE_NAME = "rabbit.basic";

	public static String getQueueName() {
		return QUEUE_NAME;
	}

	public static void main(String[] argv) throws Exception {
		Connection connection = MessageQueueInit.getConnection();
		Channel channel = connection.createChannel();

		channel.queueDeclare(QUEUE_NAME, false, false, false, null);
		
		for (int i = 1; i <= 10; i++) {
			String message = i+"";
			channel.basicPublish("", QUEUE_NAME, null, message.getBytes("UTF-8"));
			System.out.println(" [x] Sent '" + message + "'");
		}

		channel.close();
		connection.close();
	}
}
