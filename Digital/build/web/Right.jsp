<%-- 
    Document   : Right
    Created on : Apr 11, 2019, 8:42:53 PM
    Author     : Hoan toan hanh phuc 
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Right</title>
        <link href="css/right.css" rel="stylesheet" type="text/css" />
        <link href="css/style.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <div class="right">
            <div class="newst">
                <div class="tittle">
                    Digital News
                </div>
                <div class="text">
                    ${mostRecent.shortDes}
                </div>
            </div>
            <div class="newst">
                <div class="tittle">
                    Search
                </div>
                
                <div class="formSearch">
                    <form action="searchController" >
                        <input class="searchBox" type="text" name="txtSearch" size="15" required>
                        <input class="searchButton" type="submit" name="btnGo" value="Go">
                    </form>                
                </div>
            </div>
            <div class="newst">
                <div class="tittle">
                    <span>Last Articles</span><br>
                </div>
                <c:forEach items="${fiveRecent}" var="i">
                    <div class="lastArticles">
                        <a class="text" href="detailController?id=${i.id}">${i.title}</a>
                    </div>
                </c:forEach>
            </div>
        </div>    
    </body>
</html>
