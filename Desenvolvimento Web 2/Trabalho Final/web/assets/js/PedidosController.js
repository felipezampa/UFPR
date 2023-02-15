function PedidosController() {
  this.reference = $(".js-pedidos-table");
  this.radioReference = $(".radio-form");
  this.filtroSituacaoReference = $(".js-filtro-situacao");

  const _this = this;

  this.init = function () {
    bindButtonActions();
    bindButtonActionsRadio();
  };

  var bindButtonActions = function () {
    _this.reference.find(".js-recolher-button").on("click", function (e) {
      e.preventDefault();

      const pedidoId = $(e.target).data("pedido-id");
      recolherPedido(pedidoId);
    });

    _this.reference.find(".js-cancelar-button").on("click", function (e) {
      e.preventDefault();

      const pedidoId = $(e.target).data("pedido-id");
      cancelarPedido(pedidoId);
    });

    _this.reference
      .find(".js-confirmar-lavagem-button")
      .on("click", function (e) {
        e.preventDefault();

        const pedidoId = $(e.target).data("pedido-id");
        confirmarLavagem(pedidoId);
      });

    _this.reference.find(".js-finalizar-button").on("click", function (e) {
      e.preventDefault();

      const pedidoId = $(e.target).data("pedido-id");
      finalizarPedido(pedidoId);
    });

    _this.reference.find(".js-pagar-button").on("click", function (e) {
        e.preventDefault();

        const pedidoId = $(e.target).data("pedido-id");
        pagarPedido(pedidoId);
    });

    if (_this.filtroSituacaoReference.length > 0) {
      _this.filtroSituacaoReference.on("change", function (e) {
        const situacao = $(e.target).val();
        window.location.href =
          "ClienteServlet?action=listaPedidos&situacaoFiltro=" + situacao;
      });
    }
  };

  var recolherPedido = function (pedidoId) {
    fetch("PedidoServlet?action=recolher&pedidoId=" + pedidoId, {
      method: "POST",
    }).then(() => {
      window.location.reload();
    });
  };

  var cancelarPedido = function (pedidoId) {
    fetch("PedidoServlet?action=cancelar&pedidoId=" + pedidoId, {
      method: "POST",
    }).then(() => {
      window.location.reload();
    });
  };

  var confirmarLavagem = function (pedidoId) {
    fetch("PedidoServlet?action=confirmarLavagem&pedidoId=" + pedidoId, {
      method: "POST",
    }).then(() => {
      window.location.reload();
    });
  };

  var finalizarPedido = function (pedidoId) {
    fetch("PedidoServlet?action=finalizar&pedidoId=" + pedidoId, {
      method: "POST",
    }).then(() => {
      window.location.reload();
    });
  };

  var bindButtonActionsRadio = function () {
    buscaDiaHoje();
  };
  var buscaDiaHoje = function () {
    _this.radioReference.find(".input-dias").on("click", function (e) {
      document.querySelector(".div-datas").style.display = "block";
    });
    _this.radioReference.find(".input-hoje").on("click", function (e) {
      document.querySelector(".div-datas").style.display = "none";
    });
    _this.radioReference.find(".input-todos").on("click", function (e) {
      document.querySelector(".div-datas").style.display = "none";
    });
  };

  var pagarPedido = function (pedidoId) {
        fetch('PedidoServlet?action=pagar&pedidoId=' + pedidoId, {
            method: 'POST'
        }).then(() => {
            window.location.reload();
        });
    }
}

var pedidosController;
$(document).ready(function () {
  pedidosController = new PedidosController();
  pedidosController.init();
});
