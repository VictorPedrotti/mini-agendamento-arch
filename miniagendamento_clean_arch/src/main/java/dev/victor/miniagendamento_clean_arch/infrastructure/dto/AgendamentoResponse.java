package dev.victor.miniagendamento_clean_arch.infrastructure.dto;

import java.time.LocalDateTime;

import dev.victor.miniagendamento_clean_arch.core.enums.StatusAgendamento;

public record AgendamentoResponse(
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
