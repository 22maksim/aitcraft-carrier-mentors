package service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.home.aircraft_carrier_mentors.model.outbox.OutboxMessage;
import model.ChangeEvent;
import org.apache.flink.api.common.functions.OpenContext;
import org.apache.flink.api.common.functions.RichMapFunction;

import java.io.Serial;

public class ChangeEventDeserializer extends RichMapFunction<String, ChangeEvent> {

    @Serial
    private static final long serialVersionUID = 1L;

    private transient ObjectMapper mapper;

    public ChangeEventDeserializer() {
    }

    @Override
    public ChangeEvent map(String value) throws Exception {
        return mapper.readValue(value, ChangeEvent.class);
    }

    @Override
    public void open(OpenContext context) throws Exception {
        mapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addDeserializer(OutboxMessage.class, new OutboxMessageDeserializer());
        mapper.registerModule(module);
    }
}
