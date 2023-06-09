package com.hospital.hospitalapi.domain.entities;

import com.hospital.hospitalapi.domain.entities.base.Procedimento;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "Cirurgia")
public class Cirurgia extends Procedimento {
    @ManyToOne
    @JoinColumn(name = "MedicoId")
    private Medico MedicoResponsavel;
    private String Observacoes;

    public Cirurgia (){
    }

    public Cirurgia(
            LocalDate dataAgendada,
            String nome,
            String paciente,
            Medico medicoResponsavel,
            String observacoes) {
        super(
                dataAgendada,
                nome,
                paciente);
        MedicoResponsavel = medicoResponsavel;
        Observacoes = observacoes;
    }

    public Medico getMedicoResponsavel() {
        return MedicoResponsavel;
    }

    public void setMedicoResponsavel(Medico medicoResponsavel) {
        MedicoResponsavel = medicoResponsavel;
    }

    public String getObservacoes() {
        return Observacoes;
    }

    public void setObservacoes(String observacoes) {
        Observacoes = observacoes;
    }

    @Override
    public String toString() {
        return "Cirurgia{" +
                "MedicoResponsavel=" + MedicoResponsavel +
                ", Observacoes=" + Observacoes +
                '}';
    }
}
