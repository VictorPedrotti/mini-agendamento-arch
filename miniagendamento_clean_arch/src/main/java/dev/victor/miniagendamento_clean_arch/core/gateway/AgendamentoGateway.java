package dev.victor.miniagendamento_clean_arch.core.gateway;

import dev.victor.miniagendamento_clean_arch.core.entities.Agendamento;

public interface AgendamentoGateway {

  Agendamento criarAgendamento(Agendamento agendamento);
  Agendamento buscarAgendamentoPorId(Long id);
  Agendamento atualizarAgendamento(Agendamento agendamento);
  Agendamento cancelarAgendamento(Long id);
  Agendamento concluirAgendamento(Long id);
}
