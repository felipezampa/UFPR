# ds152-bantads-orchestration

## Rotas

**AVISO:** A flag 'rabbit-actions.validate' deve ser setada para true apenas quando o ambiente de desenvolvimento estiver pronto para receber as mensagens do RabbitMQ, com todos os microsserviços implementando todas as mensagens de saga. Caso contrário, a flag deve permanecer em false, tornando as requests mais lentas, mas garantindo que seja possível enviar múltiplas mensagens pela saga.

### POST `/customers`

Cria um novo cliente e conta bancária.

#### Request (REST)

```json
{
   "nome":"E",
   "cpf":"05450619979",
   "email":"asa@sksk.com",
   "endereco":{
      "cep":"80444-777",
      "logradouro":"Aaaaaa",
      "numero":6556,
      "complemento":"5ijasdi ijasd5",
      "bairro":"wekke",
      "cidade":"weddddw",
      "estado":"PR"
   },
   "salario":555.55,
   "senha":"password1",
   "confirmarSenha":"password1"
}
```


#### Mensageria (RabbitMQ)

**- Fila `orchestration-selfregistration-auth`**
MS: Auth

Payload: 
```json
{
   "manager":{
      "id":"27b56f30-9d7f-415c-bb4a-eabced912e35",
      "idExternoUsuario":null,
      "nome":"E",
      "cpf":"05450619979",
      "endereco":{
         "id":null,
         "cep":"80444-777",
         "logradouro":"Aaaaaa",
         "numero":6556,
         "complemento":"5ijasdi ijasd5",
         "bairro":"wekke",
         "cidade":"weddddw",
         "estado":"PR"
      },
      "salario":555.55,
      "saga":"3bbe479c-186d-4bcf-bab8-671ebc34840b"
   },
   "action":"auth-register"
}
```

Actions aguardadas: 

`auth-ok` - Criação de conta realizada
`auth-duplicated` - CPF/dados já cadastrados
`auth-error` - Erro genérico na criação de conta

**- Fila `orchestration-selfregistration-customer`**
MS: Cliente

Payload: 
```json
{
   "manager":{
      "id":"2e7aeda7-4d07-487e-8d5d-d75a41438140",
      "idExternoUsuario":null,
      "nome":"E",
      "cpf":"05450619979",
      "endereco":{
         "id":null,
         "cep":"80444-777",
         "logradouro":"Aaaaaa",
         "numero":6556,
         "complemento":"5ijasdi ijasd5",
         "bairro":"wekke",
         "cidade":"weddddw",
         "estado":"PR"
      },
      "salario":555.55,
      "saga":"6ed2c02d-3f1b-4db6-adee-1673b5a22532"
   },
   "action":"customer-register"
}

```

Actions aguardadas: 

`customer-ok` - Retorno em caso de sucesso
`customer-duplicated` - CPF/dados já cadastrados
`customer-error` - Retorno em caso de erro genérico

**- Fila `orchestration-selfregistration-account`**
MS: Conta

Payload: 
```json

{
   "manager":{
      "id":"2e7aeda7-4d07-487e-8d5d-d75a41438140",
      "idExternoUsuario":null,
      "nome":"E",
      "cpf":"05450619979",
      "endereco":{
         "id":null,
         "cep":"80444-777",
         "logradouro":"Aaaaaa",
         "numero":6556,
         "complemento":"5ijasdi ijasd5",
         "bairro":"wekke",
         "cidade":"weddddw",
         "estado":"PR"
      },
      "salario":555.55,
      "saga":"6ed2c02d-3f1b-4db6-adee-1673b5a22532"
   },
   "action":"account-register"
}
```

Actions aguardadas: 

`account-ok` - Retorno em caso de sucesso
`account-duplicated` - CPF/dados já cadastrados
`account-error` - Retorno em caso de erro genérico

**- Fila `orchestration-selfregistration-manager`**
MS: Gerente

Payload: 
```json
{
   "manager":{
      "id":"5f798518-b912-47e3-9d77-b4c265d4f850",
      "idExternoUsuario":null,
      "nome":"E",
      "cpf":"05450619979",
      "endereco":{
         "id":null,
         "cep":"80444-777",
         "logradouro":"Aaaaaa",
         "numero":6556,
         "complemento":"5ijasdi ijasd5",
         "bairro":"wekke",
         "cidade":"weddddw",
         "estado":"PR"
      },
      "salario":555.55,
      "saga":"c765e9bf-7a75-485a-b549-0680b1cde7b3"
   },
   "action":"manager-register"
}
```

Actions aguardadas: 

`manager-ok` - Retorno em caso de sucesso
Response: 
```json
{
   "manager":{
      "id":"2e7aeda7-4d07-487e-8d5d-d75a41438140",
      "idManager":"2e7aeda7-4d07-487e-8d5d-d75a41438140"
   },
   "action":"manager-ok"
}

```

`manager-error` - Retorno em caso de erro genérico

*EM CASO DE SUCESSO NA OBTENÇÃO DE UM GERENTE PARA A CONTA, A SAGA IRÁ REALIZAR:*
**- Fila `orchestration-selfregistration-account-manager`**
MS: Conta

Payload: 
```json
{
   "manager":{
      "id":"2e7aeda7-4d07-487e-8d5d-d75a41438140",
      "idManager":"2e7aeda7-4d07-487e-8d5d-d75a41438140",
      "idExternoUsuario":null,
      "nome":null,
      "cpf":null,
      "endereco":null,
      "salario":null,
      "saga":null
   },
   "action":"account-manager"
}

```

Actions aguardadas: 
`account-manager-ok` - Retorno em caso de sucesso
`account-manager-error` - Retorno em caso de erro genérico

**- Fila `orchestration-selfregistration-customer-manager`**
MS: Cliente

Payload: 
```json
{
   "manager":{
      "id":"2e7aeda7-4d07-487e-8d5d-d75a41438140",
      "idManager":"2e7aeda7-4d07-487e-8d5d-d75a41438140",
      "idExternoUsuario":null,
      "nome":null,
      "cpf":null,
      "endereco":null,
      "salario":null,
      "saga":null
   },
   "action":"customer-manager"
}


```

Actions aguardadas: 
`customer-manager-ok` - Retorno em caso de sucesso
`customer-manager-error` - Retorno em caso de erro genérico

#### Response (REST)

```json

{
    "success": true,
    "message": "Cliente criado com sucesso",
    "data": {
        "id": "1129b221-62a3-4b55-8b97-a67054437efd",
        "idExternoUsuario": null,
        "nome": "E",
        "cpf": "05450619979",
        "endereco": {
            "id": null,
            "cep": "80444-777",
            "logradouro": "Aaaaaa",
            "numero": 6556,
            "complemento": "5ijasdi ijasd5",
            "bairro": "wekke",
            "cidade": "weddddw",
            "estado": "PR"
        },
        "salario": 555.55,
        "saga": "a91d74a9-aeee-41ef-a2c1-61d0be9e7e98"
    }
}
```

### PUT `/customers`

Altera dados de um novo cliente.

#### Request (REST)

```json
{
"id": ""
   "nome":"E",
   "cpf":"05450619979",
   "email":"asa@sksk.com",
   "endereco":{
      "cep":"80444-777",
      "logradouro":"Aaaaaa",
      "numero":6556,
      "complemento":"5ijasdi ijasd5",
      "bairro":"wekke",
      "cidade":"weddddw",
      "estado":"PR"
   },
   "salario":555.55
}
```


#### Mensageria (RabbitMQ)

**- Fila `orchestration-selfregistration-auth`**
MS: Auth

Payload: 
```json
{
   "manager":{
      "id":"27b56f30-9d7f-415c-bb4a-eabced912e35",
      "idExternoUsuario":null,
      "nome":"E",
      "cpf":"05450619979",
      "endereco":{
         "id":null,
         "cep":"80444-777",
         "logradouro":"Aaaaaa",
         "numero":6556,
         "complemento":"5ijasdi ijasd5",
         "bairro":"wekke",
         "cidade":"weddddw",
         "estado":"PR"
      },
      "salario":555.55,
      "saga":"3bbe479c-186d-4bcf-bab8-671ebc34840b"
   },
   "action":"auth-update"
}
```

Actions aguardadas: 

`auth-update-ok` - Criação de conta realizada
`auth-update-duplicated` - CPF/dados já cadastrados
`auth-update-error` - Erro genérico na criação de conta

**- Fila `orchestration-selfregistration-customer`**
MS: Cliente

Payload: 
```json
{
   "manager":{
      "id":"2e7aeda7-4d07-487e-8d5d-d75a41438140",
      "idExternoUsuario":null,
      "nome":"E",
      "cpf":"05450619979",
      "endereco":{
         "id":null,
         "cep":"80444-777",
         "logradouro":"Aaaaaa",
         "numero":6556,
         "complemento":"5ijasdi ijasd5",
         "bairro":"wekke",
         "cidade":"weddddw",
         "estado":"PR"
      },
      "salario":555.55,
      "saga":"6ed2c02d-3f1b-4db6-adee-1673b5a22532"
   },
   "action":"customer-update"
}

```


Actions aguardadas: 

`customer-update-ok` - Criação de conta realizada
`customer-update-error` - Erro genérico 


**- Fila `orchestration-selfregistration-account`**
MS: Conta

Payload: 
```json

{
   "manager":{
      "id":"2e7aeda7-4d07-487e-8d5d-d75a41438140",
      "idExternoUsuario":null,
      "nome":"E",
      "cpf":"05450619979",
      "endereco":{
         "id":null,
         "cep":"80444-777",
         "logradouro":"Aaaaaa",
         "numero":6556,
         "complemento":"5ijasdi ijasd5",
         "bairro":"wekke",
         "cidade":"weddddw",
         "estado":"PR"
      },
      "salario":555.55,
      "saga":"6ed2c02d-3f1b-4db6-adee-1673b5a22532"
   },
   "action":"account-update"
}
```

Actions aguardadas: 

`account-update-ok` - Retorno em caso de sucesso
`account-update-error` - Retorno em caso de erro genérico

### DELETE `/customers/{id}`

Altera dados de um novo cliente.

#### Request (REST)

/customers/{id}


#### Mensageria (RabbitMQ)

**- Fila `orchestration-selfregistration-auth`**
MS: Auth

Payload: 
```json
{"manager":{"id":"2e7aeda7-4d07-487e-8d5d-d75a41438140"},"action":"delete-account"}

```

Actions aguardadas: 

`auth-delete-ok` - Criação de conta realizada
`auth-delete-duplicated` - CPF/dados já cadastrados
`auth-delete-error` - Erro genérico na criação de conta

**- Fila `orchestration-selfregistration-customer`**
MS: Cliente

Payload: 
```json
{"manager":{"id":"2e7aeda7-4d07-487e-8d5d-d75a41438140"},"action":"delete-account"}
```

Actions aguardadas: 

`customer-delete-ok` - Retorno em caso de sucesso
`customer-delete-error` - Erro genérico 


**- Fila `orchestration-selfregistration-account`**
MS: Conta

Payload: 
```json

{"manager":{"id":"2e7aeda7-4d07-487e-8d5d-d75a41438140"},"action":"delete-account"}

```

Actions aguardadas: 

`account-delete-ok` - Retorno em caso de sucesso
`account-delete-error` - Retorno em caso de erro genérico
