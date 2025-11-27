package dev.victor.miniagendamento_clean_arch.infrastructure.mapper;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import dev.victor.miniagendamento_clean_arch.core.entities.Agendamento;
import dev.victor.miniagendamento_clean_arch.core.enums.StatusAgendamento;
import dev.victor.miniagendamento_clean_arch.infrastructure.dto.AgendamentoUpdateRequest;

@Component
public class AgendamentoUpdateRequestMapper {
  
  public AgendamentoUpdateRequest toDto(Agendamento agendamento) {
    return new AgendamentoUpdateRequest(
      agendamento.titulo(), 
      agendamento.descricao(), 
      agendamento.dataInicio(), 
      agendamento.dataFim()
    );
  }

  public Agendamento merge(Agendamento agendamentoExistente, AgendamentoUpdateRequest req) {
    return new Agendamento(
      agendamentoExistente.id(),
      req.titulo() != null ? req.titulo() : agendamentoExistente.titulo(),
      req.descricao() != null ? req.descricao() : agendamentoExistente.descricao(),
      req.dataInicio() != null ? req.dataInicio() : agendamentoExistente.dataInicio(),
      req.dataFim() != null ? req.dataFim() : agendamentoExistente.dataFim(),
      agendamentoExistente.status(),
      agendamentoExistente.usuario(),
      agendamentoExistente.criadoEm(),
      LocalDateTime.now()
    );
  }

  public Agendamento toEntity(AgendamentoUpdateRequest agendamentoUpdateRequest) {
    return new Agendamento(
      null,
      agendamentoUpdateRequest.titulo(),
      agendamentoUpdateRequest.descricao(),
      agendamentoUpdateRequest.dataInicio(),
      agendamentoUpdateRequest.dataFim(),
      StatusAgendamento.AGENDADO,
      null,
      LocalDateTime.now(),
      LocalDateTime.now()
    );
  }
}
