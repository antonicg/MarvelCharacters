package com.antonicastejon.domain.helpers;

import com.antonicastejon.model.repository.entities.Comic;
import com.antonicastejon.model.repository.entities.Image;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Antoni Castejón on 29/01/2017.
 */

public class ComicHelperTest {

    private static final int SIZE_IMAGES = 1;

    @Mock
    Comic comic;
    @Mock
    Image image;
    @Mock
    List<Image> images;


    @Before
    public void initializeTests() {
        MockitoAnnotations.initMocks(this);

        when(comic.getThumbnail()).thenReturn(image);
        when(image.getPath()).thenReturn("");
        when(image.getExtension()).thenReturn("");
        when(comic.getImages()).thenReturn(images);
        when(images.get(anyInt())).thenReturn(image);
        when(images.size()).thenReturn(SIZE_IMAGES);
    }

    @Test
    public void testGetThumbnailUrl() {
        ComicHelper.getThumbnailUrl(comic);

        verify(comic).getThumbnail();
        verify(image).getPath();
        verify(image).getExtension();
    }

    @Test
    public void testGetRandomImageUrl() {
        ComicHelper.getRandomImageUrl(comic);

        verify(comic).getImages();
        verify(images).get(anyInt());
        verify(image).getPath();
        verify(image).getExtension();

    }
}
