package edu.rmit.isys3413_p01_g129;

import java.util.ArrayList;
import java.util.Date;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;

public class Prescription {
    // Prescription properties
    private int prescID; // Unique ID for the prescription
    private String firstName; // First name of the patient
    private String lastName; // Last name of the patient
    private String address; // Address of the patient
    private float sphere; // Sphere value for the prescription
    private float axis; // Axis value for the prescription
    private float cylinder; // Cylinder value for the prescription
    private Date examinationDate; // Date of examination
    private String optometrist; // Optometrist's name
    private String[] remarktypes = {"Client", "Optometrist"}; // Two categories for remarks
    private ArrayList<String> postRemarks = new ArrayList<>(); // To hold remarks, max 2

    // Filenames to store prescription and review data
    private String filename = "presc.txt"; 
    private String reviewfilename = "review.txt";

    // This method adds a prescription if all conditions are met
    public boolean addPrescription(int prescID, String firstName, String lastName, String address, 
                                    float sphere, float axis, float cylinder, 
                                    Date examinationDate, String optometrist) {
        
        // Format to ensure the date is displayed in a specific format (dd-MM-yyyy)
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        dateFormat.setLenient(false); // Ensures strict date formatting
        
        // Validation for first name and last name
        if (firstName.length() < 4 || lastName.length() < 4 || firstName.length() > 15 || lastName.length() > 15) {
            System.out.println("First name and last name must be between 4 and 15 characters");
            return false;
        }

        // Address must be at least 20 characters
        if (address.length() < 20) {
            System.out.println("Address must be at least 20 characters");
            return false;
        }

        // Validation for sphere, axis, and cylinder values
        if (sphere < -20.00 || sphere > 20.00 || cylinder < -4.00 || cylinder > 4.00 || axis < 0 || axis > 180) {
            System.out.println("Sphere must be between -20.00 and 20.00, Cylinder must be between -4.00 and 4.00, Axis must be between 0 and 180");
            return false;
        }

        // Ensure examination date is not in the future
        if (examinationDate.after(new Date())) {
            System.out.println("Examination date must be before today's date");
            return false;
        }

        // Validation for optometrist name length
        if (optometrist.length() < 8 || optometrist.length() > 25) {
            System.out.println("Optometrist name must be between 8 and 25 characters");
            return false;
        }
        
        // If all validations pass, write the prescription details to a file
        try {
            // Assign the values to the Prescription object
            this.prescID = prescID;
            this.firstName = firstName;
            this.lastName = lastName;
            this.address = address;
            this.sphere = sphere;
            this.axis = axis;
            this.cylinder = cylinder;
            this.examinationDate = examinationDate;
            this.optometrist = optometrist;
            
            // Write the prescription details to the file 'presc.txt'
            FileWriter writer = new FileWriter(filename, true); // Append mode
            writer.write("\n\n");
            writer.write("Prescription ID: " + this.prescID + "\n");
            writer.write("First Name: " + this.firstName + "\n");
            writer.write("Last Name: " + this.lastName + "\n");
            writer.write("Address: " + this.address + "\n");
            writer.write("Sphere: " + this.sphere + "\n");
            writer.write("Cylinder: " + this.cylinder + "\n");
            writer.write("Axis: " + this.axis + "\n");
            writer.write("Examination Date: " + dateFormat.format(this.examinationDate) + "\n");
            writer.write("Optometrist Name: " + this.optometrist + "\n");
            writer.close(); // Close the file after writing
        } 
        catch (IOException e) {
            e.printStackTrace(); // Print the stack trace in case of an error
        }

        return true; // Return true if prescription added successfully
    }

    // Method to add a remark to the prescription
    public boolean addRemark(String remark, String remarktype) {
        
        // Remark validation for length (must be between 6 and 20 characters)
        if (remark.length() < 6 || remark.length() > 20) {
            System.out.println("Remark must be between 6 and 20 characters");
            return false;
        }

        // Check if the maximum number of remarks (2) is exceeded
        if (postRemarks.size() >= 2) {
            System.out.println("Only 2 remarks are allowed");
            return false;
        }

        // Check if the first letter of the remark is uppercase
        if (!Character.isUpperCase(remark.charAt(0))) {
            System.out.println("First letter of remark must be uppercase");
            return false;
        }

        // Writing the remark to the file and adding it to the remark list
        try {
            FileWriter writer = new FileWriter(reviewfilename, true); // Append mode
            
            // Check if the remark type is valid (Client or Optometrist)
            if (remarktype.equals(this.remarktypes[0])) { // If remark is from "Client"
                postRemarks.add(remark); // Add remark to list
                writer.write(this.remarktypes[0] + ": " + remark + "\n"); // Write to file
            } 
            else if (remarktype.equals(this.remarktypes[1])) { // If remark is from "Optometrist"
                postRemarks.add(remark); // Add remark to list
                writer.write(this.remarktypes[1] + ": " + remark + "\n"); // Write to file
            } 
            else {
                // Invalid remark type
                System.out.println("Invalid remark category");
                writer.close(); // Close the writer before returning
                return false;
            }
            
            writer.close(); // Close the writer after writing
        } 
        catch (IOException e) {
            e.printStackTrace(); // Print stack trace in case of an error
        }

        return true; // Return true if remark is successfully added
    }
}
