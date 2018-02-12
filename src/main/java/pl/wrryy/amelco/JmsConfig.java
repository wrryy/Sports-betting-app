//package pl.wrryy.amelco;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.databind.SerializationFeature;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.jms.annotation.EnableJms;
//import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
//import org.springframework.jms.support.converter.MessageConverter;
//import org.springframework.jms.support.converter.MessageType;
//
//@Configuration
//@EnableJms
//@ComponentScan(basePackages = "pl.wrryy.amelco")
//public class JmsConfig {
////    String BROKER_URL = "tcp://localhost:61616";
////    String BROKER_USERNAME = "admin";
////    String BROKER_PASSWORD = "admin";
////
////    @Bean
////    public ActiveMQConnectionFactory connectionFactory(){
////        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
////        connectionFactory.setBrokerURL(BROKER_URL);
////        connectionFactory.setPassword(BROKER_USERNAME);
////        connectionFactory.setUserName(BROKER_PASSWORD);
////        template.setPubSubDomain(true);
////        factory.setPubSubDomain(true);
////        return connectionFactory;
////    }
////
////    @Bean
////    public JmsTemplate jmsTemplate(){
////        JmsTemplate template = new JmsTemplate();
////        template.setConnectionFactory(connectionFactory());
////        template.setPubSubDomain(true);
////        return template;
////    }
////
////    @Bean
////    public DefaultJmsListenerContainerFactory jmsListenerContainerFactory() {
////        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
////        factory.setConnectionFactory(connectionFactory());
////        factory.setConcurrency("1-1");
////        factory.setPubSubDomain(true);
////        return factory;
////    }
//public static final String ORDER_TOPIC = "order-topic";
//
//    @Bean
//    public MessageConverter messageConverter() {
//        MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
//        converter.setTargetType(MessageType.TEXT);
//        converter.setTypeIdPropertyName("_type");
//        converter.setObjectMapper(objectMapper());
//        return converter;
//    }
//
//    @Bean
//    public ObjectMapper objectMapper(){
//        ObjectMapper mapper = new ObjectMapper();
////        mapper.registerModule(new JavaTimeModule());
//        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
//        return mapper;
//    }
//
//}
