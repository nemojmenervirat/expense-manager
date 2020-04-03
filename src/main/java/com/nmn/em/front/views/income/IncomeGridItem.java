package com.nmn.em.front.views.income;

import java.time.format.DateTimeFormatter;

import com.nmn.em.back.model.Income;
import com.nmn.em.back.model.ItemType;
import com.nmn.em.front.FrontUtils;
import com.vaadin.flow.data.renderer.TemplateRenderer;

public class IncomeGridItem {

	private final Income income;

	public static TemplateRenderer<Income> getTemplate() {
		//@formatter:off
		return TemplateRenderer.of("<income-grid-item" + 
										"  header='[[item.header]]'" + 
										"  on-card-click='cardClick'"+
										"  item='[[item.incomeGridItem]]'>" + 
									"</income-grid-item>");
		//@formatter:on
	}

	public static IncomeGridItem create(Income income) {
		return new IncomeGridItem(income);
	}

	private IncomeGridItem(Income income) {
		this.income = income;
	}

	public String getDescription() {
		return income.getSource().getName();
	}

	public String getDate() {
		return income.getDate().format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
	}

	public String getValue() {
		return FrontUtils.formatCurrency(income.getValue());
	}

	public boolean isPlanned() {
		return income.getType() == ItemType.PLANNED;
	}

	public String getType() {
		return income.getType().getName();
	}

}
