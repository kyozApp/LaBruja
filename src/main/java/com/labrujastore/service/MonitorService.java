package com.labrujastore.service;

import java.util.List;

import com.labrujastore.entity.Monitor;

public interface MonitorService 
{
	public List<Monitor> listarMonitor();
	public Monitor guardarMonitor(Monitor monitor);
	public Monitor actualizarMonitor(Monitor monitor);
	public Monitor obtenerIdMonitor(Integer monitorId);
	public void eliminarMonitor(Integer monitorId);
}
