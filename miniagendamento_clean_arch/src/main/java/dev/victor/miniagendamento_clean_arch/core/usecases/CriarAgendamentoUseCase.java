package dev.victor.miniagendamento_clean_arch.core.usecases;

import dev.victor.miniagendamento_clean_arch.core.entities.Agendamento;

public interface CriarAgendamentoUseCase {

  Agendamento execute(Agendamento agendamento);
}
