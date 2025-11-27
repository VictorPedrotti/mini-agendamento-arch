package dev.victor.miniagendamento_clean_arch.core.entities;

import java.time.LocalDateTime;

import dev.victor.miniagendamento_clean_arch.core.enums.StatusAgendamento;

public record Agendamento(
  Long id,
  String titulo,
  String descricao,
  LocalDateTime dataInicio,
  LocalDateTime dataFim,
  StatusAgendamento status,
  String usuario,
  LocalDateTime criadoEm,
  LocalDateTime atualizadoEm
) {

}
