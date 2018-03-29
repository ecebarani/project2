package com.niit.test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.dao.BlogDAO;
import com.niit.model.Blog;
import com.niit.model.BlogComment;


public class BlogTestCase {
	static BlogDAO blogDAO;
	
    @BeforeClass
    public static void initialize()
    {
    	AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
    	context.scan("com.niit");
    	context.refresh();
    	
    	blogDAO=(BlogDAO)context.getBean("blogDAO");
    }
    
    @Test
    public void addBlogTest()
    {
    	Blog blog=new Blog();
    	blog.setBlogName("Hibernate Framework");
    	blog.setBlogContent("Blog specific to core java and related concept");
    	blog.setLikes(0);
    	blog.setLoginname("harish");
    	blog.setStatus("A");
    	blog.setCreateDate(new java.util.Date());
    	
    	assertTrue("problem in Blog Insertion", blogDAO.addBlog(blog));
    	
    }
    @Ignore
    @Test
    public void deleteBlogTest()
    {
    	assertTrue("Problem in Blog Deletion",blogDAO.deleteBlog(952));
    }

    @Test
    public void rejectBlogTest()
    {
    	Blog blog=blogDAO.getBlog(0);
    	assertTrue("Problem in Blog Rejection",blogDAO.rejectBlog(blog));
    }
    @Ignore
    @Test
    public void approvalBlogTest()
    {
    	Blog blog=blogDAO.getBlog(953);
    	assertTrue("Problem in Blog Approval",blogDAO.approveBlog(blog));
    }
    @Ignore
    @Test
    public void listBlogsByUserTest()
    {
    	List<Blog> listBlogs=blogDAO.listBlogs("Harish");
    	assertTrue("Problem in listing Blogs",listBlogs.size()>0);
    	
    	for(Blog blog:listBlogs)
    	{
    		System.out.println(blog.getBlogName()+":::");
    		System.out.println(blog.getBlogContent()+":::");
    		System.out.println(blog.getLoginname()+":::");
    	}	
    }
    
    @Test
    public void incrementBlogLikeTest()
    {
    	Blog blog=blogDAO.getBlog(953);
    	assertTrue("Problem in increment of like",blogDAO.incrementLike(blog));
    }
    
    @Test
    public void addCommentTest()
    {
    	BlogComment comment=new BlogComment();
    	comment.setCommentText("This blog is very nice");
    	comment.setLoginname("Ram");
    	comment.setBlogId(953);
    	comment.setCommentDate(new java.util.Date());
    	assertTrue("Problem in insertion of Blog Comment ",blogDAO.addBlogComment(comment));
    }
    
    @Test
    public void listAllBlogCommentTest()
    {
    	List<BlogComment> listBlogComments= blogDAO.listBlogComments(953);
    	assertTrue("Problem in retrieving all the BlogComments",listBlogComments.size()>0);
    	
    	for(BlogComment blogComment:listBlogComments)
    	{
    		System.out.println(blogComment.getBlogId()+":::");
    		System.out.println(blogComment.getCommentText()+":::");
    		System.out.println(blogComment.getLoginname()+":::");
    	}
    }
    
}
