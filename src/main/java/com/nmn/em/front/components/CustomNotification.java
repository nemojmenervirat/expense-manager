package com.nmn.em.front.components;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.nmn.em.front.components.CustomNotification.CustomNotificationModel;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.Notification.Position;
import com.vaadin.flow.component.polymertemplate.Id;
import com.vaadin.flow.component.polymertemplate.PolymerTemplate;
import com.vaadin.flow.templatemodel.TemplateModel;

@Tag("custom-notification")
@JsModule("./src/components/custom-notification.js")
public class CustomNotification extends PolymerTemplate<CustomNotificationModel> {

	public interface CustomNotificationModel extends TemplateModel {
		void setNotificationType(String type);
	}

	@Id
	private Div divTitle;

	@Id
	private Div divTime;

	@Id
	private Div divContent;

	@Id
	private Div divDetails;

	@Id
	private Button buttonOK;

	@Id
	private Button buttonDetails;

	public CustomNotification(NotificationType type, String text, String details, Notification notification, Dialog dialog) {

		getModel().setNotificationType(type.name());
		buttonOK.setText("U redu");
		divContent.setText(text);
		divDetails.setText(details);
		divDetails.setVisible(false);
		buttonDetails.setVisible(details != null && !details.isEmpty());
		divTime.setText(" Vrijeme: " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss")));

		switch (type) {
		case ERROR:
			divTitle.setText("Greška");
			break;
		case INFO:
			divTitle.setText("Obavještenje");
			break;
		case WARNING:
			divTitle.setText("Upozorenje");
			break;
		}

		buttonOK.addClickListener(e -> {
			if (notification != null) {
				notification.close();
			}
			if (dialog != null) {
				dialog.close();
			}
		});

		buttonDetails.addClickListener(e -> {
			divDetails.setVisible(!divDetails.isVisible());
		});

	}

	public enum NotificationType {
		INFO, ERROR, WARNING
	}

	public static void show(String text) {
		show(text, Position.BOTTOM_END, NotificationType.INFO, null, 5000);
	}

	public static void show(String text, Position position, NotificationType type, String detail, int duration) {
		if (type != NotificationType.ERROR) {
			Notification notification = new Notification();
			CustomNotification content = new CustomNotification(type, text, detail, notification, null);
			notification.add(content);
			notification.setPosition(position);
			notification.setDuration(duration);
			notification.open();
		} else {
			Dialog dialog = new Dialog();
			CustomNotification content = new CustomNotification(type, text, detail, null, dialog);
			dialog.add(content);
			dialog.setCloseOnEsc(false);
			dialog.setCloseOnOutsideClick(false);
			dialog.open();
		}
	}

	public static void show(String text, NotificationType type, String detail) {
		int duration = 0;
		Position position = Position.MIDDLE;
		if (type == NotificationType.INFO) {
			duration = 5000;
			position = Position.BOTTOM_END;
		}
		show(text, position, type, detail, duration);
	}

	public static void show(String text, NotificationType type) {
		show(text, type, null);
	}

	public static void show(String text, int duration) {
		show(text, Position.BOTTOM_END, NotificationType.INFO, null, duration);
	}

	public static void show(String text, Position position) {
		show(text, position, NotificationType.INFO, null, 5000);
	}

	public static void show(String text, Position position, NotificationType type) {
		show(text, position, type, null, type == NotificationType.INFO ? 5000 : 0);
	}

}
