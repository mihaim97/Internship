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
    </body>
</html>