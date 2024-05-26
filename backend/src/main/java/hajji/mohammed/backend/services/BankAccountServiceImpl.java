package hajji.mohammed.backend.services;

import hajji.mohammed.backend.dtos.CustomerRequestDTO;
import hajji.mohammed.backend.dtos.CustomerResponseDTO;
import hajji.mohammed.backend.entities.*;
import hajji.mohammed.backend.enums.AccountStatus;
import hajji.mohammed.backend.enums.Currency;
import hajji.mohammed.backend.enums.OperationType;
import hajji.mohammed.backend.exception.*;
import hajji.mohammed.backend.repositories.AccountOperationRepository;
import hajji.mohammed.backend.repositories.BankAccountRepository;
import hajji.mohammed.backend.repositories.CustomerRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.Instant;
import java.util.Comparator;
import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class BankAccountServiceImpl implements BankAccountService{

    private final CustomerRepository customerRepository;
    private final BankAccountRepository bankAccountRepository;
    private final AccountOperationRepository accountOperationRepository;
    private final ModelMapper modelMapper;

    @Override
    public CustomerResponseDTO saveCustomer(CustomerRequestDTO request) throws DuplicateEmailException {
        if (customerRepository.existsByEmail(request.getEmail()))
            throw new DuplicateEmailException();

        Customer customer = modelMapper.map(request, Customer.class);
        customerRepository.save(customer);
        return modelMapper.map(customer, CustomerResponseDTO.class);
    }

    @Override
    public CurrentAccount saveCurrentAccount(double initialBalance, Long customerId, double overDraft, Currency currency) throws CustomerNotFoundException, NegativeBalanceException {

        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new CustomerNotFoundException(customerId));

        if (initialBalance < 0) throw new NegativeBalanceException();

        CurrentAccount currentAccount = CurrentAccount.builder()
                .customer(customer)
                .accountStatus(AccountStatus.CREATED)
                .balance(initialBalance)
                .overDraft(overDraft)
                .createdAt(Instant.now())
                .currency(currency)
                .build();

        return bankAccountRepository.save(currentAccount);
    }

    @Override
    public SavingAccount saveSavingAccount(double initialBalance, Long customerId, double interestRate,  Currency currency) throws CustomerNotFoundException, NegativeBalanceException {

        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new CustomerNotFoundException(customerId));

        if (initialBalance < 0) throw new NegativeBalanceException();

        SavingAccount savingAccount = SavingAccount.builder()
                .customer(customer)
                .accountStatus(AccountStatus.CREATED)
                .balance(initialBalance)
                .interestRate(interestRate)
                .createdAt(Instant.now())
                .currency(currency)
                .build();

        return bankAccountRepository.save(savingAccount);
    }

    @Override
    public List<Customer> getCustomerList() {
        return customerRepository.findAll().stream()
                .sorted(Comparator.comparing(Customer::getName))
                .toList();
    }

    @Override
    public BankAccount getBankAccount(String accountId) throws AccountNotFoundException {
        return bankAccountRepository.findById(accountId)
                .orElseThrow(() -> new AccountNotFoundException(accountId));
    }

    @Override
    public List<BankAccount> getBankAccountList() {
        return bankAccountRepository.findAll()
                .stream()
                .sorted(Comparator.comparing(BankAccount::getCreatedAt))
                .toList();

    }

    @Override
    public AccountOperation debit(String accountId, double amount, String desc) throws AccountNotFoundException, BalanceNotSufficientException,  NegativeAmountException {

        BankAccount bankAccount = getBankAccount(accountId);

        if (amount < 0)
            throw new NegativeAmountException();

        if (bankAccount.getBalance() < amount)
            throw new BalanceNotSufficientException();

        AccountOperation accountOperation =  AccountOperation.builder()
                .operationType(OperationType.DEBIT)
                .bankAccount(bankAccount)
                .amount(amount)
                .operationDate(Instant.now())
                .description(desc)
                .build();


        accountOperationRepository.save(accountOperation);
        bankAccount.setBalance(bankAccount.getBalance() - amount);
        bankAccountRepository.save(bankAccount);
        return accountOperation;
    }

    @Override
    public AccountOperation credit(String accountId, double amount, String desc) throws AccountNotFoundException, NegativeAmountException {
        BankAccount bankAccount = getBankAccount(accountId);

        if (amount < 0) throw new NegativeAmountException();

        AccountOperation accountOperation =  AccountOperation.builder()
                .operationType(OperationType.CREDIT)
                .bankAccount(bankAccount)
                .amount(amount)
                .operationDate(Instant.now())
                .description(desc)
                .build();


        accountOperationRepository.save(accountOperation);
        bankAccount.setBalance(bankAccount.getBalance() + amount);
        bankAccountRepository.save(bankAccount);
        return accountOperation;
    }

    @Override
    public AccountOperation transfer(String from, String to, double amount, String desc) throws AccountNotFoundException, NegativeAmountException {
        AccountOperation debit = debit(from, amount, desc);
        credit(to, amount, desc);
        return debit;
    }
}
