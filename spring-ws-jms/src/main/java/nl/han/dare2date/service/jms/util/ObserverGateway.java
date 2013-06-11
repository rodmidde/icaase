/*
    Copyright (C) [2013] by [Rody Middelkoop, HAN University]

    Permission is hereby granted, free of charge, to any person obtaining a copy
    of this software and associated documentation files (the "Software"), to deal
    in the Software without restriction, including without limitation the rights
    to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
    copies of the Software, and to permit persons to whom the Software is
    furnished to do so, subject to the following conditions:

    The above copyright notice and this permission notice shall be included in
    all copies or substantial portions of the Software.

    THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
    IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
    FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
    AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
    LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
    OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
    THE SOFTWARE.
*/

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
