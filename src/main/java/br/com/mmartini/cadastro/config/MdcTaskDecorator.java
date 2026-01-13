package br.com.mmartini.cadastro.config;

import java.util.Map;

import org.slf4j.MDC;
import org.springframework.core.task.TaskDecorator;
import org.springframework.stereotype.Component;

@Component
public class MdcTaskDecorator implements TaskDecorator{
	
//	@Autowired
//	private TransacaoService tsService;
	
	@Override
    public Runnable decorate(Runnable runnable) {
        // Pega o contexto da thread atual (PAI)
        Map<String, String> contextMap = MDC.getCopyOfContextMap();
        return () -> {
            try {
                // Aplica o contexto na nova thread (FILHA)
                if (contextMap != null) {
                    MDC.setContextMap(contextMap);
                } 
                runnable.run();
            } finally {
                MDC.clear();
            }
        };
    }

}
