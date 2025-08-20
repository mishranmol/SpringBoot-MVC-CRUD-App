package com.anmol.week1.IntroductionToSpringBoot.repositories;

import com.anmol.week1.IntroductionToSpringBoot.entities.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity,Long> {

}
