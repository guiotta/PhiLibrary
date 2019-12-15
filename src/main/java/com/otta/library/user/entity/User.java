package com.otta.library.user.entity;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "User")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @NotBlank(message = "Name is mandatory")
    @Column(name = "name")
    private String name;
    @NotBlank(message = "E-mail is mandatory")
    @Column(name = "email")
    private String email;
    @NotBlank(message = "Password is mandatory")
    @Column(name = "password")
    private String password;
    @Column(name = "active")
    private int active;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_role")
    private Role role;

    @Version
    @Column(name = "version")
    private int version;

    public User() {
        // Do nothing
    }

    public User(String name, String email, String password, int active, Role role) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.active = active;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    @Override
    public int hashCode() {
        return Objects.hash(active, email, id, name, password, role, version);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof User)) {
            return false;
        }
        User other = (User) obj;
        return active == other.active && Objects.equals(email, other.email) && Objects.equals(id, other.id)
                && Objects.equals(name, other.name) && Objects.equals(password, other.password)
                && Objects.equals(role, other.role) && version == other.version;
    }

   
}
