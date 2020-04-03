package com.nmn.em.front.views;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.Route;

@Route(value = "saving", layout = MainView.class)
public class SavingView extends Div {

	public SavingView() {
		setText(getClass().getName());
	}
}
