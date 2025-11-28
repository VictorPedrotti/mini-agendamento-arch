package dev.victor.miniagendamento_clean_arch.infrastructure.beans;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import dev.victor.miniagendamento_clean_arch.core.gateway.AgendamentoGateway;
import dev.victor.miniagendamento_clean_arch.core.usecases.AtualizarAgendamentoUseCase;
import dev.victor.miniagendamento_clean_arch.core.usecases.AtualizarAgendamentoUseCaseImpl;
import dev.victor.miniagendamento_clean_arch.core.usecases.BuscarAgendamentoPorIdUseCase;
import dev.victor.miniagendamento_clean_arch.core.usecases.BuscarAgendamentoPorIdUseCaseImpl;
import dev.victor.miniagendamento_clean_arch.core.usecases.CancelarAgendamentoUseCase;
import dev.victor.miniagendamento_clean_arch.core.usecases.CancelarAgendamentoUseCaseImpl;
import dev.victor.miniagendamento_clean_arch.core.usecases.ConcluirAgendamentoUseCase;
import dev.victor.miniagendamento_clean_arch.core.usecases.ConcluirAgendamentoUseCaseImpl;
import dev.victor.miniagendamento_clean_arch.core.usecases.CriarAgendamentoUseCase;
import dev.victor.miniagendamento_clean_arch.core.usecases.CriarAgendamentoUseCaseImpl;
import dev.victor.miniagendamento_clean_arch.infrastructure.gateway.AgendamentoRepositoryGateway;
import dev.victor.miniagendamento_clean_arch.infrastructure.mapper.AgendamentoEntityMapper;
import dev.victor.miniagendamento_clean_arch.infrastructure.persistence.AgendamentoRepository;

@Configuration
public class BeanConfiguration {

  @Bean
  public CriarAgendamentoUseCase criarAgendamentoUseCase(AgendamentoGateway agendamentoGateway) {
    return new CriarAgendamentoUseCaseImpl(agendamentoGateway);
  }

  @Bean
  public BuscarAgendamentoPorIdUseCase buscarAgendamentoPorIdUseCase(AgendamentoGateway agendamentoGateway) {
    return new BuscarAgendamentoPorIdUseCaseImpl(agendamentoGateway);
  }

  @Bean
  public AtualizarAgendamentoUseCase atualizarAgendamentoUseCase(AgendamentoGateway agendamentoGateway) {
    return new AtualizarAgendamentoUseCaseImpl(agendamentoGateway);
  }

  @Bean
  public ConcluirAgendamentoUseCase concluirAgendamentoUseCase(AgendamentoGateway agendamentoGateway) {
    return new ConcluirAgendamentoUseCaseImpl(agendamentoGateway);
  }

  @Bean
  public CancelarAgendamentoUseCase cancelarAgendamentoUseCase(AgendamentoGateway agendamentoGateway) {
    return new CancelarAgendamentoUseCaseImpl(agendamentoGateway);
  }

  @Bean
  public AgendamentoGateway agendamentoGateway(
    AgendamentoRepository repository,
    AgendamentoEntityMapper entityMapper
  ) {
    return new AgendamentoRepositoryGateway(repository, entityMapper);
  }

}
