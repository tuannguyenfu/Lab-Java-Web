

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Menu and Price list</title>
        <link rel="stylesheet" href="css/style.css"/>
        <link rel="stylesheet" href="css/menu.css"/>
    </head>
    <body>

        <div class="site-wrapper">
            <jsp:include page="Header.jsp"/>
            <div class="content-wrapper">
                <div class="container">
                    <div class="content-left">
                        <div class="left-side col-9">
                            <div class="">
                                <h1 class="text-page-heading">Menu and Price list</h1>
                            </div>

                            <div class="list-of-menu">
                                <c:forEach items="${listMenu}" var="m">
                                    <div class="menu-wrapper">
                                        <table class="menu-table">
                                            <tr class="heading-row">
                                                <td class="heading-row__item align-left">${m.name}</td>
                                                <td class="heading-row__item align-right">Price</td>
                                            </tr>
                                            <tr class="data-row">
                                                <td class="data-row__item align-left">${m.shortDescription}</td>
                                                <td class="data-row__item align-right">€${m.price}</td>
                                            </tr>
                                        </table>
                                        <p>
                                            ${m.fullDescription} 
                                        </p>
                                    </div>
                                </c:forEach>
                                <div class="page">
                                    <p>
                                        <c:forEach begin="1" end="${maxPage}" var="i">
                                            <a class="${i==pageIndex?"active":""}" href="MenuAndListPrice?pageIndex=${i}">${i}</a>
                                        </c:forEach> 
                                    </p>
                                </div>
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
