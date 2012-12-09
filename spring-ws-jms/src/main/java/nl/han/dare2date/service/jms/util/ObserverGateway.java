package nl.han.dare2date.service.jms.util;

import java.util.Observer;

import javax.jms.*;
import javax.naming.NamingException;

public class ObserverGateway implements MessageListener {

	private Observer observer;
	private Connection connection;
	private MessageConsumer updateConsumer;

	public ObserverGateway(DurableObserver observer, String topicName)
		throws JMSException, NamingException {
		initialize(observer, topicName);
	}

	protected void initialize(DurableObserver observer, String topicName) throws JMSException, NamingException {
		this.observer = observer;
		connection = JMSUtil.getConnection(observer.getSubscriberName());
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		Destination updateTopic = JMSUtil.getDestinationForQueue(topicName);
		// The Session.createConsumer method creates a nondurable subscriber if a topic is specified as the destination.
		// A nondurable subscriber can receive only messages that are published while it is active.

        updateConsumer = session.createConsumer(updateTopic);
        updateConsumer.setMessageListener(this);
//        TopicSubscriber subscriber =  session.createDurableSubscriber((Topic)updateTopic, observer.getSubscriberName());
//        subscriber.setMessageListener(this);
	}

	public void onMessage(Message message) {
		try {
			ObjectMessage objMsg = (ObjectMessage) message; 
			Object notifiedObject = objMsg.getObject();
			observer.update(null,notifiedObject);
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}
}