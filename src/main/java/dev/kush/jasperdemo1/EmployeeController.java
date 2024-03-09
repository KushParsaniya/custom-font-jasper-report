package dev.kush.jasperdemo1;

import lombok.*;
import net.sf.jasperreports.engine.*;
import org.springframework.core.io.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.net.*;
import java.nio.charset.*;
import java.nio.file.*;

@RestController
@RequiredArgsConstructor
public class EmployeeController {

    private final ReportService reportService;

    @GetMapping("/generate")
    public String generateReport() throws JRException, FileNotFoundException {
        return reportService.generateReport();
    }

    @GetMapping(path = "/download")
    public ResponseEntity<Resource> download(@RequestParam String path) throws IOException {
        String decodedPath = URLDecoder.decode(path, StandardCharsets.UTF_8);
        Path filePath = Paths.get(decodedPath);
        Resource resource = null;
        try {
            resource = new UrlResource(filePath.toUri());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }
}
