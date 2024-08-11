package com.nsilva.project.config;

import com.nsilva.project.dto.AuthorDTO;
import com.nsilva.project.dto.CommentDTO;
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
    private PostRepository postRepository;

    @Override
    public void run(String... arg0) throws Exception {

        DateTimeFormatter sdf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");


        userReposiroty.deleteAll();
        postRepository.deleteAll();

        User maria = new User(null, "Maria Brown", "maria@gmail.com");
        User alex = new User(null, "Alex Green", "alex@gmail.com");
        User bob = new User(null, "Bob Grey", "bob@gmail.com");

        userReposiroty.saveAll(Arrays.asList(maria, alex, bob));

        Post post1 = new Post(null, LocalDateTime.parse("21/03/2024 22:12:46", sdf), "Partiu viagem", "Vou viajar para São Paulo. Abraços!", new AuthorDTO(maria) );
        Post post2 = new Post(null, LocalDateTime.parse("23/03/2014 13:31:55", sdf), "Bom dia", "Acordei feliz hoje!", new AuthorDTO(maria) );;

        CommentDTO c1 = new CommentDTO("Boa viagem mano!", LocalDateTime.parse("21/04/2024 13:30:34", sdf), new AuthorDTO(alex));
        CommentDTO c2 = new CommentDTO("Aproveite", LocalDateTime.parse("22/06/2024 17:11:34",sdf), new AuthorDTO(bob));
        CommentDTO c3 = new CommentDTO("Tenha um ótimo dia!", LocalDateTime.parse("23/07/2014 21:40:50", sdf), new AuthorDTO(alex));

        post1.getComments().addAll(Arrays.asList(c1, c2));
        post2.getComments().add(c3);


        postRepository.saveAll(Arrays.asList(post1, post2));

        maria.getPosts().addAll(Arrays.asList(post1, post2));
        userReposiroty.save(maria);
    }
}
