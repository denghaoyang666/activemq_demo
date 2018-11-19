package cn.hayye.activemq_demo.proto.topic;

import cn.hayye.activemq_demo.proto.Utils;

import javax.jms.*;
import java.io.IOException;

/**
 * 因为当前是广播式消息模型，所以如果多个Consumer可以同时接收到消息！
 */
public class TopicConsumer2 {
    /**
     * 测试消费者2
     * @param target
     */
    public void consume2(String target){
        Connection connection = Utils.getConnection();
        Session session;
        try {
            //1.创建回话，关闭事务
            session = connection.createSession(Boolean.FALSE, Session.AUTO_ACKNOWLEDGE);
            //2.创建广播(Topic)消息队列，并指明消息目的地，与生产者地址一致
            Destination destination = session.createTopic(target);
            //3.创建消费者
            MessageConsumer consumer = session.createConsumer(destination);
            //4.设置监听器
            consumer.setMessageListener(message -> {
                try {
                    System.out.println("2:"+((TextMessage)message).getText());
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            });
            //5、阻塞，程序等待接收用户消息
            System.in.read();
        } catch (JMSException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            Utils.close(connection);
        }
    }

    public static void main(String[] args) {
        TopicConsumer2 consumer2 = new TopicConsumer2();
        consumer2.consume2("topic_demo");
    }
}
