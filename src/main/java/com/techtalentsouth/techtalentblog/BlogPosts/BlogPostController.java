package com.techtalentsouth.techtalentblog.BlogPosts;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;



@Controller
public class BlogPostController {

    @Autowired
	private BlogPostRepository blogPostRepository;


    @GetMapping("/")
    public String index(BlogPost blogPost, Model model) {
        model.addAttribute("posts", blogPostRepository.findAll());
	    return "blogpost/index";
    }

    @GetMapping("/blogposts/new")
    public String newBlog (BlogPost blogPost){
        return "blogpost/new";
    }

    @PostMapping("/blogposts")
    public String addNewBlogPost(BlogPost blogPost, Model model){
        blogPostRepository.save(blogPost);
        model.addAttribute("title", blogPost.getTitle());
	    model.addAttribute("author", blogPost.getAuthor());
	    model.addAttribute("blogEntry", blogPost.getBlogEntry());
        System.out.println("Blog id is: " + blogPost.getId());
        return "blogpost/result";
    }

    @RequestMapping(value = "/blogposts/{id}", method = RequestMethod.GET)
    public String editPostWithId(@PathVariable Long id, BlogPost blogPost, Model model){
        Optional<BlogPost> post = blogPostRepository.findById(id);
        if(post.isPresent()){
            BlogPost actualPost = post.get();
            model.addAttribute("blogPost", actualPost);
        }
        return "blogpost/edit";
    }

    @RequestMapping(value = "/blogposts/update/{id}")
    public String updateExistingPost(@PathVariable Long id, BlogPost blogPost, Model model){
        Optional<BlogPost> post = blogPostRepository.findById(id);
        if(post.isPresent()){
            BlogPost actualPost = post.get();
            actualPost.setTitle(blogPost.getTitle());
            actualPost.setAuthor(blogPost.getAuthor());
            actualPost.setBlogEntry(blogPost.getBlogEntry());
            blogPostRepository.save(actualPost);
            model.addAttribute("title", actualPost.getTitle());
            model.addAttribute("author", actualPost.getAuthor());
            model.addAttribute("blogEntry", actualPost.getBlogEntry());
        }

        return "blogpost/result";
    }

   @DeleteMapping("/blogposts/{id}")
    public String deletePostWithId(@PathVariable Long id){
        blogPostRepository.deleteById(id);
        return "blogpost/delete";
    }
}