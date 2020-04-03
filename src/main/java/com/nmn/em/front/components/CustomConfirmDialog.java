package com.nmn.em.front.components;

import java.util.function.Consumer;

import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.polymertemplate.Id;
import com.vaadin.flow.component.polymertemplate.PolymerTemplate;
import com.vaadin.flow.templatemodel.TemplateModel;

@Tag("custom-confirm-dialog")
@JsModule("./src/components/custom-confirm-dialog.js")
public class CustomConfirmDialog extends PolymerTemplate<TemplateModel> {

	@Id
	private Div divTitle;
	@Id
	private Div divContent;
	@Id
	private Button buttonOK;
	@Id
	private Button buttonCancel;

	private CustomConfirmDialog(String title, String text, Dialog dialog, Consumer<Boolean> onClose) {

		divTitle.setText(title);
		divContent.setText(text);

		buttonOK.setText("Potvrdi");
		buttonCancel.setText("Odustani");

		buttonCancel.addClickListener(e -> {
			dialog.close();
			onClose.accept(false);
		});

		buttonOK.addClickListener(e -> {
			dialog.close();
			onClose.accept(true);
		});
	}

	public static void show(String title, String text, Consumer<Boolean> onClose) {
		Dialog dialog = new Dialog();
		CustomConfirmDialog content = new CustomConfirmDialog(title, text, dialog, onClose);
		dialog.add(content);
		dialog.setCloseOnOutsideClick(false);
		dialog.setCloseOnEsc(false);
		dialog.open();
	}

}
