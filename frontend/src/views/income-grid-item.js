import { PolymerElement, html } from "@polymer/polymer/polymer-element.js";

class IncomeGridItem extends PolymerElement {

	static get template() {
		return html`
      <style include="bootstrap">
			</style>

      <div class="container-fluid">
			
        <div class="row" hidden$="[[!header]]">
					<hr class="col-12 mb-1">
					<div class="col-12 col-md-6 d-flex align-items-center">
          	<h5 class="mb-0 mr-3">[[header.month]]</h5>
					</div>
					<div class="col-12 col-md-6 d-flex align-items-center">
						<h6 class="ml-md-auto mb-0">
							(<span class="text-success mr-1">[[header.actualString]]</span>/<span class="ml-1 text-info">[[header.plannedString]]</span>)
							<span class$="[[_numberTextStyle(header.percentage)]]">[[header.percentageString]]</span>
						</h6>
					</div>
          <hr class="col-12 mt-1">
        </div>				

        <div class="row">
          <div class="col-12">
						<div class="row">
							<div class="col-6 col-md-6 col-lg-2">[[item.date]]</div>
							<div class="col-6 col-md-6 col-lg-2">[[item.value]]</div>
							<div class="col-6 col-md-6 col-lg-2"><span class$="badge badge-[[_isPlannedStyle(item.planned)]]">[[item.type]]</span></div>
							<div class="col-6 col-md-6 col-lg-6 text-truncate">[[item.description]]</div>
							<hr class="col-12 mt-1 mb-0 d-block d-lg-none">
						</div>
					</div>
        </div>
				
      </div>`;
	}

	static get is() {
		return "income-grid-item";
	}

	_isPlannedStyle(isPlanned) {
		if (isPlanned) {
			return 'info';
		} else {
			return 'success';
		}
	}

	_numberTextStyle(value) {
		if (value < 0) {
			return 'text-danger';
		} else {
			return 'text-success';
		}
	}

}
window.customElements.define(IncomeGridItem.is, IncomeGridItem);
