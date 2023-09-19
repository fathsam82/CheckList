package com.skilldistillery.checklists.entities;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "check_list")
public class CheckList {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String name;

	private String frequency;

	private String description;

	@ManyToOne
	@JoinColumn(name = "check_list_type_id")
	private CheckListType checkListType;

	public CheckList() {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFrequency() {
		return frequency;
	}

	public void setFrequency(String frequency) {
		this.frequency = frequency;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public CheckListType getCheckListType() {
		return checkListType;
	}

	public void setCheckListType(CheckListType checkListType) {
		this.checkListType = checkListType;
	}

	@Override
	public String toString() {
		return "CheckList [id=" + id + ", name=" + name + ", frequency=" + frequency + ", description=" + description
				+ "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CheckList other = (CheckList) obj;
		return id == other.id;
	}

}
