package com.tamara.usuario.service;

import java.util.Optional;
import com.tamara.usuario.models.entity.Alumno;

public interface AlumnoService {

    public Iterable<Alumno> findAll();
    public Optional<Alumno> findById(Integer id);
    public Alumno Save(Alumno alumno);
    public void deleteById (Integer id);

}
