package cn.com.comline.study.design.delegate.simple;

public class Boss {

    public void command(String command,Leader leader){
        leader.doing(command);
    }
}
