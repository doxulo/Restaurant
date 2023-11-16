package restaurant.service;

import restaurant.model.Transaction;
import restaurant.dao.TransactionDAO;


public class TransactionService {
    private final TransactionDAO transactionDAO;

    public TransactionService() {
        this.transactionDAO = new TransactionDAO();
        
    }

    public void processTransaction(Transaction transaction) {
        
        transactionDAO.insertTransaction(transaction);
    }

    
}
