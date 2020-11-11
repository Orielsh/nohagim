package nohagim.Entities;

import javax.persistence.*;
import javax.persistence.Entity;
import java.sql.Blob;
import java.util.Date;

@Entity
@Table(name = "\"Customers\"")
public class Customer {

    /*Data members*/
    private Integer id;
    private String name;
    private String contactPerson;
    private String address;
    private Integer phoneNumber;
    private Integer faxNumber;
    private String email;
    private Date joinDate;
    private String professionalManagerName; //todo: need also id (picture?) and valid certificate (doc?/image).
    private Blob transportationLicence;
    //TransportationLicence .. //todo need to complete - validity and, expiration date, document.
    //Contract - picture? / PDF?


    /*Getters & Setters*/
    @Id
    public Integer getId() {return id;}
    public void setId(Integer id) {this.id = id;}

    @Column(name = "\"Name\"", nullable = false)
    public String getName() {return name;}
    public void setName(String name) {this.name = name;}

    @Column(name = "\"Contact Person\"")
    public String getContactPerson() {return contactPerson;}
    public void setContactPerson(String contactPerson) {this.contactPerson = contactPerson;}

    @Column(name = "\"Adress\"")
    public String getAddress(){return address;}
    public void setAddress(String address){this.address = address;}

    @Column(name = "\"PhoneNumber\"")
    public Integer getPhoneNumber(){return phoneNumber;}
    public void setPhoneNumber(Integer phoneNumber){this.phoneNumber = phoneNumber;}

    @Column(name = "\"FaxNumber\"")
    public Integer getFaxNumber() {return faxNumber;}
    public void setFaxNumber(Integer faxNumber) {this.faxNumber = faxNumber;}

    @Column(name = "\"E-Mail\"")
    public String getEmail() {return email;}
    public void setEmail(String email) {this.email = email;}

    @Basic
    @Temporal(TemporalType.DATE)
    @Column(name = "\"JoinDate\"", nullable = false)
    public Date getJoinDate() {return joinDate;}
    public void setJoinDate(Date joinDate) {this.joinDate = joinDate;}

    public String getProfessionalManagerName() {return professionalManagerName;}
    public void setProfessionalManagerName(String professionalManagerName) {this.professionalManagerName = professionalManagerName;}

    @Lob
    @Column(name = "\"TransportationDoc\"")
    public Blob getTransportationLicence() {return transportationLicence;}
    public void setTransportationLicence(Blob transportationLicence) {this.transportationLicence = transportationLicence;}
}
