package edu.rmit.isys3413_p01_g129;


import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.opentest4j.IncompleteExecutionException;

public class test2 {
    @Test
    public void testaddRemark(){
        Prescription prescription = new Prescription();
        Prescription prescription1 = new Prescription();

        //Test Case 1
        boolean result = prescription.addRemark("Client Remark", "Client");
        boolean result1 = prescription.addRemark("Optometrist Remark", "Optometrist");
        assertTrue(result);
        assertTrue(result1);
        
        //Test Case 2
        result = prescription1.addRemark("Optometrist Remarkkkk","Optometrist");
        result1 = prescription1.addRemark("Clien","Client");
        assertFalse(result);
        assertFalse(result1);

        //Test Case 3
        result = prescription1.addRemark("Client Remark", "Om");
        assertFalse(result);
        
        //Test Case 4
        boolean result2 = prescription.addRemark("Client Remark", "Client"); 
        assertFalse(result2);

        //Test Case 5
        result1 = prescription1.addRemark("optometrist Remark","Optometrist");
        assertFalse(result1); 
     

        //Test Case 6
        result = prescription1.addRemark("clien", "ABC"); 
        result1 = prescription1.addRemark("Optometrist Remarkkkk", "ACD"); 
        assertFalse(result);
        assertFalse(result1);        
    }
}
