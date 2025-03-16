package chien.myweb.monthreport.controller;

import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import chien.myweb.monthreport.service.RunPythonService;

@RestController
@RequestMapping("/monthreport")
public class RunPythonController {
	
	@Autowired
	RunPythonService runPythonService;
	
	@PostMapping("/runPythonFile")
	public ResponseEntity<?> runPythonFile(@RequestParam("radio") String request){
		
		String product = request;
		
		String response = runPythonService.reportFilter(product);
		
		return ResponseEntity.ok().body(response);
		//return ResponseEntity.ok().body(product);
	}

}
