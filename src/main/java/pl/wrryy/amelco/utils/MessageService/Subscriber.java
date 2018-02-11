package pl.wrryy.amelco.utils.MessageService;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class Subscriber {
    private String clientId;
    private Connection connection;
    private Session session;
    private MessageConsumer messageConsumer;
    public void closeConnection() throws JMSException {
        connection.close();
    }
    public void create(String clientId, String topicName) throws JMSException {
        this.clientId = clientId;
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(
                ActiveMQConnection.DEFAULT_BROKER_URL);
        connection = connectionFactory.createConnection();
        connection.setClientID(clientId);
        session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Topic topic = session.createTopic(topicName);
        messageConsumer = session.createConsumer(topic);
        connection.start();
    }
    public String getMessage(int timeout) throws JMSException {
        String receivedName = "No message was sent";
        Message message = messageConsumer.receive(timeout);
        if (message != null) {
            TextMessage textMessage = (TextMessage) message;
            String text = textMessage.getText();
            receivedName = "Hello " + text + "!";
        } else {
            System.out.println(clientId + ": no message received");
        }
        return receivedName;
    }
}
