
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home</title>
        <link rel="stylesheet" href="css/style.css"/>
    </head>
    <body>
        <div class="site-wrapper">
            <jsp:include page="Header.jsp"/>
            <div class="content-wrapper">
                <div class="container">
                    <div class="content-left">
                        <div class="section">
                            <img class="default-image">
                        </div>
                        <c:forEach items="${listSushi1}" var="i">
                            <div class="section intro">
                                <div class="content">
                                    <p class="content-title"><a href="DetailSushi?sushiId=${i.id}">${i.name}</a></p>
                                    <div class="img">
                                        <a href="DetailSushi?sushiId=${i.id}"><img src="${i.photoPath}"/></a>
                                    </div>
                                    ${i.description}
                                </div>
                            </div>  
                        </c:forEach>

                        <div class="page">
                            <p>
                                <c:forEach begin="1" end="${maxPage}" var="i">
                                    <a class="${i==pageIndex?"active":""}" href="HomeControl?pageIndex=${i}">${i}</a>
                                </c:forEach> 
                            </p>
                        </div>

                    </div>
                    <jsp:include page="ShareBox.jsp"></jsp:include>
                    </div>
                </div>
            <jsp:include page="Footer.jsp"></jsp:include>

        </div>
    </body>
</html>
