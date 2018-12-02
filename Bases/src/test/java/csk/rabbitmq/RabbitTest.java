package csk.rabbitmq;

import com.rabbitmq.client.*;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class RabbitTest {

    static Connection connection;

    @BeforeClass
    public static void init() throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setUsername("admin");
        factory.setPassword("admin");
        factory.setVirtualHost("/");
        //使用单点
//        factory.setHost("192.168.142.191");
//        factory.setPort(5673);
        //使用集群集群
        Address[] addresses = new Address[]{
                new Address("192.168.142.191", 5672),
                new Address("192.168.142.191", 5673),
        };
        connection = factory.newConnection(addresses);
    }

    @AfterClass
    public static void destroy() throws IOException {
        connection.close();
    }

    @Test
    public void sampleProduceTest() throws IOException, TimeoutException {
        Channel channel = connection.createChannel();
        channel.queueDeclare("basicQueue", false, false, false, null);
        for (int i = 0; i < 100; i++) {
            String msg = "测试发送队列数据" + i;
            channel.basicPublish("", "basicQueue", null, msg.getBytes());
            System.out.println("发送数据：" + msg);
        }
        channel.close();
    }

    @Test
    public void sampleConsumerTest() throws IOException, TimeoutException, InterruptedException {
        Channel channel = connection.createChannel();
        channel.queueDeclare("basicQueue", false, false, false, null);
        DefaultConsumer defaultConsumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope,
                                       AMQP.BasicProperties properties, byte[] body)
                    throws IOException {
                String message = new String(body, "UTF-8");
                System.out.println(" 接受数据:" + message + "'");
            }
        };
        channel.basicConsume("basicQueue", true, defaultConsumer);
        Thread.sleep(3000);
        channel.close();
    }


    @Test
    public void workQueueProduction() throws IOException, TimeoutException {
        Channel channel = connection.createChannel();
        channel.queueDeclare("workQueue", false, false, false, null);
        for (int j = 0; j < 100; j++) {
            StringBuilder msg = new StringBuilder("测试发送队列数据0");
            for (int i = 1; i < 10; i++) {
                msg.append(".").append("测试发送队列数据" + i + "->" + j);
            }
            channel.basicPublish("", "workQueue", null, msg.toString().getBytes());
            System.out.println("发送数据：" + msg);
        }
        channel.close();
    }

    /**
     * 使用多个消费者接受时，会自动均匀分配
     *
     * @throws IOException
     * @throws TimeoutException
     * @throws InterruptedException
     */
    @Test
    public void workQueueConsumerTest() throws IOException, TimeoutException, InterruptedException {
        Channel channel = connection.createChannel();
        channel.queueDeclare("workQueue", false, false, false, null);
        DefaultConsumer defaultConsumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope,
                                       AMQP.BasicProperties properties, byte[] body)
                    throws IOException {
                String message = new String(body, "UTF-8");
                System.out.println(" 接受数据:" + message + "'");
                for (char ch : message.toCharArray()) {
                    if (ch == '.') {
                        try {
                            Thread.sleep(1);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        };
        channel.basicConsume("workQueue", true, defaultConsumer);
        Thread.sleep(50000);
        channel.close();
    }

    /**
     * 分裂交换机 数据临时存储，使用临时绑定，消费端断开连接后绑定自动删除，无绑定时数据无法存储
     *
     * @throws IOException
     * @throws TimeoutException
     */
    @Test
    public void fanoutExchangeProduceTest() throws IOException, TimeoutException {
        Channel channel = connection.createChannel();
        channel.exchangeDeclare("log", "fanout");
        for (int i = 0; i < 100; i++) {
            String msg = "交换机测试" + i;
            channel.basicPublish("log", "", null, msg.getBytes());
            System.out.println("发送数据：" + msg);
        }
        channel.close();
    }

    @Test
    public void fanoutExchangeConsumerTest() throws IOException, InterruptedException {
        Channel channel = connection.createChannel();
        channel.exchangeDeclare("log", "fanout");
        String queueName = channel.queueDeclare().getQueue();
        channel.queueBind(queueName, "log", "");
        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope,
                                       AMQP.BasicProperties properties, byte[] body) throws IOException {
                String message = new String(body, "UTF-8");
                System.out.println(" 接受数据： " + message);
            }
        };
        channel.basicConsume(queueName, true, consumer);
        Thread.sleep(50000);
    }

    /**
     * 直接交换机 可选择级别
     *
     * @throws IOException
     * @throws TimeoutException
     */
    @Test
    public void directExchangeProduceTest() throws IOException, TimeoutException {
        Channel channel = connection.createChannel();
        channel.exchangeDeclare("log-direct", "direct");
        for (int i = 0; i < 100; i++) {
            String msg = "持久存储交换机测试" + i;
            channel.basicPublish("log-direct", "log", null, msg.getBytes());
            System.out.println("发送数据：" + msg);
        }
        channel.close();
    }

    @Test
    public void directExchangeConsumerTest() throws IOException, InterruptedException {
        Channel channel = connection.createChannel();
        channel.exchangeDeclare("log-direct", "direct");
        String queueName = channel.queueDeclare().getQueue();
        channel.queueBind(queueName, "log-direct", "log");
        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope,
                                       AMQP.BasicProperties properties, byte[] body) throws IOException {
                String message = new String(body, "UTF-8");
                System.out.println(" 接受数据： " + message);
            }
        };
        channel.basicConsume(queueName, true, consumer);
        Thread.sleep(50000);
    }

    /**
     * 话题交换机
     * * (star) can substitute for exactly one word.
     * # (hash) can substitute for zero or more words.
     */

    @Test
    public void topicExchangeProduceTest() throws IOException, TimeoutException {
        Channel channel = connection.createChannel();
        channel.exchangeDeclare("log-topic", "topic");
        for (int i = 0; i < 100; i++) {
            String msg = "持久存储交换机测试" + i;
            channel.basicPublish("log-topic", "csk.log", null, msg.getBytes());
            System.out.println("发送数据：" + msg);
        }
        channel.close();
    }

    @Test
    public void topicExchangeConsumerTest() throws IOException, InterruptedException {
        Channel channel = connection.createChannel();
        channel.exchangeDeclare("log-topic", "topic");
//        String queueName = channel.queueDeclare().getQueue();
        String queueName = "log-topic-queue";
        channel.queueDeclare(queueName, false, false, false, null);
        channel.queueBind(queueName, "log-topic", "csk.*");
        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope,
                                       AMQP.BasicProperties properties, byte[] body) throws IOException {
                String message = new String(body, "UTF-8");
                System.out.println(" 接受数据： " + message);
            }
        };
        channel.basicConsume(queueName, true, consumer);
        Thread.sleep(50000);
    }
}
