<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<html>
<head>
    <title>科目登録</title>
</head>
<body>

<h2>科目登録</h2>

<form action="SubjectCreateExecute.action" method="post">

    <!-- 科目コード -->
    <div>
        <label>科目コード：</label>
        <input type="text" name="cd" value="${cd}" maxlength="3" required>
        <span style="color:red">
            <c:out value="${error_cd}" />
        </span>
    </div>

    <br>

    <!-- 科目名 -->
    <div>
        <label>科目名：</label>
        <input type="text" name="name" value="${name}" required>
        <span style="color:red">
            <c:out value="${error_name}" />
        </span>
    </div>

    <br>

    <!-- ボタン -->
    <div>
        <button type="submit">登録</button>
        <a href="SubjectList.action">戻る</a>
    </div>

</form>

</body>
</html>