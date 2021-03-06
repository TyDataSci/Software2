package model;

import utilities.Local;

import java.time.temporal.TemporalAccessor;

public class Appointment {
    private int appointmentId;
    private String title;
    String description;
    String location;
    String type;
    String start;
    String end;
    String createDate;
    String createdBy;
    String lastUpdate;
    String lastUpdatedBy;
    int customerId;
    int userId;
    int contactId;
    String bookedIndicator;
    String customerString;
    String contactName;

    public Appointment(int appointmentId, String title, String description, String location, String type, String start, String end,
                       String createDate, String createdBy, String lastUpdate, String lastUpdatedBy, int customerId, int userId, int contactId,
                       String customerString,String contactName) {
        this.appointmentId = appointmentId;
        this.title = title;
        this.description = description;
        this.location = location;
        this.type = type;
        this.start = start;
        this.end = Local.getZonedDateTime(end);
        this.createDate = createDate;
        this.createdBy = createdBy;
        this.lastUpdate = lastUpdate;
        this.lastUpdatedBy = lastUpdatedBy;
        this.customerId = customerId;
        this.userId = userId;
        this.contactId = contactId;
        this.bookedIndicator = "Booked";
        this.customerString = customerString;
        this.contactName = contactName;
    }

    public int getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(int appointmentId) {
        this.appointmentId = appointmentId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = Local.getZonedDateTimeInUTC(start);
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = Local.getZonedDateTimeInUTC(end);
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(String lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getContactId() {
        return contactId;
    }

    public void setContactId(int contactId) {
        this.contactId = contactId;
    }

    public String getCustomerString() {
        return customerString;
    }

    public String getContactName() {
        return contactName;
    }

    public String getBookedIndicator() {
        return bookedIndicator;
    }
}
