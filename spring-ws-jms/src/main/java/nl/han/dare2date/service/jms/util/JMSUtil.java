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
			// TODO Auto-generated catch block
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
            // TODO Auto-generated catch block
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return connection;
	}
}