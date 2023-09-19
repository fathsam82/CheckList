package com.skilldistillery.checklists.controllers;

import java.util.List;
import java.util.Optional;

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

@CrossOrigin({ "*", "http://localhost/" })
@RestController
@RequestMapping("api")
public class CheckListController {

	@Autowired
	private CheckListService checkListService;

	@GetMapping("checklists")
	public List<CheckList> listAllCheckLists() {
		return checkListService.listAllListItems();

	}

	@GetMapping("checklists/{listItemId}")
	public CheckList getListItem(@PathVariable Integer listItemId, HttpServletResponse res) {
		Optional<CheckList> checkList = checkListService.getListItem(listItemId);
		if (checkList.isPresent()) {
			return checkList.get();
		} else {
			res.setStatus(404);
			return null;
		}
	}

	@PostMapping("checklists")
	public CheckList createListItem(@RequestBody CheckList newListItem, HttpServletResponse res,
			HttpServletRequest req) {
		newListItem = checkListService.create(newListItem);
		if (newListItem == null) {
			res.setStatus(404);
		} else {
			res.setStatus(201);
			StringBuffer url = req.getRequestURL();
			res.setHeader("Location", url.append("/").append(newListItem.getId()).toString());
		}

		return newListItem;

	}

	@PutMapping("checklists/{listItemId}")
	public CheckList updateListItem(@PathVariable Integer listItemId, @RequestBody CheckList checkList,
			HttpServletResponse res) {
		checkList = checkListService.update(listItemId, checkList);
		if (checkList == null) {
			res.setStatus(404);
		}

		return checkList;
	}

	@DeleteMapping("checklists/{listItemId}")
	public void delete(@PathVariable Integer listItemId, HttpServletResponse res) {
		if (checkListService.delete(listItemId)) {
			res.setStatus(204);
		} else {
			res.setStatus(404);
		}
	}
}
