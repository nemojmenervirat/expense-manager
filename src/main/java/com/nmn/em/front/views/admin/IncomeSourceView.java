package com.nmn.em.front.views.admin;

import org.springframework.beans.factory.annotation.Autowired;

import com.nmn.em.back.model.IncomeSource;
import com.nmn.em.back.repository.IncomeSourceRepository;
import com.nmn.em.front.views.MainView;
import com.vaadin.flow.router.Route;

@Route(layout = MainView.class)
public class IncomeSourceView extends CodebookView<IncomeSource, IncomeSourceRepository> {

	@Autowired
	private IncomeSourceRepository repository;

	@Override
	protected String getTitle() {
		return "Izvori prihoda";
	}

	@Override
	protected void addGridColumns() {
		addColumn(IncomeSource::getName, "Naziv");
		addColumn(IncomeSource::getIncomeGroup, "Grupa prihoda");
	}

	@Override
	protected IncomeSourceRepository getRepository() {
		return repository;
	}

	@Override
	protected CodebookForm<IncomeSource> getForm() {
		throw new UnsupportedOperationException();
	}

}
