package com.green.ecom.service;

import com.green.base.repository.generic.GenericDao;
import com.green.ecom.service.exception.StaleVersionUpdateException;
import com.green.ecom.service.exception.UniqueEntityException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.orm.hibernate3.HibernateOptimisticLockingFailureException;

import javax.inject.Inject;

/**
 * Created by IntelliJ IDEA.
 * User: gaurav
 * Date: 01/09/11
 * Time: 4:28 PM
 * To change this template use File | Settings | File Templates.
 */
public class BasicEcomServiceImpl implements BasicEcomService{
    @Inject protected GenericDao genericDao;

    public void save(Object entity) throws Exception {
        try {
      genericDao.save(entity);
    } catch (DataIntegrityViolationException e){

        throw new UniqueEntityException("Problem with saving entity " + entity, e);
     } catch(HibernateOptimisticLockingFailureException e){

         throw new StaleVersionUpdateException("Problem with saving entity " + entity,e);
      }

    }
}
