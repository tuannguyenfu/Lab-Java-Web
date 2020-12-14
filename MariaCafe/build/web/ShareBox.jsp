
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Share</title>
        <link rel="stylesheet" href="css/style.css"/>
        <link rel="stylesheet" href="css/share.css"/>
    </head>
    <body>
        <div class="content-right">
            <div class="share-box">
                <div class="share-header">Share this page</div>
                <div class="share-content">
                    <c:forEach var="share" items="${share}">
                        <div>
                            <a href="${share.URL}">
                                <img src="${imagePath}${share.icon}"> 
                                Share on ${share.socialNetwork}
                            </a>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </div>
    </body>
</html>
