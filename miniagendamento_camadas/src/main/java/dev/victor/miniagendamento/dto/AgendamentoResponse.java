package dev.victor.miniagendamento.dto;

import java.time.LocalDateTime;

import dev.victor.miniagendamento.model.StatusAgendamento;

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
