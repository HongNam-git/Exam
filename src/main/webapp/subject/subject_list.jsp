<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>科目一覧</title>
</head>
<body>
<h1>得点管理システム</h1>

<h2>科目管理一覧</h2>


<hr>

<a href="SubjectCreate.action">＋ 新規登録</a>
<table border="1">
    <tr>
        
        <th>科目コード</th>
        <th>科目名</th>
        <th>操作</th>
    </tr>

    <c:forEach var="subject" items="${subjectList}">
        <tr>
            <!-- School code (OOP style: subject.school.cd) -->
            

            <td>${subject.cd}</td>
            <td>${subject.name}</td>

            <td>
                <a href="SubjectUpdate.action?cd=${subject.cd}">変更</a>
                |
                <a href="SubjectDelete.action?school_cd=${s.school.cd}&cd=${subject.cd}">削除</a>
            </td>
        </tr>
    </c:forEach>

</table>
<a href="../auth/menu.jsp">戻る</a>

</body>
</html>