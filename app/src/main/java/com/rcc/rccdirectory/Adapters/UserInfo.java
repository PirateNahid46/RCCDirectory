package com.rcc.rccdirectory.Adapters;

public class UserInfo {
    public String cn, name;

    public UserInfo(String cn, String name) {
        this.cn = cn;
        this.name = name;
    }

    public String getCn() {
        return cn;
    }

    public void setCn(String cn) {
        this.cn = cn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
