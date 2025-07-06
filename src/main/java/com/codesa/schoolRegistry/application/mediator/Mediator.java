package com.codesa.schoolRegistry.application.mediator;

public interface Mediator {
    <TRequest, TResponse> TResponse dispatch(TRequest request);
}
