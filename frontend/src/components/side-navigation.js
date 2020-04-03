import { PolymerElement, html } from '@polymer/polymer/polymer-element.js';


class SideNavigation extends PolymerElement {

  static get template() {
    return html`
    <style>
      div.side-navigation::-webkit-scrollbar {
        width: 8px;
      }

      div.side-navigation::-webkit-scrollbar-track {
        background: #354052;
      }

      div.side-navigation::-webkit-scrollbar-thumb {
        background: #888;
      }

      div.side-navigation::-webkit-scrollbar-thumb:hover {
        background: #888;
      }

      .side-navigation-wrapper {
        padding-top: 1.5em;
        padding-bottom: 1.5em;
        display: flex;
        flex-direction: column;
      }

      .side-navigation {
        overflow-y: hidden;
        flex-grow: 1;
      }

      .side-navigation:hover {
        overflow-y: auto;
      }

      .top-part {
        display: flex;
        align-items: center;
        padding-bottom: 1.5em;
        padding-left: 0.5em;
        font-size: 1.5em;
        display: none;
      }

      .logo-icon {
        color: #47bac1;
        width: 2em;
        height: 2em;
      }

      .app-name {
        margin-left: 0.5em;
        color: white;
      }

    </style>

        <div class="side-navigation-wrapper">
          <a router-link href="" class="top-part">
            <iron-icon class="logo-icon" icon="vaadin:home-o"></iron-icon>
            <span class="app-name">Upravljanje troskovima</span>
          </a>
          <div class="side-navigation" id="sideNavigation"></div>
        </div>`;
  }

  static get is() {
    return 'side-navigation';
  }
}

window.customElements.define(SideNavigation.is, SideNavigation);
