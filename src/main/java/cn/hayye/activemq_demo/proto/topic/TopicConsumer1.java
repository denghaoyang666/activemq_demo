package cn.hayye.activemq_demo.proto.topic;

import cn.hayye.activemq_demo.proto.Utils;

import javax.jms.*;
import java.io.IOException;

/**
 * 广播模型（Topic）案例中的消费者
 * 一个生产者对应多个消费者
 *
 * 广播模式消费者需要先订阅消息，否则无法接收，例如生产者先发送消息，消费者在上线，是无法接收到之前的消息的。
 * 类似收音机，节目时间13:00~14：00,用户到15:00的时候才切换到这个频道是无法收听之前的节目的。
 */

public class TopicConsumer1 {
    /**
     * 消费者
     */
    public void consume(String target){
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
                    System.out.println("1:"+((TextMessage)message).getText());
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
        TopicConsumer1 consumer = new TopicConsumer1();
        consumer.consume("topic_demo");
    }
}
