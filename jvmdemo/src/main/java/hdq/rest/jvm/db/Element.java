package hdq.rest.jvm.db;

import hdq.rest.jvm.User;

import java.util.Date;
import java.util.UUID;

//	entity
public class Element {
    // ID 内部生成算了
    private String id;
    private String time;
    private String state;
    private String type;
    private String price;
    private String currencyType;
    private User user;

    public Element() {
        this.id = UUID.randomUUID().toString();
        // 自己改用新API吧
        this.time = new Date().toLocaleString();
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getId() {
        return id;
    }

    public String getTime() {
        return time;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getCurrencyType() {
        return currencyType;
    }

    public void setCurrencyType(String currencyType) {
        this.currencyType = currencyType;
    }


}
