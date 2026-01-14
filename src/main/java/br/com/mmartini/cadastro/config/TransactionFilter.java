package br.com.mmartini.cadastro.config;

import java.io.IOException;
import java.util.UUID;

import org.slf4j.MDC;
import org.springframework.stereotype.Component;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class TransactionFilter implements Filter {

    private static final String TRANSACTION_ID_KEY = "transactionId";

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        
        try {
            // 1. Gera o ID único
            String transactionId = UUID.randomUUID().toString();
            
            // 2. Coloca no MDC para que todos os logs da Thread tenham esse ID
            MDC.put(TRANSACTION_ID_KEY, transactionId);
            
            //log.info("Iniciando FILTER request ...");
            
            // 3. Opcional: Adiciona o ID no header da resposta HTTP
            if (response instanceof HttpServletResponse) {
                ((HttpServletResponse) response).setHeader("X-Transaction-ID", transactionId);
            }

            // 4. Segue com a requisição
            chain.doFilter(request, response);
            
        } finally {
            // 5. LIMPEZA CRUCIAL: Remove o ID ao terminar a requisição
            // Isso evita que o ID "vaze" para outra requisição se a thread for reutilizada
        	//log.info("Finalizando FILTER request ...");
            MDC.remove(TRANSACTION_ID_KEY);
            
            
        }
    }
}