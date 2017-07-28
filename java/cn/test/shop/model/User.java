package cn.test.shop.model;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import cn.test.shop.controller.validation.ValidGroup1;

public class User {
    private Integer uid;
    
    @NotEmpty(message="{user.message.username.notempty}",groups={ValidGroup1.class})
    private String username;
    
    @NotEmpty(message="{user.message.password.notempty}",groups={ValidGroup1.class})
    private String password;
    
    @NotEmpty(message="{user.message.name.notempty}",groups={ValidGroup1.class})
    private String name;
    
    @NotEmpty(message="{user.message.email.notempty}",groups={ValidGroup1.class})
    @Email(message="{user.message.email.email}",groups={ValidGroup1.class})
    private String email;
    
    
    @Pattern(regexp = "^((13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(18[0,5-9]))\\d{8}$",
    	message="{user.message.phone.pattern}",groups={ValidGroup1.class})
    private String phone;

    @NotEmpty(message="{user.message.addr.notempty}",groups={ValidGroup1.class})
    private String addr;

    private Integer state;

    private String code;

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr == null ? null : addr.trim();
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }
}