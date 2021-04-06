package nohagim.Entities;

import javax.persistence.*;
import javax.persistence.Entity;
import java.sql.Blob;
import java.time.LocalDate;

@Entity
@Table(name = "\"Customers\"")
public class Customer implements nohagim.Entities.Entity {
    //todo - test: if all fields assigned.
    /*Data members*/
    private Integer id;
    private String name;
    private String contactPerson;
    private String address;
    private Integer phoneNumber;
    private Integer faxNumber;
    private String email;
    private LocalDate joinDate;
    private String professionalManagerName; //todo: need also id (picture?) and valid certificate (doc?/image).
    private Blob professionalManagerDoc;
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

    @Column(name = "\"Address\"")
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
    @Column(name = "\"JoinDate\"", nullable = false)
    public LocalDate getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(LocalDate joinDate) {
        this.joinDate = joinDate;
    }

    @Column(name = "\"ProfessionalManagerName\"")
    public String getProfessionalManagerName() {return professionalManagerName;}
    public void setProfessionalManagerName(String professionalManagerName) {this.professionalManagerName = professionalManagerName;}

    @Lob
    @Column(name = "\"ProfessionalManagerDoc\"")
    public Blob getProfessionalManagerDoc() {
        return professionalManagerDoc;
    }

    public void setProfessionalManagerDoc(Blob professionalManagerDoc) {
        this.professionalManagerDoc = professionalManagerDoc;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
