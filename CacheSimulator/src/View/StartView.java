package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static java.lang.Integer.parseInt;

public class StartView extends JFrame{
    private JComboBox comboBoxAddressingMode;
    private JButton btnStart;
    private JComboBox comboBoxAddressSize;
    private JComboBox comboBoxCacheSize;
    private JComboBox comboBoxWritePolicy;
    private JComboBox comboBoxReplacementPolicy;
    //private JLabel lblTitle;
    public StartView() {
        this.setTitle("Menu");
        this.setBounds(100, 100, 895, 526);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().setLayout(null);

        JLabel lblTitle = new JLabel("Cache Memory Simulator");
        lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 22));
        lblTitle.setBounds(34, 10, 353, 96);
        this.getContentPane().add(lblTitle);

        comboBoxAddressingMode = new JComboBox();
        comboBoxAddressingMode.setFont(new Font("Tahoma", Font.PLAIN, 15));
        comboBoxAddressingMode.setModel(new DefaultComboBoxModel(new String[] {"Set-Associative 2-Way","Set-Associative 4-Way", "Fully Associative", "Direct Mapped"})); // "Set-Associative"
        comboBoxAddressingMode.setEditable(false);
        comboBoxAddressingMode.setBounds(181, 131, 279, 44);
        this.getContentPane().add(comboBoxAddressingMode);

        JLabel lblNewLabel = new JLabel("Addressing mode");
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblNewLabel.setBounds(34, 129, 137, 44);
        this.getContentPane().add(lblNewLabel);

        btnStart = new JButton("START");
        btnStart.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });
        btnStart.setFont(new Font("Tahoma", Font.PLAIN, 22));
        btnStart.setBounds(522, 368, 169, 66);
        this.getContentPane().add(btnStart);

        JLabel lblAddressSize = new JLabel("Address size");
        lblAddressSize.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblAddressSize.setBounds(34, 217, 137, 44);
        this.getContentPane().add(lblAddressSize);

        comboBoxAddressSize = new JComboBox();
        comboBoxAddressSize.setFont(new Font("Tahoma", Font.PLAIN, 15));
        comboBoxAddressSize.setModel(new DefaultComboBoxModel(new String[] {"32"}));
        comboBoxAddressSize.setEditable(false);
        comboBoxAddressSize.setBounds(181, 217, 279, 44);
        this.getContentPane().add(comboBoxAddressSize);

        JLabel lblCacheSize = new JLabel("Cache size");
        lblCacheSize.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblCacheSize.setBounds(34, 306, 137, 44);
        this.getContentPane().add(lblCacheSize);

        comboBoxCacheSize = new JComboBox();
        comboBoxCacheSize.setModel(new DefaultComboBoxModel(new String[] {"64", "16", "32"}));
        comboBoxCacheSize.setFont(new Font("Tahoma", Font.PLAIN, 15));
        comboBoxCacheSize.setEditable(false);
        comboBoxCacheSize.setBounds(181, 306, 279, 44);
        this.getContentPane().add(comboBoxCacheSize);

        JLabel lblWritePolicy = new JLabel("Write Policy");
        lblWritePolicy.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblWritePolicy.setBounds(34, 368, 137, 44);
        this.getContentPane().add(lblWritePolicy);

        comboBoxWritePolicy = new JComboBox();
        comboBoxWritePolicy.setModel(new DefaultComboBoxModel(new String[] {"Write Back", "Write Through"}));
        comboBoxWritePolicy.setFont(new Font("Tahoma", Font.PLAIN, 15));
        comboBoxWritePolicy.setEditable(false);
        comboBoxWritePolicy.setBounds(181, 368, 279, 44);
        this.getContentPane().add(comboBoxWritePolicy);

        JLabel lbReplacementPolicy = new JLabel("Replacement Policy");
        lbReplacementPolicy.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lbReplacementPolicy.setBounds(34, 432, 137, 44);
        this.getContentPane().add(lbReplacementPolicy);

         comboBoxReplacementPolicy = new JComboBox();
        comboBoxReplacementPolicy.setModel(new DefaultComboBoxModel(new String[] {"Random"}));
        comboBoxReplacementPolicy.setFont(new Font("Tahoma", Font.PLAIN, 15));
        comboBoxReplacementPolicy.setEditable(false);
        comboBoxReplacementPolicy.setBounds(181, 433, 279, 44);
        this.getContentPane().add(comboBoxReplacementPolicy);

        this.setVisible(true);
    }

    public String getComboBoxAddressingMode() {
        return comboBoxAddressingMode.getSelectedItem().toString();
    }

    public void setComboBoxAddressingMode(JComboBox comboBoxAddressingMode) {
        this.comboBoxAddressingMode = comboBoxAddressingMode;
    }

    public JButton getBtnStart() {
        return btnStart;
    }

    public void setBtnStart(JButton btnStart) {
        this.btnStart = btnStart;
    }

    public Integer getComboBoxAddressSize() {
        return  parseInt(comboBoxAddressSize.getSelectedItem().toString());
    }

    public void setComboBoxAddressSize(JComboBox comboBoxAddressSize) {
        this.comboBoxAddressSize = comboBoxAddressSize;
    }

    public Integer getComboBoxCacheSize() {
        return parseInt(comboBoxCacheSize.getSelectedItem().toString());
    }

    public void setComboBoxCacheSize(JComboBox comboBoxCacheSize) {
        this.comboBoxCacheSize = comboBoxCacheSize;
    }

    public String getComboBoxWritePolicy() {
        return comboBoxWritePolicy.getSelectedItem().toString();
    }

    public void setComboBoxWritePolicy(JComboBox comboBoxWritePolicy) {
        this.comboBoxWritePolicy = comboBoxWritePolicy;
    }
    public void startSimulationListener(ActionListener action){
        btnStart.addActionListener(action);
    }

    public String getComboBoxReplacementPolicy() {
        return comboBoxReplacementPolicy.getSelectedItem().toString();
    }

    public void setComboBoxReplacementPolicy(JComboBox comboBoxReplacementPolicy) {
        this.comboBoxReplacementPolicy = comboBoxReplacementPolicy;
    }
}
