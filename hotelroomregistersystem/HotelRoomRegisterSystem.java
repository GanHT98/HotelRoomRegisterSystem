/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelroomregistersystem;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author user
 */
public class HotelRoomRegisterSystem {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        JFrame f = new JFrame("Login");
        JLabel la = new JLabel("Username :");
        JLabel lb = new JLabel("Password :");
        
        JTextField jtfa = new JTextField();
        JTextField jtfb = new JTextField();
        JButton btna = new JButton("Login");
        JButton btnb = new JButton("Cancel");
        
        la.setBounds(80, 20, 100, 20);
        lb.setBounds(80, 60, 100, 20);
        jtfa.setBounds(180, 20, 130, 20);
        jtfb.setBounds(180, 60, 130, 20);
        btna.setBounds(100, 100, 100, 25);
        btnb.setBounds(200, 100, 100, 25);
        
        f.add(la);
        f.add(lb);
        f.add(jtfa);
        f.add(jtfb);
        f.add(btna);
        f.add(btnb);
        
        btna.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                 String name = jtfa.getText();
                 String pass = jtfb.getText();
                 if(name.equals("gan")&&(pass.equals("12345"))){
                    JOptionPane.showMessageDialog(null,"Welcome to the System");
                    f.setVisible(false);
                    new MainInterface();
                 }else{
                    JOptionPane.showMessageDialog(null,"Login Failed");
                 }
            }
        });
         btnb.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                f.setVisible(false);
                f.dispose();
            } 
         });
        f.setResizable(false);
        f.setSize(400, 200);
        f.setLayout(null);
        f.setLocationRelativeTo(null);
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

           
        

    }
    
}

