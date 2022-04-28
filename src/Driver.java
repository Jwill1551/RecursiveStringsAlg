import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Driver {
	// Used to iterate through the word array
	static int i;
	
	// Used to store the previous char
	static char prev_char = '`';
	
	
	// Driver method that will run the isSlop,isSlap, and isSlip methods
	public static void main(String[] args) throws FileNotFoundException {
		// Read Input from a file that will contain strings 
		Scanner sc = new Scanner(new File("text.txt"));
		String str = null;
		
		// Iterate through the file and store the strings 
		// Read each line according the integer N
		// Reset 'i' and 'prev_char' for every new word that is selected
		while(sc.hasNext()) {
			i = 0;
			str = sc.next();
			prev_char = '`';
			
			if(isSlop(str)) {
				System.out.println("Slop: " + str);
			} else if (isSlip(str)) {
				System.out.println("Slip: " + str);
			} else if (isSlap(str)) {
				System.out.println("Slap: " + str);
			} else {
				System.out.println("Not a Slap, Slip, or Slop: " + str);
			}
			
		}
		
		sc.close();
	}
	
	// Follows a character string that consists of a Slap followed by a Slip
	static boolean isSlop(String str) {
		char[] strArr = str.toCharArray();
		
		if(i != str.length()) {
			// Check if the word is a Slap
			if(isSlap(str)) {
				// Used to make sure that the program doesn't go out of bounds 
				// 'i' will be reset to the last index of the array to check the last char for 'G'
				// If there is a 'G' at the last index, then the word is a Slip
				i = strArr.length - 1;
				if(strArr[i] == 'G') {
					return true;
				}
			}
		}
		return false;
	}

	// This method always starts with an 'A' and ends with a 'C'
	static boolean isSlap(String str) {
		char[] strArr = str.toCharArray();
		
		if(i < strArr.length) {
			// Its first character is an 'A'
			if(strArr[i] == 'A') {
				prev_char = strArr[i];
				i++;
				isSlap(str);
				
				if(i == strArr.length)
					return true;
			}
			
			// If it is a two-character Slap then its second and last character is an 'H'
			if(strArr[i] == 'H' && prev_char == 'A') {
				if(i == 1) {
					return true;
				}
				
				prev_char = strArr[i];
				i++;
				isSlap(str);
				
				if(i == strArr.length)
					return true;
				
				// If it is not a two-character Slap, then it is in one of these two forms:
			} else if(strArr[i] == 'B') {
				prev_char = strArr[i];
				i++;
				isSlap(str);
				
				if(i == strArr.length)
					return true;
				// 'A' followed by a Slip (see above) followed by a 'C'
			} else if ((strArr[i] == 'D' || strArr[i] == 'E') && prev_char == 'A') {
				prev_char = strArr[i];
				i++;
				isSlip(str);
				
				if(i == strArr.length)
					return true;
				// 'A' followed by 'B' followed by a Slap, followed by a 'C'
			} else if (strArr[i] == 'C' && (prev_char == 'H' || prev_char == 'G' || prev_char == 'C')) {
				prev_char = strArr[i];
				i++;
				return true;
			} else {
				return false;
			}
		}
	
		return false;
	}

	static boolean isSlip(String str) {
		char[] strArr = str.toCharArray();
		
		if(i != strArr.length) {
			// First Character needs to be a D or E
			if(strArr[i] == 'D' || strArr[i] == 'E') {
				prev_char = strArr[i];
				i++;
				isSlip(str);
				
				if(i == strArr.length)
					return true;
			}
			
			// The first char needs to have one or more 'F's
			if(strArr[i] == 'F') {
				prev_char = strArr[i];
				i++;
				isSlip(str);
				
				if(i == strArr.length)
					return true;
			}
			
			// The 'F's are followed by either a Slip or a 'G'
			if(strArr[i] == 'G' || (strArr[i] == 'D' || strArr[i] == 'E')) {
				prev_char = strArr[i];
				i++;
				return true;
			} else {
				return false;
			}
		}

		return false;
	}
}
