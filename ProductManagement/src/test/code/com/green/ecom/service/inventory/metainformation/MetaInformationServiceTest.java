package com.green.ecom.service.inventory.metainformation;

import com.green.base.entity.ecom.address.BuyerAddress;
import com.green.base.entity.ecom.tax.Tax;
import com.green.base.entity.living.human.related.address.Locality;
import com.green.base.entity.product.meta.information.MetaInformationTypes;
import com.green.base.repository.generic.GenericDao;
import com.green.ecom.service.exception.UniqueEntityException;
import junit.framework.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.ExpectedException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.math.BigDecimal;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration( { "/test-root-context.xml" })
@TransactionConfiguration(defaultRollback=false)
public class MetaInformationServiceTest extends AbstractTransactionalJUnit4SpringContextTests{

	@Inject MetaInformationService metaInformationService;
    @Inject GenericDao genericDao;


	@Test
	@Transactional
	public void should_save_unique_meta_information_type_entity() throws Exception {
		MetaInformationTypes metaInformationTypes = new MetaInformationTypes();
        metaInformationTypes.setMetaType("Weight");

		metaInformationService.save(metaInformationTypes);

		Assert.assertNotNull(metaInformationTypes.getId());

        genericDao.delete(metaInformationTypes);

	}


    @Test
	@Transactional
    @ExpectedException(value = Exception.class)
	public void should_through_unique_exception_on_duplicate_meta_information_type_entity() throws Exception {
		MetaInformationTypes metaInformationTypes = new MetaInformationTypes();
        metaInformationTypes.setMetaType("Weight");
        metaInformationService.save(metaInformationTypes);
        Assert.assertNotNull(metaInformationTypes.getId());
        metaInformationTypes = new MetaInformationTypes();
        metaInformationTypes.setMetaType("Weight");
        metaInformationService.save(metaInformationTypes);

	}

    



}
