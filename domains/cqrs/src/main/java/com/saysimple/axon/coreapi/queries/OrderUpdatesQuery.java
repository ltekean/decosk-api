package com.saysimple.axon.coreapi.queries;

import lombok.Getter;

import java.util.Objects;

@Getter
public class OrderUpdatesQuery {

    private final String orderId;

    public OrderUpdatesQuery(String orderId) {
        this.orderId = orderId;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        OrderUpdatesQuery that = (OrderUpdatesQuery) o;
        return Objects.equals(orderId, that.orderId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId);
    }

    @Override
    public String toString() {
        return "OrderUpdatesQuery{" + "orderId='" + orderId + '\'' + '}';
    }
}
