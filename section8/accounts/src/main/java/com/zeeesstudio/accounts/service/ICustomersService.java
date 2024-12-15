package com.zeeesstudio.accounts.service;

import com.zeeesstudio.accounts.dto.CustomerDetailsDto;

public interface ICustomersService {
    /**
     *
     * @param mobileNumber - Input mobile number
     * @return Customer details based on a given mobileNumber
     */
    CustomerDetailsDto fetchCustomerDetails(String mobileNumber);

}
