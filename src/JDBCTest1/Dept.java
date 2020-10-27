package JDBCTest1;

public class Dept {
    private int Deptno;
    private String Dname;
    private String Loc;

    public int getDeptno() {
        return Deptno;
    }

    public void setDeptno(int deptno) {
        Deptno = deptno;
    }

    public String getDname() {
        return Dname;
    }

    public void setDname(String dname) {
        Dname = dname;
    }

    public String getLoc() {
        return Loc;
    }

    public void setLoc(String loc) {
        Loc = loc;
    }

    public Dept() {
    }

    public Dept(int deptno, String dname, String loc) {
        Deptno = deptno;
        Dname = dname;
        Loc = loc;
    }

    @Override
    public String toString() {
       System.out.printf("Deptno=%-10sDname=%-20sLoc=%-10s",Deptno,Dname,Loc);
        return null;
    }
}
