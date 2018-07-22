package entity;

public class FileToTable {
    private int id;
    private String name;
    private String location;
    private String size;
    
    public FileToTable() {
    }
    
    public FileToTable(String name, String location, String size) {
        this.name = name;
        this.location = location;
        this.size = size;
    }
    
    public FileToTable(int id, String name, String location, String size) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.size = size;
    }
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getLocation() {
        return location;
    }
    
    public void setLocation(String location) {
        this.location = location;
    }
    
    public String getSize() {
        return size;
    }
    
    public void setSize(String size) {
        this.size = size;
    }
}
