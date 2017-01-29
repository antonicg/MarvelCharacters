package com.antonicastejon.marvelcharacters.net;

import android.content.Context;

import com.antonicastejon.marvelcharacters.model.Comic;
import com.antonicastejon.marvelcharacters.net.requests.base.RequestConsumer;
import com.antonicastejon.marvelcharacters.net.response.ResponseWrapper;
import com.antonicastejon.marvelcharacters.net.services.Service;
import com.antonicastejon.marvelcharacters.utils.crypt.MD5;
import com.antonicastejon.marvelcharacters.utils.network.NetworkStateHelper;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Antoni Castejón on 29/01/2017.
 */

public class ServiceTest {

    @Mock
    NetworkStateHelper networkStateHelper;
    @Mock
    Context context;
    @Mock
    MD5 md5;
    @Mock
    RequestConsumer<Comic> requestConsumer;
    @Mock
    ResponseWrapper<Comic> responseMocked;

    private Service<Comic> service;

    @Before
    public void initializeTests() {
        MockitoAnnotations.initMocks(this);

        service = new Service<Comic>(context, md5, networkStateHelper) {
            @Override
            protected void executeService(long timeStamp, String hash, RequestConsumer callback) {
                try {
                    requestConsumer.accept(responseMocked);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
    }

    @Test
    public void executeRequestWithInternetAvailable() {
        when(networkStateHelper.isNetworkAvailable(context)).thenReturn(Boolean.TRUE);

        service.executeRequest(requestConsumer);

        verify(networkStateHelper).isNetworkAvailable(context);
        try {
            verify(md5).getMD5(anyString());
            verify(requestConsumer).accept(responseMocked);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void executeRequestWithNoInternetAvailable() {

        String notInternetMessage = new Service.NoInternetException().getMessage();
        when(networkStateHelper.isNetworkAvailable(context)).thenReturn(Boolean.FALSE);

        service.executeRequest(requestConsumer);

        verify(networkStateHelper).isNetworkAvailable(context);
        try {
            verify(requestConsumer).onError(-1, notInternetMessage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
