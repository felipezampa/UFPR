function OrcamentoController() {
    this.reference = $(".js-orcamentos-table");
    const _this = this;

    this.init = function () {
        bindButtonActions();
    };

    var bindButtonActions = function () {
        _this.reference.find(".js-aprovar-orcamento").on("click", function (e) {
            e.preventDefault();

            const pedidoId = $(e.target).data("pedido-id");
            aprovarOrcamento(pedidoId);
        });

        _this.reference.find(".js-rejeitar-orcamento").on("click", function (e) {
            e.preventDefault();

            const pedidoId = $(e.target).data("pedido-id");
            rejeitarOrcamento(pedidoId);
        });
    };

    var aprovarOrcamento = function (pedidoId) {
        fetch('OrcamentoServlet?action=aprovar&pedidoId=' + pedidoId, {
            method: 'POST'
        }).then(() => {
            window.location.reload();
        });
    };

    var rejeitarOrcamento = function (pedidoId) {
        var retorno = confirm("Tem certeza que deseja excluir?")
        if (retorno == true) {
            fetch('OrcamentoServlet?action=rejeitar&pedidoId=' + pedidoId, {
                method: 'POST'
            }).then(() => {
                window.location.reload();
            });
        } else if (retorno == false){
            
        }
    };
}

var orcamentoController;
$(document).ready(function () {
    orcamentoController = new OrcamentoController();
    orcamentoController.init();
});