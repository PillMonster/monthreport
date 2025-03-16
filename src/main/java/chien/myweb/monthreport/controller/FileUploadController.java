package chien.myweb.monthreport.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.JsonNode;

@Controller
@RequestMapping("/bqc")
public class FileUploadController {
	
	@GetMapping("/monthreport")
    public String index() {
        return "upload";
    }
	
	@PostMapping("/upload")
    public ResponseEntity<?> handleFileUpload(@RequestParam("file") MultipartFile file, @RequestParam("radio") String request) {
		
		System.out.println(request);  
		String product = request;
		String filePath = "";
		
		List<String> expectedFileNameMS = Arrays.asList("BG1FQC.xlsx", "BG1IQC.xlsx", "BMSFQC.xlsx", "BMSIQC.xlsx", "BP1FQC.xlsx", "BP1IQC.xlsx");
		List<String> expectedFileNameNF = Arrays.asList("BG1FQC.xlsx", "BG1IQC.xlsx", "BNFFQC.xlsx", "BNFIQC.xlsx", "BP1FQC.xlsx", "BP1IQC.xlsx",  
														"BTMFQC.xlsx", "BTMIQC.xlsx");
		List<String> expectedFileNameBH = Arrays.asList("BG1FQC.xlsx", "BG1IQC.xlsx", "BBHFQC.xlsx", "BBHIQC.xlsx", "BP1FQC.xlsx", "BP1IQC.xlsx",
														"BMSIQC.xlsx");
		
		Map<String, List<String>> productExpectedFilesMap = new HashMap<>();
	    productExpectedFilesMap.put("MS", expectedFileNameMS);
	    productExpectedFilesMap.put("NF", expectedFileNameNF);
	    productExpectedFilesMap.put("BH", expectedFileNameBH);
		
		if (file.isEmpty()) {
			String message = "請選擇一個檔案來上傳";
            return ResponseEntity.ok().body(message);
        }
		
		try {
		    // 設定上傳檔案的儲存路徑
			List<String> expectedFiles = productExpectedFilesMap.get(product);
			
		    if (expectedFiles == null) {
		        System.out.println("找不到對應的產品。");
		        return ResponseEntity.ok().body("找不到對應的產品。");
		    }
	
		    boolean found = false;
		    for (String f : expectedFiles) {
		        if (file.getOriginalFilename().equals(f)) {
		            found = true;
		            break;
		        }
		    }
	
		    if (!found) {
		        System.out.println(file.getOriginalFilename() + "，檔案名稱或副檔名不正確，請重新再確認。");
		        String message = file.getOriginalFilename() + "，檔案名稱或副檔名不正確，請重新再確認。";
		        return ResponseEntity.ok().body(message);
		    }
	
		    filePath = "D:/PythonTest/動態表/monthReport(" + product + ")/" + file.getOriginalFilename();

            File dest = new File(filePath);
            
            // 如果目錄不存在，則創建目錄
            if (!dest.getParentFile().exists()) {
                dest.getParentFile().mkdirs();
            }

            // 將檔案寫入目的地
            file.transferTo(dest);
            String message = "檔案上傳成功: " + file.getOriginalFilename();
            return ResponseEntity.ok().body(message);
            
        } catch (IOException e) {
            e.printStackTrace();
            String message = "檔案上傳失敗: " + file.getOriginalFilename();
            return ResponseEntity.ok().body(message);
        }
    }
}
