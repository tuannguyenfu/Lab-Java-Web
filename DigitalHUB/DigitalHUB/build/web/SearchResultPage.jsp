
<%@page import="javax.naming.Context"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Result Search</title>
        <link href="css/style.css" rel="stylesheet" type="text/css">
    </head>
    <body>
        <div class="container">
            <jsp:include page="Header.jsp"/>
            <jsp:include page="Menu.jsp"/>
            <div class="content">
                <div class="main">                
                    <c:forEach var="i" items="${listResultSearch}">
                        <div style="clear: both;">
                            <br>
                            <div class="tittle">
                                <a href="LoadHomePage?id=${i.id}">      
                                    ${i.title}
                                </a>
                            </div>
                            <div class="image-search">
                                <img src="${imagePath}${i.image}"/>
                            </div>
                            <div class="text-search">
                                ${i.shortDes}
                            </div>
                        </div>              
                    </c:forEach>
                    <div class="padding">
                        <c:if test="${maxPage<1}">
                            <h3>Not Found !!</h3>
                        </c:if>
                        <c:if test="${maxPage>1}">
                            <c:forEach begin="1" end="${maxPage}" var="i">
                                <a class="${i==pageIndex?"active":""}" href="SearchResult?txtPage=${i}&txtSearch=${txtSearch}">${i}</a>
                            </c:forEach>
                        </c:if>
                    </div>
                </div> 
            </div>
            <jsp:include page="Right.jsp"/> 
            <jsp:include page="Footer.jsp"/>
        </div>
    </body>
</html>
