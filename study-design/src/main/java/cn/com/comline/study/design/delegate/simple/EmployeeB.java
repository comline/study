package cn.com.comline.study.design.delegate.simple;

public class EmployeeB implements IEmployee {
    @Override
    public void doing(String command) {
        System.out.println("我是员工B，我开始干活了，擅长，执行" + command);
    }
}
