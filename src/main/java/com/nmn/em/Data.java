package com.nmn.em;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.YearMonth;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.nmn.em.back.model.Income;
import com.nmn.em.back.model.IncomeGroup;
import com.nmn.em.back.model.IncomeSource;
import com.nmn.em.back.model.ItemType;
import com.nmn.em.back.repository.IncomeGroupRepository;
import com.nmn.em.back.repository.IncomeRepository;
import com.nmn.em.back.repository.IncomeSourceRepository;

@Component
public class Data {

	@Autowired
	private IncomeRepository incomeRepository;
	@Autowired
	private IncomeGroupRepository incomeGroupRepository;
	@Autowired
	private IncomeSourceRepository incomeSourceRepository;

	private IncomeGroup insertIncomeGroup(String name) {
		IncomeGroup incomeGroup = new IncomeGroup();
		incomeGroup.setName(name);
		return incomeGroupRepository.save(incomeGroup);
	}

	private IncomeSource insertIncomeSource(String name, IncomeGroup group) {
		IncomeSource incomeSource = new IncomeSource();
		incomeSource.setIncomeGroup(group);
		incomeSource.setName(name);
		return incomeSourceRepository.save(incomeSource);
	}

	private void insertIncome(YearMonth yearMonth, int dayFrom, int dayTo, IncomeSource source, ItemType type, int valueFrom, int valueTo) {
		Income income = new Income();
		income.setDate(
				LocalDate.of(yearMonth.getYear(), yearMonth.getMonth(), Long.valueOf(Math.round(Math.random() * (dayTo - dayFrom) + dayFrom)).intValue()));
		income.setSource(source);
		income.setType(type);
		income.setValue(new BigDecimal(Math.round(Math.random() * (valueTo - valueFrom) + valueFrom)));
		incomeRepository.save(income);
	}

	@PostConstruct
	void init() {
		IncomeGroup incomeGroup = insertIncomeGroup("Svi prihodi");

		IncomeSource incomeSourceMilan = insertIncomeSource("Milanova plata", incomeGroup);
		IncomeSource incomeSourceRegres = insertIncomeSource("Milanov regres", incomeGroup);
		insertIncomeSource("Suzanina plata", incomeGroup);
		IncomeSource incomeSourceUnplanned = insertIncomeSource("Neplanirani prihodi", incomeGroup);

		LocalDate dateTo = LocalDate.now();
		LocalDate dateFrom = dateTo.minusYears(1);
		while (dateFrom.isBefore(dateTo)) {
			{
				insertIncome(YearMonth.from(dateFrom), 3, 3, incomeSourceMilan, ItemType.PLANNED, 4500, 4900);
				insertIncome(YearMonth.from(dateFrom), 2, 5, incomeSourceMilan, ItemType.ACTUAL, 4500, 5000);
				if (Math.random() * 10 > 8) {
					insertIncome(YearMonth.from(dateFrom), 2, 28, incomeSourceUnplanned, ItemType.ACTUAL, 20, 80);
				}
				if (dateFrom.getMonthValue() == 6 || dateFrom.getMonthValue() == 12) {
					insertIncome(YearMonth.from(dateFrom), 3, 3, incomeSourceRegres, ItemType.PLANNED, 300, 300);
					insertIncome(YearMonth.from(dateFrom), 2, 5, incomeSourceRegres, ItemType.ACTUAL, 300, 300);
				}
			}
			dateFrom = dateFrom.plusMonths(1);
		}
	}

}
