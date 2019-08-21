package com.lee.vocabulary.core.security;

import javax.servlet.http.HttpServletRequest;

/**
 * Strategy for automatic login of a user after registration
 */
public interface AutoLoginStrategy {
    /**
     * Login a user
     *
     * @param username
     * @param password
     * @param request
     */
    void login(String username, String password, HttpServletRequest request);
}
