package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Student;
import bean.Subject;
import bean.Test;

public class TestDAO extends DAO {

    // =========================
    // 成績一覧取得（検索用）
    // =========================
    public List<Test> filter(int entYear, String classNum, Subject subject, int no, School school) throws Exception {

        List<Test> list = new ArrayList<>();

        Connection con = getConnection();

        String sql =
            "SELECT t.*, s.name " +
            "FROM test t " +
            "JOIN student s ON t.student_id = s.no " +
            "WHERE t.school_cd = ? " +
            "AND t.ent_year = ? " +
            "AND t.class_num = ? " +
            "AND t.subject_cd = ? " +
            "AND t.no = ? " +
            "ORDER BY t.student_id";

        PreparedStatement st = con.prepareStatement(sql);

        st.setString(1, school.getCd());
        st.setInt(2, entYear);
        st.setString(3, classNum);
        st.setString(4, subject.getCd());
        st.setInt(5, no);

        ResultSet rs = st.executeQuery();

        while (rs.next()) {

            Test t = new Test();

            Student s = new Student();
            s.setNo(rs.getString("student_id"));
            s.setName(rs.getString("name"));

            Subject sub = new Subject();
            sub.setCd(rs.getString("subject_cd"));

            t.setStudent(s);
            t.setSubject(sub);
            t.setSchool(school);

            t.setClassNum(rs.getString("class_num"));
            t.setNo(rs.getInt("no"));
            t.setPoint(rs.getInt("point"));

            list.add(t);
        }

        rs.close();
        st.close();
        con.close();

        return list;
    }
}