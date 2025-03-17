import model.ChangeEvent;
import org.apache.flink.api.common.eventtime.WatermarkStrategy;
import org.apache.flink.api.connector.source.Source;
import org.apache.flink.cdc.connectors.postgres.PostgreSQLSource;
import org.apache.flink.cdc.connectors.postgres.source.PostgresSourceBuilder;
import org.apache.flink.cdc.connectors.postgres.source.config.PostgresSourceOptions;
import org.apache.flink.cdc.debezium.DebeziumSourceFunction;
import org.apache.flink.cdc.debezium.JsonDebeziumDeserializationSchema;
import org.apache.flink.connector.base.DeliveryGuarantee;
import org.apache.flink.connector.kafka.sink.KafkaSink;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.source.SourceFunction;
import service.ChangeEventDeserializer;
import service.OutboxSerializer;

import java.util.Properties;

public class OutboxMain {

    public static void main(String[] args) throws Exception {
        Properties extraProps = new Properties();
        extraProps.put("poll.interval.ms", "100");
        extraProps.put("snapshot.mode", "never");
        extraProps.put("binary.handling.mode", "base64");

//        DebeziumSourceFunction<String> sourceFunction = PostgresSourceBuilder
//                .hostname("localhost")
//                .port(5432)
//                .database("demodb")
//                .username("postgresuser")
//                .password("postgrespw")
//                .decodingPluginName("pgoutput")
//                .debeziumProperties(extraProps)
//                .deserializer(new JsonDebeziumDeserializationSchema()) // Конвертируем SourceRecord в JSON String
//                .build();

        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.setParallelism(1);

        KafkaSink<ChangeEvent> sink = KafkaSink.<ChangeEvent>builder()
                .setBootstrapServers("localhost:9092")
                .setRecordSerializer(new OutboxSerializer())
                .setProperty("session.timeout.ms", "45000")
                .setProperty("acks", "all")
                .setDeliveryGuarantee(DeliveryGuarantee.AT_LEAST_ONCE)
                .build();

//        env.fromSource(sourceFunction, WatermarkStrategy.noWatermarks(), "")
//                .map(str -> new ChangeEventDeserializer().map(str)) // Конвертируем JSON-строку в ChangeEvent
//                .filter(ce -> "m".equals(ce.getOp())) // Фильтруем только события с op="m"
//                .sinkTo(sink);

        env.execute("Postgres CDC to Kafka");
    }



}