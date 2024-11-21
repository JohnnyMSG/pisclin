package br.com.fusionc.pisclin.controller.form;

import br.com.fusionc.pisclin.modelo.Cargo;
import br.com.fusionc.pisclin.repository.CargoRepository;
import org.hibernate.annotations.NotFound;

public class CargoForm {

    @NotFound
    private String nome;
    @NotFound
    private String descricao;
    @NotFound
    private int nivel;

    public Cargo converter() {
        return new Cargo(nome, descricao, nivel);
    }

    public Cargo atualizar(Long id, CargoRepository cargoRepository) {
        Cargo cargo = cargoRepository.getReferenceById(id);
        cargo.setNome(nome);
        cargo.setDescricao(descricao);
        cargo.setNivel(nivel);

        return cargo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }
}
