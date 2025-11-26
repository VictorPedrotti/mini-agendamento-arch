package dev.victor.miniagendamento.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.victor.miniagendamento.dto.AgendamentoCreateRequest;
import dev.victor.miniagendamento.dto.AgendamentoResponse;
import dev.victor.miniagendamento.dto.AgendamentoUpdateRequest;
import dev.victor.miniagendamento.services.AgendamentoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/agendamentos")
@RequiredArgsConstructor
public class AgendamentoController {

  private final AgendamentoService service;

  @PostMapping
  public AgendamentoResponse criar (@Valid @RequestBody AgendamentoCreateRequest req) {
    return service.criar(req);
  }

  @PutMapping("/{id}")
  public AgendamentoResponse atualizar (@PathVariable Long id, @Valid @RequestBody AgendamentoUpdateRequest req) {
    return service.atualizar(id, req);  
  }

  @PutMapping("/{id}/cancelar")
  public AgendamentoResponse cancelar(@PathVariable Long id) {
    return service.cancelar(id);
  }

  @PutMapping("/{id}/concluir")
  public AgendamentoResponse concluir(@PathVariable Long id) {
    return service.concluir(id);
  }

  @GetMapping("/{id}")
  public AgendamentoResponse buscarPorId(@PathVariable Long id) {
    return service.buscarPorId(id);
  }
}
