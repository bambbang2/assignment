package main;

import configs.AppCtx;
import models.emp.Emp;
import models.emp.EmpDao;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.lang.reflect.Member;

public class ExMain {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppCtx.class);

        EmpDao empDao = ctx.getBean(EmpDao.class);

        // 추가
        Emp empI = new Emp();
        empI.setENAME("직원3");
        empI.setJOB("STAFF");
        long EMPNO = empDao.insert(empI);
        System.out.println(EMPNO);


        // 업데이트
        Emp empU = new Emp();
        empU.setJOB("STUDENT");

        long EMPNO2 = empDao.update(2);
        System.out.println(EMPNO2);


        // 삭제
        Emp empD = new Emp();
        long EMPNO3 = empDao.delete(3);
        System.out.println(EMPNO3);

        // 조회
        Emp emp = empDao.get(7369);
        System.out.println(emp);

        ctx.close();
    }
}
