

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Header</title>
        <link rel="stylesheet" href="css/style.css"/>
        <link rel="stylesheet" href="css/header.css"/>
    </head>
    <body>
        <div class="header-wrapper">
            <div class="container">
                <div class="title-wrapper">
                    <div class="title">Maria Bagnarelli's Cafe</div>
                    <div class="subtitle">Welcome to my website</div>
                </div>
                <div class="navbar">
                    <div class="container">
                        <ul class="nav" id="menuBar">
                            <li><a class="${activeMenu}"  href="HomeControl">Home</a></li>
                            <li><a class="${activeList}" href="ListCakeControl">About my Cakes</a></li>
                            <li><a class="${activeInfor}" href="InforControl">Find Maria's Cafe</a></li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
