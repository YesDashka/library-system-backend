package org.example.service.order;

import org.example.controller.response.DefaultHttpResponse;

import java.io.Serializable;

public record BookOrderResponse(
        String id,
        double totalCost,
        String status
) implements Serializable, DefaultHttpResponse {

}

