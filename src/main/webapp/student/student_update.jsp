<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<h2>学生情報変更</h2>

<form action="StudentUpdateExecute.action" method="post">

    <!-- 学生番号 (không cho sửa) -->
    <label>学生番号:</label>
    <input type="text" name="no" value="${student.no}" readonly>
    <br>

    <!-- 入学年度 -->
    <label>入学年度:</label>
    <input type="text" name="entYear" value="${student.entYear}">
    <span style="color:red">${entYearError}</span>
    <br>

    <!-- 氏名 -->
    <label>氏名:</label>
    <input type="text" name="name" value="${student.name}">
    <span style="color:red">${nameError}</span>
    <br>

    <!-- クラス -->
    <label>クラス:</label>
    <select name="classNum">
        <c:forEach var="c" items="${classList}">
            <option value="${c.classNum}"
                <c:if test="${student.classNum == c.classNum}">selected</c:if>>
                ${c.classNum}
            </option>
        </c:forEach>
    </select>
    <span style="color:red">${classError}</span>
    <br>

    <!-- 在学中 -->
    <label>在学中:</label>
    <input type="checkbox" name="attend"
        <c:if test="${student.attend}">checked</c:if>>
    <br>

    <input type="submit" value="更新">
</form>

<a href="StudentList.action">戻る</a>