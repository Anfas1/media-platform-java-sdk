package com.wix.mediaplatform.v8.service.file;

import com.wix.mediaplatform.v8.http.AuthenticatedHTTPClient;
import com.wix.mediaplatform.v8.service.MediaPlatformRequest;

public class DeleteFileIdRequest extends MediaPlatformRequest {

    DeleteFileIdRequest(AuthenticatedHTTPClient authenticatedHTTPClient, String baseUrl, String fileId) {
        super(authenticatedHTTPClient, "DELETE", baseUrl + "/files/" + fileId, null);
    }

}
