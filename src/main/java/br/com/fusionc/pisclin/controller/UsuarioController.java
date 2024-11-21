package br.com.fusionc.pisclin.controller;

import br.com.fusionc.pisclin.controller.dto.UsuarioDto;
import br.com.fusionc.pisclin.controller.form.UsuarioForm;
import br.com.fusionc.pisclin.modelo.Usuario;
import br.com.fusionc.pisclin.repository.CargoRepository;
import br.com.fusionc.pisclin.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.xml.ws.Response;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private CargoRepository cargoRepository;

    //Listar
    @GetMapping
    public List<UsuarioDto> listarUsuarios() {
        List<Usuario> usuarios = usuarioRepository.findAll();
        return UsuarioDto.converter(usuarios);
    }

    //Buscar
    @GetMapping("/{id}")
    public UsuarioDto buscarUsuario(@PathVariable("id") Long id) {
        Usuario usuario = usuarioRepository.getReferenceById(id);
        return new UsuarioDto(usuario);
    }

    //Criar
    @PostMapping
    @Transactional
    public ResponseEntity<UsuarioDto> cadastrarUsuario(@RequestBody @Validated UsuarioForm usuarioForm, UriComponentsBuilder uriBuilder) {
        Usuario usuario = usuarioForm.converter(cargoRepository);
        usuarioRepository.save(usuario);

        URI uri = uriBuilder.path("/usuarios/{id}").buildAndExpand(usuario.getId()).toUri();
        return ResponseEntity.created(uri).body(new UsuarioDto(usuario));
    }

    //Atualizar
    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<UsuarioDto> atualizarUsuario(@PathVariable("id") Long id, @RequestBody @Validated UsuarioForm usuarioForm) {
        Usuario usuario = usuarioForm.atualizar(id, usuarioRepository, cargoRepository);
        return ResponseEntity.ok(new UsuarioDto(usuario));
    }

    //Deletar
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> deletarUsuario(@PathVariable("id") Long id) {
        usuarioRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

}
