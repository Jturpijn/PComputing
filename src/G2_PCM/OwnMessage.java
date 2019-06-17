package G2_PCM;

import java.io.Serializable;
import java.util.ArrayList;

public class OwnMessage implements Serializable {

    String messageId;
    int[] array;
    int part;
    int totalparts;


    public int[] getArray() {
        return array;
    }

    public void setArray(int[] array) {
        this.array = array;
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }



    public int getPart() {
        return part;
    }

    public void setPart(int part) {
        this.part = part;
    }

    public int getTotalparts() {
        return totalparts;
    }

    public void setTotalparts(int totalparts) {
        this.totalparts = totalparts;
    }
}
