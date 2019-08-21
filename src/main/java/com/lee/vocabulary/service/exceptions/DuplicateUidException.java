/*
 * [y] hybris Platform
 *
 * Copyright (c) 2018 SAP SE or an SAP affiliate company.  All rights reserved.
 *
 * This software is the confidential and proprietary information of SAP
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with SAP.
 */
package com.lee.vocabulary.service.exceptions;


public class DuplicateUidException extends Exception {

    private static final long serialVersionUID = -8981332703089794002L;

    public DuplicateUidException() {
        super();
    }

    public DuplicateUidException(String message, Throwable cause) {
        super(message, cause);
    }

    public DuplicateUidException(String message) {
        super(message);
    }

    public DuplicateUidException(Throwable cause) {
        super(cause);
    }

}
