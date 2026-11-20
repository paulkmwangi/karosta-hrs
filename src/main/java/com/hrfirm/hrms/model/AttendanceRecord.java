package com.hrfirm.hrms.model;

public class AttendanceRecord {
    private String employeeName;
    private String department;
    private String date;
    private String clockIn;
    private String clockOut;
    private String status;

    public AttendanceRecord(String employeeName, String department, String date,
                            String clockIn, String clockOut, String status) {
        this.employeeName = employeeName;
        this.department = department;
        this.date = date;
        this.clockIn = clockIn;
        this.clockOut = clockOut;
        this.status = status;
    }

    public String getEmployeeName() { return employeeName; }
    public String getDepartment() { return department; }
    public String getDate() { return date; }
    public String getClockIn() { return clockIn; }
    public String getClockOut() { return clockOut; }
    public String getStatus() { return status; }
}
