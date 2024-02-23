package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Genero;
import com.example.demo.model.Pelicula;
import com.example.demo.repository.GeneroRepository;
import com.example.demo.repository.PeliculaRepository;

@Service
public class PeliculaService {
	
	@Autowired
	private PeliculaRepository peliRepository;
	@Autowired
	private GeneroRepository generoRepository;
	
	public List<Pelicula> getAllPelicula(){
		return peliRepository.findAll();
	}
	
	public Pelicula createPelicula(Pelicula pelicula) {
		return peliRepository.save(pelicula);
	}
	
	public void deletePelicula(Long id) {
		peliRepository.deleteById(id);
	}

	public Pelicula getPeliById(Long id) {
		return peliRepository.findById(id).orElse(null);
	}
	
	public Genero getGenero(Long idgenero) {
		return generoRepository.findById(idgenero).orElse(null);
	}
}
