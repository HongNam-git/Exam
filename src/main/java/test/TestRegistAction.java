package test;

import java.util.List;

import bean.School;
import bean.Student;
import bean.Subject;
import bean.Teacher;
import bean.Test;
import dao.ClassNumDAO;
import dao.StudentDAO;
import dao.SubjectDAO;
import dao.TestDAO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Action;

public class TestRegistAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

        // =========================
        // ログイン情報
        // =========================
        Teacher teacher = (Teacher) req.getSession().getAttribute("teacher");
        School school = teacher.getSchool();

        // =========================
        // プルダウンデータ
        // =========================
        ClassNumDAO classDao = new ClassNumDAO();
        SubjectDAO subjectDao = new SubjectDAO();

        req.setAttribute("classList", classDao.filter(school));
        req.setAttribute("subjectList", subjectDao.filter(school));

        // =========================
        // 検索パラメータ取得
        // =========================
        String entYearStr = req.getParameter("ent_year");
        String classNum = req.getParameter("class_num");
        String subjectCd = req.getParameter("subject_cd");
        String noStr = req.getParameter("no");

        // =========================
        // 初期表示
        // =========================
        if (entYearStr == null && classNum == null && subjectCd == null && noStr == null) {
            req.getRequestDispatcher("test_regist.jsp").forward(req, res);
            return;
        }

        // =========================
        // 入力チェック（必須）
        // =========================
        if (entYearStr == null || entYearStr.isEmpty()
         || classNum == null || classNum.isEmpty()
         || subjectCd == null || subjectCd.isEmpty()
         || noStr == null || noStr.isEmpty()) {

            req.setAttribute("error", "全ての項目を入力してください");
            req.getRequestDispatcher("test_regist.jsp").forward(req, res);
            return;
        }

        int entYear = Integer.parseInt(entYearStr);
        int no = Integer.parseInt(noStr);

        // =========================
        // 学生一覧（表示用）
        // =========================
        StudentDAO studentDao = new StudentDAO();
        List<Student> studentList =
                studentDao.filter(school, entYear, classNum, false);

        // =========================
        // 科目
        // =========================
        Subject subject = new Subject();
        subject.setCd(subjectCd);

        // =========================
        // 成績取得
        // =========================
        TestDAO testDao = new TestDAO();
        List<Test> testList =
                testDao.filter(entYear, classNum, subject, no, school);

        // =========================
        // JSPへ渡す
        // =========================
        req.setAttribute("studentList", studentList);
        req.setAttribute("testList", testList);

        req.setAttribute("ent_year", entYear);
        req.setAttribute("class_num", classNum);
        req.setAttribute("subject_cd", subjectCd);
        req.setAttribute("no", no);

        req.getRequestDispatcher("test_regist.jsp").forward(req, res);
    }
}