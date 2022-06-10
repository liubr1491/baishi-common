package io.baishi.common.http;

/**
 * 自定义HTTP访问异常
 *
 * @author nick
 * @date 2018/11/8
 */
public class CustomHttpException extends RuntimeException {

    public CustomHttpException() {
    }

    public CustomHttpException(String message) {
        super(message);
    }
}
