package com.example.restpos.models;

public class Expense {
    private int id;
    private String title;
    private String category;
    private double amount;
    private double tax;
    private String paymentMethod;
    private String reference;
    private String attachmentPath;
    private String dueDate;
    private String paidDate;
    private boolean isRecurring;
    private String recurrenceInterval;
    private int createdBy;

    public Expense(int id, String title, String category, double amount, double tax, String paymentMethod, String reference, String attachmentPath, String dueDate, String paidDate, boolean isRecurring, String recurrenceInterval, int createdBy) {
        this.id = id;
        this.title = title;
        this.category = category;
        this.amount = amount;
        this.tax = tax;
        this.paymentMethod = paymentMethod;
        this.reference = reference;
        this.attachmentPath = attachmentPath;
        this.dueDate = dueDate;
        this.paidDate = paidDate;
        this.isRecurring = isRecurring;
        this.recurrenceInterval = recurrenceInterval;
        this.createdBy = createdBy;
    }

    // Getters and setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }
    public double getTax() { return tax; }
    public void setTax(double tax) { this.tax = tax; }
    public String getPaymentMethod() { return paymentMethod; }
    public void setPaymentMethod(String paymentMethod) { this.paymentMethod = paymentMethod; }
    public String getReference() { return reference; }
    public void setReference(String reference) { this.reference = reference; }
    public String getAttachmentPath() { return attachmentPath; }
    public void setAttachmentPath(String attachmentPath) { this.attachmentPath = attachmentPath; }
    public String getDueDate() { return dueDate; }
    public void setDueDate(String dueDate) { this.dueDate = dueDate; }
    public String getPaidDate() { return paidDate; }
    public void setPaidDate(String paidDate) { this.paidDate = paidDate; }
    public boolean isRecurring() { return isRecurring; }
    public void setRecurring(boolean recurring) { isRecurring = recurring; }
    public String getRecurrenceInterval() { return recurrenceInterval; }
    public void setRecurrenceInterval(String recurrenceInterval) { this.recurrenceInterval = recurrenceInterval; }
    public int getCreatedBy() { return createdBy; }
    public void setCreatedBy(int createdBy) { this.createdBy = createdBy; }
}