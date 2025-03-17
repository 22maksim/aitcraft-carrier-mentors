package service;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.home.aircraft_carrier_mentors.model.outbox.OutboxMessage;

import java.io.IOException;
import java.io.Serial;
import java.util.Base64;

public class OutboxMessageDeserializer extends StdDeserializer<OutboxMessage> {

    @Serial
    private static final long serialVersionUID = 1L;

    private final ObjectMapper mapper;

    protected OutboxMessageDeserializer() {
        super(OutboxMessage.class);
        mapper =  new ObjectMapper();
    }

    @Override
    public OutboxMessage deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JacksonException {
        JsonNode node = p.getCodec().readTree(p);

        String prefix = node.get("prefix").asText();
        String contentString = node.get("content").asText();
        JsonNode content = mapper.readTree(Base64.getDecoder().decode(contentString));

        OutboxMessage outboxMessage = new OutboxMessage();
        outboxMessage.setPrefix(prefix);
        outboxMessage.setContent(content);

        return outboxMessage;
    }
}
