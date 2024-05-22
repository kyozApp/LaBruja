package com.labrujastore.service;

import java.util.ArrayList;
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

	@Override
	public Monitor guardarMonitor(Monitor monitor) {
		return monitorRepository.save(monitor);
	}

	@Override
	public Monitor actualizarMonitor(Monitor monitor) {
		return monitorRepository.save(monitor);
	}

	@Override
	public Monitor obtenerIdMonitor(Integer monitorId) {
		return monitorRepository.findById(monitorId).get();
	}

	@Override
	public void eliminarMonitor(Integer monitorId) {
		monitorRepository.deleteById(monitorId);
	}

	@Override
    public List<Monitor> obtenerMonitoresPorCategoria(Integer monitorId) {

        List<Monitor> monitores = monitorRepository.findAll();
        List<Monitor> monitoresFinal = new ArrayList<>();
        for (Monitor monitor : monitores) {
            if (monitorId.equals(monitor.getCategoria().getCategoriaId())) {
                monitoresFinal.add(monitor);
            }
        }

        return monitoresFinal;
    }
    
    @Override
    public List<Monitor> obtenerMonitoresPorMarca(Integer marcaId) {

        List<Monitor> monitores = monitorRepository.findAll();
        List<Monitor> monitoresFinal = new ArrayList<>();
        for (Monitor monitor : monitores) {
            if (monitor.getMarca() != null && marcaId.equals(monitor.getMarca().getMarcaId())) {
                monitoresFinal.add(monitor);
            }
        }

        return monitoresFinal;
    }
}
