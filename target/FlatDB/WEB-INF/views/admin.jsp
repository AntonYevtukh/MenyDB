<%--
  Created by IntelliJ IDEA.
  User: Anton
  Date: 14.10.2017
  Time: 11:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<html>
<head>
    <%@ include file="partials/meta.jsp"%>
</head>
<body>
    <%@ include file="partials/header.jsp"%>
    <div class="main_area">
        <div class="content">
            <form class="search_form" action="/admin?action=get&details" method="POST">
                <div class="find">
                    <label class="input_label">Dish</label>
                    <select id="dish" name="id" onchange="this.form.submit()">
                        <option selected value="-1">--Not selected--</option>
                        <c:forEach items="${dishes}" var="dish_item">
                            <option  class="input_text_number" value="${dish_item.id}" ${id == dish_item.id ? 'selected' : ''}>
                                <c:out value="${dish_item.name}"/></option>
                        </c:forEach>
                    </select><br>
                    <label class="input_label">Name</label>
                    <input type="text" name="name" value="${name}">
                    <label class="input_label">Weight</label>
                    <input type="number" name="weight" min="0"
                           value="${weight}"><br>
                    <label class="input_label">Price</label>
                    <input type="number" name="price" min="0" step="0.01"
                           value="<fmt:formatNumber type = "number" pattern="##0.00" value="${price}"/>">
                    <label class="input_label">Discount</label>
                    <input type="number" name="discount" min="0" max="100"
                           value="${discount == null ? '0' : discount}">
                    <input class="button" type="submit" formaction="/admin?action=add" value="Add">
                    <input class="button" type="submit" formaction="/admin?action=update" value="Update">
                    <input class="button" type="submit" formaction="/admin?action=delete" value="Delete">
                    <input class="button" type="submit" formaction="/admin?action=get" value="Refresh">
                    <br>
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
                                <td align="right">
                                    <fmt:formatNumber type = "number" pattern="##0.00" value="${dish.price}"/>
                                </td>
                                <td align="right"><c:out value="${dish.discount}"/>%</td>
                                <td align="right">
                                    <fmt:formatNumber type = "number" pattern="##0.00" value="${dish.getActualPrice()}"/>
                                </td>
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
