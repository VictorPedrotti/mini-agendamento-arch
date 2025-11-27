package dev.victor.miniagendamento_clean_arch.core.usecases;

import java.time.LocalDateTime;

import dev.victor.miniagendamento_clean_arch.core.entities.Agendamento;
import dev.victor.miniagendamento_clean_arch.core.gateway.AgendamentoGateway;

public class AtualizarAgendamentoUseCaseImpl implements AtualizarAgendamentoUseCase {

  private final AgendamentoGateway agendamentoGateway;

  public AtualizarAgendamentoUseCaseImpl(AgendamentoGateway agendamentoGateway) {
    this.agendamentoGateway = agendamentoGateway;
  }

  @Override
  public Agendamento execute(Agendamento agendamento) {
    var existente = agendamentoGateway.buscarAgendamentoPorId(agendamento.id());
    if (existente == null) {
      throw new IllegalArgumentException("Agendamento não encontrado");
    }

    return agendamentoGateway.atualizarAgendamento(new Agendamento(
        existente.id(),
        agendamento.titulo(),
        agendamento.descricao(),
        agendamento.dataInicio(),
        agendamento.dataFim(),
        existente.status(),
        existente.usuario(),
        existente.criadoEm(),
        LocalDateTime.now()));
  }

}
