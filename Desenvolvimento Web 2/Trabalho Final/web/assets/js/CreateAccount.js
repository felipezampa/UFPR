var n = 0;

buttonNext.addEventListener("click", () => {
  n++;

  const ballClasses = document.querySelectorAll(".bolas nav");
  const lineClasses = document.querySelectorAll(".linhas div");

  if (n === 1) {
    addClasse(lineClasses[0], "linha-next");
    addClasse(ballClasses[0], "ball-next");
    setDisplayNone("primeiraDiv");
    setDisplayFlex("endereco");
    setDisplayInlineBlock("buttonPreview");
    setJustifyBetween("signin_button");
  } else if (n === 2) {
    addClasse(lineClasses[1], "linha-next");
    addClasse(ballClasses[1], "ball-next");
    setDisplayNone("endereco");
    setDisplayFlex("signin_confirm");
    alterText("alterText", "Clique em finalizar para concluir seu cadastro ");
    setDisplayNone("buttonNext");
  } else if (n === 3) {
    addClasse(ballClasses[2], "ball-next");
    setDisplayFlex("endereco");
    alterText("alterText", "Confirme seu cadastro");
  } else {
    n = 3;
  }
  console.log(n);
});

buttonPreview.addEventListener("click", () => {
  const ballClasses = document.querySelectorAll(".bolas nav");
  const lineClasses = document.querySelectorAll(".linhas div");

  setDisplayNone("signin_confirm");
  alterText("alterText", "Criar conta");

  if (n === 1) {
    removeClasse(lineClasses[0], "linha-next");
    removeClasse(ballClasses[0], "ball-next");
    setJustifyEnd("signin_button");
    setDisplayNone("buttonPreview");
    setDisplayNone("endereco");
    setDisplayFlex("primeiraDiv");
  } else if (n === 2) {
    removeClasse(lineClasses[1], "linha-next");
    removeClasse(ballClasses[1], "ball-next");
    setDisplayInlineBlock("buttonPreview");
    setDisplayInlineBlock("buttonNext");
    setJustifyBetween("signin_button");
    setDisplayFlex("endereco");
  } else if (n === 3) {
    removeClasse(ballClasses[2], "ball-next");
  } else {
    n = 1;
  }
  n--;
  console.log(n);
});

function addClasse(elemento, newClass) {
  elemento.classList.add(newClass);
}
function removeClasse(elemento, classRemove) {
  elemento.classList.remove(classRemove);
}
function setJustifyBetween(id) {
  document.getElementById(id).style.justifyContent = "space-between";
}
function setJustifyEnd(id) {
  document.getElementById(id).style.justifyContent = "flex-end";
}
function setDisplayNone(id) {
  document.getElementById(id).style.display = "none";
}
function setDisplayFlex(id) {
  document.getElementById(id).style.display = "flex";
}
function setDisplayInlineBlock(id) {
  document.getElementById(id).style.display = "inline-block";
}
function setDisplayBlock(id) {
  document.getElementById(id).style.display = "block";
}
function alterText(id, text) {
  document.getElementById(id).textContent = text;
}
