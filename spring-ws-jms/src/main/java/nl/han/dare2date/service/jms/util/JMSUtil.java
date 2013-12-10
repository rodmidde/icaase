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

import java.io.IOException;
import java.util.Properties;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class JMSUtil {
	private static Context getContext() throws NamingException, IOException
	{
		Properties props = new Properties();
        props.load(JMSUtil.class.getClassLoader().
                getResourceAsStream("jndi.properties"));
		return new InitialContext(props); 
	}
	
	
	public static Destination getDestinationForQueue(String requestQueueName) {
		Context ctx 				= null;
		Destination destination 	= null;
		try {
			ctx = getContext();
			destination = (Destination) ctx.lookup(requestQueueName);
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return destination;
	}

    public static Connection getConnection(String clientID)
    {
        Context ctx 							= null;
        ConnectionFactory connectionFactory 	= null;
        Connection connection 					= null;
        try {
            ctx = getContext();
            connectionFactory = (ConnectionFactory)
                    ctx.lookup("ConnectionFactory");
            connection = connectionFactory.createConnection();
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


    public static Connection getConnection()
	{
		Context ctx 							= null;
		ConnectionFactory connectionFactory 	= null;
		Connection connection 					= null;
		try {
			ctx = getContext();
			connectionFactory = (ConnectionFactory)
			  ctx.lookup("ConnectionFactory");
			connection = connectionFactory.createConnection();
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
}
