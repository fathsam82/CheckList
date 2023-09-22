package com.skilldistillery.checklists.services;

import java.util.Optional;
import java.util.Set;

import com.skilldistillery.checklists.entities.CheckList;

public interface CheckListService {
	Set<CheckList> listAllListItems(String username);

	CheckList getListItem(String username, int listItemId);

	CheckList create(String username, CheckList newListItem);

	CheckList update(String username, int listItemId, CheckList newListItem);

	boolean delete(String username, int listItemId);
	
	
	
}
