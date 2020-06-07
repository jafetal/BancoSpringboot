package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.PrestamoDao;
import com.example.demo.entity.Prestamo;

@Service
public class PrestamosService {
	@Autowired
	PrestamoDao pDao;
	
	public void borrarPrestamosC(long id) {
		List<Prestamo> p = new ArrayList<Prestamo>(pDao.findAll());
		List<Long> l = new ArrayList<Long>();
		
		for (Prestamo temp : p) {
            if(temp.getCliente().getId() == id){
            	l.add(temp.getId());
            }
            System.out.print("\n\n\n\n HAAAAAAAAAAAAAYYYYYYYYYYYYYYYY ID Recibido: "+ temp.getId() + " \n\n\n\n");
        }
		for(Long i : l) {
			pDao.delete(i);
		}
	}
}
