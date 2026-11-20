package com.hrfirm.hrms.model;

public class LeaveRequest {
    private Long id;
    private String employeeName;
    private String leaveType;
    private String startDate;
    private String endDate;
    private String reason;
    private String status;

    public LeaveRequest(Long id, String employeeName, String leaveType, String startDate,
                        String endDate, String reason, String status) {
        this.id = id;
        this.employeeName = employeeName;
        this.leaveType = leaveType;
        this.startDate = startDate;
        this.endDate = endDate;
        this.reason = reason;
        this.status = status;
    }

    public Long getId() { return id; }
    public String getEmployeeName() { return employeeName; }
    public String getLeaveType() { return leaveType; }
    public String getStartDate() { return startDate; }
    public String getEndDate() { return endDate; }
    public String getReason() { return reason; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
