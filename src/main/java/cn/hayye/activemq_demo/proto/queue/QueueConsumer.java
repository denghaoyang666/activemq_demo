package cn.hayye.activemq_demo.proto.queue;

import cn.hayye.activemq_demo.proto.Utils;

import javax.jms.*;
import java.io.IOException;

/**
 * 点对点模型（Queue）案例中的消费者
 * 一个生产者对应一个消费者
 * 若同时启动多个消费者，每条消息只能由一个消费者接收，多条消息会平均分配给每个消费者
 *
 * 队列模式消费者不需要预先订阅消息，只要生产者发送了消息，不管用户何时上线都可以接收到，
 * 测试时可以先启动生产者发送消息，再启动消费者接收，类似短信(开机后立马就能收到短信)
 */

public class QueueConsumer {
    /**
     * 消费者
     */
    public void consume(String target){
        Connection connection = Utils.getConnection();
        Session session;
        try {
            //1.创建回话，关闭事务
            session = connection.createSession(Boolean.FALSE, Session.AUTO_ACKNOWLEDGE);
            //2.创建点对点(Queue)消息队列，并指明消息目的地，与生产者地址一致
            Destination destination = session.createQueue(target);
            //3.创建消费者
            MessageConsumer consumer = session.createConsumer(destination);
            //4.设置监听器
            consumer.setMessageListener(message -> {
                try {
                    System.out.println(((TextMessage)message).getText());
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
        QueueConsumer consumer = new QueueConsumer();
        consumer.consume("queue_demo");
    }
}
