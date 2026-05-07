<%@page contentType="text/html; charset=UTF-8"%>



<form action="LoginExecute.action" method="post">
    <p>ログイン名<input type="text" name="id"></p>
    <p>パスワード<input type="password" name="password"></p>
    <p><input type="submit" value="ログイン"></p>
</form>

<p style="color:red">${error}</p>