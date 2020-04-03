package com.nmn.em.front.translation;

import java.util.Arrays;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.datepicker.DatePicker.DatePickerI18n;

public class I18nForDatePicker {

	private DatePickerI18n i18n;

	public I18nForDatePicker() {
		i18n = new DatePickerI18n();
		i18n.setWeek("Sedmica").setCalendar("Kalendar").setClear("Ukloni").setToday("Danas").setCancel("Odustani").setFirstDayOfWeek(1)
				.setMonthNames(Arrays.asList("januar", "februar", "mart", "april", "maj", "jun", "jul", "avgust", "septembar", "oktobar", "novembar",
						"decembar"))
				.setWeekdays(Arrays.asList("ponedeljak", "utorak", "srijeda", "četvrtak", "petak", "subota", "nedjelja"))
				.setWeekdaysShort(Arrays.asList("pon", "uto", "sri", "čet", "pet", "sub", "ned"));
	}

	public static DatePickerI18n get() {
		if (UI.getCurrent().getSession().getAttribute(I18nForDatePicker.class) == null) {
			UI.getCurrent().getSession().setAttribute(I18nForDatePicker.class, new I18nForDatePicker());
		}
		return UI.getCurrent().getSession().getAttribute(I18nForDatePicker.class).i18n;
	}
}
