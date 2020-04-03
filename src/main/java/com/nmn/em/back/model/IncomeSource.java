package com.nmn.em.back.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Table
@Entity
public class IncomeSource {

	@Id
	@GeneratedValue
	private long incomeSourceId;

	@ManyToOne
	@JoinColumn(name = "income_group_id")
	private IncomeGroup incomeGroup;
	private String name;

	public long getIncomeSourceId() {
		return incomeSourceId;
	}

	public void setIncomeSourceId(long incomeSourceId) {
		this.incomeSourceId = incomeSourceId;
	}

	public IncomeGroup getIncomeGroup() {
		return incomeGroup;
	}

	public void setIncomeGroup(IncomeGroup incomeGroup) {
		this.incomeGroup = incomeGroup;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public int hashCode() {
		return Long.hashCode(incomeSourceId);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof IncomeSource) {
			return this.incomeSourceId == ((IncomeSource) obj).incomeSourceId;
		}
		return super.equals(obj);
	}

	@Override
	public String toString() {
		return this.name;
	}

}
