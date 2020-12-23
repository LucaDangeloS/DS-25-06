package e2;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class Project {
    private final List<GroupComponent> workers = new ArrayList<>();

    public void add(GroupComponent gp) {
        if (workers.contains(gp)) throw new IllegalArgumentException("Element already exists");
        workers.add(gp);
    }
    public void remove(GroupComponent gp) {
        if (workers.contains(gp)) {
            workers.remove(gp);
        } else throw new NoSuchElementException();
    }

    public List<GroupComponent> getCoworkers(GroupComponent g) {
        List<GroupComponent> ret = new ArrayList<>();
        for (GroupComponent c : workers) {
            if (!c.equals(g)) ret.add(c);
        }
        return ret;
    }

//    public String getInfo(GroupComponent gc) {
//
//    }
}
