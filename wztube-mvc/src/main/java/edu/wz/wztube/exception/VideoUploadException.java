package edu.wz.wztube.exception;

public class VideoUploadException extends RuntimeException{
    public VideoUploadException() {
    }
    
    public VideoUploadException(String message) {
        super(message);
    }
    
    public VideoUploadException(String message, Throwable cause) {
        super(message, cause);
    }
    
    public VideoUploadException(Throwable cause) {
        super(cause);
    }
    
    public VideoUploadException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
