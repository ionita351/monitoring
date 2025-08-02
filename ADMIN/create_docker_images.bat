cd ../discovery-server
docker build -t discovery-server .
cd ../server-grpc
docker build -t server-grpc .
cd ../server-rest
docker build -t server-rest .
cd ../server-stomp
docker build -t server-stomp .
cd ../server-ws
docker build -t server-ws .

cd ../client-feign
docker build -t client-feign .
cd ../client-grpc
docker build -t client-grpc .
cd ../client-rest
docker build -t client-rest .
cd ../client-ws
docker build -t client-ws .