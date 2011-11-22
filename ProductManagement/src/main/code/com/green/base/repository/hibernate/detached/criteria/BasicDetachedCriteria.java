package com.green.base.repository.hibernate.detached.criteria;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.*;
import org.springframework.stereotype.Component;

@Component
public class BasicDetachedCriteria{

	public <T> DetachedCriteria whereClause(Class<T> clazz,String conditionColumnName,Serializable conditionColumnValue){
		return DetachedCriteria.forClass(clazz).add(Restrictions.eq(conditionColumnName, conditionColumnValue));
	}

	public <T> DetachedCriteria whereInClause(Class<T> clazz,
			String conditionColumnName, Serializable...conditionColumnValues) {
		return DetachedCriteria.forClass(clazz).add(Restrictions.in(conditionColumnName, conditionColumnValues));
	}
	
	public <T> DetachedCriteria whereClauseWithJoinCondition(
			Class<T> clazz, String conditionColumnName,
			Serializable conditionColumnValue, String joinRelationName){
		return whereClause(clazz, conditionColumnName, conditionColumnValue).setFetchMode(joinRelationName, FetchMode.JOIN);
	}

    public <T> DetachedCriteria whereLikeClause(Class<T> clazz,
			String[] conditionColumnNames, Serializable[] conditionColumnValues,String[] likeColumnNames) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(clazz);
        List<String> likeColumnNamesList = likeColumnNames != null ? Arrays.asList(likeColumnNames) : Collections.<String>emptyList();
        if(conditionColumnNames.length != conditionColumnValues.length){
          throw new IllegalArgumentException("Condition column name count does not match column values count.");
        }
        for(int i =0; i < conditionColumnNames.length;++i){
           if(likeColumnNamesList.contains(conditionColumnNames[i]))
            detachedCriteria = detachedCriteria.add(Restrictions.like(conditionColumnNames[i],"%" + conditionColumnValues[i] + "%"));
           else
            detachedCriteria = detachedCriteria.add(Restrictions.eq(conditionColumnNames[i],conditionColumnValues[i]));
        }
		return detachedCriteria;
	}


    public <T> DetachedCriteria whereClause(Class<T> clazz,String[] conditionColumnNames,Serializable[] conditionColumnValues){
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(clazz);
        if(conditionColumnNames.length != conditionColumnValues.length){
          throw new IllegalArgumentException("Condition column name count does not match column values count.");
        }
        for(int i=0; i < conditionColumnNames.length; ++i){
           detachedCriteria.add(Restrictions.eq(conditionColumnNames[i],conditionColumnValues[i]));
        }
		return detachedCriteria;
	}

    public Projection makeProjections(String[] projectionColumnNames){
        ProjectionList projectionList = Projections.projectionList();
        for(String column : projectionColumnNames){
            projectionList.add(Projections.property(column));
        }

        return projectionList;
    }

}
