package com.RandomNumber.RandomNumber.entity;
import jakarta.persistence.*;

import java.math.BigInteger;

@Entity
@Table(name="RandomNumber")
public class randomNumber {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;

    @Column(name = "Num")
    private int Num;
    @Column(name = "DoubleNum")
    private long DoubleNum;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNum() {
        return Num;
    }

    public void setNum(int num) {
        Num = num;
    }

    public long getDoubleNum() {
        return DoubleNum;
    }

    public void setDoubleNum(long DoubleNum) {
        DoubleNum = DoubleNum;
    }
}
