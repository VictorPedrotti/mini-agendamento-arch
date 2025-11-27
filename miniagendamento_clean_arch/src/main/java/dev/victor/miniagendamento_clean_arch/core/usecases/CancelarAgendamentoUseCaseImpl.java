package dev.victor.miniagendamento_clean_arch.core.usecases;

import dev.victor.miniagendamento_clean_arch.core.entities.Agendamento;
import dev.victor.miniagendamento_clean_arch.core.gateway.AgendamentoGateway;

public class CancelarAgendamentoUseCaseImpl implements CancelarAgendamentoUseCase {

  private final AgendamentoGateway agendamentoGateway;

  public CancelarAgendamentoUseCaseImpl(AgendamentoGateway agendamentoGateway) {
    this.agendamentoGateway = agendamentoGateway;
  }

  @Override
  public Agendamento execute(Long id) {
    if (agendamentoGateway.buscarAgendamentoPorId(id) == null) {
      throw new IllegalArgumentException("Agendamento não encontrado");
    }

    return agendamentoGateway.cancelarAgendamento(id);
  }

}
