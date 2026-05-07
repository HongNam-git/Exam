package student;

import bean.Student;
import bean.Teacher;
import dao.ClassNumDAO;
import dao.StudentDAO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class StudentUpdateExecuteAction extends Action {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

        
        // 1. SESSION + TEACHER
        
        HttpSession session = request.getSession(false);
        Teacher teacher = (Teacher) session.getAttribute("teacher");

        if (teacher == null) {
            response.sendRedirect("Login.action");
            
        }

        
        // 2. LAY DU LIEU TU FORM
        
        String no = request.getParameter("no");
        String entYearStr = request.getParameter("entYear");
        String name = request.getParameter("name");
        String classNum = request.getParameter("classNum");
        String attendStr = request.getParameter("attend");

       
        // 3. VALIDATE
        
        boolean hasError = false;

        if (entYearStr == null || entYearStr.trim().isEmpty()) {
            request.setAttribute("entYearError", "入学年度を選択してください");
            hasError = true;
        }

        if (name == null || name.trim().isEmpty()) {
            request.setAttribute("nameError", "このフィールドを入力してください");
            hasError = true;
        }

        if (classNum == null || classNum.trim().isEmpty()) {
            request.setAttribute("classError", "このフィールドを入力してください");
            hasError = true;
        }

        
        if (hasError) {

            request.setAttribute("no", no);
            request.setAttribute("entYear", entYearStr);
            request.setAttribute("name", name);
            request.setAttribute("classNum", classNum);

            StudentDAO dao = new StudentDAO();
            request.setAttribute("student", dao.get(no, teacher.getSchool()));

            ClassNumDAO classDao = new ClassNumDAO();
            request.setAttribute("classList", classDao.filter(teacher.getSchool()));

            request.getRequestDispatcher("student_update.jsp")
                   .forward(request, response);
           
        }

        
        // 4. ÉP KIỂU
        
        int entYear = Integer.parseInt(entYearStr);
        boolean attend = (attendStr != null);

        
        // 5. TẠO OBJECT
        
        Student s = new Student();
        s.setNo(no);
        s.setEntYear(entYear);
        s.setName(name);
        s.setClassNum(classNum);
        s.setAttend(attend);
        s.setSchool(teacher.getSchool());

        
        // 6. UPDATE DB
        
        StudentDAO dao = new StudentDAO();
        boolean success = dao.update(s);

        
        // 7. KẾT QUẢ
       
        if (success) {
            request.getRequestDispatcher("student_update_done.jsp")
                   .forward(request, response);
            
        } else {
            request.setAttribute("error", "更新に失敗しました");

            request.getRequestDispatcher("student_update.jsp")
                   .forward(request, response);
            
        }
    }
}