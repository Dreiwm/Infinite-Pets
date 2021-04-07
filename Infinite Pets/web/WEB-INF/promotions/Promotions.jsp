<%-- 
    Document   : Promotions
    Created on : 7-Apr-2021, 10:35:05 AM
    Author     : Riley
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Promotions</title>
        <%@include file="../testFiles/header.jsp" %>
    </head>
    <body>
        
        <div class="wrapper">
            <div class="path">
                <div class="link">
                    <a href="">???</a>
                </div>
                <div class="link">
                    <img id="chevronRight" src="assets/img/chevronRight.svg" alt="">
                </div>
                <div class="link">
                    <a>Promotions</a>
                </div>
            </div>
            <div class="generalContainer">
                
                <!--Table of Promotions here-->
                <table>
                    <!--Header--> 
                    <thead>
                        <tr>
                            <th>Promotion Name</th>
                            <th>Promotion Description</th>
                            <th>Action</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${promos}" var="promo">
                            <tr>
                                <td>${promo.getPromotionName()}</td>
                                <td>${promo.getPromoDescription()}</td>
                                <td>
                                    <form action="Promotion" method="GET">
                                        <input type="submit" value="Edit">
                                        <input type="hidden" name="promoId" value="${promo.getPromoID()}">
                                        <input type="hidden" name="action" value="edit">
                                    </form>
                                    <!--Deactivate/activate-->
                                    <form action="Promotions" method="GET">
                                        <!--If true-->
                                        <c:if test="${promo.getActive()}">
                                            <input type="submit" value="Deactivate">
                                            <input type="hidden" name="promoId" value="${promo.getPromoID}">
                                            <input type="hidden" name="action" value="deactivate">
                                        </c:if>
                                            <c:if test="${promo.getActive() == false}">
                                            <input type="submit" value="Activate">
                                            <input type="hidden" name="promoId" value="${promo.getPromoID()}">
                                            <input type="hidden" name="action" value="activate">
                                        </c:if>
                                    </form>
                                </td>
                            </tr>
                        </c:forEach>
                        
                        <!--new promo-->
                        <tr>
                            <td></td>
                            <td></td>
                            <td>
                                <form action="NewPromotion" method="GET">
                                    <input type="submit" value="Add new promotion">
                                </form>
                            </td>
                        </tr>
                        
                    </tbody>
                </table>
            </div>
        </div>
        <footer> <%@include file="../testFiles/footer.jsp" %> </footer>
    </body>
</html>
