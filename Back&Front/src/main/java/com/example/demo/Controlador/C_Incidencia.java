package com.example.demo.Controlador;

import com.example.demo.Entidad.E_Incidencia;
import com.example.demo.Servicio.S_Incidencia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/incidencia")
@CrossOrigin("*")
public class C_Incidencia {

    @Autowired
    private S_Incidencia incidenciaService;

    @GetMapping("/listar")
    public ResponseEntity<List<E_Incidencia>> obtenerTodasLasIncidencias() {
        List<E_Incidencia> incidencias = incidenciaService.obtenerTodasLasIncidencias();
        return ResponseEntity.ok(incidencias);
    }

    @GetMapping("/{id}")
    public ResponseEntity<E_Incidencia> obtenerIncidenciaPorId(@PathVariable Long id) {
        Optional<E_Incidencia> incidencia = incidenciaService.obtenerIncidenciaPorId(id);
        if (incidencia.isPresent()) {
            return ResponseEntity.ok(incidencia.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }



    @PutMapping("/actualizar")
    public ResponseEntity<E_Incidencia> actualizarIncidencia(@RequestBody E_Incidencia incidencia) {
        Optional<E_Incidencia> incidenciaActual = incidenciaService.obtenerIncidenciaPorId(incidencia.getIncidenciaID());
        if (incidenciaActual.isPresent()) {
            incidencia.setId(incidencia.getIncidenciaID());
            E_Incidencia incidenciaActualizada = incidenciaService.actualizarIncidencia(incidencia);
            return ResponseEntity.ok(incidenciaActualizada);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @PostMapping("/crear")
    public ResponseEntity<E_Incidencia> crearIncidencia(@RequestBody E_Incidencia incidencia) {
        E_Incidencia incidenciaCreada = incidenciaService.crearIncidencia(incidencia);
        return ResponseEntity.status(HttpStatus.CREATED).body(incidenciaCreada);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> eliminarIncidencia(@PathVariable Long id) {
        incidenciaService.eliminarIncidencia(id);
        return ResponseEntity.noContent().build();
    }


    @PostMapping("actualizarIncidenciasNivel2Prioridad2")
    public ResponseEntity<String> actualizarNivel2Prioridad2() {
        incidenciaService.actualizarIncidenciasNivel2Prioridad2();
        return ResponseEntity.ok("Actualización completa");
    }

    @PostMapping("actualizarIncidenciasNivel3Prioridad3")
    public ResponseEntity<String> actualizarNivel3Prioridad3() {
        incidenciaService.actualizarIncidenciasNivel3Prioridad3();
        return ResponseEntity.ok("Actualización completa");
    }
    @PostMapping("actualizarIncidenciasNivel1Prioridad1")
    public ResponseEntity<String> actualizarNivel1Prioridad1() {
        incidenciaService.actualizarIncidenciasNivel1Prioridad1();
        return ResponseEntity.ok("Actualización completa");
    }

    @PostMapping("/actualizarIncidenciasNivel4Prioridad4")
    public ResponseEntity<String> actualizarNivel4Prioridad4() {
        incidenciaService.actualizarIncidenciasNivel4Prioridad4();
        return ResponseEntity.ok("Actualización completa");
    }



    @GetMapping("/estado4")
    public ResponseEntity<List<E_Incidencia>> obtenerIncidenciasEstado4() {
        List<E_Incidencia> incidenciasEstado4 = incidenciaService.obtenerIncidenciasEstado4();
        return ResponseEntity.ok(incidenciasEstado4);
    }

    @GetMapping("/estado3")
    public ResponseEntity<List<E_Incidencia>> obtenerIncidenciasEstado3() {
        List<E_Incidencia> incidenciasEstado3 = incidenciaService.obtenerIncidenciasEstado3();
        return ResponseEntity.ok(incidenciasEstado3);
    }

    @GetMapping("/estado1")
    public ResponseEntity<List<E_Incidencia>> obtenerIncidenciasEstado1() {
        List<E_Incidencia> incidenciasEstado1 = incidenciaService.obtenerIncidenciasEstado1();
        return ResponseEntity.ok(incidenciasEstado1);
    }


}