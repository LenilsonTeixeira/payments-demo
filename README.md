# Payments-demo

## Contextualização:
API-Payments e responsável por processar ordens de compras. 
Através de uma comparação entre o valor do crédito do cliente e do valor do produto o status da ordem de compra será "Aprovado" ou "Negado". Em conjunto cada transação é postada através de tópico do Kafka que em paralelo são consumidas através do <b>MS-Orders<b/>, onde o mesmo realiza o armazenamento das transações no MongoDB.


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

