# ds152-bantads-gerente

## GERENTE

## POST `/gerentes`
BODY:
```json
{
    "nome": "Felipe",
    "cpf": "63211213112",
    "email": "felipe@bantads.com",
    "phone": "41990001060",
    "numClientes": 3
}
```
`numClientes` não importa o valor atribuido pois a lógica é feita na GerenteController então não adianta



## PUT `/gerentes/{id}`
BODY:
```json
{
    "nome": "Felipe Editado",
    "cpf": "63211213112",
    "email": "AA@bantads.com",
    "phone": "41990001060",
    "numClientes": 3
}
```
`numClientes` - não importa o valor atribuido pois a lógica é feita na GerenteController então não adianta
`cpf` - cpf também não será alterado, então não adianta colocar no body



----------

## CLIENTE


## GET `/clientes`
É Possível fazer 3 tipos de requisições <br/>
#### `/clientes?aprovado=true` - Recupera os clientes aprovados
#### `/clientes?aprovado=false` -  Recupera os clientes que ainda não foram aprovados
#### `/clientes` - Recupera todos os clientes, aprovados e não aprovados

## PUT `/clientes/{id}`
BODY:
```json
{
    "aprovado": true
}
```

