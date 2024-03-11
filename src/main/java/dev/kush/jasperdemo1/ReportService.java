package dev.kush.jasperdemo1;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.*;
import org.springframework.stereotype.*;
import org.springframework.util.*;

import java.io.*;
import java.util.*;

@Service
public class ReportService {

    private final List<Employee> employees = List.of(
            new Employee(1, "કુશ", "કુશ", "kush@gmail.com"),
            new Employee(2, "કુશ", "કુશ", "kush@gmail.com"),

            new Employee(3, "એબીસી", "કુશ", "abc@gmail.com")
    );

//    private final List<Employee> employees = List.of(
//            new Employee(1, "kush", "Parsaniya", "kush@gmail.com"),
//            new Employee(2, "abc", "def", "abc@gmail.com")
//    );

    public String generateReport() throws FileNotFoundException, JRException {
        String path = "C:\\Users\\Admin\\Desktop\\studentResult\\employees.pdf";

        File file = ResourceUtils.getFile("classpath:report.jrxml");

        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());

        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(employees);

        Map<String,Object> parameters = new HashMap<>();
        parameters.put("createdBy","Kush Parsaniya");

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);

        JasperExportManager.exportReportToPdfFile(jasperPrint, path);

        return "Report Generated in path : " + path;
    }
}
