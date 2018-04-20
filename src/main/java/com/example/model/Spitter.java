package com.example.model;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class Spitter {
    @javax.validation.constraints.NotNull(message = "firstName不能为空")
    @Size(max=10,message = "firstName长度必须小于10")
    @Pattern(regexp="[a-z,A-Z]",message = "firstName只允许大小写字母")
    private String firstName;

    @javax.validation.constraints.NotNull(message = "lastName不能为空")
    @Size(max=10,message = "lastName长度必须小于10")
    @Pattern(regexp="[a-z,A-Z]",message = "lastName只允许大小写字母")
    private String lastName;

    @javax.validation.constraints.NotNull(message = "username不能为空")
    @Size(min=5,max=15,message = "username长度必须大于5小于15")
    @Pattern(regexp="[a-z,A-Z,0-9,_]",message = "username只允许大小写字母,数字,下划线")
    private String username;

    @javax.validation.constraints.NotNull(message = "password不能为空")
    @Size(min=5,max=15,message = "password长度必须大于5小于10")
    @Pattern(regexp="\\S",message = "password允许所有非空字符")
    private String password;

    public void setFirstName(String fistName) {
        this.firstName = fistName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {

        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
