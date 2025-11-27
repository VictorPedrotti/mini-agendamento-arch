package dev.victor.miniagendamento_clean_arch.infrastructure.mapper;

import org.springframework.stereotype.Component;

import dev.victor.miniagendamento_clean_arch.core.entities.Agendamento;
import dev.victor.miniagendamento_clean_arch.infrastructure.dto.AgendamentoResponse;

@Component
public class AgendamentoResponseMapper {

  public AgendamentoResponse toDto(Agendamento agendamento) {
    return new AgendamentoResponse(
      agendamento.id(),
      agendamento.titulo(),
      agendamento.descricao(),
      agendamento.dataInicio(),
      agendamento.dataFim(),
      agendamento.status(),
      agendamento.usuario(),
      agendamento.criadoEm(),
      agendamento.atualizadoEm()
    );
  }

  public Agendamento toEntity(AgendamentoResponse agendamentoResponse) {
    return new Agendamento(
      agendamentoResponse.id(),
      agendamentoResponse.titulo(),
      agendamentoResponse.descricao(),
      agendamentoResponse.dataInicio(),
      agendamentoResponse.dataFim(),
      agendamentoResponse.status(),
      agendamentoResponse.usuario(),
      agendamentoResponse.criadoEm(),
      agendamentoResponse.atualizadoEm()
    );
  }
}
