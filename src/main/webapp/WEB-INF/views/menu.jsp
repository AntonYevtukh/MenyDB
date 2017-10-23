<%@ page import="yevtukh.anton.model.District" %><%--
  Created by IntelliJ IDEA.
  User: Anton
  Date: 14.10.2017
  Time: 11:39
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<html>
<head>
    <%@ include file="partials/meta.jsp"%>
</head>
<body>
    <%@ include file="partials/header.jsp"%>
    <div class="main_area">
        <div class="content">
            <form class="search_form" method="POST">
                <div class="find">
                    <label class="input_label">Price From</label>
                    <input class="input_number" type="number" name="from_price" min="0" value="${from_price == null ? '0' : from_price}">
                    <label class="input_label">Price To</label>
                    <input class="input_number" type="number" name="to_price" min="0" value="${to_price == null ? '100' : to_price}"><br>
                    <label class="input_label">Summary Weight</label>
                    <input class="input_number" type="number" name="sum_weight" value="${sum_weight == null ? '1000' : sum_weight}">
                    <input class="checkbox" type="checkbox" name="discount" value="discount" ${discount ? 'checked' : ''}>
                    <label class="input_label">With Discount</label><br>
                    <input class="button" type="submit" formaction="/menu?action=get&select_all" value="Find All">
                    <input class="button" type="submit" formaction="/menu?action=get" value="Find">
                    <input class="button" type="submit" formaction="/menu?action=get&select_random" value="Random"><br>
                </div>
            </form>
            <c:if test="${success_message != null}">
                <span class="success"><c:out value="${success_message}"/></span>
            </c:if>

            <c:if test="${error_message != null}">
                <span class="error"><c:out value="${error_message}"/></span>
            </c:if>
            <c:if test="${dishes != null}">
                <div class="table_container">

                    <table border="1">
                        <tr>
                            <th width="40%">Name</th>
                            <th width="15%">Weight</th>
                            <th width="15%">Price</th>
                            <th width="15%">Discount</th>
                            <th width="15%">Actual Price</th>
                        </tr>
                        <c:forEach items="${dishes}" var="dish">
                            <tr>
                                <td><c:out value="${dish.name}"/></td>
                                <td align="right"><c:out value="${dish.weight}"/></td>
                                <td align="right"><c:out value="${dish.price}"/></td>
                                <td align="right"><c:out value="${dish.discount}"/>%</td>
                                <td align="right"><c:out value="${dish.getActualPrice()}"/></td>
                            </tr>
                        </c:forEach>
                    </table>
                </div>
            </c:if>
        </div>
    </div>
    <%@ include file="partials/footer.jsp"%>
</body>
</html>
