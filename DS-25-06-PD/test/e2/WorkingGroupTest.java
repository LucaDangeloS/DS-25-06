package e2;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class WorkingGroupTest {

    @Test
    void WorkingGroup() {
        Project p1 = new Project();

        GroupComponent group = new WorkingGroup("test");
        GroupComponent worker = new Worker("Juan", 200);
        GroupComponent worker2 = new Worker("Jorge", 350);

        group.add(worker);
        group.add(worker2);
        p1.add(group);
        List<GroupComponent> list = p1.getCoworkers(worker);

        for (GroupComponent c : list) System.out.println(c.getName()+" "+c.getCoste());
    }

}