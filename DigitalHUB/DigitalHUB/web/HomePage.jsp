
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home Page</title>
        <link href="css/style.css" rel="stylesheet" type="text/css" />
    </head>
    <body> 
        <div class="container">
            <jsp:include page="Header.jsp"/>
            <jsp:include page="Menu.jsp"/>
            <div class="content">
                <div class="main">
                    <c:if test="${top1 eq null}">
                        <h3>Not Found !</h3>
                    </c:if>
                    <c:if test="${top1 ne null}">
                        <div class="tittle">
                            ${top1.title}
                        </div>
                        <div class="image">
                            <img src="${imagePath}${top1.image}"/>
                        </div>
                        <div class="text">
                            ${top1.description}
                        </div>
                        <div class="signature">
                            <p>
                            <div class="icon1"></div>
                            <div class="icon2"></div>
                            By ${top1.author} | ${top1.dateConvert}
                            </p>
                        </div>
                    </c:if>
                </div>
            </div>
            <jsp:include page="Right.jsp"/> 
            <jsp:include page="Footer.jsp"/>
        </div>
    </body>
</html>
