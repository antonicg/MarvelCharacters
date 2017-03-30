package com.antonicastejon.marvelcharacters.net;

import com.antonicastejon.marvelcharacters.net.requests.base.RequestConsumer;
import com.antonicastejon.model.repository.api.ResponseWrapper;
import com.antonicastejon.model.repository.entities.Comic;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Antoni Castejón on 29/01/2017.
 */

public class RequestConsumerTest {

    private final static int OK_CODE = 200;
    private final static int ERROR_CODE = -1;

    @Mock
    ResponseWrapper<Comic> responseWrapper;
    @Mock
    RequestConsumer.Callback<Comic> callback;

    @Before
    public void initializeTest() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testConsumerAcceptOk() {
        when(responseWrapper.getCode()).thenReturn(OK_CODE);
        RequestConsumer<Comic> requestConsumer = new RequestConsumer<>(callback);

        try {
            requestConsumer.accept(responseWrapper);
        } catch (Exception e) {
            e.printStackTrace();
        }
        verify(callback).onResponse(any());
    }

    @Test
    public void testConsumerAcceptError() {
        when(responseWrapper.getCode()).thenReturn(ERROR_CODE);
        RequestConsumer<Comic> requestConsumer = new RequestConsumer<>(callback);

        try {
            requestConsumer.accept(responseWrapper);
        } catch (Exception e) {
            e.printStackTrace();
        }
        verify(callback).onError(anyInt(), any());
    }
}
