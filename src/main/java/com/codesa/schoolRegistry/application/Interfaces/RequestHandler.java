package com.codesa.schoolRegistry.application.Interfaces;

public interface RequestHandler<TRequest, TResponse> {
    TResponse handle(TRequest request);
}