package data;

public class Member {

    String id;
    String name;
    String email;

    public Member(String id, String name, String email)
    {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public java.lang.String getId() {
        return id;
    }

    public void setId(java.lang.String id) {
        this.id = id;
    }

    public java.lang.String getName() {
        return name;
    }

    public void setName(java.lang.String name) {
        this.name = name;
    }

    public java.lang.String getEmail() {
        return email;
    }

    public void setEmail(java.lang.String email) {
        this.email = email;
    }
}
