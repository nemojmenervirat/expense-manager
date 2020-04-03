package com.nmn.em.back;

import org.jsoup.nodes.Element;

import com.vaadin.flow.server.BootstrapListener;
import com.vaadin.flow.server.BootstrapPageResponse;

public class CustomBootstrapListener implements BootstrapListener {

	@Override
	public void modifyBootstrapPage(BootstrapPageResponse response) {
		final Element head = response.getDocument().head();
		head.append("<link rel='shortcut icon' href='icons/favicon.ico'>");
		head.append("<link rel='icon' type='image/png' href='icons/icon.png' sizes='600x600'>");
	}

}
