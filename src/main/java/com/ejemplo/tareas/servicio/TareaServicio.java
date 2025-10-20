package com.ejemplo.tareas.servicio;

import com.ejemplo.tareas.modelo.Tarea;
import com.ejemplo.tareas.repositorio.TareaRepositorio;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TareaServicio {

    private final TareaRepositorio tareaRepositorio;

    public TareaServicio(TareaRepositorio tareaRepositorio) {
        this.tareaRepositorio = tareaRepositorio;
    }

    public List<Tarea> obtenerTodas() {
        return tareaRepositorio.findAll();
    }

    public Optional<Tarea> obtenerPorId(Long id) {
        return tareaRepositorio.findById(id);
    }

    public Tarea guardar(Tarea tarea) {
        return tareaRepositorio.save(tarea);
    }

    public void eliminar(Long id) {
        tareaRepositorio.deleteById(id);
    }
}
