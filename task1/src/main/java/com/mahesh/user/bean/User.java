package com.mahesh.user.bean;

import java.util.Date;
import java.util.Comparator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import com.sun.istack.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user_tbl")
@SQLDelete(sql ="UPDATE user_tbl SET deleted=true WHERE id=?")
//@Where(clause="deleted=false")
@FilterDef(name="deletedUserFilter",parameters=@ParamDef(name="isDeleted",type = "boolean"))
@Filter(name="deletedUserFilter",condition = "deleted =:isDeleted")
public class User implements Comparator<User> {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private int id;
	@Column(name = "NAME")
	@NotBlank(message = "name should not be blank")
	private String name;
	@Column(name = "SURNAME")
	@NotBlank(message = "surname should not be blank")
	private String surname;
	@Column(name = "DOB", nullable = false)
	private Date dob;
	@Column(name = "JD", nullable = false)
	private Date joiningDate;
	@Column(name = "PINCODE")
	private int pincode;
//	@Column(name="deleted")
//	private boolean deleted=Boolean.FALSE;

	
	@Override
	public int compare(User o1, User o2) {
		int value = o1.getDob().compareTo(o2.getDob());
		return (value == 0 ? o1.getJoiningDate().compareTo(o2.getJoiningDate())
				: value);
	}

}