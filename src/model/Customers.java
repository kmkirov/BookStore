package model;

public class Customers {

    private String Name;
    private Integer Id;

    public String getName() {
        return Name;
    }

    public Integer getId() {
        return Id;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public Customers(String name, Integer id) {
        Name = name;
        Id = id; 
    }
    public void print()
    {
        System.out.printf("CustomerID: %d, Name: %s \n",Id,Name);
    }
}
