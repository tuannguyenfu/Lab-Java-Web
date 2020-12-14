
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
                        <div class="section intro">
                            <div class="content">
                                <div class="img">
                                    <a href="IntroControl"><img src="${imagePath}${introduction.picture}"/></a>
                                </div>
                                    <p class="content-title"><a href="IntroControl">${introduction.title}</a></p>
                                ${introduction.shortDescription}
                            </div>
                        </div>
                        <div class="section">
                            <div class="content">
                                <ul>
                                    <c:forEach var="top2" items="${top2}">
                                        <li class="product">
                                            <div class="img"><a href="CakeControl?id=${top2.id}"><img src="${imagePath}${top2.picture}"></a></div>
                                            <div class="product-tittle"><a href="CakeControl?id=${top2.id}">${top2.name}</a></div>
                                            <p>${top2.shortDescription}</p>
                                        </li>
                                    </c:forEach>
                                </ul>
                            </div>
                        </div>
                        <div class="section intro">
                            <div class="content">
                                <div class="content-title">Visit my cafe</div>
                                <br/>
                                <p>${infor.shortDescription}</p>
                                <p>${infor.address}</p>
                                <p>Phone: ${infomation.tel}</p>
                            </div>
                        </div>
                        <div class="section intro">
                            <div class="content">
                                Kind regards<br/>
                                <div class="signature">${infor.signature}</div>
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
