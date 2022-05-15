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
        Author eric = new Author("Eric", "Evans");
        Book ddd = new Book("Domain driven design", "1234343");
        
        eric.getBooks().add(ddd);
        ddd.getAuthors().add(eric);
        
        authorRepository.save(eric);
        bookRepository.save(ddd);
        
        Author rod = new Author("Rod", "johnson");
        Book noEJB = new Book("J2EE framework without EJB", "3432123");
        
        rod.getBooks().add(noEJB);
        noEJB.getAuthors().add(rod);

        authorRepository.save(rod);
        bookRepository.save(noEJB);

        System.out.println("Bootstrap data result:");
        System.out.println(bookRepository.count());

        Publisher abcPublisherHouse = new Publisher("ABC Publisher House", "Billing road", "Pheonix", "Arizona", "123412");
        Publisher jimPublications = new Publisher("Jim Publications", "Trading center", "George town", "Arizona", "435134");
        
        publisherRepository.save(abcPublisherHouse);
        publisherRepository.save(jimPublications);

        System.out.println("Bootstrap publisher data: ");
        System.out.println(publisherRepository.count());
    }
}
