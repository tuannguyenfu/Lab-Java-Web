

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Introduction</title>
        <link rel="stylesheet" href="css/style.css"/>
    </head>
    <body>
        <div class="site-wrapper">
            <jsp:include page="Header.jsp"/>
            <div class="content-wrapper">
                <div class="container">
                    <div class="content-left">
                        <div class="section cake">
                            <p class="product-name">${introduction.title}</p>
                            <div class="content">
                                <div class="cake-img">
                                    <img src="${imagePath}${introduction.picture}">
                                </div>
                                    ${introduction.detailDescription}
                            </div>
                        </div>
                    </div>
                    <jsp:include page="ShareBox.jsp"/>
                </div>
            </div>
            <jsp:include page="Footer.jsp"/>
        </div>
    </body>
</html>
