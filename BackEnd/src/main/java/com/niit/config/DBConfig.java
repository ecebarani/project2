package com.niit.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.niit.dao.BlogDAO;
import com.niit.dao.BlogDAOImpl;
import com.niit.dao.ForumDAO;
import com.niit.dao.ForumDAOImpl;
import com.niit.dao.JobDAO;
import com.niit.dao.JobDAOImpl;
import com.niit.dao.UserDAO;
import com.niit.dao.UserDAOImpl;
import com.niit.model.Blog;
import com.niit.model.BlogComment;
import com.niit.model.Forum;
import com.niit.model.ForumComment;
import com.niit.model.Job;
import com.niit.model.UserDetails;

@Configuration
@EnableTransactionManagement

public class DBConfig {
	@Bean
	public DataSource getH2DataSource()
	{   
		DriverManagerDataSource dataSource=new DriverManagerDataSource();
		
		dataSource.setDriverClassName("org.h2.Driver");
		dataSource.setUrl("jdbc:h2:tcp://localhost/~/test");
		dataSource.setUsername("barani");
		dataSource.setPassword("barani");
		
		System.out.println("---Data Source Created---");
		return dataSource;
	}
	
	@Bean
	public SessionFactory sessionFactory() 
	{
		LocalSessionFactoryBuilder factoryBuilder = new LocalSessionFactoryBuilder(getH2DataSource());
		Properties hibernateProperties = new Properties();
		hibernateProperties.setProperty("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
		hibernateProperties.setProperty("hibernate.hbm2ddl.auto", "update");
		hibernateProperties.setProperty("hibernate.show_sql", "true");
		hibernateProperties.setProperty("hibernate.format_sql", "true");
		hibernateProperties.setProperty("hibernate.use_sql_comments", "true");
		factoryBuilder.addProperties(hibernateProperties);
		
		factoryBuilder.addAnnotatedClass(Blog.class);
		factoryBuilder.addAnnotatedClass(BlogComment.class);
		factoryBuilder.addAnnotatedClass(Forum.class);
		factoryBuilder.addAnnotatedClass(ForumComment.class);
		factoryBuilder.addAnnotatedClass(Job.class);
		factoryBuilder.addAnnotatedClass(UserDetails.class);
		
		System.out.println("Creating SessionFactory Bean");
		return factoryBuilder.buildSessionFactory();

	}
	@Bean(name="jobDAO")
	public JobDAO getjobDAO()
	{
		System.out.println("----job DAO Implementation---");
		return new JobDAOImpl();
	}
	@Bean(name="blogDAO")
	public BlogDAO getBlogDAO()
	{
		System.out.println("----blog DAO Implementation---");
		return new BlogDAOImpl();
	}
	@Bean(name="forumDAO")
	public ForumDAO getForumDAO()
	{
		System.out.println("----forum DAO Implementation---");
		return new ForumDAOImpl();
	}
	@Bean(name="userDAO")
	public UserDAO getCategoryDAO()
	{
		System.out.println("----user DAO Implementation---");
		return new UserDAOImpl();
	}
	
	@Bean
	public HibernateTransactionManager getTransactionManager(SessionFactory sessionFactory)
	{
		System.out.println("---Transaction Manager----");
		return new HibernateTransactionManager(sessionFactory);
	}

}
