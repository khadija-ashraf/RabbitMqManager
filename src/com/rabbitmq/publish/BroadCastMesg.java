package com.rabbitmq.publish;

import com.rabbitmq.basic.MessageQueueInit;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;

public class BroadCastMesg {

	public static final String EXCHANGE_NAME = "logs";
	public static final String EXCHANGE_TYPE = "fanout";

	public static void main(String[] argv) throws Exception {
		Connection connection = MessageQueueInit.getConnection();
		Channel channel = connection.createChannel();

		channel.exchangeDeclare(EXCHANGE_NAME, EXCHANGE_TYPE);

		String message = getMessage(argv);

		for (int h = 1; h <= 2; h++) {
			for (int i = 1; i <= 10; i++) {
				message = i+"";
				channel.basicPublish(EXCHANGE_NAME, "", null, message.getBytes("UTF-8"));
				System.out.println(" [x] Sent '" + message + "'");
			}
			Thread.sleep(5000);
		}
	}

	private static String getMessage(String[] strings) {
		if (strings.length < 1)
			return "info: Hello World!";
		return joinStrings(strings, " ");
	}

	private static String joinStrings(String[] strings, String delimiter) {
		int length = strings.length;
		if (length == 0)
			return "";
		StringBuilder words = new StringBuilder(strings[0]);
		for (int i = 1; i < length; i++) {
			words.append(delimiter).append(strings[i]);
		}
		return words.toString();
	}
}
