<%-- 
    Document   : footer
    Created on : Mar 10, 2021, 12:06:05 AM
    Author     : Chris
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>

    
    <title>Footer</title>
    </head>
    <body>
        <style>
            footer {
                position: fixed;
                left: 0;
                bottom: 0;
                width: 100%;
            }
        </style>
    <footer>
        <div class="footer">
            <!-- Left side of the footer -->
            <div id="footerLeft">
                <div id="footerItem">
                    <h4>
                        Contact Us
                    </h4>
                </div>
                <div id="footerItem">
                    <a href="tel:4039889244">(403)988-9244</a>
                </div>
                <div id="footerItem">
                    <a href="mailto:infinitepetcare@gmail.com">infinitepetcare@gmail.com</a>
                </div>
                <div id="footerItem">
                    <a href="https://www.facebook.com/Infinite-Pets-1866961236871093/">
                        <img id="facebook" src="assets/img/facebook.svg" alt="">
                    </a>
                </div>
                <div id="footerItem">
                    <a href="https://www.instagram.com/infinite.pets/">
                        <img id="instagram" src="assets/img/instagram.svg" alt="">
                    </a>
                </div>
            </div>
            <!-- Right side of the footer -->
            <div id="footerRight">
                <div id="footerItem">
                    <h4>Subscribe to our newsletter!</h4>
                </div>
                <div id="footerItem">
                    <p>Receive notifications about upcoming events, new services, recommended products and more!</p>
                </div>
                <div id="footerItem">
                    <form action="">
                        <input name="emailNewsletter" type="text">
                        <button type="submit">Subscribe</button>
                    </form>
                </div>
            </div>
        </div>
    </footer>
    </body>
</html>
