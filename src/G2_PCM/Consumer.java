package G2_PCM;

import javax.jms.*;

import static G2_PCM.Main.SIZE;

public class Consumer implements MessageListener {

    private static Messagebroker messagebroker;
    private static Destination destination;
    private static MessageConsumer messageConsumer;
    int messageCount =0;

   // ArrayList<OwnMessage> messages = new ArrayList<>();
    OwnMessage[] messages = new OwnMessage[4];


    // private Map<String, LinkedList<OwnMessage>> jobs = new HashMap<>();


    public static void main(String[] args) throws JMSException {
        messagebroker = new Messagebroker();
        messagebroker.connect();
        destination = messagebroker.getQueue(Messagebroker.incomingQueue);
        messageConsumer = messagebroker.getActiveSession().createConsumer(destination);
        messageConsumer.setMessageListener(new Consumer());
        messagebroker.getActiveCon().start();
    }

    @Override
    public void onMessage(Message message) {
        try {
            message.acknowledge();
        } catch (JMSException e) {
            System.out.println("Error while acknowledging message");
            e.printStackTrace();
        }

        OwnMessage ownMessage = messageToOwnMessage(message);
        storeMessage(ownMessage);
        messageCount++;

        if (messageCount == 4){
           int[] array =  mergeMessages();
            System.out.println("eerste cijfer: " + array[0] + " \n"
                    + "middelste cijfer: " + array[SIZE/2] + " \n"
                    + "laatste cijfer: " + array[SIZE -1]);
        //    System.out.println(Producer.StringBuilder(array));
            messageCount = 0;
            long eind = System.nanoTime();
            System.out.println("eindtijd = " + eind);
        }

    }

    private OwnMessage messageToOwnMessage(Message message) {
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

    void storeMessage(OwnMessage message) {

        if(message.getMessageId().equals("laag")){
            messages[0] = message;
        } else if (message.getMessageId().equals("middellaag")){
            messages[1] = message;
        } else if (message.getMessageId().equals("middelhoog")){
            messages[2] = message;
        } else if (message.getMessageId().equals("hoog")){
            messages[3] = message;
        }

    }


    public int[] mergeMessages() {
        int[] array = new int[SIZE];
        int p = 0;
        for (OwnMessage message : messages) {
            for (int i = 0; i < message.getArray().length; i++) {
                array[p] = message.getArray()[i];
                p++;
            }
        }
        messages = new OwnMessage[4];
        return array;
    }


}
