package com.wix.mediaplatform.image.option.saturation;

import com.wix.mediaplatform.image.option.Option;

import static com.wix.mediaplatform.image.Validation.inRange;
import static java.lang.Integer.parseInt;

public class Saturation extends Option {

    public static final String KEY = "sat";

    private int saturation;

    public Saturation() {
        super(KEY);
    }

    public Saturation(int saturation) {
        super(KEY);
        if (!inRange(saturation, -100, 100)) {
            throw new IllegalArgumentException(saturation + " is not in range [-100,100]");
        }
        this.saturation = saturation;
    }

    @Override
    public String serialize() {
        return KEY + SEPARATOR + saturation;
    }

    @Override
    public Option deserialize(String... params) {
        saturation = parseInt(params[0]);
        return this;
    }
}
