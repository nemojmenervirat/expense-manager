package com.nmn.em.back.model;

public enum ItemType {

	ACTUAL("Ostvarno"), PLANNED("Planirano");

	private String name;

	ItemType(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

}
