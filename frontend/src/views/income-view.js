import { PolymerElement, html } from "@polymer/polymer/polymer-element.js";
import "./income-grid-item";
import "../../chartjs/Chart.min.js";
import "@vaadin/vaadin-menu-bar";

class IncomeView extends PolymerElement {
  static get template() {
    return html`
        <style include="bootstrap">
          vaadin-date-picker{
            width: 8em;
          }
        </style>

        <div class="container-fluid mt-3">

          <div class="row mb-3">
            <div class="col">
              <div class="card">              
                <div class="card-header bg-white">
                  <h5 class="m-0">Period posmatranja</h5>
                </div>
                <div class="card-body">
                  <vaadin-date-picker id="datePickerFrom" class="ml-2 mr-2"></vaadin-date-picker>
                  <vaadin-date-picker id="datePickerTo" class="ml-2"></vaadin-date-picker>
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
            <div class="col mb-2">
              <div class="card">
                <div class="card-header bg-white d-flex align-items-center">
                  <h5 class="m-0">Analiticki pregled</h5>
                  <vaadin-menu-bar id="gridMenuBar" theme="tertiary-inline" class="ml-auto"></vaadin-menu-bar>
                </div>
                <div class="card-body">
                  <vaadin-grid id="grid" height-by-rows theme="no-border no-row-borders"></vaadin-grid>
                </div>
              </div>
            </div>
          </div>

        </div>`;
  }

  static get is() {
    return "income-view";
  }

  ready() {
    super.ready();
    this._drawCompareToPlanChart();
  }

  _drawCompareToPlanChart() {
    var ctx = this.shadowRoot.getElementById('compareToPlanCanvas').getContext('2d');
    new Chart(ctx, {
      type: 'line',
      data: {
        labels: this.monthLabels,
        datasets:
          [{
            label: 'Planirano',
            data: this.plannedValues,
            backgroundColor: '#4BC0C0',
            borderColor: '#4BC0C0',
            borderWidth: 3,
            fill: false
          },
          {
            label: 'Ostvareno',
            data: this.actualValues,
            backgroundColor: '#28a745',
            borderColor: '#28a745',
            borderWidth: 3,
            fill: false
          }]
      },
      options: {
        responsive: true,
        maintainAspectRatio: false,
        tooltips: {
          mode: 'index',
          intersect: true
        },
        scales: {
          yAxes: [{
            ticks: {
              beginAtZero: false,
              callback: function (label, index, labels) {
                return label + ' KM';
              }
            }
          }],
          xAxes: [{
            barPercentage: 0.4
          }]
        }
      }
    });
  }
}

customElements.define(IncomeView.is, IncomeView);