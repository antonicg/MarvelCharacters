package com.antonicastejon.marvelcharacters.net.requests.comics;

import com.antonicastejon.marvelcharacters.net.requests.base.RequestConsumer;
import com.antonicastejon.marvelcharacters.net.services.MarvelService;
import com.antonicastejon.model.repository.entities.Comic;

/**
 * Created by Antoni Castejón on 28/01/2017.
 */

public class ComicsRequest {

    protected MarvelService service;
    private RequestConsumer<Comic> consumer;

    public ComicsRequest(MarvelService service, RequestConsumer<Comic> consumer) {
        this.service = service;
        this.consumer = consumer;
    }

    public void execute(int offset) {
        service.setOffset(offset);
        service.executeRequest(consumer);
    }
}
