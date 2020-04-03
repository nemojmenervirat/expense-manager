package com.nmn.em.front.views.admin;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nmn.em.back.repository.CodebookRepository;
import com.nmn.em.front.components.CustomConfirmDialog;
import com.nmn.em.front.components.CustomDialog;
import com.nmn.em.front.views.admin.CodebookView.CodebookViewModel;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.Grid.Column;
import com.vaadin.flow.component.grid.Grid.SelectionMode;
import com.vaadin.flow.component.grid.HeaderRow;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.polymertemplate.EventHandler;
import com.vaadin.flow.component.polymertemplate.Id;
import com.vaadin.flow.component.polymertemplate.PolymerTemplate;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.data.provider.Query;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.data.renderer.Renderer;
import com.vaadin.flow.data.renderer.TemplateRenderer;
import com.vaadin.flow.function.ValueProvider;
import com.vaadin.flow.router.AfterNavigationEvent;
import com.vaadin.flow.router.AfterNavigationObserver;
import com.vaadin.flow.router.HasDynamicTitle;
import com.vaadin.flow.templatemodel.TemplateModel;

@JsModule("./src/views/codebook-view.js")
@Tag("codebook-view")
public abstract class CodebookView<T, V extends CodebookRepository<T> & JpaRepository<T, Long>> extends PolymerTemplate<CodebookViewModel>
		implements HasDynamicTitle, AfterNavigationObserver {

	public interface CodebookViewModel extends TemplateModel {
		void setTitleText(String value);

		void setStatusText(String value);
	}

	@Id
	private Grid<T> grid;

	protected ListDataProvider<T> dataProvider;
	private HeaderRow filterRow;
	private CodebookForm<T> codebookForm;

	public CodebookView() {
		setStatusRow();
		grid.setSelectionMode(SelectionMode.NONE);
		grid.setMultiSort(true);
		getModel().setTitleText(getTitle());
		grid.addColumn(rendererButtonEdit()).setFlexGrow(0).setWidth("70px");
		addGridColumns();
		for (Column<T> column : grid.getColumns()) {
			column.getElement().getParent().callJsFunction("setAttribute", "resizable", true);
		}
	}

	@Override
	public void afterNavigation(AfterNavigationEvent event) {
		loadGrid();
	}

	@Override
	public String getPageTitle() {
		return getTitle();
	}

	private HeaderRow getFilterRow() {
		if (filterRow == null) {
			filterRow = grid.appendHeaderRow();
		}
		return filterRow;
	}

	protected Column<T> addColumn(ValueProvider<T, ?> valueProvider, String header) {
		return grid.addColumn(valueProvider).setHeader(header).setSortable(true).setResizable(true);
	}

	protected Column<T> addColumn(ValueProvider<T, ?> valueProvider, String header, Component filterComponent) {
		Column<T> column = grid.addColumn(valueProvider).setHeader(header).setSortable(true);
		getFilterRow().getCell(column).setComponent(filterComponent);
		return column;
	}

	protected Column<T> addColumn(Renderer<T> renderer) {
		return grid.addColumn(renderer);
	}

	protected <R> Column<T> addListColumn(ValueProvider<T, Collection<R>> valueProvider, String header) {
		Column<T> column = grid.addColumn(rendererList(valueProvider)).setHeader(header);
		return column;
	}

	protected abstract String getTitle();

	protected abstract void addGridColumns();

	protected abstract V getRepository();

	protected abstract CodebookForm<T> getForm();

	protected ComponentRenderer<Image, T> rendererImageAvatar(ValueProvider<T, Long> valueProviderImageId) {
		return new ComponentRenderer<>(e -> {
			Image image = new Image();
			image.setSrc("image/" + valueProviderImageId.apply(e));
			image.setAlt("");
			image.addClassName("avatar");
			return image;
		});
	}

	private <R> TemplateRenderer<T> rendererList(ValueProvider<T, Collection<R>> valueProviderList) {
		String html = "<ul style='margin: 0'><template is='dom-repeat' items='[[item.list]]'><li>[[item]]</li></template></ul>";
		return TemplateRenderer.<T>of(html).withProperty("list",
				e -> valueProviderList.apply(e).stream().map(r -> r.toString()).collect(Collectors.toList()));
	}

	private TemplateRenderer<T> rendererButtonEdit() {
		String html = "<vaadin-button theme='icon small' style='background-color: #17a2b8; border-radius: 3px; cursor: pointer; color: #ffffff;' on-click='clickEdit'><iron-icon icon='vaadin:edit'></iron-icon></vaadin-button>";
		return TemplateRenderer.<T>of(html).withEventHandler("clickEdit", e -> buttonEditClick(e));
	}

	@EventHandler
	private void buttonAddClick() {
		if (codebookForm == null) {
			codebookForm = getForm();
		}
		T entity = getRepository().createNew();
		codebookForm.setBean(entity, false);
		CustomDialog.show(getTitle() + " - Unos", codebookForm.getLayout(), () -> okAction(entity), null,
				() -> cancelAction());
	}

	private void buttonEditClick(T entity) {
		if (codebookForm == null) {
			codebookForm = getForm();
		}
		codebookForm.setBean(entity, true);
		CustomDialog.show(getTitle() + " - Izmjena", codebookForm.getLayout(), () -> okAction(entity),
				dialog -> deleteAction(entity, dialog), () -> cancelAction());
	}

	private boolean okAction(T entity) {
		if (codebookForm.validate()) {
			getRepository().save(entity);
			loadGrid();
			return true;
		}
		return false;
	}

	private void deleteAction(T entity, Dialog dialog) {
		CustomConfirmDialog.show("Uklanjanje", "Da li set sigurni da želite da uklonite " + "\"" + entity.toString() + "\"?", result -> {
			if (result) {
				getRepository().delete(entity);
				loadGrid();
				dialog.close();
			}
		});
	}

	private void cancelAction() {
		loadGrid();
	}

	private void loadGrid() {
		dataProvider = new ListDataProvider<>(getRepository().findAll());
		grid.setDataProvider(dataProvider);
		loadListeners.forEach(listener -> listener.run());
		setStatusRow();
	}

	private void setStatusRow() {
		int size = dataProvider == null ? 0 : dataProvider.size(new Query<>());
		getModel().setStatusText("Broj redova: " + size);
	}

	private List<Runnable> loadListeners = new LinkedList<>();

}
