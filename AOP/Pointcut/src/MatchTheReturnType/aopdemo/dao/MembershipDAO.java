package MatchTheReturnType.aopdemo.dao;

import org.springframework.stereotype.Component;

@Component
public class MembershipDAO {

    public boolean addChangeToMethodToMatch() {
        System.out.println(getClass() + ": DOING STUFF: ADD MEMBERSHIP ACCOUNT.");

        return true;
    }
}
