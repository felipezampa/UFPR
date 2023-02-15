*** Settings ***
Documentation  Implementação das keywords dos casos de teste da Secretaria On-line

Library    SeleniumLibrary
Library    DateTime
Library    String

*** Variables ***
${BROWSER}            chrome
${URL}                http://200.236.3.198:28080/secretariaonline2/Home 
${TEXTO_PAG_LOGIN}    //h3[contains(.,'Secretaria On-line do SEPT - Login')] 
${TEXT_PAG_INICIAL}   //h2[contains(.,'Solicitações')]
${GRR_ALUNO}          GRR11111111
${SENHA_ALUNO}        123
${LOGIN_SECRETARIA}   rafaela.fontana@ufpr.br
${SENHA_SECRETARIA}   123
${TEXTO_BASE}         Este é um texto utilizado para teste das solicitações da Secretaria Online. Criado em:
${LOCATOR_CONSULTAR}  //a[@href='consultarSolicitacao?origem=home&id=XXXXX'][contains(.,'Consultar')]
${LOCATOR_DELIBERAR}  //a[@href='deliberarSolicitacao?idSolicitacao=XXXXX'][contains(.,'Deliberar')]

*** Keywords ***
Abrir o Navegador
    Open Browser  browser=${BROWSER}
    Maximize Browser Window

Fechar o Navegador
    Capture Page Screenshot
    Close Browser

Acessar secretaria on-line como ALUNO
    Go To    ${URL}
    Wait Until Element Is Visible    ${TEXTO_PAG_LOGIN}
    Logar com "${GRR_ALUNO}" "${SENHA_ALUNO}"

Acessar secretaria on-line como SECRETARIA
    Go To    ${URL}
    Wait Until Element Is Visible    ${TEXTO_PAG_LOGIN}
    Logar com "${LOGIN_SECRETARIA}" "${SENHA_SECRETARIA}"

Logar com "${USUARIO}" "${SENHA}"
    # Insere e-mail e senha de acesso válidos e clica no botão submit
    # Espera título da página de solicitações aparecer
    Input Text    email    ${USUARIO}
    Input Text    senha    ${SENHA}
    Click Button    submit
    Wait Until Element Is Visible    ${TEXT_PAG_INICIAL}

Clicar em "Abrir Nova Solicitação"
    # Clica para abrir uma nova solicitação e esparar o título da página aparecer
    Click Element   //a[contains(.,'Abrir Nova Solicitação')]
    Wait Until Element Is Visible    //h3[contains(.,'Nova Solicitação')]

Selecionar o tipo de solicitação "Requerimento Geral"
    Select From List By Label    //select[contains(@name,'idTipoSolicitacao')]  Requerimento Geral
    Click Button    submit

Preencher o campo da solicitação - sem arquivo
    ${DATA ATUAL}    Get Current Date
    ${TEXTO SOLICITACAO}    Catenate    ${TEXTO_BASE}    ${DATA ATUAL}
    Input Text    descricao    ${TEXTO SOLICITACAO}
    
Clicar em "Salvar"
    Click Button    submit      
    ${URL}    Get Location 
    Pega Id da ${URL}
    Deslogar

Deslogar
    Click Element    (//a[@href='logarUsuarioSair1'][contains(.,'Sair')])[2]

Pega Id da ${URL}
    ${STR_TMP}    Fetch From Left    ${URL}    &descricao
    ${STR_TMP}    Fetch From Right    ${STR_TMP}    ?id=
    Set Suite Variable    ${ID_SOLICITACAO_CRIADA}    ${STR_TMP}
    Log    ${ID_SOLICITACAO_CRIADA} 

A solicitação criada deve aparecer na lista de solicitações do aluno
    Acessar secretaria on-line como ALUNO
    Localizar solicitação criada
    Deslogar

Localizar solicitação criada
    ${LOCATOR}    Replace String    ${LOCATOR_CONSULTAR}    XXXXX    ${ID_SOLICITACAO_CRIADA}
    Log    ${LOCATOR}
    Wait Until Element Is Visible    ${LOCATOR} 

Localizar a solicitação do aluno e clicar em "Deliberar"
    ${LOCATOR}    Replace String    ${LOCATOR_DELIBERAR}    XXXXX    ${ID_SOLICITACAO_CRIADA}
    Log    ${LOCATOR}
    Click Element    ${LOCATOR}

Preencher os campos, finalizando a solicitação
    ${DATA_ATUAL}    Get Current Date
    ${TEXTO_DELIBERACAO}    Catenate    Deliberado em ${DATA_ATUAL}
    Input Text    descricaoDeliberacao    ${TEXTO_DELIBERACAO}
    Select From List By Label    finalizar    Sim 

Clicar em "Salvar Deliberação"
    Click Button    submit
    Wait Until Element Is Visible    //h2[contains(.,'Solicitações')]
    Deslogar

A solicitação deve aparecer como "Concluída" para o aluno
    Acessar secretaria on-line como ALUNO
    ${LOCATOR}    Replace String    ${LOCATOR_CONSULTAR}    XXXXX    ${ID_SOLICITACAO_CRIADA} 
    Click Element    ${LOCATOR}
    Page Should Contain    Concluida
    Deslogar

#### GHERKIN
 O aluno está logado na Secretaria On-line
    Acessar secretaria on-line como ALUNO