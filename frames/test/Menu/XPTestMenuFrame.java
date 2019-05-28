package test.Menu;

import inbound.Files.QuickProcessing;

import java.awt.EventQueue;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.JRadioButton;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.UIManager;

import java.awt.Color;

import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.border.LineBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;

public class XPTestMenuFrame extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel JPanelMain, panelInbounds, panelRuleTriggers, panelCSP42,
			panelEnvironments;
	private JRadioButton rdbtnQaA, rdbtnQaB, rdbtnQaC, rdbtnUata, rdbtnUatb,
			rdbtnUatc, rdbtnSmoke, rdbtnRegression, rdbtnChrome, rdbtnFirefox;
	private JCheckBox chckbxInbTLog, chckbxInbEngFile;
	private JButton btnExecute, btnCancel, btnQuickfile;
	private JCheckBox chckbxInbBonusFile, chckbxBonusRules, chckbxAllInbounds,
			chckbxAllRuleTrgr, chckbxApiRules, chckbxEngRule, chckbxAllCSP42,
			chckbxLoginpage42;
	private JLabel lblTitle;
	private JLabel lblDaily;
	private JCheckBox chckbxInbStoreMaster;
	private JCheckBox chckbxInbCardAssociation;
	private JCheckBox chckbxInbCardDisassociation;
	private JCheckBox chckbxInbProduct;
	private JCheckBox chckbxInbCustomer;
	private JCheckBox chckbxInbKilllist;
	private JCheckBox chckbxInbMultiemail;
	private JCheckBox chckbxInbMultiPhone;
	private JCheckBox chckbxInbUak;
	private JSeparator separator;
	private JSeparator separator_1;
	private JLabel lblAdHoc;
	private JLabel lblOnlyFilesWith;
	private JCheckBox chckbxInbTerminate;
	private JCheckBox chckbxMemberSearch42;
	private JCheckBox chckbxAccountSummary42;
	private JCheckBox chckbxAccountActivity42;
	private JCheckBox chckbxMemberProfile42;
	private JCheckBox chckbxMergeAccounts42;
	private JCheckBox chckbxRewardHistory42;
	private JCheckBox chckbxCouponHistory42;
	private JCheckBox chckbxComPreference42;
	private JCheckBox chckbxUserAdministration42;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					XPTestMenuFrame frame = new XPTestMenuFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public XPTestMenuFrame() {
		setTitle("EXPRESS TEST SUITE");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 417, 634);
		JPanelMain = new JPanel();
		JPanelMain.setBackground(Color.WHITE);
		JPanelMain.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null,
				null, null, null));
		setContentPane(JPanelMain);
		JPanelMain.setLayout(null);

		panelInbounds = new JPanel();
		panelInbounds.setBackground(Color.WHITE);
		panelInbounds.setBorder(new TitledBorder(UIManager
				.getBorder("TitledBorder.border"), "Inbound Files.",
				TitledBorder.LEADING, TitledBorder.TOP, null,
				new Color(0, 0, 0)));
		panelInbounds.setBounds(10, 138, 392, 199);
		JPanelMain.add(panelInbounds);
		panelInbounds.setLayout(null);

		chckbxInbTLog = new JCheckBox("Transaction Log*");
		chckbxInbTLog.setFont(new Font("Tahoma", Font.PLAIN, 11));
		chckbxInbTLog.setBackground(Color.WHITE);
		chckbxInbTLog.setBounds(268, 106, 113, 23);
		panelInbounds.add(chckbxInbTLog);

		chckbxInbEngFile = new JCheckBox("Engagement*");
		chckbxInbEngFile.setFont(new Font("Tahoma", Font.PLAIN, 11));
		chckbxInbEngFile.setBackground(Color.WHITE);
		chckbxInbEngFile.setBounds(16, 171, 102, 23);
		panelInbounds.add(chckbxInbEngFile);

		chckbxInbBonusFile = new JCheckBox("BonusReward*");
		chckbxInbBonusFile.setFont(new Font("Tahoma", Font.PLAIN, 11));
		chckbxInbBonusFile.setBackground(Color.WHITE);
		chckbxInbBonusFile.setBounds(139, 171, 113, 23);
		panelInbounds.add(chckbxInbBonusFile);

		chckbxAllInbounds = new JCheckBox("All");
		chckbxAllInbounds.setFont(new Font("Tahoma", Font.PLAIN, 11));
		chckbxAllInbounds.setBackground(Color.WHITE);
		chckbxAllInbounds.setBounds(16, 17, 47, 14);
		panelInbounds.add(chckbxAllInbounds);
		chckbxAllInbounds.addActionListener(this);
		chckbxAllInbounds.setActionCommand("chckbxAllInb");

		lblDaily = new JLabel("- Daily -");
		lblDaily.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblDaily.setBounds(16, 38, 56, 14);
		panelInbounds.add(lblDaily);

		chckbxInbStoreMaster = new JCheckBox("Store Master");
		chckbxInbStoreMaster.setFont(new Font("Tahoma", Font.ITALIC, 11));
		chckbxInbStoreMaster.setBackground(Color.WHITE);
		chckbxInbStoreMaster.setBounds(139, 106, 102, 23);
		panelInbounds.add(chckbxInbStoreMaster);

		chckbxInbCardAssociation = new JCheckBox("Card Association");
		chckbxInbCardAssociation.setFont(new Font("Tahoma", Font.ITALIC, 11));
		chckbxInbCardAssociation.setBackground(Color.WHITE);
		chckbxInbCardAssociation.setBounds(16, 54, 121, 23);
		panelInbounds.add(chckbxInbCardAssociation);

		chckbxInbCardDisassociation = new JCheckBox("Card Disassociation");
		chckbxInbCardDisassociation
				.setFont(new Font("Tahoma", Font.ITALIC, 11));
		chckbxInbCardDisassociation.setBackground(Color.WHITE);
		chckbxInbCardDisassociation.setBounds(139, 54, 127, 23);
		panelInbounds.add(chckbxInbCardDisassociation);

		chckbxInbProduct = new JCheckBox("Product");
		chckbxInbProduct.setFont(new Font("Tahoma", Font.ITALIC, 11));
		chckbxInbProduct.setBackground(Color.WHITE);
		chckbxInbProduct.setBounds(16, 106, 78, 23);
		panelInbounds.add(chckbxInbProduct);

		chckbxInbCustomer = new JCheckBox("Customer");
		chckbxInbCustomer.setFont(new Font("Tahoma", Font.ITALIC, 11));
		chckbxInbCustomer.setBackground(Color.WHITE);
		chckbxInbCustomer.setBounds(268, 54, 84, 23);
		panelInbounds.add(chckbxInbCustomer);

		chckbxInbKilllist = new JCheckBox("KillList");
		chckbxInbKilllist.setFont(new Font("Tahoma", Font.ITALIC, 11));
		chckbxInbKilllist.setBackground(Color.WHITE);
		chckbxInbKilllist.setBounds(16, 80, 84, 23);
		panelInbounds.add(chckbxInbKilllist);

		chckbxInbMultiemail = new JCheckBox("Multi Email");
		chckbxInbMultiemail.setFont(new Font("Tahoma", Font.ITALIC, 11));
		chckbxInbMultiemail.setBackground(Color.WHITE);
		chckbxInbMultiemail.setBounds(139, 80, 84, 23);
		panelInbounds.add(chckbxInbMultiemail);

		chckbxInbMultiPhone = new JCheckBox("Multi Phone");
		chckbxInbMultiPhone.setFont(new Font("Tahoma", Font.ITALIC, 11));
		chckbxInbMultiPhone.setBackground(Color.WHITE);
		chckbxInbMultiPhone.setBounds(268, 80, 84, 23);
		panelInbounds.add(chckbxInbMultiPhone);

		chckbxInbUak = new JCheckBox("Use and Kill(coupon)");
		chckbxInbUak.setFont(new Font("Tahoma", Font.ITALIC, 11));
		chckbxInbUak.setBackground(Color.WHITE);
		chckbxInbUak.setBounds(16, 132, 146, 23);
		panelInbounds.add(chckbxInbUak);

		lblAdHoc = new JLabel("- Ad Hoc -");
		lblAdHoc.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblAdHoc.setBounds(16, 158, 56, 14);
		panelInbounds.add(lblAdHoc);

		lblOnlyFilesWith = new JLabel("(Only option with * has regression)");
		lblOnlyFilesWith.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblOnlyFilesWith.setBounds(63, 17, 177, 14);
		panelInbounds.add(lblOnlyFilesWith);

		chckbxInbTerminate = new JCheckBox("Terminate");
		chckbxInbTerminate.setFont(new Font("Tahoma", Font.ITALIC, 11));
		chckbxInbTerminate.setBackground(Color.WHITE);
		chckbxInbTerminate.setBounds(268, 171, 84, 23);
		panelInbounds.add(chckbxInbTerminate);

		btnQuickfile = new JButton("QuickFile");
		btnQuickfile.setFont(new Font("Tahoma", Font.PLAIN, 10));
		btnQuickfile.setActionCommand("Execute");
		btnQuickfile.setBounds(304, 15, 78, 18);
		panelInbounds.add(btnQuickfile);
		btnQuickfile.addActionListener(this);
		btnQuickfile.setActionCommand("QuickFile");

		panelRuleTriggers = new JPanel();
		panelRuleTriggers.setBackground(Color.WHITE);
		panelRuleTriggers.setBorder(new TitledBorder(UIManager
				.getBorder("TitledBorder.border"), "RuleTriggers SmokeTest.",
				TitledBorder.LEADING, TitledBorder.TOP, null,
				new Color(0, 0, 0)));
		panelRuleTriggers.setBounds(10, 341, 392, 64);
		JPanelMain.add(panelRuleTriggers);
		panelRuleTriggers.setLayout(null);

		chckbxBonusRules = new JCheckBox("By BonusReward File");
		chckbxBonusRules.setFont(new Font("Tahoma", Font.PLAIN, 11));
		chckbxBonusRules.setBackground(Color.WHITE);
		chckbxBonusRules.setBounds(139, 36, 133, 23);
		panelRuleTriggers.add(chckbxBonusRules);

		chckbxAllRuleTrgr = new JCheckBox("All");
		chckbxAllRuleTrgr.setFont(new Font("Tahoma", Font.PLAIN, 11));
		chckbxAllRuleTrgr.setBackground(Color.WHITE);
		chckbxAllRuleTrgr.setBounds(16, 17, 50, 16);
		panelRuleTriggers.add(chckbxAllRuleTrgr);
		chckbxAllRuleTrgr.addActionListener(this);
		chckbxAllRuleTrgr.setActionCommand("chckbxAllRuleTrg");

		chckbxApiRules = new JCheckBox("By API");
		chckbxApiRules.setFont(new Font("Tahoma", Font.PLAIN, 11));
		chckbxApiRules.setBackground(Color.WHITE);
		chckbxApiRules.setBounds(268, 36, 79, 23);
		panelRuleTriggers.add(chckbxApiRules);

		chckbxEngRule = new JCheckBox("By Engagement File");
		chckbxEngRule.setFont(new Font("Tahoma", Font.PLAIN, 11));
		chckbxEngRule.setBackground(Color.WHITE);
		chckbxEngRule.setBounds(16, 36, 125, 23);
		panelRuleTriggers.add(chckbxEngRule);

		btnExecute = new JButton("Execute");
		btnExecute.setBounds(83, 561, 89, 23);
		JPanelMain.add(btnExecute);
		btnExecute.addActionListener(this);
		btnExecute.setActionCommand("Execute");

		btnCancel = new JButton("Cancel");
		btnCancel.setBounds(237, 561, 89, 23);
		JPanelMain.add(btnCancel);
		btnCancel.addActionListener(this);
		btnCancel.setActionCommand("Cancel");

		ButtonGroup envRdbtns = new ButtonGroup();

		ButtonGroup TestType = new ButtonGroup();

		ButtonGroup BrowserType = new ButtonGroup();

		panelCSP42 = new JPanel();
		panelCSP42.setBackground(Color.WHITE);
		panelCSP42.setBorder(new TitledBorder(null, "CSPortal v4.2",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelCSP42.setBounds(10, 409, 392, 142);
		JPanelMain.add(panelCSP42);
		panelCSP42.setLayout(null);

		chckbxAllCSP42 = new JCheckBox("All");
		chckbxAllCSP42.setFont(new Font("Tahoma", Font.PLAIN, 11));
		chckbxAllCSP42.setBackground(Color.WHITE);
		chckbxAllCSP42.setActionCommand("chckbxAllRuleTrg");
		chckbxAllCSP42.setBounds(16, 17, 50, 16);
		panelCSP42.add(chckbxAllCSP42);

		chckbxLoginpage42 = new JCheckBox("Login");
		chckbxLoginpage42.setFont(new Font("Tahoma", Font.PLAIN, 11));
		chckbxLoginpage42.setBackground(Color.WHITE);
		chckbxLoginpage42.setBounds(16, 36, 79, 23);
		panelCSP42.add(chckbxLoginpage42);

		chckbxMemberSearch42 = new JCheckBox("Member Search");
		chckbxMemberSearch42.setEnabled(false);
		chckbxMemberSearch42.setFont(new Font("Tahoma", Font.PLAIN, 11));
		chckbxMemberSearch42.setBackground(Color.WHITE);
		chckbxMemberSearch42.setBounds(139, 36, 112, 23);
		panelCSP42.add(chckbxMemberSearch42);

		chckbxAccountSummary42 = new JCheckBox("Account Summary");
		chckbxAccountSummary42.setEnabled(false);
		chckbxAccountSummary42.setFont(new Font("Tahoma", Font.PLAIN, 11));
		chckbxAccountSummary42.setBackground(Color.WHITE);
		chckbxAccountSummary42.setBounds(268, 36, 112, 23);
		panelCSP42.add(chckbxAccountSummary42);

		chckbxAccountActivity42 = new JCheckBox("Account Activity");
		chckbxAccountActivity42.setFont(new Font("Tahoma", Font.PLAIN, 11));
		chckbxAccountActivity42.setEnabled(false);
		chckbxAccountActivity42.setBackground(Color.WHITE);
		chckbxAccountActivity42.setBounds(16, 62, 112, 23);
		panelCSP42.add(chckbxAccountActivity42);

		chckbxMemberProfile42 = new JCheckBox("Member Profile");
		chckbxMemberProfile42.setFont(new Font("Tahoma", Font.PLAIN, 11));
		chckbxMemberProfile42.setEnabled(false);
		chckbxMemberProfile42.setBackground(Color.WHITE);
		chckbxMemberProfile42.setBounds(139, 62, 112, 23);
		panelCSP42.add(chckbxMemberProfile42);

		chckbxMergeAccounts42 = new JCheckBox("Merge Accounts");
		chckbxMergeAccounts42.setFont(new Font("Tahoma", Font.PLAIN, 11));
		chckbxMergeAccounts42.setEnabled(false);
		chckbxMergeAccounts42.setBackground(Color.WHITE);
		chckbxMergeAccounts42.setBounds(268, 62, 112, 23);
		panelCSP42.add(chckbxMergeAccounts42);

		chckbxRewardHistory42 = new JCheckBox("Reward History");
		chckbxRewardHistory42.setFont(new Font("Tahoma", Font.PLAIN, 11));
		chckbxRewardHistory42.setEnabled(false);
		chckbxRewardHistory42.setBackground(Color.WHITE);
		chckbxRewardHistory42.setBounds(16, 88, 112, 23);
		panelCSP42.add(chckbxRewardHistory42);

		chckbxCouponHistory42 = new JCheckBox("Coupon History");
		chckbxCouponHistory42.setFont(new Font("Tahoma", Font.PLAIN, 11));
		chckbxCouponHistory42.setEnabled(false);
		chckbxCouponHistory42.setBackground(Color.WHITE);
		chckbxCouponHistory42.setBounds(139, 88, 112, 23);
		panelCSP42.add(chckbxCouponHistory42);

		chckbxComPreference42 = new JCheckBox("Com Preference");
		chckbxComPreference42.setFont(new Font("Tahoma", Font.PLAIN, 11));
		chckbxComPreference42.setEnabled(false);
		chckbxComPreference42.setBackground(Color.WHITE);
		chckbxComPreference42.setBounds(268, 88, 112, 23);
		panelCSP42.add(chckbxComPreference42);

		chckbxUserAdministration42 = new JCheckBox("User Administration");
		chckbxUserAdministration42.setFont(new Font("Tahoma", Font.PLAIN, 11));
		chckbxUserAdministration42.setEnabled(false);
		chckbxUserAdministration42.setBackground(Color.WHITE);
		chckbxUserAdministration42.setBounds(16, 112, 125, 23);
		panelCSP42.add(chckbxUserAdministration42);

		lblTitle = new JLabel("EXPRESS TEST SUITE");
		lblTitle.setFont(new Font("Calibri", Font.BOLD | Font.ITALIC, 25));
		lblTitle.setBounds(91, 11, 235, 23);
		JPanelMain.add(lblTitle);

		panelEnvironments = new JPanel();
		panelEnvironments.setBackground(Color.WHITE);
		panelEnvironments.setBorder(new LineBorder(new Color(211, 211, 211)));
		panelEnvironments.setBounds(53, 45, 284, 58);
		JPanelMain.add(panelEnvironments);
		panelEnvironments.setLayout(null);

		rdbtnQaA = new JRadioButton("QA-A");
		rdbtnQaA.setFont(new Font("Tahoma", Font.BOLD, 12));
		rdbtnQaA.setBounds(6, 7, 66, 23);
		panelEnvironments.add(rdbtnQaA);
		rdbtnQaA.setBackground(Color.WHITE);
		rdbtnQaA.setSelected(true);
		envRdbtns.add(rdbtnQaA);

		rdbtnQaB = new JRadioButton("QA-B");
		rdbtnQaB.setFont(new Font("Tahoma", Font.BOLD, 12));
		rdbtnQaB.setBounds(108, 7, 78, 23);
		panelEnvironments.add(rdbtnQaB);
		rdbtnQaB.setBackground(Color.WHITE);
		envRdbtns.add(rdbtnQaB);

		rdbtnQaC = new JRadioButton("QA-C");
		rdbtnQaC.setFont(new Font("Tahoma", Font.BOLD, 12));
		rdbtnQaC.setBounds(207, 7, 71, 23);
		panelEnvironments.add(rdbtnQaC);
		rdbtnQaC.setBackground(Color.WHITE);
		envRdbtns.add(rdbtnQaC);

		rdbtnUata = new JRadioButton("UAT-A");
		rdbtnUata.setFont(new Font("Tahoma", Font.BOLD, 12));
		rdbtnUata.setBounds(6, 33, 71, 23);
		panelEnvironments.add(rdbtnUata);
		rdbtnUata.setBackground(Color.WHITE);
		envRdbtns.add(rdbtnUata);

		rdbtnUatb = new JRadioButton("UAT-B");
		rdbtnUatb.setFont(new Font("Tahoma", Font.BOLD, 12));
		rdbtnUatb.setBounds(108, 33, 78, 23);
		panelEnvironments.add(rdbtnUatb);
		rdbtnUatb.setBackground(Color.WHITE);
		envRdbtns.add(rdbtnUatb);

		rdbtnUatc = new JRadioButton("UAT-C");
		rdbtnUatc.setFont(new Font("Tahoma", Font.BOLD, 12));
		rdbtnUatc.setBounds(207, 33, 71, 23);
		panelEnvironments.add(rdbtnUatc);
		rdbtnUatc.setBackground(Color.WHITE);
		envRdbtns.add(rdbtnUatc);

		separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setBackground(new Color(230, 230, 250));
		separator.setBounds(88, 15, 1, 30);
		panelEnvironments.add(separator);

		separator_1 = new JSeparator();
		separator_1.setOrientation(SwingConstants.VERTICAL);
		separator_1.setBackground(new Color(230, 230, 250));
		separator_1.setBounds(192, 15, 1, 30);
		panelEnvironments.add(separator_1);

		JPanel panelConfigs = new JPanel();
		panelConfigs.setBackground(Color.WHITE);
		panelConfigs.setBounds(38, 99, 338, 35);
		JPanelMain.add(panelConfigs);
		panelConfigs.setLayout(null);

		JSeparator separatorConfig = new JSeparator();
		separatorConfig.setBounds(153, 10, 2, 17);
		panelConfigs.add(separatorConfig);
		separatorConfig.setOrientation(SwingConstants.VERTICAL);
		separatorConfig.setBackground(new Color(230, 230, 250));

		rdbtnChrome = new JRadioButton("Chrome");
		rdbtnChrome.setSelected(true);
		rdbtnChrome.setBounds(6, 7, 71, 23);
		panelConfigs.add(rdbtnChrome);
		rdbtnChrome.setBackground(Color.WHITE);
		rdbtnChrome.setHorizontalTextPosition(JRadioButton.LEFT);
		BrowserType.add(rdbtnChrome);

		rdbtnFirefox = new JRadioButton("Firefox");
		rdbtnFirefox.setBounds(81, 7, 64, 23);
		panelConfigs.add(rdbtnFirefox);
		rdbtnFirefox.setBackground(Color.WHITE);
		rdbtnFirefox.setHorizontalTextPosition(JRadioButton.LEFT);
		BrowserType.add(rdbtnFirefox);

		rdbtnRegression = new JRadioButton("Regression*");
		rdbtnRegression.setBounds(233, 7, 98, 23);
		panelConfigs.add(rdbtnRegression);
		rdbtnRegression.setBackground(Color.WHITE);
		rdbtnRegression.setSelected(true);
		TestType.add(rdbtnRegression);

		rdbtnSmoke = new JRadioButton("Smoke");
		rdbtnSmoke.setBounds(165, 7, 71, 23);
		panelConfigs.add(rdbtnSmoke);
		rdbtnSmoke.setBackground(Color.WHITE);
		rdbtnSmoke.setEnabled(false);
		TestType.add(rdbtnSmoke);

		JSeparator separatorTitle = new JSeparator();
		separatorTitle.setBounds(71, 36, 252, 2);
		JPanelMain.add(separatorTitle);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		switch (arg0.getActionCommand()) {
		case "chckbxAllInb":
			SetAllInboundChckboxs(chckbxAllInbounds.isSelected());
			break;
		case "chckbxAllRuleTrg":
			SetAllRuleTriggerChckboxs(chckbxAllRuleTrgr.isSelected());
			break;
		case "Cancel":
			this.dispose();// QuickFile
			break;
		case "QuickFile":
			GenerateQuickFiles();// QuickFile
			break;
		case "Execute":
			try {
				String batchfile = "init.bat";
				File file = new File(System.getProperty("user.dir") + "\\",
						batchfile);
				if (!file.exists()) {
					file.createNewFile();
				}
				FileWriter writer = new FileWriter(file, false);
				BufferedWriter bufferedWriter = new BufferedWriter(writer);
				bufferedWriter.write("@ECHO OFF" + System.lineSeparator());
				bufferedWriter.write(GenerateSentence()
						+ System.lineSeparator());
				// bufferedWriter.write("PAUSE"+System.lineSeparator());
				// bufferedWriter.write("CLS"+System.lineSeparator());
				// bufferedWriter.write("EXIT"+System.lineSeparator());
				bufferedWriter.close();
				Runtime.getRuntime().exec(
						"cmd /c start " + System.getProperty("user.dir") + "\\"
								+ batchfile);
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, e.getMessage());
			}
			break;
		}

	}

	public void SetAllInboundChckboxs(boolean status) {
		chckbxInbTLog.setSelected(status);
		chckbxInbEngFile.setSelected(status);
		chckbxInbBonusFile.setSelected(status);
	}

	public void SetAllRuleTriggerChckboxs(boolean status) {
		chckbxBonusRules.setSelected(status);
		chckbxEngRule.setSelected(status);
		chckbxApiRules.setSelected(status);
	}

	public void GenerateQuickFiles() {
		try {
			JOptionPane.showMessageDialog(null,
					"Generating file(s)...\nPlease wait for success window.",
					"Quick file", JOptionPane.NO_OPTION);
			QuickProcessing qp = new QuickProcessing();
			
			if(rdbtnQaA.isSelected()){qp.env="BPQA01";}
			if(rdbtnQaB.isSelected()){qp.env="BPQA01B";}
			if(rdbtnQaC.isSelected()){qp.env="BPQA01C";}
			if(rdbtnUata.isSelected()){qp.env="BPUAT01";}
			if(rdbtnUatb.isSelected()){qp.env="BPUAT01B";}
			if(rdbtnUatc.isSelected()){qp.env="BPUAT01C";}
			
			qp.UpdateCredentials();
			qp.SetVariables();
			
			//INBOUND
			if(chckbxInbCardAssociation.isSelected()){qp.CardAssocQuickProcess();}			//CARDLOADER ASSOC
			if(chckbxInbCardDisassociation.isSelected()){qp.CardDisassocQuickProcess();}	//CARDLOADER DISASSOC
			if(chckbxInbCustomer.isSelected()){qp.CustomerQuickProcess();}			//CUSTOMER
			if(chckbxInbKilllist.isSelected()){qp.KillListQuickProcess();}			//KILLLIST
			if(chckbxInbMultiemail.isSelected()){qp.MultiEmailQuickProcess();}		//MULTIEMAIL
			if(chckbxInbMultiPhone.isSelected()){qp.MultiPhoneQuickProcess();}		//MULTIPHONE
			if(chckbxInbProduct.isSelected()){qp.ProductQuickProcess();}				//PRODUCT
			if(chckbxInbStoreMaster.isSelected()){qp.StoreQuickProcess();}					//STORE
			if(chckbxInbTLog.isSelected()){qp.TlogQuickProcess();}					//TLOG
			if(chckbxInbUak.isSelected()){qp.UAKQuickProcess();}						//UAK
					
					//SOCIAL ACTIVITY
			if(chckbxInbBonusFile.isSelected()){qp.BonusRewardQuickProcess();}		//BONUSREWARD
			if(chckbxInbEngFile.isSelected()){qp.EngagementQuickProcess();}		//ENGAGEMENT
					
					//ONE TIME FILES
			if(chckbxInbTerminate.isSelected()){qp.TerminateQuickProcess();}	//TERMINATE LID
			
			JOptionPane.showMessageDialog(null,
					"Generating file(s)...\nPlease wait for success window.",
					"Quick file", JOptionPane.NO_OPTION);
			

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Exception. ",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	public String GenerateSentence() {
		String env = "", tests = "", groups = "", browsertype = "";
		// ### ENVIRONMENT ###
		if (rdbtnQaA.isSelected()) {
			env = "BPQA01";
		}
		if (rdbtnQaB.isSelected()) {
			env = "BPQA01B";
		}
		if (rdbtnQaC.isSelected()) {
			env = "BPQA01C";
		}
		if (rdbtnUata.isSelected()) {
			env = "BPUAT01";
		}
		if (rdbtnUatb.isSelected()) {
			env = "BPUAT01B";
		}
		if (rdbtnUatc.isSelected()) {
			env = "BPUAT01C";
		}

		// ######################
		// ####### GROUPS #######
		// ######################
		if (rdbtnSmoke.isSelected()) {
			if (groups.length() == 0) {
				groups = groups + "Smoke";
			} else {
				groups = groups + ",Smoke";
			}
		}
		if (rdbtnRegression.isSelected()) {
			if (groups.length() == 0) {
				groups = groups + "Regression";
			} else {
				groups = groups + ",Regression";
			}
		}

		// ######################
		// ###### BROWSER #######
		// ######################
		if (rdbtnChrome.isSelected()) {
			if (browsertype.length() == 0) {
				browsertype = browsertype + "chrome";
			} else {
				browsertype = browsertype + ",chrome";
			}
		}
		if (rdbtnFirefox.isSelected()) {
			if (browsertype.length() == 0) {
				browsertype = browsertype + "firefox";
			} else {
				browsertype = browsertype + ",firefox";
			}
		}

		// ######################
		// ####### TESTS #######
		// ######################
		/* TLOG */
		if (chckbxInbTLog.isSelected()) {
			if (tests.length() == 0) {
				tests = tests + "TransactionLogTest";
			} else {
				tests = tests + ",TransactionLogTest";
			}
		}
		/* ENGAGEMENT */
		if (chckbxInbEngFile.isSelected()) {
			if (tests.length() == 0) {
				tests = tests + "EngagementFileTest";
			} else {
				tests = tests + ",EngagementFileTest";
			}
		}
		/* BONUSREWARD */
		if (chckbxInbBonusFile.isSelected()) {
			if (tests.length() == 0) {
				tests = tests + "BonusRewardFileTest";
			} else {
				tests = tests + ",BonusRewardFileTest";
			}
		}
		/* LOGIN PAGE V42 */
		if (chckbxLoginpage42.isSelected()) {
			if (tests.length() == 0) {
				tests = tests + "LoginPage42Test";
			} else {
				tests = tests + ",LoginPage42Test";
			}
		}

		/* SMOKE TO RULES */
		boolean IsRuleTriggerSelected = false;
		if (chckbxBonusRules.isSelected()) {
			if (groups.length() == 0) {
				groups = groups + "byBonusFile";
			} else {
				groups = groups + ",byBonusFile";
			}
			IsRuleTriggerSelected = true;
		}
		if (chckbxEngRule.isSelected()) {
			if (groups.length() == 0) {
				groups = groups + "byEngagementFile";
			} else {
				groups = groups + ",byEngagementFile";
			}
			IsRuleTriggerSelected = true;
		}
		if (chckbxApiRules.isSelected()) {
			if (groups.length() == 0) {
				groups = groups + "byAPI";
			} else {
				groups = groups + ",byAPI";
			}
			IsRuleTriggerSelected = true;
		}
		if (IsRuleTriggerSelected) {
			if (tests.length() == 0) {
				tests = tests + "ActiveRulesSmoke";
			} else {
				tests = tests + ",ActiveRulesSmoke";
			}
		}

		return "mvn test -DselectedTests=" + tests + " -Dbrowser="
				+ browsertype + " -Dgroups=" + groups + " -Denvironment=" + env;
	}
}
