package com.rabbitmq.publish;

import com.rabbitmq.client.*;
import com.rabbitmq.basic.MessageQueueInit;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;

import java.io.IOException;

public class BroadCastReceiver2 {

	public static void main(String[] argv) throws Exception {
		Connection connection = MessageQueueInit.getConnection();
		Channel channel = connection.createChannel();

		channel.exchangeDeclare(BroadCastMesg.EXCHANGE_NAME, BroadCastMesg.EXCHANGE_TYPE);
//		String queueName = BroadCastMesg.getQueueName();
		String queueName = channel.queueDeclare().getQueue();
		channel.queueBind(queueName, BroadCastMesg.EXCHANGE_NAME, "");

		System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

		Consumer consumer = new DefaultConsumer(channel) {
			@Override
			public void handleDelivery(String consumerTag, Envelope envelope,
					AMQP.BasicProperties properties, byte[] body)
					throws IOException {
				String message = new String(body, "UTF-8");
				System.out.println(" [x] Received '" + message + "'");
			}
		};
		channel.basicConsume(queueName, true, consumer);
	}
}