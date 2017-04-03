package com.hkarabakla.config;

import com.hkarabakla.model.Book;
import com.hkarabakla.model.User;
import com.hkarabakla.model.UserDetail;
import com.hkarabakla.repository.BookRepository;
import com.hkarabakla.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class ContentInitializer {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private UserRepository userRepository;

    @PostConstruct
    public void init() {

        Book b1 = new Book();
        b1.setName("Spring in action");
        b1.setIsbn("SNB182828");
        b1.setPrice(23.45);

        Book b2 = new Book();
        b2.setName("Javascript in action");
        b2.setIsbn("SNB182328");
        b2.setPrice(21.35);

        Book b3 = new Book();
        b3.setName("Head first java");
        b3.setIsbn("SNB122828");
        b3.setPrice(20.00);

        bookRepository.save(b1);
        bookRepository.save(b2);
        bookRepository.save(b3);

        User u1 = new User();
        UserDetail ud1 = new UserDetail();
        ud1.setEmail("guest@guest.com");
        ud1.setMobilePhone("5555342323");
        ud1.setImageUrl("https://media3.giphy.com/media/m6OomwWCojfS8/giphy.gif");
        u1.setUsername("guest");
        u1.setPassword("Z3Vlc3Q=");
        u1.setUserDetail(ud1);

        userRepository.save(u1);
    }
}
