package com.skilldistillery.checklists.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.checklists.entities.CheckList;
import com.skilldistillery.checklists.repositories.CheckListRepository;

@Service
@Transactional
public class CheckListServiceImpl implements CheckListService {

	@Autowired
	CheckListRepository checkListRepo;

	@Override
	public List<CheckList> listAllListItems() {

		return checkListRepo.findAll();
	}

	@Override
	public Optional<CheckList> getListItem(int listItemId) {

		if (!checkListRepo.existsById(listItemId)) {
			return Optional.empty();
		}

		return checkListRepo.findById(listItemId);
	}

	@Override
	public CheckList create(CheckList newListItem) {
		newListItem.setCheckListType(newListItem.getCheckListType());

		return checkListRepo.saveAndFlush(newListItem);
	}

	@Override
	public CheckList update(int listItemId, CheckList newListItem) {
		CheckList updatedItem = null;
		Optional<CheckList> existingListItem = checkListRepo.findById(listItemId);
		if (existingListItem.isPresent()) {
			updatedItem = existingListItem.get();
			updatedItem.setName(newListItem.getName());
			updatedItem.setDescription(newListItem.getDescription());
			updatedItem.setFrequency(newListItem.getFrequency());
			updatedItem.setCheckListType(newListItem.getCheckListType());
			checkListRepo.saveAndFlush(updatedItem);

			return updatedItem;
		}

		return null;
	}

	@Override
	public boolean delete(int listItemId) {
		if (checkListRepo.existsById(listItemId)) {
			checkListRepo.deleteById(listItemId);
			return true;
		}

		return false;
	}

}
