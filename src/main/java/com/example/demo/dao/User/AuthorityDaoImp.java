package com.example.demo.dao.User;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.Authority;

@Repository
public class AuthorityDaoImp implements AuthorityDao{
	@PersistenceContext
	private EntityManager en;

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public List<Authority> findAll() {
		List<Authority> result = en.createQuery("from Authority").getResultList();
		return result;
	}

	@Override
	@Transactional(readOnly = true)
	public Authority find(Long id) {
		Authority result = en.find(Authority.class, id);
		return result;
	}
}
