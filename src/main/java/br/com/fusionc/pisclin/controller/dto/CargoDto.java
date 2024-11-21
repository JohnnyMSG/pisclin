package br.com.fusionc.pisclin.controller.dto;

import br.com.fusionc.pisclin.modelo.Cargo;

import java.util.List;
import java.util.stream.Collectors;

public class CargoDto {

    private Long id;
    private String nome;
    private String descricao;
    private int nivel;

    public CargoDto(Cargo cargo) {
        this.id = cargo.getId();
        this.nome = cargo.getNome();
        this.descricao = cargo.getDescricao();
        this.nivel = cargo.getNivel();
    }

    public static List<CargoDto> converter(List<Cargo> cargos) {
        return cargos.stream().map(CargoDto::new).collect(Collectors.toList());
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
