package helper;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "transaction")
@XmlAccessorType(XmlAccessType.FIELD)
public class Transaction {
    private String transactionID;
    private int walletId;
    private String type;
    private double amount;
    private String transactionDate;
    private String status;

    public Transaction() {}

    public Transaction(String transactionID, int walletId, String type, double amount, String transactionDate, String status) {
        this.transactionID = transactionID;
        this.walletId = walletId;
        this.type = type;
        this.amount = amount;
        this.transactionDate = transactionDate;
        this.status = status;
    }

    // Getters and setters
}