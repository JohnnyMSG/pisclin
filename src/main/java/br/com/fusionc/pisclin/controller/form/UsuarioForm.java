package br.com.fusionc.pisclin.controller.form;

import br.com.fusionc.pisclin.enums.Sexo;
import br.com.fusionc.pisclin.modelo.Cargo;
import br.com.fusionc.pisclin.modelo.Usuario;
import br.com.fusionc.pisclin.repository.CargoRepository;
import br.com.fusionc.pisclin.repository.UsuarioRepository;
import org.hibernate.annotations.*;

public class UsuarioForm {

    @NotFound
    private String nome;
    @NotFound
    private String email;
    @NotFound
    private String telefone;
    @NotFound
    private Sexo sexo;
    @NotFound
    private int idade;
    @NotFound
    private String cpf;
    @NotFound
    private Long cargoId;

    public Usuario converter(CargoRepository cargoRepository) {
        Cargo cargo = cargoRepository.findById(cargoId).orElse(null);

        return new Usuario(nome, email, telefone, sexo, idade, cpf, cargo);
    }

    public Usuario atualizar(Long id, UsuarioRepository usuarioRepository, CargoRepository cargoRepository) {
        Usuario usuario = usuarioRepository.getReferenceById(id);
        Cargo cargo = cargoRepository.getReferenceById(cargoId);

        usuario.setNome(nome);
        usuario.setEmail(email);
        usuario.setTelefone(telefone);
        usuario.setSexo(sexo);
        usuario.setIdade(idade);
        usuario.setCpf(cpf);
        usuario.setCargo(cargo);

        return usuario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public Sexo getSexo() {
        return sexo;
    }

    public void setSexo(Sexo sexo) {
        this.sexo = sexo;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Long getCargo() {
        return cargoId;
    }

    public void setCargo(Long cargoId) {
        this.cargoId = cargoId;
    }
}
