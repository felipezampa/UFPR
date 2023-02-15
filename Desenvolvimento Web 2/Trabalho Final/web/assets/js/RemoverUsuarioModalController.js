var modalClasse = document.querySelector(".js-remover-usuario-modal-container");
var nomeFuncio = document.querySelector(".js-usuario-nome");
var idInput = document.getElementById("excluir-action");

function chamaModal(id, nome) {
    console.log(id);
    modalClasse.style.display = "block";
    nomeFuncio.textContent = nome;
    idInput.value = id;
  console.log(idInput);
}


var cancelar = document.querySelectorAll(".js-cancel-button");
cancelar[0].addEventListener("click", (e) => {
    modalClasse.style.display = "none";
});
cancelar[1].addEventListener("click", (e) => {
    modalClasse.style.display = "none";
});
       