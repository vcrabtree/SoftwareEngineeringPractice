package edu.ithaca.dragon.bank;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BankAccountTest {

    @Test
    void getBalanceTest() {
        BankAccount bankAccountA = new BankAccount("a@b.com", 0);
        assertEquals(0, bankAccountA.getBalance()); //border case
        BankAccount bankAccountB = new BankAccount("a@b.com", 200);
        assertEquals(200, bankAccountB.getBalance());
        BankAccount bankAccountC = new BankAccount("a@b.com", 100000000);
        assertEquals(100000000, bankAccountC.getBalance()); //border case
    }

    @Test
    void isAmountValidTest() {
        BankAccount bankAccount = new BankAccount("a@b.com", 2000);
        // Negative, One to Two Decimals
        assertFalse(bankAccount.isAmountValid(-1.01)); // border case
        assertFalse(bankAccount.isAmountValid(-53.83));
        assertFalse(bankAccount.isAmountValid(-9999999.9)); // border case

        // Positive, One to Two Decimals
        assertTrue(bankAccount.isAmountValid(0.01)); // border case
        assertTrue(bankAccount.isAmountValid(42.7));
        assertTrue(bankAccount.isAmountValid(9999999.99)); //border case

        // Negative, Multiple Decimals
        assertFalse(bankAccount.isAmountValid(-1.0000001)); // border case
        assertFalse(bankAccount.isAmountValid(-7.48));
        assertFalse(bankAccount.isAmountValid(-9999999.9999999)); // border case

        // Positive, Multiple Decimals
        assertFalse(bankAccount.isAmountValid(0.000001)); // border case
        assertFalse(bankAccount.isAmountValid(92.498865));
        assertFalse(bankAccount.isAmountValid(9999999.999999)); //border case
    }

    @Test
    void withdrawTest() throws InsufficientFundsException{
        BankAccount bankAccount = new BankAccount("a@b.com", 200);

        //Withdraw negative amount of money
        bankAccount.withdraw(-1);
        assertEquals(200, bankAccount.getBalance()); //border case
        bankAccount.withdraw(-50);
        assertEquals(200, bankAccount.getBalance());
        bankAccount.withdraw(-1000);
        assertEquals(200, bankAccount.getBalance()); //border case

        //Withdraw more than is in account
        assertThrows(InsufficientFundsException.class, () -> bankAccount.withdraw(201)); //border case
        assertThrows(InsufficientFundsException.class, () -> bankAccount.withdraw(350));
        assertThrows(InsufficientFundsException.class, () -> bankAccount.withdraw(1000)); //border case

        //Withdraw equals or less than is in account
        bankAccount.withdraw(1);
        assertEquals(199, bankAccount.getBalance()); //border case
        bankAccount.withdraw(100);
        assertEquals(99, bankAccount.getBalance());
        bankAccount.withdraw(99);
        assertEquals(0, bankAccount.getBalance()); //border case
    }

    @Test
    void isEmailValidTest(){

        //@ symbol
        assertFalse(BankAccount.isEmailValid( "ab.com")); //border case
        assertTrue(BankAccount.isEmailValid( "a@b.com"));
        assertFalse(BankAccount.isEmailValid( "a@b@c.com"));

        //No dash
        assertTrue(BankAccount.isEmailValid("a@b.com")); //border case

        //Dash in between letters
        assertTrue(BankAccount.isEmailValid("a-b@c.com")); //border case
        assertTrue(BankAccount.isEmailValid("a-b@c-d.com"));

        //Dash in between one letter
        assertFalse(BankAccount.isEmailValid("-ab@c.com"));
        assertFalse(BankAccount.isEmailValid("ab-@c.com"));
        assertFalse(BankAccount.isEmailValid("ab@-c.com"));
        assertFalse(BankAccount.isEmailValid("ab@c.com-"));

        //No pound
        assertTrue(BankAccount.isEmailValid("a@b.com")); //border case

        //Any pound
        assertFalse(BankAccount.isEmailValid("a#bc@d.com")); //border case
        assertFalse(BankAccount.isEmailValid("a#bc@d#e.com"));
        assertFalse(BankAccount.isEmailValid("#a#b#c#@#d#e.com")); //border case

        //No dot
        assertFalse(BankAccount.isEmailValid("ab@c")); //border case

        //One dot in between letters
        assertTrue(BankAccount.isEmailValid("a@b.com")); //border case
        assertTrue(BankAccount.isEmailValid("a.b@c.com"));
        assertTrue(BankAccount.isEmailValid("a.b.c.d.e@f.com")); //border case

        //Dot next to one letter
        assertFalse(BankAccount.isEmailValid(".a@b.com"));
        assertFalse(BankAccount.isEmailValid("a.@b.com"));
        assertFalse(BankAccount.isEmailValid("a@.com")); //border case
        assertFalse(BankAccount.isEmailValid("a@b.")); //border case
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