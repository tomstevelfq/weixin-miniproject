package beans;

import beans.journal;

import java.util.ArrayList;
import java.util.List;

public class journalGroup{
    private List<journal> list=new ArrayList<>();

    public List<journal> getList() {
        return list;
    }

    public void setList(List<journal> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "journalGroup [list=" + list + "]";
    }
}
