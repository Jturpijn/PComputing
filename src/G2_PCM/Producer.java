package G2_PCM;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

import static G2_PCM.Utils.random;


public class Producer {
        // either connect to the remote ActiveMQ running on the PI, or on the localhost
        private static String url = "failover:(tcp://localhost:61616,localhost:8161)";
        private static String subject = "testQueue1"; // Queue Name
        private static int[] array;

        public static void main(String[] args) throws JMSException {
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);
        Connection connection = connectionFactory.createConnection();
        connection.start();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Destination destination = session.createQueue(subject);
        MessageProducer producer = session.createProducer(destination);

        //fill array with random numbers and send it
        array = random(Main.SIZE, Main.MAX);

            StringBuilder builder = new StringBuilder();
            for (int value : array) {
                builder.append(value);
                builder.append(", ");

            }
            String text = builder.toString();
            System.out.println(text);

        TextMessage message = session.createTextMessage(text);
        producer.send(message);
        System.out.println("Sent Message '" + text + "'");
        connection.close();
    }
}
