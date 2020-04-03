package com.nmn.em.back;

import org.jsoup.nodes.Element;

import com.vaadin.flow.server.BootstrapListener;
import com.vaadin.flow.server.BootstrapPageResponse;

public class CustomBootstrapListener implements BootstrapListener {

	@Override
	public void modifyBootstrapPage(BootstrapPageResponse response) {
		final Element head = response.getDocument().head();
		head.append("<link rel='shortcut icon' href='icons/favicon.ico'>");
//		head.append("<script type='module'>import './frontend/styles/bootstrap.js'; import './frontend/styles/shared-styles.js';</script>");
	}

}
