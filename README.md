# Hello World Spring Boot and Kafka

## Introduction
a simple Java Hello World application for sending and receiving message thru Kafka

## How To
Install Kafka
```
$ docker run -it -p 9092:9092 -e LOG_DIR=/tmp/logs quay.io/strimzi/kafka:latest-kafka-2.8.1-amd64 /bin/sh \
    -c 'export CLUSTER_ID=$(bin/kafka-storage.sh random-uuid) && bin/kafka-storage.sh format -t $CLUSTER_ID -c config/kraft/server.properties && bin/kafka-server-start.sh config/kraft/server.properties'
```

Build Application
```
$ mvn clean package
```

Test Connection
```
$ curl -kv http://localhost:8080/
```

Connection Log
```
2023-01-13 16:56:00.413  INFO 1436 --- [ad | producer-1] com.edw.simulate.ProduceMessages         : Sending message-7921117d-29e1-4ee1-8312-a9ac770b927a 
2023-01-13 16:56:00.425  INFO 1436 --- [ntainer#0-0-C-1] com.edw.simulate.ConsumeMessages         : Reading message-7921117d-29e1-4ee1-8312-a9ac770b927a 
2023-01-13 16:56:01.378  INFO 1436 --- [ad | producer-1] com.edw.simulate.ProduceMessages         : Sending message-0d943448-6e96-44ce-b3c1-b92e9bedac1e 
2023-01-13 16:56:01.389  INFO 1436 --- [ntainer#0-0-C-1] com.edw.simulate.ConsumeMessages         : Reading message-0d943448-6e96-44ce-b3c1-b92e9bedac1e
```