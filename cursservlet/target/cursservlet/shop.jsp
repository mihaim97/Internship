<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<%@ page isELIgnored="false" %>

<body>

<div style="float:right">
    <h3>Your products</h3>
    <p>${userProducts}</p>
    <a href="yourBag">Your bag</a>
</div>

<div>
    <h1>List of products</h1>
    <h2>Cars</h2>
    <table border="1">
      <c:forEach items="${cars}" var="car">
       <tr>
         <td>${car.getName()}</td>
          <td><a href="add-product?product=${car.getName()}">Add</a></td>
       </tr>
      </c:forEach>
    </table>
    </br>
    <h2>PC</h2>
    <table border="1">
      <c:forEach items="${pc}" var="pc">
        <tr>
          <td>${pc.getName()}</td>
          <td><a href="add-product?product=${pc.getName()}">Add</a></td>
        </tr>
      </c:forEach>
    </table>
</div>

</body>

</html>