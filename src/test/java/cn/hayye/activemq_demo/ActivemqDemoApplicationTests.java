package cn.hayye.activemq_demo;

import cn.hayye.activemq_demo.anno.Consumer;
import cn.hayye.activemq_demo.anno.Producer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ActivemqDemoApplicationTests {
	@Autowired
	Producer producer;
	@Autowired
	Consumer consumer;

	@Test
	public void testQueue() throws InterruptedException {
		for (int i = 1 ; i <= 10 ; i++){
			producer.sendQueueText("test.queue","test "+i);
			Thread.sleep(1000);
		}
	}

	@Test
	public void testTopic() throws InterruptedException {
		for (int i = 1 ; i <= 10 ; i++){
			producer.sendTopicText("test.topic","test "+i);
			Thread.sleep(1000);
		}
	}

}
