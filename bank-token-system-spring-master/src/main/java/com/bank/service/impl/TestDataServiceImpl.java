package com.bank.service.impl;

import com.bank.constants.CustomerType;
import com.bank.constants.ServiceType;
import com.bank.entity.Bank;
import com.bank.entity.Branch;
import com.bank.entity.ServiceCounter;
import com.bank.repo.BankRepo;
import com.bank.repo.BranchRepo;
import com.bank.repo.ServiceCounterRepo;
import com.bank.service.TestDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class TestDataServiceImpl implements TestDataService {

    @Autowired
    BankRepo bankRepo;
    @Autowired
    BranchRepo branchRepo;
    @Autowired
    ServiceCounterRepo serviceCounterRepo;

    public boolean createBankDummyData() {

        boolean created = createBankServiceCounters();
        return created;
    }

    boolean createBankServiceCounters() {

        List<Bank> banks = bankRepo.findAll();
        if(banks.size()>0) {
            return false;
        }

        Bank bank = createBank();
        Branch[] branches = createBankBranches(bank);

        ServiceCounter s1 = new ServiceCounter("D1", ServiceType.DEPOSIT.toString(), CustomerType.PREMIUM.toString(), branches[0]);
        serviceCounterRepo.save(s1);
        ServiceCounter s2 = new ServiceCounter("D2", ServiceType.DEPOSIT.toString(), CustomerType.REGULAR.toString(), branches[0]);
        serviceCounterRepo.save(s2);
        ServiceCounter s3 = new ServiceCounter("D3", ServiceType.DEPOSIT.toString(), CustomerType.REGULAR.toString(), branches[0]);
        serviceCounterRepo.save(s3);
        ServiceCounter s4 = new ServiceCounter("W1", ServiceType.WITHDRAW.toString(), CustomerType.PREMIUM.toString(), branches[0]);
        serviceCounterRepo.save(s4);
        ServiceCounter s5 = new ServiceCounter("W2", ServiceType.WITHDRAW.toString(), CustomerType.REGULAR.toString(), branches[0]);
        serviceCounterRepo.save(s5);
        ServiceCounter s6 = new ServiceCounter("W3", ServiceType.WITHDRAW.toString(), CustomerType.REGULAR.toString(), branches[0]);
        serviceCounterRepo.save(s6);
        ServiceCounter s7 = new ServiceCounter("A1", ServiceType.ACCOUNT.toString(), CustomerType.PREMIUM.toString(), branches[0]);
        serviceCounterRepo.save(s7);
        ServiceCounter s8 = new ServiceCounter("A2", ServiceType.ACCOUNT.toString(), CustomerType.REGULAR.toString(), branches[0]);
        serviceCounterRepo.save(s8);
        ServiceCounter s9 = new ServiceCounter("E1", ServiceType.ENQUIERY.toString(), CustomerType.REGULAR.toString(), branches[0]);
        serviceCounterRepo.save(s9);

        ServiceCounter s11 = new ServiceCounter("A11", ServiceType.ACCOUNT.toString(), CustomerType.REGULAR.toString(), branches[1]);
        serviceCounterRepo.save(s11);
        ServiceCounter s12 = new ServiceCounter("E11", ServiceType.ENQUIERY.toString(), CustomerType.REGULAR.toString(), branches[1]);
        serviceCounterRepo.save(s12);
        ServiceCounter s13 = new ServiceCounter("D11", ServiceType.DEPOSIT.toString(), CustomerType.REGULAR.toString(), branches[1]);
        serviceCounterRepo.save(s13);
        ServiceCounter s14 = new ServiceCounter("W11", ServiceType.WITHDRAW.toString(), CustomerType.REGULAR.toString(), branches[1]);
        serviceCounterRepo.save(s14);

        return true;
    }

    Bank createBank() {
        Bank bank = new Bank("ABC");
        bankRepo.save(bank);
        return bank;
    }

    Branch[] createBankBranches(Bank bank) {

        Branch branch1 = new Branch("beghumpeth", "BANK001", bank);
        branchRepo.save(branch1);
        Branch branch2 = new Branch("bnjarahills", "BANK002", bank);
        branchRepo.save(branch2);
        Branch branches[] = new Branch[2];
        branches[0] = branch1;
        branches[1] = branch2;
        return branches;
    }

}
