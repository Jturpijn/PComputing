package G2_PCM;

import javax.jms.*;
import org.apache.activemq.ActiveMQConnectionFactory;


import java.util.ArrayList;
import java.util.Arrays;

import static G2_PCM.Main.*;
import static G2_PCM.Utils.random;
import static G2_PCM.Utils.split;


public class Producer {

    private static Messagebroker messagebroker;
    private static Destination destination;
    private static int[] locArray;
 // either connect to the remote Messagebroker running on the PI, or on the localhost
        private static String url = "failover:(tcp://localhost:61616,localhost:8161)";
        private static String subject = "incomingQueue"; // Queue Name
        private static int[] array;

    public static void sendMessages() {
        int slices = 2; // how many parts the array is split in
        array = random(SIZE, MAX);
        int[] laag = new int[SIZE/2];
        int[] middellaag = new int[SIZE/2];
        int[] middelhoog = new int[SIZE/2];
        int[] hoog = new int[SIZE/2];
        int q = 0;
        int b = 0;
        int c = 0;
        int l = 0;
        for(int p = 0; p < SIZE; p++){
            if(p < MAX/4){
                    laag[q] = array[p];
                    q++;
            }else if (p > MAX/4 && p < MAX/2){
                    middellaag[b] = array[p];
                    b++;
            }else if(p > MAX/2 && p < (MAX/2 + MAX/4)){
                    middelhoog[c] = array[p];
                    c++;
            }else{
                    hoog[l] = array[p];
                    l++;
            }
        }

        for (int i = 0; i < slices; i++) {

            int[] part = Arrays.copyOfRange(array, (i * SIZE/2), (SIZE/2));

            OwnMessage ownMessage = new OwnMessage();
            ownMessage.setMessageId(i);
            ownMessage.setTotalparts(2);
            ownMessage.setPart(i+1);
            ownMessage.setArray(part);


            messagebroker.sendSortMessageOnQueue(
                    messagebroker.getActiveSession(),
                    Messagebroker.incomingQueue,
                    ownMessage
            );
        }
    }

        public static void main(String[] args) throws JMSException {

            array = random(SIZE, MAX);
            int[] laag = new int[SIZE];
            int[] middellaag = new int[SIZE];
            int[] middelhoog = new int[SIZE];
            int[] hoog = new int[SIZE];
            int q = 0;
            int b = 0;
            int c = 0;
            int l = 0;
            for(int p = 0; p < SIZE; p++){
                if(p < MAX/4){
                    laag[q] = array[p];
                    q++;
                }else if (p > MAX/4 && p < MAX/2){
                    middellaag[b] = array[p];
                    b++;
                }else if(p > MAX/2 && p < (MAX/2 + MAX/4)){
                    middelhoog[c] = array[p];
                    c++;
                }else{
                    hoog[l] = array[p];
                    l++;
                }
            }

            System.out.println("laag: " + StringBuilder(laag));
            System.out.println("ml: " + StringBuilder(middellaag));
            System.out.println("mh: " + StringBuilder(middelhoog));
            System.out.println("h: " + StringBuilder(hoog));


//        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);
//        Connection connection = connectionFactory.createConnection();
//        connection.start();
//        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
//        Destination destination = session.createQueue(subject);
//        MessageProducer producer = session.createProducer(destination);
//
//        //fill array with random numbers and send it
//        array = random(SIZE, MAX);
//         ArrayList<Integer> subarray1 = new ArrayList<>();
//         ArrayList<Integer> subarray2 = new ArrayList<>();
//        for(int i = 0; i < SIZE; i++){
//            if (array[i] < (MAX/2)){
//                subarray1.add(array[i]);
//            }else {
//                subarray2.add(array[i]);
//            }
//        }
//         String text1 = StringBuilderList(subarray1);
//         String text2 = StringBuilderList(subarray2);
//         //   System.out.println(text);
//
//        TextMessage message = session.createTextMessage();
//        message.setText(text1);
//        producer.send(message);
//        message.setText(text2);
//        producer.send(message);
//        System.out.println("Sent Message '" + text1 + "'");
//        connection.close();
    }

    public static String StringBuilderList(ArrayList array){
        StringBuilder builder = new StringBuilder();
        for (Object value : array) {
            builder.append(value);
            builder.append(", ");

        }
        String text = builder.toString();
       return  text;
    }

    public static String StringBuilder (int[] array){
        StringBuilder builder = new StringBuilder();
        for (int value : array) {
            builder.append(value);
            builder.append(", ");

        }
        String text = builder.toString();
        return  text;
    }
}
