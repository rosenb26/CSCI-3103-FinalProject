
public class Executive {

    private String name;
    private double salary;

    public Executive(String name) {
        this.name = name;
        this.salary = 40000;
    }

    public double getSalary() {
        return this.salary;
    }

    public void incrementSalary(double d) {
        this.salary += d;
    }

    public String getName() {
        return this.name;
    }

    @Override
    public boolean equals(Object x) {
        return x instanceof Executive && ((Executive) x).name.equals(this.name);
    }
}
