package com.nmn.em.back.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.YearMonth;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Table
@Entity
public class Income implements Comparable<Income> {

	@Id
	@GeneratedValue
	private long incomeId;
	private BigDecimal value;
	private LocalDate date;

	@ManyToOne
	@JoinColumn(name = "income_source_id")
	private IncomeSource source;

	@Enumerated(EnumType.STRING)
	private ItemType type;

	public long getIncomeId() {
		return incomeId;
	}

	public void setIncomeId(long incomeId) {
		this.incomeId = incomeId;
	}

	public BigDecimal getValue() {
		return value;
	}

	public void setValue(BigDecimal value) {
		this.value = value;
	}

	public LocalDate getDate() {
		return date;
	}

	public YearMonth getYearMonth() {
		return YearMonth.from(date);
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public IncomeSource getSource() {
		return source;
	}

	public IncomeGroup getSourceGroup() {
		return source.getIncomeGroup();
	}

	public void setSource(IncomeSource source) {
		this.source = source;
	}

	public ItemType getType() {
		return type;
	}

	public void setType(ItemType type) {
		this.type = type;
	}

	@Override
	public int hashCode() {
		return Long.hashCode(incomeId);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Income) {
			return this.incomeId == ((Income) obj).incomeId;
		}
		return super.equals(obj);
	}

	@Override
	public int compareTo(Income o) {
		int cmp = this.date.compareTo(o.date);
		if (cmp == 0) {
			cmp = Long.compare(this.incomeId, o.incomeId);
		}
		return cmp;
	}

}
