package nohagim.Entities;

import javax.persistence.*;
import javax.persistence.Entity;
import java.time.LocalDate;

enum LicenceClass{A,A1,A2,B,C,C1,D,D1,D2,D3}

@Entity
@Table (name = "DRIVERS")
public class Driver {
    /*Properties data-members*/
    private int id;
    private String firstName;
    private String lastName;
    private int licenceId;
    private LicenceClass lc;
    private LocalDate licenceIssuingDate;
    private LocalDate licenceExpDate;
    private LocalDate healthDeclarationExpDate;

    /*Getters&Setters:*/
    @Id
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public int getLicenceId() {
        return licenceId;
    }
    public void setLicenceId(int licenceId) {
        this.licenceId = licenceId;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "LicenceClass")
    public LicenceClass getLc() {
        return lc;
    }
    public void setLc(LicenceClass lc) {
        this.lc = lc;
    }

    public LocalDate getLicenceIssuingDate() {
        return licenceIssuingDate;
    }
    public void setLicenceIssuingDate(LocalDate licenceIssuingDate) {
        this.licenceIssuingDate = licenceIssuingDate;
    }
    public LocalDate getLicenceExpDate() {
        return licenceExpDate;
    }
    public void setLicenceExpDate(LocalDate licenceExpDate) {
        this.licenceExpDate = licenceExpDate;
    }
    public LocalDate getHealthDeclarationExpDate() {
        return healthDeclarationExpDate;
    }
    public void setHealthDeclarationExpDate(LocalDate healthDeclarationExpDate) {
        this.healthDeclarationExpDate = healthDeclarationExpDate;
    }
}
