package com.qingme.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Initialization extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	private static Connection conn=null;
	private static PreparedStatement pstmt;
	private static ResultSet rset;
	
	private static int question_count = 10;
	private static int project_id;
	private static int[] Answer = new int [question_count];
	private static String userType;
	private static String userID;
	private static String submitTime;
	
	private static int[] CommonNumber;
	private static int SponsorNumber;
	private static int SponsorQuestionID;
	private static int SponsorLocation;
	private static String[][] QuestionContent;
	private static String[] AllSponsor, AllDetail, AllSelectionA, AllSelectionB, AllSelectionC, AllSelectionD, AllSelectionCorrect,	AllExplain, AllRate;
	
	//数据库连接函数，包含连接的具体信息
	public static void DatabaseConnection (){
		try{
			String UserName = "root";
			String Password = "lyln";
			String Url = "jdbc:mysql://166.111.180.74:3306/qm_tiku";
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection (Url, UserName, Password);
		}
		catch (Exception e){System.out.println("Connection Error!");}
	}
	
	//获取题目序号的函数
	//输入：项目编号proID，要获取的普通题目数量QuestionCount
	//输出：数量为QuestionCount的数组，含有指定数量不重复的范围内整数
	public static int[] CommonQuestionNumber (int proID, int QuestionCount){
		
		int[] result = new int[QuestionCount];
		int[] error = new int[QuestionCount];
		
		try{
			//SQL语句执行部分：在tiku_project表里查找该项目对应的题目类型
			pstmt = conn.prepareStatement("select typeID from tiku_project where proID = " + proID + ";");
			rset = pstmt.executeQuery();
			rset.next();
			int QuestionType = rset.getInt(1);			
					
			//SQL语句执行部分：在tiku_question表里查找所求题目类型的总题数
			pstmt = conn.prepareStatement("select questionCount from tiku_question where typeID = " + QuestionType + ";");
			rset = pstmt.executeQuery();
			rset.next();
			int TotalCount = rset.getInt(1);
		
			//生成不重复的QuestionCount个范围内整数
			Random rand = new Random();
			int flag = 0;		
			result[0] = rand.nextInt(TotalCount);
			result[0]++;
		
			for (int i = 1; i < QuestionCount; i++){
				while (flag < i){
					flag = 0;
					result[i] = rand.nextInt(TotalCount+1);
					for (int n = 0; n < i; n++){
						if (result [n] != result [i] && result [i] != 0) flag++;
					}
				}			
			}
		return result;
		}
		catch (Exception e){return error;}
	}
	
	//获取赞助商题目编号的函数
	//输入：项目编号proID
	//输出：该项目的赞助商题目中的随机题号
	public static int SponsorQuestionNumber (int proID){
		
		try{
			//SQL语句执行部分：在tiku_project表里查找该项目的赞助商编号
			pstmt = conn.prepareStatement("select sponID from tiku_project where proID = " + proID + ";");
			rset = pstmt.executeQuery();
			rset.next();
			int sponID = rset.getInt(1);
			
			//SQL语句执行部分：在tiku_sponsor表里查找该赞助商的总题数
			pstmt = conn.prepareStatement("select questCount from tiku_sponsor where sponID = " + sponID + ";");
			rset = pstmt.executeQuery();
			rset.next();
			int TotalCount = rset.getInt(1);
			
			//生成一个TotalCount范围内整数
			Random rand = new Random();
			int result = rand.nextInt(TotalCount);
			result++;
			
			return result;
		}
		catch (Exception e){return -1;}
	}
	
	//获取赞助商名称、简介、网址和联系方式的函数，以及项目URL
	//输入：项目编号
	//输出：该项目对应赞助商的名称、简介、网址和联系方式，以及项目URL
	public static String[] SponsorInformation (int proID){

		String[] result = new String[5];
		String[] error = new String[5];
		try{
			//SQL语句执行部分：在tiku_project表里查找该项目的赞助商编号和项目URL
			pstmt = conn.prepareStatement("select sponID, proUrl from tiku_project where proID = " + proID + ";");
			rset = pstmt.executeQuery();
			rset.next();
			int sponID = rset.getInt(1);
			result[4] = rset.getString(2);
			
			//SQL语句执行部分：在tiku_sponsor表里查找该赞助商的具体信息
			pstmt = conn.prepareStatement("select * from tiku_sponsor where sponID = " + sponID + ";");
			rset = pstmt.executeQuery();
			rset.next();
			result[0] = rset.getString(2);
			result[1] = rset.getString(5);
			result[2] = rset.getString(6);
			result[3] = rset.getString(7);
			return result;
		}
		//如果数据库连接有误，返回的数组里包含5个Null
		catch (Exception e){return error;}
	}	
	
	//获取题目具体信息函数
	//输入：项目编号proID，题目类型Type（0代表普通题目，1代表赞助商），题目序号QuestionNumber
	//输出：题目具体信息String QuestionContent[]
	public static String[] QuestionContent (int proID, int Type, int QuestionNumber){
		String[] result = new String[8];
		String[] error = new String[8];
		try{
			//如果是普通题目，则查询普通题目表
			if (Type == 0){
				
				//SQL语句执行部分：在tiku_project表里查找该项目对应的普通题目类型
				pstmt = conn.prepareStatement("select typeID from tiku_project where proID = " + proID + ";");
				rset = pstmt.executeQuery();
				rset.next();
				int QuestionType = rset.getInt(1);	
				
				//SQL语句执行部分，在tiku_question表里查找该类型题目对应的表格
				pstmt = conn.prepareStatement("select typeTable from tiku_question where typeID = " + QuestionType + ";");
				rset = pstmt.executeQuery();
				rset.next();
				String typeTable = rset.getString(1);
				
				//SQL语句执行部分：在对应的普通题目表里查找题目具体信息
				pstmt = conn.prepareStatement("select * from " + typeTable + " where ID = " + QuestionNumber + ";");
				rset = pstmt.executeQuery();
				if (rset.next()){
					for (int i = 0; i < 7; i++){result[i] = rset.getString(i+2);}
					result[7] = rset.getString(11);
					return result; 
				} 
				else return error; 
			}

			else{
				//SQL语句执行部分：在tiku_project表里查找该项目对应的赞助商编号
				pstmt = conn.prepareStatement("select sponID from tiku_project where proID = " + proID + ";");
				rset = pstmt.executeQuery();
				rset.next();
				int SponID = rset.getInt(1);
				
				//SQL语句执行部分：在tiku_sponsor_001表格中找到对应赞助商的相应题目
				pstmt = conn.prepareStatement("select * from tiku_sponsor_001 where sponID = " + SponID + ";");
				rset = pstmt.executeQuery();
				for (int i = 0; i < QuestionNumber -1 ; i++){rset.next();}}
				if (rset.next()){
					for (int i = 0; i < 7; i++){result[i] = rset.getString(i+3);}
					result[7] = rset.getString(12);
					SponsorQuestionID = rset.getInt(1);
					return result; 
				} 
				else return error; 
			}
		//如果数据库连接有误，返回的数组里包含8个Null
		catch (Exception e){return error;}
	}	

	//内部参数初始化函数
	//输出：无，读取内部参数
	//输出：无，内部参数赋值
	public static void Initialize(){

		AllDetail = new String[question_count];
		AllSelectionA = new String[question_count];
		AllSelectionB = new String[question_count];
		AllSelectionC = new String[question_count];
		AllSelectionD = new String[question_count];
		AllSelectionCorrect = new String[question_count];
		AllExplain = new String[question_count];
		AllRate = new String[question_count];
		
		for(int i = 0; i < question_count; i++){
			AllDetail[i] = QuestionContent[i][0];
			AllSelectionA[i] = QuestionContent[i][1];
			AllSelectionB[i] = QuestionContent[i][2];
			AllSelectionC[i] = QuestionContent[i][3];
			AllSelectionD[i] = QuestionContent[i][4];
			AllSelectionCorrect[i] = QuestionContent[i][5];
			AllExplain[i] = QuestionContent[i][6];
			AllRate[i] = QuestionContent[i][7];
		}
	}
	
	//参数输出接口函数
	//输入：无输入，读取内部参数（项目编号，题目数量）
	//输出：每道题目的具体信息，以及赞助商信息
	public static void GetInformation(){
		
		CommonNumber = new int[question_count - 1];
		AllSponsor = new String[5];
		QuestionContent = new String[question_count][8];
		
		DatabaseConnection();
		CommonNumber = CommonQuestionNumber(project_id, question_count - 1);
		SponsorNumber = SponsorQuestionNumber(project_id);
		AllSponsor = SponsorInformation(project_id);
				
		Random rand = new Random();
		SponsorLocation = rand.nextInt(3); 
		SponsorLocation = SponsorLocation + 4;
		
		for (int i = 0; i < SponsorLocation; i++){
			QuestionContent[i] = QuestionContent(project_id, 0, CommonNumber[i]);
		}
		for (int i = SponsorLocation + 1; i < question_count; i++){
			QuestionContent[i] = QuestionContent(project_id, 0, CommonNumber[i - 1]);
		}
		
		QuestionContent[SponsorLocation] = QuestionContent(project_id, 1, SponsorNumber);
	}
	
	//数据库连接关闭函数
	//输入：无，读取全局变量
	//输出：无，关闭数据库连接的全局变量
	public static void CloseDatabase(){
		if (conn != null){
			try{conn.close ();}
			catch (Exception e){System.out.println("Can't close database!");};
		}
	}
	
	//项目答题记录更新函数
	//输入：答题者账号类型userType，答题者账号userID，答题时间submitTime
	//输出：整形标识变量（1：添加成功；0：重复答题；-1：答题者类型赋值有误；-11：数据库查询语句有误）
	public static int UpdateProject(){
		
		try{
			//SQL语句执行部分：在tiku_project表里查找该项目的表格
			pstmt = conn.prepareStatement("select proTable from tiku_project where proID = " + project_id + ";");
			rset = pstmt.executeQuery();
			rset.next();
			String proTable = rset.getString(1);			
			
			//SQL语句执行部分：在proTable表里查找该账号的记录
			pstmt = conn.prepareStatement("select userType from " + proTable + " where userID = '" + userID + "';");
			rset = pstmt.executeQuery();
			if (rset.next()){
				String UT = rset.getString(1);
				if (UT.equals(userType)) { return 0; }
			}
			
			//SQL语句执行部分：先更新项目总答题数，再更新项目答题类别数，再插入信息数据
			if (userType.equals("qingme") || userType.equals("qq") || userType.equals("weibo") || userType.equals("weixin") || userType.equals("phone")){
				
				//项目答题总数增加
				pstmt = conn.prepareStatement("select proCount from tiku_project where proID = " + project_id + ";");
				rset = pstmt.executeQuery();
				rset.next();
				int count = rset.getInt(1);
				count++;
				pstmt = conn.prepareStatement("update tiku_project set proCount = " + count + " where proID = " + project_id + ";");
				pstmt.execute();
				
				//单项答题数增加
				pstmt = conn.prepareStatement("select " + userType + "Count from tiku_project where proID = " + project_id + ";");
				rset = pstmt.executeQuery();
				rset.next();
				count = rset.getInt(1);
				count++;
				pstmt = conn.prepareStatement("update tiku_project set " + userType + "Count = " + count + " where proID = " + project_id + ";");
				pstmt.execute();
				
				//在对应项目表增加答题信息条目
				pstmt = conn.prepareStatement("insert " + proTable + " (userType,userID,submitTime) values ('" + userType + "','" + userID + "','" + submitTime + "');");
				pstmt.execute();
				
				return 1;
			}
			else return -1;
		}
		catch (Exception e){return -11;}
	}
	
	//答题数量和正确率更新函数
	//输入：无，读取系统全局变量
	//输出：整形标识变量（1：更新成功；-1：数据库查询语句有误）
	public static int UpdateQuestion(){
		try{
			//SQL语句执行部分：在tiku_project表里查找该项目的表格
			pstmt = conn.prepareStatement("select typeID from tiku_project where proID = " + project_id + ";");
			rset = pstmt.executeQuery();
			rset.next();
			int QuestionType = rset.getInt(1);
			
			//SQL语句执行部分：在tiku_project表里查找该项目的表格
			pstmt = conn.prepareStatement("select typeTable from tiku_question where typeID = " + QuestionType + ";");
			rset = pstmt.executeQuery();
			rset.next();
			String QuestionTable = rset.getString(1);
			
			for (int i = 0; i < SponsorLocation; i++){
				if (Answer[i] != -1){
					pstmt = conn.prepareStatement("select selectedCount, errorCount from " + QuestionTable + " where ID = " + CommonNumber[i] + ";");
					rset = pstmt.executeQuery();
					rset.next();
					double S_Count = rset.getInt(1);
					double E_Count = rset.getInt(2);
					S_Count++;
					E_Count = E_Count + Answer[i];
					double Rate = E_Count / S_Count;
					BigDecimal bg = new BigDecimal(Rate);
					double Rate_2 = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
					String E_Rate = "" + Rate_2;
					pstmt = conn.prepareStatement("update " + QuestionTable + " set selectedCount = " + S_Count + ", errorCount = " + E_Count + ", errorRate = '" + E_Rate + "' where ID = " + CommonNumber[i] + ";");
					pstmt.execute();
				}
			}
			
			for (int i = SponsorLocation + 1; i < question_count; i++){
				if (Answer[i] != -1){
					pstmt = conn.prepareStatement("select selectedCount, errorCount from " + QuestionTable + " where ID = " + CommonNumber[i - 1] + ";");
					rset = pstmt.executeQuery();
					rset.next();
					double S_Count = rset.getInt(1);
					double E_Count = rset.getInt(2);
					S_Count++;
					E_Count = E_Count + Answer[i];
					double Rate = E_Count / S_Count;
					BigDecimal bg = new BigDecimal(Rate);
					double Rate_2 = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
					String E_Rate = "" + Rate_2;
					pstmt = conn.prepareStatement("update " + QuestionTable + " set selectedCount = " + S_Count + ", errorCount = " + E_Count + ", errorRate = '" + E_Rate + "' where ID = " + CommonNumber[i-1] + ";");
					pstmt.execute();
				}
			}
			
			if (Answer[SponsorLocation] != -1){
				pstmt = conn.prepareStatement("select sponID from tiku_project where proID = " + project_id + ";");
				rset = pstmt.executeQuery();
				rset.next();
				int sponID = rset.getInt(1);
			
				pstmt = conn.prepareStatement("select selectedCount, errorCount from tiku_sponsor_001 where questID = " + SponsorQuestionID + " and sponID = " + sponID + ";");
				rset = pstmt.executeQuery();
				rset.next();
				double S_Count = rset.getInt(1);
				double E_Count = rset.getInt(2);
				S_Count++;
				E_Count = E_Count + Answer[SponsorLocation];
				double Rate = E_Count / S_Count;
				BigDecimal bg = new BigDecimal(Rate);
				double Rate_2 = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
				String E_Rate = "" + Rate_2;
				pstmt = conn.prepareStatement("update tiku_sponsor_001 set selectedCount = " + S_Count + ", errorCount = " + E_Count + ", errorRate = '" + E_Rate + "' where questID = " + SponsorQuestionID + " and sponID = " + sponID + ";");
				pstmt.execute();
			}
			return 1;
		}
		catch (Exception e){return -11;}
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
			doPost(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		String jspFlag = req.getParameter("jspFlag");
		
		if ("0".equals(jspFlag)) {    			//页面加载时执行这部分操作
			String proIdStr = req.getParameter("projectId");
			project_id = Integer.parseInt(proIdStr);
			
			Initialization.GetInformation();
			Initialization.Initialize();
			
			resp.setContentType("application/json;charset=UTF-8");//必须的设置，返回串类型指定json
			PrintWriter out=resp.getWriter();
			
			String str0 = "{\"allSponsor\":[", str1 = "\"allDetail\":[", str2 = "\"allSelectionA\":[", str3 = "\"allSelectionB\":[", str4 = "\"allSelectionC\":[",
					str5 = "\"allSelectionD\":[", str6 = "\"allSelectionCorrect\":[", str7 = "\"allExplain\":[", str8 = "\"allRate\":[", data="";
			for (int i = 0; i < AllSponsor.length-1; i++) {
				str0 += "{\"txt\":\"" + AllSponsor[i] + "\"},";
			}
			for (int j = 0; j < AllDetail.length-1; j++) {
				str1 += "{\"txt\":\"" + AllDetail[j] + "\"},";
				str2 += "{\"txt\":\"" + AllSelectionA[j] + "\"},";
				str3 += "{\"txt\":\"" + AllSelectionB[j] + "\"},";
				str4 += "{\"txt\":\"" + AllSelectionC[j] + "\"},";
				str5 += "{\"txt\":\"" + AllSelectionD[j] + "\"},";
				str6 += "{\"txt\":\"" + AllSelectionCorrect[j] + "\"},";
				str7 += "{\"txt\":\"" + AllExplain[j] + "\"},";
				str8 += "{\"txt\":\"" + AllRate[j] + "\"},";
			}
			str0 += "{\"txt\":\"" + AllSponsor[AllSponsor.length-1] + "\"}],";
			str1 += "{\"txt\":\"" + AllDetail[AllDetail.length-1] + "\"}],";
			str2 += "{\"txt\":\"" + AllSelectionA[AllDetail.length-1] + "\"}],";
			str3 += "{\"txt\":\"" + AllSelectionB[AllDetail.length-1] + "\"}],";
			str4 += "{\"txt\":\"" + AllSelectionC[AllDetail.length-1] + "\"}],";
			str5 += "{\"txt\":\"" + AllSelectionD[AllDetail.length-1] + "\"}],";
			str6 += "{\"txt\":\"" + AllSelectionCorrect[AllDetail.length-1] + "\"}],";
			str7 += "{\"txt\":\"" + AllExplain[AllDetail.length-1] + "\"}],";
			str8 += "{\"txt\":\"" + AllRate[AllDetail.length-1] + "\"}]}";
			data = str0 + str1 + str2 + str3 + str4 + str5 + str6 +str7 + str8;
			out.write(data);
			out.flush();
			out.close();
//			CloseDatabase();
		}else if ("1".equals(jspFlag)) {  			//页面提交后执行下面的操作
			//要从页面得到的数据
			userType = req.getParameter("userType");
			userID = req.getParameter("userID");
			submitTime = req.getParameter("submitTime");
			String[] Answers = req.getParameter("deltaArrs").split(",");
			for (int i = 0; i < Answers.length; i++) {
				Answer[i] = Integer.parseInt(Answers[i]);
			}
			System.out.print("Time: "+new Date().toString());
			System.out.print("    Answer:");
			for(int i=0;i<10;i++){
				System.out.print(" "+Answer[i]);
			}
			System.out.println("");
			int flag_question = 0;		//该题目是否更新成功
			int flag_project = UpdateProject(); //该帐号是否重复答题
			if (flag_project == 1){
				flag_question = UpdateQuestion();
			}else {
				//重复答题,通过ajax向页面发出提示
			}
			CloseDatabase();
		}else {
			return ;
		}
	}
}