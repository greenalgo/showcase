package com.green.base.repository.generic;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * @author gaurav
 */
public interface GenericDao {

    public void save(Object object) throws Exception;

    public <T> void save(Collection<T> objectCollection) throws Exception;

    public <T> T find(Class<T> clazz, Serializable id) throws Exception;

    public void delete(Object object) throws Exception;

    public <T> Collection<T> findAll(Class<T> clazz) throws Exception;

    public <T> T getReference(Class<T> clazz, Object id) throws Exception;

    public <T> T merge(T object) throws Exception;

    public <T> T findFirstWhereCondition(Class<T> clazz,
                                         String conditionColumnName, Serializable conditionColumnValue)
            throws Exception;

    public <T> Collection<T> findAllWhereCondition(Class<T> clazz,
                                                   String conditionColumnName, Serializable conditionColumnValue)
            throws Exception;

    public <T> Collection<T> findCollectionWhereInCondition(Class<T> clazz,
                                                            String conditionColumnName, Serializable... conditionColumnValues)
            throws Exception;

    public <T> T findFirstWhereInCondition(Class<T> clazz,
                                           String conditionColumnName, Serializable... conditionColumnValues)
            throws Exception;

    public <T> Collection<T> findCollectionWithWhereAndJoinCondition(
            Class<T> clazz, String conditionColumnName,
            Serializable conditionColumnValue, String joinRelationName)
            throws Exception;

    public <T> T findFirstWithWhereAndJoinCondition(
            Class<T> clazz, String conditionColumnName,
            Serializable conditionColumnValue, String joinRelationName)
            throws Exception;

    public <T> Long getTotalCountForEntity(Class<T> clazz) throws Exception;

    public <T> List<T> getPaginatedListForEntity(Class<T> clazz, int page, int perPageCount) throws Exception;

    public <T> Long getTotalCountForEntityWithClause(Class<T> clazz, String[] columnNames,
           Serializable[] columnValues, String[] likeColumns) throws Exception;

    public <T> List<T> getPaginatedListForEntityWithClause(Class<T> clazz, String[] columnNames,
           Serializable[] columnValues, String[] likeColumns, int page, int perPageCount) throws Exception;

    public <T> List<Object> findProjectionOnEntity(Class<T> clazz, String[] projectionColumnNames,
                                           String[] conditionColumns, Serializable[] conditionColumnValues) throws Exception;

    //public <T> Collection<T> merge(Collection<T> objectCollection) throws Exception;

    /*public <T> Collection<T> findCollectionWithEagerOnClassWithInnerJoinOnCondition(
             Class<T> clazz, String conditionColumnName,
             Serializable conditionColumnValue, String joinRelationName)
             throws Exception;

     public <T> T findFirstWithEagerOnClassWithInnerJoinOnCondition(
             Class<T> clazz, String conditionColumnName,
             Serializable conditionColumnValue, String joinRelationName)
             throws Exception;






     public <T> Collection<T> multiSelectAll(Class<T> clazz, String columnNamesCsv);

     public <X, Y> Long countDistinctOnRootWhereAllMatch(Class<X> queryClass,
             Class<Y> rootClass, String conditionColumnNamesCsv,
             Serializable... conditionColumnValues);

     public <X, Y> Long countDistinctOnRootWhereAllMatchAndLike(
             Class<X> queryClass, Class<Y> rootClass, String likeColumnName,
             String likeColumnValue, String conditionColumnNamesCsv,
             Serializable... conditionColumnValues);

     public <X, Y> Long countDistinctOnRootWhereAllMatchAndMultiLike(
             Class<X> queryClass, Class<Y> rootClass, String likeColumnNameCsv,
             String[] likeColumnValues, String conditionColumnNamesCsv,
             Serializable... conditionColumnValues);

     public <X, Y> Collection<X> multiSelectWhereAllMatch(Class<X> queryClass,
             Class<Y> rootClass, String columnNamesCsv,
             String conditionColumnNamesCsv,
             Serializable... conditionColumnValues);

     public <X, Y> Collection<X> multiSelectWithLimitWhereAllMatch(
             Class<X> queryClass, Class<Y> rootClass, Integer startLimit,
             Integer maxSize, String columnNamesCsv,
             String conditionColumnNamesCsv,
             Serializable... conditionColumnValues);

     public <X, Y> Collection<X> multiSelectWithLimitWhereAllMatchAndMultiLike(
             Class<X> queryClass, Class<Y> rootClass, Integer startLimit,
             Integer maxSize, String columnNamesCsv, String likeColumnNameCsv,
             String[] likeColumnValues, String conditionColumnNamesCsv,
             Serializable... conditionColumnValues);

     public <X, Y> Collection<X> multiSelectWithLimitWhereAllMatchAndLike(
             Class<X> queryClass, Class<Y> rootClass, Integer startLimit,
             Integer maxSize, String columnNamesCsv, String likeColumnName,
             String likeColumnValue, String conditionColumnNamesCsv,
             Serializable... conditionColumnValues);

     public Collection<Object> executeNamedQuery(String namedQuery, String[] params,
             Object[] values);*/
}
