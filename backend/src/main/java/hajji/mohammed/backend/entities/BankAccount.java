package hajji.mohammed.backend.entities;

import hajji.mohammed.backend.enums.AccountStatus;
import hajji.mohammed.backend.enums.Currency;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.UuidGenerator;

import java.time.Instant;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type", length = 2, discriminatorType = DiscriminatorType.STRING)
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor@SuperBuilder
public abstract class BankAccount {
    @Id
    @UuidGenerator(style = UuidGenerator.Style.TIME)
    private String id;
    private Instant createdAt;
    private double balance;
    @Enumerated(EnumType.STRING)
    private Currency currency;
    @Enumerated(EnumType.STRING)
    private AccountStatus accountStatus;
    @ManyToOne
    private Customer customer;

    @OneToMany(mappedBy = "bankAccount", fetch = FetchType.LAZY)
    private List<AccountOperation> operations;
}
