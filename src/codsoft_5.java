import java.util.*;
import java.io.*;

class Student implements Serializable {
    private String name;
    private String rollNumber;
    private String grade;

    public Student(String name, String rollNumber, String grade) {
        this.name = name;
        this.rollNumber = rollNumber;
        this.grade = grade;
    }

    public String getName() { return name; }
    public String getRollNumber() { return rollNumber; }
    public String getGrade() { return grade; }
    public void setName(String name) { this.name = name; }
    public void setRollNumber(String rollNumber) { this.rollNumber = rollNumber; }
    public void setGrade(String grade) { this.grade = grade; }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", rollNumber='" + rollNumber + '\'' +
                ", grade='" + grade + '\'' +
                '}';
    }
}

class StudentManagementSystem {
    private List<Student> students = new ArrayList<>();

    public void addStudent(Student student) {
        students.add(student);
        System.out.println("Student added.");
    }
    public void removeStudent(String rollNumber) {
        Iterator<Student> it = students.iterator();
        while (it.hasNext()) {
            Student s = it.next();
            if (s.getRollNumber().equalsIgnoreCase(rollNumber)) {
                it.remove();
                System.out.println("Student removed.");
                return;
            }
        }
        System.out.println("Student not found.");
    }
    public Student searchStudent(String rollNumber) {
        for (Student s : students) {
            if (s.getRollNumber().equalsIgnoreCase(rollNumber)) {
                return s;
            }
        }
        return null;
    }
    public void displayAll() {
        if (students.isEmpty()) {
            System.out.println("No students.");
            return;
        }
        for (Student s : students) {
            System.out.println(s);
        }
    }
    public void saveToFile(String filename) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(students);
        } catch (IOException e) {
            System.out.println("Error saving data to file.");
        }
    }
    public void loadFromFile(String filename) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            students = (List<Student>) ois.readObject();
        } catch (Exception e) {
            // Ignore if file not found or error
        }
    }
}

public class StudentManagementMain {
    public static void main(String[] args) {
        StudentManagementSystem sms = new StudentManagementSystem();
        String filename = "students.dat";
        sms.loadFromFile(filename);

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nStudent Management System:");
            System.out.println("1. Add student");
            System.out.println("2. Remove student");
            System.out.println("3. Search student");
            System.out.println("4. Display all students");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");
            int choice = scanner.nextInt(); scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Name: "); String name = scanner.nextLine();
                    System.out.print("Roll Number: "); String roll = scanner.nextLine();
                    System.out.print("Grade: "); String grade = scanner.nextLine();
                    if (name.trim().isEmpty() || roll.trim().isEmpty() || grade.trim().isEmpty()) {
                        System.out.println("All fields are required!");
                    } else {
                        sms.addStudent(new Student(name, roll, grade));
                    }
                    break;
                case 2:
                    System.out.print("Roll Number to remove: ");
                    String rollRemove = scanner.nextLine();
                    sms.removeStudent(rollRemove);
                    break;
                case 3:
                    System.out.print("Roll Number to search: ");
                    String rollSearch = scanner.nextLine();
                    Student found = sms.searchStudent(rollSearch);
                    System.out.println(found == null ? "Not found." : found);
                    break;
                case 4:
                    sms.displayAll();
                    break;
                case 5:
                    sms.saveToFile(filename);
                    System.out.println("Data saved. Exiting.");
                    return;
                default:
                    System.out.println("Invalid choice");
            }
        }
    }
}
