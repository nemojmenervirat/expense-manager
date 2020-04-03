package com.nmn.em.front.views.income;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;

import com.nmn.em.back.model.Income;
import com.nmn.em.back.model.ItemType;
import com.nmn.em.back.repository.IncomeRepository;
import com.nmn.em.front.FrontUtils;
import com.nmn.em.front.components.DatePickerT;
import com.nmn.em.front.views.MainView;
import com.nmn.em.front.views.income.IncomeView.IncomeViewModel;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.contextmenu.MenuItem;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.Grid.SelectionMode;
import com.vaadin.flow.component.html.Hr;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.menubar.MenuBar;
import com.vaadin.flow.component.polymertemplate.Id;
import com.vaadin.flow.component.polymertemplate.PolymerTemplate;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.templatemodel.TemplateModel;

@PageTitle("Prihodi")
@Route(value = "income", layout = MainView.class)
@JsModule("./src/views/income-view.js")
@Tag("income-view")
public class IncomeView extends PolymerTemplate<IncomeViewModel> {

	public interface IncomeViewModel extends TemplateModel {
		public void setMonthLabels(List<String> monthLabels);

		public void setPlannedValues(List<Double> plannedValues);

		public void setActualValues(List<Double> actualValues);
	}

	@Id
	private DatePickerT datePickerFrom;
	@Id
	private DatePickerT datePickerTo;
	@Id
	private Grid<Income> grid;
	@Id
	private MenuBar gridMenuBar;

	@Autowired
	private IncomeRepository incomeRepository;

	@PostConstruct
	void init() {
		MenuItem menuItem = gridMenuBar.addItem(VaadinIcon.ELLIPSIS_DOTS_V.create());
		menuItem.getSubMenu().addItem("Dodaj novi prihod", e -> addIncome());
		menuItem.getSubMenu().add(new Hr());
		menuItem.getSubMenu().addItem("Omogući izmjene", e -> enableEdit());

		List<Income> incomeList = incomeRepository.findAllByOrderByDateDescIncomeIdDesc();
		LocalDate dateTo = incomeList.get(0).getDate();
		LocalDate dateFrom = incomeList.get(incomeList.size() - 1).getDate();
		datePickerFrom.setValue(dateFrom);
		datePickerTo.setValue(dateTo);

		List<Double> plannedValues = new LinkedList<>();
		List<Double> actualValues = new LinkedList<>();
		List<String> monthLabels = new LinkedList<>();
		Map<YearMonth, List<Income>> incomePerMonthMap = incomeList.stream().collect(Collectors.groupingBy(Income::getYearMonth));
		for (YearMonth yearMonth : incomePerMonthMap.keySet().stream().sorted().collect(Collectors.toSet())) {
			monthLabels.add(FrontUtils.getMonth(yearMonth));
			plannedValues.add(incomePerMonthMap.get(yearMonth).stream().filter(e -> e.getType() == ItemType.PLANNED).map(e -> e.getValue())
					.reduce(BigDecimal.ZERO, BigDecimal::add).doubleValue());
			actualValues.add(incomePerMonthMap.get(yearMonth).stream().filter(e -> e.getType() == ItemType.ACTUAL).map(e -> e.getValue())
					.reduce(BigDecimal.ZERO, BigDecimal::add).doubleValue());
		}
		getModel().setMonthLabels(monthLabels);
		getModel().setPlannedValues(plannedValues);
		getModel().setActualValues(actualValues);

		IncomeGridItemHeaderGenerator hearingGenerator = new IncomeGridItemHeaderGenerator(incomePerMonthMap);

		grid.setItems(incomeList);
		grid.setSelectionMode(SelectionMode.NONE);
		grid.addColumn(IncomeGridItem.getTemplate().withProperty("incomeGridItem", IncomeGridItem::create)
				.withProperty("header", hearing -> hearingGenerator.get(hearing.getIncomeId())));

	}

	private void enableEdit() {
		UI.getCurrent().navigate(IncomeEditView.class);
	}

	private void addIncome() {
		System.out.println("ADD INCOME");
		// TODO Auto-generated method stub
	}

}
