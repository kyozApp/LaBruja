package com.labrujastore.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.labrujastore.entity.Monitor;
import com.labrujastore.repository.MonitorRepository;

@Service
public class MonitorServiceImpl implements MonitorService
{
	@Autowired
	private MonitorRepository monitorRepository;

	@Override
	public List<Monitor> listarMonitor() {
		return monitorRepository.findAll();
	}

	@SuppressWarnings("null")
	@Override
	public Monitor guardarMonitor(Monitor monitor) {
		return monitorRepository.save(monitor);
	}

	@SuppressWarnings("null")
	@Override
	public Monitor actualizarMonitor(Monitor monitor) {
		return monitorRepository.save(monitor);
	}

	@SuppressWarnings("null")
	@Override
	public Monitor obtenerIdMonitor(Integer monitorId) {
		return monitorRepository.findById(monitorId).get();
	}

	@SuppressWarnings("null")
	@Override
	public void eliminarMonitor(Integer monitorId) {
		monitorRepository.deleteById(monitorId);
	}

}
