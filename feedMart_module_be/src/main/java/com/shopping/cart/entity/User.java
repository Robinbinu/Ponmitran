package com.shopping.cart.entity;

import java.util.Objects;

import jakarta.persistence.*;

@Entity
@Table(name = "user_data")
public class User {
    private @Id @GeneratedValue long id;
    
    @Column(nullable = false)
    private String username;
    @Column(nullable = false)
    private String password;
    
    private  boolean loggedIn;

    public User() {
    }

    public User(String username, 
                String password) {
        this.username = username;
        this.password = password;
        this.loggedIn = false;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
		this.id = id;
	}

	public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return Objects.equals(username, user.username) &&
                Objects.equals(password, user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, password, 
                            loggedIn);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", loggedIn=" + loggedIn +
                '}';
    }
}