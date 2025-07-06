package com.codesa.schoolRegistry.application.mediator;

import org.springframework.core.ResolvableType;
import org.springframework.stereotype.Component;

import com.codesa.schoolRegistry.application.Interfaces.RequestHandler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;



@Component
public class MediatorImpl implements Mediator {

    private final Map<Class<?>, RequestHandler<?, ?>> handlers = new HashMap<>();

    public MediatorImpl(List<RequestHandler<?, ?>> discoveredHandlers) {
        for (RequestHandler<?, ?> handler : discoveredHandlers) {
            Class<?> requestType = getGenericRequestType(handler);
            if (requestType != null) {
                handlers.put(requestType, handler);
            } else {
                throw new IllegalStateException("Could not resolve generic request type for handler: " + handler.getClass().getName());
            }
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public <TRequest, TResponse> TResponse dispatch(TRequest request) {
        RequestHandler<TRequest, TResponse> handler = (RequestHandler<TRequest, TResponse>) handlers.get(request.getClass());
        if (handler == null) {
            throw new IllegalArgumentException("No handler found for: " + request.getClass().getName());
        }
        return handler.handle(request);
    }

    private Class<?> getGenericRequestType(RequestHandler<?, ?> handler) {
        return ResolvableType.forClass(handler.getClass())
                .as(RequestHandler.class)
                .getGeneric(0)
                .resolve();
    }
}

