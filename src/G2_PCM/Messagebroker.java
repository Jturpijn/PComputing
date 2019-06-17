package G2_PCM;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class Messagebroker {

    public final static String serverUrl = "tcp://localhost:61616";
    public final static String incomingQueue = "incomingQueue";
    public final static String outgoingQueue = "outgoingQueue";

    private Connection activeCon;
    private Session activeSession;

    public void connect()
    {
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(serverUrl);
        try
        {
            Connection con = connectionFactory.createConnection();
            Session session = con.createSession(false, Session.AUTO_ACKNOWLEDGE);

            activeCon = con;
            activeSession = session;
        } catch (JMSException e)
        {
            System.out.println("COULD NOT CONNECT TO '" + serverUrl + "'");
            return;
        }

        System.out.println("Connected to '" + serverUrl + "'");
    }
    public Destination getQueue(String queue) throws JMSException
    {
        return activeSession.createQueue(queue);
    }

    public void sendSortMessageOnQueue(Session session, String queue, OwnMessage message)
    {
        try
        {
            Destination dest = session.createQueue(queue);
            MessageProducer msgProd = session.createProducer(dest);

            ObjectMessage msg = session.createObjectMessage();
            msg.setObject(message);

            msgProd.send(msg);
        } catch (JMSException e)
        {
            System.out.println("Failed to sent message: '" + e.getMessage() + "'");
        }

        System.out.println("Sent message: '" + message + "'");
    }



    public Connection getActiveCon() {
        return activeCon;
    }

    public void setActiveCon(Connection activeCon) {
        this.activeCon = activeCon;
    }

    public Session getActiveSession() {
        return activeSession;
    }

    public void setActiveSession(Session activeSession) {
        this.activeSession = activeSession;
    }
}
