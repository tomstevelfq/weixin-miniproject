package beans;

import java.util.ArrayList;
import java.util.List;

public class indexViewsGroup {
    private List<indexViews> list=new ArrayList<>();

    public List<indexViews> getList() {
        return list;
    }

    public void setList(List<indexViews> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "indexViewsGroup [list=" + list + "]";
    }
}
