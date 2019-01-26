package pl.robert.project.app.transaction.domain;

interface TransactionValidationStrings {

    int MIN_TRANSACTION_TITLE_LENGTH = 1;
    int MAX_TRANSACTION_TITLE_LENGTH = 40;
    int MIN_TRANSACTION_DESCRIPTION_LENGTH = 1;
    int MAX_TRANSACTION_DESCRIPTION_LENGTH = 255;
    int BANK_ACCOUNT_NUMBER_LENGTH = 29;

    ////////////////////////////////////////////////////////////////////////////////////////////

    String C_TITLE_NULL = "title.null";
    String C_DESCRIPTION_NULL = "description.null";
    String C_AMOUNT_NULL = "amount.null";
    String C_RECEIVER_BANK_ACCOUNT_NUMBER_NULL = "receiver_bank_account_number.null";

    String C_TITLE_LENGTH = "title.length";
    String C_DESCRIPTION_LENGTH = "description.length";
    String C_RECEIVER_BANK_ACCOUNT_NUMBER_LENGTH = "receiver_bank_account_number.length";

    String C_TRANSACTIONS_NOT_EXISTS = "transactions.not_exists";
    String C_TRANSACTION_NOT_EXISTS = "transaction.id.not_exists";
    String C_RECEIVER_BANK_ACCOUNT_NUMBER_NOT_EXISTS = "receiver_bank_account_number.not_exists";

    String C_RECEIVER_BANK_ACCOUNT_NUMBER_WRONG = "receiver_bank_account_number.wrong";
    String C_BANK_ACCOUNT_BALANCE_NOT_ENOUGHT_MONEY = "bank_account.balance.not_enought_money";

    String C_USER_HAS_GOT_NO_TRANSACTIONS = "no_transactions";
    String C_USER_HAS_GOT_NO_SENT_TRANSACTIONS = "no_sent_transactions";
    String C_USER_HAS_GOT_NO_RECEIVED_TRANSACTIONS = "no_received_transactions";

    ////////////////////////////////////////////////////////////////////////////////////////////

    String M_TITLE_NULL = "Enter your title";
    String M_DESCRIPTION_NULL = "Enter your description";
    String M_AMOUNT_NULL = "Enter your amount";
    String M_RECEIVER_BANK_ACCOUNT_NUMBER_NULL = "Enter receiver bank account number";

    String M_TITLE_LENGTH = "Title should have from 1 to 40 characters";
    String M_DESCRIPTION_LENGTH = "Title should have from 1 to 255 characters";
    String M_RECEIVER_BANK_ACCOUNT_NUMBER_LENGTH = "Receiver bank account number should have 22 characters";

    String M_TRANSACTIONS_NOT_EXISTS = "Transactions not exists";
    String M_TRANSACTION_NOT_EXISTS = "Transaction id not exists";
    String M_RECEIVER_BANK_ACCOUNT_NUMBER_NOT_EXISTS = "Receiver bank account number not exists";

    String M_RECEIVER_BANK_ACCOUNT_NUMBER_WRONG = "You can't send your money from your own account";
    String M_BANK_ACCOUNT_BALANCE_NOT_ENOUGHT_MONEY = "Not enought balance on your account";

    String M_USER_HAS_GOT_NO_TRANSACTIONS = "No transactions";
    String M_USER_HAS_GOT_NO_SENT_TRANSACTIONS = "No sent transactions";
    String M_USER_HAS_GOT_NO_RECEIVED_TRANSACTIONS = "No received transactions";
}
