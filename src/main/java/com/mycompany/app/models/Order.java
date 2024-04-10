package com.mycompany.app.models;

import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;

import java.util.Date;
import java.util.List;

public class Order {
    @BsonId
    private ObjectId id;
    @BsonProperty("user_id")
    private ObjectId user_id;
    @BsonProperty("full_name")
    private String fullName;
    @BsonProperty("goods_id")
    private List<ObjectId> goods;
    @BsonProperty("cost")
    private Float cost;
    @BsonProperty("address")
    private Address address;
    @BsonProperty("date")
    private Date date;
    @BsonProperty("status")
    private String status; // accepted, completed, declined

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public ObjectId getUser_id() {
        return user_id;
    }

    public void setUser_id(ObjectId user_id) {
        this.user_id = user_id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public List<ObjectId> getGoods() {
        return goods;
    }

    public void setGoods(List<ObjectId> goods) {
        this.goods = goods;
    }

    public Float getCost() {
        return cost;
    }

    public void setCost(Float cost) {
        this.cost = cost;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", user_id=" + user_id +
                ", fullName='" + fullName + '\'' +
                ", goods=" + goods +
                ", cost=" + cost +
                ", address=" + address +
                ", date=" + date +
                ", status='" + status + '\'' +
                '}';
    }
}