CREATE SCHEMA outbox_orchestrator;
SET search_path TO outbox_orchestrator;

CREATE TABLE outbox_message
(
    id         SERIAL PRIMARY KEY,
    outbox_type       VARCHAR(255) NOT NULL,
    content    jsonb        NOT NULL,
    orchestrator_status     VARCHAR(255) NOT NULL,
    processes  jsonb        NOT NULL, -- пример {"process-payment": "STARTING", "process-order": "STARTING", "process-delivery": "WAITING"}
    created_at timestamp with time zone
);
ALTER SEQUENCE outbox_message_id START WITH 1001;
ALTER TABLE outbox_message
    REPLICA IDENTITY FULL;

CREATE TABLE process_payment
(
    id                  SERIAL PRIMARY KEY,
    status              VARCHAR(255),
    unique_id_operation uuid NOT NULL,
    payload             jsonb,
    outbox_id INTEGER NOT NULL REFERENCES outbox_message(id)
);
ALTER SEQUENCE process_payment_id START WITH 10001;
ALTER TABLE process_payment
    REPLICA IDENTITY FULL;

CREATE TABLE process_order
(
    id                  SERIAL PRIMARY KEY,
    status              VARCHAR(255),
    unique_id_operation uuid NOT NULL,
    payload             jsonb,
    outbox_id INTEGER NOT NULL REFERENCES outbox_message(id)
);
ALTER SEQUENCE process_order_id START WITH 10001;
ALTER TABLE process_order
    REPLICA IDENTITY FULL;

CREATE TABLE process_delivery
(
    id                  SERIAL PRIMARY KEY,
    status              VARCHAR(255),
    unique_id_operation uuid NOT NULL,
    payload             jsonb,
    outbox_id INTEGER NOT NULL REFERENCES outbox_message(id)
);
ALTER SEQUENCE process_delivery_id START WITH 10001;
ALTER TABLE process_delivery
    REPLICA IDENTITY FULL;