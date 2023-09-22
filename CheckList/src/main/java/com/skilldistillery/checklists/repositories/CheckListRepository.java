package com.skilldistillery.checklists.repositories;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.checklists.entities.CheckList;

public interface CheckListRepository extends JpaRepository<CheckList, Integer> {
	Set<CheckList> findByUser_Username(String username);
	CheckList findByIdAndUser_Username(int checkListId, String username);
}
