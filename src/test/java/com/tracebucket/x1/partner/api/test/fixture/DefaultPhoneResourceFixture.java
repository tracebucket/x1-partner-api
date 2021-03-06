package com.tracebucket.x1.partner.api.test.fixture;

import com.tracebucket.x1.dictionary.api.domain.PhoneType;
import com.tracebucket.x1.partner.api.rest.resources.DefaultPhoneResource;
import com.tracebucket.x1.partner.api.test.builder.DefaultPhoneResourceBuilder;

/**
 * Created by sadath on 16-Apr-15.
 */
public class DefaultPhoneResourceFixture {
    public static DefaultPhoneResource standardPhone() {
        DefaultPhoneResource email = DefaultPhoneResourceBuilder.aPhoneBuilder()
                .withExtension("102")
                .withNumber("(+91)080-233456")
                .withPhoneType(PhoneType.HOME)
                .build();
        return email;
    }
}