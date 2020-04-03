package com.nmn.em.front.views.income;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.nmn.em.back.model.Income;
import com.nmn.em.back.repository.IncomeRepository;
import com.nmn.em.back.repository.IncomeSourceRepository;
import com.nmn.em.front.views.MainView;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.contextmenu.MenuItem;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.menubar.MenuBar;
import com.vaadin.flow.component.polymertemplate.Id;
import com.vaadin.flow.component.polymertemplate.PolymerTemplate;
import com.vaadin.flow.router.AfterNavigationEvent;
import com.vaadin.flow.router.AfterNavigationObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.templatemodel.TemplateModel;

@PageTitle("Uređivanje prihoda")
@JsModule("./src/views/income-edit-view.js")
@Tag("income-edit-view")
@Route(layout = MainView.class)
public class IncomeEditView extends PolymerTemplate<TemplateModel> implements AfterNavigationObserver {

	@Id
	private Grid<Income> grid;
	@Id
	private MenuBar gridMenuBar;
	@Id
	private Button buttonSave;
	@Id
	private Button buttonCancel;

	private Map<Income, IncomeGridEditItem> map = new HashMap<>();

	@Autowired
	private IncomeRepository incomeRepository;
	@Autowired
	private IncomeSourceRepository incomeSourceRepository;

	public IncomeEditView() {
		MenuItem menuItem = gridMenuBar.addItem(VaadinIcon.ELLIPSIS_DOTS_V.create());
		menuItem.getSubMenu().addItem("Dodaj novi prihod", e -> addIncome());

		grid.addComponentColumn(e -> getOrCreate(e).getComboBoxItemType()).setHeader("Tip");
		grid.addComponentColumn(e -> getOrCreate(e).getDatePickerDate()).setHeader("Datum");
		grid.addColumn(Income::getSourceGroup).setHeader("Grupa prihoda");
		grid.addComponentColumn(e -> getOrCreate(e).getComboBoxIncomeSource()).setHeader("Izvor prihoda");
		grid.addComponentColumn(e -> getOrCreate(e).getTextFieldValue()).setHeader("Iznos");
		grid.getColumns().forEach(column -> column.setAutoWidth(true));

		buttonSave.addClickListener(click -> saveAll());
		buttonCancel.addClickListener(click -> discardChanges());
	}

	private void addIncome() {
		System.out.println("ADD INCOME");
		// TODO Auto-generated method stub
	}

	private void saveAll() {
		for (Income income : map.keySet()) {
			if (map.get(income).isModified()) {
				incomeRepository.save(income);
			}
		}
		UI.getCurrent().navigate(IncomeView.class);
	}

	private void discardChanges() {
		UI.getCurrent().navigate(IncomeView.class);
	}

	private IncomeGridEditItem getOrCreate(Income income) {
		if (!map.containsKey(income)) {
			IncomeGridEditItem item = new IncomeGridEditItem(income, incomeSourceRepository.findAll());
			map.put(income, item);
		}
		return map.get(income);
	}

	@Override
	public void afterNavigation(AfterNavigationEvent event) {
		grid.setItems(incomeRepository.findAll().stream().sorted(Comparator.reverseOrder()));
		grid.recalculateColumnWidths();
	}

}
