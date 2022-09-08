package com.proyectodePruebaUdeA.ciclo3.controller;
import com.proyectodePruebaUdeA.ciclo3.modelos.Empresa;
import com.proyectodePruebaUdeA.ciclo3.service.EmpresaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
public class ControllerFull {
@Autowired
    EmpresaService empresaService;

    @GetMapping("/enterprises")
    public List<Empresa> verEmpresa(){
        return empresaService.getAllEmpresa();
    }
    @PostMapping("/enterprises")
    public Empresa guardarEmpresa(@RequestBody Empresa emp){
        return this.empresaService.saveOrUpdateEmpresa(emp);
    }

    @GetMapping(path = "enterprises/{id}")
    public Empresa empresaPorID8(@PathVariable("id") Integer id){
       return this.empresaService.getEmpresaById(id);
    }

    @PatchMapping("enterprises/{id}")
    public Empresa actualizarEmpresa(@PathVariable("id") Integer id,@RequestBody Empresa empresa){
        Empresa emp= empresaService.getEmpresaById(id);
        emp.setNombre(empresa.getNombre());
        emp.setDireccion(empresa.getDireccion());
        emp.setTelefono(empresa.getTelefono());
        emp.setNIT(empresa.getNIT());
        return empresaService.saveOrUpdateEmpresa(empresa);
    }
    @DeleteMapping (path = "enterprises/{id}")
    public String DeleteEmpresa(@PathVariable("id") Integer id) {
        boolean respuesta = this.empresaService.deleteEmpresa(id);
        if (respuesta) { //Si respuesta es true?
            return "Se elimino la empresa con id" + id;
        } else {
            return "No se pudo eliminar la empresa con id" + id;
        }
    }
}