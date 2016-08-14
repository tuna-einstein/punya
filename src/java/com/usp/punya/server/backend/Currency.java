package com.usp.punya.server.backend;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Currency
 *
 */
@Entity
public class Currency implements Serializable, Comparable<Currency> {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	private Long currencyId;
	
	private String type;
	
	private String description;

	
	public Long getCurrencyId() {
		return currencyId;
	}

	public void setCurrencyId(Long currencyId) {
		this.currencyId = currencyId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public int compareTo(Currency c) {
		return getType().compareTo(c.getType());
	}
}
