package com.bigdatacompany.eticaret;

import jakarta.annotation.PostConstruct;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.protocol.types.Field;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.stereotype.Component;

import java.util.Properties;
@Component
public class KafkaProducerr {
    Producer producer;
    @PostConstruct
    public void init(){
        Properties config = new Properties();
        config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"Your Ip Adress : Port");
        config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,new StringSerializer().getClass().getName());
        config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,new StringSerializer().getClass().getName());

       Producer producer=new KafkaProducer<String,String>(config);
    }
    public void send(String term){
        ProducerRecord<String,String> rec=new ProducerRecord<String,String>("searchv2",term);
        producer.send(rec);
    }
    public void close(){
        producer.close();
    }
}
