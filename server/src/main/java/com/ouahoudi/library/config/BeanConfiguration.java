package com.ouahoudi.library.config;

import com.ouahoudi.library.application.service.BookScoringService;
import com.ouahoudi.library.application.service.BookService;
import com.ouahoudi.library.application.service.OpenLibraryService;
import com.ouahoudi.library.application.usecase.book.*;
import com.ouahoudi.library.domain.model.port.out.book.BookRepositoryPort;
import com.ouahoudi.library.domain.model.port.out.book.BookScoringRulePort;
import com.ouahoudi.library.infrastructure.adapter.BookRepositoryAdapter;
import com.ouahoudi.library.infrastructure.adapter.BookScoringRuleAdapter;
import com.ouahoudi.library.infrastructure.repository.JpaAuthorRepository;
import com.ouahoudi.library.infrastructure.repository.JpaBookRepository;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class BeanConfiguration {

    @Bean
    public BookRepositoryPort bookRepositoryPort(JpaBookRepository jpaBookRepo, JpaAuthorRepository jpaAuthorRepo) {
        return new BookRepositoryAdapter(jpaBookRepo, jpaAuthorRepo);
    }

    @Bean
    public BookScoringRulePort bookScoringRulePort() {
        return new BookScoringRuleAdapter();
    }

    @Bean
    public CreateBookUseCase bookUseCase(BookRepositoryPort port) {
        return new BookService(port);
    }

    @Bean
    public FindBookByTitleUseCase findBookUseCase(BookRepositoryPort port) {
        return new BookService(port);
    }

    @Bean
    public FindBookByIdUseCase findBookByIdUseCase(BookRepositoryPort port) {
        return new BookService(port);
    }

    @Bean
    public FindAuthorByBookIdsUseCase findAuthorByBookIdsUseCase(BookRepositoryPort port) {
        return new BookService(port);
    }

    @Bean
    public OpenLibraryService openLibraryService() {
        return new OpenLibraryService();
    }


    @Bean
    public UpdateBookUseCase updateBookUseCase(BookRepositoryPort port) {
        return new BookService(port);
    }

    @Bean
    public DeleteBookUseCase deleteBookUseCase(BookRepositoryPort port) {
        return new BookService(port);
    }

    @Bean
    public GiveBookScoringUseCase giveBookScoringUseCase(BookScoringRulePort rule) {
        return new BookScoringService(rule);
    }

    @Bean
    public ApplicationRunner runner(ApplicationContext ctx) {
        return args -> {
            System.out.println("Beans disponibles :");
            Arrays.stream(ctx.getBeanDefinitionNames())
                    .filter(name -> name.contains("Book"))
                    .forEach(System.out::println);
        };
    }
}
