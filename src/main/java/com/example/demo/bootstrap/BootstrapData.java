package com.example.demo.bootstrap;

import com.example.demo.domain.Author;
import com.example.demo.domain.Book;
import com.example.demo.domain.Publisher;
import com.example.demo.repository.AuthorRepository;
import com.example.demo.repository.BookRepository;
import com.example.demo.repository.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootstrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootstrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Started n Bootstrap");
        Publisher blooms=new Publisher();
        blooms.setName("Bloomburry");
        blooms.setAddress("London");
        publisherRepository.save(blooms);
        Author eric=new Author("Eric","Evans");
        Book ddd=new Book("Domain Driven Design","123123");

        eric.getBooks().add(ddd);
        ddd.getAuthors().add(eric);
        ddd.setPublisher(blooms);
        blooms.getBooks().add(ddd);

        System.out.println("Publisher count: "+publisherRepository.count());
        authorRepository.save(eric);
        bookRepository.save(ddd);
        publisherRepository.save(blooms);


        Author rod=new Author("Rod","Johnson");
        Book noEJB=new Book("J2EE Development without EJB","3939459459");
        rod.getBooks().add(noEJB);
        noEJB.getAuthors().add(rod);
        noEJB.setPublisher(blooms);
        blooms.getBooks().add(noEJB);

        authorRepository.save(rod);
        bookRepository.save(noEJB);
        publisherRepository.save(blooms);

        System.out.println("Number of books "+bookRepository.count());
        System.out.println("Publisher books "+ blooms.getBooks().size());
    }
}
