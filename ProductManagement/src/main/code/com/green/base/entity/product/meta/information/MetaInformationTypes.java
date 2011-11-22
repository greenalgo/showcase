package com.green.base.entity.product.meta.information;

import com.green.base.entity.object.BaseEntity;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * 
 * @author gaurav
 *
 * Defines different meta information that a product can have
 * eg: Size, Weight
 * 
 */
@Entity
public class MetaInformationTypes extends BaseEntity implements Serializable{


	@OneToMany
	private Collection<MetaInformation> metaInformations;
	
	@NotNull @Column(unique = true)
	private String metaType;

	public Collection<MetaInformation> getMetaInformations() {
		return metaInformations;
	}

	public void setMetaInformations(Collection<MetaInformation> metaInformations) {
		this.metaInformations = metaInformations;
	}

	public String getMetaType() {
		return metaType;
	}

	public void setMetaType(String metaType) {
		this.metaType = metaType;
	}
	
	

}
