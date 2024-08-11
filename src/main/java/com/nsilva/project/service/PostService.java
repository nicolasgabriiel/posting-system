package com.nsilva.project.service;

import com.nsilva.project.entities.Post;
import com.nsilva.project.repository.PostRepository;
import com.nsilva.project.service.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    @Autowired
    private PostRepository repo;

    public Post findById(String id) {
        Optional<Post> obj = repo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Object not Found"));
    }

    public List<Post> findByTitle(String text) {
        return repo.findByTitleContainingIgnoreCase(text);
    }
}