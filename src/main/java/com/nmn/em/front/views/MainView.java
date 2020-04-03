package com.nmn.em.front.views;

import com.nmn.em.front.components.SideNavigation;
import com.nmn.em.front.components.SideNavigation.MenuItem;
import com.nmn.em.front.views.admin.IncomeGroupView;
import com.nmn.em.front.views.admin.IncomeSourceView;
import com.nmn.em.front.views.income.IncomeView;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.page.Viewport;
import com.vaadin.flow.router.AfterNavigationEvent;
import com.vaadin.flow.router.AfterNavigationObserver;

//@PWA(name = "Nemojmenervirat Expense Manager", shortName = "NEM")
@JsModule("./styles/bootstrap.js")
@CssImport("./styles/shared-styles.css")
@CssImport(value = "./styles/theme-app-layout.css", themeFor = "vaadin-app-layout")
@Viewport("width=device-width, minimum-scale=1, initial-scale=1, user-scalable=yes, viewport-fit=cover")
public class MainView extends AppLayout implements AfterNavigationObserver {

	private SideNavigation sideNavigation;

	public MainView() {

		addToNavbar(new DrawerToggle());
		setPrimarySection(Section.DRAWER);

		sideNavigation = new SideNavigation();
		sideNavigation.addMenuItem(HomeView.class, "Početna", VaadinIcon.GRID_SMALL);
		sideNavigation.addMenuItem(IncomeView.class, "Prihodi", VaadinIcon.WALLET);
		sideNavigation.addMenuItem(ExpenditureView.class, "Rashodi", VaadinIcon.CASH);
		sideNavigation.addMenuItem(SavingView.class, "Štednja", VaadinIcon.PIGGY_BANK);
		sideNavigation.addMenuWithSubitems("Administracija", VaadinIcon.COG,
				MenuItem.create(IncomeGroupView.class, "Grupe prihoda"), MenuItem.create(IncomeSourceView.class, "Izvori prihoda"));
		addToDrawer(sideNavigation);

	}

	@Override
	public void afterNavigation(AfterNavigationEvent event) {
		String href = event.getLocation().getPath().isEmpty() ? "home" : event.getLocation().getPath();
		sideNavigation.markSelected(href);
	}

}
