package com.example.demo.dao;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dao.User.AuthorityDao;
import com.example.demo.dao.User.UserDao;
import com.example.demo.entity.Authority;
import com.example.demo.entity.Cliente;
import com.example.demo.entity.Prestamo;
import com.example.demo.entity.User;

@Repository
public class PrestamoDaoImp implements PrestamoDao {
	@PersistenceContext
	private EntityManager en;

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public List<Prestamo> findAll() {
		/*HQL o JPQL != SQL*/
		List<Prestamo> result = en.createQuery("from Prestamo").getResultList();
		return result;
	}

	@Override
	@Transactional(readOnly = true)
	public Prestamo find(Long id) {
		Prestamo result = en.find(Prestamo.class, id);//select * from alumno where id ={id}
		return result;
	}

	@Override
	@Transactional
	public void insert(Prestamo nuevo) {
		en.persist(nuevo);
		en.flush();
	}

	@Override
	@Transactional
	public void update(Prestamo nuevo) {
		Prestamo antes = find(nuevo.getId());
		BeanUtils.copyProperties(nuevo, antes);
		en.flush();
	}

	@Override
	@Transactional
	public void delete(Long id) {
		System.out.print("\n\n\n\n HAAAAAAAAAAAAAYYYYYYYYYYYYYYYY BORRANDO EL ID: "+ id + " \n\n\n\n");
		Prestamo entity = find(id);
		System.out.print("\n HAAAAAAAAAAAAAYYYYYYYYYYYYYYYY Encontre esto: "+ entity.getId() + " \n");
		en.remove(entity);
	}

}
