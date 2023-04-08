package org.example.service.order;

import java.io.Serializable;

public record BookOrderResult(double totalCost, String status) implements Serializable {

}

