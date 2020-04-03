package com.nmn.em.front.components;

import com.nmn.em.front.components.NavMenuItem.NavMenuItemModel;
import com.nmn.em.front.components.SideNavigation.MenuItem;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.polymertemplate.EventHandler;
import com.vaadin.flow.component.polymertemplate.Id;
import com.vaadin.flow.component.polymertemplate.PolymerTemplate;
import com.vaadin.flow.dom.Element;
import com.vaadin.flow.templatemodel.TemplateModel;

@JsModule("./src/components/nav-menu-item.js")
@Tag("nav-menu-item")
public class NavMenuItem extends PolymerTemplate<NavMenuItemModel> {

	public interface NavMenuItemModel extends TemplateModel {
		void setIcon(String text);

		void setLabel(String text);
	}

	@Id
	private Div sideMenuItem;
	@Id
	private Element icon;
	@Id
	private Element iconToggle;
	@Id
	private Div divCollapse;

	private SideNavigation appLayout;
	private MenuItem menuItem;

	public NavMenuItem(SideNavigation appLayout, MenuItem menuItem) {
		this.appLayout = appLayout;
		this.menuItem = menuItem;
		menuItem.setComponent(this);
		icon.setVisible(menuItem.getIcon() != null);
		getModel().setIcon(menuItem.getIcon());
		getModel().setLabel(menuItem.getLabel());
		if (menuItem.getSubItems() != null) {
			for (MenuItem subItem : menuItem.getSubItems()) {
				NavMenuItem navMenuItem = new NavMenuItem(appLayout, subItem);
				navMenuItem.sideMenuItem.addClassName("side-menu-subitem");
				divCollapse.add(navMenuItem);
			}
		}
		iconToggle.setVisible(menuItem.getSubItems() != null);
	}

	@EventHandler
	private void _clicked() {
		if (menuItem.getUrl() != null) {
			UI.getCurrent().navigate(menuItem.getUrl());
			appLayout.select(menuItem);
		}
	}

	public void expand() {
		getElement().executeJs("this._clicked()");
	}

	public void deselect() {
		sideMenuItem.removeClassName("side-menu-item-active");
	}

	public void select() {
		sideMenuItem.addClassName("side-menu-item-active");
	}

}
