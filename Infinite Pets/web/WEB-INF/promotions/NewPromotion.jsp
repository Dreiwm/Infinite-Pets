<%-- 
    Document   : NewPromotion
    Created on : 7-Apr-2021, 4:41:56 PM
    Author     : Riley
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@include file="../testFiles/header.jsp" %>
        <title>New Promotion</title>
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
                    <a>New Promotion</a>
                </div>
            </div>
            <div class="generalContainer">
                <h1>New Promotion</h1>
                <form action="NewPromotion" method="POST">
                    <h2>Promotion</h2>
                    <!--Promotion Name-->
                    <label for="promoName">Promotion Name:</label>
                    <input type="text" placeholder="Promotion name" name="serviceName" id="promoName">
                    <br/>
                    <!--Promotion Description-->
                    <label for="promoDescription">Promotion Description: </label>
                    <br/>
                    <textarea id="promoDescription" name="promotionDesc" rows="5" cols="30" placeholder="A short descriiption about this promotion."></textarea>
                   
                    <!--Service (I hope only promo per service...)-->
                    <h2>Select Service</h2>
                    <select name="selectService">
                        <c:forEach items="${services}" var="service">
                            <option value="${service}">${service}</option>
                        </c:forEach>
                    </select>
                    
                    <h2>Discount</h2>
                    <!--Discount-->
                    <label for="discPercent">Discount (in percent):</label>
                    <input type="number" placeholder="10" name="discountPercent" id="discPercent" min="1">
                    
                    <br/>
                    
                    <label for="discType">Discount Type:</label>
                    <select name="discType">
                        <option value="type1">Discount type 1</option>
                        <option value="type2">Discount Type 2</option>
                    </select>
                    
                    <br/><br/>
                    <!--Submit/cancel-->
                    <input type="submit" value="Cancel">
                    <!--Cancel -> Promotions-->
                    <input type="submit" value="Add New Promotion">
                    
                </form>
                
                
            </div>
        </div>
        <footer> <%@include file="../testFiles/footer.jsp" %> </footer>
    </body>
</html>
