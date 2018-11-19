package cn.hayye.activemq_demo.proto.queue;

import cn.hayye.activemq_demo.proto.Utils;

import javax.jms.*;

/**
 * 点对点模型（Queue）案例中的生产者
 * 一个生产者对应一个消费者
 */

public class QueueProducer {
    /**
     * 生产者
     */
    public void produce(String target,String msg){
        Connection connection = Utils.getConnection();
        Session session;
        try {
            //1.创建回话，开启事务
            session = connection.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);
            //2.创建点对点(Queue)消息队列，并指明消息目的地
            Destination destination = session.createQueue(target);
            //3.创建消息生产者
            MessageProducer producer = session.createProducer(destination);
            //4.创建一条消息
            TextMessage text = session.createTextMessage(msg);
            //5.生产者发送消息
            producer.send(text);
            //6.因为设置了事务，所以需要提交会话
            session.commit();
        } catch (JMSException e) {
            e.printStackTrace();
        }finally {
            Utils.close(connection);
        }
    }

    public static void main(String[] args) {
        QueueProducer producer = new QueueProducer();
        for(int i=10;i<=20;i++){
            producer.produce("queue_demo","queue "+i+" ...");
        }

    }
}
