package com.home.aircraft_carrier_mentors.configuration.kafka.debezium;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
@RequiredArgsConstructor
public class DebeziumConnectorService {
    private static final String CONNECT_URL = "http://localhost:8083/connectors/postgres-connector/config";

    private final RestTemplate restTemplate;

    @PostConstruct
    private void createConnector() {
        String requestJson = """
                        {
                            "connector.class": "io.debezium.connector.postgresql.PostgresConnector",
                            "database.hostname": "postgres",
                            "database.port": "5432",
                            "database.user": "maksim",
                            "database.password": "password",
                            "database.dbname": "payments_db",
                            "database.server.name": "postgres-server",
                            "plugin.name": "pgoutput",
                            "slot.name": "debezium_slot",
                            "publication.name": "debezium_pub",
                            "table.include.list": "public.outbox_payment",
                            "topic.prefix": "outbox_payment",
                            "schema.history.internal.kafka.bootstrap.servers": "kafka:9093",
                            "schema.history.internal.kafka.topic": "schema-changes.inventory"
                        }
                """;

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(requestJson, headers);
        restTemplate.put(CONNECT_URL, entity, String.class);

        ResponseEntity<String> responseEntity = restTemplate.getForEntity(CONNECT_URL, String.class);
        System.out.println("Получили следующие настройки дебезиума коннекта постгреса : " + responseEntity.getBody());
    }
}
