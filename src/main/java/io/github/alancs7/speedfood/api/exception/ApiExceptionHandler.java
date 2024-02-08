package io.github.alancs7.speedfood.api.exception;

import com.fasterxml.jackson.databind.JsonMappingException.Reference;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.fasterxml.jackson.databind.exc.PropertyBindingException;
import io.github.alancs7.speedfood.domain.exception.BusinessException;
import io.github.alancs7.speedfood.domain.exception.ResourceInUseException;
import io.github.alancs7.speedfood.domain.exception.ResourceNotFoundException;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.TypeMismatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.mapping.PropertyReferenceException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    public static final String MSG_ERRO_GENERICA_USUARIO_FINAL = "Ocorreu um erro interno inesperado no sistema. " +
            "Tente novamente e se o problema persistir, " +
            "entre em contato com o administrador do sistema.";

    @Autowired
    private MessageSource messageSource;

    @ExceptionHandler(PropertyReferenceException.class)
    public ResponseEntity<Object> handlePropertyReferenceException(PropertyReferenceException ex, WebRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ApiErrorType apiErrorType = ApiErrorType.INVALID_PARAMETER;
        String propertyName = ex.getPropertyName();
        String className = ex.getType().getType().getSimpleName();

        String detail = String.format("O parâmetro de URL '%s' não existe para o tipo '%s'. " +
                "Corrija e informe um valor correto.", propertyName, className);

        ApiError apiError = createStandardErrorBuilder(status, apiErrorType, detail)
                .userMessage(MSG_ERRO_GENERICA_USUARIO_FINAL)
                .build();

        return handleExceptionInternal(ex, apiError, new HttpHeaders(), status, request);
    }

    @Override
    protected ResponseEntity<Object> handleBindException(BindException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return handleValidationInternal(ex, headers, status, request, ex.getBindingResult());
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return handleValidationInternal(ex, headers, status, request, ex.getBindingResult());
    }

    private ResponseEntity<Object> handleValidationInternal(Exception ex, HttpHeaders headers, HttpStatus status, WebRequest request, BindingResult bindingResult) {
        ApiErrorType apiErrorType = ApiErrorType.INVALID_DATA;
        String detail = "Um ou mais campos estão inválidos. Faça o preenchimento e tente novamente.";

        List<ApiError.Field> fieldErrors = bindingResult.getAllErrors().stream()
                .map(objectError -> {
                    String message = messageSource.getMessage(objectError, LocaleContextHolder.getLocale());

                    String name = objectError.getObjectName();

                    if (objectError instanceof FieldError fieldError) name = fieldError.getField();

                    return ApiError.Field.builder()
                            .name(name)
                            .userMessage(message)
                            .build();
                })
                .collect(Collectors.toList());

        ApiError apiError = createStandardErrorBuilder(status, apiErrorType, detail)
                .userMessage(detail)
                .fields(fieldErrors)
                .build();

        return handleExceptionInternal(ex, apiError, headers, status, request);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleUncaught(Exception ex, WebRequest request) {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        ApiErrorType apiErrorType = ApiErrorType.SERVER_ERROR;
        String detail = MSG_ERRO_GENERICA_USUARIO_FINAL;

        ex.printStackTrace();

        ApiError apiError = createStandardErrorBuilder(status, apiErrorType, detail)
                .userMessage(detail)
                .build();

        return handleExceptionInternal(ex, apiError, new HttpHeaders(), status, request);
    }

    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ApiErrorType apiErrorType = ApiErrorType.RESOURCE_NOT_FOUND;
        String requestURL = ex.getRequestURL();

        String detail = String.format("O recurso %s, que você tentou acessar, é inexistente.", requestURL);

        ApiError apiError = createStandardErrorBuilder(status, apiErrorType, detail)
                .userMessage(MSG_ERRO_GENERICA_USUARIO_FINAL)
                .build();

        return handleExceptionInternal(ex, apiError, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        if (ex instanceof MethodArgumentTypeMismatchException) {
            return handleMethodArgumentTypeMismatchException((MethodArgumentTypeMismatchException) ex, headers, status, request);
        }
        return super.handleTypeMismatch(ex, headers, status, request);
    }

    private ResponseEntity<Object> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ApiErrorType apiErrorType = ApiErrorType.INVALID_PARAMETER;
        String propertyName = ex.getName();
        String invalidValue = ex.getValue().toString();
        String requiredType = ex.getRequiredType().getSimpleName();

        String detail = String.format("O parâmetro de URL '%s' recebeu um valor '%s', que é de um tipo inválido. " +
                "Corrija e informe um valor compatível com o tipo %s.", propertyName, invalidValue, requiredType);

        ApiError apiError = createStandardErrorBuilder(status, apiErrorType, detail)
                .userMessage(MSG_ERRO_GENERICA_USUARIO_FINAL)
                .build();

        return handleExceptionInternal(ex, apiError, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        Throwable rootCause = ExceptionUtils.getRootCause(ex);

        if (rootCause instanceof InvalidFormatException) {
            return handleInvalidFormatException((InvalidFormatException) rootCause, headers, status, request);
        } else if (rootCause instanceof PropertyBindingException) {
            return handlePropertyBindingException((PropertyBindingException) rootCause, headers, status, request);
        }

        ApiErrorType apiErrorType = ApiErrorType.INCOMPREHENSIBLE_MESSAGE;
        String detail = "O corpo da requisição está inválido. Verifique erro de sintaxe.";

        ApiError apiError = createStandardErrorBuilder(status, apiErrorType, detail)
                .detail(MSG_ERRO_GENERICA_USUARIO_FINAL)
                .build();

        return handleExceptionInternal(ex, apiError, headers, status, request);
    }

    private ResponseEntity<Object> handlePropertyBindingException(PropertyBindingException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ApiErrorType apiErrorType = ApiErrorType.INCOMPREHENSIBLE_MESSAGE;
        String propertyName = joinPropertyName(ex.getPath());

        String detail = String.format("A propriedade '%s' não existe. "
                + "Corrija ou remova essa propriedade e tente novamente.", propertyName);

        ApiError apiError = createStandardErrorBuilder(status, apiErrorType, detail)
                .userMessage(MSG_ERRO_GENERICA_USUARIO_FINAL)
                .build();

        return handleExceptionInternal(ex, apiError, headers, status, request);
    }

    private ResponseEntity<Object> handleInvalidFormatException(InvalidFormatException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ApiErrorType apiErrorType = ApiErrorType.INCOMPREHENSIBLE_MESSAGE;
        String propertyName = joinPropertyName(ex.getPath());
        String invalidValue = ex.getValue().toString();
        String expectedType = ex.getTargetType().getSimpleName();

        String detail = String.format("A propriedade '%s' recebeu o valor '%s', que é de um tipo inválido. " +
                "Corrija e informa um valor compativel com o tipo %s.", propertyName, invalidValue, expectedType);

        ApiError apiError = createStandardErrorBuilder(status, apiErrorType, detail)
                .userMessage(MSG_ERRO_GENERICA_USUARIO_FINAL)
                .build();

        return handleExceptionInternal(ex, apiError, headers, status, request);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        ApiErrorType apiErrorType = ApiErrorType.RESOURCE_NOT_FOUND;
        String detail = ex.getMessage();

        ApiError apiError = createStandardErrorBuilder(status, apiErrorType, detail)
                .userMessage(detail)
                .build();

        return handleExceptionInternal(ex, apiError, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<?> handleBusinessException(BusinessException ex, WebRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ApiErrorType apiErrorType = ApiErrorType.BUSINESS_ERROR;
        String detail = ex.getMessage();

        ApiError apiError = createStandardErrorBuilder(status, apiErrorType, detail)
                .userMessage(detail)
                .build();

        return handleExceptionInternal(ex, apiError, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(ResourceInUseException.class)
    public ResponseEntity<?> handleResourceInUseException(ResourceInUseException ex, WebRequest request) {
        HttpStatus status = HttpStatus.CONFLICT;
        ApiErrorType apiErrorType = ApiErrorType.RESOURCE_IN_USE;
        String detail = ex.getMessage();

        ApiError apiError = createStandardErrorBuilder(status, apiErrorType, detail)
                .userMessage(detail)
                .build();

        return handleExceptionInternal(ex, apiError, new HttpHeaders(), status, request);
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {

        if (body == null || body instanceof String) {
            body = ApiError.builder()
                    .title(body instanceof String error ? error : status.getReasonPhrase())
                    .status(status.value())
                    .userMessage(MSG_ERRO_GENERICA_USUARIO_FINAL)
                    .timestamp(OffsetDateTime.now())
                    .build();
        }

        return super.handleExceptionInternal(ex, body, headers, status, request);
    }

    private ApiError.ApiErrorBuilder createStandardErrorBuilder(HttpStatus status,
                                                                ApiErrorType apiErrorType,
                                                                String detail) {
        return ApiError.builder()
                .status(status.value())
                .type(apiErrorType.getUri())
                .title(apiErrorType.getTitle())
                .detail(detail)
                .timestamp(OffsetDateTime.now());
    }

    private String joinPropertyName(List<Reference> references) {
        return references.stream()
                .map(Reference::getFieldName)
                .collect(Collectors.joining("."));
    }

}
