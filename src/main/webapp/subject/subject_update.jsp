<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>科目変更</title>
</head>
<body>

<h1>得点管理システム</h1>
<h2>科目変更</h2>

<hr>


<c:if test="${not empty error}">
    <p style="color:red;">${error}</p>
</c:if>


<!-- フォーム -->
<form action="SubjectUpdateExecute.action" method="post">

    <!-- hidden -->
    <input type="hidden" name="cd" value="${subject.cd}">

    <table border="1">

        <!-- 科目コード -->
        <tr>
            <th>科目コード</th>
            <td>${subject.cd}</td>
        </tr>

        <!-- 科目名 -->
        <tr>
            <th>科目名</th>
            <td>
                <input type="text" name="name" value="${subject.name}">

                <!-- エラー（fieldごと） -->
                <c:if test="${not empty nameError}">
                    <div style="color:red;">
                        ${nameError}
                    </div>
                </c:if>
            </td>
        </tr>

    </table>

    <br>

    <!-- ボタン -->
    <input type="submit" value="更新">
    <a href="SubjectList.action">戻る</a>

</form>

</body>
</html>