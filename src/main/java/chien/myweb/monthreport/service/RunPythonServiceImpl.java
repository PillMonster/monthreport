package chien.myweb.monthreport.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Service;

@Service
public class RunPythonServiceImpl implements RunPythonService{
	
	@Override
	public String reportFilter(String product) {
		
		String response = "";
		String pythonFilePath = "";
		SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
		
		try {
            // 指定Python文件路徑
   
            if (product.equals("MS")) {
            	pythonFilePath = "D:/PythonTest/動態表/monthReport(MS)/reportFilter.py";
        	}
    		else if (product.equals("NF")) {
    			pythonFilePath = "D:/PythonTest/動態表/monthReport(NF)/reportFilter.py";
        	}
        	else if (product.equals("BH")) {
        		pythonFilePath = "D:/PythonTest/動態表/monthReport(BH)/reportFilter.py";
        	}

            // 建構排程
            ProcessBuilder processBuilder = new ProcessBuilder("python", pythonFilePath);
            processBuilder.redirectErrorStream(true); // 將錯誤流合併到輸出流中
            
            // 將輸入流從父排程傳給子排程
            processBuilder.redirectInput(ProcessBuilder.Redirect.INHERIT); 
            
            // 設置字符編碼
            processBuilder.environment().put("PYTHONIOENCODING", "utf-8"); 
            // 啟動排成
            Process process = processBuilder.start();

            // 獲取排程的輸出流，並設定字符編碼
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream(), "UTF-8"));
            String line;
            
            Date date = new Date();
        	String nowTime = sdFormat.format(date);
            
            while ((line = reader.readLine()) != null) { // 讀取Python腳本的輸出
            	response += line;
                System.out.println("Python輸出: " + line + "(" + product +")" + " " + nowTime);
            }
            
            // 讀取排程的錯誤輸出，並設定字符編碼
            BufferedReader errorReader = new BufferedReader(new InputStreamReader(process.getErrorStream(), "UTF-8"));
         
            
            String errorLine;
            while ((errorLine = errorReader.readLine()) != null) {
                System.out.println("Error: " + errorLine);
            }
            
            int exitCode = process.waitFor(); // 等待排程執行完成
            
            System.out.println("Python腳本執行完成，退出代碼：" + exitCode); // 打印牌程的退出代碼

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
		
		return response;
	}
}
