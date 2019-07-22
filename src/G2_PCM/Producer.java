package G2_PCM;

import javax.jms.*;
import org.apache.activemq.ActiveMQConnectionFactory;


import java.util.ArrayList;
import java.util.Arrays;

import static G2_PCM.Main.*;
import static G2_PCM.Utils.*;


public class Producer {

    private static Messagebroker messagebroker;
    private static Destination destination;


        static int[] ownArray = new int[SIZE];

    public static void sendMessages() {
        //array = random(SIZE, MAX);
        shuffleArray(ownArray);
        int[] laag = new int[SIZE/4];
        int[] middellaag = new int[SIZE/4];
        int[] middelhoog = new int[SIZE/4];
        int[] hoog = new int[SIZE/4];
        int a = 0;
        int b = 0;
        int c = 0;
        int d = 0;
        for(int p = 0; p < SIZE; p++){
            if(ownArray[p] < SIZE/4){
                laag[a] = ownArray[p];
                a++;
            }else if (ownArray[p] >= SIZE/4 && ownArray[p] < SIZE/2){
                middellaag[b] = ownArray[p];
                b++;
            }else if(ownArray[p] >= SIZE/2 && ownArray[p] < (SIZE/2 + SIZE/4)){
                middelhoog[c] = ownArray[p];
                c++;
            }else if (ownArray[p] >= (SIZE/2 + SIZE/4)){
                hoog[d] = ownArray[p];
                d++;
            }
        }
        for (int i = 0; i < 4; i++) {
            OwnMessage ownMessage = new OwnMessage();
            if(i == 0){
                ownMessage.setArray(laag);
                ownMessage.setMessageId("laag");
            }else if(i == 1){
                ownMessage.setArray(middellaag);
                ownMessage.setMessageId("middellaag");
            }else if(i == 2){
                ownMessage.setArray(middelhoog);
                ownMessage.setMessageId("middelhoog");
            } else {
                ownMessage.setArray(hoog);
                ownMessage.setMessageId("hoog");
            }

            ownMessage.setTotalparts(4);
            ownMessage.setPart(i+1);
            messagebroker.sendSortMessageOnQueue(
                    messagebroker.getActiveSession(),
                    Messagebroker.outgoingQueue,
                    ownMessage
            );
        }
    }

        public static void main(String[] args) throws JMSException {
            messagebroker = new Messagebroker();
            messagebroker.connect();
            destination = messagebroker.getQueue(Messagebroker.incomingQueue);
            sendMessages();
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
