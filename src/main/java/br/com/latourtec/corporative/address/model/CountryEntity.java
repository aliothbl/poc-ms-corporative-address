package br.com.latourtec.corporative.address.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "TB_COUNTRY", schema = "ADDRESS", uniqueConstraints = { @UniqueConstraint(columnNames = { "UUID"})})
@SequenceGenerator(name = "COUNTRY_SEQ", sequenceName = "SQ_TB_COUNTRY", allocationSize = 1, schema = "ADDRESS")
public class CountryEntity implements Serializable {
	
	@Id
	@Column(name = "CD_SEQ_COUNTRY")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "COUNTRY_SEQ")
	private Long id;
	
	@Column(name = "UUID", updatable = false, nullable = false)
	private String uuid;
	
	@Column(name = "NM_NAME", nullable = false)
	private String name;
	
	public Long getId() {
		return id;
	}
	
	public void setId(final Long id) {
		this.id = id;
	}
	
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
	
	@PrePersist
	public void setDefaultProperties() {
		this.uuid = UUID.randomUUID().toString();
	}
}
