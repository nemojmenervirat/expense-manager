package com.nmn.em.front.views.income;

import java.math.BigDecimal;
import java.time.YearMonth;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.nmn.em.back.model.Income;
import com.nmn.em.back.model.ItemType;
import com.nmn.em.front.FrontUtils;
import com.vaadin.flow.shared.util.SharedUtil;

public class IncomeGridItemHeaderGenerator {

	private final Map<Long, IncomeGridItemHeader> headerMap = new HashMap<>();

	public IncomeGridItemHeaderGenerator(Map<YearMonth, List<Income>> incomePerMonth) {
		if (incomePerMonth.isEmpty()) {
			return;
		}

		for (YearMonth yearMonth : incomePerMonth.keySet()) {
			long firstItem = incomePerMonth.get(yearMonth).stream().sorted(Comparator.reverseOrder()).findFirst().get().getIncomeId();
			IncomeGridItemHeader header = new IncomeGridItemHeader();
			header.setMonth(SharedUtil.capitalize(FrontUtils.getMonth(yearMonth.getMonthValue(), yearMonth.getYear())));
			header.setPlanned(
					incomePerMonth.get(yearMonth).stream().filter(e -> e.getType() == ItemType.PLANNED).map(e -> e.getValue()).reduce(BigDecimal.ZERO,
							BigDecimal::add).doubleValue());
			header.setActual(incomePerMonth.get(yearMonth).stream().filter(e -> e.getType() == ItemType.ACTUAL).map(e -> e.getValue()).reduce(BigDecimal.ZERO,
					BigDecimal::add).doubleValue());
			headerMap.put(firstItem, header);
		}
	}

	public IncomeGridItemHeader get(long id) {
		return headerMap.get(id);
	}

}
