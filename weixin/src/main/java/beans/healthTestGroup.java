package beans;


import java.util.ArrayList;
import java.util.List;

public class healthTestGroup {
    private List<healTest> list=new ArrayList<>();

    public List<healTest> getList() {
        return list;
    }

    public void setList(List<healTest> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "healthTestGroup [list=" + list + "]";
    }
}
