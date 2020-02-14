<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<%@ page isELIgnored="false" %>

<html>
    <body>
    <h1>Your products<h1/>
    <table border="1">
      <c:forEach items="${products}" var="prod">
       <tr>
         <td>${prod}</td>
       </tr>
      </c:forEach>
    </table>
    <a href="buy">Buy</a>

    <h1>Your orders</h1>
     <c:forEach items="${orders}" var="order">
        <h3>Order id: ${order.getId()}</h3>
        <table border="1">
         <tr>
           <c:forEach items="${order.getOrderInfo()}" var="orderInfo">
             <td>${orderInfo.getProduct().getName()}</td>
           </c:forEach>
         </tr>
       </table>
     </c:forEach>


    </body>
</html>