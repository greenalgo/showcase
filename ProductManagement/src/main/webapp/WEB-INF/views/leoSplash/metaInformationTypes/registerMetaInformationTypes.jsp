<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<div class="left_articles">
				<h2>Register Meta Information Types</h2>
				<p></p>
				<p>This module is for registering a Meta Information i.e. an attribute a product can have e.g. Size,Weight,Color.</p>
			</div>

			
			<f:form method="POST" modelAttribute="metaInformationTypes" action="${pageContext.request.contextPath}/leoSplash/metaInformationTypes/create">
			    <tiles:insertTemplate template="/WEB-INF/views/leoSplash/metaInformationTypes/_metaInformationTypes.jsp" />
			</f:form>
			
								
			