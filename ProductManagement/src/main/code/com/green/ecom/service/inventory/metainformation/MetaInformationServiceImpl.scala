package com.green.ecom.service.inventory.metainformation

import com.green.base.entity.product.meta.information.MetaInformationTypes
import javax.inject.Inject
import com.green.base.repository.generic.GenericDao
import scala.reflect.BeanProperty
import com.green.ecom.service.logging.Logging
import com.green.ecom.service.exception.UniqueEntityException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.dao.DataIntegrityViolationException
import scala.collection.JavaConversions._
import xml.Node
import com.green.ecom.app.AppConstants
import org.springframework.orm.hibernate3.HibernateOptimisticLockingFailureException
import com.green.ecom.service.{BasicEcomServiceImpl}

/**
 * Created by IntelliJ IDEA.
 * User: gaurav
 * Date: 21/08/11
 * Time: 12:38 PM
 * To change this template use File | Settings | File Templates.
 */

@Service
class MetaInformationServiceImpl extends BasicEcomServiceImpl with Logging with MetaInformationService{

  def listMetaInformationTypesForPage(page: String): String = {
    var pageI: Int = 0;
    var list: List[MetaInformationTypes] = null
    try {
      if (page != null)
        pageI = Integer.parseInt(page);
      list = asScalaBuffer(genericDao.getPaginatedListForEntity(classOf[MetaInformationTypes], pageI, AppConstants.pageCount)).toList;
    } catch {
      case e: NumberFormatException =>
      case e: Exception =>
    }

    return generate(list);
  }

  private def generate(list: List[MetaInformationTypes]): String = {
    var table: Node = <table>
      <tr>
        <th>Id:</th> <th>MetaType:</th>
      </tr>
    </table>
    var child: Node = null;
    list.foreach(meta => {
      child = <tr>
        <td>
          {meta.getId}
        </td> <td>
          {meta.getMetaType}
        </td>
      </tr>
      table = addToModel(child, table)

    })
    return table.toString()
  }

  private def addToModel(child: Node, parent: Node): Node = {


    return parent match {
      case <table>{children@_*}</table> => <table>{children ++ child}</table>
      case other => other;
    }

  }

  def getTotalPagesForMetaInformationTypes: java.lang.Integer = {
    val count = genericDao.getTotalCountForEntity(classOf[MetaInformationTypes])
    return scala.math.ceil(count.doubleValue() / AppConstants.pageCount).asInstanceOf[Int];
  }

  def getTotalPagesForSearchedMetaInformationTypes(metaType: String): java.lang.Integer = {
    val columnValue = new Array[java.io.Serializable](1)
    columnValue(0) = metaType
    val count = genericDao.getTotalCountForEntityWithClause(classOf[MetaInformationTypes], Array("metaType"), columnValue, Array("metaType"))
    return scala.math.ceil(count.doubleValue() / AppConstants.pageCount).asInstanceOf[Int];
  }

  def listSearchedMetaInformationTypesForPage(metaType: String, page: String):String = {
    var pageI: Int = 0;
    var list: List[MetaInformationTypes] = null
    try {
      if (page != null)
        pageI = Integer.parseInt(page);
      val columnValue = new Array[java.io.Serializable](1)
      columnValue(0) = metaType
      list = asScalaBuffer(genericDao.getPaginatedListForEntityWithClause(classOf[MetaInformationTypes], Array("metaType"), columnValue, Array("metaType"), pageI, AppConstants.pageCount)).toList;
    } catch {
      case e: NumberFormatException =>
      case e: Exception =>
    }

    return generate(list);
  }


}