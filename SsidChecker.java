import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SsidChecker{
    private String item;
    private boolean isValid;
    private List<String> validItems = new ArrayList<>();
    private List<String> invalidItems = new ArrayList<>();

    SsidChecker(){
        item = "";
        isValid = false;
    }

    // Setters
    public void setItem(String item){
        this.item = item;
        isValid = false;
    }

    // Getters
    public boolean getValidation(){
        return isValid;
    }
    public String getCurrentItem(){
        return item;
    }
    public List<String> getValidItems(){
        return validItems;
    }
    public List<String> getInvalidItems(){
        return invalidItems;
    }

    // Compute
    //Validates item and adds the item to a list if it is or it not valid
    public void validateItem(){
        // Checks the item is correct length
        if(item.length()!=12){
            isValid = false;
            invalidItems.add(item);
        }
        else{
            // Checks there is a dash at the right position
            if(item.charAt(5) == '-'){
                int dashIndex = item.indexOf("-");
                String numbers = item.substring(0, dashIndex);
                boolean numbersValid = false;

                // Loop through numbers and check they are digits and that each number is greater than the next
                for (int i = 0; i < dashIndex -1; i++) {
                    if(Character.isDigit(numbers.charAt(i)) && (numbers.charAt(i)>numbers.charAt(i+1))){
                        numbersValid = true;
                    }
                    else{
                        numbersValid = false;
                        invalidItems.add(item);
                        break;
                    }
                }
                if(numbersValid){
                    // Checks Eir is after the dash
                    if(item.substring(dashIndex + 1, dashIndex + 4).equals("Eir")){
                        // Checks space is after Eir
                        if(item.substring(dashIndex + 4).charAt(0) == ' '){
                            // Checks last two characters are a digit and a letter
                            if(Character.isDigit(item.substring(dashIndex + 5).charAt(0)) && Character.isLetter((item.substring(dashIndex + 5).charAt(1)))){
                                isValid = true;
                                validItems.add(item);
                            }
                            else{
                                invalidItems.add(item);
                            }
                        }
                        else{
                            invalidItems.add(item);
                        }
                    }
                    else{
                        invalidItems.add(item);
                    }
                }
                else{
                    invalidItems.add(item);
                }
            }
        }
    }

    // Generate as many passwords as the user requests
    public String[] generatePasswords(int numOfPasswords, String PPSN, int[]calculation){
        // Create alphabet array
        char[] alphabet = "abcdefghijklmnopqrstuvwxyz".toCharArray();
        String[] passwords = new String[numOfPasswords];
        Random random = new Random();

        // Create new password
        for (int i = 0; i < numOfPasswords; i++) {
            // Build password
            // First add 4 random letters
            StringBuilder password = new StringBuilder();
            for(int j = 0; j<4;j++){
                password.append(alphabet[random.nextInt(alphabet.length)]);
            }
            // Add @
            password.append('@');
            // Take first 7 digits and appends modulus remainder
            password.append(Integer.parseInt(PPSN.substring(0, 7))%calculation[i]);
            // Append last two letters
            password.append(PPSN.substring(7));
            // Add password to passwords array
            passwords[i]=password.toString();
        }
        return passwords;
    }
}

