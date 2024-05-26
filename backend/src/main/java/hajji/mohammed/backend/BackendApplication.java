package hajji.mohammed.backend;

import hajji.mohammed.backend.dtos.CustomerRequestDTO;
import hajji.mohammed.backend.dtos.CustomerResponseDTO;
import hajji.mohammed.backend.dtos.TestDTO;
import hajji.mohammed.backend.entities.*;
import hajji.mohammed.backend.enums.AccountStatus;
import hajji.mohammed.backend.enums.Currency;
import hajji.mohammed.backend.enums.OperationType;
import hajji.mohammed.backend.repositories.AccountOperationRepository;
import hajji.mohammed.backend.repositories.BankAccountRepository;
import hajji.mohammed.backend.repositories.CustomerRepository;
import hajji.mohammed.backend.services.BankAccountService;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.ui.Model;

import java.time.Instant;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@SpringBootApplication
public class BackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(BackendApplication.class, args);
    }


    @Bean
    CommandLineRunner start(BankAccountService bankAccountService, ModelMapper modelMapper){
        return args -> {
            Stream.of("Amine", "ismail", "houssam")
                    .forEach(name -> {
                        CustomerRequestDTO requestDTO = CustomerRequestDTO.builder()
                                .name(name)
                                .email(name + "@gmail.com")
                                .build();
                        bankAccountService.saveCustomer(requestDTO);
                    });

            bankAccountService.getCustomerList()
                     .stream()
                     .map(Customer::getId)
                     .forEach(customerID -> {
                         bankAccountService.saveCurrentAccount(Math.random() * 200000, customerID, Math.random() * 5000, Currency.MAD);
                         bankAccountService.saveSavingAccount(Math.random() * 200000, customerID, Math.random(), Currency.MAD);
                     });

            bankAccountService.getBankAccountList()
                     .forEach(bankAccount -> {
                         bankAccountService.credit(bankAccount.getId(), Math.random() * 40, "credit");
                         bankAccountService.debit(bankAccount.getId(), Math.random() * 40, "debit");
                     });




            bankAccountService.getCustomerList()
                    .stream()
                    .map(customer -> modelMapper.map(customer, TestDTO.class))
                    .forEach(customerResponseDTO -> {
                        System.out.println(customerResponseDTO);
                        /*
                        System.out.println("id: " + customerResponseDTO.getId());
                        System.out.println("name: " + customerResponseDTO.getInnerDTO().getName());
                        System.out.println("email: " + customerResponseDTO.getInnerDTO().getEmail());

                         */
                        System.out.println("---------------------------");
                    });



        };
    }

}
