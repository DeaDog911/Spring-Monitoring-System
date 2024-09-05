# Отчет Kafka Exmaple

# Описание приложения
Основные компоненты системы
- `Zookeeper`: Служит для координации и управления конфигурацией Kafka, обеспечивает распределённое управление брокерами Kafka.
- `Kafka`: Платформа для передачи потоковых данных, которая используется для обмена сообщениями между микросервисами.
- `Metrics Producer`: Служит для генерации метрик приложения с помощью Spring Actuator и отправки их в топик `metrics` Kafka.
Для передачи метрики сериализуются в JSON.
- `Metrics Consumer`: Получает и обработка метрики из Kafka. При невозможности обработки сообщения оно отправляется в топик `metrics.DLT`.
Полученные метрики десериализуются из JSON и сохраняются в MongoDB.

## Анализ метрик

Для полученных метрик высчитываются максимальные, минимальные, и средние значения, а так же определяются превышения значений пороговых уровней.

## Инструкции по запуску

### Клонирование репозитория

```bash
git clone https://github.com/DeaDog911/Spring-Monitoring-System.git
```

### Сборка и запуск приложения
Перейдите в директорию проекта и выполните следующие команды:

Запуск Docker контейнеров с Kafka и Zookeeper
```bash
docker-compose build
docker-compose up 
```

Сборка и запуск MetricsProducer и MetricsConsumer
```bash
cd MetricsConsumer
mvn install
mvn spring-boot:run

cd ../MetricsProducer
mvn install
mvn spring-boot:run
```

### Документация API

После запуска приложения документация API доступна по адресу

Producer
http://localhost:8080/swagger-ui/index.html

Consumer
http://localhost:8090/swagger-ui/index.html


