
import java.util.ArrayList;
import java.util.Scanner;

public class UserInterface {

    private ArrayList<Department> departments;
    private Scanner reader;

    public UserInterface() {
        this.departments = new ArrayList<>();
        this.reader = new Scanner(System.in);
    }

    public void run() {
        this.printMainMenu();
    }

    public void printMainMenu() {
        while (true) {
            System.out.println();
            System.out.println("1 - Add a department");
            System.out.println("2 - Hire a person");
            System.out.println("3 - Join a person to a department");
            System.out.println("4 - Remove a person from a department");
            System.out.println("5 - Transfer a person from one department to another");
            System.out.println("6 - Payroll");
            System.out.println("7 - Get salary of a person");
            System.out.println("8 - Quit");

            String input = this.reader.nextLine();
            while (!this.validateInput(input, 8)) {
                System.out.println("Invalid selection. Please try again.");
                input = this.reader.nextLine();
            }
            int choice = Integer.parseInt(input);
            if (choice == 8) {
                break;
            }
            this.analyzeChoice(choice);
        }
    }

    public void analyzeChoice(int choice) {
        String input;
        switch (choice) {
            case 1: {
                System.out.print("\nDepartment name: ");
                this.addDepartment(this.reader.nextLine());
                break;
            }
            case 2: {
                System.out.print("\nName of person: ");
                this.hire(this.reader.nextLine());
                break;
            }
            case 3: {
                System.out.print("\nPerson name: ");
                String person = this.reader.nextLine();
                System.out.print("\nDepartment name: ");
                String department = this.reader.nextLine();
                this.join(person, department);
                break;
            }
            case 4: {
                System.out.print("\nPerson name: ");
                String person = this.reader.nextLine();
                System.out.print("\nDepartment name: ");
                String department = this.reader.nextLine();
                if (this.checkPersonInDepartment(person, department)) {
                    this.removePersonFromDepartment(person, department);
                }
                break;
            }
            case 5: {
                System.out.print("\nPerson name: ");
                String person = this.reader.nextLine();
                System.out.print("\nFrom department: ");
                String from = this.reader.nextLine();

                if (this.checkPersonInDepartment(person, from)) {
                    this.removePersonFromDepartment(person, from);

                    System.out.print("\nTo department: ");
                    String to = this.reader.nextLine();
                    this.addDepartment(to);
                    this.departments.get(this.departments.indexOf(new Department(to)))
                            .add(new Executive(person));
                    this.removePersonFromDepartment(person, from);
                }
                break;
            }
            case 6: {
                if (this.departments.isEmpty()) {
                    System.out.println("No departments with employees.");
                }
                else {
                    for (Department d : this.departments) {
                        System.out.println(d);
                    }
                    break;
                }
            }
            case 7: {
                System.out.print("\n1 - Enter a person's name");
                System.out.print("\n2 - Browse by department");
                System.out.print("\n3 - Show all people");
                System.out.println("\n4 - Back to main menu");
                input = this.reader.nextLine();
                while (!this.validateInput(input, 4)) {
                    System.out.println("Invalid selection. Please try again.");
                    input = this.reader.nextLine();
                }
                this.analyzeSalaryMenu(Integer.parseInt(input));
                break;
            }
        }
    }

    public void analyzeSalaryMenu(int choice) {
        String input;
        switch (choice) {
            case 1: {
                System.out.print("Name: ");
                input = reader.nextLine();
                this.listAllByName(input);
                break;
            }
            case 2: {
                if (this.departments.isEmpty()) {
                    System.out.println("No departments to show.");
                }
                else {
                    this.listAllDepartments();
                    System.out.print("\nWhich department? ");
                    input = reader.nextLine();
                    while (!this.validateInput(input, this.departments.size())) {
                        System.out.println("Invalid input");
                        input = reader.nextLine();
                    }
                    this.listByDepartment(Integer.parseInt(input));
                }
                break;
            }
            case 3: {
                this.listEveryone();
                break;
            }
        }
    }

    public void listEveryone() {
        if (this.departments.isEmpty()) {
            System.out.println("No departments, and hence no people, to show.");
        }
        else {
            for (int i = 1; i <= this.departments.size(); i++) {
                System.out.println();
                this.listByDepartment(i);
            }
        }
    }

    public void listByDepartment(int choice) {
        Department d = this.departments.get(choice - 1);

        System.out.println("People in department " + d.getName() + ":");
        for (Executive e : this.departments.get(this.departments.indexOf(d)).getExecutives()) {
            System.out.println(e.getName());
        }

    }

    public void listAllByName(String name) {
        boolean match = false;
        for (Department d : this.departments) {
            for (Executive e : d.getExecutives()) {
                if (e.getName().equalsIgnoreCase(name)) {
                    System.out.println(name + " " + e.getSalary() + "(" + d.getName() + " department)");
                    match = true;
                }
            }
        }
        if (!match) {
            System.out.println("No people with that name.");
        }
        System.out.println();
    }

    public void listAllDepartments() {
        System.out.println("Department list:");
        int i = 1;
        for (Department d : this.departments) {
            System.out.println(i + " - " + d.getName());
            i++;
        }
    }

    public void removePersonFromDepartment(String person, String department) {
        for (Department d : this.departments) {
            if (d.getName().equalsIgnoreCase(department)) {
                d.remove(person);
                break;
            }
        }
    }

    public boolean checkPersonInDepartment(String person, String department) {
        Department d = new Department(department);
        if (!this.departments.contains(d)) {
            System.out.println("There is no department with the name " + department);
            return false;
        }
        if (!this.departments.get(this.departments.indexOf(d)).getExecutives()
                .contains(new Executive(person))) {
            System.out.println(person + " is not a member of " + department);
            return false;
        }
        return true;

    }

    public boolean validateInput(String input, int bound) {
        try {
            int x = Integer.parseInt(input);
            return x >= 1 && x <= bound;
        }
        catch (NumberFormatException e) {
            return false;
        }
    }

    public void addDepartment(String name) {
        name = this.capitalizeName(name);
        Department d = new Department(name);
        if (!this.departments.contains(d)) {
            this.departments.add(new Department(name));
            System.out.println("Department " + name + " was added.");
        }
        else {
            System.out.println("Department " + name + " already exists.");
        }
    }

    public String capitalizeName(String name) {
        if (name == null || name.length() == 0) {
            return "";
        }
        return name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase();

    }

    public void hire(String name) {
        if (this.departments.isEmpty()) {
            this.addDepartment("Unemployed");
        }
        else {
            if (!this.departments.get(0).getName().equals("Unemployed")) {
                this.departments.add(0, new Department("Unemployed"));
            }
        }
        this.departments.get(0).add(new Executive(name));
    }

    public void join(String name, String department) {
        Department d = new Department(department);
        if (!this.departments.contains(d)) {
            this.addDepartment(department);
        }
        this.departments.get(this.departments.indexOf(d)).add(new Executive(name));
    }

}
