package com.green.base.repository.generic;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import com.green.base.entity.product.meta.information.MetaInformationTypes;
import junit.framework.Assert;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.green.base.entity.ecom.address.BuyerAddress;
import com.green.base.entity.ecom.tax.Tax;
import com.green.base.entity.living.human.related.address.Locality;
import com.green.base.repository.generic.GenericDao;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"/test-root-context.xml"})
@TransactionConfiguration(defaultRollback = false)
public class HibernateGenericDaoTest extends AbstractTransactionalJUnit4SpringContextTests {

    @Inject
    GenericDao genericDao;

    @Test
    @Transactional
    public void should_save_any_entity() throws Exception {
        Tax tax = new Tax();
        tax.setDescription("Govt.Tax");
        tax.setTaxAmount(13);
        tax.setTaxName("India Tax");
        tax.setTaxPercentage(new BigDecimal("10.01"));

        genericDao.save(tax);

        tax = genericDao.find(Tax.class, tax.getId());

        Assert.assertNotNull(tax);
        Assert.assertEquals("India Tax", tax.getTaxName());
    }


    @Test
    @Transactional
    public void should_save_any_entity_with_cascade_save_also() throws Exception {
        BuyerAddress buyerAddress = new BuyerAddress();
        buyerAddress.setLineOne("Line 1");
        buyerAddress.setLineTwo("Line 2");
        buyerAddress.setZip("4000066");
        Locality locality = new Locality();
        locality.setDistinguished("West");
        locality.setLocality("Bandra");
        buyerAddress.setLocality(locality);

        genericDao.save(buyerAddress);

        buyerAddress = genericDao.find(BuyerAddress.class, buyerAddress.getId());

        Assert.assertNotNull(buyerAddress);

        locality = buyerAddress.getLocality();

        Assert.assertNotNull(locality);

        Assert.assertEquals("Bandra", locality.getLocality());

    }

    @Test
    @Transactional
    @Ignore
    public void should_throw_exception_for_unique_constraint() throws Exception {
        try {
            MetaInformationTypes m = new MetaInformationTypes();
            m.setMetaType("Size");
            this.genericDao.save(m);
            MetaInformationTypes m1 = new MetaInformationTypes();
            m1.setMetaType("Size");
            this.genericDao.save(m1);
        } catch (Exception e) {
            return;
        }
        Assert.fail();
    }


    @Test
    @Transactional
    public void should_list_paginated_data_on_entity() throws Exception {
        MetaInformationTypes metaInformationTypes = new MetaInformationTypes();
        metaInformationTypes.setMetaType("Weight1");
        genericDao.save(metaInformationTypes);
        metaInformationTypes = new MetaInformationTypes();
        metaInformationTypes.setMetaType("Weight2");
        genericDao.save(metaInformationTypes);
        metaInformationTypes = new MetaInformationTypes();
        metaInformationTypes.setMetaType("Weight3");
        genericDao.save(metaInformationTypes);
        metaInformationTypes = new MetaInformationTypes();
        metaInformationTypes.setMetaType("Weight4");
        genericDao.save(metaInformationTypes);
        metaInformationTypes = new MetaInformationTypes();
        metaInformationTypes.setMetaType("Weight5");
        genericDao.save(metaInformationTypes);

        List<MetaInformationTypes> list = genericDao.getPaginatedListForEntity(MetaInformationTypes.class, 1, 2);
        Assert.assertEquals("Weight1", list.get(0).getMetaType());
        Assert.assertEquals("Weight2", list.get(1).getMetaType());
        list = genericDao.getPaginatedListForEntity(MetaInformationTypes.class, 2, 2);
        Assert.assertEquals("Weight3", list.get(0).getMetaType());
        Assert.assertEquals("Weight4", list.get(1).getMetaType());

        Assert.assertEquals(2, list.size());

    }


    @Test
    @Transactional
    public void should_fetch_total_count_for_entity() throws Exception {
        MetaInformationTypes metaInformationTypes = new MetaInformationTypes();
        metaInformationTypes.setMetaType("Weight11");
        genericDao.save(metaInformationTypes);
        metaInformationTypes = new MetaInformationTypes();
        metaInformationTypes.setMetaType("Weight21");
        genericDao.save(metaInformationTypes);
        metaInformationTypes = new MetaInformationTypes();
        metaInformationTypes.setMetaType("Weight31");
        genericDao.save(metaInformationTypes);
        metaInformationTypes = new MetaInformationTypes();
        metaInformationTypes.setMetaType("Weight41");
        genericDao.save(metaInformationTypes);
        metaInformationTypes = new MetaInformationTypes();
        metaInformationTypes.setMetaType("Weight51");
        genericDao.save(metaInformationTypes);

        Long count = genericDao.getTotalCountForEntity(MetaInformationTypes.class);


        Assert.assertTrue(count >= 5);

    }


    @Test
    @Transactional
    public void should_fetch_total_count_for_entity_on_like_match() throws Exception {
        MetaInformationTypes metaInformationTypes = new MetaInformationTypes();
        metaInformationTypes.setMetaType("Weight011");
        genericDao.save(metaInformationTypes);
        metaInformationTypes = new MetaInformationTypes();
        metaInformationTypes.setMetaType("Weight021");
        genericDao.save(metaInformationTypes);
        metaInformationTypes = new MetaInformationTypes();
        metaInformationTypes.setMetaType("Weight031");
        genericDao.save(metaInformationTypes);
        metaInformationTypes = new MetaInformationTypes();
        metaInformationTypes.setMetaType("Weight041");
        genericDao.save(metaInformationTypes);
        metaInformationTypes = new MetaInformationTypes();
        metaInformationTypes.setMetaType("Weight051");
        genericDao.save(metaInformationTypes);

        Long count = genericDao.getTotalCountForEntityWithClause(MetaInformationTypes.class, new String[]{"metaType"}, new Serializable[]{"weight0"}, new String[]{"metaType"});


        Assert.assertTrue(count == 5);

    }


    @Test
    @Transactional
    public void should_fetch_total_count_for_entity_on_like_match_with_more_than_one_column() throws Exception {


        Locality locality = new Locality();
        locality.setDistinguished("West");
        locality.setLocality("Bandra");


        genericDao.save(locality);

        locality = new Locality();
        locality.setDistinguished("East");
        locality.setLocality("Bandra");


        genericDao.save(locality);

        Long count = genericDao.getTotalCountForEntityWithClause(Locality.class, new String[]{"distinguished", "locality"}, new Serializable[]{"east", "bandra"}, new String[]{"distinguished", "locality"});


        Assert.assertTrue(count == 1);

    }


    @Test
    @Transactional
    public void should_fetch_total_count_for_entity_on_more_than_one_column() throws Exception {


        Locality locality = new Locality();
        locality.setDistinguished("West");
        locality.setLocality("Dadar");


        genericDao.save(locality);

        locality = new Locality();
        locality.setDistinguished("East");
        locality.setLocality("Dadar");


        genericDao.save(locality);

        Long count = genericDao.getTotalCountForEntityWithClause(Locality.class, new String[]{"distinguished", "locality"}, new Serializable[]{"east", "dadar"}, null);


        Assert.assertTrue(count == 1);

    }


    @Test
    @Transactional
    public void should_list_paginated_data_on_projection_on_column() throws Exception {
        MetaInformationTypes metaInformationTypes = new MetaInformationTypes();
        metaInformationTypes.setMetaType("Weightx121");
        genericDao.save(metaInformationTypes);
        metaInformationTypes = new MetaInformationTypes();
        metaInformationTypes.setMetaType("Weightx221");
        genericDao.save(metaInformationTypes);
        metaInformationTypes = new MetaInformationTypes();
        metaInformationTypes.setMetaType("Weightx321");
        genericDao.save(metaInformationTypes);
        metaInformationTypes = new MetaInformationTypes();
        metaInformationTypes.setMetaType("Weightx421");
        genericDao.save(metaInformationTypes);
        metaInformationTypes = new MetaInformationTypes();
        metaInformationTypes.setMetaType("Weightx521");
        genericDao.save(metaInformationTypes);

        List<MetaInformationTypes> list = genericDao.getPaginatedListForEntityWithClause(MetaInformationTypes.class, new String[]{"metaType"}, new String[]{"weightx"}, new String[]{"metaType"}, 1, 2);
        Assert.assertEquals("Weightx121", list.get(0).getMetaType());
        Assert.assertEquals("Weightx221", list.get(1).getMetaType());
        list = genericDao.getPaginatedListForEntityWithClause(MetaInformationTypes.class, new String[]{"metaType"}, new String[]{"weightx"}, new String[]{"metaType"}, 2, 2);
        Assert.assertEquals("Weightx321", list.get(0).getMetaType());
        Assert.assertEquals("Weightx421", list.get(1).getMetaType());

        Assert.assertEquals(2, list.size());

    }


    @Test
    @Transactional
    public void should_fetch_projection_list_as_provided() throws Exception {
        Locality locality = new Locality();
        locality.setDistinguished("East");
        locality.setLocality("Mulund");
        genericDao.save(locality);
        List<Object> list = genericDao.findProjectionOnEntity(Locality.class, new String[]{"locality"}, new String[]{"locality"}, new String[]{"Mulund"});
        Assert.assertNotNull(list.toString(), list);

    }

   
}
