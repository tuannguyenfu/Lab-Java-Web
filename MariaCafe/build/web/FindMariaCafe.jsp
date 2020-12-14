
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Find Maria's Cafe</title>
        <link rel="stylesheet" href="css/style.css"/>
    </head>
    <body>
        <div class="site-wrapper">
            <jsp:include page="Header.jsp"/>
            <div class="content-wrapper">
                <div class="container">
                    <div class="content-left">
                        <div class="section">
                            <p class="product-name">Find Maria's Cafe</p>
                            <div class="content">
                                <ul>
                                    <li class="item">
                                        <div class="product-tittle">Address and contact:</div>
                                        ${infor.address}
                                        <p><div class="span">Tel:</div><a href="tel:${infor.tel}">${infor.tel}</a></p>
                                        <p><div class="span">Email:</div><a href="mailto:${infor.email}">${infor.email}</a></p>
                                    </li>
                                    <li class="item">
                                        <div class="product-tittle">Opening hours:</div>
                                        ${infor.openingHours}
                                    </li>
                                </ul>
                            </div>
                        </div>
                                    <div class="map"></div>
                    </div>
                    <jsp:include page="ShareBox.jsp"/>
                </div>
            </div>
            <jsp:include page="Footer.jsp"/>
        </div>
    </body>
</html>
