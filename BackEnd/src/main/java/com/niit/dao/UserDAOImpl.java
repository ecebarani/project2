package com.niit.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.model.Job;
import com.niit.model.UserDetails;
@Repository("userDAO")

public class UserDAOImpl implements UserDAO {
	@Autowired
	SessionFactory sessionFactory;

	//@Override
	@Transactional
	public boolean addUser(UserDetails userDetails) {
		try
		{
			sessionFactory.getCurrentSession().save(userDetails);
			return true;
		}
		catch(Exception e)
		{
			System.out.println("Exception araised:"+e);
		    return false;
		}
		
	}

	//@Override
	@Transactional
	public boolean deleteUser(UserDetails userDetails) {
		try
		{
		   sessionFactory.getCurrentSession().delete(userDetails);
		   return true;
		}
		catch(Exception e)
		{
			return false;	
		}
	}

	//@Override
	@Transactional
	public boolean updateUser(UserDetails userDetails) {
		try
		{
			sessionFactory.getCurrentSession().update(userDetails);
			return true;
		}
		catch(Exception e)
		{
			System.out.println("Exception Arised:"+e);
		    return false;
		}
	}

	//@Override
	public List<UserDetails> listUser(String username) {
		try
		{
			Session session=sessionFactory.openSession();
			Query query=session.createQuery("from UserDetials where loginname=:username");
			query.setParameter("username", username);
			List<UserDetails> listUserDetais=query.list();
			return listUserDetais;
		}
		catch(Exception e)
		{
			return null;
        }
	}
	
	//@Override
	@Transactional
	public UserDetails getUser(int userId) {
		Session session=sessionFactory.openSession();
		UserDetails user=(UserDetails)session.get(UserDetails.class,userId);
		session.close();
		return user;
	}

}
