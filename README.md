# Payments-demo

## Contextualização:
O API-Payments e responsável por criar ordens de compras, com base na comparação entre o valor do crédito do cliente e do valor do produto uma ordem  pode assumir o <b>status</b> de "Aprovada" ou "Negada". Em paralelo cada transação processada é postada através de tópico do Kafka. 
<b>O MS-Orders<b/> consome as mensagens do Kafka e realiza o armazenamento do histórico das transações no MongoDB.


## Passo a passo para executar o projeto

1 - Acessar diretório <b>docker</b><br>
2 - Executar script <b>sh docker-build.sh</b><br>
3 - Executar script <b>sh docker-start.sh</b> <br>


#### Endpoints:

API-Payments: http://localhost:8082/api-payments/v1/

MS-orders: http://localhost:8080/ms-orders/v1/

PGAdmin URL: http://localhost:15432/login<br>
<b>Email</b>:administrator@sys.com <b>Password</b>:admin

Prometheus URL: http://localhost:9090

Grafana URL:http://localhost:3000<br>
<b>User</b>:admin <b>Password</b>:admin

