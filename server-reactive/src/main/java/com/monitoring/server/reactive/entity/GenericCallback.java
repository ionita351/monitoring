package com.monitoring.server.reactive.entity;

import org.reactivestreams.Publisher;
import org.springframework.data.r2dbc.mapping.OutboundRow;
import org.springframework.data.r2dbc.mapping.event.BeforeSaveCallback;
import org.springframework.data.relational.core.sql.SqlIdentifier;
import org.springframework.r2dbc.core.Parameter;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

public class GenericCallback<S> implements BeforeSaveCallback<S> {

    @Override
    public Publisher<S> onBeforeSave(S entity, OutboundRow row, SqlIdentifier table) {
        if (Optional.ofNullable(row.get("id"))
                .map(Parameter::getValue)
                .orElse(null) == null) {
            row.put("id", Parameter.from(UUID.randomUUID()));
        }
        row.put("time_stamp", Parameter.from(LocalDateTime.now()));
        return Mono.just(entity);
    }
}
