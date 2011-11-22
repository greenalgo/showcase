package com.green.base.entity.product.meta.information;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * 
 * @author gaurav
 *
 * Defines different instances/values a meta information type can have
 * eg: Size - S,M,L,XL
 *     Weight -  100lbs,250lbs.....
 *     
 * Note: To avoid join with types we will have redundant column for type    
 * 
 */
@Entity
public class MetaInformation implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6210093973701218380L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	private MetaInformationTypes metaInformationTypes;
	
	private String metaInformationTypeValue;
	
	private String metaInformationType;//redundant to avoid join in some use cases eg: product display on ui

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public MetaInformationTypes getMetaInformationTypes() {
		return metaInformationTypes;
	}

	public void setMetaInformationTypes(MetaInformationTypes metaInformationTypes) {
		this.metaInformationTypes = metaInformationTypes;
	}

	public String getMetaInformationTypeValue() {
		return metaInformationTypeValue;
	}

	public void setMetaInformationTypeValue(String metaInformationTypeValue) {
		this.metaInformationTypeValue = metaInformationTypeValue;
	}

	public String getMetaInformationType() {
		return metaInformationType;
	}

	public void setMetaInformationType(String metaInformationType) {
		this.metaInformationType = metaInformationType;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof MetaInformation)) {
			return false;
		}
		MetaInformation other = (MetaInformation) obj;
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		return true;
	}
	
	
	

}
