package com.tamara.usuario.service.impl;

import com.tamara.usuario.models.entity.Alumno;
import com.tamara.usuario.repository.AlumnoRepository;
import com.tamara.usuario.service.AlumnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class AlumnoServiceImpl implements AlumnoService {

    @Autowired
    private AlumnoRepository alumnoRepository; // Cambia el nombre a alumnoRepository

    @Override
    @Transactional(readOnly = true)
    public Iterable<Alumno> findAll() {
        return alumnoRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Alumno> findById(Integer id) {
        return alumnoRepository.findById(id);
    }

    @Override
    @Transactional
    public Alumno Save(Alumno alumno) { // Cambiado a 'save'
        return alumnoRepository.save(alumno); // Cambiado a 'alumnoRepository'
    }

    @Override
    @Transactional
    public void deleteById(Integer id) {
        alumnoRepository.deleteById(id);
    }
}
