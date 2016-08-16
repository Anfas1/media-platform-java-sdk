package com.wix.mediaplatform.image.option.hue;

import com.wix.mediaplatform.image.option.Option;

import static com.wix.mediaplatform.image.Validation.inRange;
import static java.lang.Integer.parseInt;

public class Hue extends Option {

    public static final String KEY = "hue";

    private int hue;

    public Hue() {
        super(KEY);
    }

    public Hue(int hue) {
        super(KEY);
        if (!inRange(hue, -100, 100)) {
            throw new IllegalArgumentException(hue + " is not in range [-100,100]");
        }
        this.hue = hue;
    }

    @Override
    public String serialize() {
        return KEY + SEPARATOR + hue;
    }

    @Override
    public Option deserialize(String... params) {
        hue = parseInt(params[0]);
        return this;
    }
}
