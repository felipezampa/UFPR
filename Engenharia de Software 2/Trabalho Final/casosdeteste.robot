*** Settings ***
Documentation    Esta suíte de testes verifica o funcionamento de pesquisas 
          ...    de certos vídeos e utilização do YouTube
Resource         resources.robot
Test Setup       Abrir o Navegador
Test Teardown    Fechar o Navegador

*** Test Cases ***
Caso de teste 1: Acessar a categoria de Filmes
   [Tags]    primeiro
    Acessar o YouTube
    Clicar no botão "Filmes" na barra lateral
    Deve aparecer os principais filmes disponíveis

Caso de teste 2: Busca vídeo sobre poker no Youtube 
    [Tags]  segundo
    Acessar o Youtube
    Buscar poker no Youtube
    Verificar se aparecem vídeos sobre poker

Caso de teste 3: Acessar vídeos mais vistos recentemente
    [Tags]    terceiro
    Acessar o YouTube
    Clicar no botão “Em Alta” na barra lateral
    Deve aparecer os vídeos mais vistos na aba Em alta

Caso de teste 4: Acessar a categoria jogos
    [Tags]    quarto
    Acessar o YouTube
    Clicar no botão “Jogos” na barra lateral
    Deve aparecer os principais jogos ao vivo

Caso de teste 05: Busca e clica videoclipe de Cornerstone - Arctic Monkeys  
   [Tags]  pesquisa
    Acessar o Youtube
    Buscar Cornerstone - Arctic Monkeys no Youtube
    Clicar no vídeo Cornerstone - Arctic Monkeys
    Verificar se o vídeo é exibido
