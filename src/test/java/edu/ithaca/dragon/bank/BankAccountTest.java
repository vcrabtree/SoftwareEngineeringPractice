package edu.ithaca.dragon.bank;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BankAccountTest {

    @Test
    void getBalanceTest() {
        BankAccount bankAccount = new BankAccount("a@b.com", 200);

        assertEquals(200, bankAccount.getBalance());
    }

    @Test
    void withdrawTest() {
        BankAccount bankAccount = new BankAccount("a@b.com", 200);
        bankAccount.withdraw(100);

        assertEquals(100, bankAccount.getBalance());
    }

    @Test
    void isEmailValidTest(){

        //equivalence classes

        //valid prefix and domain
        assertTrue(BankAccount.isEmailValid( "a@b.com"));

        //invalid prefix and domain
        assertFalse(BankAccount.isEmailValid("")); //border case - no email
        //other border case - multiple errors in both parts?

        //invalid prefix
        assertFalse(BankAccount.isEmailValid("haha-@haha.com"));
        assertFalse(BankAccount.isEmailValid("haha..haha@haha.com"));
        assertFalse(BankAccount.isEmailValid(".haha@haha.com"));
        assertFalse(BankAccount.isEmailValid("haha#haha@haha.com"));
        assertFalse(BankAccount.isEmailValid("@haha.com")); //border case - no prefix at all
        //other border case - multiple errors in prefix

        //invalid domain
        assertFalse(BankAccount.isEmailValid("haha@")); //border case - no domain
        assertFalse(BankAccount.isEmailValid("haha@haha..com"));
        //other border case - multiple errors in domain
    }

    @Test
    void constructorTest() {
        BankAccount bankAccount = new BankAccount("a@b.com", 200);

        assertEquals("a@b.com", bankAccount.getEmail());
        assertEquals(200, bankAccount.getBalance());
        //check for exception thrown correctly
        assertThrows(IllegalArgumentException.class, ()-> new BankAccount("", 100));
    }

}