import { PolymerElement, html } from '@polymer/polymer/polymer-element.js';

class CustomNotification extends PolymerElement {

  static get template() {
    return html`
    <style include="bootstrap">
      #divTitle {
        font-size: 1.2em;
        font-weight: bold;
      }

      .iconInfo {
        color: blue;
        opacity: 0.5;
      }

      .iconError {
        color: red;
        opacity: 0.5;
      }

      .iconWarning {
        color: orange;
        opacity: 0.6;
      }

      #divTime {
        font-size: 0.7em;
      }

      #divDetails {
        font-size: 0.8em;
        border-radius: 7x;
        border: 1px solid gray;
        padding: 10px;
        max-height: 100px;
        margin-top: 15px;
      }

      #buttonDetails {
        position: absolute;
        bottom: 5px;
        left: 5px;
        height: 22px;
      }

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

      .container-fluid {
        min-width: 400px;
      }

      #icon {
        margin: auto;
      }
    </style>


    <div id="root" class$="container-fluid m-0 alert [[_getBackgroundStyle(notificationType)]]">

      <div class="row">
        <div id="divTitle" class="col-12"></div>
      </div>

      <div class="row">
        <div id="iconContent" class="col-2">
          <iron-icon id="icon" icon="[[_getIcon(notificationType)]]" class="mt-4"></iron-icon>
        </div>
        <div class="col-10">
          <div class="row mt-3 mb-2">
            <div id="divContent" class="col-12"></div>
          </div>
          <div class="row">
            <div id="divTime" class="col-12"></div>
          </div>
          <div class="row">
            <div class="col-8">
              <vaadin-button id="buttonDetails" theme="icon" value="DA" on-click="_divDetailsClicked">
                <iron-icon id="iconDetails" icon="vaadin:angle-down"></iron-icon>
              </vaadin-button>
            </div>
            <div class="col-4">
              <vaadin-button id="buttonOK" class="btn btn-sm"></vaadin-button>
            </div>
          </div>
        </div>
      </div>

      <div id="divDetails" class="row pre-scrollable" value="DA">
        <div class="col-12">{{detail}}
        </div>
      </div>
    </div>`;
  }

  static get is() {
    return 'custom-notification'
  }
  _divDetailsClicked() {

  }
  _getBackgroundStyle(type) {
    switch (type) {
      case 'Error': return 'alert-danger';
      case 'Info': return 'alert-info';
      case 'Warning': return 'alert-warning';
    }
  }
  _getIcon(type) {
    switch (type) {
      case 'Error': return 'vaadin:close-circle';
      case 'Info': return 'vaadin:info-circle';
      case 'Warning': return 'vaadin:warning';
    }
  }
  ready() {
    super.ready();
    var container = this.parentNode.parentNode.parentNode
    console.log(container);
    if (container.tagName === 'VAADIN-NOTIFICATION-CARD') {
      var content = container.shadowRoot.childNodes[2].childNodes[1];
      console.log(content);
      content.style.padding = '0px';
    }
    if (container.tagName === 'VAADIN-DIALOG-OVERLAY') {
      var content = container.shadowRoot.childNodes[4].childNodes[1];
      console.log(content);
      content.style.padding = '0px';
    }
  }
}

customElements.define(CustomNotification.is, CustomNotification);

