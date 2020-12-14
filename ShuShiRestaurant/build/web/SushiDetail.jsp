
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Detail</title>
        <link rel="stylesheet" href="css/style.css"/>
    </head>
    <body>
        <div class="site-wrapper">
            <jsp:include page="Header.jsp"/>
            <div class="content-wrapper">
                <div class="container">
                    <div class="content-left">
                        <div class="section cake">
                            <p class="product-name">${sushi.name}</p>
                            <div class="content">
                                <div class="cake-img">
                                    <img src="${sushi.photoPath}">
                                </div>
                                ${sushi.fullDescription}  
                                <br>
                                <br>
                                <p><div class="au">Author:<br>${sushi.author}</div></p>
                                <p><div class="au">Date created:<br>${sushi.dateCreated}</div></p>
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
