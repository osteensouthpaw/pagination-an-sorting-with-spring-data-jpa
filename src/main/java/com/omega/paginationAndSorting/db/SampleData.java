package com.omega.paginationAndSorting.db;

import com.github.javafaker.Faker;
import com.omega.paginationAndSorting.address.Address;
import com.omega.paginationAndSorting.user.UserRepository;
import com.omega.paginationAndSorting.user.User;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.IntStream;

@Component
public class SampleData implements CommandLineRunner {
    private final Faker faker = new Faker();
    private final UserRepository userRepository;

    public SampleData(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        List<User> people = IntStream.rangeClosed(1, 100)
                .mapToObj(user -> new User(
                        faker.name().firstName(),
                        faker.name().lastName(),
                        faker.internet().emailAddress(),
                        faker.phoneNumber().cellPhone(),
                        new Address(
                                faker.address().streetAddress(),
                                faker.address().city(),
                                faker.address().state(),
                                faker.address().zipCode()
                        )
                ))
                .toList();

        System.out.println(userRepository.saveAll(people));
    }
}
