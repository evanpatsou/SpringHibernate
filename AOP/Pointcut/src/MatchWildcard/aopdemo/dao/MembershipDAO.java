package MatchWildcard.aopdemo.dao;

import org.springframework.stereotype.Component;

@Component
public class MembershipDAO {

    public void addChangeToMethodToMatch() {
        System.out.println(getClass() + ": DOING STUFF: ADD MEMBERSHIP ACCOUNT.");
    }
}
