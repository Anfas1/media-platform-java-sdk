package com.wix.mediaplatform.v6.service.archive;

import com.wix.mediaplatform.v6.service.Job;
import com.wix.mediaplatform.v6.service.RestResponse;

public class CreateArchiveJob extends Job {

    private CreateArchiveSpecification specification;

    private RestResponse<CreateArchiveJobResult> result;

    public CreateArchiveJob() {
    }

    @Override
    public CreateArchiveSpecification getSpecification() {
        return specification;
    }

    @Override
    public RestResponse getResult() {
        return result;
    }
}
