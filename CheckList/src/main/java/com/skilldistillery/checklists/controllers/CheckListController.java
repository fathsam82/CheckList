package com.skilldistillery.checklists.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.checklists.entities.CheckList;
import com.skilldistillery.checklists.services.CheckListService;

@RestController
@RequestMapping("api")
public class CheckListController {
	
	@Autowired
	private CheckListService checkListService;
	
	@GetMapping("checklists")
	List<CheckList> listCheckLists() {
		return checkListService.listAllListItems();
		
	}

}
