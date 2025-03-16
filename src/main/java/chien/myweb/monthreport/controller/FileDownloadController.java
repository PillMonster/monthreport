package chien.myweb.monthreport.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;


@RestController
@RequestMapping("/bqc")
public class FileDownloadController {
	
	SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
	//@RequestParam("radio") String request
	@PostMapping(value = "/downloadReport", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity<StreamingResponseBody> downloadFileForReport(@RequestParam(name = "type") String request) throws IOException {
		
		//System.out.println(request);
    	String product = request;
    	String path = "";
    	
    	// 設定檔案的儲存路徑
    	if (product.equals("MS")) {
    		path = "D:/PythonTest/動態表/monthReport(MS)/動態表-MS.xlsx";
    	}
    	else if (product.equals("NF")) {
    		path = "D:/PythonTest/動態表/monthReport(NF)/動態表-NF.xlsx";
    	}
    	else if (product.equals("BH")) {
    		path = "D:/PythonTest/動態表/monthReport(BH)/動態表-BH.xlsx";
    	} 
    	//path = "D:/PythonTest/動態表/monthReport(NF)/動態表(單機版)-NF.xlsx";
    	
    	// InputStream: 取得指定路徑中的文件，來讀取文件的字元流
    	InputStream inputStream = new FileInputStream(path);
    	
        // StreamingResponseBody: Spring提供一個用於Spring MVC中處理文件下載的介面，
    	// 透過介面的Lambda表達式，使用FileCopyUtils.copy將輸入流複製到輸出流，並指派給body變數
    	StreamingResponseBody body = outputStream -> FileCopyUtils.copy(inputStream, outputStream);
    		
    	Date date = new Date();
    	String nowTime = sdFormat.format(date);
    	
    	System.out.println("動態表下載成功" + "("+ product +")" + " " + nowTime);
        // Content-Disposition: 告訴瀏覽器要下載的文件
        // attachment: 文件作為附件下載
        // filename: 下載的文件名稱
        return ResponseEntity.ok()
                .header("Content-Disposition", "attachment; filename=abc.xlsx")
                .body(body);
    }
	
	
	@PostMapping(value = "/downloadData", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity<StreamingResponseBody> downloadFileForData(@RequestParam(name = "type") String request) throws IOException {
		
		//System.out.println(request);
    	String product = request;
    	String path = "";
    	
    	// 設定檔案的儲存路徑
    	if (product.equals("MS")) {
    		path = "D:/PythonTest/動態表/monthReport(MS)/月報表資料.xlsx";
    	}
    	else if (product.equals("NF")) {
    		path = "D:/PythonTest/動態表/monthReport(NF)/月報表資料.xlsx";
    	}
    	else if (product.equals("BH")) {
    		path = "D:/PythonTest/動態表/monthReport(BH)/月報表資料.xlsx";
    	} 
    	//path = "D:/PythonTest/動態表/monthReport(NF)/動態表(單機版)-NF.xlsx";
    	
    	// InputStream: 取得指定路徑中的文件，來讀取文件的字元流
    	InputStream inputStream = new FileInputStream(path);
    	
        // StreamingResponseBody: Spring提供一個用於Spring MVC中處理文件下載的介面，
    	// 透過介面的Lambda表達式，使用FileCopyUtils.copy將輸入流複製到輸出流，並指派給body變數
    	StreamingResponseBody body = outputStream -> FileCopyUtils.copy(inputStream, outputStream);
    	
    	Date date = new Date();
    	String nowTime = sdFormat.format(date);
    	
    	System.out.println("月報表資料下載成功" + "("+ product +")" + " " + nowTime);
        // Content-Disposition: 告訴瀏覽器要下載的文件
        // attachment: 文件作為附件下載
        // filename: 下載的文件名稱
        return ResponseEntity.ok()
                .header("Content-Disposition", "attachment; filename=abc.xlsx")
                .body(body);
    }

}
