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

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.io.IOException;
import java.util.Properties;

public class JMSUtil {
    /**
     * Returns a reference to a Destination object given a queuename
     * @param queueName
     * @return
     */
    public static Destination getDestinationForQueue(String queueName) {
        Destination destination = null;
        try {
            destination = (Destination) getContext().lookup(queueName);
        } catch (NamingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return destination;
    }

    /**
     * Returns a connection to the JMS broker for a specific clientID
     * @param clientID
     * @return
     */
    public static Connection getConnection(String clientID) {
        Connection connection = null;
        try {
            connection = ((ConnectionFactory) getContext().lookup("ConnectionFactory")).createConnection();
            connection.setClientID(clientID);
            connection.start();
        } catch (NamingException e) {
            e.printStackTrace();
        } catch (JMSException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return connection;
    }

    /**
     * Returns a connection to the JMS broker
     * @return
     */
    public static Connection getConnection() {
        Connection connection = null;
        try {
            connection = ((ConnectionFactory) getContext().lookup("ConnectionFactory")).createConnection();
            connection.start();
        } catch (NamingException e) {
            e.printStackTrace();
        } catch (JMSException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return connection;
    }


    private static Context getContext() throws NamingException, IOException {
        Properties props = new Properties();
        props.load(JMSUtil.class.getClassLoader().
                getResourceAsStream("jndi.properties"));
        return new InitialContext(props);
    }
}
