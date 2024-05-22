package com.labrujastore.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.labrujastore.entity.Combo;
import com.labrujastore.repository.ComboRepository;

@Service
public class ComboServiceImpl implements ComboService {

    @Autowired
    private ComboRepository comboRepository;

    @Override
    public Combo actualizarCombo(Combo combo) {
        return comboRepository.save(combo);
    }

    @Override
    public void eliminarCombo(Integer comboId) {
        comboRepository.deleteById(comboId);
    }

    @Override
    public Combo guardarCombo(Combo combo) {
        return comboRepository.save(combo);
    }

    @Override
    public List<Combo> listarCombo() {
        return comboRepository.findAll();
    }

    @Override
    public Combo obtenerIdCombo(Integer comboId) {
        return comboRepository.findById(comboId).get();
    }

    @Override
    public List<Combo> obtenerCombosPorCategoria(Integer comboId) {

        List<Combo> combos = comboRepository.findAll();
        List<Combo> combosFinal = new ArrayList<>();
        for (Combo combo : combos) {
            if (comboId.equals(combo.getCategoria().getCategoriaId())) {
                combosFinal.add(combo);
            }
        }

        return combosFinal;
    }
    
    @Override
    public List<Combo> obtenerCombosPorMarca(Integer marcaId) {

        List<Combo> combos = comboRepository.findAll();
        List<Combo> combosFinal = new ArrayList<>();
        for (Combo combo : combos) {
            if (combo.getMarca() != null && marcaId.equals(combo.getMarca().getMarcaId())) {
                combosFinal.add(combo);
            }
        }

        return combosFinal;
    }
}
