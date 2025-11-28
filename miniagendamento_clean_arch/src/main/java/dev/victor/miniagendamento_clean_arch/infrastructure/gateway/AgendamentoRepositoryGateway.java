package dev.victor.miniagendamento_clean_arch.infrastructure.gateway;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import dev.victor.miniagendamento_clean_arch.core.entities.Agendamento;
import dev.victor.miniagendamento_clean_arch.core.enums.StatusAgendamento;
import dev.victor.miniagendamento_clean_arch.core.gateway.AgendamentoGateway;
import dev.victor.miniagendamento_clean_arch.infrastructure.mapper.AgendamentoEntityMapper;
import dev.victor.miniagendamento_clean_arch.infrastructure.persistence.AgendamentoEntity;
import dev.victor.miniagendamento_clean_arch.infrastructure.persistence.AgendamentoRepository;

@Component
public class AgendamentoRepositoryGateway implements AgendamentoGateway{

  private final AgendamentoRepository repository;
  private final AgendamentoEntityMapper entityMapper;

  public AgendamentoRepositoryGateway(AgendamentoRepository repository, AgendamentoEntityMapper entityMapper) {
    this.repository = repository;
    this.entityMapper = entityMapper;
  }

  @Override
  public Agendamento criarAgendamento(Agendamento agendamento) {
    validarIntervalo(agendamento.dataInicio(), agendamento.dataFim());
    checarConflito(agendamento.usuario(), agendamento.dataInicio(), agendamento.dataFim(), null);

    AgendamentoEntity agendamentoEntity = repository.save(entityMapper.toEntity(agendamento));
    return entityMapper.toDomain(agendamentoEntity);
  }

  @Override
  public Agendamento buscarAgendamentoPorId(Long id) {
    return repository.findById(id)
            .map(entityMapper::toDomain)
            .orElse(null);
  }

  @Override
  public Agendamento atualizarAgendamento(Agendamento agendamento) {
    return entityMapper.toDomain(repository.save(entityMapper.toEntity(agendamento)));  
  }

  @Override
  public Agendamento cancelarAgendamento(Long id) {
    var existente = repository.findById(id)
          .orElseThrow(() -> new IllegalArgumentException("Agendamento não encontrado"));

    existente.setStatus(StatusAgendamento.CANCELADO);
    existente.setAtualizadoEm(LocalDateTime.now());
    return entityMapper.toDomain(repository.save(existente));  
  }

  @Override
  public Agendamento concluirAgendamento(Long id) {
    var existente = repository.findById(id)
          .orElseThrow(() -> new IllegalArgumentException("Agendamento não encontrado"));

    existente.setStatus(StatusAgendamento.CONCLUIDO);
    existente.setAtualizadoEm(LocalDateTime.now());
    return entityMapper.toDomain(repository.save(existente));
  }

  private void validarIntervalo(LocalDateTime inicio, LocalDateTime fim) {
    if (inicio == null || fim == null || !inicio.isBefore(fim)) {
      throw new IllegalArgumentException("Intervalo inválido");
    }  
  }

  private void checarConflito(String usuario, LocalDateTime inicio, LocalDateTime fim, Long ignoreId) {
    if (repository.existsConflito(usuario, inicio, fim, ignoreId)) {
      throw new IllegalArgumentException("Conflito de agenda!");
    }  
  }
}
