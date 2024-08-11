package com.nsilva.project.config;

import com.nsilva.project.entities.Post;
import com.nsilva.project.entities.User;
import com.nsilva.project.repository.PostRepository;
import com.nsilva.project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

@Configuration
public class ConfigTest implements CommandLineRunner {


    @Autowired
    private UserRepository userReposiroty;

    @Autowired
    private PostRepository postReposiroty;

    @Override
    public void run(String... arg0) throws Exception {

        DateTimeFormatter sdf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");


        userReposiroty.deleteAll();

        User maria = new User(null, "Maria Brown", "maria@gmail.com");
        User alex = new User(null, "Alex Green", "alex@gmail.com");
        User bob = new User(null, "Bob Grey", "bob@gmail.com");

        Post post1 = new Post(null, LocalDateTime.parse("21/03/2024 22:12:46", sdf), "Partiu viagem", "Vou viajar para São Paulo. Abraços!", maria);
        Post post2 = new Post(null, LocalDateTime.parse("23/03/2014 13:31:55", sdf), "Bom dia", "Acordei feliz hoje!", maria);

        userReposiroty.saveAll(Arrays.asList(maria, alex, bob));

        postReposiroty.saveAll(Arrays.asList(post1, post2));
    }
}
