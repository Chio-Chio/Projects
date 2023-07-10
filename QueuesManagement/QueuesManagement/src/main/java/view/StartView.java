package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartView extends JFrame{
    private JTextField inArrivalMin;
    //inArrivalMax
    private JTextField inArrivalMax;
    private JTextField inServiceMax;
    private JTextField inServiceMin;
    private JTextField inNrClients;
    private JTextField inNrQueues;
    private JTextField inTMaxSimulation;

    JButton startSimulationButton;

    public StartView() {
        this.setTitle("QUEUES MANAGEMENT APPLICATION USING\r\nTHREADS AND SYNCHRONIZATION MECHANISMS");
        this.getContentPane().setBackground(Color.CYAN);
        this.setBounds(100, 100, 1100, 607);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().setLayout(new BoxLayout(this.getContentPane(), BoxLayout.X_AXIS));

        JPanel panel = new JPanel();
        panel.setBackground(Color.PINK);
        this.getContentPane().add(panel);

        JTextField txtSimulationIntervaltMax = new JTextField();
        txtSimulationIntervaltMax.setText("Simulation Interval(t MAX simulation):");
        txtSimulationIntervaltMax.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD | Font.ITALIC, 18));
        txtSimulationIntervaltMax.setEditable(false);
        txtSimulationIntervaltMax.setColumns(10);
        txtSimulationIntervaltMax.setBackground(Color.CYAN);

         inTMaxSimulation = new JTextField();
        inTMaxSimulation.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD | Font.ITALIC, 18));
        inTMaxSimulation.setEditable(true);
        inTMaxSimulation.setColumns(10);
        inTMaxSimulation.setBackground(Color.CYAN);

        JTextField textField_aTime = new JTextField();
        textField_aTime.setEditable(false);
        textField_aTime.setText("Arrival Time");
        textField_aTime.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD | Font.ITALIC, 18));
        textField_aTime.setColumns(10);
        textField_aTime.setBackground(Color.CYAN);

        JTextField textField_aMin = new JTextField();
        textField_aMin.setText("MIN");
        textField_aMin.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD | Font.ITALIC, 18));
        textField_aMin.setEditable(false);
        textField_aMin.setColumns(10);
        textField_aMin.setBackground(Color.CYAN);

        JTextField textField_aMax = new JTextField();
        textField_aMax.setText("MAX");
        textField_aMax.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD | Font.ITALIC, 18));
        textField_aMax.setEditable(false);
        textField_aMax.setColumns(10);
        textField_aMax.setBackground(Color.CYAN);

        inArrivalMin = new JTextField();
        inArrivalMin.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD | Font.ITALIC, 18));
        inArrivalMin.setEditable(true);
        inArrivalMin.setColumns(10);
        inArrivalMin.setBackground(Color.CYAN);

        inArrivalMax = new JTextField();
        inArrivalMax.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD | Font.ITALIC, 18));
        inArrivalMax.setEditable(true);
        inArrivalMax.setColumns(10);
        inArrivalMax.setBackground(Color.CYAN);

        JTextField textField_sTime = new JTextField();
        textField_sTime.setText("Service Time");
        textField_sTime.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD | Font.ITALIC, 18));
        textField_sTime.setEditable(false);
        textField_sTime.setColumns(10);
        textField_sTime.setBackground(Color.CYAN);

        JTextField textField_sMin = new JTextField();
        textField_sMin.setText("MIN");
        textField_sMin.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD | Font.ITALIC, 18));
        textField_sMin.setEditable(false);
        textField_sMin.setColumns(10);
        textField_sMin.setBackground(Color.CYAN);

        JTextField textField_sMax = new JTextField();
        textField_sMax.setText("MAX");
        textField_sMax.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD | Font.ITALIC, 18));
        textField_sMax.setEditable(false);
        textField_sMax.setColumns(10);
        textField_sMax.setBackground(Color.CYAN);

        inServiceMax = new JTextField();
        inServiceMax.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD | Font.ITALIC, 18));
        inServiceMax.setEditable(true);
        inServiceMax.setColumns(10);
        inServiceMax.setBackground(Color.CYAN);

        inServiceMin = new JTextField();
        inServiceMin.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD | Font.ITALIC, 18));
        inServiceMin.setEditable(true);
        inServiceMin.setColumns(10);
        inServiceMin.setBackground(Color.CYAN);

        JTextField textFieldNrClients = new JTextField();
        textFieldNrClients.setText(" Number of clients (N):");
        textFieldNrClients.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD | Font.ITALIC, 18));
        textFieldNrClients.setEditable(false);
        textFieldNrClients.setColumns(10);
        textFieldNrClients.setBackground(Color.CYAN);

        JTextField textFieldNrQueues = new JTextField();
        textFieldNrQueues.setText(" Number of queues (Q):");
        textFieldNrQueues.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD | Font.ITALIC, 18));
        textFieldNrQueues.setEditable(false);
        textFieldNrQueues.setColumns(10);
        textFieldNrQueues.setBackground(Color.CYAN);

        inNrClients = new JTextField();
        inNrClients.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD | Font.ITALIC, 18));
        inNrClients.setEditable(true);
        inNrClients.setColumns(10);
        inNrClients.setBackground(Color.CYAN);

        inNrQueues = new JTextField();
        inNrQueues.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD | Font.ITALIC, 18));
        inNrQueues.setEditable(true);
        inNrQueues.setColumns(10);
        inNrQueues.setBackground(Color.CYAN);

        startSimulationButton = new JButton("START SIMULATION!");
        startSimulationButton.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD | Font.ITALIC, 28));
        GroupLayout gl_panel = new GroupLayout(panel);
        gl_panel.setHorizontalGroup(
                gl_panel.createParallelGroup(GroupLayout.Alignment.TRAILING)
                        .addGroup(gl_panel.createSequentialGroup()
                                .addGap(242)
                                .addComponent(textFieldNrClients, GroupLayout.DEFAULT_SIZE, 246, Short.MAX_VALUE)
                                .addGap(27)
                                .addComponent(inNrClients, GroupLayout.DEFAULT_SIZE, 257, Short.MAX_VALUE)
                                .addGap(266))
                        .addGroup(gl_panel.createSequentialGroup()
                                .addGap(242)
                                .addComponent(textFieldNrQueues, GroupLayout.DEFAULT_SIZE, 246, Short.MAX_VALUE)
                                .addGap(27)
                                .addComponent(inNrQueues, GroupLayout.DEFAULT_SIZE, 257, Short.MAX_VALUE)
                                .addGap(266))
                        .addGroup(gl_panel.createSequentialGroup()
                                .addGap(242)
                                .addComponent(txtSimulationIntervaltMax, GroupLayout.DEFAULT_SIZE, 246, Short.MAX_VALUE)
                                .addGap(27)
                                .addComponent(inTMaxSimulation, GroupLayout.DEFAULT_SIZE, 257, Short.MAX_VALUE)
                                .addGap(266))
                        .addGroup(gl_panel.createSequentialGroup()
                                .addGap(32)
                                .addComponent(textField_aTime, GroupLayout.DEFAULT_SIZE, 137, Short.MAX_VALUE)
                                .addGroup(gl_panel.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(gl_panel.createSequentialGroup()
                                                .addGap(6)
                                                .addComponent(textField_aMin, GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE)
                                                .addGap(6))
                                        .addGroup(gl_panel.createSequentialGroup()
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(textField_aMax, GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
                                                .addGap(0)))
                                .addGroup(gl_panel.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(inArrivalMin, GroupLayout.DEFAULT_SIZE, 267, Short.MAX_VALUE)
                                        .addGroup(gl_panel.createSequentialGroup()
                                                .addGap(6)
                                                .addComponent(inArrivalMax, GroupLayout.DEFAULT_SIZE, 261, Short.MAX_VALUE)))
                                .addGap(17)
                                .addComponent(textField_sTime, GroupLayout.DEFAULT_SIZE, 123, Short.MAX_VALUE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(gl_panel.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(textField_sMax, GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
                                        .addComponent(textField_sMin, GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE))
                                .addGap(18)
                                .addGroup(gl_panel.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(gl_panel.createSequentialGroup()
                                                .addGap(6)
                                                .addComponent(inServiceMin, GroupLayout.DEFAULT_SIZE, 258, Short.MAX_VALUE))
                                        .addComponent(inServiceMax, GroupLayout.DEFAULT_SIZE, 264, Short.MAX_VALUE))
                                .addGap(73))
                        .addGroup(gl_panel.createSequentialGroup()
                                .addGap(299)
                                .addComponent(startSimulationButton, GroupLayout.DEFAULT_SIZE, 485, Short.MAX_VALUE)
                                .addGap(254))
        );
        gl_panel.setVerticalGroup(
                gl_panel.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(gl_panel.createSequentialGroup()
                                .addGap(47)
                                .addGroup(gl_panel.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(textFieldNrClients, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(inNrClients, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE))
                                .addGap(18)
                                .addGroup(gl_panel.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(textFieldNrQueues, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(inNrQueues, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE))
                                .addGap(18)
                                .addGroup(gl_panel.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(txtSimulationIntervaltMax, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(inTMaxSimulation, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE))
                                .addGap(15)
                                .addGroup(gl_panel.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(gl_panel.createSequentialGroup()
                                                .addGap(56)
                                                .addComponent(textField_aTime, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE))
                                        .addGroup(gl_panel.createSequentialGroup()
                                                .addGap(8)
                                                .addComponent(textField_aMin, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE)
                                                .addGap(14)
                                                .addComponent(textField_aMax, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE))
                                        .addGroup(gl_panel.createSequentialGroup()
                                                .addGap(3)
                                                .addComponent(inArrivalMin, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE)
                                                .addGap(3)
                                                .addComponent(inArrivalMax, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE))
                                        .addGroup(gl_panel.createSequentialGroup()
                                                .addGap(56)
                                                .addComponent(textField_sTime, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE))
                                        .addGroup(gl_panel.createSequentialGroup()
                                                .addGap(8)
                                                .addComponent(textField_sMin, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE)
                                                .addGap(14)
                                                .addComponent(textField_sMax, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE))
                                        .addGroup(gl_panel.createSequentialGroup()
                                                .addComponent(inServiceMin, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE)
                                                .addGap(6)
                                                .addComponent(inServiceMax, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(startSimulationButton, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
                                .addGap(12))
        );
        panel.setLayout(gl_panel);

        this.setVisible(true);
    }

    public String getInArrivalMin() {
        return inArrivalMin.getText();
    }

    public void setInArrivalMin(String inArrivalMin) {
        this.inArrivalMin.setText(inArrivalMin);
    }

    public String getInArrivalMax() {
        return inArrivalMax.getText();
    }

    public void setInArrivalMax(String inArrivalMax) {
        this.inArrivalMax.setText((inArrivalMax));
    }

    public String getInServiceMax() {
        return inServiceMax.getText();
    }

    public void setInServiceMax(String inServiceMax) {
        this.inServiceMax.setText(inServiceMax);
    }

    public String getInServiceMin() {
        return inServiceMin.getText();
    }

    public void setInServiceMin(String inServiceMin) {
        this.inServiceMin.setText(inServiceMin);
    }

    public String getInNrClients() {
        return inNrClients.getText();
    }

    public void setInNrClients(String inNrClients) {
        this.inNrClients.setText(inNrClients);
    }

    public String getInNrQueues() {
        return inNrQueues.getText();
    }

    public void setInNrQueues(String inNrQueues) {
        this.inNrQueues.setText(inNrQueues);
    }

//    public JButton getStartSimulationButton() {
//        return startSimulationButton;
//    }
//
//    public void setStartSimulationButton(JButton startSimulationButton) {
//        this.startSimulationButton = startSimulationButton;
//    }


    public String getInTMaxSimulation() {
        return inTMaxSimulation.getText();
    }

    public void setInTMaxSimulation(String inTMaxSimulation) {
        this.inTMaxSimulation.setText(inTMaxSimulation);
    }

    public void showErrorMessage(String message){
        JOptionPane.showMessageDialog(this, message, "ERROR MESSAGE", JOptionPane.ERROR_MESSAGE);
        //refresh();
    }
    public void startSimulationListener(ActionListener action){
        startSimulationButton.addActionListener(action);
    }
}
