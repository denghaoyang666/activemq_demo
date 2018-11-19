package cn.hayye.activemq_demo.proto;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;

public class Utils {

    public static final String NAME = "user";
    public static final String PASSWORD = "123456";
    public static final String URL = "tcp://127.0.0.1:61616";

    public static Connection getConnection(){
        Connection connection ;
        try {
            ConnectionFactory factory = new ActiveMQConnectionFactory(NAME,PASSWORD,URL);
            connection = factory.createConnection();
            connection.start();
        } catch (JMSException e) {
            connection = null ;
            e.printStackTrace();
        }
        return connection;
    }

    public static void close(Connection connection){
        if(connection!=null){
            try {
                connection.close();
            } catch (JMSException e) {
                e.printStackTrace();
            }
        }
    }
}
