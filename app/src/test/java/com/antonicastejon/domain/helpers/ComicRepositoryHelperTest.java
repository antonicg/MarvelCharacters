package com.antonicastejon.domain.helpers;

import com.antonicastejon.model.repository.entities.ComicRepository;
import com.antonicastejon.model.repository.entities.ImageRepository;

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

public class ComicRepositoryHelperTest {

    private static final int SIZE_IMAGES = 1;

    @Mock
    ComicRepository comicRepository;
    @Mock
    ImageRepository imageRepository;
    @Mock
    List<ImageRepository> imageRepositories;


    @Before
    public void initializeTests() {
        MockitoAnnotations.initMocks(this);

        when(comicRepository.getThumbnail()).thenReturn(imageRepository);
        when(imageRepository.getPath()).thenReturn("");
        when(imageRepository.getExtension()).thenReturn("");
        when(comicRepository.getImageRepositories()).thenReturn(imageRepositories);
        when(imageRepositories.get(anyInt())).thenReturn(imageRepository);
        when(imageRepositories.size()).thenReturn(SIZE_IMAGES);
    }

    @Test
    public void testGetThumbnailUrl() {
        ComicRepositoryHelper.getThumbnailUrl(comicRepository);

        verify(comicRepository).getThumbnail();
        verify(imageRepository).getPath();
        verify(imageRepository).getExtension();
    }

    @Test
    public void testGetRandomImageUrl() {
        ComicRepositoryHelper.getRandomImageUrl(comicRepository);

        verify(comicRepository).getImageRepositories();
        verify(imageRepositories).get(anyInt());
        verify(imageRepository).getPath();
        verify(imageRepository).getExtension();

    }
}
