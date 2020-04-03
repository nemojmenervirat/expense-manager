package com.nmn.em.back.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nmn.em.back.model.Income;

public interface IncomeRepository extends JpaRepository<Income, Long> {
	
	public List<Income> findAllByOrderByDateDescIncomeIdDesc();

}
