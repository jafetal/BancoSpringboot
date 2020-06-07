package com.example.demo.dao.User;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.User;

@Repository
public class UserDaoImp implements UserDao{
	@PersistenceContext
	private EntityManager en;

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public List<User> findAll() {
		List<User> result = en.createQuery("from User").getResultList();
		return result;
	}

	@Override
	@Transactional(readOnly = true)
	public User find(Long id) {
		User result = en.find(User.class, id);
		return result;
	}

	@Override
	@Transactional
	public void insert(User nuevo) {
		en.persist(nuevo);
		en.flush();
	}

	@Override
	@Transactional
	public void update(User nuevo) {
		User antes = find(nuevo.getId());
		BeanUtils.copyProperties(nuevo, antes);
		en.flush();
	}

	@Override
	@Transactional
	public void delete(Long id) {
		User entity = find(id);
		en.remove(entity);
	}
}
