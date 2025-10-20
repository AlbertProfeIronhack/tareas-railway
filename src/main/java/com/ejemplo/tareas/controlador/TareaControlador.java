package com.ejemplo.tareas.controlador;

import com.ejemplo.tareas.modelo.Tarea;
import com.ejemplo.tareas.servicio.TareaServicio;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tareas")
@CrossOrigin(origins = "*") // Permitir llamadas desde el frontend
public class TareaControlador {

    private final TareaServicio tareaServicio;

    public TareaControlador(TareaServicio tareaServicio) {
        this.tareaServicio = tareaServicio;
    }

    @GetMapping
    public List<Tarea> listarTareas() {
        return tareaServicio.obtenerTodas();
    }

    @GetMapping("/{id}")
    public Tarea obtenerTarea(@PathVariable Long id) {
        return tareaServicio.obtenerPorId(id)
                .orElseThrow(() -> new RuntimeException("Tarea no encontrada con id " + id));
    }

    @PostMapping
    public Tarea crearTarea(@RequestBody Tarea tarea) {
        return tareaServicio.guardar(tarea);
    }

    @PutMapping("/{id}")
    public Tarea actualizarTarea(@PathVariable Long id, @RequestBody Tarea tareaActualizada) {
        Tarea tarea = tareaServicio.obtenerPorId(id)
                .orElseThrow(() -> new RuntimeException("Tarea no encontrada con id " + id));
        
        tarea.setTitulo(tareaActualizada.getTitulo());
        tarea.setDescripcion(tareaActualizada.getDescripcion());
        tarea.setCompletado(tareaActualizada.isCompletado());

        return tareaServicio.guardar(tarea);
    }

    @DeleteMapping("/{id}")
    public void eliminarTarea(@PathVariable Long id) {
        tareaServicio.eliminar(id);
    }
}
