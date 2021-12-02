package com.techtalentsouth.techtalentblog.BlogPosts;

import org.springframework.data.repository.CrudRepository;

public interface BlogPostRepository extends CrudRepository<BlogPost, Long> {
    
}
