package G2_PCM;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class Consumer implements MessageListener  {

    private Map<String, LinkedList<OwnMessage>> jobs = new HashMap<>();


    public static void main(String[] args) throws JMSException {
//        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);
//        Connection connection = connectionFactory.createConnection();
//        connection.start();
//        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
//        Destination destination = session.createQueue(Messagebroker.outgoingQueue);
//        MessageConsumer consumer = session.createConsumer(destination);
//        Message message = consumer.receive();
//        if (message instanceof TextMessage) {
//            TextMessage textMessage = (TextMessage) message;
//            System.out.println("Received'"+textMessage.getText() + "'");
//        }
//        connection.close();
    }

    @Override
    public void onMessage(Message message) {
        try {
            message.acknowledge();
        } catch (JMSException e) {
            System.out.println("Error while acknowledging message");
            e.printStackTrace();
        }

        OwnMessage ownMessage = messageToMerge(message);
        storeMessage(ownMessage);

//        if(jobs.get(ownMessage.getMessageId()).size() == ownMessage.getTotalparts()){
//            int[] sorted = combineSortedChunks(sortMsg.getJobId());
//            System.out.println("Successfully combined " + sorted.length + " elements.");
//        }

    }

    private OwnMessage messageToMerge(Message message){
        ObjectMessage objectMessage;
        OwnMessage ownMessage = new OwnMessage();

        if (message instanceof ObjectMessage) {
            objectMessage = (ObjectMessage) message;

            try {
                ownMessage = (OwnMessage) objectMessage.getObject();
            } catch (JMSException e) {
                e.printStackTrace();
            }
        }

        return ownMessage;
    }

    void storeMessage(OwnMessage message){
        ArrayList<OwnMessage> messages = new ArrayList<>();
        messages.add(message);
    }


}
