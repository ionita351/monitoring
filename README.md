Пример использования технологий.
================================

Упрощенная модель мониторинга объектов.
- Сервера принимающие координаты по разным технологиям (server-rest, server-grpc, server-ws, server-reactive)
- Клиенты передающие координаты ( (client-rest, client-feign), client-grpc, client-ws, client-reactive)
Сервера получая координаты фиксируют их в базе данных и транслируют в брокер KAFKA.
Потребители информации (сервер server-stomp) получают из через брокер KAFKA и через протокол STOMP предоставляют их потребителям.

Порядок использования:

1. Собираем модули:
cd ./monotiring
docker clean install

2. Создаем docker images
cd ./monitoring/ADMIN
.\create_docker_images.bat

3. Запускаем приложение через docker-compose
cd ./monitoring/ADMIN
docker compose -f .\start_all.yml up

Примечание: можно так же запустить систему локально: 
1. запустить docker images для postgres и kafka (.\start_postgres_and_kafka.yml)
2. запустить discovery-server.
3. запустить требуемые компоненты.

