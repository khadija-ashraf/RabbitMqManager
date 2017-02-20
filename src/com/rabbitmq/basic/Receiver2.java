package com.rabbitmq.basic;

import java.io.IOException;

import com.rabbitmq.client.*;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;

public class Receiver2 {

	public static void main(String[] argv) throws java.io.IOException,
			java.lang.InterruptedException {

		Connection connection = MessageQueueInit.getConnection();
		Channel channel = connection.createChannel();

		channel.queueDeclare(Sender.getQueueName(), false, false, false, null);
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
		channel.basicConsume(Sender.getQueueName(), true, consumer);
	}
}
