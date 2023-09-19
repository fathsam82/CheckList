package com.skilldistillery.checklists.services;

import java.util.List;
import java.util.Optional;

import com.skilldistillery.checklists.entities.CheckList;

public interface CheckListService {
	List<CheckList> listAllListItems();

	Optional<CheckList> getListItem(int listItemId);

	CheckList create(CheckList newListItem);

	CheckList update(int listItemId, CheckList newListItem);

	boolean delete(int listItemId);
}
