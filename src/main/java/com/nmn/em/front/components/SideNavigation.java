package com.nmn.em.front.components;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.polymertemplate.Id;
import com.vaadin.flow.component.polymertemplate.PolymerTemplate;
import com.vaadin.flow.router.RouteConfiguration;
import com.vaadin.flow.templatemodel.TemplateModel;

@JsModule("./src/components/side-navigation.js")
@Tag("side-navigation")
public class SideNavigation extends PolymerTemplate<TemplateModel> {

	@Id
	private Div sideNavigation;

	private NavMenuItem current;

	private List<MenuItem> menuItems = new LinkedList<>();

	public void select(MenuItem selected) {
		if (current != null) {
			current.deselect();
		}
		if (selected != null) {
			if (selected.getParent() != null) {
				selected.getParent().getComponent().expand();
			}
			selected.getComponent().select();
		}
		current = selected == null ? null : selected.getComponent();
	}

	public void markSelected(String href) {
		MenuItem selected = menuItems.stream().filter(e -> href.equals(e.getUrl())).findAny().orElse(null);
		select(selected == null ? null : selected);
	}

	public void addMenuWithSubitems(String label, VaadinIcon icon, MenuItem... subItems) {
		MenuItem item = MenuItem.create(label, icon);
		menuItems.add(item);
		item.setSubItems(Arrays.asList(subItems));
		menuItems.addAll(item.getSubItems());
		NavMenuItem navMenuItem = new NavMenuItem(this, item);
		sideNavigation.add(navMenuItem);
	}

	public void addMenuItem(Class<? extends Component> navigationTarget, String label, VaadinIcon icon) {
		MenuItem item = MenuItem.create(navigationTarget, label, icon);
		menuItems.add(item);
		NavMenuItem navMenuItem = new NavMenuItem(this, item);
		sideNavigation.add(navMenuItem);
	}

	public static class MenuItem {

		private String label;
		private String url;
		private Class<? extends Component> navigationTarget;
		private String icon;
		private NavMenuItem component;
		private List<MenuItem> subItems;
		private MenuItem parent;

		private MenuItem() {
		}

		public static MenuItem create(Class<? extends Component> navigationTarget, String label, VaadinIcon icon) {
			MenuItem item = new MenuItem();
			item.setLabel(label);
			item.setNavigationTarget(navigationTarget);
			if (icon != null) {
				item.setIcon("vaadin:" + icon.toString().replace("_", "-").toLowerCase());
			}
			if (navigationTarget != null) {
				RouteConfiguration configuration = RouteConfiguration.forRegistry(UI.getCurrent().getRouter().getRegistry());
				String route = configuration.getUrl(navigationTarget);
				item.setUrl(route);
			}
			return item;
		}

		public static MenuItem create(Class<? extends Component> navigationTarget, String label) {
			return create(navigationTarget, label, null);
		}

		public static MenuItem create(String label, VaadinIcon icon) {
			return create(null, label, icon);
		}

		public String getLabel() {
			return label;
		}

		public void setLabel(String label) {
			this.label = label;
		}

		public String getUrl() {
			return url;
		}

		public void setUrl(String url) {
			this.url = url;
		}

		public Class<? extends Component> getNavigationTarget() {
			return navigationTarget;
		}

		public void setNavigationTarget(Class<? extends Component> navigationTarget) {
			this.navigationTarget = navigationTarget;
		}

		public String getIcon() {
			return icon;
		}

		public void setIcon(String icon) {
			this.icon = icon;
		}

		public NavMenuItem getComponent() {
			return component;
		}

		public void setComponent(NavMenuItem component) {
			this.component = component;
		}

		public List<MenuItem> getSubItems() {
			return subItems;
		}

		public void setSubItems(List<MenuItem> subItems) {
			this.subItems = subItems;
			subItems.forEach(e -> e.setParent(this));
		}

		public MenuItem getParent() {
			return parent;
		}

		public void setParent(MenuItem parent) {
			this.parent = parent;
		}

	}

}
