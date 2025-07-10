package com.example.todolistapp.enums;

public enum Status {
    COMPLETED,
    IN_PROGRESS,
    CANCELLED,
    PENDING;

    public boolean isTerminal() {
        return this == COMPLETED || this == CANCELLED;
    }

    public boolean canTransitionTo(Status newStatus) {
        // Разрешаем любые переходы между не-терминальными статусами
        if (!this.isTerminal() && !newStatus.isTerminal()) {
            return true;
        }

        // Конкретные правила переходов в терминальные статусы:
        return switch (this) {
            case IN_PROGRESS -> newStatus == COMPLETED || newStatus == CANCELLED;
            case PENDING -> newStatus == IN_PROGRESS || newStatus == CANCELLED;
            default -> false;
        };
    }
}
