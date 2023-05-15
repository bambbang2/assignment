package main;

import configs.AppCtx;
import models.emp.Emp;
import models.emp.EmpDao;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ExMain {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppCtx.class);

        EmpDao empDao = ctx.getBean(EmpDao.class);
        Emp emp = empDao.get(7369);
        System.out.println(emp);

        Emp empI = new Emp();
        empI.setENAME("직원3");
        empI.setJOB("STAFF");
        long EMPNO = empDao.insert(empI);
        System.out.println(EMPNO);

        Emp empU = new Emp();
        empU.setJOB("STUDENT");
        long EMPNO2 = empDao.update(empU);
        System.out.println(EMPNO2);

        Emp empD = new Emp();
        long EMPNO3 = empDao.delete(3);
        System.out.println(EMPNO3);

        ctx.close();
    }
}
