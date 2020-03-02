package beans;

import java.util.ArrayList;
import java.util.List;

public class menuGroup {
    private List<menu> list=new ArrayList<>();

    public List<menu> getList() {
        return list;
    }

    public void setList(List<menu> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "menuGroup [list=" + list + "]";
    }
}
