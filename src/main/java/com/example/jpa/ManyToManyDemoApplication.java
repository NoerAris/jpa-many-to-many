package com.example.jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.jpa.model.Post;
import com.example.jpa.model.Tag;
import com.example.jpa.repository.PostRepository;
import com.example.jpa.repository.TagRepository;

@SpringBootApplication
public class ManyToManyDemoApplication implements CommandLineRunner{
	
	@Autowired
	private PostRepository postRepository;
	
	@Autowired
	private TagRepository tagRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(ManyToManyDemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {		
		//Cleanup table
		postRepository.deleteAllInBatch();
		tagRepository.deleteAllInBatch();
		
		//Create Post
		Post post = new Post("First Post", "Test Description", "This content with large size");
	
		//Create two tags
		Tag tag1 = new Tag("First Tag");
		Tag tag2 = new Tag("Second Tag");
		
		//Add reference in the post
		post.getTags().add(tag1);
		post.getTags().add(tag2);
		
		//Add post reference in tags
		tag1.getPosts().add(post);
		tag2.getPosts().add(post);
		
		postRepository.save(post);
	}

}
