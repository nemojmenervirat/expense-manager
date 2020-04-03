package com.nmn.em.back;

import com.nmn.em.front.FrontUtils;
import com.nmn.em.front.components.CustomNotification;
import com.nmn.em.front.components.CustomNotification.NotificationType;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.notification.Notification.Position;
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
				Throwable source = error.getThrowable();
				while (source.getCause() != null) {
					source = source.getCause();
				}
				source.printStackTrace();
				CustomNotification.show(source.getMessage(), Position.MIDDLE, NotificationType.ERROR);
				error.getThrowable().printStackTrace();
			});
		});
		event.getSource().addUIInitListener(e -> {
			UI.getCurrent().setLocale(FrontUtils.getLocale());
		});
	}
}
