package dev.victor.miniagendamento.repository;

import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import dev.victor.miniagendamento.model.Agendamento;

public interface AgendamentoRepository extends JpaRepository<Agendamento, Long> {

  @Query("""
      SELECT CASE WHEN COUNT(a) > 0 THEN true ELSE false END
          FROM Agendamento a 
          WHERE a.usuario = :usuario
              AND a.status = dev.victor.miniagendamento.model.StatusAgendamento.AGENDADO
              AND (a.dataInicio < :fim AND dataFim > :inicio)
              AND (:ignoreId is NULL OR a.id <> : ignoreId)
  """)

  boolean existsConflito(@Param("usuario") String usuario,
                         @Param("inicio") LocalDateTime inicio,
                         @Param("fim") LocalDateTime fim, 
                         @Param("ignoreId") Long ignoreId);
}

