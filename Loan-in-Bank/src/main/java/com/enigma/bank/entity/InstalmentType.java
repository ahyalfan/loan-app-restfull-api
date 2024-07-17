package com.enigma.bank.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Table(name = "m_instalment_type")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class InstalmentType {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Enumerated(EnumType.STRING)
    private EInstalmentType instalmentType;
    @OneToMany(mappedBy = "instalmentType")
    private Set<Loan> loans;

    public enum EInstalmentType {
        ONE_MONTH,
        THREE_MONTHS,
        SIXTH_MONTHS,
        NINE_MONTHS,
        TWELVE_MONTHS
    }
}
