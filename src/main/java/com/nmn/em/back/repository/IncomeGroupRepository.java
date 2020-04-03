package com.nmn.em.back.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nmn.em.back.model.IncomeGroup;

public interface IncomeGroupRepository extends JpaRepository<IncomeGroup, Long>, CodebookRepository<IncomeGroup> {

}
