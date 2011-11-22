package com.green.ecom.controller.inventory.metainformation;

import com.green.base.entity.product.meta.information.MetaInformationTypes;
import com.green.base.repository.generic.GenericDao;
import com.green.ecom.app.AppConstants;
import com.green.ecom.controller.message.MessageConstants;
import com.green.ecom.service.exception.StaleVersionUpdateException;
import com.green.ecom.service.exception.UniqueEntityException;
import com.green.ecom.service.inventory.metainformation.MetaInformationService;
import com.green.web.spring.mvc.extensions.SpringWebUtil;
import com.green.web.spring.mvc.extensions.flash.FlashMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.validation.Valid;
import java.beans.PropertyEditorSupport;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author gaurav
 *         <p/>
 *         This controller is responsible for crud operations on
 *         MetaInformationTypes entity class
 */
@Controller
@RequestMapping("/leoSplash/metaInformationTypes")
public class MetaInformationTypesController {

    private static final Logger LOGGER = LoggerFactory.getLogger(MetaInformationTypesController.class);
    @Inject
    private GenericDao genericDao;
    @Inject
    private SpringWebUtil springWebUtil;
    @Inject
    private MetaInformationService metaInformationService;

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.setValidator(new Validator() {
            public boolean supports(Class<?> aClass) {
                return MetaInformationTypes.class.equals(aClass) || MetaInformationSearchForm.class.equals(aClass);
            }

            public void validate(Object o, Errors errors) {
                ValidationUtils.rejectIfEmptyOrWhitespace(errors, "metaType", MessageConstants.ERROR_IS_REQUIRED);
            }
        });


		binder.registerCustomEditor(Date.class, new PropertyEditorSupport(){
            public void setAsText(String value) {
        try {
            setValue(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").parse(value));
        } catch(ParseException e) {
            setValue(null);
            LOGGER.error("Problem parsing date with value " + value,e);
        }
    }

      public String getAsText() {
          if(getValue() != null)
                 return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format((Date) getValue());
          return "";
    }
        });

    }

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public MetaInformationTypes _new() {
        LOGGER.info("New Meta information creation request received");
        return new MetaInformationTypes();

    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(@Valid MetaInformationTypes metaInformationTypes,
                         BindingResult result, Model model) {
        try {
            if (result.hasErrors()) {
                return "leoSplash/metaInformationTypes/new";
            }
            LOGGER.info("Saving MetaInformationTypes {}", metaInformationTypes);
            metaInformationTypes.setCreationDate(new Date());
            metaInformationTypes.setLastUpdatedDate(new Date());
            this.metaInformationService.save(metaInformationTypes);
            FlashMap.setSuccessMessage(springWebUtil.getMessageFromResourceBundle(MessageConstants.SUCCESS_CREATION_MESSAGE) + " MetaInformationTypes");
            return "redirect:/leoSplash/metaInformationTypes/show/" + metaInformationTypes.getId();
        } catch (UniqueEntityException e) {
            model.addAttribute("message", "Entity by name " + metaInformationTypes.getMetaType() + " already exists.");
            LOGGER.error("Problem while saving MetaInformationTypes " + e.getMessage(), e);
            return "leoSplash/metaInformationTypes/new";
        } catch (Exception e) {
            LOGGER.error("Problem while saving MetaInformationTypes " + e.getMessage(), e);
        }
        return "internalError";
    }

    @RequestMapping(value = "/show/{id}", method = RequestMethod.GET)
    public String show(@PathVariable Long id, Model model) {
        LOGGER.info("Fetching Meta information type for id creation request received {}", id);

        try {
            if (!getMetaInformationTypesForId(id, model)) return "noResultFound";

            return "leoSplash/metaInformationTypes/show/*";
        } catch (Exception e) {
            LOGGER.error("Problem while fetching MetaInformationTypes {}", e.getMessage(), e);
        }

        return "internalError";
    }

    @RequestMapping(value = "/list/{page}", method = RequestMethod.GET)
    public
    @ResponseBody
    String list(@PathVariable String page, @RequestParam Integer totalPages, @RequestParam String searchMetaType) {
        LOGGER.info("Fetching list for page {} and total pages {}", page, totalPages);
        if (searchMetaType != null) {
            return metaInformationService.listSearchedMetaInformationTypesForPage(searchMetaType, page);
        }
        return metaInformationService.listMetaInformationTypesForPage(page);

    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String listMeta(Model model) {
        int totalPages = metaInformationService.getTotalPagesForMetaInformationTypes();
        LOGGER.info("Total pages found as {}", totalPages);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("metaInformationSearchForm", new MetaInformationSearchForm());
        return "listMetaInformationTypes";

    }


    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public String search(MetaInformationSearchForm metaInformationSearchForm, Model model) {

        LOGGER.info("Searching for MetaInformationTypes {}", metaInformationSearchForm.getMetaType());
        int totalPages = metaInformationService.getTotalPagesForSearchedMetaInformationTypes(metaInformationSearchForm.getMetaType());
        LOGGER.info("Total pages found as {}", totalPages);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("searchMetaType", metaInformationSearchForm.getMetaType());
        return "listMetaInformationTypes";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String edit(@PathVariable Long id, Model model) {
        LOGGER.info("Fetching Meta information type for id edit request received {}", id);

        try {
            if (!getMetaInformationTypesForId(id, model)) return "noResultFound";

            return "leoSplash/metaInformationTypes/edit/";
        } catch (Exception e) {
            LOGGER.error("Problem while fetching MetaInformationTypes {}", e.getMessage(), e);
        }

        return "internalError";
    }


    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    public String update(@Valid MetaInformationTypes metaInformationTypes,
                         BindingResult result, Model model,@PathVariable Long id) {
        MetaInformationTypes metaInformationTypesToBeUpdated;
        try {
            metaInformationTypes.setId(id);
            if (result.hasErrors()) {
                return "leoSplash/metaInformationTypes/edit/" + id;
            }

            LOGGER.info("Updating MetaInformationTypes {}", metaInformationTypes);

            this.metaInformationService.save(metaInformationTypes);
            FlashMap.setSuccessMessage(springWebUtil.getMessageFromResourceBundle(MessageConstants.SUCCESS_UPDATE_MESSAGE) + " MetaInformationTypes");
            return "redirect:/leoSplash/metaInformationTypes/show/" + metaInformationTypes.getId();
        } catch (UniqueEntityException e) {
            model.addAttribute("message", "Entity by name " + metaInformationTypes.getMetaType() + " already exists.");
            LOGGER.error("Problem while updating MetaInformationTypes " + e.getMessage(), e);
            return "leoSplash/metaInformationTypes/edit/" + id;
        }catch (StaleVersionUpdateException e){
            model.addAttribute("message", "Entity by name " + metaInformationTypes.getMetaType() + " was updated while your updation process was going, please restart edit process");
            LOGGER.error("Problem while updating MetaInformationTypes " + e.getMessage(), e);
            return "leoSplash/metaInformationTypes/edit/" + id;
        }catch (Exception e) {
            LOGGER.error("Problem while editing MetaInformationTypes " + e.getMessage(), e);
        }
        return "internalError";
    }


    private boolean getMetaInformationTypesForId(Long id, Model model) throws Exception {
        MetaInformationTypes metaInformationTypes;
        metaInformationTypes = this.genericDao.find(MetaInformationTypes.class, id);
        if (metaInformationTypes == null) {
            LOGGER.info("No result found for id {}", id);
            return false;
        } else
            model.addAttribute("metaInformationTypes", metaInformationTypes);
        return true;
    }


}
