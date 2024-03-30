package org.example.testjpa.controller;

import org.example.testjpa.model.Emp;
import org.example.testjpa.repository.EmpRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class SimpleController {

    @Autowired
    private EmpRepository empRepository;

    @RequestMapping(value="/", method= RequestMethod.GET)
    public String hello(@Param("name") String name) {
        return "Hello " + name;
    }

    @RequestMapping(value="/employees", method= RequestMethod.GET)
    public List<Emp> getEmployees() {
        return empRepository.findAll();
    }
    @GetMapping(value="/employees/{id}")
    public Emp getEmployeeById(@PathVariable Long id) {
        Optional<Emp> employeeOptional = empRepository.findById(id);
        return employeeOptional.orElse(null);
    }
    @PostMapping(value="/employees")
    public Emp addEmployee(@RequestBody Emp emp) {
        return empRepository.save(emp);
    }
    @PutMapping(value="/employees/{id}")
    public Emp updateEmployee(@PathVariable Long id, @RequestBody Emp emp) {
        emp.setEmpno(id);
        return empRepository.save(emp);
    }
    @DeleteMapping(value="/employees/{id}")
    public void deleteEmployee(@PathVariable Long id) {
        empRepository.deleteById(id);
    }
}
