package hajji.mohammed.backend.services;

import hajji.mohammed.backend.dtos.CustomerRequestDTO;
import hajji.mohammed.backend.dtos.CustomerResponseDTO;
import hajji.mohammed.backend.dtos.InnerDTO;
import hajji.mohammed.backend.dtos.TestDTO;
import hajji.mohammed.backend.entities.Customer;
import org.modelmapper.Converter;
import org.modelmapper.ExpressionMap;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.modelmapper.builder.ConfigurableConditionExpression;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.TreeSet;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper(){
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.createTypeMap(Customer.class, TestDTO.class)
                .addMappings(mapper -> mapper.map(Customer::getId, TestDTO::setId))
                .addMappings(mapper -> mapper.<String>map(Customer::getName, (dest, v) -> dest.getInnerDTO().setName(v)))
                .addMappings(mapper -> mapper.<String>map(Customer::getEmail, (dest, v) -> dest.getInnerDTO().setEmail(v)));

        return new ModelMapper();
    }
}
