package br.com.fusionc.pisclin.repository;

import br.com.fusionc.pisclin.modelo.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

}
