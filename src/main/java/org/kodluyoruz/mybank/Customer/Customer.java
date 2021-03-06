package org.kodluyoruz.mybank.Customer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.kodluyoruz.mybank.Account.PrimaryAccount.PrimaryAccount;
import org.kodluyoruz.mybank.Account.SavingAccount.SavingAccount;
import org.kodluyoruz.mybank.Card.BankCard.BankCard;
import org.kodluyoruz.mybank.Card.CreditCard.CreditCard;
import org.kodluyoruz.mybank.Recipient.Recipient;

import javax.persistence.*;
import java.util.List;

@Builder
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id", nullable = false, updatable = false)
    private Long Id;
    private String name;
    private String surname;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Recipient> recipientList;

    @OneToOne(cascade ={CascadeType.DETACH,CascadeType.REMOVE})
    @JoinColumn(name = "primary_account_id", referencedColumnName = "Id")
    private PrimaryAccount primaryAccount;

    @OneToOne(mappedBy = "customerId",cascade ={CascadeType.DETACH,CascadeType.REMOVE})
    private CreditCard creditCard;

    @OneToOne(mappedBy = "customerId",cascade ={CascadeType.DETACH,CascadeType.REMOVE})
    private BankCard bankCard;

    @OneToOne(cascade ={CascadeType.DETACH,CascadeType.REMOVE})
    @JoinColumn(name="saving_account_id", referencedColumnName = "Id")
    private SavingAccount savingAccount;

    public CustomerDto toCustomerDto(){
         return CustomerDto.builder().Id(this.Id).name(this.name).surname(this.surname).
                 build();
     }

}
