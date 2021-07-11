package br.com.latourtec.corporative.address.api.dto;

import java.io.Serializable;

public class CountryRequest implements Serializable {
	
	private String name;
	
	public String getName() {
		return name;
	}
	
	public void setName(final String name) {
		this.name = name;
	}
}
