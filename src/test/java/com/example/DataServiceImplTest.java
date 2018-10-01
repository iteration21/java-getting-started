package com.example;

import java.util.ArrayList;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;


public class DataServiceImplTest {

    @org.junit.Test
    public void enrichWithSystemData() throws Exception {
        //test with no spring context
        //arrange
        DataServiceImpl service = new DataServiceImpl();
        ArrayList<String> output = new ArrayList<>();

        //act
        service.enrichWithSystemData(output);

        //assert
        assertThat(output.size(), is(greaterThan(10)));
    }

}