function NovoPedidoController() {
    this.adicionarRoupaContainerReference = $(".js-adicionar-roupa-container");
    this.adicionarCarrinhoButtonReference = $(".js-adicionar-carrinho");
    this.carrinhoTableBodyReference = $(".js-carrinho-table-body");
    this.excluirItemButtonReference = $(".js-excluir-item");
    this.enviarPedidoButtonReference = $(".js-enviar-pedido");

    const _this = this;

    this.init = function () {
        bindButtonActions();
    };

    var bindButtonActions = function () {
        _this.adicionarCarrinhoButtonReference.on("click", function (e) {
            e.preventDefault();

            const pedidoId = _this.adicionarRoupaContainerReference.find(".js-pedido-id").val();
            const roupaId = _this.adicionarRoupaContainerReference.find(".js-select-roupa").val();
            const quantidade = _this.adicionarRoupaContainerReference.find(".js-quantidade").val();

            if (!quantidade) {
                alert("Informe a quantidade de roupas");
                return;
            }
            adicionarCarrinho(pedidoId, roupaId, quantidade);
        });

        _this.excluirItemButtonReference.on("click", function (e) {
            e.preventDefault();

            const itemId = $(e.target).data("itemId");
            const roupa = $(e.target).data("roupa");
            removerItemModalController.show(itemId, roupa)
        });

        _this.enviarPedidoButtonReference.on("click", function (e) {
            e.preventDefault();

            const numItems = _this.carrinhoTableBodyReference.children().length;
            if (!numItems) {
                alert("Adicione pelo menos uma roupa ao carrinho");
                return;
            }

            const pedidoId = _this.adicionarRoupaContainerReference.find(".js-pedido-id").val();
            enviarPedido(pedidoId);
        });
    };

    var adicionarCarrinho = function (pedidoId, roupaId, quantidade) {
        const url = "PedidoServlet?action=adicionarItem&pedidoId=" + pedidoId + "&roupaId=" + roupaId + "&quantidade=" + quantidade;
        fetch(url, {
            method: "POST"
        }).then(() => {
            window.location.reload();
        });
    };

    this.excluirItem = function (itemId) {
        fetch("PedidoServlet?action=removerItem&itemId=" + itemId, {
            method: "POST"
        }).then(() => {
            window.location.reload();
        });
    };

    var enviarPedido = function (pedidoId) {
        fetch("PedidoServlet?action=solicitarOrcamento&pedidoId=" + pedidoId, {
            method: "POST"
        }).then(() => {
            location.href = "ClienteServlet?action=orcamentos";
        });
    };
}


var novoPedidoController;
$(document).ready(function () {
    novoPedidoController = new NovoPedidoController();
    novoPedidoController.init();
});
