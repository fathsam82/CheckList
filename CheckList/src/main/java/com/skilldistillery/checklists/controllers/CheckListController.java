package com.skilldistillery.checklists.controllers;

import java.security.Principal;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.checklists.entities.CheckList;
import com.skilldistillery.checklists.services.CheckListService;

@RestController
@RequestMapping("api")
@CrossOrigin({ "*", "http://localhost/" })
public class CheckListController {

	@Autowired
	private CheckListService checkListService;

//	private String username = "johnnyboy";   //temp

	@GetMapping("checklists")
	public Set<CheckList> listAllCheckLists(Principal principal, HttpServletRequest req, HttpServletResponse res) {
		return checkListService.listAllListItems(principal.getName());

	}

	@GetMapping("checklists/{listItemId}")
	public CheckList getListItem(Principal principal, @PathVariable Integer listItemId, HttpServletResponse res) {

		CheckList checkList = checkListService.getListItem(principal.getName(), listItemId);
		if (checkList == null) {
			res.setStatus(404);
		}
		return checkList;

	}

	@PostMapping("checklists")
	public CheckList createListItem(Principal principal, @RequestBody CheckList newListItem, HttpServletResponse res,
			HttpServletRequest req) {
		try {
			newListItem = checkListService.create(principal.getName(), newListItem);
			if (newListItem == null) {
				res.setStatus(404);
			} else {
				res.setStatus(201);
				StringBuffer url = req.getRequestURL();
				res.setHeader("Locatioin", url.append("/").append(newListItem.getId()).toString());
			}
		} catch (Exception e) {
			res.setStatus(400);
			newListItem = null;
			e.printStackTrace();
		}
		return newListItem;

	}

	@PutMapping("checklists/{listItemId}")
	public CheckList updateListItem(Principal principal, @PathVariable Integer listItemId, @RequestBody CheckList checkList,
			HttpServletResponse res) {
		checkList = checkListService.update(principal.getName(), listItemId, checkList);
		try {
			if (checkList == null) {
				res.setStatus(404);
			}
		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(400);
			checkList = null;
		}
		return checkList;

	}

	@DeleteMapping("checklists/{listItemId}")
	public void delete(Principal principal, @PathVariable Integer listItemId, HttpServletResponse res) {
		try {
			if (checkListService.delete(principal.getName(), listItemId)) {
				res.setStatus(204);
			} else {
				res.setStatus(404);
			}
		} catch (Exception e) {
			res.setStatus(400);
		}
	}
}
