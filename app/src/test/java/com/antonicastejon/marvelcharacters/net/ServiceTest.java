package com.antonicastejon.marvelcharacters.net;

import android.content.Context;

import com.antonicastejon.domain.RequestConsumer;
import com.antonicastejon.domain.helpers.NetworkStateHelper;
import com.antonicastejon.model.repository.api.ResponseWrapper;
import com.antonicastejon.model.repository.crypt.MD5;
import com.antonicastejon.model.repository.entities.CharacterRepository;
import com.antonicastejon.model.repository.services.base.Service;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

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
    RequestConsumer<CharacterRepository> requestConsumer;
    @Mock
    ResponseWrapper<CharacterRepository> responseMocked;

    private Service<CharacterRepository> service;

    @Before
    public void initializeTests() {
//        MockitoAnnotations.initMocks(this);
//
//        service = new Service<CharacterRepository>(md5, "", "") {
//            @Override
//            protected Flowable<ResponseWrapper<CharacterRepository>> executeService(long timeStamp, String hash) {
//                try {
//                    requestConsumer.accept(responseMocked);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//                return null;
//            }
//        };
    }

    @Test
    public void executeRequestWithInternetAvailable() {
//        when(networkStateHelper.isNetworkAvailable(context)).thenReturn(Boolean.TRUE);
//
//        service.executeRequest(requestConsumer);
//
//        verify(networkStateHelper).isNetworkAvailable(context);
//        try {
//            verify(md5).getMD5(anyString());
//            verify(requestConsumer).accept(responseMocked);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }

    @Test
    public void executeRequestWithNoInternetAvailable() {

//        String notInternetMessage = new Service.NoInternetException().getMessage();
//        when(networkStateHelper.isNetworkAvailable(context)).thenReturn(Boolean.FALSE);
//
//        service.executeRequest(requestConsumer);
//
//        verify(networkStateHelper).isNetworkAvailable(context);
//        try {
//            verify(requestConsumer).onError(-1, notInternetMessage);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }
}
