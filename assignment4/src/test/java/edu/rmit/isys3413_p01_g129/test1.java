package edu.rmit.isys3413_p01_g129;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class test1 {

    // This method contains test cases for the addPrescription method in the Prescription class
    @Test
    public void testaddPrescription() {
        // Create an instance of the Prescription class to be tested
        Prescription prescription = new Prescription();

        // Create a valid examination date (past date)
        Date date = new GregorianCalendar(2024, Calendar.FEBRUARY, 11).getTime();

        // Test Case 1: Valid input, should return true
        boolean result = prescription.addPrescription(10,"John","Patel","123 Fake St, Melbourne 3000 VIC ",1.00f,1.00f,1.00f,date,"Dr. Smith");
        assertTrue(result);

        // Test Case 2: Invalid first name (too short, too long), last name (too short, too long)
        // These cases test name length validations
        result = prescription.addPrescription(10,"Jon","Patel","123 Fake St, Melbourne 3000 VIC ",1.00f,1.00f,1.00f, date,"Dr. Smith");
        assertFalse(result); // First name too short (less than 4 characters)
        
        result = prescription.addPrescription(10,"John","Pat","123 Fake St, Melbourne 3000 VIC ",1.00f,1.00f,1.00f, date,"Dr. Smith");
        assertFalse(result); // Last name too short
        
        result = prescription.addPrescription(10,"Johnnnnnnnnnnnnn","Patel","123 Fake St, Melbourne 3000 VIC ",1.00f,1.00f,1.00f, date,"Dr. Smith");
        assertFalse(result); // First name too long (more than 15 characters)

        result = prescription.addPrescription(10,"John","Patellllllllllll","123 Fake St, Melbourne 3000 VIC ",1.00f,1.00f,1.00f, date,"Dr. Smith");
        assertFalse(result); // Last name too long

        // Test Case 3: Invalid address (less than 20 characters)
        result = prescription.addPrescription(10,"John","Patel","123 Fake St",1.00f,1.00f,1.00f, date,"Dr. Smith");
        assertFalse(result); // Address too short

        // Test Case 4: Invalid sphere, cylinder, axis values (outside of allowed ranges)
        // Testing sphere boundaries
        result = prescription.addPrescription(10,"John","Patel","123 Fake St, Melbourne 3000 VIC ",-20.01f,1.00f,1.00f,date,"Dr. Smith");
        assertFalse(result); // Sphere below the valid range

        result = prescription.addPrescription(10,"John","Patel","123 Fake St, Melbourne 3000 VIC ",20.01f,1.00f,1.00f,date,"Dr. Smith"); 
        assertFalse(result); // Sphere above the valid range

        // Testing cylinder boundaries
        result = prescription.addPrescription(10,"John","Patel","123 Fake St, Melbourne 3000 VIC ",1.00f,-1.01f,1.00f,date,"Dr. Smith");
        assertFalse(result); // Cylinder below valid range

        result = prescription.addPrescription(10,"John","Patel","123 Fake St, Melbourne 3000 VIC ",1.00f,180.01f,1.00f,date,"Dr. Smith");
        assertFalse(result); // Axis above the valid range (should be between 0 and 180)

        // Testing axis boundaries
        result = prescription.addPrescription(10,"John","Patel","123 Fake St, Melbourne 3000 VIC ",1.00f,1.00f,-4.01f,date,"Dr. Smith");
        assertFalse(result); // Cylinder below valid range

        result = prescription.addPrescription(10,"John","Patel","123 Fake St, Melbourne 3000 VIC ",1.00f,1.00f,4.01f,date,"Dr. Smith");
        assertFalse(result); // Cylinder above valid range

        // Test Case 5: Invalid future date for the examination date
        Date date1 = new GregorianCalendar(2025, Calendar.FEBRUARY, 11).getTime(); // Future date
        result = prescription.addPrescription(10,"John","Patel","123 Fake St, Melbourne 3000 VIC ",1.00f,1.00f,1.00f,date1,"Dr. Smith");
        assertFalse(result); // Examination date cannot be in the future

        // Test Case 6: Invalid optometrist name (too short, too long)
        result = prescription.addPrescription(10,"John","Patel","123 Fake St, Melbourne 3000 VIC ",1.00f,1.00f,1.00f,date,"Dr. Smi");
        assertFalse(result); // Optometrist name too short (less than 8 characters)

        result = prescription.addPrescription(10,"John","Patel","123 Fake St, Melbourne 3000 VIC ",1.00f,1.00f,1.00f,date,"Dr. Smithhhhhhhhhhhhhhhhhh");
        assertFalse(result); // Optometrist name too long (more than 25 characters)
    }
}
