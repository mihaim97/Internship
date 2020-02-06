<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<%@ page isELIgnored="false" %>

<body>

<div style="float:right">
    <h3>Your products</h3>
    <p>${userProducts}</p>
</div>

<div>
    <h1>List of products</h1>
    <h2>Cars</h2>
    <table>
      <c:forEach items="${cars}" var="car">
       <tr>
         <td>${car}</td>
       </tr>
      </c:forEach>
    </table>
    </br>
    <h2>PC</h2>
    <table>
      <c:forEach items="${pc}" var="pc">
        <tr>
          <td>${pc}</td>
          <td><a href="add-product?product=${pc}">Add</a></td>
        </tr>
      </c:forEach>
    </table>
</div>

</body>

</html>