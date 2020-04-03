import { PolymerElement, html } from "@polymer/polymer/polymer-element.js";
import "@vaadin/vaadin-button/vaadin-button.js";
import "@vaadin/vaadin-text-field/vaadin-text-field.js";
import "../../chartjs/Chart.min.js";

class HomeView extends PolymerElement {
  static get template() {
    return html`
        <style include="bootstrap">
          .card-icon {
            width: 2.225em;
            height: 2.225em;
          }
        </style>

        <div id="container" class="container-fluid mt-3">


          <div class="row mb-1">

            <div class="col-sm-12 col-md mb-2">
              <div class="card bg-white">
                <div class="card-body">
                  <div class="media">
                    <div class="d-inline-block mt-2 mr-4">
                      <iron-icon class="text-success card-icon" icon="vaadin:wallet"></iron-icon>
                    </div>
                    <div class="media-body">
                      <div class="d-flex mb-2 align-items-center">
                        <h3 class="m-0">5000 KM</h3>
                        <div class="text-success ml-2">(3% povecanje)</div>
                      </div>
                      <div class="mb-0">Prihodi u tekucem mjesecu</div>
                    </div>
                  </div>
                </div>
              </div>
            </div>

            <div class="col-sm-12 col-md mb-2">
              <div class="card bg-white">
                <div class="card-body">
                  <div class="media">
                    <div class="d-inline-block mt-2 mr-4">
                      <iron-icon class="text-danger card-icon" icon="vaadin:cash"></iron-icon>
                    </div>
                    <div class="media-body">
                      <div class="d-flex mb-2 align-items-center">
                        <h3 class="m-0">3000 KM</h3>
                        <div class="text-success ml-2">(5% smanjenje)</div>
                      </div>
                      <div class="mb-0">Rashodi u tekucem mjesecu</div>
                    </div>
                  </div>
                </div>
              </div>
            </div>

          </div>


          <div class="row mb-1">
          
            <div class="col mb-2">
              <div class="card">
                <div class="card-header bg-white">
                  <h5 class="m-0">Poredjenje sa planom</h5>
                </div>
                <div class="card-body">
                  <canvas id="compareToPlanCanvas"></canvas>
                </div>
              </div>
            </div>

          </div>

          <div class="row mb-2">

            <div class="col-sm-12 col-md-8 mb-2">
              <div class="card h-100">
                <div class="card-header bg-white">
                  <h5 class="m-0">Rashodi u odnosu na prosli mjesec</h5>
                </div>
                <div class="card-body">
                  <canvas id="compareToLastMonthCanvas"></canvas>
                </div>
              </div>
            </div>

            <div class="col-sm-12 col-md-4 mb-2">
              <div class="card">
                <div class="card-header bg-white">
                  <h5 class="m-0">Struktura rashoda</h5>
                </div>
                <div class="card-body">
                  <canvas id="structureCanvas"></canvas>
                </div>
              </div>
            </div>

          </div>

        </div>`;
  }


  static get is() {
    return "home-view";
  }

  _resizedEventHandler(event) {
    for (var id in Chart.instances) {
      Chart.instances[id].resize()
    }

  }

  _addEvent(object, type, callback) {
    if (object == null || typeof (object) == 'undefined') return;
    if (object.addEventListener) {
      object.addEventListener(type, callback, false);
    } else if (object.attachEvent) {
      object.attachEvent("on" + type, callback);
    } else {
      object["on" + type] = callback;
    }
  }

  ready() {
    super.ready();
    this._drawCompareToPlanChart();
    this._drawStructureChart();
    this._drawCompareToLastMonthChart();
    this._addEvent(window, "load", this._resizedEventHandler);
    this._addEvent(window, "resize", this._resizedEventHandler);
  }

  _drawCompareToPlanChart() {
    var ctx = this.shadowRoot.getElementById('compareToPlanCanvas').getContext('2d');
    new Chart(ctx, {
      type: 'horizontalBar',
      data: {
        labels: ['Planirani prihodi', 'Dosadasnji prihodi', 'Planirani rashodi', 'Dosadasnji rashodi'],
        datasets: [{
          label: 'Iznos',
          data: [4900, 5000, 4000, 3000],
          backgroundColor: ['#c6ebeb', '#c1f0cc', '#c6ebeb', '#f4bec3'],
          borderColor: ['#4BC0C0', '#28a745', '#4BC0C0', '#dc3545'],
          borderWidth: 1
        }]

      },
      options: {
        responsive: true,
        maintainAspectRatio: false,
        legend: {
          display: false
        },

        scales: {
          xAxes: [{

            ticks: {

              beginAtZero: true
            }

          }]

        }
      }
    });
  }

  _drawStructureChart() {
    var ctx = this.shadowRoot.getElementById('structureCanvas').getContext('2d');
    new Chart(ctx, {
      type: 'pie',
      data: {
        labels: ['Racuni', 'Stanarina', 'Kredit', 'Ostalo'],
        datasets: [{
          data: ['600', '400', '800', '1200'],
          backgroundColor: ['#FF6384', '#FFC233', '#4BC0C0'],
          label: 'Broj sms-ova'
        }]

      },
      options: {
        responsive: true
      }
    });
  }

  _drawCompareToLastMonthChart() {
    var ctx = this.shadowRoot.getElementById('compareToLastMonthCanvas').getContext('2d');
    new Chart(ctx, {
      type: 'line',
      data: {
        labels: [1, 5, 10, 15, 20, 25, 30],
        datasets:
          [{

            label: 'Prethodni mjesec',
            data: [300, 400, 500, 500, 800, 2000],
            backgroundColor: '#4BC0C0',
            borderColor: '#4BC0C0',
            borderWidth: 3,
            fill: false
          },
          {
            label: 'Tekuci mjesec',
            data: [800, 800, 900, 950],
            backgroundColor: '#FF6384',
            borderColor: '#FF6384',
            borderWidth: 3,
            fill: false
          }]

      },
      options: {
        responsive: true,
        maintainAspectRatio: false
      }
    });
  }

}

customElements.define(HomeView.is, HomeView);
