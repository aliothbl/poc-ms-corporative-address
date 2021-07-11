package br.com.latourtec.corporative.address.api.dto;

import org.springframework.hateoas.RepresentationModel;

public class CountryResponse extends RepresentationModel<CountryResponse> {
	
	private String uuid;
	private String name;
	
	public String getUuid() {
		return uuid;
	}
	
	public void setUuid(final String uuid) {
		this.uuid = uuid;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(final String name) {
		this.name = name;
	}
}
