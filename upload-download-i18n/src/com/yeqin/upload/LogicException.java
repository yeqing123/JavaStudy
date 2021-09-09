package com.yeqin.upload;


public class LogicException extends RuntimeException {

	private static final long serialVersionUID = 1L;
    /**
     * 包含了异常信息和原因的构造器
     * @param message 异常信息
     * @param cause 异常的原因
     */
	public LogicException(String message, Throwable cause) {
		super(message, cause);
	}
    /**
     * 只包含了异常信息的构造器
     * @param message 异常信息
     */
	public LogicException(String message) {
		super(message);
	}
}
