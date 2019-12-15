package com.otta.movies.user.model;

import java.util.Objects;

public class UserShow {
    private String name;
    private String email;

    public UserShow() {
        // Do nothing.
    }

    public UserShow(String name, String email) {
        this.name = name;
        this.email = email;
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

    @Override
    public int hashCode() {
        return Objects.hash(email, name);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof UserShow)) {
            return false;
        }
        UserShow other = (UserShow) obj;
        return Objects.equals(email, other.email) && Objects.equals(name, other.name);
    }

}
