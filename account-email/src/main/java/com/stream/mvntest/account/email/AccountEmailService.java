package com.stream.mvntest.account.email;

/**
 * Created by stream on 2014/11/3.
 */
public interface AccountEmailService {
    void sendMail(String to, String subject, String emailContext) throws AccountEmailException;
}
