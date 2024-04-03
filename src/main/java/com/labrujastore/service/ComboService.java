package com.labrujastore.service;

import java.util.List;

import com.labrujastore.entity.Combo;

public interface ComboService 
{
    public List<Combo> listarCombo();
    public Combo guardarCombo(Combo combo);
    public Combo actualizarCombo(Combo combo);
    public Combo obtenerIdCombo(Integer comboId);
    public void eliminarCombo(Integer comboId);
    public List<Combo> obtenerCombosPorCategoria(Integer comboId);
}
