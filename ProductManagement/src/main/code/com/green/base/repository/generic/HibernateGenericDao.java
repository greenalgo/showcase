package com.green.base.repository.generic;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import javax.inject.Inject;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.green.base.repository.hibernate.detached.criteria.BasicDetachedCriteria;
import com.green.base.utility.GreenUtility;

/**
 * 
 * @author gaurav
 * 
 */
@Repository
@SuppressWarnings("unchecked")
public class HibernateGenericDao implements GenericDao {

	@Inject
	private HibernateTemplate hibernateTemplate;
	@Inject
	private BasicDetachedCriteria basicDetachedCriteria;
	@Inject
	private GreenUtility greenUtility;


	public void save(Object object) throws Exception {
		this.hibernateTemplate.saveOrUpdate(object);

	}


	public <T> void save(Collection<T> objectCollection) throws Exception {
		this.hibernateTemplate.saveOrUpdateAll(objectCollection);
	}


	public <T> T find(Class<T> clazz, Serializable id) throws Exception {
		return this.hibernateTemplate.get(clazz, id);
	}


	public void delete(Object object) throws Exception {
		this.hibernateTemplate.delete(object);
	}


	public <T> Collection<T> findAll(Class<T> clazz) throws Exception {
		return this.hibernateTemplate.find("from " + clazz.getName());
	}


	public <T> T getReference(Class<T> clazz, Object id) throws Exception {
		return this.hibernateTemplate.load(clazz, (Serializable) id);
	}


	public <T> T merge(T object) throws Exception {
		return this.hibernateTemplate.merge(object);
	}


	public <T> T findFirstWhereCondition(Class<T> clazz,
			String conditionColumnName, Serializable conditionColumnValue)
			throws Exception {
		return (T) greenUtility.getTopResult(this.hibernateTemplate
				.findByCriteria(this.basicDetachedCriteria.whereClause(clazz,
						conditionColumnName, conditionColumnValue), 0, 1));
	}


	public <T> Collection<T> findAllWhereCondition(Class<T> clazz,
			String conditionColumnName, Serializable conditionColumnValue)
			throws Exception {
		return greenUtility.getNullSafeList(this.hibernateTemplate
				.findByCriteria(this.basicDetachedCriteria.whereClause(clazz,
						conditionColumnName, conditionColumnValue)));
	}


	public <T> Collection<T> findCollectionWhereInCondition(Class<T> clazz,
			String conditionColumnName, Serializable... conditionColumnValues)
			throws Exception {

		return greenUtility.getNullSafeList(this.hibernateTemplate
				.findByCriteria(this.basicDetachedCriteria.whereInClause(clazz,
						conditionColumnName, conditionColumnValues)));
	}


	public <T> T findFirstWhereInCondition(Class<T> clazz,
			String conditionColumnName, Serializable... conditionColumnValues)
			throws Exception {

		return (T) greenUtility.getTopResult(this.hibernateTemplate
				.findByCriteria(this.basicDetachedCriteria.whereInClause(clazz,
						conditionColumnName, conditionColumnValues), 0, 1));
	}


	public <T> Collection<T> findCollectionWithWhereAndJoinCondition(
			Class<T> clazz, String conditionColumnName,
			Serializable conditionColumnValue, String joinRelationName)
			throws Exception {
		return greenUtility.getNullSafeList(this.hibernateTemplate
				.findByCriteria(this.basicDetachedCriteria
						.whereClauseWithJoinCondition(clazz,
								conditionColumnName, conditionColumnValue,
								joinRelationName)));
	}


	public <T> T findFirstWithWhereAndJoinCondition(Class<T> clazz,
			String conditionColumnName, Serializable conditionColumnValue,
			String joinRelationName) throws Exception {
		return (T) greenUtility.getTopResult(this.hibernateTemplate
				.findByCriteria(this.basicDetachedCriteria
						.whereClauseWithJoinCondition(clazz,
								conditionColumnName, conditionColumnValue,
								joinRelationName), 0, 1));
	}

    public <T> Long getTotalCountForEntity(Class<T> clazz) throws Exception {
        List<Object> list = this.hibernateTemplate.findByCriteria(DetachedCriteria.forClass(clazz).setProjection(Projections.rowCount()));
        if(list == null)
            return 0l;
       return list.get(0) != null ? Long.valueOf(list.get(0).toString()) : 0l;

    }

    public <T> List<T> getPaginatedListForEntity(Class<T> clazz,int page,int perPageCount) throws Exception {
        return greenUtility.getNullSafeList(this.hibernateTemplate.findByCriteria(DetachedCriteria.forClass(clazz),(page - 1)* perPageCount,perPageCount));
    }

    public <T> Long getTotalCountForEntityWithClause(Class<T> clazz, String[] columnNames, Serializable[] columnValues, String[] likeColumns) throws Exception {
        List<Object> list = this.hibernateTemplate.findByCriteria(this.basicDetachedCriteria.whereLikeClause(clazz,columnNames,columnValues,likeColumns).setProjection(Projections.rowCount()));
        if(list == null)
            return 0l;
        return list.get(0) != null ? Long.valueOf(list.get(0).toString()) : 0l;
    }

    public <T> List<T> getPaginatedListForEntityWithClause(Class<T> clazz, String[] columnNames, Serializable[] columnValues, String[] likeColumns, int page, int perPageCount) throws Exception {
        return greenUtility.getNullSafeList(this.hibernateTemplate.findByCriteria(this.basicDetachedCriteria.whereLikeClause(clazz,columnNames,columnValues,likeColumns),(page - 1)* perPageCount,perPageCount));
    }

    public <T> List<Object> findProjectionOnEntity(Class<T> clazz, String[] projectionColumnNames, String[] conditionColumns, Serializable[] conditionColumnValues) throws Exception {
        Projection projections = this.basicDetachedCriteria.makeProjections(projectionColumnNames);
        return this.hibernateTemplate.findByCriteria(this.basicDetachedCriteria.whereClause(clazz,conditionColumns,conditionColumnValues).setProjection(projections));
    }

}
