import { PolymerElement, html } from '@polymer/polymer/polymer-element.js';


class NavMenuItem extends PolymerElement {

  static get template() {
    return html`
    <style>
      .side-menu-item {
        display: flex;
        align-items: center;
        padding: 0.6em;
        padding-left: 1em;
        cursor: pointer;
      }

      .side-menu-subitem {
        padding: 0.4em;
        padding-left: 2em;
        cursor: pointer;
        font-size: 0.9em;
      }

      .side-menu-item>iron-icon {
        width: 1.6em;
        padding-right: 0.6em;
      }

      .side-menu-item:hover {
        background-color: #2D3646;
      }

      .side-menu-item-active {
        background-color: #2D3646;
      }

      .expand {
        display: block;
        transition: width 2s;
        transition-timing-function: linear;
      }

      .collapse {
        display: none;
        transition: width 2s;
        transition-timing-function: linear;
      }

      .toggle-icon-collapse {
        width: 1em;
        height: 1em;
        padding: 0 !important;
        padding-top: 0.375em !important;
        transition: transform 0.25s;
      }

      .toggle-icon-expand {
        padding-top: 0 !important;
        transform: rotate(180deg);
      }

      :host{
        --vaadin-app-layout-drawer-overlay: false;
      }

      @media (max-width: 800px){
        :host {
          --vaadin-app-layout-drawer-overlay: true;
        }
      }
    </style>

    <div id="sideMenuItem" class="side-menu-item" data-toggle="collapse" data-target-id="divCollapse"
      on-click="_clicked">
      <iron-icon id="icon" icon="[[icon]]"></iron-icon>
      [[label]]
      <iron-icon id="iconToggle" class="toggle-icon-collapse" icon="vaadin:angle-down"></iron-icon>
    </div>
    <div class="collapse" id="divCollapse">

    </div>`;
  }

  static get is() {
    return 'nav-menu-item';
  }
  _isShadyCSS() {
    return window.ShadyCSS && !window.ShadyCSS.nativeCss;
  }
  _getCustomPropertyValue(customProperty) {
    let customPropertyValue;
    if (this._isShadyCSS()) {
      window.ShadyCSS.styleSubtree(this);
      customPropertyValue = window.ShadyCSS.getComputedStyleValue(this, customProperty);
    } else {
      customPropertyValue = getComputedStyle(this).getPropertyValue(customProperty);
    }
    return (customPropertyValue || '').trim().toLowerCase();
  }
  _clicked() {
    const target = this.shadowRoot.getElementById('divCollapse');
    const icon = this.shadowRoot.getElementById('iconToggle');
    const overlay = this._getCustomPropertyValue('--vaadin-app-layout-drawer-overlay') == 'true';
    if (target === null) return;
    if (target.classList.contains("collapse")) {
      target.classList.remove("collapse");
      target.classList.add("expand");
      icon.classList.add("toggle-icon-expand");
    } else {
      target.classList.remove("expand");
      target.classList.add("collapse");
      icon.classList.remove("toggle-icon-expand");
    }
    if (overlay) {
      window.dispatchEvent(new CustomEvent('close-overlay-drawer'));
    }
  }
}
window.customElements.define(NavMenuItem.is, NavMenuItem);
