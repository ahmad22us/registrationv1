package com.api;

public class Employee {

    private long id;
    private String name;
    private int sal;

    //setter
    public Employee(long id, String name, int sal) {
        this.id = id;
        this.name = name;
        this.sal = sal;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSal(int sal) {
        this.sal = sal;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getSal() {
        return sal;
    }
}
