package com.nmn.em.back;

import com.nmn.em.front.FrontUtils;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.Notification.Position;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.server.ServiceInitEvent;
import com.vaadin.flow.server.VaadinServiceInitListener;
import com.vaadin.flow.spring.annotation.SpringComponent;

@SpringComponent
public class CustomVaadinServiceInitListener implements VaadinServiceInitListener {

	@Override
	public void serviceInit(ServiceInitEvent event) {
		event.addBootstrapListener(new CustomBootstrapListener());
		event.getSource().addSessionInitListener(e -> {
			e.getSession().setErrorHandler(error -> {
				error.getThrowable().printStackTrace();
				Throwable source = error.getThrowable();
				while (source.getCause() != null) {
					source = source.getCause();
				}
				Notification notification = new Notification(source.getMessage(), 0, Position.MIDDLE);
				notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
				notification.open();
			});
		});
		event.getSource().addUIInitListener(e -> {
			UI.getCurrent().setLocale(FrontUtils.getLocale());
		});
	}
}
