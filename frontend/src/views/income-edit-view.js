import { PolymerElement, html } from "@polymer/polymer/polymer-element.js";

class IncomeEditView extends PolymerElement {
  static get template() {
    return html`
        <style include="bootstrap">
        </style>

        <div class="w-100 h-100 d-flex flex-column p-3">            
          <div class="card-header bg-white d-flex align-items-center">
            <h5 class="m-0">Izmjena prihoda</h5>
            <vaadin-menu-bar id="gridMenuBar" theme="tertiary-inline" class="ml-auto"></vaadin-menu-bar>
          </div>
          <div class="card-body d-flex flex-column flex-fill p-0"> 
            <vaadin-grid id="grid" theme="column-borders row-stripes"></vaadin-grid>
          </div>
          <div class="card-footer bg-white d-flex align-items-center">
            <vaadin-button id="buttonSave" class="ml-auto btn-success" theme="icon">
              <iron-icon icon="vaadin:check"></iron-icon>
            </vaadin-button>
            <vaadin-button id="buttonCancel" class="ml-2 btn-light" theme="icon">
              <iron-icon icon="vaadin:close"></iron-icon>
            </vaadin-button>
          </div>
        </div>`;
  }

  static get is() {
    return "income-edit-view";
  }

}

customElements.define(IncomeEditView.is, IncomeEditView);