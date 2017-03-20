package com.wix.mediaplatform.dto.metadata.basic;

public class VideoFormat {

    private String formatLongName;

    private Long duration;

    private Float bitrate;

    private Long size;

    public VideoFormat() {
    }

    public String getFormatLongName() {
        return formatLongName;
    }

    public Long getDuration() {
        return duration;
    }

    public Float getBitrate() {
        return bitrate;
    }

    public Long getSize() {
        return size;
    }
}
