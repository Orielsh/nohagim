package nohagimb.Entities;

import java.time.LocalDate;

public class Driver {
    /*Properties data-members*/
    private int id;
    private String firstName;
    private String lastName;
    private int licenceId;
    private enum licenceClass{A,A1,A2,B,C,C1,D,D1,D2,D3}
    private LocalDate licenceIssuingDate;
    private LocalDate licenceExpDate;
    private LocalDate healthDeclarationExpDate;
}
