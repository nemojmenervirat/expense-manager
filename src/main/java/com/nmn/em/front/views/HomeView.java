package com.nmn.em.front.views;

import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.polymertemplate.PolymerTemplate;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import com.vaadin.flow.templatemodel.TemplateModel;

@PageTitle("Početna")
@Route(value = "home", layout = MainView.class)
@RouteAlias(value = "", layout = MainView.class)
@JsModule("./src/views/home-view.js")
@Tag("home-view")
public class HomeView extends PolymerTemplate<TemplateModel> {

}
