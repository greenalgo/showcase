<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script type="text/javascript">

   $(document).ready(

           function(){


               $('#loadingDiv')
                   .hide()  // hide it initially
                   .ajaxStart(function() {
                       $(this).show();
                   })
                   .ajaxStop(function() {
                       $(this).hide();
                   })
               ;
               $("#firstPage").click( function() {
                      if($("#currentPage").val() == 1){
                          return;
                      }
                      $("#currentPage").val(1);
                      get_data()
                  }
               );
               $("#previousPage").click( function() {
                      if($("#currentPage").val() == 1){
                          return;
                      }
                      $("#currentPage").val(parseInt($("#currentPage").val()) - 1);
                      get_data()
                  }
               );
               $("#nextPage").click( function() {
                      if($("#totalPages").val() == $("#currentPage").val()){
                          return;
                      }
                      $("#currentPage").val(parseInt($("#currentPage").val()) + 1);
                      get_data()
                  }
               );
               $("#lastPage").click( function() {
                      if($("#totalPages").val() == $("#currentPage").val()){
                          return;
                      }
                      $("#currentPage").val($("#totalPages").val());
                      get_data()
                  }
               );
               get_data()
           }


   );

   function get_data(){
    var page =  $("#currentPage").val()
    var url =  "${pageContext.request.contextPath}/leoSplash/metaInformationTypes/list/" + page;

    $.ajax({
        type: "GET",
        data: {totalPages:$("#totalPages").val(),searchMetaType:$("#searchMetaType").val()},
        url:url,
        success:function(html){
            $('#table').html(html);
            $('#pageOf').html("<span>" + "Page " + $("#currentPage").val() + " of " + $("#totalPages").val() + ".</span>")
        }
    });
   }

</script>
<div class="left_articles">
				<h2>List Meta Information Types</h2>
				<p></p>
				<p></p>
			</div>
         <fieldset>
        <f:form modelAttribute="metaInformationSearchForm" method="GET" action="${pageContext.request.contextPath}/leoSplash/metaInformationTypes/search">
             <f:input path="metaType" size="40"/> <br />
            <input type="submit" name="Search" value="Search">
        </f:form>
            <br />
		<f:form modelAttribute="metaInformationTypes">
			

              <div id="loadingDiv"><span>Loading ..... Please Wait....</span></div>
              <br />
              <div id="table"></div>
              <br />
              <div id="pageOf"></div>
              <br />
              <div id="navigators">
               <a href="#" id="firstPage"> << </a> |
                  <a href="#" id="previousPage"> < </a> |
                  <a href="#" id="nextPage"> > </a> |
                  <a href="#" id="lastPage"> >> </a>
              </div>
              <input type="hidden" id="currentPage" name="currentPage" value="1">
              <input type="hidden" id="totalPages" name="totalPages" value="${totalPages}">
              <input type="hidden" id="searchMetaType" name="searchMetaType" value="${searchMetaType}">
			</fieldset>

				
			   </f:form>
		
			
								
			