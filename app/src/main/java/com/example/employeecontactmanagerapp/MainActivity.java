package com.example.employeecontactmanagerapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private EditText etEmpName, etEmpId, etEmpPhone;
    private Spinner spDepartment;

    private ArrayList<Employee> employees;
    private EmployeeAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Employee Manager - REGNO"); // Optional (title may not show if NoActionBar)
        setContentView(R.layout.activity_main);

        etEmpName = findViewById(R.id.etEmpName);
        etEmpId = findViewById(R.id.etEmpId);
        etEmpPhone = findViewById(R.id.etEmpPhone);
        spDepartment = findViewById(R.id.spDepartment);
        Button btnAddEmployee = findViewById(R.id.btnAddEmployee);

        // Spinner (departments)
        ArrayAdapter<CharSequence> deptAdapter = ArrayAdapter.createFromResource(
                this, R.array.departments, android.R.layout.simple_spinner_item
        );
        deptAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spDepartment.setAdapter(deptAdapter);

        // RecyclerView
        RecyclerView rv = findViewById(R.id.rvEmployees);
        rv.setLayoutManager(new LinearLayoutManager(this));

        employees = new ArrayList<>();
        adapter = new EmployeeAdapter(employees, this::openDetails);
        rv.setAdapter(adapter);

        btnAddEmployee.setOnClickListener(v -> addEmployee());
    }

    private void addEmployee() {
        String name = etEmpName.getText().toString().trim();
        String empId = etEmpId.getText().toString().trim();
        String phone = etEmpPhone.getText().toString().trim();
        String dept = spDepartment.getSelectedItem().toString();

        if (name.isEmpty()) { etEmpName.setError("Enter name"); etEmpName.requestFocus(); return; }
        if (empId.isEmpty()) { etEmpId.setError("Enter employee ID"); etEmpId.requestFocus(); return; }
        if (phone.isEmpty()) { etEmpPhone.setError("Enter phone"); etEmpPhone.requestFocus(); return; }

        employees.add(new Employee(name, empId, phone, dept));
        adapter.notifyItemInserted(employees.size() - 1);

        Toast.makeText(this, "Employee Added", Toast.LENGTH_SHORT).show();

        etEmpName.setText("");
        etEmpId.setText("");
        etEmpPhone.setText("");
        etEmpName.requestFocus();
    }

    private void openDetails(Employee e) {
        Intent i = new Intent(MainActivity.this, DetailActivity.class);
        i.putExtra("name", e.name);
        i.putExtra("empId", e.empId);
        i.putExtra("phone", e.phone);
        i.putExtra("dept", e.dept);
        startActivity(i);
    }
}