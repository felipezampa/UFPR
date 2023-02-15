*** Settings ***
Documentation    Esta suíte de testes verifica o funcionamento de pesquisas 
          ...    de certos vídeos no YouTube
Resource         resources.robot
Test Setup       Abrir o Navegador
Test Teardown    Fechar o Navegador

*** Test Cases ***
Caso de teste 1: Buscar aula de Web Service login em Java
    [Tags]    pesquisa
    Acessar YouTube
    Buscar aula de Web Service Login em Java
    Verificar se aula do Prof. Razer aparece nos resultados

Caso de teste 2: Buscar aula de Web Service login em Java com Gherkin
    [Tags]    pesquisa
    Given Acessar YouTube
    When Buscar aula de Web Service Login em Java
    Then Verificar se aula do Prof. Razer aparece nos resultados

Caso de teste 3: Buscar um vídeo aleatório no youtube
    [Tags]    pesquisa
    Acessar YouTube
    Buscar vídeo Le Fishe
    
    