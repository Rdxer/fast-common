package com.rdxer.common.utils;

import com.rdxer.common.utils.id.ShortEXUuid;
import org.junit.jupiter.api.Test;

import java.util.UUID;

class ShortEXUuidTest {

    @Test
    void getShortUUID() {
        UUID uuid = UUID.randomUUID();
        String s = ShortEXUuid.getShortUUID(uuid, 10);
        System.out.println(s);
    }
}