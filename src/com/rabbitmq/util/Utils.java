package com.rabbitmq.util;

import java.io.IOException;

import com.rabbitmq.basic.MessageQueueInit;
import com.rabbitmq.basic.Sender;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.publish.BroadCastMesg;

public class Utils {
			
	public static void main(String[] argv) throws Exception {
		unsubscribe("", BroadCastMesg.EXCHANGE_TYPE, BroadCastMesg.EXCHANGE_NAME);
	}
	
	public static void unsubscribe(String queuename,  String exchangeType, String exchangeName) throws IOException {
		Connection connection = MessageQueueInit.getConnection();
		Channel channel = connection.createChannel();

		try {
			channel.exchangeDelete(BroadCastMesg.EXCHANGE_NAME);
			channel.queueDelete(Sender.getQueueName());
		} finally {
			/*if (channel != null) {
				channel.close();
			}
			if (connection != null)
				connection.close();*/
		}
	}
}
