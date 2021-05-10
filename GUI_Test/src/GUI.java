import java.awt.*;
import java.awt.event.*;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

import javax.swing.*;
import javax.swing.event.*;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

public class GUI {
	private JFrame frame;
	private String[] Stations = { "�n��", "�x�_", "�O��", "���", "�s��", "�]��", "�x��", "����", "���L", "�Ÿq", "�x�n", "����" };
	private String[] TripType = { "��{", "�h�^�{" };
	private String[] TicketType = { "�L", "����", "�j�ǥ�" };
	private String[] SeatType = {"�зǨ��[", "�ӰȨ��["};
	private String[] TicketCountChoice= {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
	private int[][] carQueryResult = //����, �}�l��, �}�l��, ������, ������, �樮��, �樮��, ���
		{
				{838,17,32,17,39,0,7,100},
				{242,17,47,17,54,0,7,80},
				{660,17,51,17,59,0,8,65}
		};
	private String startStation = "�n��";
	private String endStation = "�n��";
	private Date tripDate;
	private int checked = 0; 
	private int mode = 1;
	private int ticketPrice = 1350;
	private String mySeatType = "�зǨ��[";
	private int[] ticketCount = {1, 0, 1}; //����, ����, �j�ǥ�
	private Calendar nowDate;
	private int year,month,date;

	public GUI() {
		frame = new JFrame();
	}

	public GridBagConstraints getConstraints(int gridx, int gridy, int gridwidth, int gridheight, int weightx,
			int weighty, int fill) {
		GridBagConstraints ret = new GridBagConstraints();
		ret.gridx = gridx;
		ret.gridy = gridy;
		ret.gridwidth = gridwidth;
		ret.gridheight = gridheight;
		ret.weightx = weightx;
		ret.weighty = weighty;
		ret.fill = fill;
		return ret;
	}

	public JPanel getTicket() {
		JPanel ret = new JPanel();
		
		return ret;
	}
	
	public void run() {
		/*time = new Date();
		nowDate.setTime(time);
		*/
		tripDate = new Date();
		nowDate = Calendar.getInstance();
		year = nowDate.get(Calendar.YEAR);
		month = nowDate.get(Calendar.MONTH);
		date = nowDate.get(Calendar.DATE);

		frame.setSize(960, 540);
		frame.setLayout(new GridBagLayout());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("���K�q���t��");

		/*
		 * JLabel label = new JLabel("" + mode); frame.add(label, getConstraints(1, 3,
		 * 1, 1, 0, 0, GridBagConstraints.CENTER));
		 */
		JButton TimePriceButton = new JButton("�ɨ��P����");
		TimePriceButton.setBackground(new Color(153, 153, 153));
		frame.add(TimePriceButton, getConstraints(0, 0, 2, 1, 0, 0, GridBagConstraints.BOTH));
		
		JButton OnlineOrderButton = new JButton("�����q��");
		OnlineOrderButton.setBackground(new Color(153, 153, 153));
		frame.add(OnlineOrderButton, getConstraints(2, 0, 2, 1, 0, 0, GridBagConstraints.BOTH));

		JButton TicketInfoButton = new JButton("�ڪ�����");
		TicketInfoButton.setBackground(new Color(153, 153, 153));
		frame.add(TicketInfoButton, getConstraints(4, 0, 2, 1, 0, 0, GridBagConstraints.BOTH));

		JPanel panel1 = new JPanel(new GridBagLayout());

		JLabel startStationLabel = new JLabel("�_�{��");
		panel1.add(startStationLabel, getConstraints(0, 0, 1, 1, 0, 0, GridBagConstraints.BOTH));

		JComboBox startStationComboBox = new JComboBox(Stations);
		panel1.add(startStationComboBox, getConstraints(0, 1, 1, 1, 0, 0, GridBagConstraints.BOTH));
		// listener

		/*
		 * ImageIcon exchangeIcon = new ImageIcon("./exchange.png"); JButton
		 * exchangeStationButton = new JButton(exchangeIcon);
		 * exchangeStationButton.setRolloverIcon(exchangeIcon);
		 * panel1.add(exchangeStationButton, getConstraints(1, 1, 1, 1, 0, 0,
		 * GridBagConstraints.BOTH));
		 */

		JLabel endStationLabel = new JLabel("���I��");
		panel1.add(endStationLabel, getConstraints(5, 0, 1, 1, 0, 0, GridBagConstraints.BOTH));

		JComboBox endStationComboBox = new JComboBox(Stations);
		panel1.add(endStationComboBox, getConstraints(5, 1, 1, 1, 0, 0, GridBagConstraints.BOTH));
		// listener

		JComboBox tripTypeComboBox = new JComboBox(TripType);

		panel1.add(tripTypeComboBox, getConstraints(0, 2, 6, 1, 0, 0, GridBagConstraints.BOTH));

		JLabel goDateLabel = new JLabel("�h�{���");
		panel1.add(goDateLabel, getConstraints(0, 3, 4, 1, 0, 0, GridBagConstraints.BOTH));

		JLabel goTimeLabel = new JLabel("�h�{�ɶ�");
		panel1.add(goTimeLabel, getConstraints(4, 3, 2, 1, 0, 0, GridBagConstraints.BOTH));

		UtilDateModel goModel = new UtilDateModel();
		goModel.setDate(year, month, date);
		goModel.setSelected(true);
		Properties goP = new Properties();
		goP.put("text.today", "Today");
		goP.put("text.month", "Month");
		goP.put("text.year", "Year");
		JDatePanelImpl goDatePanel = new JDatePanelImpl(goModel, goP);
		JDatePickerImpl goDatePicker = new JDatePickerImpl(goDatePanel, new DateLabelFormatter());
		panel1.add(goDatePicker, getConstraints(0, 4, 4, 1, 0, 0, GridBagConstraints.BOTH));

		JSpinner goTimeSpinner = new JSpinner(new SpinnerDateModel());
		JSpinner.DateEditor goTimeEditor = new JSpinner.DateEditor(goTimeSpinner, "HH:mm");
		goTimeSpinner.setEditor(goTimeEditor);
		goTimeSpinner.setValue(new Date());
		panel1.add(goTimeSpinner, getConstraints(4, 4, 2, 1, 0, 0, GridBagConstraints.BOTH));

		JLabel backDateLabel = new JLabel("�^�{���");
		panel1.add(backDateLabel, getConstraints(0, 5, 4, 1, 0, 0, GridBagConstraints.BOTH));

		JLabel backTimeLabel = new JLabel("�^�{�ɶ�");
		panel1.add(backTimeLabel, getConstraints(4, 5, 2, 1, 0, 0, GridBagConstraints.BOTH));

		UtilDateModel backModel = new UtilDateModel();
		backModel.setDate(year, month, date);
		backModel.setSelected(true);
		Properties backP = new Properties();
		backP.put("text.today", "Today");
		backP.put("text.month", "Month");
		backP.put("text.year", "Year");
		JDatePanelImpl backDatePanel = new JDatePanelImpl(backModel, backP);
		JDatePickerImpl backDatePicker = new JDatePickerImpl(backDatePanel, new DateLabelFormatter());
		panel1.add(backDatePicker, getConstraints(0, 6, 4, 1, 0, 0, GridBagConstraints.BOTH));

		JSpinner backTimeSpinner = new JSpinner(new SpinnerDateModel());
		JSpinner.DateEditor backTimeEditor = new JSpinner.DateEditor(backTimeSpinner, "HH:mm");
		backTimeSpinner.setEditor(backTimeEditor);
		backTimeSpinner.setValue(new Date());
		panel1.add(backTimeSpinner, getConstraints(4, 6, 2, 1, 0, 0, GridBagConstraints.BOTH));

		JLabel ticketTypeLabel = new JLabel("�A���u�f");
		panel1.add(ticketTypeLabel, getConstraints(0, 7, 6, 1, 0, 0, GridBagConstraints.BOTH));

		JComboBox ticketTypeComboBox = new JComboBox(TicketType);
		panel1.add(ticketTypeComboBox, getConstraints(0, 8, 6, 1, 0, 0, GridBagConstraints.BOTH));

		JButton queryButton = new JButton("�d��");
		queryButton.setBackground(Color.ORANGE);
		panel1.add(queryButton, getConstraints(0, 9, 6, 1, 0, 0, GridBagConstraints.BOTH));

		
		JPanel panel2 = new JPanel(new GridBagLayout());
		
		JLabel Label2_1 = new JLabel("�_�{��");
		JLabel Label2_2 = new JLabel("��F��");
		JLabel Label2_3 = new JLabel("��{����");
		JLabel Label2_4 = new JLabel("�h�{���");
		JLabel Label2_5 = new JLabel("�h�{�ɶ�");

		panel2.add(Label2_1, getConstraints(0, 0, 1, 1, 0, 0, GridBagConstraints.BOTH));
		panel2.add(Label2_2, getConstraints(1, 0, 1, 1, 0, 0, GridBagConstraints.BOTH));
		panel2.add(Label2_3, getConstraints(2, 0, 1, 1, 0, 0, GridBagConstraints.BOTH));
		panel2.add(Label2_4, getConstraints(3, 0, 1, 1, 0, 0, GridBagConstraints.BOTH));
		panel2.add(Label2_5, getConstraints(4, 0, 1, 1, 0, 0, GridBagConstraints.BOTH));

		JComboBox startStationComboBox2 = new JComboBox(Stations);
		JComboBox endStationComboBox2 = new JComboBox(Stations);
		JComboBox tripTypeComboBox2 = new JComboBox(TripType);
		UtilDateModel goModel2 = new UtilDateModel();
		goModel2.setDate(year, month, date);
		goModel2.setSelected(true);
		Properties goP2 = new Properties();
		goP.put("text.today", "Today");
		goP.put("text.month", "Month");
		goP.put("text.year", "Year");
		JDatePanelImpl goDatePanel2 = new JDatePanelImpl(goModel2, goP2);
		JDatePickerImpl goDatePicker2 = new JDatePickerImpl(goDatePanel2, new DateLabelFormatter());
		JSpinner goTimeSpinner2 = new JSpinner(new SpinnerDateModel());
		JSpinner.DateEditor goTimeEditor2 = new JSpinner.DateEditor(goTimeSpinner2, "HH:mm");
		goTimeSpinner2.setEditor(goTimeEditor2);
		goTimeSpinner2.setValue(new Date());
		
		panel2.add(startStationComboBox2, getConstraints(0, 1, 1, 1, 0, 0, GridBagConstraints.BOTH));
		panel2.add(endStationComboBox2, getConstraints(1, 1, 1, 1, 0, 0, GridBagConstraints.BOTH));
		panel2.add(tripTypeComboBox2, getConstraints(2, 1, 1, 1, 0, 0, GridBagConstraints.BOTH));
		panel2.add(goDatePicker2, getConstraints(3, 1, 1, 1, 0, 0, GridBagConstraints.BOTH));
		panel2.add(goTimeSpinner2, getConstraints(4, 1, 2, 1, 0, 0, GridBagConstraints.BOTH));		
		JLabel backDateLabel2 = new JLabel("�^�{���");
		JLabel backTimeLabel2 = new JLabel("�^�{�ɶ�");
		UtilDateModel backModel2 = new UtilDateModel();
		backModel2.setDate(year, month, date);
		backModel2.setSelected(true);
		Properties backP2 = new Properties();
		backP2.put("text.today", "Today");
		backP2.put("text.month", "Month");
		backP2.put("text.year", "Year");
		JDatePanelImpl backDatePanel2 = new JDatePanelImpl(backModel2, backP2);
		JDatePickerImpl backDatePicker2 = new JDatePickerImpl(backDatePanel2, new DateLabelFormatter());
		
		JSpinner backTimeSpinner2 = new JSpinner(new SpinnerDateModel());
		JSpinner.DateEditor backTimeEditor2 = new JSpinner.DateEditor(backTimeSpinner2, "HH:mm");
		backTimeSpinner2.setEditor(backTimeEditor2);
		backTimeSpinner2.setValue(new Date());
		
		
		
		panel2.add(backDateLabel2, getConstraints(3, 2, 1, 1, 0, 0, GridBagConstraints.BOTH));		
		panel2.add(backTimeLabel2, getConstraints(4, 2, 1, 1, 0, 0, GridBagConstraints.BOTH));
		panel2.add(backDatePicker2, getConstraints(3, 3, 1, 1, 0, 0, GridBagConstraints.BOTH));
		panel2.add(backTimeSpinner2, getConstraints(4, 3, 2, 1, 0, 0, GridBagConstraints.BOTH));

		JLabel Label2_6 = new JLabel("���[����");
		JLabel Label2_7 = new JLabel("����");
		JLabel Label2_8 = new JLabel("�ĵ���");
		JLabel Label2_9 = new JLabel("�R�߲�");
		JLabel Label2_10 = new JLabel("�q�Ѳ�");
		JLabel Label2_11 = new JLabel("�j�ǥ��u�f��");

		panel2.add(Label2_6, getConstraints(0, 4, 1, 1, 0, 0, GridBagConstraints.BOTH));
		panel2.add(Label2_7, getConstraints(1, 4, 1, 1, 0, 0, GridBagConstraints.BOTH));
		panel2.add(Label2_8, getConstraints(2, 4, 1, 1, 0, 0, GridBagConstraints.BOTH));
		panel2.add(Label2_9, getConstraints(3, 4, 1, 1, 0, 0, GridBagConstraints.BOTH));
		panel2.add(Label2_10, getConstraints(4, 4, 1, 1, 0, 0, GridBagConstraints.BOTH));
		panel2.add(Label2_11, getConstraints(5, 4, 1, 1, 0, 0, GridBagConstraints.BOTH));
		
		JComboBox seatTypeComboBox = new JComboBox(SeatType);
		JComboBox adultCountComboBox = new JComboBox(TicketCountChoice);
		JComboBox kidCountComboBox = new JComboBox(TicketCountChoice);
		JComboBox heartCountComboBox = new JComboBox(TicketCountChoice);
		JComboBox elderCountComboBox = new JComboBox(TicketCountChoice);
		JComboBox collegeCountComboBox = new JComboBox(TicketCountChoice);
		

		panel2.add(seatTypeComboBox, getConstraints(0, 5, 1, 1, 0, 0, GridBagConstraints.BOTH));
		panel2.add(adultCountComboBox, getConstraints(1, 5, 1, 1, 0, 0, GridBagConstraints.BOTH));
		panel2.add(kidCountComboBox, getConstraints(2, 5, 1, 1, 0, 0, GridBagConstraints.BOTH));
		panel2.add(heartCountComboBox, getConstraints(3, 5, 1, 1, 0, 0, GridBagConstraints.BOTH));
		panel2.add(elderCountComboBox, getConstraints(4, 5, 1, 1, 0, 0, GridBagConstraints.BOTH));
		panel2.add(collegeCountComboBox, getConstraints(5, 5, 1, 1, 0, 0, GridBagConstraints.BOTH));
		
		JButton queryButton2 = new JButton("�d��");
		queryButton2.setBackground(Color.ORANGE);
		
		panel2.add(queryButton2, getConstraints(0, 6, 6, 1, 0, 0, GridBagConstraints.BOTH));
		
		
		
		JPanel panel3 = new JPanel(new GridBagLayout());
		JLabel label3_0 = new JLabel("�h�{�G"+startStation+" - "+ endStation + "  "+ String.format("%02d", tripDate.getMonth()+1)+"/"+String.format("%02d", tripDate.getDate()));
		JLabel label3_1 = new JLabel("���");
		JLabel label3_2 = new JLabel("����");
		JLabel label3_3 = new JLabel("�����u�f*");
		JLabel label3_4 = new JLabel("�X�o�ɶ�");
		JLabel label3_5 = new JLabel("��F�ɶ�");
		JLabel label3_6 = new JLabel("�樮�ɶ�");
		JLabel label3_7 = new JLabel("�q�����");
		label3_0.setForeground(Color.ORANGE);
		label3_7.setForeground(Color.ORANGE);
		
		panel3.add(label3_0, getConstraints(0, 0, 6, 2, 0, 0, GridBagConstraints.NORTHWEST));
		panel3.add(label3_1, getConstraints(0, 2, 1, 1, 0, 0, GridBagConstraints.BOTH));
		panel3.add(label3_2, getConstraints(1, 2, 1, 1, 0, 0, GridBagConstraints.EAST));
		panel3.add(label3_3, getConstraints(2, 2, 1, 1, 0, 0, GridBagConstraints.BOTH));
		panel3.add(label3_4, getConstraints(3, 2, 1, 1, 0, 0, GridBagConstraints.BOTH));
		panel3.add(label3_5, getConstraints(4, 2, 1, 1, 0, 0, GridBagConstraints.BOTH));
		panel3.add(label3_6, getConstraints(5, 2, 1, 1, 0, 0, GridBagConstraints.BOTH));
		
		JCheckBox[] checkboxList = new JCheckBox[100];
		JLabel[] carNumberList = new JLabel[100];
		JLabel[] discountList = new JLabel[100];
		JLabel[] goTimeList = new JLabel[100];
		JLabel[] arriveTimeList = new JLabel[100];
		JLabel[] totalTimeList = new JLabel[100];
		
		/*
		 * ����	�����u�f*	�X�o�ɶ�	��F�ɶ�	�樮�ɶ�
		 */
		for(int i=0;i<carQueryResult.length;i++) {
			checkboxList[i] = new JCheckBox();
			carNumberList[i] = new JLabel(carQueryResult[i][0]+"");
			goTimeList[i] = new JLabel(String.format("%02d",carQueryResult[i][1])+":"+String.format("%02d",carQueryResult[i][2]));
			arriveTimeList[i] = new JLabel(String.format("%02d",carQueryResult[i][3])+":"+String.format("%02d",carQueryResult[i][4]));
			totalTimeList[i] = new JLabel(String.format("%02d",carQueryResult[i][5])+":"+String.format("%02d",carQueryResult[i][6]));
			if(carQueryResult[i][7] == 100) {
				discountList[i] = new JLabel();
			}else {
				discountList[i] = new JLabel(carQueryResult[i][7]+"��");
			}

			panel3.add(checkboxList[i], getConstraints(0, i+3, 1, 1, 0, 0, GridBagConstraints.EAST));
			panel3.add(carNumberList[i], getConstraints(1, i+3, 1, 1, 0, 0, GridBagConstraints.EAST));
			panel3.add(discountList[i], getConstraints(2, i+3, 1, 1, 0, 0, GridBagConstraints.EAST));
			panel3.add(goTimeList[i], getConstraints(3, i+3, 1, 1, 0, 0, GridBagConstraints.EAST));
			panel3.add(arriveTimeList[i], getConstraints(4, i+3, 1, 1, 0, 0, GridBagConstraints.EAST));
			panel3.add(totalTimeList[i], getConstraints(5, i+3, 1, 1, 0, 0, GridBagConstraints.EAST));
		}
		checkboxList[0].setSelected(true);
		

		panel3.add(label3_7, getConstraints(0, carQueryResult.length+3, 6, 2, 0, 0, GridBagConstraints.SOUTHWEST));
		//��{	���	����	�_�{��	��F��	�X�o�ɶ�	��F�ɶ�
		
		JLabel label3_8 = new JLabel("��{");
		JLabel label3_9 = new JLabel("���");
		JLabel label3_10 = new JLabel("����");
		JLabel label3_11 = new JLabel("�_�{��");
		JLabel label3_12 = new JLabel("��F��");
		JLabel label3_13 = new JLabel("�X�o�ɶ�");
		JLabel label3_14 = new JLabel("��F�ɶ�");
		
		panel3.add(label3_8, getConstraints(0, carQueryResult.length+5, 1, 1, 0, 0, GridBagConstraints.BOTH));
		panel3.add(label3_9, getConstraints(1, carQueryResult.length+5, 1, 1, 0, 0, GridBagConstraints.BOTH));
		panel3.add(label3_10, getConstraints(2, carQueryResult.length+5, 1, 1, 0, 0, GridBagConstraints.EAST));
		panel3.add(label3_11, getConstraints(3, carQueryResult.length+5, 1, 1, 0, 0, GridBagConstraints.BOTH));
		panel3.add(label3_12, getConstraints(4, carQueryResult.length+5, 1, 1, 0, 0, GridBagConstraints.BOTH));
		panel3.add(label3_13, getConstraints(5, carQueryResult.length+5, 1, 1, 0, 0, GridBagConstraints.BOTH));
		panel3.add(label3_14, getConstraints(6, carQueryResult.length+5, 1, 1, 0, 0, GridBagConstraints.BOTH));
		
		JLabel label3_15 = new JLabel("�h�{");
		JLabel label3_16 = new JLabel(String.format("%02d", tripDate.getMonth()+1)+"/"+String.format("%02d", tripDate.getDate()));
		JLabel label3_17 = new JLabel(""+carQueryResult[checked][0]);
		JLabel label3_18 = new JLabel(""+startStation);
		JLabel label3_19 = new JLabel(""+endStation);
		JLabel label3_20 = new JLabel(String.format("%02d",carQueryResult[checked][1])+":"+String.format("%02d",carQueryResult[checked][2]));
		JLabel label3_21 = new JLabel(String.format("%02d",carQueryResult[checked][3])+":"+String.format("%02d",carQueryResult[checked][4]));
		
		panel3.add(label3_15, getConstraints(0, carQueryResult.length+6, 1, 1, 0, 0, GridBagConstraints.BOTH));
		panel3.add(label3_16, getConstraints(1, carQueryResult.length+6, 1, 1, 0, 0, GridBagConstraints.BOTH));
		panel3.add(label3_17, getConstraints(2, carQueryResult.length+6, 1, 1, 0, 0, GridBagConstraints.EAST));
		panel3.add(label3_18, getConstraints(3, carQueryResult.length+6, 1, 1, 0, 0, GridBagConstraints.BOTH));
		panel3.add(label3_19, getConstraints(4, carQueryResult.length+6, 1, 1, 0, 0, GridBagConstraints.BOTH));
		panel3.add(label3_20, getConstraints(5, carQueryResult.length+6, 1, 1, 0, 0, GridBagConstraints.BOTH));
		panel3.add(label3_21, getConstraints(6, carQueryResult.length+6, 1, 1, 0, 0, GridBagConstraints.BOTH));
		
		String tmp = "";
		if(ticketCount[0] > 0) {
			tmp += "����"+ticketCount[0]+"�i";
		}
		if(ticketCount[1] > 0) {
			tmp += " ����"+ticketCount[1]+"�i";
		}
		if(ticketCount[2] > 0) {
			tmp += " �j�ǥ�"+ticketCount[2]+"�i";
		}
		
		JLabel label3_22 = new JLabel(mySeatType);
		JLabel label3_23 = new JLabel(tmp);
		
		panel3.add(label3_22, getConstraints(0, carQueryResult.length+7, 1, 1, 0, 0, GridBagConstraints.BOTH));
		panel3.add(label3_23, getConstraints(1, carQueryResult.length+7, 1, 1, 0, 0, GridBagConstraints.BOTH));

		JButton button3_1 = new JButton("���s�d��");
		JButton button3_2 = new JButton("�T�{����");
		button3_1.setBackground(new Color(153,153,153));
		button3_2.setBackground(Color.ORANGE);
		
		panel3.add(button3_1, getConstraints(0, carQueryResult.length+8, 2, 1, 0, 0, GridBagConstraints.BOTH));
		panel3.add(button3_2, getConstraints(5, carQueryResult.length+8, 2, 1, 0, 0, GridBagConstraints.BOTH));
		
		
		JPanel panel4 = new JPanel(new GridBagLayout());

		JLabel label4_1 = new JLabel("�q�����");
		JLabel label4_2 = new JLabel("��{");
		JLabel label4_3 = new JLabel("���");
		JLabel label4_4 = new JLabel("����");
		JLabel label4_5 = new JLabel("�_�{��");
		JLabel label4_6 = new JLabel("��F��");
		JLabel label4_7 = new JLabel("�X�o�ɶ�");
		JLabel label4_8 = new JLabel("��F�ɶ�");
		JLabel label4_9 = new JLabel("����");
		JLabel label4_10 = new JLabel("����, �j�ǥ�");
		JLabel label4_11 = new JLabel("�p�p");
		label4_1.setForeground(Color.ORANGE);
		
		panel4.add(label4_1, getConstraints(0, 0, 1, 1, 0, 0, GridBagConstraints.BOTH));
		panel4.add(label4_2, getConstraints(0, 1, 1, 1, 0, 0, GridBagConstraints.BOTH));
		panel4.add(label4_3, getConstraints(1, 1, 1, 1, 0, 0, GridBagConstraints.BOTH));
		panel4.add(label4_4, getConstraints(2, 1, 1, 1, 0, 0, GridBagConstraints.BOTH));
		panel4.add(label4_5, getConstraints(3, 1, 1, 1, 0, 0, GridBagConstraints.BOTH));
		panel4.add(label4_6, getConstraints(4, 1, 1, 1, 0, 0, GridBagConstraints.BOTH));
		panel4.add(label4_7, getConstraints(5, 1, 1, 1, 0, 0, GridBagConstraints.BOTH));
		panel4.add(label4_8, getConstraints(6, 1, 1, 1, 0, 0, GridBagConstraints.BOTH));
		panel4.add(label4_9, getConstraints(7, 1, 1, 1, 0, 0, GridBagConstraints.BOTH));
		panel4.add(label4_10, getConstraints(8, 1, 1, 1, 0, 0, GridBagConstraints.BOTH));
		panel4.add(label4_11, getConstraints(9, 1, 1, 1, 0, 0, GridBagConstraints.BOTH));
		
		JLabel label4_12 = new JLabel("�h�{");
		JLabel label4_13 = new JLabel(String.format("%02d", tripDate.getMonth()+1)+"/"+String.format("%02d", tripDate.getDate()));
		JLabel label4_14 = new JLabel(""+carQueryResult[checked][0]);//����, �}�l��, �}�l��, ������, ������, �樮��, �樮��, ���
		JLabel label4_15 = new JLabel(""+startStation);
		JLabel label4_16 = new JLabel(""+endStation);
		JLabel label4_17 = new JLabel(String.format("%02d",carQueryResult[checked][1])+":"+String.format("%02d",carQueryResult[checked][2]));
		JLabel label4_18 = new JLabel(String.format("%02d",carQueryResult[checked][3])+":"+String.format("%02d",carQueryResult[checked][4]));
		JLabel label4_19 = new JLabel(""+ticketCount[0]);
		JLabel label4_20 = new JLabel(""+ticketCount[1]+", "+ticketCount[2]);
		JLabel label4_21 = new JLabel("TWD"+ticketPrice);
		
		panel4.add(label4_12, getConstraints(0, 2, 1, 1, 0, 0, GridBagConstraints.BOTH));
		panel4.add(label4_13, getConstraints(1, 2, 1, 1, 0, 0, GridBagConstraints.BOTH));
		panel4.add(label4_14, getConstraints(2, 2, 1, 1, 0, 0, GridBagConstraints.BOTH));
		panel4.add(label4_15, getConstraints(3, 2, 1, 1, 0, 0, GridBagConstraints.BOTH));
		panel4.add(label4_16, getConstraints(4, 2, 1, 1, 0, 0, GridBagConstraints.BOTH));
		panel4.add(label4_17, getConstraints(5, 2, 1, 1, 0, 0, GridBagConstraints.BOTH));
		panel4.add(label4_18, getConstraints(6, 2, 1, 1, 0, 0, GridBagConstraints.BOTH));
		panel4.add(label4_19, getConstraints(7, 2, 1, 1, 0, 0, GridBagConstraints.BOTH));
		panel4.add(label4_20, getConstraints(8, 2, 1, 1, 0, 0, GridBagConstraints.EAST));
		panel4.add(label4_21, getConstraints(9, 2, 1, 1, 0, 0, GridBagConstraints.BOTH));
		

		JLabel label4_22 = new JLabel("���[: "+mySeatType);
		tmp = "";
		if(ticketCount[0] > 0) {
			tmp += "����"+ticketCount[0]+"�i";
		}
		if(ticketCount[1] > 0) {
			tmp += " ����"+ticketCount[1]+"�i";
		}
		if(ticketCount[2] > 0) {
			tmp += " �j�ǥ�"+ticketCount[2]+"�i";
		}
		JLabel label4_23 = new JLabel("����: "+ tmp);
		JLabel label4_24 = new JLabel("�`���� TWD" + ticketPrice);
		label4_24.setForeground(Color.RED);
		panel4.add(label4_22, getConstraints(0, 3, 1, 1, 0, 0, GridBagConstraints.BOTH));
		panel4.add(label4_23, getConstraints(1, 3, 1, 1, 0, 0, GridBagConstraints.EAST));
		panel4.add(label4_24, getConstraints(9, 3, 1, 1, 0, 0, GridBagConstraints.BOTH));
		
		JLabel label4_25 = new JLabel("�����H��T");
		JLabel label4_26 = new JLabel("�����Ҹ��X");
		JTextArea textarea4_1 = new JTextArea();
		label4_25.setForeground(Color.ORANGE);
		panel4.add(label4_25, getConstraints(0, 4, 1, 1, 0, 0, GridBagConstraints.BOTH));
		panel4.add(label4_26, getConstraints(0, 5, 1, 1, 0, 0, GridBagConstraints.BOTH));
		panel4.add(textarea4_1, getConstraints(1, 5, 1, 1, 0, 0, GridBagConstraints.BOTH));
		
		JButton button4_1 = new JButton("����");
		JButton button4_2 = new JButton("�����q��");
		button4_2.setBackground(Color.ORANGE);
		panel4.add(button4_1, getConstraints(0, 6, 1, 1, 0, 0, GridBagConstraints.BOTH));
		panel4.add(button4_2, getConstraints(9, 6, 1, 1, 0, 0, GridBagConstraints.BOTH));
		
		JPanel panel5 = new JPanel(new GridBagLayout());
		
		JLabel label5_1 = new JLabel("�����q��");
		
		JButton button5_1 = new JButton("��^");

		panel5.add(label5_1, getConstraints(0, 0, 1, 1, 0, 0, GridBagConstraints.BOTH));
		panel5.add(button5_1, getConstraints(0, 1, 1, 1, 0, 0, GridBagConstraints.BOTH));
		
		
		
		backDateLabel.setVisible(false);
		backDatePicker.setVisible(false);
		backTimeLabel.setVisible(false);
		backTimeSpinner.setVisible(false);
		tripTypeComboBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String tripTypeStr = String.valueOf(tripTypeComboBox.getSelectedItem());
				// System.out.println(tripTypeStr);
				if (tripTypeStr.equals("��{")) {
					backDateLabel.setVisible(false);
					backDatePicker.setVisible(false);
					backTimeLabel.setVisible(false);
					backTimeSpinner.setVisible(false);
				} else {
					backDateLabel.setVisible(true);
					backDatePicker.setVisible(true);
					backTimeLabel.setVisible(true);
					backTimeSpinner.setVisible(true);

				}
			}
		});
		
		
		backDateLabel2.setVisible(false);
		backDatePicker2.setVisible(false);
		backTimeLabel2.setVisible(false);
		backTimeSpinner2.setVisible(false);
		tripTypeComboBox2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String tripTypeStr = String.valueOf(tripTypeComboBox2.getSelectedItem());
				// System.out.println(tripTypeStr);
				if (tripTypeStr.equals("��{")) {
					backDateLabel2.setVisible(false);
					backDatePicker2.setVisible(false);
					backTimeLabel2.setVisible(false);
					backTimeSpinner2.setVisible(false);
				} else {
					backDateLabel2.setVisible(true);
					backDatePicker2.setVisible(true);
					backTimeLabel2.setVisible(true);
					backTimeSpinner2.setVisible(true);

				}
			}
		});
		
		TimePriceButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mode = 1;
				panel1.setVisible(true);
				panel2.setVisible(false);
				panel3.setVisible(false);
				panel4.setVisible(false);
				panel5.setVisible(false);
			}
		});
		
		OnlineOrderButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mode = 2;
				panel1.setVisible(false);
				panel2.setVisible(true);
				panel3.setVisible(false);
				panel4.setVisible(false);
				panel5.setVisible(false);
			}
		});
		
		TicketInfoButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mode = 3;
				panel1.setVisible(false);
				panel2.setVisible(false);
				panel3.setVisible(true);
				panel4.setVisible(false);
				panel5.setVisible(false);
			}
		});
		
		queryButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mode = 4;
			}
		});
		
		queryButton2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				panel1.setVisible(false);
				panel2.setVisible(false);
				panel3.setVisible(true);
				panel4.setVisible(false);
				panel5.setVisible(false);
			}
		});;
		
		for(int i=0;i<carQueryResult.length;i++) {
			checkboxList[i].addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					for(int j=0;j<carQueryResult.length;j++) {
						if(e.getSource() == checkboxList[j]) {
							checkboxList[j].setSelected(true);
							checked = j;
							label3_16.setText(String.format("%02d", tripDate.getMonth()+1)+"/"+String.format("%02d", tripDate.getDate()));
							label3_17.setText(""+carQueryResult[checked][0]);
							label3_20.setText(String.format("%02d",carQueryResult[checked][1])+":"+String.format("%02d",carQueryResult[checked][2]));
							label3_21.setText(String.format("%02d",carQueryResult[checked][3])+":"+String.format("%02d",carQueryResult[checked][4]));
							
							break;
						}
					}
					for(int j=0;j<carQueryResult.length;j++) {
						if(j != checked) {
							checkboxList[j].setSelected(false);
						}
					}
				}
			});
		}
		
		button4_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				panel1.setVisible(true);
				panel2.setVisible(false);
				panel3.setVisible(false);
				panel4.setVisible(false);
				panel5.setVisible(false);
			}
		});
		
		button4_2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				panel1.setVisible(false);
				panel2.setVisible(false);
				panel3.setVisible(false);
				panel4.setVisible(false);
				panel5.setVisible(true);
			}
		});
		
		button3_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				panel1.setVisible(false);
				panel2.setVisible(true);
				panel3.setVisible(false);
				panel4.setVisible(false);
				panel5.setVisible(false);
			}
		});
		button3_2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				panel1.setVisible(false);
				panel2.setVisible(false);
				panel3.setVisible(false);
				panel4.setVisible(true);
				panel5.setVisible(false);
			}
		});
		
		button5_1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				panel1.setVisible(true);
				panel2.setVisible(false);
				panel3.setVisible(false);
				panel4.setVisible(false);
				panel5.setVisible(false);
				
			}
		});
		
		//=====================
		panel1.setVisible(false);
		panel2.setVisible(false);
		panel3.setVisible(false);
		panel4.setVisible(false);
		panel5.setVisible(true);
		
		frame.add(panel1, getConstraints(0, 1, 6, 10, 0, 0, GridBagConstraints.BOTH));
		frame.add(panel2, getConstraints(0, 1, 6, 10, 0, 0, GridBagConstraints.BOTH));
		frame.add(panel3, getConstraints(0, 1, 6, 10, 0, 0, GridBagConstraints.BOTH));
		frame.add(panel4, getConstraints(0, 1, 6, 10, 0, 0, GridBagConstraints.BOTH));
		frame.add(panel5, getConstraints(0, 1, 6, 10, 0, 0, GridBagConstraints.BOTH));

		frame.setVisible(true);
	}
}
