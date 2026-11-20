package com.hrfirm.hrms.service;

import com.hrfirm.hrms.model.AttendanceRecord;
import com.hrfirm.hrms.model.Employee;
import com.hrfirm.hrms.model.LeaveRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class HrDataService {
    private final List<Employee> employees = new ArrayList<>();
    private final List<LeaveRequest> leaveRequests = new ArrayList<>();
    private final List<AttendanceRecord> attendance = new ArrayList<>();
    private final AtomicLong employeeIds = new AtomicLong(8);

    public HrDataService() {
        employees.add(new Employee(1L, "Amina", "Wanjiku", "amina.wanjiku@northstarhr.test", "+254 712 345 678", "Human Resources", "HR Manager", "Full-time", "Active", "2021-03-15", 145000));
        employees.add(new Employee(2L, "Brian", "Otieno", "brian.otieno@northstarhr.test", "+254 723 456 789", "Finance", "Accountant", "Full-time", "Active", "2022-01-10", 98000));
        employees.add(new Employee(3L, "Grace", "Njeri", "grace.njeri@northstarhr.test", "+254 734 567 890", "Technology", "Software Engineer", "Full-time", "Active", "2023-06-05", 125000));
        employees.add(new Employee(4L, "David", "Mwangi", "david.mwangi@northstarhr.test", "+254 745 678 901", "Operations", "Operations Lead", "Full-time", "Active", "2020-11-20", 132000));
        employees.add(new Employee(5L, "Faith", "Achieng", "faith.achieng@northstarhr.test", "+254 756 789 012", "Marketing", "Marketing Executive", "Contract", "Active", "2024-02-12", 85000));
        employees.add(new Employee(6L, "Kevin", "Kiptoo", "kevin.kiptoo@northstarhr.test", "+254 767 890 123", "Technology", "IT Support", "Full-time", "On Leave", "2022-09-01", 76000));
        employees.add(new Employee(7L, "Lydia", "Muthoni", "lydia.muthoni@northstarhr.test", "+254 778 901 234", "Human Resources", "Recruitment Officer", "Part-time", "Active", "2025-01-06", 62000));

        leaveRequests.add(new LeaveRequest(1L, "Kevin Kiptoo", "Annual Leave", "2026-11-24", "2026-11-28", "Family commitments", "Pending"));
        leaveRequests.add(new LeaveRequest(2L, "Grace Njeri", "Sick Leave", "2026-11-18", "2026-11-19", "Medical appointment and recovery", "Approved"));
        leaveRequests.add(new LeaveRequest(3L, "Brian Otieno", "Annual Leave", "2026-12-02", "2026-12-05", "Personal travel", "Pending"));
        leaveRequests.add(new LeaveRequest(4L, "Faith Achieng", "Compassionate", "2026-11-10", "2026-11-11", "Family emergency", "Approved"));

        attendance.add(new AttendanceRecord("Amina Wanjiku", "Human Resources", "2026-11-21", "08:02 AM", "05:04 PM", "Present"));
        attendance.add(new AttendanceRecord("Brian Otieno", "Finance", "2026-11-21", "08:15 AM", "05:00 PM", "Present"));
        attendance.add(new AttendanceRecord("Grace Njeri", "Technology", "2026-11-21", "08:01 AM", "—", "Working"));
        attendance.add(new AttendanceRecord("David Mwangi", "Operations", "2026-11-21", "—", "—", "Absent"));
        attendance.add(new AttendanceRecord("Faith Achieng", "Marketing", "2026-11-21", "08:27 AM", "05:12 PM", "Present"));
    }

    public List<Employee> getEmployees() { return employees; }
    public List<LeaveRequest> getLeaveRequests() { return leaveRequests; }
    public List<AttendanceRecord> getAttendance() { return attendance; }

    public Optional<Employee> findEmployee(Long id) {
        return employees.stream().filter(e -> e.getId().equals(id)).findFirst();
    }

    public void saveEmployee(Employee employee) {
        if (employee.getId() == null) {
            employee.setId(employeeIds.incrementAndGet());
            employees.add(employee);
        } else {
            findEmployee(employee.getId()).ifPresent(existing -> {
                existing.setFirstName(employee.getFirstName());
                existing.setLastName(employee.getLastName());
                existing.setEmail(employee.getEmail());
                existing.setPhone(employee.getPhone());
                existing.setDepartment(employee.getDepartment());
                existing.setPosition(employee.getPosition());
                existing.setEmploymentType(employee.getEmploymentType());
                existing.setStatus(employee.getStatus());
                existing.setHireDate(employee.getHireDate());
                existing.setSalary(employee.getSalary());
            });
        }
    }

    public void deleteEmployee(Long id) {
        employees.removeIf(employee -> employee.getId().equals(id));
    }

    public void updateLeaveStatus(Long id, String status) {
        leaveRequests.stream().filter(leave -> leave.getId().equals(id))
                .findFirst().ifPresent(leave -> leave.setStatus(status));
    }

    public long countActiveEmployees() {
        return employees.stream().filter(e -> "Active".equals(e.getStatus())).count();
    }

    public long countPendingLeaves() {
        return leaveRequests.stream().filter(l -> "Pending".equals(l.getStatus())).count();
    }
}
