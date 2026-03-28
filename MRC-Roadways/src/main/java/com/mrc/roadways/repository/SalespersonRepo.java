package com.mrc.roadways.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.mrc.roadways.entity.Salesperson;

public interface SalespersonRepo extends JpaRepository<Salesperson, Integer>{
	Salesperson findBySalesId(int id);
}
