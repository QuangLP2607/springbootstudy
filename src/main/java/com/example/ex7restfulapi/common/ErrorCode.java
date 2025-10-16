package com.example.ex7restfulapi.common;

public enum ErrorCode {

    // ==== SUCCESS ====
    SUCCESS(200, "OK"),
    CREATED(201, "Created successfully"),
    UPDATED(200, "Updated successfully"),
    DELETED(200, "Deleted successfully"),

    // ==== CLIENT ERRORS ====
    BAD_REQUEST(400, "Bad Request"),
    UNAUTHORIZED(401, "Unauthorized"),
    FORBIDDEN(403, "Forbidden"),
    NOT_FOUND(404, "Resource Not Found"),
    CONFLICT(409, "Conflict"),
    UNPROCESSABLE_ENTITY(422, "Unprocessable Entity"),

    // ==== SERVER ERROR ====
    INTERNAL_SERVER_ERROR(500, "Internal Server Error");

    private final int code;
    private final String message;

    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int code() { return code; }
    public String message() { return message; }
}
