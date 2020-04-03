package com.nmn.em.front.views.admin;

import org.springframework.beans.factory.annotation.Autowired;

import com.nmn.em.back.model.IncomeGroup;
import com.nmn.em.back.repository.IncomeGroupRepository;
import com.nmn.em.front.views.MainView;
import com.vaadin.flow.router.Route;

@Route(layout = MainView.class)
public class IncomeGroupView extends CodebookView<IncomeGroup, IncomeGroupRepository> {

	@Autowired
	private IncomeGroupRepository repository;

	@Override
	protected String getTitle() {
		return "Grupe prihoda";
	}

	@Override
	protected void addGridColumns() {
		addColumn(IncomeGroup::getName, "Naziv");
		addListColumn(IncomeGroup::getIncomeSources, "Izvori prihoda");
	}

	@Override
	protected IncomeGroupRepository getRepository() {
		return repository;
	}

	@Override
	protected CodebookForm<IncomeGroup> getForm() {
		throw new UnsupportedOperationException();
	}

}
