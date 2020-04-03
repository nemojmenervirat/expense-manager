package com.nmn.em.front.views.admin;

import com.vaadin.flow.component.Component;

public interface CodebookForm<T> {

	public Component getLayout();

	public void setBean(T bean, boolean editing);

	public boolean validate();
}
