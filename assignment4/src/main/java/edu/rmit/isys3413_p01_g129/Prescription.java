package edu.rmit.isys3413_p01_g129;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
 
public class Prescription{
    private int prescID;
    private String firstName;
    private String lastName;
    private String address;
    private float sphere;
    private float axis; 
    private float cylinder; 
    private Date examinationDate; 
    private String optometrist;
    private String[] remarktypes={"Client","Optometrist"};
    private ArrayList <String> postRemarks = new ArrayList<>();
    
    private String filename = "presc.txt";
    private String reviewfilename = "review.txt";

    public boolean addPrescription(int prescID,String firstName,String lastName,String address,float sphere,float axis,float cylinder,Date examinationDate,String optometrist){  

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        dateFormat.setLenient(false);

        if(firstName.length() < 4 || lastName.length() < 4 || firstName.length() > 15 || lastName.length() > 15){
            System.out.println("First name and last name must be between 4 and 15 characters");
            return false;
        }
        if(address.length() < 20)
        {
            System.out.println("Address must be at least 20 characters");
            return false;
        }
        if (sphere < -20.00 || sphere > 20.00 || cylinder < -4.00 || cylinder > 4.00 || axis < 0 || axis > 180) {
            System.out.println("Sphere must be between -20.00 and 20.00, Cylinder must be between -4.00 and 4.00, Axis must be between 0 and 180");
            return false;
        }
        if(examinationDate.after(new Date())){
            System.out.println("Examination date must be before today's date");
            return false;
        }
        
        if(optometrist.length() < 8 || optometrist.length() > 25){
            System.out.println("Optometrist name must be between 8 and 25 characters");
            return false;
        }
       
        try{ 
            this.prescID = prescID;
            this.firstName = firstName;
            this.lastName = lastName;
            this.address = address;
            this.sphere = sphere;
            this.axis = axis;
            this.cylinder = cylinder;
            this.examinationDate = examinationDate;
            this.optometrist = optometrist;
            FileWriter writer = new FileWriter(filename, true);
            writer.write( "\n" + "\n");
            writer.write("Prescription ID: " + this.prescID + "\n");
            writer.write("First Name: " + this.firstName + "\n");
            writer.write("Last Name: " + this.lastName + "\n");
            writer.write("Address: " + this.address + "\n");
            writer.write("Sphere: " + this.sphere + "\n");
            writer.write("Cylinder: " + this.cylinder + "\n");
            writer.write("Axis: " + this.axis + "\n");
            writer.write("Examination Date: " + dateFormat.format(this.examinationDate) + "\n");
            writer.write("optometrist Name: " + this.optometrist + "\n");
            writer.close();
        }
        catch(IOException e){
            e.printStackTrace();
        } 
        return true;
    }

    public boolean addRemark(String remark, String remarktype){
           
        if (remark.length() < 6 || remark.length() > 20) {
            System.out.println("Remark must be between 5 and 20 characters");
            return false;
        }
    
        if(postRemarks.size() >= 2){
            System.out.println("Only 2 remarks are allowed");
            return false;
        }
        if(!Character.isUpperCase(remark.charAt(0))){
            System.out.println("First letter of remark must be uppercase");
            return false;
        }
        {

        }
        
        try{
            FileWriter writer = new FileWriter(reviewfilename, true);
            
            if(remarktype.equals(this.remarktypes[0])){
                postRemarks.add(remark);
                writer.write( this.remarktypes[0] + ": " + remark + "\n");
            }
            else if(remarktype.equals(this.remarktypes[1])){
               this.postRemarks.add(remark);
               writer.write(this.remarktypes[1] + ": " + remark + "\n");
            }
            else{
                System.out.println("Invalid remark category");
                writer.close();
                return false;
            }
            writer.close();
        }
        
        catch(IOException e){
            e.printStackTrace();
        }
        return true;
    }

    public static void main(String[] args){
        Prescription presc = new Prescription();
        Date date = new GregorianCalendar(2024, Calendar.FEBRUARY, 11).getTime();
        presc.addPrescription(10,"John","Patel","123 Fake St, Melbourne 3000 VIC",1.00f,1.00f,1.00f,date,"Dr. Smith");
        presc.addRemark("Client Remark","Client");
        presc.addRemark("Optometrist Remark","Optometrist");
    }
}
