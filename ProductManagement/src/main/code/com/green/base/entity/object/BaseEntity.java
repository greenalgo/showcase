package com.green.base.entity.object;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: gaurav
 * Date: 01/09/11
 * Time: 11:27 AM
 * To change this template use File | Settings | File Templates.
 */
@MappedSuperclass
public class BaseEntity implements Serializable{

    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Long id;

    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    protected Date creationDate;

    @Version
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    protected Date lastUpdatedDate;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    public void setLastUpdatedDate(Date lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }
}
