package cn.hayye.activemq_demo.anno;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;

@Component
public class Consumer {

    @JmsListener(destination = "test.queue")
    public void receiver1(Message message){
        TextMessage text = (TextMessage)message;
        try {
            System.out.println("<<<<< 1 来自于test.queue 内容："+text.getText());
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
    @JmsListener(destination = "test.queue")
    public void receiver2(Message message){
        TextMessage text = (TextMessage)message;
        try {
            System.out.println("<<<<< 2 来自于test.queue 内容："+text.getText());
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

    /**
     * 广播(Topic)消息的消费者需要配置ContainerFactory
     */
    @JmsListener(destination = "test.topic" , containerFactory = "subJmsListenerContainerFactory")
    public void receiver3(Message message){
        TextMessage text = (TextMessage)message;
        try {
            System.out.println("<<<<< 3 来自于test.topic 内容："+text.getText());
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
    @JmsListener(destination = "test.topic" , containerFactory = "subJmsListenerContainerFactory")
    public void receiver4(Message message){
        TextMessage text = (TextMessage)message;
        try {
            System.out.println("<<<<< 4 来自于test.topic 内容："+text.getText());
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
