package com.zeeesstudio.accounts.service.impl;

import com.zeeesstudio.accounts.dto.AccountDto;
import com.zeeesstudio.accounts.dto.CardsDto;
import com.zeeesstudio.accounts.dto.CustomerDetailsDto;
import com.zeeesstudio.accounts.dto.LoansDto;
import com.zeeesstudio.accounts.entity.Account;
import com.zeeesstudio.accounts.entity.Customer;
import com.zeeesstudio.accounts.exception.ResourceNotFoundException;
import com.zeeesstudio.accounts.mapper.AccountMapper;
import com.zeeesstudio.accounts.mapper.CustomerMapper;
import com.zeeesstudio.accounts.repository.AccountRepository;
import com.zeeesstudio.accounts.repository.CustomerRepository;
import com.zeeesstudio.accounts.service.ICustomersService;
import com.zeeesstudio.accounts.service.client.CardsFeignClient;
import com.zeeesstudio.accounts.service.client.LoansFeignClient;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomersServiceImpl implements ICustomersService {
    private AccountRepository accountRepository;
    private CustomerRepository customerRepository;
    private CardsFeignClient cardsFeignClient;
    private LoansFeignClient loansFeignClient;
    /**
     * @param mobileNumber - Input mobile number
     * @return Customer details based on a given mobileNumber
     */
    @Override
    public CustomerDetailsDto fetchCustomerDetails(String mobileNumber) {
        Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber)
        );
        Account account = accountRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(
                () -> new ResourceNotFoundException("Account", "customerId", customer.getCustomerId().toString())
        );

        CustomerDetailsDto customerDetailsDto = CustomerMapper.mapToCustomerDetailsDto(customer, new CustomerDetailsDto());
        customerDetailsDto.setAccountDto(AccountMapper.mapToAccountsDto(account, new AccountDto()));

        ResponseEntity<LoansDto> loansDtoResponseEntity = loansFeignClient.fetchLoanDetails(mobileNumber);
        customerDetailsDto.setLoansDto(loansDtoResponseEntity.getBody());

        ResponseEntity<CardsDto> cardsDtoResponseEntity = cardsFeignClient.fetchCardDetails(mobileNumber);
        customerDetailsDto.setCardsDto(cardsDtoResponseEntity.getBody());

        return customerDetailsDto;
    }
}
