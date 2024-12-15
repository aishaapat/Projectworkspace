package org.example.projectworkspace.Authentication;

public class User {

    // Fields representing the user details corresponding to database columns

    private String firstname;
    private String lastname;
    private String password;
    private String zipcode;
    private String ssn;
    private String address;
    private String state;
    private String email;
    private String question;
    private String answer;
    private String type;  // e.g., admin or customer
    private String username;
    private int uid;

    // Constructor to initialize the user object

    public User(String firstname, String lastname, String password, String zipcode,
                String ssn, String address, String state, String email,
                String question, String answer, String type, String username, int userID ) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.password = password;
        this.zipcode = zipcode;
        this.ssn = ssn;
        this.address = address;
        this.state = state;
        this.email = email;
        this.question = question;
        this.answer = answer;
        this.type = type;
        this.username = username;
        this.uid = uid;
    }

    // Getters and Setters for each field

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getSsn() {
        return ssn;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getUserID() {
        return uid;
    }

    public void set(int uid) {
        this.uid = uid ;
    }

    // Optional: Override toString() for easy debugging or displaying user info
    @Override
    public String toString() {
        return "User{" +
                "firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", email='" + email + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}