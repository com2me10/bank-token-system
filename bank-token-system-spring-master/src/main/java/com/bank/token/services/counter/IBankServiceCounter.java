package com.bank.token.services.counter;

import com.bank.entity.CustomerToken;

/**
 * Created by bhuvneshwars on 16/5/18.
 */
public interface IBankServiceCounter {

    public void addTokenToQueue(CustomerToken token);

    public CustomerToken processToken(String counterType);

    public int pendingTokens();
}
