package dev.victor.miniagendamento_clean_arch.core.usecases;

import dev.victor.miniagendamento_clean_arch.core.entities.Agendamento;
import dev.victor.miniagendamento_clean_arch.core.gateway.AgendamentoGateway;

public class BuscarAgendamentoPorIdUseCaseImpl implements BuscarAgendamentoPorIdUseCase {

  private final AgendamentoGateway agendamentoGateway;

  public BuscarAgendamentoPorIdUseCaseImpl(AgendamentoGateway agendamentoGateway) {
    this.agendamentoGateway = agendamentoGateway;
  }

  @Override
  public Agendamento execute(Long id) {
    var agendamento = agendamentoGateway.buscarAgendamentoPorId(id);
    if (agendamento == null) {
      throw new IllegalArgumentException("Agendamento não encontrado");
    }
    return agendamento;
  }

}
