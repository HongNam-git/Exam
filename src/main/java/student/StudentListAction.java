package student;

import java.util.ArrayList;
import java.util.List;

import bean.ClassNum;
import bean.Student;
import bean.Teacher;
import dao.ClassNumDAO;
import dao.StudentDAO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class StudentListAction extends Action {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        // 1. LAY SESSION(chi lay session da ton tai)
        HttpSession session = request.getSession(false);

        // 2. LAY TEACHER TU SESSION
        Teacher teacher = (Teacher) session.getAttribute("teacher");

        // CHECK LOGIN HAY KHONG
        if (teacher == null) {
            response.sendRedirect("Login.action");
            
        }
        
        
        

       
        // 4 XU LY FILTER(f1,f2,f3)
        StudentDAO studentDao = new StudentDAO();
        String f1 = request.getParameter("f1");
        List<Student> studentList;
        
        //TH1: lan dua vao man hinh, chua co filter =>chi lay theo school+mac dinh(ko loc)
        if (f1 == null) {
            
            studentList = studentDao.filter(teacher.getSchool(), false);
         
         //TH2: da submit form filter
        } else {
            String f2 = request.getParameter("f2");//lay class
            String f3 = request.getParameter("f3");//checl box 在学中
            
            if ((f1.isEmpty()) && (f2 != null && !f2.isEmpty())) {

                request.setAttribute("error", "クラスだけ選択することはできません。入学年度を選択してください。");

                studentList = studentDao.filter(teacher.getSchool(), false);

            } else {
            	//ep kieu string f1(nam nhap hoc) sang int , neu rong thi gan 0(ko loc theo nam)
                int entYear = (f1.isEmpty()) ? 0 : Integer.parseInt(f1);
                //neu k chon class => gan ""
                String classNum = (f2 == null) ? "" : f2;
                //neu checkboc duoc tich thi f3 !- null => true
                boolean isAttend = (f3 != null);
                
                //***CHON HAM FILTER PHU HOP***
                // nam + class + attend
                if (entYear != 0 && !classNum.isEmpty()) {
                    studentList = studentDao.filter(teacher.getSchool(), entYear, classNum, isAttend);
                 
                //nam + attend
                } else if (entYear != 0) {
                    studentList = studentDao.filter(teacher.getSchool(), entYear, isAttend);
                    
                // attend
                } else {
                    studentList = studentDao.filter(teacher.getSchool(), isAttend);
                }
            }
            
            
        }
        
        //lay dnah sach student
        request.setAttribute("list", studentList);
        
        //tinh tong so student
        
        int total = studentList.size();
        request.setAttribute("total", total);
        
        //giu lai gia tri filter 
        request.setAttribute("f1", f1);
        request.setAttribute("f2", request.getParameter("f2"));
        request.setAttribute("f3", request.getParameter("f3") != null);
        

        //  LAY DS class cua STUDENT THEO SCHOOL_CD CUA TEACHER
        ClassNumDAO classDao = new ClassNumDAO();
        List<ClassNum> classList = classDao.filter(teacher.getSchool());
        request.setAttribute("classList", classList);
        
        
        //tao danh sach 5 truoc den nam hien tai
        int currentYear = java.time.LocalDate.now().getYear();
        List<Integer> entYearSet = new ArrayList<>();
        for (int i = currentYear - 10; i <= currentYear; i++) {
            entYearSet.add(i);
        }    
        request.setAttribute("ent_year", entYearSet);
        
        

        // 6.FORWARD SANG JSP
        request.getRequestDispatcher("student_list.jsp")
               .forward(request, response);

        
    }
}


/*
 * Flow xử lý:
 * 1. Lấy teacher từ session
 * 2. Lấy schoolCd từ teacher
 * 3. Lấy danh sách student theo schoolCd
 * 4. Lấy danh sách class theo schoolCd
 * 5. Gửi dữ liệu sang JSP
 * 6. Forward sang student_list.jsp
 */