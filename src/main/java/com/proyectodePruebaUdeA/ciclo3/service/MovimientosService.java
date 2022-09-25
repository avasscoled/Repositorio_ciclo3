package com.proyectodePruebaUdeA.ciclo3.service;

import com.proyectodePruebaUdeA.ciclo3.modelos.MovimientoDinero;
import com.proyectodePruebaUdeA.ciclo3.repo.MovimientosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MovimientosService {
    @Autowired
    MovimientosRepository movimientosRepository;

    // Metodo que muestra todos los movimientos sin filtro
    public List<MovimientoDinero> getAllMovimientos(){
        List<MovimientoDinero> movimientosList= new ArrayList<>();
        movimientosRepository.findAll().forEach(movimiento -> movimientosList.add(movimiento));
        return movimientosList;
    }
    //Metodo para buscar movimientos por id
    public MovimientoDinero getMovimientoById(Integer id){
        return movimientosRepository.findById(id).get();
    }
    //Metodo para guardar o actualizar
    public boolean saveOrUpdateMovimiento(MovimientoDinero movimientoDinero){
        MovimientoDinero mov=movimientosRepository.save(movimientoDinero);
        if(movimientosRepository.findById(mov.getId())!=null){
            return true;
        }
        return false;
    }
    //Metodo para eliminar un movimiento por Id
    public boolean deleteMovimiento(Integer id){
        movimientosRepository.deleteById(id);
        if (this.movimientosRepository.findById(id).isPresent()){
            return false;
        }
        return true;
    }
    public ArrayList<MovimientoDinero> obtenerPorEmpleado(Integer id){
        return movimientosRepository.findByEmpleado(id);
    }
    public ArrayList<MovimientoDinero> obtenerPorEmpresa(Integer id){
        return movimientosRepository.findByEmpresa(id);
    }

       //Servicio para ver la suma de todos los montos
       public long obtenerSumaMontos(){
          return movimientosRepository.SumarMonto();
       }

    //Servicio para ver la suma de todos los montos por empleado
    public long MontosPorEmpleado(Integer id){
        return movimientosRepository.MontosPorEmpleado(id);
    }

    //Servicio para ver la suma de todos los montos por empresa
    public long MontosPorEmpresa(Integer id){
        return movimientosRepository.MontosPorEmpresas(id);
    }
}
