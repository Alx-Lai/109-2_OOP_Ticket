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
	private String[] Stations = { "南港", "台北", "板橋", "桃園", "新竹", "苗栗", "台中", "彰化", "雲林", "嘉義", "台南", "左營" };
	private String[] TripType = { "單程", "去回程" };
	private String[] TicketType = { "無", "早鳥", "大學生" };
	private String[] SeatType = {"標準車廂", "商務車廂"};
	private String[] TicketCount= {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
	private int mode = 1;
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
		nowDate = Calendar.getInstance();
		year = nowDate.get(Calendar.YEAR);
		month = nowDate.get(Calendar.MONTH);
		date = nowDate.get(Calendar.DATE);

		frame.setSize(960, 540);
		frame.setLayout(new GridBagLayout());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("高鐵訂票系統");

		/*
		 * JLabel label = new JLabel("" + mode); frame.add(label, getConstraints(1, 3,
		 * 1, 1, 0, 0, GridBagConstraints.CENTER));
		 */
		JButton TimePriceButton = new JButton("時刻表與票價");
		TimePriceButton.setBackground(new Color(153, 153, 153));
		frame.add(TimePriceButton, getConstraints(0, 0, 2, 1, 0, 0, GridBagConstraints.BOTH));
		
		JButton OnlineOrderButton = new JButton("網路訂票");
		OnlineOrderButton.setBackground(new Color(153, 153, 153));
		frame.add(OnlineOrderButton, getConstraints(2, 0, 2, 1, 0, 0, GridBagConstraints.BOTH));

		JButton TicketInfoButton = new JButton("我的票卷");
		TicketInfoButton.setBackground(new Color(153, 153, 153));
		frame.add(TicketInfoButton, getConstraints(4, 0, 2, 1, 0, 0, GridBagConstraints.BOTH));

		JPanel panel1 = new JPanel(new GridBagLayout());

		JLabel startStationLabel = new JLabel("起程站");
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

		JLabel endStationLabel = new JLabel("終點站");
		panel1.add(endStationLabel, getConstraints(5, 0, 1, 1, 0, 0, GridBagConstraints.BOTH));

		JComboBox endStationComboBox = new JComboBox(Stations);
		panel1.add(endStationComboBox, getConstraints(5, 1, 1, 1, 0, 0, GridBagConstraints.BOTH));
		// listener

		JComboBox tripTypeComboBox = new JComboBox(TripType);

		panel1.add(tripTypeComboBox, getConstraints(0, 2, 6, 1, 0, 0, GridBagConstraints.BOTH));

		JLabel goDateLabel = new JLabel("去程日期");
		panel1.add(goDateLabel, getConstraints(0, 3, 4, 1, 0, 0, GridBagConstraints.BOTH));

		JLabel goTimeLabel = new JLabel("去程時間");
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

		JLabel backDateLabel = new JLabel("回程日期");
		panel1.add(backDateLabel, getConstraints(0, 5, 4, 1, 0, 0, GridBagConstraints.BOTH));

		JLabel backTimeLabel = new JLabel("回程時間");
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

		JLabel ticketTypeLabel = new JLabel("適用優惠");
		panel1.add(ticketTypeLabel, getConstraints(0, 7, 6, 1, 0, 0, GridBagConstraints.BOTH));

		JComboBox ticketTypeComboBox = new JComboBox(TicketType);
		panel1.add(ticketTypeComboBox, getConstraints(0, 8, 6, 1, 0, 0, GridBagConstraints.BOTH));

		JButton queryButton = new JButton("查詢");
		queryButton.setBackground(Color.ORANGE);
		panel1.add(queryButton, getConstraints(0, 9, 6, 1, 0, 0, GridBagConstraints.BOTH));

		
		JPanel panel2 = new JPanel(new GridBagLayout());
		
		JLabel Label2_1 = new JLabel("起程站");
		JLabel Label2_2 = new JLabel("到達站");
		JLabel Label2_3 = new JLabel("行程類型");
		JLabel Label2_4 = new JLabel("去程日期");
		JLabel Label2_5 = new JLabel("去程時間");

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
		
		JLabel backDateLabel2 = new JLabel("回程日期");
		JLabel backTimeLabel2 = new JLabel("回程時間");
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

		JLabel Label2_6 = new JLabel("車廂種類");
		JLabel Label2_7 = new JLabel("全票");
		JLabel Label2_8 = new JLabel("孩童票");
		JLabel Label2_9 = new JLabel("愛心票");
		JLabel Label2_10 = new JLabel("敬老票");
		JLabel Label2_11 = new JLabel("大學生優惠票");

		panel2.add(Label2_6, getConstraints(0, 4, 1, 1, 0, 0, GridBagConstraints.BOTH));
		panel2.add(Label2_7, getConstraints(1, 4, 1, 1, 0, 0, GridBagConstraints.BOTH));
		panel2.add(Label2_8, getConstraints(2, 4, 1, 1, 0, 0, GridBagConstraints.BOTH));
		panel2.add(Label2_9, getConstraints(3, 4, 1, 1, 0, 0, GridBagConstraints.BOTH));
		panel2.add(Label2_10, getConstraints(4, 4, 1, 1, 0, 0, GridBagConstraints.BOTH));
		panel2.add(Label2_11, getConstraints(5, 4, 1, 1, 0, 0, GridBagConstraints.BOTH));
		
		JComboBox seatTypeComboBox = new JComboBox(SeatType);
		JComboBox adultCountComboBox = new JComboBox(TicketCount);
		JComboBox kidCountComboBox = new JComboBox(TicketCount);
		JComboBox heartCountComboBox = new JComboBox(TicketCount);
		JComboBox elderCountComboBox = new JComboBox(TicketCount);
		JComboBox collegeCountComboBox = new JComboBox(TicketCount);
		

		panel2.add(seatTypeComboBox, getConstraints(0, 5, 1, 1, 0, 0, GridBagConstraints.BOTH));
		panel2.add(adultCountComboBox, getConstraints(1, 5, 1, 1, 0, 0, GridBagConstraints.BOTH));
		panel2.add(kidCountComboBox, getConstraints(2, 5, 1, 1, 0, 0, GridBagConstraints.BOTH));
		panel2.add(heartCountComboBox, getConstraints(3, 5, 1, 1, 0, 0, GridBagConstraints.BOTH));
		panel2.add(elderCountComboBox, getConstraints(4, 5, 1, 1, 0, 0, GridBagConstraints.BOTH));
		panel2.add(collegeCountComboBox, getConstraints(5, 5, 1, 1, 0, 0, GridBagConstraints.BOTH));
		
		JButton queryButton2 = new JButton("查詢");
		queryButton2.setBackground(Color.ORANGE);
		
		panel2.add(queryButton2, getConstraints(0, 6, 6, 1, 0, 0, GridBagConstraints.BOTH));
		
		backDateLabel.setVisible(false);
		backDatePicker.setVisible(false);
		backTimeLabel.setVisible(false);
		backTimeSpinner.setVisible(false);
		tripTypeComboBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String tripTypeStr = String.valueOf(tripTypeComboBox.getSelectedItem());
				// System.out.println(tripTypeStr);
				if (tripTypeStr.equals("單程")) {
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
				if (tripTypeStr.equals("單程")) {
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
			}
		});
		
		OnlineOrderButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mode = 2;
				panel1.setVisible(false);
				panel2.setVisible(true);
			}
		});
		
		TicketInfoButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mode = 3;
			}
		});
		
		queryButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mode = 4;
			}
		});
		//=====================
		panel1.setVisible(false);
		panel2.setVisible(true);
		
		frame.add(panel1, getConstraints(0, 1, 6, 10, 0, 0, GridBagConstraints.BOTH));
		frame.add(panel2, getConstraints(0, 1, 6, 10, 0, 0, GridBagConstraints.BOTH));

		frame.setVisible(true);
	}
}
