package dev.victor.miniagendamento.services;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import dev.victor.miniagendamento.dto.AgendamentoCreateRequest;
import dev.victor.miniagendamento.dto.AgendamentoResponse;
import dev.victor.miniagendamento.dto.AgendamentoUpdateRequest;
import dev.victor.miniagendamento.mapper.AgendamentoMapper;
import dev.victor.miniagendamento.model.Agendamento;
import dev.victor.miniagendamento.model.StatusAgendamento;
import dev.victor.miniagendamento.repository.AgendamentoRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@Service
public class AgendamentoService {

  private final AgendamentoRepository repo;

  public AgendamentoService(AgendamentoRepository repo) {
    this.repo = repo;
  }

  @Transactional
  public AgendamentoResponse criar (@Valid AgendamentoCreateRequest req) {

    validarIntervalo(req.dataInicio(), req.dataFim());
    checarConflito(req.usuario(), req.dataInicio(), req.dataFim(), null);

    Agendamento entity = AgendamentoMapper.toEntity(req);
    entity = repo.save(entity);
    return AgendamentoMapper.toResponse(entity);
  }

  @Transactional
  public AgendamentoResponse atualizar (Long id, @Valid AgendamentoUpdateRequest req) {
    Agendamento entity = repo.findById(id)
              .orElseThrow(() -> new EntityNotFoundException("Agendamento não encontrado"));
    AgendamentoMapper.merge(entity, req);

    validarIntervalo(req.dataInicio(), req.dataFim());
    checarConflito(entity.getUsuario(), req.dataInicio(), req.dataFim(), null);

    entity = repo.save(entity);
    return AgendamentoMapper.toResponse(entity);
  }

  @Transactional
  public AgendamentoResponse cancelar (Long id) {
    Agendamento entity = repo.findById(id)
              .orElseThrow(() -> new EntityNotFoundException("Agendamento não encontrado"));

    entity.setStatus(StatusAgendamento.CANCELADO);
    entity = repo.save(entity);
    return AgendamentoMapper.toResponse(entity);
  }

  @Transactional
  public AgendamentoResponse concluir (Long id) {
    Agendamento entity = repo.findById(id)
              .orElseThrow(() -> new EntityNotFoundException("Agendamento não encontrado"));

    entity.setStatus(StatusAgendamento.CONCLUIDO);
    entity = repo.save(entity);
    return AgendamentoMapper.toResponse(entity);
  }

  public AgendamentoResponse buscarPorId (Long id) {
    Agendamento a = repo.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Agendamento não encontrado"));
    
    return AgendamentoMapper.toResponse(a);
  }

  private void validarIntervalo(LocalDateTime inicio, LocalDateTime fim) {
    if (inicio == null || fim == null || inicio.isBefore(fim)) {
      throw new IllegalArgumentException("Intervalo inválido: dataInicio deve ser anterior a dataFim");
    }
  }

  private void checarConflito(String usuario, LocalDateTime inicio, LocalDateTime fim, Long id) {
    if (repo.existsConflito(usuario, inicio, fim, id)){
      throw new IllegalArgumentException("Conflito na agenda: já existe um agendamento nesse período");
    }

  }


}
