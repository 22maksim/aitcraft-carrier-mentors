package service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.home.aircraft_carrier_mentors.model.outbox.OutboxMessage;
import model.ChangeEvent;
import org.apache.flink.api.common.serialization.SerializationSchema.InitializationContext;
import org.apache.flink.connector.kafka.sink.KafkaRecordSerializationSchema;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.nio.charset.StandardCharsets;

public class OutboxSerializer  implements KafkaRecordSerializationSchema<ChangeEvent> {
    private ObjectMapper mapper;


    @Override
    public void open(InitializationContext context, KafkaSinkContext sinkContext) throws Exception {
        mapper =  new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addDeserializer(OutboxMessage.class, new OutboxMessageDeserializer());
        mapper.registerModule(module);
    }

    @Override
    public ProducerRecord<byte[], byte[]> serialize(ChangeEvent element, KafkaSinkContext context, Long timestamp) {
        try {
            JsonNode content = element.getMessage().getContent();

            ProducerRecord<byte[], byte[]> record = new ProducerRecord<>(
                    content.get("aggregate_type").asText(),
                    content.get("aggregate_id").asText().getBytes(),
                    mapper.writeValueAsBytes(content));
            record.headers().add("message_id", content.get("id").asText().getBytes(StandardCharsets.UTF_8));

            return record;
        }
        catch (JsonProcessingException e) {
            throw new IllegalArgumentException("Couldn't serialize outbox message", e);
        }
    }
}
