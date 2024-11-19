package waterrefillingsalesystem;

public class Admin {
    private final String name;
    private final String username;
    private final String email;
    private String password;  

    public Admin(String name, String username, String email, String password) {
        this.name = name;
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

   
    public void setPassword(String newPassword) {
        this.password = newPassword;
    }
}
