package com.proyectodePruebaUdeA.ciclo3.service;

import com.proyectodePruebaUdeA.ciclo3.modelos.Empleado;
import com.proyectodePruebaUdeA.ciclo3.modelos.Empresa;
import com.proyectodePruebaUdeA.ciclo3.repo.EmpleadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmpleadoService {

    public boolean deleteMovimiento;
    @Autowired
    EmpleadoRepository empleadoRepository;
    // Metodo para ver los empleados registrados en la base de datos
    public List<Empleado> getAllEmpleado(){
        List<Empleado> empleadoList= new ArrayList<>();
        empleadoRepository.findAll().forEach(empleado -> empleadoList.add(empleado));
        return empleadoList;

    }
    //Metodo para buscar empleado por ID
    public Optional<Empleado> getEmpleadoById(Integer id){ //Otra forma de Hacerlo metodo Optional
        return empleadoRepository.findById(id);
    }

    //Metodo para buscar empleado por empresa
    public ArrayList<Empleado> obtenerPorEmpresa(Integer id){
        return empleadoRepository.findByEmpresa(id);
    }

    //Metodo para guardar o actualizar los registros en empleados(Usuarios)
    public boolean saveOrUpDateEmpleado(Empleado empl){
        Empleado emp=empleadoRepository.save(empl);
        if (empleadoRepository.findById(emp.getId())!=null){
            return true;
        }
        return false;
    }
    //Metodo para eliminar un registro de empleado por Id
    public boolean deleteEmpleado(Integer id){
        empleadoRepository.deleteById(id);
        if (this.empleadoRepository.findById(id).isPresent()){
            return false;
        }
        return true;
    }
}
