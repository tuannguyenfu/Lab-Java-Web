
<%@page import="javax.naming.Context"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Result Search</title>
        <link href="css/style.css" rel="stylesheet" type="text/css">

    </head>

    <body>
        <div class="container">
            <jsp:include page="Header.jsp"/>
            <div class="content">
                <div class="main">  
                    <c:forEach items="${listSearch}" var="s">
                        <div class="listSearch">
                            <div class="tittle" >
                                <a href="detailController?id=${s.id}">      
                                    ${s.title}
                                </a>
                            </div>
                            <div>
                                <div class="image_search">
                                    <img src="images/${s.image}" alt=""/>
                                </div>
                                <div class="text">
                                    ${s.shortDes}
                                    </div>
                            </div>
                        </div>
                    </c:forEach>
                    <div class="paging">
                        <h1>${message}</h1>
                        <c:if test="${message == null}">
                            <c:forEach begin="1" end="${pageCount}" var="i">
<!--                                chuyền đc index = i về và lưu lại txtSearch-->
<!--khi click vao nut go thi nó se lay đc txtSearch về nhưng lây xog về đẩy du lieu lên jsp thi nó da mât r
khi mk load du lieu lên thi mk đay them txtSearh lên để mk lưu no lại
lây về xog lại đẩy lên theo kiểu quay v-->
                                <a class="${i == pageIndex ? 'active':''}" href="searchController?pageIndex=${i}&txtSearch=${search}">
                                    ${i}
                                </a>
                            </c:forEach>
                        </c:if>
                    </div>
                </div> 
                <jsp:include page="Right.jsp"/> 
            </div>
            <jsp:include page="Footer.jsp"/>
        </div>

    </body>
</html>
