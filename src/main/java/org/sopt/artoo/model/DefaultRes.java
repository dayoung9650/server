package org.sopt.artoo.model;


import org.sopt.artoo.utils.ResponseMessage;
import org.sopt.artoo.utils.StatusCode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.sopt.artoo.utils.StatusCode;

@Data
@Builder
@AllArgsConstructor
public class DefaultRes<T> {
    private int status;
    private String message;
    private T data;

    public DefaultRes(final int status, final String message) {
        this.status = status;
        this.message = message;
        this.data = null;
    }

    public static <T> DefaultRes<T> res(final int status, final String message) {
        return res(status, message, null);
    }

    public static <T> DefaultRes<T> res(final int status, final String message, final T t) {
        return DefaultRes.<T>builder()
                .data(t)
                .status(status)
                .message(message)
                .build();
    }

    public static final DefaultRes FAIL_DEFAULT_RES = new DefaultRes(StatusCode.INTERNAL_SERVER_ERROR, ResponseMessage.INTERNAL_SERVER_ERROR);

    public static final DefaultRes FAIL_AUTHORIZATION_RES = new DefaultRes(StatusCode.UNAUTHORIZED, ResponseMessage.UNAUTHORIZED);

}