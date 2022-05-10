package application;

import domain.entities.Employee;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Program {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter full file path: ");
        String path = sc.nextLine();

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            List<Employee> list = new ArrayList<>();

            String line = br.readLine();
            while (line != null) {
                String[] fields = line.split(",");
                list.add(new Employee(fields[0], fields[1], Double.parseDouble(fields[2])));
                line = br.readLine();
            }

            System.out.print("Enter salary: ");
            double s = sc.nextDouble();

            List<String> dataEmployee = list.stream()
                    .filter(p -> p.getSalary() > s)
                    .map(p -> p.getEmail()).sorted()
                    .collect(Collectors.toList());

            dataEmployee.forEach(System.out::println);
            System.out.println("");

            List<Double> sumSalary = list.stream()
                    .filter(p -> p.getName().contains("M"))
                    .map(p -> p.getSalary())
                    .collect(Collectors.toList());

            double result = list.stream()
                    .filter(p -> p.getName().contains("M"))
                    .mapToDouble(Employee::getSalary)
                    .sum();

            System.out.println("Sum of salary of people whose name starts with 'M': " + result);

        } catch (IOException e) {
            System.out.println("Error :" + e.getMessage());
        }
        sc.close();
    }
}
