package models.emp;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Member;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


@Configuration
public class EmpDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
    public static Emp selectByEMPNO(String EMPNO) {
        String sql = "SELECT * FROM Emp WHERE SELECTBYEMPNO = ?";
        List<Emp> results = jdbcTemplate.query(sql, (rs, rowNum) -> {
                        Emp emp = new Emp(
                                rs.getString("EMPNO"),
                                rs.getString("ENAME"),
                                rs.getString("JOB");
                        return emp;
                    }, EMPNO);
        return results.isEmpty() ? null : results.get(0);
    }
     */

    @Transactional
    public long insert(Emp emp) {
        String sql = "INSERT INTO EMP2 (EMPNO, ENAME, JOB) VALUES (EMP2_SEQ.nextval, ?, ?)";
        KeyHolder holder = new GeneratedKeyHolder();
        jdbcTemplate.update(conn -> {
            PreparedStatement ps = conn.prepareStatement(sql, new String[]{"EMPNO"});
            ps.setString(1, emp.getENAME());
            ps.setString(2, emp.getJOB());

            return ps;

        }, holder);

        Number key = holder.getKey();
        long EMPNO = key.longValue();

        return EMPNO;
    }

    @Transactional
    public long update(long no) {
        String sql = "UPDATE EMP2 SET JOB = ? WHERE EMPNO = ?";
        KeyHolder holder = new GeneratedKeyHolder();
        jdbcTemplate.update(conn -> {
            PreparedStatement ps = conn.prepareStatement(sql, new String[]{"EMPNO"});
            ps.setLong(1, no);
            return ps;
        }, holder);

        Number key = holder.getKey();
        long EMPNO = key.longValue();

        return EMPNO;
    }

    @Transactional
    public long delete(long no) {

        String sql = "DELETE FROM EMP2 WHERE EMPNO = ?";

        KeyHolder holder = new GeneratedKeyHolder();
        jdbcTemplate.update(conn -> {
            PreparedStatement ps = conn.prepareStatement(sql, new String[]{"EMPNO"});
            ps.setLong(1, no);
            return ps;

        }, holder);

        Number key = holder.getKey();
        long EMPNO = key.longValue();

        return EMPNO;
    }

    public Emp get(long EMPNO){ // 단일 조회
        try {
            String sql = "SELECT * FROM EMP WHERE EMPNO = ?";
            Emp emp = jdbcTemplate.queryForObject(sql, this::mapper, EMPNO);

            return emp;
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    private Emp mapper(ResultSet r, int i) throws  SQLException {
        Emp emp = new Emp();
        emp.setEMPNO(r.getLong("EMPNO"));
        emp.setENAME(r.getString("ENAME"));
        emp.setJOB(r.getString("JOB"));

        return emp;
    }
}