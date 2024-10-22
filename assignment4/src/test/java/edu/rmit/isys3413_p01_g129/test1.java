package edu.rmit.isys3413_p01_g129;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class test1 {
    @Test
    public void testaddPrescription() {
        Prescription prescription = new Prescription();
        Date date = new GregorianCalendar(2024, Calendar.FEBRUARY, 11).getTime();

        //Test Case 1
        boolean result = prescription.addPrescription(10,"John","Patel","123 Fake St, Melbourne 3000 VIC ",1.00f,1.00f,1.00f,date,"Dr. Smith") ;
        assertTrue(result);

        //Test Case 2
        result = prescription.addPrescription(10,"Jon","Patel","123 Fake St, Melbourne 3000 VIC ",1.00f,1.00f,1.00f, date,"Dr. Smith");
        assertFalse(result);
        result = prescription.addPrescription(10,"John","Pat","123 Fake St, Melbourne 3000 VIC ",1.00f,1.00f,1.00f, date,"Dr. Smith");
        assertFalse(result);
        result = prescription.addPrescription(10,"Johnnnnnnnnnnnnn","Patel","123 Fake St, Melbourne 3000 VIC ",1.00f,1.00f,1.00f, date,"Dr. Smith");
        assertFalse(result);
        result = prescription.addPrescription(10,"John","Patellllllllllll","123 Fake St, Melbourne 3000 VIC ",1.00f,1.00f,1.00f, date,"Dr. Smith");
        assertFalse(result);

        //Test Case 3
        result = prescription.addPrescription(10,"John","Patel","123 Fake St",1.00f,1.00f,1.00f, date,"Dr. Smith");
        assertFalse(result);

        //Test Case 4
        result = prescription.addPrescription(10,"John","Patel","123 Fake St, Melbourne 3000 VIC ",-20.01f,1.00f,1.00f,date,"Dr. Smith");
        assertFalse(result);
        result = prescription.addPrescription(10,"John","Patel","123 Fake St, Melbourne 3000 VIC ",20.01f,1.00f,1.00f,date,"Dr. Smith"); 
        assertFalse(result);
        result = prescription.addPrescription(10,"John","Patel","123 Fake St, Melbourne 3000 VIC ",1.00f,-1.01f,1.00f,date,"Dr. Smith");
        assertFalse(result);
        result = prescription.addPrescription(10,"John","Patel","123 Fake St, Melbourne 3000 VIC ",1.00f,180.01f,1.00f,date,"Dr. Smith");
        assertFalse(result);
        result = prescription.addPrescription(10,"John","Patel","123 Fake St, Melbourne 3000 VIC ",1.00f,1.00f,-4.01f,date,"Dr. Smith");
        assertFalse(result);
        result = prescription.addPrescription(10,"John","Patel","123 Fake St, Melbourne 3000 VIC ",1.00f,1.00f,4.01f,date,"Dr. Smith");
        assertFalse(result);          

        //Test Case 5
        Date date1 = new GregorianCalendar(2025, Calendar.FEBRUARY, 11).getTime();
        result = prescription.addPrescription(10,"John","Patel","123 Fake St, Melbourne 3000 VIC ",1.00f,1.00f,1.00f,date1,"Dr. Smith");
        assertFalse(result); 
        
        //Test Case 6
        result = prescription.addPrescription(10,"John","Patel","123 Fake St, Melbourne 3000 VIC ",1.00f,1.00f,1.00f,date,"Dr. Smi");
        assertFalse(result);
        result = prescription.addPrescription(10,"John","Patel","123 Fake St, Melbourne 3000 VIC ",1.00f,1.00f,1.00f,date,"Dr. Smithhhhhhhhhhhhhhhhhh");  
        assertFalse(result);

    }
}
