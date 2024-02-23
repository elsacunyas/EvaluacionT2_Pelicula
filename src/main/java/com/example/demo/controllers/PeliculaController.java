package com.example.demo.controllers;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.model.Genero;
import com.example.demo.model.Pelicula;
import com.example.demo.service.GeneroService;
import com.example.demo.service.PeliculaService;

@Controller
@RequestMapping("/peliculas")
public class PeliculaController {
	
	@Autowired
	private PeliculaService peliService;
	
	@Autowired
	private GeneroService generoService;
	
	@GetMapping("/pelicula")
	public String getAllPelicula(Model model) {
		List<Pelicula> listPeliculas = peliService.getAllPelicula();
		
		model.addAttribute("peliculas",listPeliculas);
		
		return "peliculaList";
	}
	
	@GetMapping("/register")
	public String register(Model model) {
		model.addAttribute("generos",generoService.getAllGenero());
		return"peliRegister";
	}
	
	@PostMapping("/register")
	public String createPelicula(@RequestParam("nombre") String nombre,@RequestParam("director")String director,
			@RequestParam("fechaEstreno") Date fechaEstreno,@RequestParam("idgenero") Long idgenero, Model model) {
		
		
		Pelicula pelicula = new Pelicula();
		Genero genero =peliService.getGenero(idgenero);
		
		pelicula.nombre = nombre;
		pelicula.director = director;
		pelicula.fechaEstreno = fechaEstreno;
		pelicula.genero = genero;
		
		peliService.createPelicula(pelicula);
		
		List<Pelicula> listPeliculas = peliService.getAllPelicula();
		model.addAttribute("peliculas", listPeliculas);
		return "peliculaList";
	}
	
	@GetMapping("/edit/{idPelicula}")
	public String getPeliById(@PathVariable Long idPelicula, Model model) {
		model.addAttribute("generos",generoService.getAllGenero());
		Pelicula pelicula = peliService.getPeliById(idPelicula);
		//pelicula.genero.getNombre();
		
		model.addAttribute("pelicula",pelicula);
		return "peliculaEdit";
	}
	
	@PostMapping("/edit")
	public String editPelicula(@RequestParam("idPelicula") Long idPelicula,@RequestParam("nombre") String nombre,@RequestParam("director")String director,
			@RequestParam("fechaEstreno") Date fechaEstreno,@RequestParam("idgenero") Long idgenero, Model model) {
		
		Pelicula pelicula = peliService.getPeliById(idPelicula);
		Genero genero =peliService.getGenero(idgenero);
		pelicula.nombre = nombre;
		pelicula.director = director;
		pelicula.fechaEstreno = fechaEstreno;
		pelicula.genero =genero;
		
		peliService.createPelicula(pelicula);
		List<Pelicula> listPeliculas = peliService.getAllPelicula();
		model.addAttribute("peliculas", listPeliculas);
		
		return"peliculaList";
	}
	
	@GetMapping("/delete/{idPelicula}")
	public String deletePelicula(@PathVariable Long idPelicula, Model model) {
		peliService.deletePelicula(idPelicula);
		
		List<Pelicula> listPeliculas = peliService.getAllPelicula();
		model.addAttribute("peliculas",listPeliculas);
		
		return "peliculaList";
	}
	

}
