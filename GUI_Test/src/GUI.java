import java.awt.*;
import java.awt.event.*;
import java.util.Date;
import java.util.Properties;

import javax.swing.*;
import javax.swing.event.*;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;


public class GUI{
    private JFrame frame;
    private String[] Stations = {"南港", "台北", "板橋", "桃園", "新竹", "苗栗", "台中", "彰化", "雲林", "嘉義", "台南", "左營"};
    private String[] TripType = {"單程", "去回程"};
    private String[] TicketType = {"無", "早鳥", "大學生"};
    private int mode = 1;

    public GUI(){
        frame = new JFrame();
    }
    public GridBagConstraints getConstraints(int gridx, int gridy, int gridwidth, int gridheight, int weightx, int weighty, int fill){
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
    public void run(){
        frame.setSize(1280, 720);
        frame.setLayout(new GridBagLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("高鐵訂票系統");
        
        /*
        JLabel label = new JLabel("" + mode);
        frame.add(label, getConstraints(1, 3, 1, 1, 0, 0, GridBagConstraints.CENTER));
		*/
        JButton TimePriceButton = new JButton("時刻表與票價");
        TimePriceButton.setBackground(new Color(102, 102, 102));
        TimePriceButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                mode = 1;
                //label.setText("" + mode);
            }
        });
        frame.add(TimePriceButton, getConstraints(0, 0, 3, 1, 0, 0, GridBagConstraints.BOTH));

        /*
        JButton OnlineOrderButton = new JButton("網路訂票");
		OnlineOrderButton.setBackground(new Color(102, 102, 102));
        OnlineOrderButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                mode = 2;
                //label.setText("" + mode);
            }
        });
        frame.add(OnlineOrderButton, getConstraints(1, 0, 1, 1, 0, 0, GridBagConstraints.BOTH));
        

        JButton SellInfoButton = new JButton("銷售資訊");
        SellInfoButton.setBackground(new Color(102, 102, 102));
        SellInfoButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                mode = 3;
                //label.setText("" + mode);
            }
        });
        frame.add(SellInfoButton, getConstraints(2, 0, 1, 1, 0, 0, GridBagConstraints.BOTH));
		*/

        JButton TicketInfoButton = new JButton("我的票卷");
		TicketInfoButton.setBackground(new Color(102, 102, 102));
        TicketInfoButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                mode = 2;
                //label.setText("" + mode);
            }
        });
        frame.add(TicketInfoButton, getConstraints(3, 0, 3, 1, 0, 0, GridBagConstraints.BOTH));
        

        JPanel panel = new JPanel(new GridBagLayout());
        
        JLabel startStationLabel = new JLabel("起程站");
        panel.add(startStationLabel, getConstraints(0, 0, 3, 1, 0, 0, GridBagConstraints.BOTH));

        JComboBox startStationComboBox = new JComboBox(Stations);
        panel.add(startStationComboBox, getConstraints(0, 1, 3, 1, 0, 0, GridBagConstraints.BOTH));
        //listener

        
        /*
        ImageIcon exchangeIcon = new ImageIcon("./exchange.png");
        JButton exchangeStationButton = new JButton(exchangeIcon);
        exchangeStationButton.setRolloverIcon(exchangeIcon);
        panel.add(exchangeStationButton, getConstraints(1, 1, 1, 1, 0, 0, GridBagConstraints.BOTH));
        */
   
        JLabel endStationLabel = new JLabel("終點站");
        panel.add(endStationLabel, getConstraints(3, 0, 3, 1, 0, 0, GridBagConstraints.BOTH));

        JComboBox endStationComboBox = new JComboBox(Stations);
        panel.add(endStationComboBox, getConstraints(3, 1, 3, 1, 0, 0, GridBagConstraints.BOTH));
        //listener

        JComboBox tripTypeComboBox = new JComboBox(TripType);
        panel.add(tripTypeComboBox, getConstraints(0, 2, 6, 1, 0, 0, GridBagConstraints.BOTH));

        JLabel goDateLabel = new JLabel("去程日期");
        panel.add(goDateLabel, getConstraints(0, 3, 4, 1, 0, 0, GridBagConstraints.BOTH));

        JLabel goTimeLabel = new JLabel("去程時間");
        panel.add(goTimeLabel, getConstraints(4, 3, 2, 1, 0, 0, GridBagConstraints.BOTH));

        UtilDateModel goModel = new UtilDateModel();
        goModel.setDate(20,04,2014);
        // Need this...
        Properties goP = new Properties();
        goP.put("text.today", "Today");
        goP.put("text.month", "Month");
        goP.put("text.year", "Year");
        JDatePanelImpl goDatePanel = new JDatePanelImpl(goModel, goP);
        // Don't know about the formatter, but there it is...
        JDatePickerImpl goDatePicker = new JDatePickerImpl(goDatePanel, new DateLabelFormatter());
        panel.add(goDatePicker, getConstraints(0, 4, 4, 1, 0, 0, GridBagConstraints.BOTH));
        
        JSpinner goTimeSpinner = new JSpinner( new SpinnerDateModel() );
        JSpinner.DateEditor goTimeEditor = new JSpinner.DateEditor(goTimeSpinner, "HH:mm:ss");
        goTimeSpinner.setEditor(goTimeEditor);
        goTimeSpinner.setValue(new Date());
        panel.add(goTimeSpinner, getConstraints(4, 4, 2, 1, 0, 0, GridBagConstraints.BOTH));
        
        JLabel backDateLabel = new JLabel("回程日期");
        panel.add(backDateLabel, getConstraints(0, 5, 4, 1, 0, 0, GridBagConstraints.BOTH));

        JLabel backTimeLabel = new JLabel("回程時間");
        panel.add(backTimeLabel, getConstraints(4, 5, 2, 1, 0, 0, GridBagConstraints.BOTH));

        UtilDateModel backModel = new UtilDateModel();
        backModel.setDate(20,04,2014);
        // Need this...
        Properties backP = new Properties();
        backP.put("text.today", "Today");
        backP.put("text.month", "Month");
        backP.put("text.year", "Year");
        JDatePanelImpl backDatePanel = new JDatePanelImpl(backModel, backP);
        // Don't know about the formatter, but there it is...
        JDatePickerImpl backDatePicker = new JDatePickerImpl(backDatePanel, new DateLabelFormatter());
        panel.add(backDatePicker, getConstraints(0, 6, 4, 1, 0, 0, GridBagConstraints.BOTH));
        
        JSpinner backTimeSpinner = new JSpinner( new SpinnerDateModel() );
        JSpinner.DateEditor backTimeEditor = new JSpinner.DateEditor(backTimeSpinner, "HH:mm:ss");
        backTimeSpinner.setEditor(backTimeEditor);
        backTimeSpinner.setValue(new Date());
        panel.add(backTimeSpinner, getConstraints(4, 6, 2, 1, 0, 0, GridBagConstraints.BOTH));
        
        JLabel ticketTypeLabel = new JLabel("適用優惠");
        panel.add(ticketTypeLabel, getConstraints(0, 7, 6, 1, 0, 0, GridBagConstraints.BOTH));


        JComboBox ticketTypeComboBox = new JComboBox(TicketType);
        panel.add(ticketTypeComboBox, getConstraints(0, 8, 6, 1, 0, 0, GridBagConstraints.BOTH));

        JButton queryButton = new JButton("查詢");
        queryButton.setBackground(Color.ORANGE);
        panel.add(queryButton, getConstraints(0, 9, 6, 1, 0, 0, GridBagConstraints.BOTH));


        panel.setVisible(true);

        frame.add(panel, getConstraints(0, 1, 6, 10, 0, 0, GridBagConstraints.BOTH));

        frame.setVisible(true);
    }
}
