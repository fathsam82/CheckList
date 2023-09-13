package com.skilldistillery.checklists.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.checklists.entities.CheckList;

public interface CheckListRepository extends JpaRepository<CheckList, Integer> {

}
