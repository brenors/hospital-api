package com.hospital.hospitalapi.service.services;

import com.hospital.hospitalapi.domain.entities.Paciente;
import com.hospital.hospitalapi.domain.entities.Relatorio;
import com.hospital.hospitalapi.domain.enums.PacienteStatusEnum;
import com.hospital.hospitalapi.repository.repositories.PacienteRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service // define que esta classe será uma fornecedora de serviço da aplicação(onde criamos a lógica de negócio)
public class PacienteService {
    private final PacienteRepository repository; // faz a injeção de dependência do repositório realacionado ao paciente

    @Autowired
    public PacienteService(PacienteRepository repository) {
        this.repository = repository;
    }

    public List<Paciente> ListarPacientes(){
        return repository.findAll();
    }

    public Object ObterPacientePorCpf(String cpf){
        Optional<Paciente> paciente = repository.ObterPacientePorCpf(cpf); // faz comunicação com o repositório para retornar um paciente pelo CPF dentro da base de dados
        if (!paciente.isPresent())
            throw new IllegalStateException("Paciente com o CPF " + cpf + " não encontrado.");

        return paciente;
    }

    public Object CriarPaciente(Paciente paciente) {
            Optional<Paciente> pacientePorCpf = repository.ObterPacientePorCpf(paciente.getCPF());// faz comunicação com o repositório para reportar um paciente pelo Id dentro da base de dados

            if(pacientePorCpf.isPresent())
                throw new IllegalStateException("Paciente com CPF " + pacientePorCpf.get().getCPF() + " já existe na base de dados.");

            if(paciente.getDataNascimento().equals(LocalDate.now()))
                throw new IllegalStateException("Data de nascimento precisa ser maior que a data atual.");

            return repository.save(paciente); // pede para o repositório salvar esse ojeto na base de dados
    }

    @Transactional
    public Object AlterarPaciente(Long id, String nome, String endereco){
        Paciente paciente = repository.findById(id).orElseThrow(() -> new IllegalStateException("Paciente com o Id " + id + " não existe."));
        if ((nome != null) && (nome.length() > 0) && (!Objects.equals(paciente.getNome(), nome)))
            paciente.setNome(nome);

        if ((endereco != null) && (endereco.length() > 0)){
            if (Objects.equals(paciente.getEndereco(), endereco))
                throw new IllegalStateException("Endereço precisa ser diferente do existente.");

            paciente.setEndereco(endereco);
        }
        return paciente;
    }

    public void RemoverPacientePorCpf(String cpf){
        Optional<Paciente> paciente = repository.ObterPacientePorCpf(cpf);
        if (paciente.isPresent())
            repository.delete(paciente.get());
        else {
            throw new IllegalStateException("Paciente com CPF " + cpf + " não encontrado.");
        }
    }

    public void RemoverPaciente(String cpf) {
        Optional<Paciente> paciente = repository.ObterPacientePorCpf(cpf);
        if (paciente.isPresent())
            throw new IllegalStateException("Paciente com o CPF " + cpf + " não existe.");

        repository.delete(paciente.get()); // pede para o repositório remover um ojeto da base de dados com o Id informado
    }
}
