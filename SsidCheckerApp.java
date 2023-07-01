import javax.swing.JOptionPane;
import java.util.Random;

public class SsidCheckerApp {
    public static void main(String[] args) {
        SsidChecker checker = new SsidChecker();
        int count = 0;
        StringBuilder validItems = new StringBuilder();
        StringBuilder invalidItems = new StringBuilder();
        
        try {
            // Asks how many items they would like to enter.
            int numOfItems = Integer.parseInt(JOptionPane.showInputDialog("How many items would you like to validate? Please make sure to type a number between 1-20"));
            if(numOfItems<=20){
                // Loops based on users input
                while(count<numOfItems){
                    // Asks user for item
                    String item = JOptionPane.showInputDialog("Please enter your item:"+"\n"+"Remember it needs the format NNNNN-Eir NL where N is a number and L is a letter.");
                    // Count that the user entered a item
                    count = count +1;
                    // Sets the item inside SsidChecker to be validated
                    checker.setItem(item);
                    // Validates the item
                    checker.validateItem();
                    // Check if item was valid
                    if(checker.getValidation()){
                        JOptionPane.showMessageDialog(null, "Your item is valid.");
                    }
                    else{
                        JOptionPane.showMessageDialog(null, "Sorry, your input was not valid.");
                    }
                }
            }
            else{
                JOptionPane.showMessageDialog(null, "Number must be between 1-20");
            }
            
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "You must enter a number.");
        }
        
        // I check if the count is greater than 0. If its greater than 0 then I take that as the user has entered items
        // I then display the valid and invalid items to the user
        if(count>0){
            for (String str : checker.getValidItems()) {
                validItems.append(str).append("\n");
            }
            for (String str : checker.getInvalidItems()) {
                invalidItems.append(str).append("\n");
            }

            JOptionPane.showMessageDialog(null, "Valid Items: "+"\n"+validItems.toString());
            JOptionPane.showMessageDialog(null, "Invalid Items: "+"\n"+invalidItems.toString());
        }


        // Generate Password
        int numOfPasswords = Integer.parseInt(JOptionPane.showInputDialog("How many passwords would you like to generate?"));
        String PPSN = JOptionPane.showInputDialog("Please enter your PPSN:").toLowerCase();
        int[] calculations = new int[numOfPasswords];
        Random random = new Random();
        StringBuilder passwords = new StringBuilder();

        // Sets random int into array
        for (int j : calculations) {
            j = random.nextInt(9) + 21;
        }
        // For each password returned it appends to the passwords Stringbuilder and then displays to the user.
        for (String p : checker.generatePasswords(numOfPasswords, PPSN, calculations)) {
            passwords.append(p);
            passwords.append("\n");
        }
        JOptionPane.showMessageDialog(null, "Passwords: "+"\n"+passwords.toString());
    }
}
