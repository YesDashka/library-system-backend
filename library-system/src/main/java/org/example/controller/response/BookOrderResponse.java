package org.example.controller.response;

public class BookOrderResponse implements DefaultHttpResponse{
    private String orderId;
    private String message;

    public BookOrderResponse() {
    }

    public BookOrderResponse(String orderId, String message) {
        this.orderId = orderId;
        this.message = message;
    }

    public String getOrderId() {
        return orderId;
    }

    public String getMessage() {
        return message;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
