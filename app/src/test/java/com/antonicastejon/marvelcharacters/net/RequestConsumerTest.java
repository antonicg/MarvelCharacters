package com.antonicastejon.marvelcharacters.net;

import com.antonicastejon.domain.RequestConsumer;
import com.antonicastejon.model.repository.api.ResponseWrapper;
import com.antonicastejon.model.repository.entities.ComicRepository;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;

/**
 * Created by Antoni Castejón on 29/01/2017.
 */

public class RequestConsumerTest {

    private final static int OK_CODE = 200;
    private final static int ERROR_CODE = -1;

    @Mock
    ResponseWrapper<ComicRepository> responseWrapper;
    @Mock
    RequestConsumer.Callback<ComicRepository> callback;

    @Before
    public void initializeTest() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testConsumerAcceptOk() {
//        when(responseWrapper.getCode()).thenReturn(OK_CODE);
//        RequestConsumer<ComicRepository> requestConsumer = new RequestConsumer<>(callback);
//
//        try {
//            requestConsumer.accept(responseWrapper);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        verify(callback).onResponse(any());
    }

    @Test
    public void testConsumerAcceptError() {
//        when(responseWrapper.getCode()).thenReturn(ERROR_CODE);
//        RequestConsumer<ComicRepository> requestConsumer = new RequestConsumer<>(callback);
//
//        try {
//            requestConsumer.accept(responseWrapper);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        verify(callback).onError(anyInt(), any());
    }
}
