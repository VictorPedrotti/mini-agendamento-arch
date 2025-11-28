package dev.victor.miniagendamento_clean_arch.infrastructure.presentation;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.victor.miniagendamento_clean_arch.core.usecases.AtualizarAgendamentoUseCase;
import dev.victor.miniagendamento_clean_arch.core.usecases.BuscarAgendamentoPorIdUseCase;
import dev.victor.miniagendamento_clean_arch.core.usecases.CancelarAgendamentoUseCase;
import dev.victor.miniagendamento_clean_arch.core.usecases.ConcluirAgendamentoUseCase;
import dev.victor.miniagendamento_clean_arch.core.usecases.CriarAgendamentoUseCase;
import dev.victor.miniagendamento_clean_arch.infrastructure.dto.AgendamentoCreateRequest;
import dev.victor.miniagendamento_clean_arch.infrastructure.dto.AgendamentoResponse;
import dev.victor.miniagendamento_clean_arch.infrastructure.dto.AgendamentoUpdateRequest;
import dev.victor.miniagendamento_clean_arch.infrastructure.mapper.AgendamentoCreateMapper;
import dev.victor.miniagendamento_clean_arch.infrastructure.mapper.AgendamentoResponseMapper;
import dev.victor.miniagendamento_clean_arch.infrastructure.mapper.AgendamentoUpdateRequestMapper;

@RestController
@RequestMapping("api/agendamentos")
public class AgendamentoController {

  private final CriarAgendamentoUseCase criarAgendamentoUseCase;
  private final BuscarAgendamentoPorIdUseCase buscarAgendamentoPorIdUseCase;
  private final AtualizarAgendamentoUseCase atualizarAgendamentoUseCase;
  private final CancelarAgendamentoUseCase cancelarAgendamentoUseCase;
  private final ConcluirAgendamentoUseCase concluirAgendamentoUseCase;
  private final AgendamentoCreateMapper agendamentoCreateMapper;
  private final AgendamentoResponseMapper agendamentoResponseMapper;
  private final AgendamentoUpdateRequestMapper agendamentoUpdateRequestMapper;
  
  public AgendamentoController(CriarAgendamentoUseCase criarAgendamentoUseCase,
      BuscarAgendamentoPorIdUseCase buscarAgendamentoPorIdUseCase,
      AtualizarAgendamentoUseCase atualizarAgendamentoUseCase, CancelarAgendamentoUseCase cancelarAgendamentoUseCase,
      ConcluirAgendamentoUseCase concluirAgendamentoUseCase, AgendamentoCreateMapper agendamentoCreateMapper,
      AgendamentoResponseMapper agendamentoResponseMapper,
      AgendamentoUpdateRequestMapper agendamentoUpdateRequestMapper) {
    this.criarAgendamentoUseCase = criarAgendamentoUseCase;
    this.buscarAgendamentoPorIdUseCase = buscarAgendamentoPorIdUseCase;
    this.atualizarAgendamentoUseCase = atualizarAgendamentoUseCase;
    this.cancelarAgendamentoUseCase = cancelarAgendamentoUseCase;
    this.concluirAgendamentoUseCase = concluirAgendamentoUseCase;
    this.agendamentoCreateMapper = agendamentoCreateMapper;
    this.agendamentoResponseMapper = agendamentoResponseMapper;
    this.agendamentoUpdateRequestMapper = agendamentoUpdateRequestMapper;
  }

  @PostMapping
  public ResponseEntity<AgendamentoResponse> criar(@RequestBody AgendamentoCreateRequest req) {
    var criado = criarAgendamentoUseCase.execute(agendamentoCreateMapper.toEntity(req));
    return ResponseEntity.status(HttpStatus.CREATED).body(agendamentoResponseMapper.toDto(criado));  
  }

  @GetMapping("/{id}")
  public ResponseEntity<AgendamentoResponse> buscarPorId(@PathVariable Long id) {
    return ResponseEntity.ok(agendamentoResponseMapper.toDto(buscarAgendamentoPorIdUseCase.execute(id)));  
  }

  @PutMapping("/{id}") 
  public ResponseEntity<AgendamentoResponse> atualizar(@PathVariable Long id, @RequestBody AgendamentoUpdateRequest req) {
    var salvo = atualizarAgendamentoUseCase.execute(
      agendamentoUpdateRequestMapper.merge(buscarAgendamentoPorIdUseCase.execute(id), req));
    
    return ResponseEntity.ok(agendamentoResponseMapper.toDto(salvo));
  }

  @PatchMapping("/{id}/cancelar") 
  public ResponseEntity<AgendamentoResponse> cancelar(@PathVariable Long id) {
    var salvo = cancelarAgendamentoUseCase.execute(id);
    return ResponseEntity.ok(agendamentoResponseMapper.toDto(salvo));  
  }

  @PatchMapping("/{id}/concluir") 
  public ResponseEntity<AgendamentoResponse> concluir(@PathVariable Long id) {
    var salvo = concluirAgendamentoUseCase.execute(id);
    return ResponseEntity.ok(agendamentoResponseMapper.toDto(salvo));  
  }
  
}
