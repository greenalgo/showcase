<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="left_articles">
				<h2>Show Meta Information Types</h2>
				<p></p>
				<p></p>
			</div>

		<f:form modelAttribute="metaInformationTypes">
			
			<fieldset>
			  <table cellspacing="0">
                <tr>
                    <td colspan="2">
                        <c:if test="${not empty message}">
					        <div id="message" class="${message.type}">${message.text}</div>
		  		        </c:if>
                    </td>
                </tr>
			    <tr>
			      <th><f:label path="metaType"><spring:message code="label.meta.information.type.metaType"/></f:label>  </th>
			      <td>${metaInformationTypes.metaType}</td>
			    </tr>
			    
			  </table>
			</fieldset>
				
			   </f:form>
		
			
								
			