package com.client.client.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Getter
@Setter
public class Record {

    @Id
    private Long id;
    private String status;
    private Double amount;
    private Integer applicationSignedHour;
    private Integer applicationSignedWeekday;
    private String city;
    private String country;
    private String creditScoreEsEquifaxRisk;
    @JsonFormat(pattern="dd/MM/yyyy")
    private Date dateOfBirth;
    private Double debtToIncome;
    private Integer education;
    private String employmentDurationCurrentEmployer;
    private String employmentPosition;
    private Integer employmentStatus;
    private Integer existingLiabilities;
    private Integer gender;
    private Integer homeOwnershipType;
    private Integer incomeFromPrincipalEmployer;
    private Integer incomeTotal;
    private Double interestRateAPR;
    private Date loanDate;
    private Integer loanDuration;
    private Integer maritalStatus;
    private Boolean newCreditCustomer;
    private Integer noOfPreviousLoansBeforeLoan;
    private Integer occupationArea;
    private Integer useOfLoan;
    private Integer verificationType;
    private String workExperience;
    private Double previousScore;
    private Boolean defaulted;
    private Date defaultDate;


    @Override
    public String toString() {
        return "Record{" +
                "id='" + id + '\'' +
                ", status='" + status + '\'' +
                ", amount='" + amount + '\'' +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                ", education='" + education + '\'' +
                ", gender='" + gender + '\'' +
                '}';
    }
}
