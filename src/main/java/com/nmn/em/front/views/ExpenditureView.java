package com.nmn.em.front.views;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.Route;

@Route(value = "expenditure", layout = MainView.class)
public class ExpenditureView extends Div {

	public ExpenditureView() {
		setText(getClass().getName());
	}
}
