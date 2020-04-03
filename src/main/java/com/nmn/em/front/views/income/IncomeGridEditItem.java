package com.nmn.em.front.views.income;

import java.util.List;

import com.nmn.em.back.model.Income;
import com.nmn.em.back.model.IncomeSource;
import com.nmn.em.back.model.ItemType;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.converter.StringToBigDecimalConverter;

public class IncomeGridEditItem {

	private ComboBox<ItemType> comboBoxItemType = new ComboBox<>();
	private DatePicker datePickerDate = new DatePicker();
	private ComboBox<IncomeSource> comboBoxIncomeSource = new ComboBox<>();
	private TextField textFieldValue = new TextField();

	private Binder<Income> binder = new Binder<>();
	private boolean modified;

	public IncomeGridEditItem(Income income, List<IncomeSource> incomeSourceList) {
		comboBoxItemType.setItems(ItemType.values());
		comboBoxItemType.setItemLabelGenerator(e -> e.getName());
		comboBoxIncomeSource.setItems(incomeSourceList);

		comboBoxItemType.setWidth("100%");
		datePickerDate.setWidth("100%");
		comboBoxIncomeSource.setWidth("100%");
		textFieldValue.setWidth("100%");

		binder.forField(comboBoxItemType).bind(Income::getType, Income::setType);
		binder.forField(datePickerDate).bind(Income::getDate, Income::setDate);
		binder.forField(comboBoxIncomeSource).bind(Income::getSource, Income::setSource);
		binder.forField(textFieldValue).withConverter(new StringToBigDecimalConverter("Mora biti broj.")).bind(Income::getValue, Income::setValue);

		binder.setBean(income);
		binder.addValueChangeListener(e -> modified = true);
	}

	public ComboBox<ItemType> getComboBoxItemType() {
		return comboBoxItemType;
	}

	public DatePicker getDatePickerDate() {
		return datePickerDate;
	}

	public ComboBox<IncomeSource> getComboBoxIncomeSource() {
		return comboBoxIncomeSource;
	}

	public TextField getTextFieldValue() {
		return textFieldValue;
	}

	public boolean isModified() {
		return modified;
	}

}
