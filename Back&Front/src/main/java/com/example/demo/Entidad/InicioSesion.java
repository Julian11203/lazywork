package com.example.demo.Entidad;

import javax.persistence.*;
import java.time.LocalDateTime;
@Entity
@Table(name = "IniciosSesion")
public class InicioSesion{
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "InicioID")
        private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuarioid", referencedColumnName = "usuarioid", nullable = false)
    private Usuarioback Usuarioback;



    @Column(nullable = false)
        private LocalDateTime tiempodesesion;

        @Column(name = "FechaHoraInicio", nullable = false)
        private LocalDateTime fechaHoraInicio;


        @Column(nullable = true)
        private LocalDateTime FechaHoraFin;
        // Constructor por defecto
        public InicioSesion() {
        }

        public InicioSesion(Long id, com.example.demo.Entidad.Usuarioback usuarioback, LocalDateTime tiempodesesion, LocalDateTime fechaHoraInicio, LocalDateTime fechaHoraFin) {
            this.id = id;
            Usuarioback = usuarioback;
            this.tiempodesesion = tiempodesesion;
            this.fechaHoraInicio = fechaHoraInicio;
            FechaHoraFin = fechaHoraFin;
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public Usuarioback getUsuarioback() {
            return Usuarioback;
        }

        public void setUsuarioback(Usuarioback usuarioback) {
            Usuarioback = usuarioback;
        }

        public LocalDateTime getTiempodesesion() {
            return tiempodesesion;
        }

        public void setTiempodesesion(LocalDateTime tiempodesesion) {
            this.tiempodesesion = tiempodesesion;
        }

        public LocalDateTime getFechaHoraInicio() {
            return fechaHoraInicio;
        }

        public void setFechaHoraInicio(LocalDateTime fechaHoraInicio) {
            this.fechaHoraInicio = fechaHoraInicio;
        }

        public LocalDateTime getFechaHoraFin() {
            return FechaHoraFin;
        }

        public void setFechaHoraFin(LocalDateTime fechaHoraFin) {
            FechaHoraFin = fechaHoraFin;
        }

        @Override
        public String toString() {
            return "InicioSesion{" +
                    "id=" + id +
                    ", Usuarioback=" + Usuarioback +
                    ", tiempodesesion=" + tiempodesesion +
                    ", fechaHoraInicio=" + fechaHoraInicio +
                    ", FechaHoraFin=" + FechaHoraFin +
                    '}';
        }
}