package dev.victor.miniagendamento_clean_arch.core.usecases;

import dev.victor.miniagendamento_clean_arch.core.entities.Agendamento;
import dev.victor.miniagendamento_clean_arch.core.gateway.AgendamentoGateway;

public class CriarAgendamentoUseCaseImpl implements CriarAgendamentoUseCase {

  private final AgendamentoGateway agendamentoGateway;

  public CriarAgendamentoUseCaseImpl(AgendamentoGateway agendamentoGateway) {
    this.agendamentoGateway = agendamentoGateway;
  }

  @Override
  public Agendamento execute(Agendamento agendamento) {
    return agendamentoGateway.criarAgendamento(agendamento);
  }
  
}
