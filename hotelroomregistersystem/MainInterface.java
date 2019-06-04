/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelroomregistersystem;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author user
 */
public class MainInterface {
     public static ArrayList<CustomerRegister> customer = new <CustomerRegister>ArrayList();
     public static File outFile = new File("Customer.txt");
     public static String[] coloumnName ={"NO","NAME","IC","ROOM"};
     public static DefaultTableModel model = new DefaultTableModel(coloumnName,0);
      
    public MainInterface() {
        JFrame mainframe = new JFrame("Hotel Register System");       
        JLabel la = new JLabel("Customer Name :");
        JLabel lb = new JLabel("Customer IC :");
        JLabel lc = new JLabel("Room :");
        JTextField jtfname = new JTextField();
        JTextField jtfic = new JTextField();
        JTextField jtfroom = new JTextField();
        JButton btna = new JButton("Insert");
        JButton btnb = new JButton("Delete");
        JButton btnc = new JButton("Update");
        JButton btnd = new JButton("Search");
        
        la.setBounds(20, 20, 100, 20);
        lb.setBounds(20, 60, 100, 20);
        lc.setBounds(20, 100, 100, 20);
        jtfname.setBounds(120, 20, 100, 20);
        jtfic.setBounds(120, 60, 200, 20);
        jtfroom.setBounds(120, 100, 200, 20);
        btna.setBounds(20, 140, 100, 20);
        btnb.setBounds(120, 140, 100, 20);
        btnc.setBounds(220, 140, 100, 20);
        btnd.setBounds(320, 140, 100, 20);
       
        JTable jtable = new JTable(model); 
        JScrollPane pane = new JScrollPane(jtable);
        pane.setBounds(20, 180, 400, 200);
        pane.setVisible(true);

        mainframe.add(la);
        mainframe.add(lb);
        mainframe.add(lc);
        mainframe.add(jtfname);
        mainframe.add(jtfic);
        mainframe.add(jtfroom);
        mainframe.add(btna);
        mainframe.add(btnb);
        mainframe.add(btnc);
        mainframe.add(btnd);
        mainframe.add(pane);
        
        mainframe.setResizable(false);
        mainframe.setSize(600, 500);//SET FRAME SIZE
        mainframe.setLayout(null);// SET FRAME TO NULL TYPE
        mainframe.setLocationRelativeTo(null);
        mainframe.setVisible(true);//SHOW THE FRAME
        mainframe.setVisible(true);
        mainframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        loadFile();
 
        btna.addActionListener(new ActionListener(){//insert
            @Override
            public void actionPerformed(ActionEvent e) {
             String name = jtfname.getText();
             String ic = jtfic.getText();
             String room = jtfroom.getText();
             customer.add(new CustomerRegister(name, ic, room));
             insertFile();
             
             for(int i=customer.size()-1;i<customer.size();i++){
                 String[]data = new String[4];
                 data[0] = String.valueOf(i+1);
                 data[1] = customer.get(i).getName();
                 data[2] = customer.get(i).getIC();
                 data[3] = customer.get(i).getRoom();
                 
                 model.addRow(data);
             }}
            });
         
                 
        btnb.addActionListener(new ActionListener(){//delete
            @Override
            public void actionPerformed(ActionEvent e) {
                 String Name =jtfname.getText();
                      boolean delete =false;
                      for(int i=0;i<customer.size();i++){
                          if(Name.equals(customer.get(i).getName())){
                           customer.remove(customer.get(i)); 
                           model.getDataVector().removeAllElements(); 
                           replaceData(); 
                           delete=true;
                          }
                      }
                      if(customer.isEmpty()){ 
                          model.fireTableDataChanged();
                      }else if(delete == false){
                          JOptionPane.showMessageDialog(null,"Can't find the data");
                      }
                  }
 });
         btnc.addActionListener(new ActionListener(){//update
            @Override
            public void actionPerformed(ActionEvent e) {
            String Name,IC,Room;
            Name=jtfname.getText();
            boolean update= false;
            for(int i=0;i<customer.size();i++){
                
                if(Name.equals(customer.get(i).getName())){
                
                   IC=jtfic.getText();
                   Room=jtfroom.getText();
                   
                   customer.get(i).setIC(IC); 
                   customer.get(i).setRoom(Room); 
                   
                   model.getDataVector().removeAllElements(); 
                   replaceData();
                   update=true;
                  
                }
            }
            if(customer.isEmpty()){
            JOptionPane.showMessageDialog(null,"No data found");
            
        }
            else if(update == false)
                JOptionPane.showMessageDialog(null,"Item Not Found");
        }

         });
         btnd.addActionListener(new ActionListener(){//search
            @Override
            public void actionPerformed(ActionEvent e) {
                String Name =jtfname.getText();
                 boolean search = false;
                 for(int i=0;i<customer.size(); i++){
                 if(Name.equals(customer.get(i).getName())){
                     showFoundItem(i);
                     search=true;
           }
           }
           if(search==false){
               JOptionPane.showMessageDialog(null,"Cannot find item!");
           }
        }

            });
    }
     
      private static void insertFile(){
        try{
           FileWriter outFileStream =new FileWriter(outFile); 
           PrintWriter outStream = new PrintWriter(outFileStream);
           
           for(int i =0;i<customer.size();i++){
               outStream.print(Integer.toString(i+1)+",");
               outStream.print(customer.get(i).getName()+",");
               outStream.print(customer.get(i).getIC()+",");
               outStream.println(customer.get(i).getRoom());
        }
           outStream.close();
        }
         catch(Exception e){
             JOptionPane.showMessageDialog(null,"Error in insertFile");
         }   
    }
    public static void loadFile(){
        try{
            Scanner scanner =new Scanner (outFile);
            String data;
            int i =1;
            
            while(scanner.hasNextLine()){
                data=scanner.nextLine();
                String dataArray[]=data.split(",");
                
                dataArray[0] = Integer.toString(i);
                customer.add(new CustomerRegister(dataArray[1],dataArray[2],dataArray[3]));
                model.addRow(dataArray);
                i++;
                
            }
            scanner.close();
            
        }catch(Exception ex){
             JOptionPane.showMessageDialog(null,"empty file");
        }
    }
    
        
    //method    
    public static void showFoundItem(int i){
            JOptionPane.showMessageDialog(null,"Search item found!\nName: "+customer.get(i).getName() +"\nIC:"+customer.get(i).getIC() +"\nRoom:"+ customer.get(i).getRoom());
       }
    public static void replaceData(){ 
        try{
     outFile.delete();
     outFile.createNewFile();
     FileWriter fileOutStream = new FileWriter(outFile);
     PrintWriter outStream= new PrintWriter(fileOutStream); 
     for (int i=0;i<customer.size();i++){
            outStream.print(Integer.toString(i+1)+",");
            outStream.print(customer.get(i).getName()+",");
            outStream.print(customer.get(i).getIC()+",");
            outStream.println(customer.get(i).getRoom());
        }
            outStream.close();
            
            Scanner scanner = new Scanner (outFile);
            String data;
            int i =1; 
            while(scanner.hasNextLine()){
                data = scanner.nextLine(); 
                String dataArray[]=data.split(","); 
                dataArray[0] = Integer.toString(i);
                
                model.addRow(dataArray);
                i++;
            }
            scanner.close();
        }
        catch(Exception ex)
        {
            JOptionPane.showMessageDialog(null,"empty file");
        }
        
    }
}
