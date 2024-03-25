package com.labrujastore.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.labrujastore.entity.Laptop;
import com.labrujastore.repository.LaptopRepository;

@Service
public class LaptopServiceImpl implements LaptopService
{
	@Autowired
	private LaptopRepository laptopRepository;

	@Override
	public List<Laptop> listarLaptop() {
		return laptopRepository.findAll();
	}

	@SuppressWarnings("null")
	@Override
	public Laptop guardarLaptop(Laptop laptop) {
		return laptopRepository.save(laptop);
	}

	@SuppressWarnings("null")
	@Override
	public Laptop actualizarLaptop(Laptop laptop) {
		return laptopRepository.save(laptop);
	}

	@SuppressWarnings("null")
	@Override
	public Laptop obtenerIdLaptop(Integer laptopId) {
		return laptopRepository.findById(laptopId).get();
	}

	@SuppressWarnings("null")
	@Override
	public void eliminarLaptop(Integer laptopId) {
		laptopRepository.deleteById(laptopId);
	}

}
