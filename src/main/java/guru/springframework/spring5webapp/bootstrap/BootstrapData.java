package guru.springframework.spring5webapp.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
import guru.springframework.spring5webapp.domain.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;

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
        Publisher abcPublisherHouse = new Publisher("ABC Publisher House", "Billing road", "Pheonix", "Arizona", "123412");
        publisherRepository.save(abcPublisherHouse);
        
        Author eric = new Author("Eric", "Evans");
        Book ddd = new Book("Domain driven design", "1234343");
        ddd.setPublisher(abcPublisherHouse);
        
        eric.getBooks().add(ddd);
        ddd.getAuthors().add(eric);
        abcPublisherHouse.getBooks().add(ddd);
        
        authorRepository.save(eric);
        bookRepository.save(ddd);
        
        Author rod = new Author("Rod", "johnson");
        Book noEJB = new Book("J2EE framework without EJB", "3432123");
        noEJB.setPublisher(abcPublisherHouse);
        
        rod.getBooks().add(noEJB);
        noEJB.getAuthors().add(rod);
        abcPublisherHouse.getBooks().add(noEJB);

        authorRepository.save(rod);
        bookRepository.save(noEJB);

        System.out.println("Bootstrap data result:");
        System.out.println(bookRepository.count());

        System.out.println("Bootstrap publisher data: ");
        System.out.println("No of books published by publisher: " + abcPublisherHouse.getBooks().size());
    }
}
