package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class SimulationView extends JFrame {
    private JTextArea textAreaMainMemory;
    private JTextArea textAreaCacheMemory;
    private JTextArea textAreaCpuRequests;
    private  JButton btnNextRequest;
    private JTextArea textAreaHitMiss;
    public SimulationView(){
        this.setTitle("Simulation");
        this.setBounds(0, 0, 1192, 816);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.getContentPane().setLayout(null);

        JScrollPane scrollPaneMainMemory = new JScrollPane();
        scrollPaneMainMemory.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPaneMainMemory.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPaneMainMemory.setBounds(22, 301, 537, 465);
        this.getContentPane().add(scrollPaneMainMemory);

        textAreaMainMemory = new JTextArea();
        textAreaMainMemory.setEditable(false);
        scrollPaneMainMemory.setViewportView(textAreaMainMemory);

        JScrollPane scrollPaneCacheMemory = new JScrollPane();
        scrollPaneCacheMemory.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPaneCacheMemory.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPaneCacheMemory.setBounds(569, 63, 582, 703);
        this.getContentPane().add(scrollPaneCacheMemory);

        textAreaCacheMemory = new JTextArea();
        textAreaCacheMemory.setEditable(false);
        scrollPaneCacheMemory.setViewportView(textAreaCacheMemory);

        JLabel lblMainMemory = new JLabel("Main Memory");
        lblMainMemory.setFont(new Font("Tahoma", Font.PLAIN, 22));
        lblMainMemory.setBounds(22, 250, 321, 58);
        this.getContentPane().add(lblMainMemory);

        JLabel lblCacheMemory = new JLabel("Cache Memory");
        lblCacheMemory.setFont(new Font("Tahoma", Font.PLAIN, 22));
        lblCacheMemory.setBounds(570, 0, 321, 58);
        this.getContentPane().add(lblCacheMemory);

        JLabel lblCpuRequests = new JLabel("CPU Requests");
        lblCpuRequests.setFont(new Font("Tahoma", Font.PLAIN, 22));
        lblCpuRequests.setBounds(22, 0, 143, 58);
        this.getContentPane().add(lblCpuRequests);

        JScrollPane scrollPaneCpuRequests = new JScrollPane();
        scrollPaneCpuRequests.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPaneCpuRequests.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPaneCpuRequests.setBounds(22, 63, 531, 200);
        this.getContentPane().add(scrollPaneCpuRequests);

        textAreaCpuRequests = new JTextArea();
        textAreaCpuRequests.setEditable(false);
        scrollPaneCpuRequests.setViewportView(textAreaCpuRequests);

//        btnNextRequest = new JButton("next request");
//        btnNextRequest.setBackground(Color.LIGHT_GRAY);
//        btnNextRequest.setBounds(175, 17, 120, 35);
//        this.getContentPane().add(btnNextRequest);


        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setBounds(872, 0, 279, 65);
        this.getContentPane().add(scrollPane);

         textAreaHitMiss = new JTextArea();
        scrollPane.setViewportView(textAreaHitMiss);
        textAreaHitMiss.setEditable(false);

//        textAreaHitMiss = new JTextArea();
//        textAreaHitMiss.setEditable(false);
//        textAreaHitMiss.setBounds(882, 7, 269, 45);
//        this.getContentPane().add(textAreaHitMiss);
        this.setVisible(true);
    }

    public String getTextAreaMainMemory() {
        return textAreaMainMemory.getText();
    }

    public void setTextAreaMainMemory(String textAreaMainMemory) {
        this.textAreaMainMemory.setText(textAreaMainMemory);
    }

    public void appendToTextAreaMainMemory(String textAreaMainMemory) {
        this.textAreaMainMemory.setText(this.getTextAreaMainMemory() +textAreaMainMemory);
    }

    public String getTextAreaCacheMemory() {
        return textAreaCacheMemory.getText();
    }

    public void setTextAreaCacheMemory(String textAreaCacheMemory) {

        this.textAreaCacheMemory.setText(textAreaCacheMemory);
    }

    public void appendToTextAreaCacheMemory(String textAreaCacheMemory) {

        this.textAreaCacheMemory.setText( this.getTextAreaCacheMemory() +textAreaCacheMemory);
    }

    public String getTextAreaCpuRequests() {
        return textAreaCpuRequests.getText();
    }

    public void setTextAreaCpuRequests(String textAreaCpuRequests) {
        this.textAreaCpuRequests.setText(textAreaCpuRequests);
    }
    public void appendToTextAreaCpuRequests(String textAreaCpuRequests) {
        this.textAreaCpuRequests.setText(this.getTextAreaCpuRequests()+textAreaCpuRequests);
    }

    public String getTextAreaHitMiss() {
        return textAreaHitMiss.getText();
    }

    public void setTextAreaHitMiss(String textAreaHitMiss) {
        this.textAreaHitMiss.setText(textAreaHitMiss);
    }

    public  void appendTextAreaHitMiss(String textAreaHitMiss){
        this.textAreaHitMiss.setText(this.getTextAreaHitMiss() + textAreaHitMiss);
    }

    public void nextRequestListener(ActionListener action){
        btnNextRequest.addActionListener(action);
    }
}
