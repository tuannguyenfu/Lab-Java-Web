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
    </head>
    <body>
         <div class="right">
                <div class="newst">
                    <div class="titleNews">
                        <span>Digital News</span>
                    </div>
                    <div class="contentNews">
                        ${shortDes}
                    </div>
                </div>
                <div class="newst">
                    <div class="titleNews">
                        <span>Search</span><br>
                    </div>
                    <form action="SearchResult" method="post" name="searchForm">
                        <input type="text" name="txtSearch" id="txtSearch" value="" size="15" class="searchBox" required>
                        <input type="submit" name="btnGo" value="Go" id="btnGo" class="searchButton">
                    </form>                        
                </div>
                <div class="newst">
                    <div class="titleNews">
                        <span>Last Articles</span><br>
                    </div>
                    <c:forEach var="i" items="${list5next}">
                        <div class="lastArticles">
                            <a href="LoadHomePage?id=${i.id}"> ${i.title}</a>
                        </div>
                    </c:forEach>
                </div>
            </div>    
    </body>
</html>
