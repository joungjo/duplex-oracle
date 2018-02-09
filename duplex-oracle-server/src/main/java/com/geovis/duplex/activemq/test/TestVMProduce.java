package com.geovis.duplex.activemq.test;

import javax.jms.Connection;
import javax.jms.DeliveryMode;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

public class TestVMProduce implements Runnable {

	@Override
	public void run() {
		try {
			ActiveMQConnectionFactory factory = 
					new ActiveMQConnectionFactory(ActiveMQConnection.DEFAULT_USER,
							ActiveMQConnection.DEFAULT_PASSWORD, 
							"failover:(tcp://192.168.44.4:61616?wireFormat.maxInactivityDuration=10000)&amp;maxReconnectDelay=10000");
			Connection connection = factory.createConnection();
			Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			Queue queue = session.createQueue("test");
			MessageProducer producer = session.createProducer(queue);
			producer.setDeliveryMode(DeliveryMode.PERSISTENT);
			for (int i = 0; i < 10; i++) {
				if (i % 2 == 1) {
					ObjectMessage message = session.createObjectMessage();
					message.setObject(new TestBean("" + i));
					producer.send(message);
				} 
				Thread.sleep(600);
			}
			session.close();
		} catch (JMSException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	

	public static void main(String[] args) {
		TestVMProduce produce = new TestVMProduce();
		produce.run();
	}

}
