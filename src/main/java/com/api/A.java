package com.api;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class A {

    public static void main(String[] args) {

//        List<Employee> data = Arrays.asList(
//                new Employee(1, "Mike", 5000),
//                new Employee(2, "Stallin", 4000),
//                new Employee(3, "Adam", 7000),
//                new Employee(4, "Ana", 5000)
//        );
//
////        List<Employee> newData = data.stream().filter(e -> e.getSal() > 4000).collect(Collectors.toList());
////        List<Employee> newData = data.stream().filter(e -> e.getName().startsWith("M")).collect(Collectors.toList());
//
//        //Group by
//        Map<Integer, List<Employee>> newData = data.stream().collect(Collectors.groupingBy(e -> e.getSal()));
//        System.out.println(newData);


//        for(Employee e : newData){
//            System.out.println(e.getId());
//            System.out.println(e.getName());
//            System.out.println(e.getSal());
//        }

//        List<Integer> data = Arrays.asList(10, 20, 13, 15);
////        int sum = data.stream().mapToInt(Integer::intValue).sum();
////        System.out.println(sum);
//
//        // find Max
//        Optional<Integer> maxValue = data.stream().max(Integer::compareTo);
//        System.out.println(maxValue.get());
//
//        int sumEven = data.stream().filter(t -> t % 2 == 0).mapToInt(Integer::intValue).sum();
//        System.out.println(sumEven);

        List<Employee2> employeeList = Arrays.asList(
                new Employee2(1, "mike"),
                new Employee2(2, "stallin"),
                new Employee2(3, "adam")
        );

        // map(e -> mapToDto(e)) : takes object one by one and supply to mapToDto function
        List<EmployeeDto> dtos1 = employeeList.stream().map(e -> mapToDto(e)).collect(Collectors.toList());

        //using method reference => A::mapToDto => classname :: functionName
        List<EmployeeDto> dtos2 = employeeList.stream().map(A::mapToDto).collect(Collectors.toList());

    }

    static EmployeeDto mapToDto(Employee2 employee){

        EmployeeDto dto = new EmployeeDto();
        dto.setId(employee.getId());
        dto.setName(employee.getName());
        return dto;
    }
}
