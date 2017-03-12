package com.owais.app.service.model;

import javax.persistence.*;

@Entity
@Table(name = "Members")
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    String name;
    String studentId;
    String phoneNo;
    String dateOfBirth;

    public Member() {

    }

    public Member(String name,
                  String studentId,
                  String phoneNo,
                  String dateOfBirth) {
        this.name = name;
        this.studentId = studentId;
        this.phoneNo = phoneNo;
        this.dateOfBirth = dateOfBirth;
    }

    //Getters
    public String getName() {return this.name;}
    public String getStudentId() {return this.studentId;}
    public String getPhoneNo() {return this.phoneNo;}
    public String getDateOfBirth() {return this.dateOfBirth;}
    public Long getId() {return this.id;}

    //Setters
    public void setName(String name) {this.name = name;}
    public void setStudentId(String studentId) {this.studentId = studentId;}
    public void setPhoneNo(String phoneNo) {this.phoneNo = phoneNo;}
    public void setDateOfBirth(String dateOfBirth) {this.dateOfBirth = dateOfBirth;}

    @Override
    public String toString() {
        return "[ " + name + " , " + studentId + " , " + phoneNo + " , " + dateOfBirth + " ]";
    }


}

