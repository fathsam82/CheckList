package com.skilldistillery.checklists.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.checklists.entities.CheckList;
import com.skilldistillery.checklists.repositories.CheckListRepository;

@Service
public class CheckListServiceImpl implements CheckListService {
	
	@Autowired
	private CheckListRepository checkListRepo;

	@Override
	public List<CheckList> listAllListItems() {
		return checkListRepo.findAll();
		
	}

	@Override
	public CheckList getListItem(int listItemId) {
		
		return null;
	}

	@Override
	public CheckList create(CheckList newListItem) {
		
		return null;
	}

	@Override
	public CheckList update(int listItemId, CheckList newListItem) {
		
		return null;
	}

	@Override
	public boolean delete(int listItemId) {
		
		return false;
	}

}
