package com.arthurlamberti.cdb.domain.paper;

import com.arthurlamberti.cdb.domain.Identifier;
import com.arthurlamberti.cdb.domain.utils.IdUtils;

import static java.util.Objects.requireNonNull;

public class PaperID extends Identifier {

    private final String uuid;

    public PaperID(final String uuid) {
        requireNonNull(uuid);
        this.uuid = uuid;
    }

    public static PaperID from(final String anId) {
        return new PaperID(anId.toLowerCase());
    }

    public static PaperID unique() {
        return PaperID.from(IdUtils.uuid());
    }

    @Override
    public String getValue() {
        return this.uuid;
    }

}
