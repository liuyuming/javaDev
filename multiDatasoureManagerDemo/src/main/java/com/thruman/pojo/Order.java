package com.thruman.pojo;

import java.util.Date;
import javax.persistence.*;

@Table(name = "order")
public class Order {
    @Id
    private Long id;

    @Column(name = "create_time")
    private Date createTime;

    private String number;

    private Byte status;

    @Column(name = "product_id")
    private String productId;

    @Column(name = "total_amount")
    private Long totalAmount;

    private Integer count;

    @Column(name = "user_id")
    private String userId;

    /**
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return create_time
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * @return number
     */
    public String getNumber() {
        return number;
    }

    /**
     * @param number
     */
    public void setNumber(String number) {
        this.number = number == null ? null : number.trim();
    }

    /**
     * @return status
     */
    public Byte getStatus() {
        return status;
    }

    /**
     * @param status
     */
    public void setStatus(Byte status) {
        this.status = status;
    }

    /**
     * @return product_id
     */
    public String getProductId() {
        return productId;
    }

    /**
     * @param productId
     */
    public void setProductId(String productId) {
        this.productId = productId == null ? null : productId.trim();
    }

    /**
     * @return total_amount
     */
    public Long getTotalAmount() {
        return totalAmount;
    }

    /**
     * @param totalAmount
     */
    public void setTotalAmount(Long totalAmount) {
        this.totalAmount = totalAmount;
    }

    /**
     * @return count
     */
    public Integer getCount() {
        return count;
    }

    /**
     * @param count
     */
    public void setCount(Integer count) {
        this.count = count;
    }

    /**
     * @return user_id
     */
    public String getUserId() {
        return userId;
    }

    /**
     * @param userId
     */
    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", createTime=").append(createTime);
        sb.append(", number=").append(number);
        sb.append(", status=").append(status);
        sb.append(", productId=").append(productId);
        sb.append(", totalAmount=").append(totalAmount);
        sb.append(", count=").append(count);
        sb.append(", userId=").append(userId);
        sb.append("]");
        return sb.toString();
    }
}