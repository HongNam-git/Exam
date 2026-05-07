<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<h2>学生情報登録</h2>

<form action="StudentCreateExecute.action" method="post">

    <!-- 入学年度 -->
    <label>入学年度:</label>
    <input type="text" name="entYear"
           value="${entYear}"
           placeholder="入学年度を入力してください"
           required>
    <span style="color:red">${entYearError}</span>
    <br>

    <!-- 学生番号 -->
    <label>学生番号:</label>
    <input type="text" name="no"
           value="${no}"
           placeholder="学生番号を入力してください"
           required maxlength="10">
    <span style="color:red">${noError}</span>
    <br>

    <!-- 氏名 -->
    <label>氏名:</label>
    <input type="text" name="name"
           value="${name}"
           placeholder="氏名を入力してください"
           required maxlength="30">
    <span style="color:red">${nameError}</span>
    <br>

    <!-- クラス -->
    <label>クラス:</label>
    <select name="classNum">
        <option value="">----</option>
        <c:forEach var="c" items="${classList}">
            <option value="${c.classNum}"
                <c:if test="${classNum == c.classNum}">selected</c:if>>
                ${c.classNum}
            </option>
        </c:forEach>
    </select>
    <span style="color:red">${classError}</span>
    <br>

    <!-- submit -->
    <input type="submit" value="登録して終了">

</form>

<a href="StudentList.action">戻る</a>