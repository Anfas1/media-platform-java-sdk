package com.wix.mediaplatform.v6.service.file;

import com.wix.mediaplatform.v6.service.FileDescriptor;
import com.wix.mediaplatform.v6.service.Job;
import com.wix.mediaplatform.v6.service.RestResponse;

public class ImportFileJob extends Job<FileDescriptor> {

    private ImportFileSpecification specification;

    private RestResponse<FileDescriptor> result;

    public ImportFileJob() {
    }

    @Override
    public ImportFileSpecification getSpecification() {
        return specification;
    }

    @Override
    public RestResponse<FileDescriptor> getResult() {
        return result;
    }
}
