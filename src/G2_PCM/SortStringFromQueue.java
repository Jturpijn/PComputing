package G2_PCM;

import javax.jms.*;

import static G2_PCM.MergeSort.mergesort;

public class SortStringFromQueue implements MessageListener {
        private String ConsumerName;

        private static Messagebroker messagebroker;
        private static Destination destination;
        private static Session session;
        private static MessageConsumer consumer;
        private int check = 0;
        public static long start;

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
                check++;
            OwnMessage ownMessage = this.sortMessage(message);

            messagebroker.sendSortMessageOnQueue(
                    messagebroker.getActiveSession(),
                    Messagebroker.incomingQueue,
                    ownMessage
            );

            if(check == 1){
                start = System.nanoTime();
            }

            if(check == 2){
                long end = System.nanoTime();
                long tijd = ((end - start) / 1000)/1000;
                System.out.print("Took: " + tijd);
                check = 0;
            }

        }

        public OwnMessage sortMessage(Message message) {

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
            destination = messagebroker.getQueue(Messagebroker.outgoingQueue);
            consumer = messagebroker.getActiveSession().createConsumer(destination);
            consumer.setMessageListener(new SortStringFromQueue("name"));
            messagebroker.getActiveCon().start();
        }

    }
