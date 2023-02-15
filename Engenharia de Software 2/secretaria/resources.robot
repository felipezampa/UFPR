*** Settings ***
Documentation  Resources dos testes da Secretaria On-line
Library        SeleniumLibrary

*** Variables ***
#Atenção! Este é um ambiente de testes da Secretaria On-line!
${URL}                http://200.236.3.198:28080/secretariaonline2/Home  
${GRR_ALUNO}          GRR11111111
${SENHA_ALUNO}        123
${TEXTO_PAG_LOGIN}    //h3[contains(.,'Secretaria On-line do SEPT - Login')]
${TEXTO_PAG_INICIAL}  //h2[contains(.,'Solicitações')]
${LINK_CONTATO}       //a[contains(.,'Contato')]

*** Keywords ***
Abrir Browser
    Open Browser  browser=chrome
    Maximize Browser Window

Fechar Browser
    Capture Page Screenshot
    Close Browser

Acessar Secretaria Online no endereço "${URL}"
    Go To    ${URL}
    Wait Until Element Is Visible    ${TEXTO_PAG_LOGIN}

Acessar página de contato 
    Click Element    locator=${LINK_CONTATO}

Verificar se contato é exibido
    Element Should Be Visible    locator=//h3[contains(.,'Contato com os Administradores do Sistema')]    

Informar dados de login de ALUNO
    Input Text  email  ${GRR_ALUNO}
    Input Text  senha  ${SENHA_ALUNO}
    Click Button  submit
    Wait Until Element Is Visible    ${TEXTO_PAG_INICIAL}

Verificar se login foi realizado com sucesso
    Element Should Be Visible    ${TEXTO_PAG_INICIAL}    
    