package model;

public class Customers {

    private String Name;
    private String Id;

    public String getName() {
        return Name;
    }

    public String getId() {
        return Id;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setId(String id) {
        Id = id;
    }

    public Customers(String name, String id) {
        Name = name;
        Id = id;
    }
}
