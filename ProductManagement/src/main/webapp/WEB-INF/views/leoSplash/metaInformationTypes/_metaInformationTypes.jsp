<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<fieldset>
			  <table cellspacing="0">
                <tr>
                    <td colspan="2">
                        <c:if test="${not empty message}">
					        <div id="message">${message}</div>
		  		        </c:if>
                    </td>
                </tr>
			    <tr>
			      <th><f:label path="metaType"><spring:message code="label.meta.information.type.metaType"/> * </f:label>  </th>
			      <td>
			          <f:input path="metaType" size="40"/><br />
			          <f:errors path="metaType" cssClass="error"/>
			      </td>
			    </tr>
			     <tr>
			      
			      <td>
			          <input type="submit" name="Submit" value="Submit">
			         
			      </td>
			    </tr>
			  </table>
                <f:hidden path="creationDate"/>
                <f:hidden path="lastUpdatedDate"/>
			</fieldset>

			