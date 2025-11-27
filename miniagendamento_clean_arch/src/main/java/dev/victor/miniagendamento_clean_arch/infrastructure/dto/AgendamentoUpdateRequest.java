package dev.victor.miniagendamento_clean_arch.infrastructure.dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.Size;

public record AgendamentoUpdateRequest(
  @Size(max = 120) String titulo,
  @Size(max = 120) String descricao,
  LocalDateTime dataInicio,
  LocalDateTime dataFim
) {

}
