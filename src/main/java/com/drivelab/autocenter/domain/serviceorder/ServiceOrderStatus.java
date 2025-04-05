package com.drivelab.autocenter.domain.serviceorder;

import com.drivelab.autocenter.domain.DomainException;
import org.springframework.lang.NonNull;

import java.util.Arrays;
import java.util.Set;

public enum ServiceOrderStatus {
    WAITING_START("waiting_start", Set.of("work_in_progress")),
    WORK_IN_PROGRESS("work_in_progress", Set.of("paused")),
    PAUSED("paused", Set.of("work_in_progress"));

    private final String key;
    private final Set<String> nextAllowedList;

    ServiceOrderStatus(String key, Set<String> nextAllowedList) {
        this.key = key;
        this.nextAllowedList = nextAllowedList;
    }

    public String key() {
        return key;
    }

    public static ServiceOrderStatus fromKey(@NonNull String key) {
        return Arrays.stream(values())
                .filter(t -> t.key().equals(key))
                .findFirst()
                .orElseThrow(() -> new DomainException("Invalid ServiceOrderStatus key"));
    }

    public boolean canChangeTo(@NonNull ServiceOrderStatus nextStatus) {
        return nextAllowedList.contains(nextStatus.key);
    }

    @Override
    public String toString() {
        return key;
    }
}
