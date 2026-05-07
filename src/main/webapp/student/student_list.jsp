<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<h2>学生一覧</h2>

<a href="../auth/Logout.action">ログアウト</a>
<div>
<a href="StudentCreate.action">新規登録</a>

</div>


<%--**************FORM DIEN PHAN 絞込********************** --%>


<form action="StudentList.action" method="get">

    <%-- 1.loc theo 入学年度(select)--%>
    <label>入学年度: </label>
    <select name="f1">
        <option value="">--------</option>
        <c:forEach var="year" items="${ent_year}">
            <option value="${year}" ${year == f1 ? 'selected' : ''}>
                ${year}
            </option>
        </c:forEach>
    </select>


   <%--2.loc class(select)--%>
    <label>クラス: </label>
    <select name="f2">
        <option value="" <c:if test="${empty f2}">selected</c:if>>--------</option>
        <c:forEach var="cNum" items="${classList}">
            <option value="${cNum.classNum}"
                <c:if test="${f2 == cNum.classNum}">selected</c:if>>
                ${cNum.classNum}
            </option>
        </c:forEach>
    </select>
	
	
	<%--o check 在学中 (checkbox) --%>
    <label>
    	在学中
        <input type="checkbox" name="f3" value="true"
            ${f3 ? 'checked' : ''}>
        
    </label>
    <input type="submit" value="絞込み">
    
    <c:if test="${not empty error}">
    <p style="color:red">${error}</p>
	</c:if>
    
</form>


<%--**************************************************************************** --%>

<%--HIEN THI DANH SACH SINH VIEN --%>

<p>検索結果：${total}件</p>

<table border="1">
    <tr>
        <th>入学年度</th>
        <th>学生番号</th>
        <th>氏名</th>
        <th>クラス</th>
        <th>在学中</th>
    </tr>

    <c:forEach var="s" items="${list}">
        <tr>
            <td>${s.entYear}</td>
            <td>${s.no}</td>
            <td>${s.name}</td>
            <td>${s.classNum}</td>
            <td>
                <%-- HIEN THI ○ NEU IS_ATTEND LA TRUE , NGUOC LAI HIEN THI × --%>
                <c:if test="${s.attend}">○</c:if>
                <c:if test="${!s.attend}">×</c:if>
                
            </td>
            <td><a href="StudentUpdate.action?no=${s.no}">変更</a></td>
        </tr>
    </c:forEach>
</table>
<a href="../auth/menu.jsp">戻る</a>

<%--******************************************************************************: --%>

<%-----------------THONG BAO NEU KHONG CO DU LIEU-----------------%>
<c:if test="${empty list}">
    <p>学生の情報がありません。</p>
</c:if>