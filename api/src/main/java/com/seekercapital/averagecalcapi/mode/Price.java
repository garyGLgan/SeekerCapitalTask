package com.seekercapital.averagecalcapi.mode;

import java.io.Serializable;
import java.util.Objects;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Price implements Serializable {

    @Id
    private String id;

    private int price;

    private long createAt;

    public Price(){
    }

    public Price(int price, long createAt) {
        this.price = price;
        this.createAt = createAt;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public long getCreateAt() {
        return createAt;
    }

    public void setCreateAt(long createAt) {
        this.createAt = createAt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString(){
        return new StringBuilder().append("Price(").append(price).append(",").append(createAt).append(")").toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Price price1 = (Price) o;
        return price == price1.price &&
                createAt == price1.createAt ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, price, createAt);
    }
}
