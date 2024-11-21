package br.com.fusionc.pisclin.controller;

import br.com.fusionc.pisclin.controller.dto.CargoDto;
import br.com.fusionc.pisclin.controller.form.CargoForm;
import br.com.fusionc.pisclin.modelo.Cargo;
import br.com.fusionc.pisclin.repository.CargoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/cargos")
public class CargoController {

    @Autowired
    private CargoRepository cargoRepository;

    //Listar
    @GetMapping
    public List<CargoDto> listarCargos() {
        List<Cargo> cargos = cargoRepository.findAll();
        return CargoDto.converter(cargos);
    }

    //Buscar
    @GetMapping("/{id}")
    public CargoDto buscarCargo(@PathVariable("id") Long id) {
        Cargo cargo = cargoRepository.getReferenceById(id);
        return new CargoDto(cargo);
    }

    //Criar
    @PostMapping
    @Transactional
    public ResponseEntity<CargoDto> cadastrarCargo(@RequestBody @Validated CargoForm cargoForm, UriComponentsBuilder uriBuilder) {
        Cargo cargo = cargoForm.converter();
        cargoRepository.save(cargo);

        URI uri = uriBuilder.path("/cargos/{id}").buildAndExpand(cargo.getId()).toUri();
        return ResponseEntity.created(uri).body(new CargoDto(cargo));
    }

    //Atualizar
    @PutMapping
    @Transactional
    public ResponseEntity<CargoDto> atualizarCargo(@PathVariable("id") Long id, @RequestBody @Validated CargoForm cargoForm) {
        Cargo cargo = cargoForm.atualizar(id, cargoRepository);
        return ResponseEntity.ok(new CargoDto(cargo));
    }

    //Deletar
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> deletarCargo(@PathVariable("id") Long id) {
        cargoRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

}
