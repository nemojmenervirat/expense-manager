import { PolymerElement, html } from '@polymer/polymer/polymer-element.js';

class CustomConfirmDialog extends PolymerElement {

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

      #divTitle {
        font-size: 1.2em;
        font-weight: bold;
      }
    </style>

    <div class="container-fluid">

      <div class="row">
        <div id="divTitle" class="col-12">{{caption}}</div>
      </div>

      <div class="row mt-3 mb-5">
        <div class="col-12">
          <div id="divContent">{{content}}</div>
        </div>
      </div>

      <div class="row">
        <div class="col-12">
          <div class="float-right">
            <vaadin-button id="buttonOK" on-click="close">
              <iron-icon icon="vaadin:check"></iron-icon>
              {{okText}}
            </vaadin-button>
            <vaadin-button id="buttonCancel" class="ml-3" on-click="close">
              <iron-icon icon="vaadin:close"></iron-icon>
              {{cancelText}}
            </vaadin-button>
          </div>
        </div>
      </div>

    </div>`;
  }

  static get is() {
    return 'custom-confirm-dialog';
  }
}

customElements.define(CustomConfirmDialog.is, CustomConfirmDialog);

