package beans;

import java.util.ArrayList;
import java.util.List;

public class videoGroup {
    private List<video> list=new ArrayList<>();

    public List<video> getList() {
        return list;
    }

    public void setList(List<video> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "videoGroup [list=" + list + "]";
    }
}
