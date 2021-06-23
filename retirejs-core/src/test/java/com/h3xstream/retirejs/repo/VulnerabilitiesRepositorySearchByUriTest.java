package com.h3xstream.retirejs.repo;

import com.esotericsoftware.minlog.Log;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

import static com.h3xstream.retirejs.repo.PrettyDisplay.displayResults;
import org.json.JSONException;
import static org.testng.Assert.assertEquals;

/**
 * Test the class VulnerabilitiesRepository
 */
public class VulnerabilitiesRepositorySearchByUriTest {

    VulnerabilitiesRepository repo;

    @BeforeClass
    public void setUp() throws IOException, JSONException {
        Log.DEBUG();

        VulnerabilitiesRepositoryLoader.syncWithOnlineRepository = true;
        String filePathTestRepo = getClass().getResource("/retirejs_repository_test.json").toExternalForm();
        repo = new VulnerabilitiesRepositoryLoader().load(filePathTestRepo);
    }

    @Test
    public void findJqueryByUri() throws IOException {

        List<JsLibraryResult> res = repo.findByUri("/1.6.2/jquery.js");
        displayResults(res);
        assertEquals(res.size() , 2, "Jquery not found (/1.6.2/jquery.js)");

        res = repo.findByUri("/1.6.3/jquery.js");
        displayResults(res);
        assertEquals(res.size() , 1, "Jquery not found (/1.6.3/jquery.js)");

        res = repo.findByUri("/1.8.9/jquery.min.js");
        displayResults(res);
        assertEquals(res.size() , 1, "Jquery not found (/1.8.9/jquery.min.js)");
    }

    @Test
    public void findEmberByUri() throws IOException {

        List<JsLibraryResult> res = repo.findByUri("/v1.3.0-1/ember.js");
        displayResults(res);
        assertEquals(res.size() , 2, "Ember not found (/v1.3.0-1/ember.js)");

        res = repo.findByUri("/1.0.0-rc.5/ember.min.js");
        displayResults(res);
        assertEquals(res.size() , 2, "Ember not found (/1.0.0-rc.5/ember.min.js)");
    }
}
