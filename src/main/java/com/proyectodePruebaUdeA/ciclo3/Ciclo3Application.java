package com.proyectodePruebaUdeA.ciclo3;

import com.proyectodePruebaUdeA.ciclo3.modelos.Empresa;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@SpringBootApplication (exclude = {SecurityAutoConfiguration.class})
public class Ciclo3Application {

	@GetMapping("/hello")
	public String hello(){
		return "Hola ciclo 3...SAldremos vivos de esto";
	}


	@GetMapping("/test")
	public String test(){
		Empresa emp = new Empresa("SQFULLPACKAGE","OASIS DE LA MODA", "3232323", "71219652");
		emp.setNombre("SQLFULLPACKAGE SAS");
		return emp.getNombre();
	}

	public static void main(String[] args) {
		SpringApplication.run(Ciclo3Application.class, args);
	}

}
