package com.zeeesstudio.accounts.service;

import com.zeeesstudio.accounts.dto.CustomerDto;

public interface IAccountService {
    /**
     * @param customerDto
     */
    void createAccount(CustomerDto customerDto);

    /**
     * @param mobileNumber
     * @return
     */
    CustomerDto fetchAccount(String mobileNumber);

    /**
     * @param customerDto
     * @return
     */
    boolean updateAccount(CustomerDto customerDto);

    /**
     * @param mobileNumber
     * @return
     */
    boolean deleteAccount(String mobileNumber);
}
