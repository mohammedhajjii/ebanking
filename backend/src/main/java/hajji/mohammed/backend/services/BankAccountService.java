package hajji.mohammed.backend.services;

import hajji.mohammed.backend.dtos.CustomerRequestDTO;
import hajji.mohammed.backend.dtos.CustomerResponseDTO;
import hajji.mohammed.backend.entities.*;
import hajji.mohammed.backend.enums.Currency;
import hajji.mohammed.backend.exception.*;

import java.util.List;

public interface BankAccountService {

    CustomerResponseDTO saveCustomer(CustomerRequestDTO request) throws DuplicateEmailException;
    CurrentAccount saveCurrentAccount(double initialBalance, Long customerId, double overDraft, Currency currency) throws CustomerNotFoundException, NegativeBalanceException;
    SavingAccount saveSavingAccount(double initialBalance, Long customerId, double interestRate, Currency currency) throws CustomerNotFoundException, NegativeBalanceException;

    List<Customer> getCustomerList();

    BankAccount getBankAccount(String accountId) throws AccountNotFoundException;

    List<BankAccount> getBankAccountList();

    AccountOperation debit(String accountId, double amount, String desc) throws AccountNotFoundException, NegativeAmountException;
    AccountOperation credit(String accountId, double amount, String desc) throws AccountNotFoundException, NegativeAmountException;
    AccountOperation transfer(String from, String to, double amount, String desc) throws AccountNotFoundException, NegativeAmountException;
}
