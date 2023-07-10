package view;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import java.awt.*;

public class SimulationView extends JFrame{

    JTextArea textArea;
    public SimulationView(){
        this.setTitle("Simulation");
        this.getContentPane().setBackground(Color.PINK);
        this.setBounds(100, 100, 1066, 585);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JScrollPane scrollPane = new JScrollPane();
        GroupLayout groupLayout = new GroupLayout(this.getContentPane());
        groupLayout.setHorizontalGroup(
                groupLayout.createParallelGroup(Alignment.TRAILING)
                        .addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
                                .addGap(93)
                                .addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 867, Short.MAX_VALUE)
                                .addGap(92))
        );
        groupLayout.setVerticalGroup(
                groupLayout.createParallelGroup(Alignment.LEADING)
                        .addGroup(groupLayout.createSequentialGroup()
                                .addGap(44)
                                .addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 453, Short.MAX_VALUE)
                                .addGap(51))
        );

        textArea = new JTextArea();
        textArea.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 18));
        textArea.setEditable(false);
        scrollPane.setViewportView(textArea);
        this.getContentPane().setLayout(groupLayout);

        this.setVisible(true);
    }

    public String getTextArea() {
        return textArea.getText();
    }

    public void setTextArea(String textArea) {
        this.textArea.setText(textArea);
    }

    public void appendToTextArea(String toBeAppended){
        String aux = getTextArea() + toBeAppended;
        System.out.println(aux);
        this.setTextArea(aux);
    }
}
