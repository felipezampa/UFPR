*** Settings ***
Documentation        Demonstração do RobotFramework com a Secretaria On-line
Test Setup           Abrir Browser
Test Teardown        Fechar Browser
Resource             resources.robot

*** Test Cases ***

CT01: Verificar contatos da secretaria online
    [Documentation]  Verifica se os contatos são corretamente mostrados
    [Tags]    contatos
    Acessar Secretaria Online no endereço "$URL"
    Acessar página de contato
    Verificar se contato é exibido

CT02: Logar na Secretaria Online como ALUNO
    [Tags]    login sucesso
    Acessar Secretaria Online no endereço "$URL"
    Informar dados de login de ALUNO
    Verificar se login foi realizado com sucesso