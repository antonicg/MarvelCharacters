package com.antonicastejon.model;

import com.antonicastejon.model.repository.api.MarvelApi;
import com.antonicastejon.model.repository.api.ResponseWrapper;
import com.antonicastejon.model.repository.crypt.MD5;
import com.antonicastejon.model.repository.entities.CharacterRepository;
import com.antonicastejon.model.repository.services.MarvelService;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import io.reactivex.Flowable;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Antoni Castejón on 29/01/2017.
 */

public class ServiceTest {

    @Mock
    MD5 md5;
    @Mock
    ResponseWrapper<CharacterRepository> responseMocked;
    @Mock
    MarvelApi marvelApi;

    private MarvelService service;

    @Before
    public void initializeTests() {
        MockitoAnnotations.initMocks(this);
        service = new MarvelService(marvelApi, md5, "", "");
    }

    @Test
    public void testExecute() {
        when(marvelApi.getCharacters(anyLong(), anyString(), anyString(), anyInt(), anyInt()))
                .thenReturn(Flowable.just(responseMocked));
        try {
            when(md5.getMD5(anyString())).thenReturn("");
        } catch (Exception e) {
            e.printStackTrace();
        }

        service.execute();

        try {
            verify(md5).getMD5(anyString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
