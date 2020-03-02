package beans;

import java.util.ArrayList;
import java.util.List;

public class healthAnalyseGroup {
        private List<healthAnalyse> list=new ArrayList<>();

        public List<healthAnalyse> getList() {
            return list;
        }

        public void setList(List<healthAnalyse> list) {
            this.list = list;
        }

        @Override
        public String toString() {
            return "healthAnalyseGroup [list=" + list + "]";
        }

}
