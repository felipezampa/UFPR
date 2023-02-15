*** Settings ***
Documentation       Implementação das keywords dos casos de teste da Secretaria On-line

Library             SeleniumLibrary
Library             DateTime
Library             String


*** Variables ***
${BROWSER}          chrome
${LINK_YOUTUBE}     https://www.youtube.com/
${TEXTO_YOUTUBE}    (//yt-icon[contains(@id,'logo-icon')])[1]
${LOGIN}            testesengenhariaweb2@gmail.com
${SENHA}            Teste#1234
${LINK_VIDEO}
...    //yt-formatted-string[@class='style-scope ytd-video-renderer'][contains(.,'Arctic Monkeys - Cornerstone (Official Video)')]


*** Keywords ***
Abrir o Navegador
    Open Browser    browser=${BROWSER}
    Maximize Browser Window

Fechar o Navegador
    Capture Page Screenshot
    Close Browser

Acessar o YouTube
    Go To    ${LINK_YOUTUBE}
    Wait Until Element Is Visible    ${TEXTO_YOUTUBE}

#CT1
Clicar no botão "Filmes" na barra lateral
    Click Element    //tp-yt-paper-item[@class='style-scope ytd-guide-entry-renderer'][contains(.,'Filmes')]
    

Deve aparecer os principais filmes disponíveis
    Wait Until Page Contains    Filmes e programas

#CT2

Buscar poker no Youtube
    Input Text    //input[contains(@autocapitalize,'none')]    Poker
    Click Button    search-icon-legacy

Verificar se aparecem vídeos sobre poker
    Wait Until Element Is Visible
    ...    //ytd-item-section-renderer[contains(@class,'style-scope ytd-section-list-renderer')]

#CT3

Clicar no botão “Em Alta” na barra lateral
    Click Element    //tp-yt-paper-item[@class='style-scope ytd-guide-entry-renderer'][contains(.,'Em alta')]

Deve aparecer os vídeos mais vistos na aba Em alta
    Wait Until Page Contains    Em alta

#CT4

Clicar no botão “Jogos” na barra lateral
    Click Element    //a[@class='yt-simple-endpoint style-scope ytd-guide-entry-renderer'][contains(.,'Jogos')]

Deve aparecer os principais jogos ao vivo
    Wait Until Page Contains    Jogos

#CT5

Buscar Cornerstone - Arctic Monkeys no Youtube
    Input Text    //input[contains(@autocapitalize,'none')]    Arctic Monkeys - Cornerstone
    Click Button    search-icon-legacy

Clicar no vídeo Cornerstone - Arctic Monkeys
    Wait Until Element Is Visible
    ...    //yt-formatted-string[@class='style-scope ytd-video-renderer'][contains(.,'Arctic Monkeys - Cornerstone (Official Video)')]
    Click Element    locator=${LINK_VIDEO}

Verificar se o vídeo é exibido
    Wait Until Element Is Visible
    ...    //yt-formatted-string[@class='style-scope ytd-watch-metadata'][contains(.,'Arctic Monkeys - Cornerstone (Official Video)')]
