package com.skilldistillery.checklists.services;

import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.checklists.entities.CheckList;
import com.skilldistillery.checklists.entities.User;
import com.skilldistillery.checklists.repositories.CheckListRepository;
import com.skilldistillery.checklists.repositories.UserRepository;

@Service
@Transactional
public class CheckListServiceImpl implements CheckListService {

	@Autowired
	CheckListRepository checkListRepo;

	@Autowired
	UserRepository userRepo;

	@Override
	public Set<CheckList> listAllListItems(String username) {

		return checkListRepo.findByUser_Username(username);
	}

	@Override
	public CheckList getListItem(String username, int listItemId) {

		CheckList checkList = null;

		Optional<CheckList> checkListOpt = checkListRepo.findById(listItemId);
		if (checkListOpt.isPresent() && checkListOpt.get().getUser().getUsername().equals(username)) {
			checkList = checkListOpt.get();
		}
		return checkList;

	}

	@Override
	public CheckList create(String username, CheckList newListItem) {
		User user = userRepo.findByUsername(username);
		if (user != null) {
			newListItem.setUser(user);
			return checkListRepo.saveAndFlush(newListItem);
		}
		return null;
	}

	@Override
	public CheckList update(String username, int listItemId, CheckList newListItem) {
		CheckList existing = checkListRepo.findByIdAndUser_Username(listItemId, username);
		if (existing != null) {
			existing.setCompleted(newListItem.getCompleted());
			existing.setCompleteDate(newListItem.getCompleteDate());
			existing.setDueDate(newListItem.getDueDate());
			existing.setName(newListItem.getName());
			existing.setDescription(newListItem.getDescription());
			existing.setFrequency(newListItem.getFrequency());
			existing.setCheckListType(newListItem.getCheckListType());   //recently added
			checkListRepo.saveAndFlush(existing);

		}
		return existing;
	}

	@Override
	public boolean delete(String username, int listItemId) {
		boolean deleted = false;
		CheckList toDelete = checkListRepo.findByIdAndUser_Username(listItemId, username);
		if (toDelete != null) {
			checkListRepo.delete(toDelete);
			deleted = true;
		}
		return deleted;

	}

}
