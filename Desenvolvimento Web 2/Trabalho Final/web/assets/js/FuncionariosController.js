var idFuncionario = document.getElementById("edidar-action");
var nome = document.getElementById("detalhe-nome");
var email = document.getElementById("detalhe-email");
var dataNascimento = document.getElementById("detalhe_data");
var buttonCriarSalvar = document.querySelector(".js-button-usuario-button");
var buttonLimpar = document.querySelector(".js-limpar-formulario-button");
var error = document.querySelectorAll(".invalid-feedback");
function FuncionariosController() {

    this.init = function () {
        validarForm();
    }


    function validarForm() {


        nome.addEventListener("blur", (e) => {
            error[0].style.display = "block";
            error[0].textContent = validaNome(nome.value);
        });

        email.addEventListener("blur", (e) => {
            error[1].style.display = "block";
            error[1].textContent = validaEmail(email.value);
        });
    }

    validaEmail = function (email) {
        if (email.length < 5)
            return "Email deve ter no mínimo 5 caracteres";
        if (email.length > 255)
            return "O email deve ter no máximo 255 caracteres";
        if (!email.match(/^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/))
            return "Formato de e-mail inválido";

        return "";
    }

    validaNome = function (nome) {
        if (nome.length < 5)
            return "Nome deve ter no mínimo 5 caracteres";
        if (nome.split(" ").length < 2)
            return "Não é permitido informar apenas o primeiro nome";
        if (nome.length > 255)
            return "O nome deve ter no máximo 255 caracteres";
        if (!nome.match(/^[a-zA-ZÀ-ú ]+$/))
            return "O nome deve conter apenas letras";
        return "";
    }
}


var funcionariosController;
$(document).ready(function () {
    funcionariosController = new FuncionariosController();
    funcionariosController.init();
});
function mostrar(id) {


    var valores = document.querySelectorAll("table tr .usuario");
    var listaFuncionario = [];
    var arratList = [];
    for (let n = 0; n < valores.length; ) {
        count = 0;
        arratList = [];
        while (count <= 3) {
            arratList[count] = valores[n].innerHTML;
            count++;
            n++;
        }
        listaFuncionario.push(arratList);
    }

    const buscaFuncionario = listaFuncionario.find((element) => element[0] === id);
    idFuncionario.value = buscaFuncionario[0];
    nome.value = buscaFuncionario[1];
    email.value = buscaFuncionario[2];
    dataNascimento.value = buscaFuncionario[3];
    buttonCriarSalvar.value = "Salvar";
    buttonCriarSalvar.style.backgroundColor = "#0d6efd";
}


buttonLimpar.addEventListener("click", (e) => {
    buttonCriarSalvar.value = "Criar";
    buttonCriarSalvar.style.backgroundColor = "#198754";
});

buttonCriarSalvar.addEventListener("Click", (e)=>{
    window.location.reload();
});
