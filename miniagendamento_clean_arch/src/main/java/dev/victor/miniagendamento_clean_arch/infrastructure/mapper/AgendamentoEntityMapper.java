package dev.victor.miniagendamento_clean_arch.infrastructure.mapper;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import dev.victor.miniagendamento_clean_arch.core.entities.Agendamento;
import dev.victor.miniagendamento_clean_arch.core.enums.StatusAgendamento;
import dev.victor.miniagendamento_clean_arch.infrastructure.persistence.AgendamentoEntity;

@Component
public class AgendamentoEntityMapper {

  public AgendamentoEntity toEntity(Agendamento agendamento) {
    return AgendamentoEntity.builder()
            .titulo(agendamento.titulo())
            .descricao(agendamento.descricao())
            .dataInicio(agendamento.dataInicio())
            .dataFim(agendamento.dataFim())
            .usuario(agendamento.usuario())
            .status(StatusAgendamento.AGENDADO)
            .criadoEm(LocalDateTime.now())
            .atualizadoEm(LocalDateTime.now())
            .build();  
  }

  public Agendamento toDomain(AgendamentoEntity agendamentoEntity) {
    return new Agendamento(
            agendamentoEntity.getId(),
            agendamentoEntity.getTitulo(),
            agendamentoEntity.getDescricao(),
            agendamentoEntity.getDataInicio(),
            agendamentoEntity.getDataFim(),
            agendamentoEntity.getStatus(),
            agendamentoEntity.getUsuario(),
            agendamentoEntity.getCriadoEm(),
            agendamentoEntity.getAtualizadoEm()
    );
  }
}
