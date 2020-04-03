package com.nmn.em.front.components;

import com.nmn.em.front.translation.I18nForDatePicker;
import com.vaadin.flow.component.datepicker.DatePicker;

public class DatePickerT extends DatePicker {

	public DatePickerT() {
		super();
		setI18n(I18nForDatePicker.get());
	}
}
