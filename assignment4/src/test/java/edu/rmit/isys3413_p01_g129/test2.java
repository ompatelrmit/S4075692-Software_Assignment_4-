package edu.rmit.isys3413_p01_g129;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class test2 {

    // This method contains test cases for the addRemark method in the Prescription class
    @Test
    public void testaddRemark(){
        // Create instances of the Prescription class for testing
        Prescription prescription = new Prescription();
        Prescription prescription1 = new Prescription();
        /* I created two Prescription objects because, in the first test case, the remarks are successfully added and return true. 
        However, for the second test case, I needed to create a second Prescription object because the first one already has two remarks added. 
        Once the maximum number of remarks is reached, any additional attempts, regardless of the parameters, will always return false. 
        By using a new object, we can test other scenarios independently */

        // Test Case 1: Valid remarks from both client and optometrist
        boolean result = prescription.addRemark("Client Remark", "Client");
        boolean result1 = prescription.addRemark("Optometrist Remark", "Optometrist");
        assertTrue(result);  // Valid client remark
        assertTrue(result1); // Valid optometrist remark
        
        // Test Case 2: Invalid remarks due to length
        // Remarks that are either too short or too long
        result = prescription1.addRemark("Optometrist Remarkkkk", "Optometrist");
        result1 = prescription1.addRemark("Clien", "Client");
        assertFalse(result);  // Invalid optometrist remark (too long)
        assertFalse(result1); // Invalid client remark (too short)

        // Test Case 3: Invalid role not matching "Client" or "Optometrist"
        result = prescription1.addRemark("Client Remark", "Om");
        assertFalse(result); // Invalid role ("Om" is not a valid role)

        // Test Case 4: We can only add two remarks to a prescription
        boolean result2 = prescription.addRemark("Client Remark", "Client"); 
        assertFalse(result2); // Duplicate remark from the client should return false

        // Test Case 5: Case-sensitivity check (optometrist role in lowercase)
        result1 = prescription1.addRemark("optometrist Remark", "Optometrist");
        assertFalse(result1); // Case mismatch in role or remark content

        // Test Case 6: Invalid roles that are not "Client" or "Optometrist"
        result = prescription1.addRemark("clien", "ABC"); 
        result1 = prescription1.addRemark("Optometrist Remarkkkk", "ACD"); 
        assertFalse(result);  // Invalid role ("ABC")
        assertFalse(result1); // Invalid role ("ACD")
    }
}
