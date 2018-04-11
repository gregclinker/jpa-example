/*
 * Copyright 2002-2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package hello;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DepartmentRepositoryTest {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private DataSource dataSource;

    @Test
    public void test1() throws Exception {

        showTables();

        Department department = new Department("finance", "finance department");
        department.setEmployes(new ArrayList<>());
        Employee employee = new Employee("Noby", "Stiles");
        employee.setDepartment(department);
        department.getEmployes().add(employee);
        employee = new Employee("Frank", "Spencer");
        employee.setDepartment(department);
        department.getEmployes().add(employee);

        departmentRepository.save(department);

        assertEquals(1, departmentRepository.count());
        assertNotNull(departmentRepository.findOne(1l));
        assertNotNull(departmentRepository.findOne(1l).getEmployes());
        assertEquals(2, departmentRepository.findOne(1l).getEmployes().size());

        assertEquals(2, employeeRepository.count());
        assertNotNull(employeeRepository.findOne(1l));
        assertNotNull(employeeRepository.findOne(2l));
        assertNotNull(employeeRepository.findOne(1l).getDepartment());
        assertNotNull(employeeRepository.findOne(2l).getDepartment());
    }

    private void showTables() throws Exception {

        DatabaseMetaData metaData = dataSource.getConnection().getMetaData();
        ResultSet tables = metaData.getTables(null, "PUBLIC", "%", null);
        while (tables.next()) {
            String tableName = tables.getString(3);
            System.out.println(tableName);
            ResultSet columns = metaData.getColumns(null, "PUBLIC", tableName, null);
            while (columns.next()) {
                System.out.println("\t" + columns.getString(4));
            }
        }
    }
}