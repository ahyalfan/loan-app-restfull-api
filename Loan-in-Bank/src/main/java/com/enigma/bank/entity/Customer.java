package com.enigma.bank.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "m_customer")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String firstName;
    private String lastName;
    private String phone;
    @Temporal(TemporalType.TIMESTAMP)
    private Date birth_day;
    private String status;
    private boolean deleted;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
    @OneToMany(mappedBy = "customers")
    private Set<LoanDocument> loanDocuments;
    @OneToMany(mappedBy = "customers")
    private Set<Loan> loans;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return deleted == customer.deleted && Objects.equals(id, customer.id) && Objects.equals(firstName, customer.firstName) && Objects.equals(lastName, customer.lastName) && Objects.equals(phone, customer.phone) && Objects.equals(birth_day, customer.birth_day) && Objects.equals(status, customer.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, phone, birth_day, status, deleted);
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id='" + id + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phone='" + phone + '\'' +
                ", birth_day=" + birth_day +
                ", status='" + status + '\'' +
                ", deleted=" + deleted +
                '}';
    }
}
