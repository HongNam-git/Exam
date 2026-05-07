<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>科目削除</title>
</head>
<body>

<h1>得点管理システム</h1>
<h2>科目削除</h2>

<hr>


<p>以下の科目を削除しますか？</p>


<table border="1">

    <tr>
        <th>科目コード</th>
        <td>${subject.cd}</td>
    </tr>

    <tr>
        <th>科目名</th>
        <td>${subject.name}</td>
    </tr>

</table>

<br>


<!-- フォーム -->
<form action="SubjectDeleteExecute.action" method="post">

    <!-- hidden -->
    <input type="hidden" name="cd" value="${subject.cd}">

    <!-- ボタン -->
    <input type="submit" value="削除">
    <a href="SubjectList.action">戻る</a>

</form>

</body>
</html>