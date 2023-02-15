*** Settings ***
Documentation       Implementação das keywords dos casos de teste do YouTube

Library             SeleniumLibrary

*** Variables ***
${BROWSER}          chrome
${LINK_YOUTUBE}     https://www.youtube.com/
${TEXTO_YOUTUBE}    (//yt-icon[contains(@id,'logo-icon')])[1]
${TEXTO_RAZER}      Web Service Login em Java
${TEXTO_VÍDEO}      le fishe

*** Keywords ***
Abrir o Navegador
    Open Browser    browser=${BROWSER}
    Maximize Browser Window

Fechar o Navegador
    Capture Page Screenshot
    Close Browser

Acessar YouTube
    Go To    ${LINK_YOUTUBE}
    Wait Until Element Is Visible    ${TEXTO_YOUTUBE}

Buscar aula de Web Service Login em Java
    Input Text    search_query    ${TEXTO_RAZER}
    Click Button    search-icon-legacy

Verificar se aula do Prof. Razer aparece nos resultados
    Wait Until Page Contains   Prof. Razer

Buscar vídeo Le Fishe
    Input Text    search_query    ${TEXTO_VÍDEO}
    Click Button    search-icon-legacy

Verificar se vídeo do Le Fishe aparece nos resultados
    Wait Until Page Contains    le fishe