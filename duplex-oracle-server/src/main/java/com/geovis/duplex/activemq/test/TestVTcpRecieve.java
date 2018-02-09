package com.geovis.duplex.activemq.test;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

public class TestVTcpRecieve implements Runnable {
	@Override
	public void run() {
		try {

			ConnectionFactory connectionFactory = 
					new ActiveMQConnectionFactory(ActiveMQConnection.DEFAULT_USER,
							ActiveMQConnection.DEFAULT_PASSWORD, 
							"failover:(tcp://192.168.44.4:61616?wireFormat.maxInactivityDuration=10000)&amp;maxReconnectDelay=10000");
			Connection connection = connectionFactory.createConnection();
			connection.setClientID("001");
			connection.start();
			final Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			Queue queue = session.createQueue("test");
			MessageConsumer consumer = session.createConsumer(queue);
			while(true) {
				try {
					TestBean object = (TestBean)((ObjectMessage) consumer.receive()).getObject();
					System.out.println(object.getName());
				} catch (JMSException e) {
					e.printStackTrace();
				}
			}
		} catch (JMSException e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		//		new Thread(new TestVMProduce()).start();
		(new TestVTcpRecieve()).run();
	}

}
