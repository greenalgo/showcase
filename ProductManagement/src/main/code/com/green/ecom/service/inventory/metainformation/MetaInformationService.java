package com.green.ecom.service.inventory.metainformation;

import com.green.base.entity.product.meta.information.MetaInformationTypes;
import com.green.ecom.service.BasicEcomService;

/**
 * Created by IntelliJ IDEA.
 * User: gaurav
 * Date: 21/08/11
 * Time: 12:34 PM
 * To change this template use File | Settings | File Templates.
 */
public interface MetaInformationService extends BasicEcomService{

    public String listMetaInformationTypesForPage(String page);
    public Integer getTotalPagesForMetaInformationTypes();
    public Integer getTotalPagesForSearchedMetaInformationTypes(String metaType);
    public String listSearchedMetaInformationTypesForPage(String metaType,String page);


}
