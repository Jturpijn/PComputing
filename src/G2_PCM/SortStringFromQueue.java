package G2_PCM;

import javax.jms.*;

import static G2_PCM.MergeSort.mergesort;

public class SortStringFromQueue implements MessageListener {
        private String ConsumerName;

        private static Messagebroker messagebroker;
        private static Destination destination;
        private static Session session;
        private static MessageConsumer consumer;

        public SortStringFromQueue(String consumerName) {
            ConsumerName = consumerName;
        }

        @Override
        public void onMessage(Message message) {

            try {
                message.acknowledge();
            } catch (JMSException e) {
                System.out.println("Error while acknowledging message");
                e.printStackTrace();
            }

            OwnMessage ownMessage = this.sortMessage(message);

            messagebroker.sendSortMessageOnQueue(
                    messagebroker.getActiveSession(),
                    Messagebroker.outgoingQueue,
                    ownMessage
            );
        }

        public OwnMessage sortMessage(Message message) {
            int[] integers = null; // to hold the converted and sorted numbers

            ObjectMessage objectMessage;
            OwnMessage ownMessage = new OwnMessage();

            if (message instanceof ObjectMessage) {
                objectMessage = (ObjectMessage) message;


                try {
                    ownMessage = (OwnMessage) objectMessage.getObject();
                } catch (JMSException e) {
                    e.printStackTrace();
                }
                    mergesort(ownMessage.getArray());
                    ownMessage.setArray(ownMessage.getArray());
                }

            return ownMessage;
        }

        public static void main(String[] args) throws JMSException {
            messagebroker = new Messagebroker();
            messagebroker.connect();
            destination = messagebroker.getQueue(Messagebroker.incomingQueue);
            consumer = messagebroker.getActiveSession().createConsumer(destination);
            consumer.setMessageListener(new SortStringFromQueue("name"));
            messagebroker.getActiveCon().start();
        }

    }
