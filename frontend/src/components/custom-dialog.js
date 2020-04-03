import { PolymerElement, html } from "@polymer/polymer/polymer-element.js";

class CustomDialog extends PolymerElement {

  static get template() {
    return html`
    <style include="bootstrap">
      #buttonOK {
        background-color: #377AA5;
        color: white;
      }

      #buttonOK:hover {
        background-color: #306f96;
      }

      #buttonOK:active {
        background-color: #255a7a;
      }

      #buttonCancel {
        color: #377AA5;
      }

      .container-fluid {
        min-width: 600px;
      }
    </style>

    <div class="container-fluid">

      <div class="row">
        <h4 class="col-12">[[titleText]]</h4>
      </div>

      <div class="row mt-3 mb-5">
        <div class="col-12">
          <div id="divContent"></div>
        </div>
      </div>

      <div class="row">
        <div class="col-12">
          <div class="d-flex align-items-right justify-content-end">
            <vaadin-button id="buttonDelete" class="btn btn-danger mr-auto" on-click="_deleteClicked">
              <iron-icon icon="vaadin:trash" slot="prefix"></iron-icon>
              [[deleteText]]
            </vaadin-button>
            <vaadin-button id="buttonOK" on-click="_okClicked">
              <iron-icon icon="vaadin:check" slot="prefix"></iron-icon>
              [[okText]]
            </vaadin-button>
            <vaadin-button id="buttonCancel" class="ml-3" on-click="_cancelClicked">
              <iron-icon icon="vaadin:close" slot="prefix"></iron-icon>
              [[cancelText]]
            </vaadin-button>
          </div>
        </div>
      </div>
    `;
  }

  static get is() {
    return 'custom-dialog';
  }


}

customElements.define(CustomDialog.is, CustomDialog);