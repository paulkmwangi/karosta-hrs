package com.hrfirm.hrms.controller;

import com.hrfirm.hrms.model.Employee;
import com.hrfirm.hrms.service.HrDataService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class HrController {
    private final HrDataService data;

    public HrController(HrDataService data) {
        this.data = data;
    }

    @GetMapping("/")
    public String dashboard(Model model) {
        model.addAttribute("activePage", "dashboard");
        model.addAttribute("employeeCount", data.getEmployees().size());
        model.addAttribute("activeCount", data.countActiveEmployees());
        model.addAttribute("pendingLeaves", data.countPendingLeaves());
        model.addAttribute("departmentCount", data.getEmployees().stream().map(Employee::getDepartment).distinct().count());
        model.addAttribute("recentEmployees", data.getEmployees().stream().limit(5).toList());
        model.addAttribute("leaveRequests", data.getLeaveRequests().stream().limit(4).toList());
        model.addAttribute("attendance", data.getAttendance());
        return "dashboard";
    }

    @GetMapping("/employees")
    public String employees(@RequestParam(required = false) String q, Model model) {
        var list = data.getEmployees();
        if (q != null && !q.isBlank()) {
            String query = q.toLowerCase();
            list = list.stream().filter(e -> e.getFullName().toLowerCase().contains(query)
                    || e.getDepartment().toLowerCase().contains(query)
                    || e.getPosition().toLowerCase().contains(query)).toList();
        }
        model.addAttribute("activePage", "employees");
        model.addAttribute("employees", list);
        model.addAttribute("q", q == null ? "" : q);
        return "employees";
    }

    @GetMapping("/employees/new")
    public String newEmployee(Model model) {
        model.addAttribute("activePage", "employees");
        model.addAttribute("employee", new Employee());
        model.addAttribute("formTitle", "Add employee");
        return "employee-form";
    }

    @GetMapping("/employees/{id}/edit")
    public String editEmployee(@PathVariable Long id, Model model, RedirectAttributes redirect) {
        var employee = data.findEmployee(id);
        if (employee.isEmpty()) {
            redirect.addFlashAttribute("errorMessage", "Employee not found.");
            return "redirect:/employees";
        }
        model.addAttribute("activePage", "employees");
        model.addAttribute("employee", employee.get());
        model.addAttribute("formTitle", "Edit employee");
        return "employee-form";
    }

    @PostMapping("/employees/save")
    public String saveEmployee(@ModelAttribute Employee employee, RedirectAttributes redirect) {
        data.saveEmployee(employee);
        redirect.addFlashAttribute("successMessage", "Employee details saved successfully.");
        return "redirect:/employees";
    }

    @PostMapping("/employees/{id}/delete")
    public String deleteEmployee(@PathVariable Long id, RedirectAttributes redirect) {
        data.deleteEmployee(id);
        redirect.addFlashAttribute("successMessage", "Employee record deleted.");
        return "redirect:/employees";
    }

    @GetMapping("/attendance")
    public String attendance(Model model) {
        model.addAttribute("activePage", "attendance");
        model.addAttribute("attendance", data.getAttendance());
        return "attendance";
    }

    @GetMapping("/leave")
    public String leave(Model model) {
        model.addAttribute("activePage", "leave");
        model.addAttribute("leaveRequests", data.getLeaveRequests());
        return "leave";
    }

    @PostMapping("/leave/{id}/status")
    public String updateLeave(@PathVariable Long id, @RequestParam String status, RedirectAttributes redirect) {
        if (!status.equals("Approved") && !status.equals("Rejected")) {
            redirect.addFlashAttribute("errorMessage", "Invalid leave status.");
        } else {
            data.updateLeaveStatus(id, status);
            redirect.addFlashAttribute("successMessage", "Leave request updated.");
        }
        return "redirect:/leave";
    }

    @GetMapping("/payroll")
    public String payroll(Model model) {
        model.addAttribute("activePage", "payroll");
        model.addAttribute("employees", data.getEmployees());
        model.addAttribute("totalPayroll", data.getEmployees().stream().mapToDouble(Employee::getSalary).sum());
        return "payroll";
    }

    @GetMapping("/recruitment")
    public String recruitment(Model model) {
        model.addAttribute("activePage", "recruitment");
        model.addAttribute("openRoles", new String[][] {
                {"Senior Java Developer", "Technology", "Full-time", "12 applicants", "Open"},
                {"HR Assistant", "Human Resources", "Full-time", "8 applicants", "Open"},
                {"Digital Marketing Specialist", "Marketing", "Contract", "16 applicants", "Reviewing"},
                {"Finance Intern", "Finance", "Internship", "21 applicants", "Open"}
        });
        return "recruitment";
    }
}
