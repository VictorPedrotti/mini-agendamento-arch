package dev.victor.miniagendamento_clean_arch.infrastructure.mapper;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import dev.victor.miniagendamento_clean_arch.core.entities.Agendamento;
import dev.victor.miniagendamento_clean_arch.core.enums.StatusAgendamento;
import dev.victor.miniagendamento_clean_arch.infrastructure.dto.AgendamentoCreateRequest;

@Component
public class AgendamentoCreateMapper {

  public AgendamentoCreateRequest toDto(Agendamento agendamento) {
    return new AgendamentoCreateRequest(
      agendamento.titulo(), 
      agendamento.descricao(), 
      agendamento.dataInicio(), 
      agendamento.dataFim(), 
      agendamento.usuario());
  }

  public Agendamento toEntity(AgendamentoCreateRequest agendamentoCreateRequest) {
    return new Agendamento(
      null,
      agendamentoCreateRequest.titulo(),
      agendamentoCreateRequest.descricao(),
      agendamentoCreateRequest.dataInicio(),
      agendamentoCreateRequest.dataFim(),
      StatusAgendamento.AGENDADO,
      agendamentoCreateRequest.usuario(),
      LocalDateTime.now(),
      LocalDateTime.now()
    );
  }
}
