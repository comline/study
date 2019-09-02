package cn.com.comline.study.design.delegate.simple;

import java.util.HashMap;
import java.util.Map;

public class Leader {
    //预先知道每个员工的特长、特征，分发任务
    private Map<String, IEmployee> register = new HashMap<>();

    public Leader(){
        register.put("加密",new EmployeeA());
        register.put("架构",new EmployeeB());
    }

    public void doing(String command){
        register.get(command).doing(command);
    }
}
