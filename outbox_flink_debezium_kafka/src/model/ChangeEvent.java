package model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.JsonNode;
import com.home.aircraft_carrier_mentors.model.outbox.OutboxMessage;

public class ChangeEvent {

    // Это событие с которым работает Debezium

    private String op;
    private long ts_ms;
    private JsonNode source;

    @JsonInclude(Include.ALWAYS)
    private JsonNode before;
    private JsonNode after;

    @JsonInclude(Include.NON_NULL)
    private OutboxMessage message;

    @JsonInclude(Include.NON_NULL)
    private JsonNode transaction;

    @JsonInclude(Include.NON_NULL)
    private JsonNode auditData;

    public String getOp() {
        return op;
    }

    public void setOp(String op) {
        this.op = op;
    }

    public long getTs_ms() {
        return ts_ms;
    }

    public void setTs_ms(long ts_ms) {
        this.ts_ms = ts_ms;
    }

    public JsonNode getSource() {
        return source;
    }

    public void setSource(JsonNode source) {
        this.source = source;
    }

    public JsonNode getBefore() {
        return before;
    }

    public void setBefore(JsonNode before) {
        this.before = before;
    }

    public JsonNode getAfter() {
        return after;
    }

    public void setAfter(JsonNode after) {
        this.after = after;
    }

    public OutboxMessage getMessage() {
        return message;
    }

    public void setMessage(OutboxMessage message) {
        this.message = message;
    }

    public JsonNode getTransaction() {
        return transaction;
    }

    public void setTransaction(JsonNode transaction) {
        this.transaction = transaction;
    }

    public JsonNode getAuditData() {
        return auditData;
    }

    public void setAuditData(JsonNode auditData) {
        this.auditData = auditData;
    }
}
