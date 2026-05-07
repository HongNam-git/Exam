package auth;

import bean.Teacher;
import dao.TeacherDAO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Action;

public class LoginExecuteAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

        // 1. lấy dữ liệu
        String id = req.getParameter("id");
        String password = req.getParameter("password");

        // 2. gọi DAO
        TeacherDAO dao = new TeacherDAO();
        Teacher teacher = dao.findByIdAndPassword(id, password);

        // 3. xử lý
        if (teacher != null) {
            // ✔ login thành công
            req.getSession().setAttribute("teacher", teacher);
            res.sendRedirect("menu.jsp"); // chuyển sang menu

        } else {
            // ❌ login thất bại
            req.setAttribute("error", "IDまたはパスワードが確認できませんでした");
            req.getRequestDispatcher("login.jsp").forward(req, res);
        }

        
    }
}