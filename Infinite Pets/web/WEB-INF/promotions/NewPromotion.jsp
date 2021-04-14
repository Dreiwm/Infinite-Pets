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
                    <input type="text" placeholder="Promotion name" name="promotionName" id="promoName">
                    <br/>
                    <!--Promotion Description-->
                    <label for="promoDescription">Promotion Description: </label>
                    <br/>
                    <textarea id="promoDescription" name="promotionDesc" rows="5" cols="30" placeholder="A short descriiption about this promotion."></textarea>
                   
                    <h3>Promotion Date</h3>
                    <input type="date" placeholder="YYYY-MM-DD" name="startDate">
                    to 
                    <input type="date" placeholder="YYYY-MM-DD" name="endDate">
                    <!--Service (I hope only promo per service...)-->
                    <h2>Select Service</h2>
                    <select name="selectService">
                        <!--
                        <c:forEach items="${services}" var="service">
                            <option value="${service.getServiceID()}">${service}</option>
                        </c:forEach> 
                        -->
                        <option value="1">Service 1</option>
                        <option value="2">Service 2</option>
                    </select>
                    
                    <h2>Discount</h2>
                    <!--Discount-->
                    <label for="discPercent">Discount (in percent):</label>
                    <input type="number" placeholder="10" name="discountPercent" id="discPercent" min="1">
                    
                    <br/>
                    
                    <label for="discType">Discount Type:</label>
                    <select name="discType">
                        <option value="s" selected="true">Single Use</option>
                        <option value="i">Infinite Use</option>
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
