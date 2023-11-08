package com.enviro.envirobank;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@OpenAPIDefinition(
        info=@Info(
                title = "Enviro Bank",
                description ="Welcome to Enviro Bank! A bank that allows you to withdraw money " +
                        "before you can even make a deposit," +
                        " worse, before you could register even :)" ,
                version = "v1",
                contact = @Contact(
                        name = "Patience",
                        email = "patience.cele@enviro365.co.za",
                        url = "https://www.enviro365.co.za/"
                )
        )
)
public class EnviroBankApplication {

    //Configuring Model Mapper as a spring bean so that it can be injectable anywhere in the application
    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }

    public static void main(String[] args) {
        SpringApplication.run(EnviroBankApplication.class, args);
    }

}
