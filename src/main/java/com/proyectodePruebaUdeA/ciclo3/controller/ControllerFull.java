package com.proyectodePruebaUdeA.ciclo3.controller;

import com.proyectodePruebaUdeA.ciclo3.modelos.Empleado;
import com.proyectodePruebaUdeA.ciclo3.modelos.Empresa;
import com.proyectodePruebaUdeA.ciclo3.modelos.MovimientoDinero;
import com.proyectodePruebaUdeA.ciclo3.service.EmpleadoService;
import com.proyectodePruebaUdeA.ciclo3.service.EmpresaService;
import com.proyectodePruebaUdeA.ciclo3.service.MovimientosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class ControllerFull {
    @Autowired
    EmpresaService empresaService;
    @Autowired
    EmpleadoService empleadoService;
    @Autowired
    MovimientosService movimientosService;


    //EMPRESAS
    @GetMapping({"/","/VerEmpresas"})
    public String viewEmpresas(Model model, @ModelAttribute("mensaje") String mensaje) {
        List<Empresa> listaEmpresas=empresaService.getAllEmpresa();
        model.addAttribute("emplist",listaEmpresas);
        model.addAttribute("mensaje",mensaje);
        return "verEmpresas"; //Llamamos al Html
    }


    @GetMapping("/AgregarEmpresa")
    public String nuevaEmpresa(Model model, @ModelAttribute("mensaje") String mensaje){
        Empresa emp= new Empresa();
        model.addAttribute("emp",emp);
        model.addAttribute("mensaje", mensaje);
        return "agregarEmpresa";
    }

    @PostMapping("/GuardarEmpresa")
    public String guardarEmpresa(Empresa emp, RedirectAttributes redirectAttributes){
        if(empresaService.saveOrUpdateEmpresa(emp)==true){
            redirectAttributes.addFlashAttribute("mensaje","GuardarOK");
            return "redirect:/VerEmpresas";
        }
        redirectAttributes.addFlashAttribute("mensaje","GuardarERROR");
        return "redirect:/AgregarEmpresa";
    }
    @GetMapping("/EditarEmpresa/{id}")
    public String editarEmpresa(Model model, @PathVariable Integer id, @ModelAttribute("mensaje") String mensaje){
        Empresa emp=empresaService.getEmpresaById(id);
        model.addAttribute("emp",emp);
        model.addAttribute("mensaje", mensaje);
        return "editarEmpresa";
    }

    @PostMapping("/ActualizarEmpresa")
    public String updateEmpresa(@ModelAttribute("emp") Empresa emp, RedirectAttributes redirectAttributes){
        if(empresaService.saveOrUpdateEmpresa(emp)){
            redirectAttributes.addFlashAttribute("mensaje","ActualizarOK");
            return "redirect:/VerEmpresas";
        }
        redirectAttributes.addFlashAttribute("mensaje","ActualizarERROR");
        return "redirect:/EditarEmpresa/"+emp.getId();
    }
    @GetMapping("/EliminarEmpresa/{id}")
    public String eliminarEmpresa(@PathVariable Integer id, RedirectAttributes redirectAttributes){
        if (empresaService.deleteEmpresa(id)==true){
            redirectAttributes.addFlashAttribute("mensaje","EliminarOK");
            return "redirect:/VerEmpresas";
        }
        redirectAttributes.addFlashAttribute("mensaje","EliminarERROR");
        return "redirect:/VerEmpresas";
    }
   //EMPLEADOS
   @GetMapping("/VerEmpleados")
   public String viewEmpleado(Model model, @ModelAttribute("mensaje") String mensaje) {
       List<Empleado> listaEmpleados=empleadoService.getAllEmpleado();
       model.addAttribute("emplelist",listaEmpleados);
       model.addAttribute("mensaje",mensaje);
       return "VerEmpleados"; //Llamamos al Html
   }

    @GetMapping("/AgregarEmpleado")
    public String nuevoEmpleado(Model model, @ModelAttribute("mensaje") String mensaje){
        Empleado empl= new Empleado();
        model.addAttribute("empl",empl);
        model.addAttribute("mensaje", mensaje);
        List<Empresa> listaEmpresas= empresaService.getAllEmpresa();
        model.addAttribute("emprelist",listaEmpresas);
        return "agregarEmpleado"; //Llamar html
    }
    @PostMapping("/GuardarEmpleado")
    public String guardarEmpleado(Empleado empl, RedirectAttributes redirectAttributes){
        if(empleadoService.saveOrUpDateEmpleado(empl)==true){
            redirectAttributes.addFlashAttribute("mensaje","GuardarOK");
            return "redirect:/VerEmpleados";
        }
        redirectAttributes.addFlashAttribute("mensaje","GuardarERROR");
        return "redirect:/AgregarEmpleado";
    }

    @GetMapping("/EditarEmpleado/{id}")
    public String editarEmpleado(Model model, @PathVariable Integer id, @ModelAttribute("mensaje") String mensaje){
        Empleado empl=empleadoService.getEmpleadoById(id).get();
        model.addAttribute("empl",empl);
        model.addAttribute("mensaje", mensaje);
        List<Empresa> listaEmpresas= empresaService.getAllEmpresa();
        model.addAttribute("emprelist",listaEmpresas);
        return "editarEmpleado";
    }
    @PostMapping("/ActualizarEmpleado")
    public String updateEmpleado(@ModelAttribute("emp") Empleado empl, RedirectAttributes redirectAttributes){
        if(empleadoService.saveOrUpDateEmpleado(empl)){
            redirectAttributes.addFlashAttribute("mensaje","ActualizarOK");
            return "redirect:/VerEmpleados";
        }
        redirectAttributes.addFlashAttribute("mensaje","ActualizarERROR");
        return "redirect:/EditarEmpleado/"+empl.getId();
    }
    @GetMapping("/EliminarEmpleado/{id}")
    public String eliminarEmpleado(@PathVariable Integer id, RedirectAttributes redirectAttributes){
        if (empleadoService.deleteEmpleado(id)==true){
            redirectAttributes.addFlashAttribute("mensaje","EliminarOK");
            return "redirect:/VerEmpleados";
        }
        redirectAttributes.addFlashAttribute("mensaje","EliminarERROR");
        return "redirect:/VerEmpleados";
    }
    @GetMapping("/Empresa/{id}/Empleados")//Filtrar los empleados por empresas
    public String verEmpleadosPorEmpresa(@PathVariable("id") Integer id, Model model){
        List<Empleado> ListaEmpleados = empleadoService.obtenerPorEmpresa(id);
        model.addAttribute("emplelist", ListaEmpleados);
        return "verEmpleados"; //Llamanos al html con el emplelist de los empleados filtrados
    }
    //MOVIMIENTOS
    @GetMapping("/VerMovimientos")//Controlador que nos lleva al templete donde veremos todos los movimientos
    public String viewMovimientos(Model model, @ModelAttribute("mensaje") String mensaje) {
        List<MovimientoDinero> listaMovimientos=movimientosService.getAllMovimientos();
        model.addAttribute("movlist",listaMovimientos);
        model.addAttribute("mensaje",mensaje);
        return "VerMovimientos"; //Llamamos al Html
    }
    @GetMapping("/AgregarMovimiento")//Controlador que nos lleva al templete donde podremos crear un nuevo movimiento
    public String nuevoMovimiento(Model model, @ModelAttribute("mensaje") String mensaje){
        MovimientoDinero movimiento= new MovimientoDinero();
        model.addAttribute("mov",movimiento);
        model.addAttribute("mensaje", mensaje);
        List<Empleado> listaEmpleados= empleadoService.getAllEmpleado();
        model.addAttribute("emplelist",listaEmpleados);
        return "agregarMovimiento"; //Llamar html
    }

    @PostMapping("/GuardarMovimiento")
    public String guardarMovimiento(MovimientoDinero mov, RedirectAttributes redirectAttributes){
        if(movimientosService.saveOrUpdateMovimiento(mov)){
            redirectAttributes.addFlashAttribute("mensaje","GuardarOK");
            return "redirect:/VerMovimientos";
        }
        redirectAttributes.addFlashAttribute("mensaje","GuardarERROR");
        return "redirect:/AgregarMovimiento";
    }
    @GetMapping("/EditarMovimiento/{id}")
    public String editarMovimiento(Model model, @PathVariable Integer id, @ModelAttribute("mensaje") String mensaje) {
        //Creamos un atributo para el modelo, que se llame igualmente mov y es el que ira el html para llenar
        MovimientoDinero mov = movimientosService.getMovimientoById(id);
        model.addAttribute("mov", mov);
        model.addAttribute("mensaje", mensaje);
        List<Empleado> listaEmpleados = empleadoService.getAllEmpleado();
        model.addAttribute("emplelist", listaEmpleados);
        return "editarMovimiento";
    }
    @PostMapping("/ActualizarMovimiento")
    public String updateMovimiento(@ModelAttribute("mov") MovimientoDinero mov, RedirectAttributes redirectAttributes){
        if(movimientosService.saveOrUpdateMovimiento(mov)){
            redirectAttributes.addFlashAttribute("mensaje","ActualizarOK");
            return "redirect:/VerMovimientos";
        }
        redirectAttributes.addFlashAttribute("mensaje","ActualizarERROR");
        return "redirect:/EditarMovimiento/"+mov.getId();
    }
    @GetMapping("/EliminarMovimiento/{id}")
    public String eliminarMovimiento(@PathVariable Integer id, RedirectAttributes redirectAttributes){
        if (movimientosService.deleteMovimiento(id)){
            redirectAttributes.addFlashAttribute("mensaje","EliminarOK");
            return "redirect:/VerMovimientos";
        }
        redirectAttributes.addFlashAttribute("mensaje","EliminarERROR");
        return "redirect:/VerMovimientos";
    }
    @GetMapping("/Empleado/{id}/Movimientos")//Filtro de Movimientos por empleados
    public String movimientosPorEmpleado(@PathVariable("id")Integer id, Model model){
        List<MovimientoDinero> movlist = movimientosService.obtenerPorEmpleado(id);
        model.addAttribute("movlist",movlist);
        return "verMovimientos";//Llamamos al html
    }

}