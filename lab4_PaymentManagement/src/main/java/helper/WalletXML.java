/*
 * Wallet XML Representation for REST API Responses
 */
package helper;

import java.util.Date;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * WalletXML Class
 * Supports XML serialization for all wallet operations.
 */
@XmlRootElement(name = "wallet")
@XmlAccessorType(XmlAccessType.FIELD)
public class WalletXML {

    @XmlElement(name = "userId")
    private int userId;

    @XmlElement(name = "balance")
    private float balance;

    @XmlElement(name = "createdAt")
    private Date createdAt;

    @XmlElement(name = "updatedAt")
    private Date updatedAt;

    /**
     * Default constructor
     */
    public WalletXML() {
    }

    /**
     * Constructor with all fields
     * @param userId User ID
     * @param balance Wallet balance
     * @param createdAt Creation timestamp
     * @param updatedAt Update timestamp
     */
    public WalletXML(int userId, float balance, Date createdAt, Date updatedAt) {
        this.userId = userId;
        this.balance = balance;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    // Getters and Setters

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
}
