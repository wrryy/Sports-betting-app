//package pl.wrryy.amelco;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.jms.annotation.EnableJms;
//import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
//import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
//import org.springframework.jms.support.converter.MessageConverter;
//import org.springframework.jms.support.converter.MessageType;
//
//@Configuration
//@EnableJms
//public class MessageConfig {
//    @Bean
//    public DefaultJmsListenerContainerFactory jmsListenerContainerFactory() {
//        DefaultJmsListenerContainerFactory factory =
//                new DefaultJmsListenerContainerFactory();
//        factory.setConnectionFactory(connectionFactory());
//        factory.setDestinationResolver(destinationResolver());
//        factory.setConcurrency("3-10");
//        factory.setClientID("brokerClientId");
//        factory.setSubscriptionDurable(true);
//        return factory;
//    }
//
//    @Bean
//    public MessageConverter jacksonJmsMessageConverter() {
//        MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
//        converter.setTargetType(MessageType.TEXT);
//        converter.setTypeIdPropertyName("_type");
//        return converter;
//    }
//}
