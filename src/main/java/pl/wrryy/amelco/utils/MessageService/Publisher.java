package pl.wrryy.amelco.utils.MessageService;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class Publisher {
    private String clientId;
    private Connection connection;
    private Session session;
    private MessageProducer messageProducer;
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
        messageProducer = session.createProducer(topic);
    }
    public void sendMessage(String message) throws JMSException {
        TextMessage textMessage = session.createTextMessage(message);
        messageProducer.send(textMessage);
    }
}
