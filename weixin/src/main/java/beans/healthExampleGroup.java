package beans;

import java.util.ArrayList;
import java.util.List;

public class healthExampleGroup {
    private List<healthExample> list=new ArrayList<>();

    public List<healthExample> getList() {
        return list;
    }

    public void setList(List<healthExample> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "healthExampleGroup [list=" + list + "]";
    }
}
