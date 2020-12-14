
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>About Cakes</title>
        <link rel="stylesheet" href="css/style.css"/>
    </head>
    <body>
        <div class="site-wrapper">
            <jsp:include page="Header.jsp"/>
            <div class="content-wrapper">
                <div class="container">
                    <div class="content-left">
                        <ul class="container-list">
                            <c:forEach var="i" items="${cakes}">
                                <li class="each-product">
                                    <div class="content">
                                        <div class="image">
                                            <a href="CakeControl?id=${i.id}"><img src="${imagePath}${i.picture}"></a>
                                        </div>
                                        <p class="product-tittle"><a href="CakeControl?id=${i.id}">${i.name}</a></p>
                                            ${i.shortDescription}
                                    </div>
                                </li>
                            </c:forEach>
                        </ul>
                        <div class="page" id="page">
                            <p><c:forEach begin="1" end="${maxPage}" var="i">
                                <a class="${i==pageIndex?"active":""}" href="ListCakeControl?txtPage=${i}">${i}</a>
                            </c:forEach></p>
                        </div>
                    </div>
                    <jsp:include page="ShareBox.jsp"/>
                </div>
            </div>
            <jsp:include page="Footer.jsp"/>
        </div>
    </body>
</html>
