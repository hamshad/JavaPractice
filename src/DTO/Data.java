package DTO;

public class Data {

    Long Id;
    String Name, Desc;

    public Data(Long Id, String Name, String Desc) {
        this.Id = Id;
        this.Name = Name;
        this.Desc = Desc;
    }

    public Long getId() {
        return Id;
    }

    public String getName() {
        return Name;
    }

    public String getDesc() {
        return Desc;
    }

}
