
import java.util.Queue;
import java.util.LinkedList;

public class Department {

    private String name;
    private int numberOfEmployees;
    private Queue<Executive> executives;

    public Department(String name) {
        this.name = name;
        this.executives = new LinkedList<>();
    }

    public String getName() {
        return this.name;
    }

    public int getEmployees() {
        return this.numberOfEmployees;
    }

    public double getAverageSalary() {
        return this.computeAverageSalary();
    }

    public double computeAverageSalary() {
        double average = 0;
        for (Executive x : this.executives) {
            average += x.getSalary();
        }
        return this.executives.size() > 0 ? average / this.executives.size() : 0;

    }

    public void add(Executive e) {
        if (!this.name.equals("Unemployed")) {
            for (Executive exec : this.executives) {
                exec.incrementSalary(5000);
            }
        }
        else {
            e.incrementSalary(-1 * e.getSalary());
        }
        this.executives.add(e);
    }

    public void remove(String name) {
        Queue<Executive> temp = new LinkedList<>();
        while (!this.executives.isEmpty()) {
            Executive next = this.executives.remove();
            if (!next.getName().equals(name)) {
                temp.add(next);
            }
        }
        this.executives = temp;
    }

    public Queue<Executive> getExecutives() {
        return this.executives;
    }

    @Override
    public boolean equals(Object x) {
        if (!(x instanceof Department)) {
            return false;
        }
        return this.name.equalsIgnoreCase(((Department) x).name);
    }

    @Override
    public String toString() {
        String result = "\tDepartment: " + name + "\n";
        result += "\tName \tSalary\n";

        for (Executive e : this.executives) {
            result += "\t" + e.getName() + "\t" + e.getSalary() + "\n";
        }
        return result;
    }

}
