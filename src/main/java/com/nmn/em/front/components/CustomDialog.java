package com.nmn.em.front.components;

import java.util.function.Consumer;
import java.util.function.Supplier;

import com.nmn.em.front.components.CustomDialog.CustomDialogModel;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.polymertemplate.Id;
import com.vaadin.flow.component.polymertemplate.PolymerTemplate;
import com.vaadin.flow.templatemodel.TemplateModel;

@Tag("custom-dialog")
@JsModule("./src/components/custom-dialog.js")
public class CustomDialog extends PolymerTemplate<CustomDialogModel> {

	public interface CustomDialogModel extends TemplateModel {
		void setTitleText(String value);

		void setOkText(String value);

		void setCancelText(String value);

		void setDeleteText(String value);
	}

	@Id
	private Div divContent;
	@Id
	private Button buttonOK;
	@Id
	private Button buttonCancel;
	@Id
	private Button buttonDelete;

	private CustomDialog(String title, Component content, Dialog dialog,
			Supplier<Boolean> okAction, Consumer<Dialog> deleteAction, Runnable cancelAction) {

		getModel().setTitleText(title);
		divContent.add(content);

		buttonOK.addClickListener(e -> {
			if (okAction != null) {
				if (okAction.get()) {
					dialog.close();
				}
			}
		});
		buttonCancel.addClickListener(e -> {
			if (cancelAction != null) {
				cancelAction.run();
			}
			dialog.close();
		});
		buttonDelete.setVisible(deleteAction != null);
		buttonDelete.addClickListener(e -> {
			if (deleteAction != null) {
				deleteAction.accept(dialog);
			}
		});

		getModel().setOkText("Potvrdi");
		getModel().setCancelText("Odustani");
		getModel().setDeleteText("Ukloni");

	}

	public static void show(String title, Component content,
			Supplier<Boolean> okAction, Consumer<Dialog> deleteAction, Runnable cancelAction) {
		Dialog dialog = new Dialog();
		CustomDialog customDialog = new CustomDialog(title, content, dialog, okAction, deleteAction,
				cancelAction);
		dialog.add(customDialog);
		dialog.setCloseOnOutsideClick(false);
		dialog.setCloseOnEsc(false);
		dialog.open();
	}

}
