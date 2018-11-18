package com.wix.mediaplatform.v6.management;

import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import com.wix.mediaplatform.v6.BaseTest;
import com.wix.mediaplatform.v6.auth.Authenticator;
import com.wix.mediaplatform.v6.configuration.Configuration;
import com.wix.mediaplatform.v6.http.AuthenticatedHTTPClient;
import com.wix.mediaplatform.v6.service.Destination;
import com.wix.mediaplatform.v6.service.Job;
import com.wix.mediaplatform.v6.service.Source;
import com.wix.mediaplatform.v6.service.response.TranscodeJobsResponse;
import com.wix.mediaplatform.v6.service.transcode.*;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class TranscodeServiceTest extends BaseTest {

    @Rule
    public WireMockRule wireMockRule = new WireMockRule(wireMockConfig().httpsPort(PORT));

    private Configuration configuration = new Configuration("localhost:" + PORT, "appId", "sharedSecret");
    private Authenticator authenticator = new Authenticator(configuration);
    private AuthenticatedHTTPClient authenticatedHttpClient = new AuthenticatedHTTPClient(authenticator, httpClient, gson);

    private TranscodeService transcodeService = new TranscodeService(configuration, authenticatedHttpClient);

    @Before
    public void setup() {
        WireMock.reset();
    }

    @Test
    public void transcodeFile() throws Exception {
        stubFor(post(urlEqualTo("/_api/av/transcode"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("transcode-response.json")));

        TranscodeRequest transcodeRequest = new TranscodeRequest()
                .addSource(new Source().setPath("/test/video.mp4") )
                .addSpecification( new TranscodeSpecification()
                        .setDestination(new Destination()
                                .setDirectory("/test/encodes/")
                                .setAcl("public"))
                        .setQualityRange( new QualityRange()
                                .setMinimum("240p")
                                .setMaximum("1440p")));
        TranscodeJobsResponse response = transcodeService.transcodeVideo(transcodeRequest);

        assertThat(response.getGroupId(), is("fb79405a16434aab87ccbd1384563033"));
        for (Job job: response.getJobs()) {
            assertThat(job instanceof TranscodeJob, is(true));
        }

    }

}