package com.arthurlamberti.cdb.infrastructure.config.useCases;

import com.arthurlamberti.cdb.application.paper.create.CreatePaperUseCase;
import com.arthurlamberti.cdb.application.paper.create.DefaultCreatePaperUseCase;
import com.arthurlamberti.cdb.application.paper.retrieve.get.DefaultGetPaperUseCase;
import com.arthurlamberti.cdb.application.paper.retrieve.get.GetPaperUseCase;
import com.arthurlamberti.cdb.application.paper.retrieve.list.DefaultListPaperUseCase;
import com.arthurlamberti.cdb.application.paper.retrieve.list.ListPaperUseCase;
import com.arthurlamberti.cdb.domain.paper.PaperGateway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PaperUseCaseConfig {

    private final PaperGateway paperGateway;

    public PaperUseCaseConfig(
            final PaperGateway paperGateway
    ) {
        this.paperGateway = paperGateway;
    }

    @Bean
    public CreatePaperUseCase createPaperUseCase(){
        return new DefaultCreatePaperUseCase(paperGateway);
    }

    @Bean
    public GetPaperUseCase getPaperUseCase(){
        return new DefaultGetPaperUseCase(paperGateway);
    }

    @Bean
    public ListPaperUseCase listPaperUseCase() {
        return new DefaultListPaperUseCase(paperGateway);
    }
}
