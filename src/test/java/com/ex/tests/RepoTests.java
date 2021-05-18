package com.ex.tests;

import com.ex.repository.Repository;
import org.junit.Before;

import static org.mockito.Mockito.mock;

public class RepoTests {
    private Repository dao;

    @Before
    public void init() {
        dao = mock(Repository.class);
    }


}
