package com.example.medilab.controller;
import java.io.IOException;



import java.util.HashMap;
import java.util.List;
import java.util.Map;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.medilab.service.ReportService;
import com.lowagie.text.DocumentException;



@RestController
@RequestMapping("/report-pdf")
public class ReportController {
	
	
	@Autowired
	private ReportService reportService;

	@GetMapping("/generate")
	public ResponseEntity<byte[]> generatePdf(@RequestParam("templates") String templatePath) {
	    try {
	        // You can add more context variables as needed
	        Map<String, Object> contextVariables = new HashMap<>();
	        byte[] pdfBytes = reportService.generatePdf(templatePath, contextVariables);

	        HttpHeaders headers = new HttpHeaders();
	        headers.setContentType(MediaType.APPLICATION_PDF); /*<< To show pdf(mediatype(img,excel,vedio etc..) in postman after removing this comment it display a byte code in postman>> */
	        headers.setContentDispositionFormData("attachment", "generated.pdf");
	        
	        return new ResponseEntity<>(pdfBytes, headers, HttpStatus.OK);
	    } catch (DocumentException | IOException e) {
	        // Handle exceptions appropriately (e.g., log them)
	        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}
	
	
	}


