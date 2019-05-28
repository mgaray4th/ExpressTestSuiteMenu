package inbound.Files;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import org.apache.commons.io.FileUtils;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;

public class QuickProcessing {
	private DateFormat dt, tm, ss, eng_date;
	private JSch jsch;
	private Session session;
	private Channel channel;
	private ChannelSftp sftpChannel;
	private Connection conn;
	private Statement stmt;
	private ResultSet rs;

	public String env = "";

	/* SFTP Connection */
	public String SftpServer = "";
	public String SftpPath = "";
	public String SftpPort = "22";
	public String SftpUser = "4thsource";
	public String SftpPwd = "4thsource";

	/* DB Connection */
	public String DBUser = "4thsource";
	public String DBPwd = "4thsource";
	public String DBUrl = "";

	private final String automatedFilesPath = System.getProperty("user.dir")
			+ "\\automatedFiles\\";
	public final String ConfigFilePath = System.getProperty("user.dir")
			+ "\\src\\utility\\";
	public boolean WereDBVarsModified = false;
	public boolean WereSftpVarsModified = false;

	/* CONSTRUCTOR */
	public QuickProcessing() {
		jsch = new JSch();
		dt = new SimpleDateFormat("yyyyMMdd");
		tm = new SimpleDateFormat("HHmmss");
		ss = new SimpleDateFormat("yyyMMddss");
		eng_date = new SimpleDateFormat("MMddyyyyHHmmss");
	}

	/* Setting variables */
	public void SetVariables() throws Exception {
		DBUrl = GetDBUrl();
		DBSetUp();
		SftpServer = GetSftpServer();
		SftpPath = GetSftpPath();
		SftpSetUp();
	}

	/* FILES */
	public void CardAssocQuickProcess() throws Exception {
		System.out.println("CARDLOADER ASSOC quick processing.");

		// PRODUCT values
		String ALTERNATEID = dt.format(new Date()) + tm.format(new Date())
				+ "01", PLCC_TOKEN = dt.format(new Date())
				+ tm.format(new Date()) + "001", IS_PRIMARY = "Y", OPEN_CLOSED_IND = "O";

		// Generating MASK
		String mask = "EXP_CARDLOADER_ASSOC_" + dt.format(new Date()) + "_"
				+ tm.format(new Date()) + ".TXT.dec";
		// Creating file
		FileCreation(automatedFilesPath, mask);
		// Opening File to add content.. using BufferedWriter when inserting big
		// amount of data for better performance
		FileWriter writer = new FileWriter(automatedFilesPath + mask, true);
		BufferedWriter bufferedWriter = new BufferedWriter(writer);

		// Creating and inserting Detail row
		String header = "ALTERNATEID|PLCC_TOKEN|IS_PRIMARY|OPEN_CLOSED_IND";
		String row = ALTERNATEID + "|" + PLCC_TOKEN + "|" + IS_PRIMARY + "|"
				+ OPEN_CLOSED_IND;

		bufferedWriter.write(row + System.lineSeparator());
		bufferedWriter.close();
		System.out.println("Record sent:");
		System.out.println(header);
		System.out.println(row);
		UploadSftpFile(automatedFilesPath, SftpPath + "/in/auto/", mask);
		GenerateTriggerForInbound(mask);
		FileDelete(automatedFilesPath, mask);
		System.out
				.println("QUICK FILE PROCESS COMPLETED SUCCESSFULLY - CARD ASSOC");
	}

	public void CardDisassocQuickProcess() throws Exception {
		System.out.println("CARDLOADER DISASSOC quick processing.");

		// PRODUCT values
		String IP_CODE = GetFromActiveMember("l.ipcode"), TOKENIZED_CC_NBR = dt
				.format(new Date()) + tm.format(new Date()) + "001";

		// Generating MASK
		String mask = "EXP_CARDLOADER_DISASSOC_" + dt.format(new Date()) + "_"
				+ tm.format(new Date()) + ".TXT.dec";
		// Creating file
		FileCreation(automatedFilesPath, mask);
		// Opening File to add content.. using BufferedWriter when inserting big
		// amount of data for better performance
		FileWriter writer = new FileWriter(automatedFilesPath + mask, true);
		BufferedWriter bufferedWriter = new BufferedWriter(writer);

		// Creating and inserting Detail row
		String header = "IP_CODE|TOKENIZED_CC_NBR";
		String row = IP_CODE + "|" + TOKENIZED_CC_NBR;

		bufferedWriter.write(row + System.lineSeparator());
		bufferedWriter.close();
		System.out.println("Record sent:");
		System.out.println(header);
		System.out.println(row);
		UploadSftpFile(automatedFilesPath, SftpPath + "/in/auto/", mask);
		GenerateTriggerForInbound(mask);
		FileDelete(automatedFilesPath, mask);
		System.out
				.println("QUICK FILE PROCESS COMPLETED SUCCESSFULLY - CARD DISASSOC");
	}

	public void CustomerQuickProcess() throws Exception {
		System.out.println("CUSTOMER quick processing.");

		// PRODUCT values
		String RECTYPE = "5", MEMBER_KEY = dt.format(new Date())
				+ tm.format(new Date()) + "1", LOYALTY_ID = "", NAME_PREFIX = ""
				+ dt.format(new Date()), FIRST_NAME = "First "
				+ dt.format(new Date()), LAST_NAME = "Last "
				+ dt.format(new Date()), NAME_SUFFIX = ""
				+ dt.format(new Date()), ADDRESS_1 = "Adress one "
				+ dt.format(new Date()), CITY = "MZ", STATE = "ST", ZIPCODE = tm
				.format(new Date()), COUNTRY = "ABC", PRIMARY_PHONE_NUMBER = "66"
				+ dt.format(new Date()), EMAIL_ADDRESS_MAILABLE = "0", MEMBER_STATUS_CODE = "5", MEMBER_SOURCE = "257", GENDER = "U", IS_PLCC_HOLDER = "0", BIRTH_DATE = "1015", // MMDD
		DIRECT_MAIL_OPT_IN = "0", DIRECT_MAIL_OPT_IN_DATE = dt
				.format(new Date()) + tm.format(new Date()), EMAIL_OPT_IN = "0", EMAIL_OPT_IN_DATE = dt
				.format(new Date()) + tm.format(new Date()), SMS_OPT_IN = "0", SMS_OPT_IN_DATE = dt
				.format(new Date()) + tm.format(new Date()), LAST_ACTIVITY_DATE = dt
				.format(new Date()) + tm.format(new Date()), NCOA_LAST_CHANGE_DATE = dt
				.format(new Date()) + tm.format(new Date());

		// Generating MASK
		String mask = "EXP_CUSTOMER_" + dt.format(new Date()) + "_"
				+ tm.format(new Date()) + ".TXT.dec";
		// Creating file
		FileCreation(automatedFilesPath, mask);
		// Opening File to add content.. using BufferedWriter when inserting big
		// amount of data for better performance
		FileWriter writer = new FileWriter(automatedFilesPath + mask, true);
		BufferedWriter bufferedWriter = new BufferedWriter(writer);

		// Creating and inserting row
		String header = "RECTYPE|MEMBER_KEY|LOYALTY_ID|NAME_PREFIX|FIRST_NAME|LAST_NAME|"
				+ "NAME_SUFFIX|ADDRESS_1|CITY|STATE|ZIPCODE|COUNTRY|PRIMARY_PHONE_NUMBER|"
				+ "EMAIL_ADDRESS_MAILABLE|MEMBER_STATUS_CODE|MEMBER_SOURCE|GENDER|"
				+ "IS_PLCC_HOLDER|BIRTH_DATE|DIRECT_MAIL_OPT_IN|DIRECT_MAIL_OPT_IN_DATE|"
				+ "EMAIL_OPT_IN|EMAIL_OPT_IN_DATE|SMS_OPT_IN|SMS_OPT_IN_DATE|"
				+ "LAST_ACTIVITY_DATE|NCOA_LAST_CHANGE_DATE";
		String row = RECTYPE + "|" + MEMBER_KEY + "|" + LOYALTY_ID + "|"
				+ NAME_PREFIX + "|" + FIRST_NAME + "|" + LAST_NAME + "|"
				+ NAME_SUFFIX + "|" + ADDRESS_1 + "|" + CITY + "|" + STATE
				+ "|" + ZIPCODE + "|" + COUNTRY + "|" + PRIMARY_PHONE_NUMBER
				+ "|" + EMAIL_ADDRESS_MAILABLE + "|" + MEMBER_STATUS_CODE + "|"
				+ MEMBER_SOURCE + "|" + GENDER + "|" + IS_PLCC_HOLDER + "|"
				+ BIRTH_DATE + "|" + DIRECT_MAIL_OPT_IN + "|"
				+ DIRECT_MAIL_OPT_IN_DATE + "|" + EMAIL_OPT_IN + "|"
				+ EMAIL_OPT_IN_DATE + "|" + SMS_OPT_IN + "|" + SMS_OPT_IN_DATE
				+ "|" + LAST_ACTIVITY_DATE + "|" + NCOA_LAST_CHANGE_DATE;

		bufferedWriter.write(row + System.lineSeparator());
		bufferedWriter.close();
		System.out.println("Record sent:");
		System.out.println(header);
		System.out.println(row);
		UploadSftpFile(automatedFilesPath, SftpPath + "/in/auto/", mask);
		GenerateTriggerForInbound(mask);
		FileDelete(automatedFilesPath, mask);
		System.out
				.println("QUICK FILE PROCESS COMPLETED SUCCESSFULLY - CUSTOMER");
	}

	public void KillListQuickProcess() throws Exception {
		System.out.println("KILLLIST quick processing.");

		// PRODUCT values
		String HOUSEHOLDKEY = dt.format(new Date()) + tm.format(new Date())
				+ "01", FIRSTNAME = "First " + dt.format(new Date()), NAMEPREFIX = "Prefix", LASTNAME = "Last "
				+ dt.format(new Date()), NAMESUFFIX = "Suffix", ADDRESSLINEONE = "Adress one "
				+ dt.format(new Date()), CITY = "City", STATE = "ST", ZIPCODE = tm
				.format(new Date());

		// Generating MASK
		String mask = "EXP_KILLLIST_" + dt.format(new Date()) + "_"
				+ tm.format(new Date()) + ".TXT.dec";
		// Creating file
		FileCreation(automatedFilesPath, mask);
		// Opening File to add content.. using BufferedWriter when inserting big
		// amount of data for better performance
		FileWriter writer = new FileWriter(automatedFilesPath + mask, true);
		BufferedWriter bufferedWriter = new BufferedWriter(writer);

		// Creating and inserting Detail row
		String header = "HOUSEHOLDKEY|FIRSTNAME|NAMEPREFIX|LASTNAME|NAMESUFFIX|"
				+ "ADDRESSLINEONE|CITY|STATE|ZIPCODE";
		String row = HOUSEHOLDKEY + "|" + FIRSTNAME + "|" + NAMEPREFIX + "|"
				+ LASTNAME + "|" + NAMESUFFIX + "|" + ADDRESSLINEONE + "|"
				+ CITY + "|" + STATE + "|" + ZIPCODE;

		bufferedWriter.write(row + System.lineSeparator());
		bufferedWriter.close();
		System.out.println("Record sent:");
		System.out.println(header);
		System.out.println(row);
		UploadSftpFile(automatedFilesPath, SftpPath + "/in/auto/", mask);
		GenerateTriggerForInbound(mask);
		FileDelete(automatedFilesPath, mask);
		System.out
				.println("QUICK FILE PROCESS COMPLETED SUCCESSFULLY - KILLLIST");
	}

	public void MultiEmailQuickProcess() throws Exception {
		System.out.println("MULTIEMAIL quick processing.");

		// PRODUCT values
		String A_EMAILADDRESS = dt.format(new Date()) + tm.format(new Date())
				+ "@test.com", A_OPTINFLAG = "NO", A_OPTDATE = dt
				.format(new Date()) + tm.format(new Date()), A_MEMBERKEY = GetFromActiveMember("l.alternateid");

		// Generating MASK
		String mask = "EXP_MULTIEMAIL_" + dt.format(new Date()) + "_"
				+ tm.format(new Date()) + ".TXT.dec";
		// Creating file
		FileCreation(automatedFilesPath, mask);
		// Opening File to add content.. using BufferedWriter when inserting big
		// amount of data for better performance
		FileWriter writer = new FileWriter(automatedFilesPath + mask, true);
		BufferedWriter bufferedWriter = new BufferedWriter(writer);

		// Creating and inserting Detail row
		String header = "A_EMAILADDRESS|A_OPTINFLAG|A_OPTDATE|A_MEMBERKEY";
		String row = A_EMAILADDRESS + "|" + A_OPTINFLAG + "|" + A_OPTDATE + "|"
				+ A_MEMBERKEY;

		bufferedWriter.write(row + System.lineSeparator());
		bufferedWriter.close();
		System.out.println("Record sent:");
		System.out.println(header);
		System.out.println(row);
		UploadSftpFile(automatedFilesPath, SftpPath + "/in/auto/", mask);
		GenerateTriggerForInbound(mask);
		FileDelete(automatedFilesPath, mask);
		System.out
				.println("QUICK FILE PROCESS COMPLETED SUCCESSFULLY - MULTIEMAIL");
	}

	public void MultiPhoneQuickProcess() throws Exception {
		System.out.println("MULTIPHONE quick processing.");

		// PRODUCT values
		String A_PHONENUMBER = "66" + dt.format(new Date()), A_OPTINFLAG = "NO", A_OPTDATE = dt
				.format(new Date()) + tm.format(new Date()), A_MEMBERKEY = GetFromActiveMember("l.alternateid");

		// Generating MASK
		String mask = "EXP_MULTIPHONE_" + dt.format(new Date()) + "_"
				+ tm.format(new Date()) + ".TXT.dec";
		// Creating file
		FileCreation(automatedFilesPath, mask);
		// Opening File to add content.. using BufferedWriter when inserting big
		// amount of data for better performance
		FileWriter writer = new FileWriter(automatedFilesPath + mask, true);
		BufferedWriter bufferedWriter = new BufferedWriter(writer);

		// Creating and inserting Detail row
		String header = "A_PHONENUMBER|A_OPTINFLAG|A_OPTDATE|A_MEMBERKEY";
		String row = A_PHONENUMBER + "|" + A_OPTINFLAG + "|" + A_OPTDATE + "|"
				+ A_MEMBERKEY;

		bufferedWriter.write(row + System.lineSeparator());
		bufferedWriter.close();
		System.out.println("Record sent:");
		System.out.println(header);
		System.out.println(row);
		UploadSftpFile(automatedFilesPath, SftpPath + "/in/auto/", mask);
		GenerateTriggerForInbound(mask);
		FileDelete(automatedFilesPath, mask);
		System.out
				.println("QUICK FILE PROCESS COMPLETED SUCCESSFULLY - MULTIPHONE");
	}

	public void ProductQuickProcess() throws Exception {
		System.out.println("PRODUCT quick processing.");

		// PRODUCT values
		String PRODUCTKEY = dt.format(new Date()) + tm.format(new Date())
				+ "01", PARTNUMBER = dt.format(new Date())
				+ tm.format(new Date()) + "01", NAME = "Product inserted by Java automated process on "
				+ dt.format(new Date()), CLASSCODE = tm.format(new Date()), CLASSDESCRIPTION = "Class description "
				+ dt.format(new Date()), DEPTCODE = tm.format(new Date()), DEPTDESCRIPTION = "Dept Description "
				+ dt.format(new Date()), DIVISIONCODE = tm.format(new Date()), DIVISIONNAME = "Division name "
				+ dt.format(new Date());

		// Generating MASK
		String mask = "EXP_PRODUCT_" + dt.format(new Date()) + "_"
				+ tm.format(new Date()) + ".TXT.dec";
		// Creating file
		FileCreation(automatedFilesPath, mask);
		// Opening File to add content.. using BufferedWriter when inserting big
		// amount of data for better performance
		FileWriter writer = new FileWriter(automatedFilesPath + mask, true);
		BufferedWriter bufferedWriter = new BufferedWriter(writer);

		// Creating and inserting Detail row
		String header = "PRODUCTKEY|PARTNUMBER|NAME|CLASSCODE|CLASSDESCRIPTION|"
				+ "DEPTCODE|DEPTDESCRIPTION|DIVISIONCODE|DIVISIONNAME";
		String row = PRODUCTKEY + "|" + PARTNUMBER + "|" + NAME + "|"
				+ CLASSCODE + "|" + CLASSDESCRIPTION + "|" + DEPTCODE + "|"
				+ DEPTDESCRIPTION + "|" + DIVISIONCODE + "|" + DIVISIONNAME;

		bufferedWriter.write(row + System.lineSeparator());
		bufferedWriter.close();
		System.out.println("Record sent:");
		System.out.println(header);
		System.out.println(row);
		UploadSftpFile(automatedFilesPath, SftpPath + "/in/auto/", mask);
		GenerateTriggerForInbound(mask);
		FileDelete(automatedFilesPath, mask);
		System.out
				.println("QUICK FILE PROCESS COMPLETED SUCCESSFULLY - PRODUCT");
	}

	public void StoreQuickProcess() throws Exception {
		System.out.println("STOREMASTER quick process.");

		// PRODUCT values
		String STORENAME = "Name " + dt.format(new Date()), ADDRESSLINEONE = "Adress line one "
				+ dt.format(new Date()), ADDRESSLINETWO = "Adress line two "
				+ dt.format(new Date()), CITY = "Java", STATE = "JV", ZIPCODE = tm
				.format(new Date()), COUNTRY = "ABC", REGION = tm
				.format(new Date()), DISTRICT = "Division name "
				+ dt.format(new Date()), STORENUMBER = dt.format(new Date())
				+ tm.format(new Date()) + "01", STOREKEY = dt
				.format(new Date()) + tm.format(new Date()) + "01";

		// Generating MASK
		String mask = "EXP_STOREMASTER_" + dt.format(new Date()) + "_"
				+ tm.format(new Date()) + ".TXT.dec";
		// Creating file
		FileCreation(automatedFilesPath, mask);
		// Opening File to add content.. using BufferedWriter when inserting big
		// amount of data for better performance
		FileWriter writer = new FileWriter(automatedFilesPath + mask, true);
		BufferedWriter bufferedWriter = new BufferedWriter(writer);

		// Creating and inserting Detail row
		String header = "STORENAME|ADDRESSLINEONE|ADDRESSLINETWO|CITY|STATE|"
				+ "ZIPCODE|COUNTRY|REGION|DISTRICT|STORENUMBER|STOREKEY";
		String row = STORENAME + "|" + ADDRESSLINEONE + "|" + ADDRESSLINETWO
				+ "|" + CITY + "|" + STATE + "|" + ZIPCODE + "|" + COUNTRY
				+ "|" + REGION + "|" + DISTRICT + "|" + STORENUMBER + "|"
				+ STOREKEY;

		bufferedWriter.write(row + System.lineSeparator());
		bufferedWriter.close();
		System.out.println("Record sent:");
		System.out.println(header);
		System.out.println(row);
		UploadSftpFile(automatedFilesPath, SftpPath + "/in/auto/", mask);
		GenerateTriggerForInbound(mask);
		FileDelete(automatedFilesPath, mask);
		System.out
				.println("QUICK FILE PROCESS COMPLETED SUCCESSFULLY - STOREMASTER");
	}

	public void TlogQuickProcess() throws Exception {
		System.out.println("TLOG quick processing.");

		// DETAIL ROW values
		String D_lid = GetFromActiveMember("v.loyaltyidnumber"), D_headerid = dt
				.format(new Date()) + tm.format(new Date()) + "01", D_detailid = dt
				.format(new Date()) + tm.format(new Date()) + "01", D_detailquantity = "1", D_dtlDiscountAmnt = "-0.0", D_txnNumber = tm
				.format(new Date()), D_txnDate = dt.format(new Date()), D_txnRegisterNbr = "1", D_txnStore = "2401", D_txnType = "1", D_dtlItemLineNbr = "1", D_dtlProduct = GetFromProducTable("p.partnumber"), D_dtlRetailAmnt = "0.01", D_dtlSaleAmnt = "0.01", D_AssDscFlag = "0";

		// TENDER ROW values
		String T_txnStore = D_txnStore, T_txnDate = D_txnDate, T_headerid = D_headerid, T_tenderid = dt
				.format(new Date()) + tm.format(new Date()) + "01", T_TenderType = "35", T_TenderAmnt = "1", T_TokenizedNbr = "0";

		// Generating MASK
		String mask = "EXP_TLOG_" + dt.format(new Date()) + "_"
				+ tm.format(new Date()) + ".TXT.dec";
		// Creating file
		FileCreation(automatedFilesPath, mask);
		// Opening File to add content.. using BufferedWriter when inserting big
		// amount of data for better performance
		FileWriter writer = new FileWriter(automatedFilesPath + mask, true);
		BufferedWriter bufferedWriter = new BufferedWriter(writer);

		// Creating and inserting Detail row
		String detailheader = "Type|D_lid|D_headerid|D_detailid|D_detailquantity|"
				+ "D_dtlDiscountAmnt|D_txnNumber|D_txnDate|D_txnRegisterNbr|D_txnStore|"
				+ "D_txnType|D_dtlItemLineNbr|D_dtlProduct|D_dtlRetailAmnt|D_dtlSaleAmnt|"
				+ "D_AssDscFlag";
		String detailrow = "D" + "|" + D_lid + "|" + D_headerid + "|"
				+ D_detailid + "|" + D_detailquantity + "|" + D_dtlDiscountAmnt
				+ "|" + D_txnNumber + "|" + D_txnDate + "|" + D_txnRegisterNbr
				+ "|" + D_txnStore + "|" + D_txnType + "|" + D_dtlItemLineNbr
				+ "|" + D_dtlProduct + "|" + D_dtlRetailAmnt + "|"
				+ D_dtlSaleAmnt + "|" + D_AssDscFlag;
		bufferedWriter.write(detailrow + System.lineSeparator());

		// Creating and inserting Tender row
		String tenderheader = "Typt|T_txnStore|T_txnDate|T_headerid|T_tenderid|"
				+ "T_TenderType|T_TenderAmnt|T_TokenizedNbr";
		String tenderrow = "T" + "|" + T_txnStore + "|" + T_txnDate + "|"
				+ T_headerid + "|" + T_tenderid + "|" + T_TenderType + "|"
				+ T_TenderAmnt + "|" + T_TokenizedNbr;
		bufferedWriter.write(tenderrow + System.lineSeparator());

		bufferedWriter.close();
		System.out.println("Records sent:");
		System.out.println(detailheader);
		System.out.println(detailrow);
		System.out.println(tenderheader);
		System.out.println(tenderrow);
		UploadSftpFile(automatedFilesPath, SftpPath + "/in/auto/", mask);
		GenerateTriggerForInbound(mask);
		FileDelete(automatedFilesPath, mask);
		System.out.println("QUICK FILE PROCESS COMPLETED SUCCESSFULLY - TLOG");
	}

	public void UAKQuickProcess() throws Exception {
		System.out.println("UAK quick processing.");

		// PRODUCT values
		String lid = GetFromActiveMember("v.loyaltyidnumber"), CAMPAIGN_KEY = ss
				.format(new Date()), CAMPAIGN_SEGMENT_KEY = "Automated file. "
				+ env, RING_CODE = tm.format(new Date()), MEMBERKEY = GetFromLid(
				"l.alternateid", lid), MEMBERID = GetFromLid("l.ipcode", lid), COUPON_CODE = dt
				.format(new Date()) + tm.format(new Date()) + "01", COUPON_ISSUE_DATE = dt
				.format(new Date()) + tm.format(new Date()), COUPON_EXPIRATION_DATE = "20211230222515", COUPON_START_DATE = dt
				.format(new Date()) + tm.format(new Date()), COUPON_END_DATE = "20211230222515", TIP_ON = "1";

		// Generating MASK
		String mask = "EXP_UAK_" + dt.format(new Date()) + "_"
				+ tm.format(new Date()) + ".TXT.dec";
		// Creating file
		FileCreation(automatedFilesPath, mask);
		// Opening File to add content.. using BufferedWriter when inserting big
		// amount of data for better performance
		FileWriter writer = new FileWriter(automatedFilesPath + mask, true);
		BufferedWriter bufferedWriter = new BufferedWriter(writer);

		// Creating and inserting Detail row
		String header = "CAMPAIGN_KEY|CAMPAIGN_SEGMENT_KEY|RING_CODE|MEMBERKEY|MEMBERID|"
				+ "COUPON_CODE|COUPON_ISSUE_DATE|COUPON_EXPIRATION_DATE|COUPON_START_DATE|"
				+ "COUPON_END_DATE|TIP_ON";
		String row = CAMPAIGN_KEY + "|" + CAMPAIGN_SEGMENT_KEY + "|"
				+ RING_CODE + "|" + MEMBERKEY + "|" + MEMBERID + "|"
				+ COUPON_CODE + "|" + COUPON_ISSUE_DATE + "|"
				+ COUPON_EXPIRATION_DATE + "|" + COUPON_START_DATE + "|"
				+ COUPON_END_DATE + "|" + TIP_ON;

		bufferedWriter.write(row + System.lineSeparator());
		bufferedWriter.close();
		System.out.println("Records sent:");
		System.out.println(header);
		System.out.println(row);
		UploadSftpFile(automatedFilesPath, SftpPath + "/in/auto/", mask);
		GenerateTriggerForInbound(mask);
		FileDelete(automatedFilesPath, mask);
		System.out.println("QUICK FILE PROCESS COMPLETED SUCCESSFULLY - UAK");
	}

	public void EngagementQuickProcess() throws Exception {
		System.out.println("ENGAGEMENT quick file processing.");

		// PRODUCT values
		String LOYALTYID = MemberWithoutSLFB10today("v.loyaltyidnumber"), ENGAG_DESC = "SOC_LOGIN_FACEBOOK", ENGAG_DATE = eng_date
				.format(new Date()), // MMddyyyyHHmmss
		NOTES = "Automated file process";

		// Generating MASK
		String mask = "BP_EXP_ENGAGEMENT_" + dt.format(new Date()) + "_"
				+ tm.format(new Date()) + ".TXT.PGP.dec";
		// Creating file
		FileCreation(automatedFilesPath, mask);
		// Opening File to add content.. using BufferedWriter when inserting big
		// amount of data for better performance
		FileWriter writer = new FileWriter(automatedFilesPath + mask, true);
		BufferedWriter bufferedWriter = new BufferedWriter(writer);

		// Creating and inserting Detail row
		String header = "LOYALTYID|ENGAG_DESC|ENGAG_DATE|NOTES";
		String row = LOYALTYID + "|" + ENGAG_DESC + "|" + ENGAG_DATE + "|"
				+ NOTES;

		bufferedWriter.write(row + System.lineSeparator());
		bufferedWriter.close();
		System.out.println("Record sent:");
		System.out.println(header);
		System.out.println(row);
		UploadSftpFile(automatedFilesPath, SftpPath + "/in/auto/", mask);
		GenerateTriggerForInbound(mask);
		FileDelete(automatedFilesPath, mask);
	}

	public void BonusRewardQuickProcess() throws Exception {
		System.out.println("BONUSREWARD quick file processing.");

		// PRODUCT values
		String LOYALTYID = GetFromActiveMember("v.loyaltyidnumber"), ENGAG_DESC = "REW_BASE_BIRTHDAY", ENGAG_DATE = eng_date
				.format(new Date()), // MMddyyyyHHmmss
		NOTES = "Automated file process", NUM_OF_DAYS = "10";

		// Generating MASK
		String mask = "BP_EXP_BONUSREWARD_" + dt.format(new Date()) + "_"
				+ tm.format(new Date()) + ".TXT.PGP.dec";
		// Creating file
		FileCreation(automatedFilesPath, mask);
		// Opening File to add content.. using BufferedWriter when inserting big
		// amount of data for better performance
		FileWriter writer = new FileWriter(automatedFilesPath + mask, true);
		BufferedWriter bufferedWriter = new BufferedWriter(writer);

		// Creating and inserting Detail row
		String header = "LOYALTYID|ENGAG_DESC|ENGAG_DATE|NOTES|NUM_OF_DAYS";
		String row = LOYALTYID + "|" + ENGAG_DESC + "|" + ENGAG_DATE + "|"
				+ NOTES + "|" + NUM_OF_DAYS;

		bufferedWriter.write(row + System.lineSeparator());
		bufferedWriter.close();
		System.out.println("Record sent:");
		System.out.println(header);
		System.out.println(row);
		UploadSftpFile(automatedFilesPath, SftpPath + "/in/auto/", mask);
		GenerateTriggerForInbound(mask);
		FileDelete(automatedFilesPath, mask);
	}

	public void TerminateQuickProcess() throws Exception {
		System.out.println("TERMINATE LID quick file processing.");

		// PRODUCT values
		String LOYALTYID = GetFromActiveMember("v.loyaltyidnumber"), REASON_CODE = "Fraud";

		// Generating MASK
		String mask = "BP_EXP_TERMINATE_" + dt.format(new Date()) + "_"
				+ tm.format(new Date()) + ".TXT.dec";
		// Creating file
		FileCreation(automatedFilesPath, mask);
		// Opening File to add content.. using BufferedWriter when inserting big
		// amount of data for better performance
		FileWriter writer = new FileWriter(automatedFilesPath + mask, true);
		BufferedWriter bufferedWriter = new BufferedWriter(writer);

		// Creating and inserting Detail row
		String header = "LOYALTYID|REASON_CODE";
		String row = LOYALTYID + "|" + REASON_CODE;

		bufferedWriter.write("LOYALTY_ID|REASON CODE" + System.lineSeparator());
		bufferedWriter.write(row + System.lineSeparator());
		bufferedWriter.close();
		System.out.println("Record sent:");
		System.out.println(header);
		System.out.println(row);
		UploadSftpFile(automatedFilesPath, SftpPath + "/in/auto/", mask);
		GenerateTriggerForInbound(mask);
		FileDelete(automatedFilesPath, mask);
	}

	/* CUSTOM METHODS */
	public boolean FileCreation(String path, String filename)
			throws IOException {
		try {
			System.out.println("Creating file. " + filename);
			File file = new File(path + filename);
			file.createNewFile();
			System.out.println("Dir. " + path);
			System.out.println("File creation success.");
			return true;
		} catch (Exception e) {
			throw e;
		}
	}

	public String CheckSftpConnection() throws Exception {
		JSch jsch = new JSch();
		Session session = null;
		try {
			System.out.println("Checking Sftp connection...");
			session = jsch.getSession(SftpUser, SftpServer,
					Integer.parseInt(SftpPort));
			session.setConfig("StrictHostKeyChecking", "no");
			session.setPassword(SftpPwd);
			session.connect();
			session.disconnect();
			System.out.println("Sftp connection success.");
			return "Success";
		} catch (Exception e) {
			System.out.println("Exception. " + e.getMessage());
			throw e;
		}
	}

	public void SftpSetup(String server) throws Exception {
		try {
			System.out.println("Setting Sftp connection..");
			session = jsch.getSession(SftpUser, server,
					Integer.parseInt(SftpPort));
			session.setConfig("StrictHostKeyChecking", "no");
			session.setPassword(SftpPwd);
			session.connect();
			channel = session.openChannel("sftp");
			channel.connect();
			System.out.println("Sftp connection success.");
		} catch (Exception e) {
			SftpDisconnect();
			System.out.println("Exception. " + e.getMessage());
			throw e;
		}
	}

	public void SftpDisconnect() throws IOException {
		try {
			if (sftpChannel != null) {
				sftpChannel.exit();
			}
			if (channel != null) {
				channel.disconnect();
			}
			if (session != null) {
				session.disconnect();
			}
			System.out.println("Sftp correctly disconnected.");
		} catch (Exception e) {
			System.out.println("Exception. " + e.getMessage());
			throw e;
		}
	}

	public void UploadSftpFile(String from, String to, String filename)
			throws Exception {
		try {
			System.out.println("Uploading file. " + filename);
			System.out.println("To. " + to);// "/in/auto/"
			SftpSetup(SftpServer);
			sftpChannel = (ChannelSftp) channel;
			sftpChannel.put(from + filename, to + filename);
			System.out.println("Upload success.");
			SftpDisconnect();
		} catch (SftpException e) {
			System.out.println("Exception. " + e.getMessage());
			SftpDisconnect();
			throw e;
		}
	}

	public boolean FileDelete(String path, String filename) throws Exception {
		try {
			System.out.println("Deleting file. " + filename);
			File file = new File(path + filename);
			file.delete();
			System.out.println("Dir. " + path);
			System.out.println("File delete success.");
			return true;
		} catch (Exception e) {
			System.out.println("Error deleting file. " + e.getMessage());
			throw e;
		}
	}

	public String CheckDBConnection() throws Exception {
		try {
			System.out.println("Checking DataBase connection...");
			conn = DriverManager.getConnection(DBUrl, DBUser, DBPwd);
			stmt = conn.createStatement();
			DBDisconnect();
			System.out.println("Database connection success.");
			return "Success";
		} catch (Exception e) {
			DBDisconnect();
			System.out.println("Exception. " + e.getMessage());
			throw e;
		}
	}

	public void SetConnection() throws Exception {
		try {
			System.out.println("Setting DB connection..");
			conn = DriverManager.getConnection(DBUrl, DBUser, DBPwd);
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			System.out.println("DB connection success.");
		} catch (Exception e) {// SQLException
			DBDisconnect();
			System.out.println("Exception. " + e.getMessage());
			throw e;
		}
	}

	public String GetFromActiveMember(String value) throws Exception {
		System.out.println("Getting '" + value + "' from an active member.");
		String Query = "select " + value + " from bp_exp.lw_virtualcard v "
				+ "inner join bp_exp.lw_loyaltymember l on v.ipcode=l.ipcode "
				+ "where l.memberstatus = 1 "
				+ "and l.alternateid is not null " + "and rownum <= 1";
		return DBSingleRecord(Query);
	}

	public String GetFromProducTable(String value) throws Exception {
		System.out.println("Getting '" + value + "' from product table.");
		String Query = "select " + value + " from bp_exp.lw_product p "
				+ "where 1=1 and REGEXP_LIKE(p.partnumber, '^[[:digit:]]+$') "
				+ "and p.partnumber is not null";
		return DBSingleRecord(Query);
	}

	public String GetFromLid(String value, String lid) throws Exception {
		System.out.println("Getting '" + value + "' from member with LID '"
				+ lid + "'.");
		String Query = "select " + value + " from bp_exp.lw_virtualcard v "
				+ "inner join bp_exp.lw_loyaltymember l on v.ipcode=l.ipcode "
				+ "where v.loyaltyidnumber = '" + lid + "'";
		return DBSingleRecord(Query);
	}

	public String MemberWithoutSLFB10today(String value) throws Exception {
		System.out
				.println("Getting '"
						+ value
						+ "' from a member that does not have SOCIAL_LOGIN_FACEBOOK issued today.");
		String Query = "select "
				+ value
				+ " from bp_exp.lw_virtualcard v "
				+ "join bp_exp.lw_loyaltymember l on l.ipcode=v.ipcode "
				+ "where l.memberstatus = 1 "
				+ "and v.vckey not in ( select t.vckey from bp_exp.lw_pointtransaction t "
				+ "                     join bp_exp.lw_pointevent e on e.pointeventid=t.pointeventid "
				+ "                     where e.name = 'SLFB10' "
				+ "                     and trunc(t.transactiondate) = trunc(sysdate) "
				+ "                     ) " + "and rownum < 2";
		return DBSingleRecord(Query);
	}

	public String DBSingleRecord(String Query) throws Exception {
		try {
			String result = "";
			SetConnection();
			System.out.println("Executing query...");
			System.out.println("'" + Query + "'");
			rs = stmt.executeQuery(Query);
			if (rs.next()) {
				if (rs.getString(1) != null) {
					result = rs.getString(1);
					System.out.println("DB result: '" + result + "'.");
				} else {
					System.out.println("Null/empty value returned from DB.");
				}
			} else {
				System.out.println("No result returned from DB.");
			}
			rs.close();
			stmt.close();
			conn.close();
			System.out.println("DB connection closed.");
			return result;
		} catch (Exception e) {// SQLException
			System.out.println("Exception. " + e.getMessage());
			throw e;
		}
	}

	public void GenerateTriggerForInbound(String filename) throws Exception {
		System.out.println("Generating trigger file for Inbound File.");
		System.out.println(filename);
		String Query = "BEGIN "
				+ "sla.stg_utils .gen_trigger_file(p_filename => '" + filename
				+ "', " + "p_client_cd => 'EXPLW', "
				+ "p_directory_name => 'EXP_LW_IN_AUTO'); " + "END;";
		try {
			SetConnection();
			System.out.println("Executing query...");
			stmt.executeQuery(Query);
			stmt.close();
			conn.close();
			System.out.println("Success.");
			System.out.println("DB connection closed.");
		} catch (Exception e) {// SQLException
			DBDisconnect();
			System.out.println("Exception. " + e.getMessage());
			throw e;
		}
	}

	public void DBDisconnect() throws Exception {
		try {
			if (rs != null) {
				rs.close();
			}
			if (stmt != null) {
				stmt.close();
			}
			if (conn != null) {
				conn.close();
			}
			System.out.println("DB correctly disconnected.");
		} catch (Exception e) {
			System.out.println("Exception. " + e.getMessage());
			throw e;
		}
	}

	public void UpdateCredentials() throws IOException {
		try {
			System.out.println("Getting credentials from main project.");

			/* Reading all info from configuration file in main project */
			String content = FileUtils.readFileToString(new File(ConfigFilePath
					+ "Configuration.java"), "UTF-8");

			/* Retrieving SftpUser from main project file */
			String sftpus = "public String SftpUser = \"";
			int sftpusBegin = content.indexOf(sftpus) + sftpus.length();
			int sftpusend = content.indexOf("\"", content.indexOf(sftpus)
					+ sftpus.length());
			SftpUser = content.substring(sftpusBegin, sftpusend);
			/* Retrieving SftpPwd from main project file */
			String sftppw = "public String SftpPwd = \"";
			int sftppwBegin = content.indexOf(sftppw) + sftppw.length();
			int sftppwEnd = content.indexOf("\"", content.indexOf(sftppw)
					+ sftppw.length());
			SftpPwd = content.substring(sftppwBegin, sftppwEnd);

			/* Retrieving DB User from main project file */
			String dbus = "public String DBUser = \"";
			int dbusBegin = content.indexOf(dbus) + dbus.length();
			int dbusEnd = content.indexOf("\"",
					content.indexOf(dbus) + dbus.length());
			DBUser = content.substring(dbusBegin, dbusEnd);
			/* Retrieving DB Password from main project file */
			String dbpw = "public String DBPwd = \"";
			int dbpwBegin = content.indexOf(dbpw) + dbpw.length();
			int dbpwEnd = content.indexOf("\"",
					content.indexOf(dbpw) + dbpw.length());
			DBPwd = content.substring(dbpwBegin, dbpwEnd);

		} catch (IOException e) {
			// Simple exception handling, replace with what's necessary for your
			// use case!
			throw new RuntimeException("Generating file failed", e);
		}
	}

	public String GetDBUrl() throws IOException {
		String result = "";
		switch (env) {
		case "BPQA01":
			result = "jdbc:oracle:thin:@tytora-n01.brierley.com:1521:BPQA01";
			break;
		case "BPQA01B":
			result = "jdbc:oracle:thin:@typoravg01.brierley.com:1521:BPQA01B";
			break;
		case "BPQA01C":
			result = "jdbc:oracle:thin:@cy2coravg01.brierley.com:1521:BPQA01C";
			break;
		case "BPUAT01":
			result = "jdbc:oracle:thin:@tytora-n01.brierley.com:1521:BPUAT01";
			break;
		case "BPUAT01B":
			result = "jdbc:oracle:thin:@typoravg01.brierley.com:1521:BPUAT01B";
			break;
		case "BPUAT01C":
			result = "jdbc:oracle:thin:@cy2coravg01.brierley.com:1521:BPUAT01C";
			break;
		case "LT1C":
			result = "jdbc:oracle:thin:@typoraracdg1-v01.Brierley.com:1521/users";
			// result =
			// "jdbc:oracle:thin:@typoraracdg1-v01.Brierley.com:1521:users";
			break;
		case "PROD":
			result = "jdbc:oracle:thin:@cyporarac2-v01.Brierleyweb.com:1521:users";
			break;
		}
		System.out.println("DB URL: '" + result + "'.");
		return result;
	}

	public String GetSftpServer() throws IOException {
		String result = "";
		switch (env) {
		case "BPQA01":
		case "BPUAT01":
			result = "tytora-n01";
			break;
		case "BPQA01B":
		case "BPUAT01B":
			result = "typoravg01";
			break;
		case "BPQA01C":
		case "BPUAT01C":
			result = "cy2coravg01";
			break;
		case "LT1C":
			result = "NA/";
		}
		System.out.println("Sftp server: '" + result + "'.");
		return result;
	}

	public String GetSftpPath() throws IOException {
		String result = "";
		switch (env) {
		case "BPQA01":
			result = "/opt/app/oracle/flatfiles/exp/lw/qa_a/";
			break;
		case "BPQA01B":
			result = "/opt/app/oracle/flatfiles/exp/lw/qa_b/";
			break;
		case "BPQA01C":
			result = "/opt/app/oracle/flatfiles/exp/lw/qa_c/";
			break;
		case "BPUAT01":
			result = "/opt/app/oracle/flatfiles/exp/lw/uat_a/";
			break;
		case "BPUAT01B":
			result = "/opt/app/oracle/flatfiles/exp/lw/uat_b/";
			break;
		case "BPUAT01C":
			result = "/opt/app/oracle/flatfiles/exp/lw/uat_c/";
			break;
		}
		System.out.println("Sftp path: '" + result + "'.");
		return result;
	}

	public void DBSetUp() throws Exception {
		int attempt;
		String DBConn;

		attempt = 1;
		do {
			DBConn = CheckDBConnection();
			if (!DBConn.equals("Success")) {
				RequestForDBConstants(DBConn);
				attempt++;
			}
		} while (!DBConn.equals("Success") && attempt <= 3);
		if (!DBConn.equals("Success")) {
			System.out
					.println("DB connection failed, cannot continue with testing");
			System.exit(1);
			throw new Exception("DB connection failed, finished execution.");
		}

		if (DBConn.equals("Success")) {
			if (WereDBVarsModified && !env.equals("PROD")) {
				SaveDBContantsInFile();
			}
		}
	}

	public void RequestForDBConstants(String msg) throws IOException {
		System.out.println("Requesting DB credentials...");
		JTextField dbuser = new JTextField(), dbpass = new JPasswordField();
		dbuser.setText(DBUser);
		Object[] message = { "DB Connection.", env, "Username:", dbuser,
				"Password:", dbpass, System.lineSeparator(), msg, };
		int result = JOptionPane.showConfirmDialog(null, message,
				"Error while connecting", JOptionPane.DEFAULT_OPTION,
				JOptionPane.WARNING_MESSAGE);
		if (result == JOptionPane.OK_OPTION) {
			DBUser = dbuser.getText();
			DBPwd = dbpass.getText();
			WereDBVarsModified = true;
			System.out.println("Request uccess.");
		}
	}

	public void SaveDBContantsInFile() throws IOException {
		try {
			System.out.println("Saving DB credentials in local.");
			String content = FileUtils.readFileToString(new File(
					ConfigFilePath + "Configuration.java"), "UTF-8");
			String var1 = "public String DBUser = \"", var2 = "public String DBPwd = \"";
			// Get Substring starting at '"' after equals
			String currentUser = content.substring(
					content.indexOf(var1) + var1.length(), content.indexOf(
							"\"", content.indexOf(var1) + var1.length()))
					+ "\"";
			String currentPwd = content.substring(
					content.indexOf(var2) + var2.length(), content.indexOf(
							"\"", content.indexOf(var2) + var2.length()))
					+ "\"";
			content = content.replace(var1 + currentUser, var1 + DBUser + "\"");
			content = content.replace(var2 + currentPwd, var2 + DBPwd + "\"");
			File tempFile = new File(ConfigFilePath + "Configuration.java");
			FileUtils.writeStringToFile(tempFile, content, "UTF-8");
			System.out.println("Save success.");
		} catch (IOException e) {
			System.out.println("Error. " + e.getMessage());
			throw new RuntimeException("Generating file failed", e);
		}
	}

	public void SftpSetUp() throws Exception {
			int attempt;
			String SftpConn;

			attempt = 1;
			do {
				SftpConn = CheckSftpConnection();
				if (!SftpConn.equals("Success")) {
					RequestForSftpConstants(SftpConn);
					attempt++;
				}
			} while (!SftpConn.equals("Success") && attempt <= 3);
			if (!SftpConn.equals("Success")) {
				System.out
						.println("SFTP connection failed, cannot continue with testing");
				System.exit(1);
				throw new Exception(
						"SFTP connection failed, finished execution.");
			}

			if (SftpConn.equals("Success")) {
				if (WereSftpVarsModified) {
					SaveSftpContantsInFile();
				}
			}
	}

	public void RequestForSftpConstants(String msg) throws IOException {
		System.out.println("Requesting Sftp credentials...");
		JTextField sftpuser = new JTextField(), sftppass = new JPasswordField();
		sftpuser.setText(SftpUser);
		Object[] message = { "SFTP Connection.", "Username:", sftpuser,
				"Password:", sftppass, System.lineSeparator(), msg, };
		int result = JOptionPane.showConfirmDialog(null, message,
				"Error while connecting", JOptionPane.DEFAULT_OPTION,
				JOptionPane.WARNING_MESSAGE);
		if (result == JOptionPane.OK_OPTION) {
			SftpUser = sftpuser.getText();
			SftpPwd = sftppass.getText();
			WereSftpVarsModified = true;
			System.out.println("Request success.");
		}
	}

	public void SaveSftpContantsInFile() throws IOException {
		try {
			System.out.println("Saving Sftp credentials in local.");
			String content = FileUtils.readFileToString(new File(
					ConfigFilePath + "Configuration.java"), "UTF-8");
			String var1 = "public String SftpUser = \"", var2 = "public String SftpPwd = \"";
			// Get Substring starting at '"' after equals
			String currentUser = content.substring(
					content.indexOf(var1) + var1.length(), content.indexOf(
							"\"", content.indexOf(var1) + var1.length()))
					+ "\"";
			String currentPwd = content.substring(
					content.indexOf(var2) + var2.length(), content.indexOf(
							"\"", content.indexOf(var2) + var2.length()))
					+ "\"";
			content = content.replace(var1 + currentUser, var1 + SftpUser
					+ "\"");
			content = content.replace(var2 + currentPwd, var2 + SftpPwd + "\"");
			File tempFile = new File(ConfigFilePath + "Configuration.java");
			FileUtils.writeStringToFile(tempFile, content, "UTF-8");
			System.out.println("Save success.");
		} catch (IOException e) {
			// Simple exception handling, replace with what's necessary for your
			// use case!
			System.out.println("Error. " + e.getMessage());
			throw new RuntimeException("Generating file failed", e);
		}
	}
}
