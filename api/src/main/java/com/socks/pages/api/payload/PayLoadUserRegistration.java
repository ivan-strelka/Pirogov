package com.socks.pages.api.payload;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.ILoggerFactory;

import javax.annotation.Generated;

@Slf4j
public class PayLoadUserRegistration {

    @JsonProperty("password")
    private String password;

    @JsonProperty("email")
    private String email;

    @JsonProperty("username")
    private String username;

    @JsonProperty("lastname")
    private String lastname;

    @JsonProperty("firstname")
    private String firstname;


    public String getPassword() {

        return password;
    }

    public void setPassword(String password) {

        this.password = password;
    }

    public String getEmail() {

        return email;
    }

    public void setEmail(String email) {

        this.email = email;
    }

    public String getUsername() {

        return username;
    }

    public void setUsername(String username) {

        this.username = username;
    }

    public String getLastname() {

        return lastname;
    }

    public void setLastname(String lastname) {

        this.lastname = lastname;
    }

    public String getFirstname() {

        return firstname;
    }

    public void setFirstname(String firstname)
    {
        this.firstname = firstname;
    }

    @Override
    public String toString() {
        return
                "Response{" +
                        "password = '" + password + '\'' +
                        ",email = '" + email + '\'' +
                        ",username = '" + username + '\'' +
                        ",firstname = '" + firstname + '\'' +
                        ",lastname = '" + lastname + '\'' +
                        "}";
    }


}
