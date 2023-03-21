package com.tenpo.challenge.audit.domain.action.intercept;

import com.tenpo.challenge._shared.constants.AuditExclusionsConstants;
import com.tenpo.challenge.audit.domain.model.Audit;
import com.tenpo.challenge.audit.domain.port.AuditRepositoryPort;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

@Slf4j
@ControllerAdvice
@RequiredArgsConstructor
public class AuditInterceptor implements ResponseBodyAdvice {

    private final AuditRepositoryPort auditRepositoryPort;

    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        return true;
    }

    @Override
    @Async
    public Object beforeBodyWrite(Object body, MethodParameter returnType,
        MediaType selectedContentType, Class selectedConverterType, ServerHttpRequest request,
        ServerHttpResponse response) {
        try {
            String uriString = String.valueOf(request.getURI());

            if(uriString.contains(AuditExclusionsConstants.ERROR) || uriString.contains(AuditExclusionsConstants.AUDITS)) {
                return body;
            }

            this.auditRepositoryPort.save(Audit
                .builder()
                .method(request.getMethod().name())
                .path(uriString)
                .response(body)
                .build()
            );
        } catch (Exception e) {
            log.error("Something went wrong when try to save audit log in database", e);
        }

        return body;
    }
}