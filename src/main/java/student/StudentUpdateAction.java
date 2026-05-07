package student;

import bean.Student;
import bean.Teacher;
import dao.ClassNumDAO;
import dao.StudentDAO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class StudentUpdateAction extends Action {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

        // =========================
        // 1. LẤY SESSION + TEACHER
        // =========================
        HttpSession session = request.getSession(false);
        Teacher teacher = (Teacher) session.getAttribute("teacher");

        if (teacher == null) {
            response.sendRedirect("Login.action");
            
        }
        
        // =========================
        // 2. LẤY student_no từ request
        // =========================
        String no = request.getParameter("no");

        // =========================
        // 3. LẤY STUDENT TỪ DB
        // =========================
        StudentDAO dao = new StudentDAO();
        Student student = dao.get(no, teacher.getSchool());

        // =========================
        // 4. SET vào request
        // =========================
        request.setAttribute("student", student);

        // =========================
        // 5. LOAD classList
        // =========================
        ClassNumDAO classDao = new ClassNumDAO();
        request.setAttribute("classList", classDao.filter(teacher.getSchool()));

        // =========================
        // 6. CHUYỂN SANG JSP
        // =========================
        request.getRequestDispatcher("student_update.jsp")
        .forward(request, response);

       
        
    }
}