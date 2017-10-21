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
    <%@ include file="meta.jsp"%>
</head>
<body>
    <%@ include file="header.jsp"%>
    <div class="main_area">
        <div class="content">
            <h2>${db_worker.getDB_CONNECTION()}</h2>
            <h2>${db_worker.getDB_USER()}</h2>
            <h2>${db_worker.getDB_PASSWORD()}</h2>
            <form class="search_form" method="POST">
                <div class="find">
                    <label class="input_label">District</label>
                    <select name="select_district">
                        <option selected value>Any</option>
                        <c:forEach items="${districts}" var="district">
                            <option value="${district}" ${select_district == district ? 'selected' : ''}><c:out value="${district}"/></option>
                        </c:forEach>
                    </select>
                    <label class="input_label">Address</label>
                    <input type="text" name="select_address" value="${select_address}"><br>
                    <label class="input_label">Rooms From</label>
                    <input type="number" name="from_rooms" value="${from_rooms}">
                    <label class="input_label">Rooms To</label>
                    <input type="number" name="to_rooms" value="${to_rooms}"><br>
                    <label class="input_label">Area From</label>
                    <input type="number" name="from_area" value="${from_area}">
                    <label class="input_label">Area To</label>
                    <input type="number" name="to_area" value="${to_area}"><br>
                    <label class="input_label">Price From</label>
                    <input type="number" name="from_price" value="${from_price}">
                    <label class="input_label">Price To</label>
                    <input type="number" name="to_price" value="${to_price}"><br>
                    <input class="button" type="submit" formaction="/flats?action=get" value="Find"><br>
                </div>
                <div class="add">
                    <label class="input_label">District</label>
                    <select name="insert_district">
                        <c:forEach items="${districts}" var="district">
                            <option value="${district}" ${district == insert_district ? 'selected' : ''}><c:out value="${district}"/></option>
                        </c:forEach>
                    </select>
                    <label class="input_label">Address</label>
                    <input type="text" name="insert_address" value="${insert_address}"><br>
                    <label class="input_label">Rooms</label>
                    <input type="number" name="rooms" value="${rooms}">
                    <label class="input_label">Area</label>
                    <input type="number" name="area" value="${area}"><br>
                    <label class="input_label">Price</label>
                    <input type="number" name="price" value="${price}"><br>
                    <input class="button" type="submit" formaction="/flats?action=add" value="Add"><br>
                </div>
            </form>
            <c:if test="${success_message != null}">
                <span class="success"><c:out value="${success_message}"/></span>
            </c:if>

            <c:if test="${error_message != null}">
                <span class="error"><c:out value="${error_message}"/></span>
            </c:if>
            <c:if test="${flats != null}">
                <div class="table_container">
                    <label class="selection_info"><c:out value="${select}"/></label>
                    <table border="1">
                        <tr>
                            <th width="20%">District</th>
                            <th width="50%">Address</th>
                            <th width="10%">Rooms</th>
                            <th width="10%">Area</th>
                            <th width="10%">Price</th>
                        </tr>
                        <c:forEach items="${flats}" var="flat">
                            <tr>
                                <td><c:out value="${flat.district}"/></td>
                                <td><c:out value="${flat.address}"/></td>
                                <td><c:out value="${flat.rooms}"/></td>
                                <td><c:out value="${flat.area}"/></td>
                                <td><c:out value="${flat.price}"/></td>
                            </tr>
                        </c:forEach>
                    </table>
                </div>
            </c:if>
        </div>
    </div>
    <%@ include file="footer.jsp"%>
</body>
</html>
