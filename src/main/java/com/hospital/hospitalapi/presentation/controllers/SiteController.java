package com.hospital.hospitalapi.presentation.controllers;

import com.hospital.hospitalapi.domain.entities.Cirurgia;
import com.hospital.hospitalapi.domain.entities.Funcionario;
import com.hospital.hospitalapi.presentation.controllers.FuncionarioController;
import com.hospital.hospitalapi.repository.repositories.FuncionarioRepository;
import com.hospital.hospitalapi.service.services.FuncionarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
public class SiteController {
    private final FuncionarioController funcionarioController;
    private final FuncionarioRepository repository;

    @Autowired
    public SiteController(FuncionarioController funcionarioController, FuncionarioRepository funcionarioRepository){
        this.funcionarioController = funcionarioController;
        this.repository = funcionarioRepository;
    }

    @GetMapping("/index")
    private String index() {
        return "/pages/Administrador/adm.html";
    }

    @GetMapping("/funcionariolista")
    private ModelAndView listagem_funcionarios() {
        List<Funcionario> listaFuncionarios = funcionarioController.ListarFuncionarios();

        ModelAndView mv = new ModelAndView("/pages/Funcionario/funcionario-list.html");
        mv.addObject("listafuncionarios", listaFuncionarios);

        return mv;
    }

    @PostMapping("/funcionariocadastrar")
    private String salvarFuncionario(Funcionario func) {
        System.out.println(func.getNome());
        System.out.println(func.getCargo());
        System.out.println(func.getSalario());
        System.out.println(func.getCargaHoraria());
        System.out.println(func.getCPF());
        System.out.println(func.getDataNascimento());
        System.out.println(func.getEndereco());
        funcionarioController.CriarFuncionario(func); 
        return "redirect:/index";
    }

    @GetMapping("/funcionariocadastro")
    private String cadastrar_funcionarios() {
        return "/pages/Funcionario/funcionario-edit.html";
    }

    @GetMapping("/cirurgialista")
    private ModelAndView listagem_cirurgia() {
        //List<Cirurgia> listaCirurgia = cirurgiaController.ListarFuncionarios();

        ModelAndView mv = new ModelAndView("/pages/Funcionario/funcionario-list.html");
        //mv.addObject("listafuncionarios", listaFuncionarios);

        return mv;
    }

}