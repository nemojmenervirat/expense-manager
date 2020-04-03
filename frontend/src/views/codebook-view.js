import { PolymerElement, html } from "@polymer/polymer/polymer-element.js";

class CodebookView extends PolymerElement {

  static get template() {
    return html`
      <style include="bootstrap">
      </style>

      <div class="container-fluid h-100 d-flex flex-column">
        <div class="row mt-3 mb-3 flex-fill">
          <div class="col">
            <div class="card h-100">
              <div class="card-header bg-white d-flex">
                <h5 class="m-0">[[titleText]]</h5>
                <vaadin-menu-bar id="gridMenuBar" theme="tertiary-inline" class="ml-auto"></vaadin-menu-bar>
              </div>
              <div class="card-body">
                <vaadin-grid id="grid" theme="column-borders no-border"></vaadin-grid>
              </div>
              <div class="card-footer">
                <div>[[statusText]]</div>
              </div>
            </div>
          </div>
        </div>        
      </div>
    `;
  }

  static get is() {
    return 'codebook-view';
  }

}

window.customElements.define(CodebookView.is, CodebookView);
