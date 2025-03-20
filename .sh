gradle tasks --all

docker run --rm -e ES_HOST=elasticsearch -e ES_PORT=9200 -e INPUT_PORT=5000 -e OUTPUT_INDEX=logs-%{+YYYY.MM.dd} \
 docker.elastic.co/logstash/logstash:7.10.2 -f /path/to/logstash.conf
