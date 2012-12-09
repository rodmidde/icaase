package nl.han.dare2date.service.jms.util;

import java.io.Serializable;

import javax.jms.*;
import javax.naming.NamingException;

public class SubjectGateway {

	private Connection connection;
	private Session session;
	private MessageProducer updateProducer;

	public SubjectGateway(String topicName) throws JMSException, NamingException {
		this.initialize(topicName);
	}

	protected void initialize(String topicName) throws JMSException, NamingException {
		connection = JMSUtil.getConnection();
		session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		Destination updateTopic = JMSUtil.getDestinationForQueue(topicName);
		updateProducer = session.createProducer(updateTopic);
	}

	public void notifyObservers(Serializable o) throws JMSException {
		ObjectMessage message = session.createObjectMessage(o);
        message.setJMSDeliveryMode(DeliveryMode.PERSISTENT);
        updateProducer.setDeliveryMode(DeliveryMode.PERSISTENT);
		updateProducer.send(message);
	}
}