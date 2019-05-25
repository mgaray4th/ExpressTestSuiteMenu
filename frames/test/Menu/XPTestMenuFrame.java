package test.Menu;

import java.awt.EventQueue;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
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

public class XPTestMenuFrame extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel JPanelMain, panelEnv, panelInbounds, panelRuleTriggers;
	private JRadioButton rdbtnQaA, rdbtnQaB, rdbtnQaC, rdbtnUata, rdbtnUatb,
			rdbtnUatc, rdbtnSmoke, rdbtnRegression;
	JCheckBox chckbxTLog, chckbxEngFile;
	private JButton btnExecute, btnCancel;
	private JCheckBox chckbxBonusRFile,chckbxBonusRules,chckbxAllInbounds,chckbxAllRuleTrgr,chckbxApiRules,chckbxEngRule;

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
		setBounds(100, 100, 372, 416);
		JPanelMain = new JPanel();
		JPanelMain.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(JPanelMain);
		JPanelMain.setLayout(null);

		panelEnv = new JPanel();
		panelEnv.setBorder(new TitledBorder(null, "ENVIRONMENTS.",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelEnv.setBounds(21, 11, 278, 84);
		JPanelMain.add(panelEnv);
		panelEnv.setLayout(null);

		rdbtnQaA = new JRadioButton("QA-A");
		rdbtnQaA.setSelected(true);
		rdbtnQaA.setBounds(18, 19, 66, 23);
		panelEnv.add(rdbtnQaA);

		rdbtnQaB = new JRadioButton("QA-B");
		rdbtnQaB.setBounds(103, 19, 78, 23);
		panelEnv.add(rdbtnQaB);

		rdbtnQaC = new JRadioButton("QA-C");
		rdbtnQaC.setBounds(183, 19, 78, 23);
		panelEnv.add(rdbtnQaC);

		rdbtnUata = new JRadioButton("UAT-A");
		rdbtnUata.setBounds(18, 45, 78, 23);
		panelEnv.add(rdbtnUata);

		rdbtnUatb = new JRadioButton("UAT-B");
		rdbtnUatb.setBounds(98, 45, 78, 23);
		panelEnv.add(rdbtnUatb);

		rdbtnUatc = new JRadioButton("UAT-C");
		rdbtnUatc.setBounds(176, 45, 78, 23);
		panelEnv.add(rdbtnUatc);

		panelInbounds = new JPanel();
		panelInbounds.setBorder(new TitledBorder(null, "InboundFiles.",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelInbounds.setBounds(21, 132, 289, 114);
		JPanelMain.add(panelInbounds);
		panelInbounds.setLayout(null);

		chckbxTLog = new JCheckBox("Transaction Log");
		chckbxTLog.setBounds(16, 40, 120, 23);
		panelInbounds.add(chckbxTLog);

		chckbxEngFile = new JCheckBox("Engagement File");
		chckbxEngFile.setBounds(138, 40, 120, 23);
		panelInbounds.add(chckbxEngFile);

		chckbxBonusRFile = new JCheckBox("BonusReward File");
		chckbxBonusRFile.setBounds(16, 66, 120, 23);
		panelInbounds.add(chckbxBonusRFile);

		chckbxAllInbounds = new JCheckBox("All");
		chckbxAllInbounds.setBounds(18, 14, 97, 23);
		panelInbounds.add(chckbxAllInbounds);
		chckbxAllInbounds.addActionListener(this);
		chckbxAllInbounds.setActionCommand("chckbxAllInb");

		rdbtnSmoke = new JRadioButton("Smoke");
		rdbtnSmoke.setEnabled(false);
		rdbtnSmoke.setBounds(58, 102, 78, 23);
		JPanelMain.add(rdbtnSmoke);

		rdbtnRegression = new JRadioButton("Regression");
		rdbtnRegression.setSelected(true);
		rdbtnRegression.setBounds(138, 102, 95, 23);
		JPanelMain.add(rdbtnRegression);

		panelRuleTriggers = new JPanel();
		panelRuleTriggers.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "RuleTriggers SmokeTest.", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelRuleTriggers.setBounds(31, 257, 247, 77);
		JPanelMain.add(panelRuleTriggers);
		panelRuleTriggers.setLayout(null);

		chckbxBonusRules = new JCheckBox("By BonusReward File");
		chckbxBonusRules.setBounds(100, 17, 141, 23);
		panelRuleTriggers.add(chckbxBonusRules);
		
		chckbxAllRuleTrgr = new JCheckBox("All");
		chckbxAllRuleTrgr.setBounds(6, 17, 97, 23);
		panelRuleTriggers.add(chckbxAllRuleTrgr);
		chckbxAllRuleTrgr.addActionListener(this);
		chckbxAllRuleTrgr.setActionCommand("chckbxAllRuleTrg");
		
		chckbxApiRules = new JCheckBox("By API");
		chckbxApiRules.setBounds(6, 43, 79, 23);
		panelRuleTriggers.add(chckbxApiRules);
		
		chckbxEngRule = new JCheckBox("By Engagement File");
		chckbxEngRule.setBounds(100, 43, 141, 23);
		panelRuleTriggers.add(chckbxEngRule);

		btnExecute = new JButton("Execute");
		btnExecute.setBounds(41, 345, 89, 23);
		JPanelMain.add(btnExecute);
		btnExecute.addActionListener(this);
		btnExecute.setActionCommand("Execute");

		btnCancel = new JButton("Cancel");
		btnCancel.setBounds(176, 345, 89, 23);
		JPanelMain.add(btnCancel);
		btnCancel.addActionListener(this);
		btnCancel.setActionCommand("Cancel");

		ButtonGroup envRdbtns = new ButtonGroup();
		envRdbtns.add(rdbtnQaA);
		envRdbtns.add(rdbtnQaB);
		envRdbtns.add(rdbtnQaC);
		envRdbtns.add(rdbtnUata);
		envRdbtns.add(rdbtnUatb);
		envRdbtns.add(rdbtnUatc);

		ButtonGroup TestType = new ButtonGroup();
		TestType.add(rdbtnSmoke);
		TestType.add(rdbtnRegression);
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
			this.dispose();
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
	
	public void SetAllInboundChckboxs(boolean status){
		chckbxTLog.setSelected(status);
		chckbxEngFile.setSelected(status);
		chckbxBonusRFile.setSelected(status);
	}
	
	public void SetAllRuleTriggerChckboxs(boolean status){
		chckbxBonusRules.setSelected(status);
		chckbxEngRule.setSelected(status);
		chckbxApiRules.setSelected(status);
	}

	public String GenerateSentence() throws Exception {
		String env = "", tests = "", groups = "";
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

		// ### GROUPS ######
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

		// ### TEST SET ######
		if (chckbxTLog.isSelected()) {
			if (tests.length() == 0) {
				tests = tests + "TransactionLogTest";
			} else {
				tests = tests + ",TransactionLogTest";
			}
		}
		if (chckbxEngFile.isSelected()) {
			if (tests.length() == 0) {
				tests = tests + "EngagementFileTest";
			} else {
				tests = tests + ",EngagementFileTest";
			}
		}
		if (chckbxBonusRFile.isSelected()) {
			if (tests.length() == 0) {
				tests = tests + "BonusRewardFileTest";
			} else {
				tests = tests + ",BonusRewardFileTest";
			}
		}
		
		/* SMOKE TO RULES*/
		boolean IsRuleTriggerSelected=false;
		if (chckbxBonusRules.isSelected()) {
			if (groups.length() == 0) {
				groups = groups + "BONUSREWARDFILE";
			} else {
				groups = groups + ",BONUSREWARDFILE";
			}
			IsRuleTriggerSelected=true;
		}
		if (chckbxEngRule.isSelected()) {
			if (groups.length() == 0) {
				groups = groups + "ENGAGEMENTFILE";
			} else {
				groups = groups + ",ENGAGEMENTFILE";
			}
			IsRuleTriggerSelected=true;
		}
		if (chckbxApiRules.isSelected()) {
			if (groups.length() == 0) {
				groups = groups + "API";
			} else {
				groups = groups + ",API";
			}
			IsRuleTriggerSelected=true;
		}
		if(IsRuleTriggerSelected){
			if (tests.length() == 0) {
				tests = tests + "ActiveRulesSmoke";
			} else {
				tests = tests + ",ActiveRulesSmoke";
			}
		}

		return "mvn test -DselectedTests=" + tests + " -Dgroups=" + groups
				+ " -Denvironment=" + env;
	}
}
