package com.otta.library.user.model;

import java.util.Objects;

public class UserInformation {
    private String name;
    private String email;
    private String password;

    public UserInformation() {
        // Do nothing.
    }

    public UserInformation(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, name, password);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof UserInformation)) {
            return false;
        }
        UserInformation other = (UserInformation) obj;
        return Objects.equals(email, other.email) && Objects.equals(name, other.name)
                && Objects.equals(password, other.password);
    }

}
