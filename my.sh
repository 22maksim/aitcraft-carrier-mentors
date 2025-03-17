# Создание сети локально для теста
docker network create my_kafka_debezium_postgres_connect

docker run -d --name zookeeper --network my_kafka_debezium_postgres_connect \
  -e ZOOKEEPER_CLIENT_PORT=2181 \
  confluentinc/cp-zookeeper:7.5.0

docker run -d --name kafka --network my_kafka_debezium_postgres_connect \
  -e KAFKA_BROKER_ID=1 \
  -e KAFKA_ZOOKEEPER_CONNECT=zookeeper:2181 \
  -e KAFKA_LISTENERS=PLAINTEXT://0.0.0.0:9092 \
  -e KAFKA_ADVERTISED_LISTENERS=PLAINTEXT://kafka:9092 \
  -e KAFKA_INTER_BROKER_LISTENER_NAME=PLAINTEXT \
  -e KAFKA_OFFSETS_TOPIC_REPLICATION_FACTOR=1 \
  -p 9092:9092 \
  confluentinc/cp-kafka:7.5.0

docker run -d --name my_postgres --network my_kafka_debezium_postgres_connect \
  -e POSTGRES_USER=myuser \
  -e POSTGRES_PASSWORD=mypassword \
  -e POSTGRES_DB=mydb \
  -p 5432:5432 \
  sha256:578aff6bb201e0c5d4070daca61c5fa3be8ac96b2c4a744530ae5b368ac9046c \
  -c wal_level=logical \
  -c max_wal_senders=5 \
  -c max_replication_slots=5 \
  -c hot_standby=on

docker run -d --name debezium_connect --network my_kafka_debezium_postgres_connect \
  -e CONFIG_STORAGE_TOPIC=connect-configs \
  -e DEBEZIUM_VERSION="2.7" \
  -e GROUP_ID=debezium-connect \
  -e BOOTSTRAP_SERVERS=kafka:9092 \
  -e OFFSET_STORAGE_TOPIC=connect-offsets \
  -e PLUGIN_PATH=/kafka/connect \
  -e POSTGRES_HOST=my_postgres \
  -e POSTGRES_DB=mydb \
  -e POSTGRES_USER=myuser \
  -e POSTGRES_PASSWORD=mypassword \
  -e STATUS_STORAGE_TOPIC=connect-status \
  -p 8083:8083 \
  sha256:2e1a3eafcb57ca7ebe0a4dfad5241d713ac5d62163da22a204c301de8fa8188f
