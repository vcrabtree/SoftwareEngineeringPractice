package edu.ithaca.dragon.bank;

public class BankAccount {

    private String email;
    private double balance;

    /**
     * @throws IllegalArgumentException if email is invalid
     */
    public BankAccount(String email, double startingBalance) throws IllegalArgumentException {
        if (isEmailValid(email)){
            this.email = email;
            this.balance = startingBalance;
        }
        else {
            throw new IllegalArgumentException("Email address: " + email + " is invalid, cannot create account");
        }
        if (isAmountValid(startingBalance) == true) {
            this.balance = startingBalance;
        }
        else {
            throw new IllegalArgumentException("Invalid starting balance");
        }
    }

    public double getBalance(){
        return balance;
    }

    public String getEmail(){
        return email;
    }

    public static boolean isAmountValid(double amount) {
        if (amount < 0) {
            return false;
        }
        String amountString = Double.toString(amount);
        int length = amountString.length();
        int decimal = amountString.indexOf('.');
        int numDecimal = (length - 1) - decimal;
        if (numDecimal > 2) {
            return false;
        }
        return true;
    }

    /**
     * @post reduces the balance by amount if amount is non-negative and smaller than balance
     */
    public void withdraw (double amount) throws InsufficientFundsException, IllegalArgumentException {
        if (isAmountValid(amount) == false) {
            throw new IllegalArgumentException("Not a valid amount");
        }

        if (amount <= balance) {
            balance -= amount;
        }
        else {
            throw new InsufficientFundsException("Not enough money");
        }
    }


    public static boolean isEmailValid(String email){

        int length = email.length();
        int atIndex = email.indexOf('@');

        //make sure there is some @ in the middle of the address
        if (atIndex == -1 || atIndex == 0 || atIndex == length-1) {
            return false;
        }

        //make sure there's only one @ and split email
        String[] emailSplit = email.split("@");
        if (emailSplit.length > 2) {
            return false;
        }

        //make sure there's a dot something at least 2 chars in domain
        int dotIndex = email.lastIndexOf('.');
        if (atIndex > dotIndex || dotIndex > email.length() - 3){
            return false;
        }


        for (int emailPiece = 0; emailPiece < emailSplit.length; emailPiece++) {

            String currPiece = emailSplit[emailPiece];
            for (int charIndex = 0; charIndex < currPiece.length(); charIndex++) {
                char currChar = currPiece.charAt(charIndex);

                //check for valid characters
                Boolean valid = false;

                //special characters
                if (currChar == 45 || currChar == 46 || currChar == 95 ) {
                    valid = true;
                    //check for special characters at beginning and end
                    if (charIndex == 0 || charIndex == currPiece.length()-1) {
                        valid = false;
                    } else {
                        //check for double special characters
                        char left = currPiece.charAt(charIndex-1);
                        char right = currPiece.charAt(charIndex+1);
                        if (left == 45 || left == 46 || left == 95 || right == 45 || right == 46 || right == 95 ) {
                            valid = false;
                        }
                    }

                }
                //numbers
                else if (currChar >= 48 && currChar <= 57) {
                    valid = true;
                }
                //uppercase letters
                else if (currChar >= 65 && currChar <= 90) {
                    valid = true;
                }
                //lowercase letters
                else if (currChar >= 97 && currChar <= 122) {
                    valid = true;
                }

                if (!valid) {
                    return false;
                }
            }
        }

        return true;

    }
}
