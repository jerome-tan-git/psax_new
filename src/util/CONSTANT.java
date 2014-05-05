package util;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;

import org.apache.commons.codec.binary.Base64;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class CONSTANT {
	
	public final static int scorePerSingle = 1;
	public final static int scorePerMulti = 2;
	public final static int scorePerJudge = 1;
	
//	public final static int singleChoiceNum = 10;
//	public final static int multipleChoiceNum = 10;
//	public final static int judgeNum = 10;
	public final static int singleChoiceNum = 40;
	public final static int multipleChoiceNum = 5;
	public final static int judgeNum = 5;
	
	
	public final static int pageNum = 10;//考题页数
	public final static int pageSize = 5;//每页考题数量	
//	public final static int pageSize = 3;//每页考题数量		
	public final static int pageArtSize = 5;//每页文章数量
	public final static int pageUserSize = 10;//每页用户数量
	
	public final static int momentMaxLength = 280;
	public final static int articleAbsMaxLength = 45;
	public final static int articleImageListLength = 5;
	public final static int bbsTopicVolumn = 3;//每页显示BBS topic数目
	
	public final static String noContent = "暂无";
	
	public final static String CKeditorUrlPath = "./ckimages";
	public final static String uploadImagesPath = "./uploadimg";
	public final static String uploadFilesPath = "./uploaddoc";
	
	public final static String[] committees = {
		"未选择","重点企业专业委员会","糖果糕点专业委员会","饮料专业委员会",
		"副食品专业委员会","南北货专业委员会","食品相关产品专业委员会",
	};
	public static HashMap<String,Integer> seq;
	public final static String[] alphas = {
		"a","b","c","d","e",
		"f","g","h","i","j",
		"k","l","m","n","o",
		"p","q","r","s","t",
		"u","v","w","x","y",
		"z"
	};
	 public static String encodeStr(String plainText){  
	        byte[] b=plainText.getBytes();  
	        Base64 base64=new Base64();  
	        b=base64.encode(b);  
	        String s=new String(b);  
	        return s.replaceAll("/", "_");  
	    }  
	 public static String decodeStr(String encodeStr){ 
		 	encodeStr = encodeStr.replaceAll("_", "/");
	        byte[] b=encodeStr.getBytes();  
	        Base64 base64=new Base64();  
	        b=base64.decode(b);  
	        String s=new String(b);  
	        return s;  
	    } 
	public final static Collection<Integer> getRandomSeq(int _rzSize, ArrayList<Integer> _orlist){
		
		HashSet<Integer> rzlist = new HashSet<Integer>();
		Collection<Integer> result = new ArrayList<Integer>();
//		Integer[] index = new Integer[_rzSize];
//		System.out.println("mm0----------_rzSize="+_rzSize+", _orlist.size="+_orlist.size());
		if(_orlist.size()>_rzSize){
			while(rzlist.size()<_rzSize){  
				for(int i=0; i<_orlist.size(); i++){
					int id = (int) (Math.random() * _orlist.size());
//					System.out.println("mm2------------id="+id);
					rzlist.add(_orlist.get(id));
//					System.out.println("mm1------------"+rzlist.toString());
					if(rzlist.size()==_rzSize)
							break;
				}  
			} 
		}else
			System.out.println("DATA ERROR, PLS INV...");
 
		
		System.out.println("");
		for(Integer or:_orlist)		
			System.out.print(or+"::");
		System.out.println("");
		for(Integer rz:rzlist){			
			System.out.print(rz+"::");
			result.add(rz);
		}
		
		return result;
	}
	
	public final static Integer[] getRandomCol(int _rzSize, ArrayList<Integer> _orlist){
		
		HashSet<Integer> rzlist = new HashSet<Integer>();
		Integer[] index = new Integer[_rzSize];
		
		while(rzlist.size()<_rzSize){  
			for(int i=0; i<_orlist.size(); i++){
				int id = (int) (Math.random() * _orlist.size());
				rzlist.add(_orlist.get(id));
				if(rzlist.size()==_rzSize)
						break;
			}  
		}  
			
		int i=0;
		for(Integer rz:rzlist){			
			System.out.print(rz+"::");
			index[i]=rz;
			i++;
		}
		
		return index;
	}

	public final static String getThisYear(){		
		Date currentTime = new Date();   
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy");
		return formatter.format(currentTime);
	}
	public final static String getTodayDate(){
//		String today = "";
//		Calendar now = Calendar.getInstance();
//        int year = now.get(Calendar.YEAR);
//        int month = now.get(Calendar.MONTH) + 1;
//        int day = now.get(Calendar.DAY_OF_MONTH);
//        String smonth = "" + month;
//        String sday = "" + day;
//        if (month < 10)
//            smonth = "0" + month;
//        if (day < 10)
//            sday = "0" + day;
//        today = year + smonth + sday;
//		return today;
		Date currentTime = new Date();   
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		return formatter.format(currentTime);
	}
	
	public final static String getNowTime(){
		Date currentTime = new Date();   
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		return formatter.format(currentTime);
	}
	public final static String getNowTimeWithoutDay(){
		Date currentTime = new Date();   
		SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
		return formatter.format(currentTime);
	}
	public final static String getNowTime2Second(){
		Date currentTime = new Date();   
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
		return formatter.format(currentTime);
	}
	
	public static String getJSCateName(int _categoryid){
        String type = "single_selection";
        if(_categoryid==3)
        	type = "multi_selection";
        if(_categoryid==2)
        	type = "single_selection";
        if(_categoryid==1)
        	type = "truefalse";
        return type;
	}
	public static boolean getJStruefalse(int _refistrue){
		boolean tf = false;
		if(_refistrue==0)
			tf = false;
		if(_refistrue==1)
			tf = true;
		return tf;
	}
	
	//return seq numbers, sort by DATE
	public static List<Integer> sortDatesDesc(List<String> _datelist){
		List<Integer> seqs = new ArrayList<Integer>();
		List<Date> datelist = new ArrayList<Date>();		
		List<Date> unsorteddatelist = new ArrayList<Date>();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");  
		 /**字符串转时间**/  
		for(String dt:_datelist){
			try {
				datelist.add((Date) format.parse(dt));
				unsorteddatelist.add((Date) format.parse(dt));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		/**打印时间**/  
        System.out.println("排序前：");  
        for(Date d:datelist){  
            System.out.println(format.format(d));  
        }  
        /**冒泡排序**/  
        Date tempDate = null;  
        for(int i=datelist.size()-1; i>0; --i) {  
           for(int j=0; j<i; ++j) {  
                /**从大到小的排序**/  
                if(datelist.get(j+1).after(datelist.get(j))){  
                    tempDate = datelist.get(j);  
                    datelist.set(j, datelist.get(j+1));  
                    datelist.set(j+1, tempDate);  
                }else{  
                /**从小到大**/  
//                  tempDate = dateList.get(j);  
//                  dateList.set(j, dateList.get(j+1));  
//                  dateList.set(j+1, tempDate);  
                }  
            }  
        }  
        /**打印排序之后的时间**/
        System.out.println("排序后：");  
        for(int i=0; i<datelist.size(); i++){  
        	Date d = datelist.get(i);
            System.out.println(format.format(d));  
            for(int j=0; j<unsorteddatelist.size(); j++){
            	Date ud = unsorteddatelist.get(j);
            	if(ud.equals(d) && !seqs.contains(j)){
            		seqs.add(j);
            		break;
            	}            		
            }
        }  
		for(Integer seq:seqs)
			System.out.println("sesq="+seq);
		return seqs;
	}
	
	public static List<Integer> sortDatesDesc(List<String> _datelist, String _dateFormat){
		List<Integer> seqs = new ArrayList<Integer>();
		List<Date> datelist = new ArrayList<Date>();		
		List<Date> unsorteddatelist = new ArrayList<Date>();
		SimpleDateFormat format = new SimpleDateFormat(_dateFormat);  
		 /**字符串转时间**/  
		for(String dt:_datelist){
			try {
				datelist.add((Date) format.parse(dt));
				unsorteddatelist.add((Date) format.parse(dt));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		/**打印时间**/  
        System.out.println("排序前：");  
        for(Date d:datelist){  
            System.out.println(format.format(d));  
        }  
        /**冒泡排序**/  
        Date tempDate = null;  
        for(int i=datelist.size()-1; i>0; --i) {  
           for(int j=0; j<i; ++j) {  
                /**从大到小的排序**/  
                if(datelist.get(j+1).after(datelist.get(j))){  
                    tempDate = datelist.get(j);  
                    datelist.set(j, datelist.get(j+1));  
                    datelist.set(j+1, tempDate);  
                }else{  
                /**从小到大**/  
//                  tempDate = dateList.get(j);  
//                  dateList.set(j, dateList.get(j+1));  
//                  dateList.set(j+1, tempDate);  
                }  
            }  
        }  
        /**打印排序之后的时间**/
        System.out.println("排序后：");  
        for(int i=0; i<datelist.size(); i++){  
        	Date d = datelist.get(i);
            System.out.println(format.format(d));  
            for(int j=0; j<unsorteddatelist.size(); j++){
            	Date ud = unsorteddatelist.get(j);
            	if(ud.equals(d) && !seqs.contains(j)){
            		seqs.add(j);
            		break;
            	}            		
            }
        }  
		for(Integer seq:seqs)
			System.out.println("sesq="+seq);
		return seqs;
	}
	
	public static String replaceHtml(String html){
		String regEx="<.+?>"; //表示标签
		Pattern p=Pattern.compile(regEx);
		Matcher m=p.matcher(html);
		String s=m.replaceAll("");
		return s;
	}
	
	public static String[] getImgWidthHeight(String _filename) throws IOException{
		BufferedImage bi = null;
		
			bi = ImageIO.read(new File(_filename));
		
		String[] sizes = {bi.getWidth()+"",""+bi.getHeight()};
		System.out.println("Width=" + bi.getWidth());  
		System.out.println("Height=" + bi.getHeight());
		return sizes;
	}
	
//	public static String exportExcel(String _sheetname, String _filename, List<List<String>> _docs){
//		System.out.println("exportExcel---------1");
//		System.out.println("_sheetname="+_sheetname);
//		System.out.println("_filename="+_filename);
//		for(int i=0;i<_docs.size();i++){
//			System.out.println("line num="+i+",content="+_docs.get(i).toString());					
//		}
//		System.out.println("0-0");
//		HSSFWorkbook wb = new HSSFWorkbook();
//		System.out.println("0-1");
//		HSSFSheet sheet = wb.createSheet(_sheetname);
//		System.out.println("1-1");
//		if(_docs!=null && _docs.size()>0){
//			System.out.println("2-2");
//			for(int i=0;i<_docs.size(); i++){
//				List<String> line = _docs.get(i);
//				System.out.println("line3-3="+i+", content="+line.toString());
//				HSSFRow row = sheet.createRow(i);
//				for(int j=0; j<line.size(); j++){
//					HSSFCell cell = row.createCell(j);
//					cell.setCellValue(line.get(j));
//				}
//			}				
//		}else{
//			System.out.println("No data to output!");
//		}	
////		输出到具体路径
//		FileOutputStream fileout = null;
//		System.out.println("4-4");
//		try {
//			fileout = new FileOutputStream(_filename);
//		} catch (FileNotFoundException e) {
//			System.out.println("文件没找到！");
//			e.printStackTrace();			
////			return "FileNotFoundError";
//			return "fail1";
//		}
//		try {
//			wb.write(fileout);
//			fileout.close();
//		} catch (IOException e) {
//			System.out.println("文件写入出错！");
//			e.printStackTrace();
////			return "FileWritingError";
//			return "fail2";
//		}
//		System.out.println("5-5");
//		return "success";
//	}
	
	public static HSSFWorkbook getWorkbook(String _sheetname, List<List<String>> _docs) throws Exception { 
		
		HSSFWorkbook workbook = new HSSFWorkbook();		
		HSSFSheet sheet = workbook.createSheet(_sheetname);
		HSSFCellStyle cellStyle=workbook.createCellStyle();     
		cellStyle.setWrapText(true);     
		
	    
        if(_docs!=null && _docs.size()>0){
			
			for(int i=0;i<_docs.size(); i++){
				List<String> line = _docs.get(i);			
				HSSFRow row = sheet.createRow(i);
				for(int j=0; j<line.size(); j++){
					@SuppressWarnings("deprecation")
					HSSFCell cell = row.createCell((short)j);  
					cell.setCellValue(line.get(j));
					cell.setCellStyle(cellStyle); 
				}
			}				
		}else{
			System.out.println("No data to output!");
		}
        
        return workbook;  
    }  
	


	public static InputStream exportExcelStream(String _sheetname, String _filename, List<List<String>> _docs){
		
		
//		System.out.println("0-0");
//		HSSFWorkbook wb = new HSSFWorkbook();
//		System.out.println("0-1");
//		HSSFSheet sheet = wb.createSheet(_sheetname);
//		System.out.println("1-1");
//		if(_docs!=null && _docs.size()>0){
//			System.out.println("2-2");
//			for(int i=0;i<_docs.size(); i++){
//				List<String> line = _docs.get(i);
//				System.out.println("line3-3="+i+", content="+line.toString());
//				HSSFRow row = sheet.createRow(i);
//				for(int j=0; j<line.size(); j++){
//					HSSFCell cell = row.createCell(j);
//					cell.setCellValue(line.get(j));
//				}
//			}				
//		}else{
//			System.out.println("No data to output!");
//		}	
		HSSFWorkbook wb = null;
		try {
			wb = getWorkbook(_sheetname, _docs);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
//		输出到具体路径
		FileOutputStream fileout = null;
		System.out.println("4-4");
		try {
			fileout = new FileOutputStream(_filename);
		} catch (FileNotFoundException e) {
			System.out.println("文件没找到！");
			e.printStackTrace();			
//			return "FileNotFoundError";
		}
		try {
			wb.write(fileout);
			fileout.close();
		} catch (IOException e) {
			System.out.println("文件写入出错！");
			e.printStackTrace();
//			return "FileWritingError";
		}


		InputStream in = null;		  
		  try {
		   in = new FileInputStream(_filename);//将file转换成输入流
		   //in.close();
		  } catch (Exception e) {
		   e.printStackTrace();
		  }
		  
		  return in;
	}
	
	
	
	public static void main(String[] args){
//		ArrayList<Integer> _orlist = new ArrayList<Integer>();
//		for(int i=0; i<10; i++)
//			_orlist.add(i);
//		for(int i=0; i<_orlist.size(); i++)
//			System.out.print(_orlist.get(i)+"::");
//		System.out.println("\n");
////		ArrayList<Integer> rzlist = CONSTANT.getRandomSeq(2, _orlist);
//		Integer[] index = CONSTANT.getRandomCol(judgeNum, _orlist);
//		ArrayList<Integer> rzlist = new ArrayList<Integer>();
//		Collection<Integer> rlist  = CONSTANT.getRandomSeq(2, _orlist);
//		rzlist.addAll(rlist);
		
//		System.out.println(CONSTANT.getNowTime());
//		System.out.println(CONSTANT.getTodayDate());
		
//		List<String> arts = new ArrayList<String>();
//		arts.add("2012-08-24 12:51:12");
//		arts.add("2012-09-12 09:45:18");
//		arts.add("2012-12-22 14:05:12");
//		arts.add("1981-06-17 02:15:49");
//		arts.add("2013-01-23 15:16:46");
//		arts.add("2013-12-24");
//		arts.add("2013-05-14 10:12:56");
//		CONSTANT.sortDatesDesc(arts);
		
//		CONSTANT.getImgWidthHeight("//C://Users//Administrator//workspace_2//psax_new//WebContent//images//key_img_1.png");
//		CONSTANT.getImgWidthHeight("//C://Users//Administrator//workspace_2//psax_new//WebContent//images//10035.jpg");
		
//		String sheetname = "import";
//		String filename = "d:/import"+CONSTANT.getNowTime2Second()+".xls";
//		ArrayList<List<String>> excelDownloads = new ArrayList<List<String>>();
//		for(int i=0;i<7;i++){
//			List<String> line = new ArrayList<String>();
//			for(int j=0;j<10;j++){
//				line.add("v"+i+"_"+j);
//			}
//			excelDownloads.add(line);
//		}
//		CONSTANT.exportExcel(sheetname, filename,excelDownloads);
//		System.out.println("OK");
		
		List<String> arts = new ArrayList<String>();
		arts.add("08/24/2012");
		arts.add("09/12/2012");
		arts.add("12/22/2012");
		arts.add("06/17/1981");
		arts.add("01/23/2013");
		arts.add("12/24/2013");
		arts.add("05/14/1981");
		System.out.println(CONSTANT.sortDatesDesc(arts, "MM/dd/yyyy"));
		
	}
}
