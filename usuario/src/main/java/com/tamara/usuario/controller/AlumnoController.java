package com.tamara.usuario.controller;


import com.tamara.usuario.models.entity.Alumno;
import com.tamara.usuario.service.AlumnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/alumno")
public class AlumnoController {

    @Autowired
    private AlumnoService service;

    @Value("${config.balanceador.test}")
    private String balanceadorTest;

    @GetMapping("/balanceador-test")
    public ResponseEntity<?> balanceadorTest() {
        Map<String, Object> response = new HashMap<>();
        response.put("balanceador", balanceadorTest);
        response.put("alumno", service.findAll());

        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/todos")
    public ResponseEntity<?> listarAlumno(){
        return ResponseEntity.ok().body(service.findAll());
    }

    @GetMapping("/Uno/{id}")
    public ResponseEntity<?> ver(@PathVariable Integer id){
        Optional <Alumno> ob = service.findById(id);
        if (ob.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok().body(ob.get());
    }

    @PostMapping("/Guardar")
    public ResponseEntity<?> crear(@RequestBody Alumno alumno){

        Alumno alumno1 = service.Save(alumno);
        return ResponseEntity.status(HttpStatus.CREATED).body(alumno1);
    }


    @PutMapping("/{id}")
    public ResponseEntity<?> editar(@RequestBody Alumno alumno, @PathVariable Integer id) {
        Optional<Alumno> alumnoExistente = service.findById(id);

        if (alumnoExistente.isEmpty()) {
            return ResponseEntity.noContent().build(); // 204 No Content si no se encuentra el alumno
        }

        Alumno alumnoActual = alumnoExistente.get();

        // Actualizar los campos del alumno
        alumnoActual.setNombre(alumno.getNombre());
        alumnoActual.setApellido(alumno.getApellido());
        alumnoActual.setEmail(alumno.getEmail());

        // Guardar el alumno actualizado
        service.Save(alumnoActual);

        return ResponseEntity.ok(alumnoActual); // Devuelve el alumno actualizado
    }

    @DeleteMapping("/eliminar/{id}")
    private ResponseEntity<?> eliminar(@PathVariable Integer id){
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
