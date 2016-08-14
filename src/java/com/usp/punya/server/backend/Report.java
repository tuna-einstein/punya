package com.usp.punya.server.backend;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Basic;
import javax.persistence.CascadeType;

import com.google.appengine.api.datastore.Key;
import com.usp.punya.server.backend.model.Address;
import com.usp.punya.server.backend.model.BookInfo;

@Entity
public class Report {

	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Key id;

	@OneToMany(cascade = CascadeType.ALL, 
	        mappedBy = "report", orphanRemoval = true, fetch=FetchType.EAGER)
	private List<BookInfo> bookInfos;
	
	private Date date;
	
	@Embedded
	@Basic(fetch=FetchType.EAGER)
	private Address address;

	public Key getId() {
		return id;
	}

	public List<BookInfo> getBookInfos() {
		return bookInfos;
	}

	public void setBookInfos(List<BookInfo> bookInfos) {
		this.bookInfos = bookInfos;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}
}