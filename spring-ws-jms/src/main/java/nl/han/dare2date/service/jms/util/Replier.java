package nl.han.dare2date.service.jms.util;

import java.io.Serializable;

import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Session;
import javax.naming.NamingException;

/**
 * 
 * @author mdkr
 *
 * Replier does not need the replyQueue, it is included in the message.
 */
public abstract class Replier implements MessageListener {

	private Session session;
	private MessageProducer invalidProducer;

	protected Replier(Connection connection, String requestQueueName, String invalidQueueName)
		throws JMSException, NamingException {
		initialize(connection, requestQueueName, invalidQueueName);
	}

	protected void initialize(Connection connection, String requestQueueName, String invalidQueueName)
		throws NamingException, JMSException {
		session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		Destination requestQueue = JMSUtil.getDestinationForQueue(requestQueueName);
		Destination invalidQueue = JMSUtil.getDestinationForQueue(invalidQueueName);
		MessageConsumer requestConsumer = session.createConsumer(requestQueue);
		MessageListener listener = this;
		requestConsumer.setMessageListener(listener);
		invalidProducer = session.createProducer(invalidQueue);
	}

	public void onMessage(Message message) {
		try {
			if ((message instanceof ObjectMessage) && (message.getJMSReplyTo() != null)) {
				ObjectMessage requestMessage = (ObjectMessage) message;

				System.out.println("Received request");
				System.out.println("\tTime:       " + System.currentTimeMillis() + " ms");
				System.out.println("\tMessage ID: " + requestMessage.getJMSMessageID());
				System.out.println("\tCorrel. ID: " + requestMessage.getJMSCorrelationID());
				System.out.println("\tReply to:   " + requestMessage.getJMSReplyTo());
				System.out.println("\tContents:   " + requestMessage.getObject());

				Serializable contents = requestMessage.getObject();
				
				handleMessage(contents);
				
				Destination replyDestination = message.getJMSReplyTo();
				MessageProducer replyProducer = session.createProducer(replyDestination);

				ObjectMessage replyMessage = getReplyMessage();
				replyMessage.setJMSCorrelationID(requestMessage.getJMSMessageID());
				replyProducer.send(replyMessage);

				System.out.println("Sent reply");
				System.out.println("\tTime:       " + System.currentTimeMillis() + " ms");
				System.out.println("\tMessage ID: " + replyMessage.getJMSMessageID());
				System.out.println("\tCorrel. ID: " + replyMessage.getJMSCorrelationID());
				System.out.println("\tReply to:   " + replyMessage.getJMSReplyTo());
				System.out.println("\tContents:   " + replyMessage.getObject());
			} else {
				System.out.println("Invalid message detected");
				System.out.println("\tType:       " + message.getClass().getName());
				System.out.println("\tTime:       " + System.currentTimeMillis() + " ms");
				System.out.println("\tMessage ID: " + message.getJMSMessageID());
				System.out.println("\tCorrel. ID: " + message.getJMSCorrelationID());
				System.out.println("\tReply to:   " + message.getJMSReplyTo());

				message.setJMSCorrelationID(message.getJMSMessageID());
				invalidProducer.send(message);

				System.out.println("Sent to invalid message queue");
				System.out.println("\tType:       " + message.getClass().getName());
				System.out.println("\tTime:       " + System.currentTimeMillis() + " ms");
				System.out.println("\tMessage ID: " + message.getJMSMessageID());
				System.out.println("\tCorrel. ID: " + message.getJMSCorrelationID());
				System.out.println("\tReply to:   " + message.getJMSReplyTo());
			}
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}

	public abstract ObjectMessage getReplyMessage();

	public abstract void handleMessage(Serializable contents);

	public Session getSession() {
		return session;
	}
}