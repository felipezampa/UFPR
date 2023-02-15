function RemoverItemModalController() {
    this.templateContainer = $(".js-remover-item-modal-container");
    this.reference;
    const _this = this;

    this.init = function() {
        renderTemplate();
        bindCancelButton();
        bindSubmitButton();
    }

    var renderTemplate = function() {
        _this.templateContainer.html(`
            <div class="modal fade js-remover-item-modal" tabindex="-1">
              <input type="hidden" class="js-item-id" />
              <div class="modal-dialog">
                <div class="modal-content">
                  <div class="modal-header">
                    <h5 class="modal-title">Remover Item do Pedido</h5>
                    <button type="button" class="btn-close js-cancel-button" data-bs-dismiss="modal" aria-label="Cancelar"></button>
                  </div>
                  <div class="modal-body">
                    <p>Você tem certeza que deseja remover o item <span class="js-item-nome"></span>?</p>
                    <p>Esta ação não poderá ser desfeita.</p>
                  </div>
                  <div class="modal-footer">
                    <button type="button" class="btn btn-secondary js-cancel-button" data-bs-dismiss="modal">Cancelar</button>
                    <button type="button" class="btn btn-primary js-confirm-button">Confirmar</button>
                  </div>
                </div>
              </div>
            </div>
        `);

        _this.reference = _this.templateContainer.find(".js-remover-item-modal");
    }

    this.show = function(itemId, roupa) {
        fillModalData(itemId, roupa);
        _this.reference.modal("show");
    }

    this.hide = function() {
        _this.reference.modal("hide");
    }

    var fillModalData = function(itemId, roupa) {
        _this.reference.find(".js-item-id").data("itemId", itemId);
        _this.reference.find(".js-item-nome").text(roupa);
    }

    var bindCancelButton = function() {
        _this.reference.find(".js-cancel-button").on("click", function() {
            _this.hide();
        });
    }

    var bindSubmitButton = function() {
        _this.reference.find(".js-confirm-button").on("click", function() {
            const itemId = _this.reference.find(".js-item-id").data("itemId");
            novoPedidoController.excluirItem(itemId);

            _this.hide();
        });
    }
}

var removerItemModalController;
$(document).ready(function () {
    removerItemModalController = new RemoverItemModalController();
    removerItemModalController.init();
});
