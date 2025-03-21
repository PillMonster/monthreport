背景：
品管工程師於每月月底須作成當月的動態表，統計各加工單位及廠商的交貨數量及批量，因廠商種類多，加上各倉庫別含有不同產品別，需要一一篩選及過濾不同產品別的料號，這些過程十分繁瑣，處理資料過程中如一不注意，容易出錯，需要往前再修正。

文書作業流程：
1. 從R12系統下載各倉庫別的Excel檔案。
2. 整合Excel檔案(篩選及過濾)。
3. 插入欄位。
4. 計算每一間供應商各材料的交貨日期。
5. 統計每一間供應商各材料的交貨數量及批量。
6. 最後將每一間供應商的交貨數量及批量輸入至動態表。

改善案後：
1. 撰寫Python讀取Excel檔案，自動篩選、過濾、統計及匯入。
2. 撰寫Java建置網站，提供品管工程師上傳檔案及下載動態表。

效益：
改善後可快速取得整理後的動態表，作業時間減少8小時，有助於降低進料人員工作量。
