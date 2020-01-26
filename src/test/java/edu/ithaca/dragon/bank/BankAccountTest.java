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

        //@ symbol
        assertFalse(BankAccount.isEmailValid( "ab.com")); //boarder case
        assertTrue(BankAccount.isEmailValid( "a@b.com"));
        assertFalse(BankAccount.isEmailValid( "a@b@c.com"));

        //No dash
        assertTrue(BankAccount.isEmailValid("a@b.com")); //boarder case

        //Dash in between letters
        assertTrue(BankAccount.isEmailValid("a-b@c.com")); //boarder case
        assertTrue(BankAccount.isEmailValid("a-b@c-d.com"));

        //Dash in between one letter
        assertFalse(BankAccount.isEmailValid("-ab@c.com"));
        assertFalse(BankAccount.isEmailValid("ab-@c.com"));
        assertFalse(BankAccount.isEmailValid("ab@-c.com"));
        assertFalse(BankAccount.isEmailValid("ab@c.com-"));

        //No pound
        assertTrue(BankAccount.isEmailValid("a@b.com")); //boarder case

        //Any pound
        assertFalse(BankAccount.isEmailValid("a#bc@d.com")); //boarder case
        assertFalse(BankAccount.isEmailValid("a#bc@d#e.com"));
        assertFalse(BankAccount.isEmailValid("#a#b#c#@#d#e.com")); //boarder case

        //No dot
        assertFalse(BankAccount.isEmailValid("ab@c")); //boarder case

        //One dot in between letters
        assertTrue(BankAccount.isEmailValid("a@b.com")); //boarder case
        assertTrue(BankAccount.isEmailValid("a.b@c.com"));
        assertTrue(BankAccount.isEmailValid("a.b.c.d.e@f.com")); //boarder case

        //Dot next to one letter
        assertFalse(BankAccount.isEmailValid(".a@b.com"));
        assertFalse(BankAccount.isEmailValid("a.@b.com"));
        assertFalse(BankAccount.isEmailValid("a@.com")); //boarder case
        assertFalse(BankAccount.isEmailValid("a@b.")); //boarder case
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