package com.nmn.em.back.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nmn.em.back.model.IncomeSource;

public interface IncomeSourceRepository extends JpaRepository<IncomeSource, Long>, CodebookRepository<IncomeSource> {

}
