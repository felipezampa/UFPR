function UsuariosController() {
    const _this = this;

    this.getFuncionarios = function() {
        const todosUsuarios = fetchUsuarios();

        return todosUsuarios.filter((usuario) => usuario.tipo === 'FUNCIONARIO');
    }

    this.getUsuario = function(usuarioId) {
        const todosUsuarios = fetchUsuarios();
        return todosUsuarios.find((usuario) => usuario.id === usuarioId);
    }

    this.editar = function(id, newUserData) {
        if (!id) return;

        let usuarios = fetchUsuarios(true);
        let usuario = usuarios.find((usuario) => usuario.id === id);
        let index = usuarios.indexOf(usuario);

        let usuarioEditado = Object.assign(usuario, newUserData);
        usuarios[index] = usuarioEditado;

        localStorage.setItem("usuarios", JSON.stringify(usuarios));
    }

    this.adicionar = function(novoUsuario) {
        const userParams = new URLSearchParams(novoUsuario).toString()
        fetch('AuthServlet?action=salvarConta&' + userParams, {
            method: 'POST'
        });
    }

    this.remover = function(id) {
        fetch('FuncionarioServlet?action=deletar&id=' + id, {
            method: 'POST'
        }).then(() => {
            window.location.reload();
        });
    }

    this.init = function() {
    }

    //Sempre que for feita alguma alteração em um usuário precisa passar getDeleted = true para não perder os que estão marcados como deletados
    var fetchUsuarios = function(getDeleted = false) {
        let todosUsuarios = JSON.parse(localStorage.getItem("usuarios"));
        if (getDeleted) return todosUsuarios;

        return todosUsuarios.filter((usuario) => !usuario.deletado);
    }

    var populateLocalStorageIfNecessary = function() {
        let storedItems = localStorage.getItem("usuarios");
        if (storedItems) return;

        const usuariosMockData = [
            {
                id: 1,
                cpf: "769.612.530-32",
                nome: "Mário da Silva",
                email: "mario@email.com",
                endereco: "Rua 1, 123",
                telefone: "(11) 1234-5678",
                tipo: "FUNCIONARIO",
                deletado: false
            },
            {
                id: 2,
                cpf: "157.894.390-63",
                nome: "Maria da Silva",
                email: "maria@email.com",
                endereco: "Rua 2, 456",
                telefone: "(11) 98765-4321",
                tipo: "FUNCIONARIO",
                deletado: false
            },
            {
                id: 3,
                cpf: "157.894.390-63",
                nome: "João",
                email: "joao@email.com",
                endereco: "Rua 1, 123",
                telefone: "(11) 1234-5678",
                tipo: "CLIENTE",
                deletado: false
            },
            {
                id: 4,
                cpf: "130.768.640-09",
                nome: "José",
                email: "jose@email.com",
                endereco: "Rua 3, 789",
                telefone: "(11) 1234-5678",
                tipo: "CLIENTE",
                deletado: false
            },
            {
                id: 5,
                cpf: "157.894.390-63",
                nome: "Joana",
                email: "joana@email.com",
                endereco: "Rua 2, 456",
                telefone: "(11) 98765-4321",
                tipo: "CLIENTE",
                deletado: false
            }
        ]

        setState(usuariosMockData);
    }

    var setState = function(usuarios) {
        localStorage.setItem("usuarios", JSON.stringify(usuarios));
    }

    this.validaNome = function(nome) {
        if (nome.length < 5) return "Nome deve ter no mínimo 5 caracteres";
        if(nome.split(" ").length < 2) return "Não é permitido informar apenas o primeiro nome";
        if (nome.length > 255) return "O nome deve ter no máximo 255 caracteres";
        if (!nome.match(/^[a-zA-ZÀ-ú ]+$/)) return "O nome deve conter apenas letras";

        return "";
    }

    this.validaEmail = function(email) {
        if (email.length < 5) return "Email deve ter no mínimo 5 caracteres";
        if (email.length > 255) return "O email deve ter no máximo 255 caracteres";
        if (!email.match(/^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/)) return "Formato de e-mail inválido";

        return "";
    }

    // Aqui não valida se é um cpf válido, apenas se está no formato correto.
    // No back podemos validar
    this.validaCPF = function(cpf) {
        if (cpf.length !== 14) return "O CPF deve conter 11 dígitos";
        if (!cpf.match(/^[0-9]{3}\.[0-9]{3}\.[0-9]{3}-[0-9]{2}$/)) return "O CPF deve estar no formato 000.000.000-00";

        return "";
    }

    this.validaTelefone = function(telefone) {
        if (telefone.length !== 15) return "O telefone deve conter 12 dígitos";
        if (!telefone.match(/^\([0-9]{2}\) [0-9]{5}-[0-9]{4}$/)) return "O telefone deve estar no formato (00) 00000-0000";

        return "";
    }

    //TODO: implementar validação de endereço
    this.validaEndereco = function(endereco) {
        return "";
    }
}

var usuariosController;
$(document).ready(function () {
    usuariosController = new UsuariosController();
    usuariosController.init();
});