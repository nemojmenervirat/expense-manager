package com.nmn.em.back.model;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table
public class IncomeGroup {

	@Id
	@GeneratedValue
	private long incomeGroupId;
	private String name;

	@OneToMany(mappedBy = "incomeGroup")
	private Collection<IncomeSource> incomeSources;

	public long getIncomeGroupId() {
		return incomeGroupId;
	}

	public void setIncomeGroupId(long incomeGroupId) {
		this.incomeGroupId = incomeGroupId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Collection<IncomeSource> getIncomeSources() {
		return incomeSources;
	}

	public void setIncomeSources(Collection<IncomeSource> incomeSources) {
		this.incomeSources = incomeSources;
	}

	@Override
	public int hashCode() {
		return Long.hashCode(incomeGroupId);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof IncomeGroup) {
			return this.incomeGroupId == ((IncomeGroup) obj).incomeGroupId;
		}
		return super.equals(obj);
	}

	@Override
	public String toString() {
		return this.name;
	}
}
