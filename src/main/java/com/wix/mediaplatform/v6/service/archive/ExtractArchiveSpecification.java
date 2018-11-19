package com.wix.mediaplatform.v6.service.archive;


import com.wix.mediaplatform.v6.service.Destination;
import com.wix.mediaplatform.v6.service.Source;
import com.wix.mediaplatform.v6.service.Specification;

public class ExtractArchiveSpecification implements Specification {

    private Source source;

    private Destination destination;

    private ExtractedFilesReport extractedFilesReport;

    public ExtractArchiveSpecification() {
    }

    public Source getSource() {
        return source;
    }

    public Destination getDestination() {
        return destination;
    }

    public ExtractedFilesReport getExtractedFilesReport() {
        return extractedFilesReport;
    }
}
