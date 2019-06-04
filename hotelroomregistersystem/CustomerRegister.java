/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelroomregistersystem;

/**
 *
 * @author user
 */
public class CustomerRegister {
    static String getName;
    private String name, ic, room;
 
    CustomerRegister(String name, String ic, String room){
        this.name = name;
        this.ic = ic;
        this.room = room;
    }
    void setName(String name){
        this.name = name;
    }
     void setIC(String ic){
        this.ic = ic;
    }    
     void setRoom(String room){
        this.room = room;
    } 
    String getName(){
        return name;
    }
    String getIC(){
        return ic;
    }
    String getRoom(){
        return room;
}
}

