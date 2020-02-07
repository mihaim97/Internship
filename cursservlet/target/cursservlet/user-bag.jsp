<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<%@ page isELIgnored="false" %>

<html>
    <body>
    <table border="1">
      <c:forEach items="${products}" var="prod">
       <tr>
         <td>${prod}</td>
       </tr>
      </c:forEach>
    </table>
    </body>
<html>