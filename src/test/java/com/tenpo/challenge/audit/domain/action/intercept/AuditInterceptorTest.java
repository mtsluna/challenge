package com.tenpo.challenge.audit.domain.action.intercept;

import com.tenpo.challenge.audit.domain.model.Audit;
import com.tenpo.challenge.audit.domain.port.AuditRepositoryPort;
import com.tenpo.challenge.calculation.adapters.inbound.api.presentation.CalculationPresentation;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.URI;
import java.security.Principal;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.server.ServerHttpAsyncRequestControl;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;

@ExtendWith(MockitoExtension.class)
class AuditInterceptorTest {

  @InjectMocks
  private AuditInterceptor auditInterceptor;

  @Mock
  private AuditRepositoryPort auditRepositoryPort;

  @Test
  void supportsTrue() {
    boolean result = this.auditInterceptor.supports(null, null);
    Assertions.assertTrue(result);
  }

  @Test
  void beforeBodyWrite() {

    var calculationPresentation = CalculationPresentation
        .builder()
        .result(11.0)
        .build();

    var audit = Audit
        .builder()
        .method(HttpMethod.GET.name())
        .path("endpointsarasa")
        .response(calculationPresentation)
        .build();

    Mockito.when(this.auditRepositoryPort.save(audit))
        .thenReturn(audit);

    Object body = this.auditInterceptor.beforeBodyWrite(
        calculationPresentation,
        null,
        null,
        null,
        new ServerHttpRequest() {
          @Override
          public HttpHeaders getHeaders() {
            return null;
          }

          @Override
          public HttpMethod getMethod() {
            return HttpMethod.GET;
          }

          @Override
          public URI getURI() {
            return URI.create("endpointsarasa");
          }

          @Override
          public InputStream getBody() throws IOException {
            return null;
          }

          @Override
          public Principal getPrincipal() {
            return null;
          }

          @Override
          public InetSocketAddress getLocalAddress() {
            return null;
          }

          @Override
          public InetSocketAddress getRemoteAddress() {
            return null;
          }

          @Override
          public ServerHttpAsyncRequestControl getAsyncRequestControl(ServerHttpResponse response) {
            return null;
          }
        },
        new ServerHttpResponse() {
          @Override
          public HttpHeaders getHeaders() {
            return null;
          }

          @Override
          public OutputStream getBody() throws IOException {
            return null;
          }

          @Override
          public void setStatusCode(HttpStatusCode status) {

          }

          @Override
          public void flush() throws IOException {

          }

          @Override
          public void close() {

          }
        }
    );

    Assertions.assertEquals(calculationPresentation, body);

    Mockito.verify(this.auditRepositoryPort, Mockito.times(1))
        .save(audit);

  }

  @Test
  void beforeBodyWriteErrorCatched() {

    var calculationPresentation = CalculationPresentation
        .builder()
        .result(11.0)
        .build();

    var audit = Audit
        .builder()
        .method(HttpMethod.GET.name())
        .path("endpointsarasa")
        .response(calculationPresentation)
        .build();

    Mockito.doThrow(new IllegalArgumentException("Something wen wrong"))
        .when(this.auditRepositoryPort)
        .save(audit);

    Assertions.assertDoesNotThrow(() -> {
      Object body = this.auditInterceptor.beforeBodyWrite(
          calculationPresentation,
          null,
          null,
          null,
          new ServerHttpRequest() {
            @Override
            public HttpHeaders getHeaders() {
              return null;
            }

            @Override
            public HttpMethod getMethod() {
              return HttpMethod.GET;
            }

            @Override
            public URI getURI() {
              return URI.create("endpointsarasa");
            }

            @Override
            public InputStream getBody() throws IOException {
              return null;
            }

            @Override
            public Principal getPrincipal() {
              return null;
            }

            @Override
            public InetSocketAddress getLocalAddress() {
              return null;
            }

            @Override
            public InetSocketAddress getRemoteAddress() {
              return null;
            }

            @Override
            public ServerHttpAsyncRequestControl getAsyncRequestControl(ServerHttpResponse response) {
              return null;
            }
          },
          new ServerHttpResponse() {
            @Override
            public HttpHeaders getHeaders() {
              return null;
            }

            @Override
            public OutputStream getBody() throws IOException {
              return null;
            }

            @Override
            public void setStatusCode(HttpStatusCode status) {

            }

            @Override
            public void flush() throws IOException {

            }

            @Override
            public void close() {

            }
          }
      );

      Assertions.assertEquals(calculationPresentation, body);

      Mockito.verify(this.auditRepositoryPort, Mockito.times(1))
          .save(audit);
    });

  }

  @Test
  void beforeBodyWriteNotIncludedRoutes() {

    var calculationPresentation = CalculationPresentation
        .builder()
        .result(11.0)
        .build();

    Object body = this.auditInterceptor.beforeBodyWrite(
        calculationPresentation,
        null,
        null,
        null,
        new ServerHttpRequest() {
          @Override
          public HttpHeaders getHeaders() {
            return null;
          }

          @Override
          public HttpMethod getMethod() {
            return HttpMethod.GET;
          }

          @Override
          public URI getURI() {
            return URI.create("/sarasa/error");
          }

          @Override
          public InputStream getBody() throws IOException {
            return null;
          }

          @Override
          public Principal getPrincipal() {
            return null;
          }

          @Override
          public InetSocketAddress getLocalAddress() {
            return null;
          }

          @Override
          public InetSocketAddress getRemoteAddress() {
            return null;
          }

          @Override
          public ServerHttpAsyncRequestControl getAsyncRequestControl(ServerHttpResponse response) {
            return null;
          }
        },
        new ServerHttpResponse() {
          @Override
          public HttpHeaders getHeaders() {
            return null;
          }

          @Override
          public OutputStream getBody() throws IOException {
            return null;
          }

          @Override
          public void setStatusCode(HttpStatusCode status) {

          }

          @Override
          public void flush() throws IOException {

          }

          @Override
          public void close() {

          }
        }
    );

    Assertions.assertEquals(calculationPresentation, body);

  }

  @Test
  void beforeBodyWriteNotIncludedRoutesAudit() {

    var calculationPresentation = CalculationPresentation
        .builder()
        .result(11.0)
        .build();

    Object body = this.auditInterceptor.beforeBodyWrite(
        calculationPresentation,
        null,
        null,
        null,
        new ServerHttpRequest() {
          @Override
          public HttpHeaders getHeaders() {
            return null;
          }

          @Override
          public HttpMethod getMethod() {
            return HttpMethod.GET;
          }

          @Override
          public URI getURI() {
            return URI.create("/sarasa/audits");
          }

          @Override
          public InputStream getBody() throws IOException {
            return null;
          }

          @Override
          public Principal getPrincipal() {
            return null;
          }

          @Override
          public InetSocketAddress getLocalAddress() {
            return null;
          }

          @Override
          public InetSocketAddress getRemoteAddress() {
            return null;
          }

          @Override
          public ServerHttpAsyncRequestControl getAsyncRequestControl(ServerHttpResponse response) {
            return null;
          }
        },
        new ServerHttpResponse() {
          @Override
          public HttpHeaders getHeaders() {
            return null;
          }

          @Override
          public OutputStream getBody() throws IOException {
            return null;
          }

          @Override
          public void setStatusCode(HttpStatusCode status) {

          }

          @Override
          public void flush() throws IOException {

          }

          @Override
          public void close() {

          }
        }
    );

    Assertions.assertEquals(calculationPresentation, body);

  }

}