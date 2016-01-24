import java.util.Stack;

public class Poland {
	// Method Calculate takes expression as string and return result
		// This method calls another methods of current class
	static public Fraction Calculate(String input) {
		if (input.startsWith("-"))
			input = "0" + input;
		String output = GetExpression(input); //Transformation of expression to postfix form
		Fraction result = Counting(output); // returned expression solving
		return result; // return result
	}
	
	//This method transforms string to postfix form
	static private String GetExpression(String input) {
		String output = ""; //String for expression
		Stack<Character> operStack = new Stack<Character>(); //Stack for operators

		for (int i = 0; i < input.length(); i++) {  //for each symbol in imputed string
			// Avoid separators!
			if (IsDelimeter(input.charAt(i)))
				continue; // Go to the next symbol

			//If symbol is digit -> read all number
			if (Character.isDigit(input.charAt(i))) // If symbol is digit
			{
				//Read untill separator or operator (to get a number)
				while (!IsDelimeter(input.charAt(i)) && !IsOperator(input.charAt(i))) {
					output += input.charAt(i); // Add each digit to output string
					i++; // Go to the next symbol
					if (i == input.length())
						break; // If symbol is the last -> break
				}

				output += " "; //Finish our string-expression with space
				i--; //Make one step back (where symbol before separator
			}

			// If symbol is operator
			if (IsOperator(input.charAt(i))) // If operator
			{
				if (input.charAt(i) == '(') // If symbol is "("
					operStack.push(input.charAt(i));  // Write to the stack
				else if (input.charAt(i) == ')') // If symbol is ")"
				{
					//Write all operators until "(" to the string
					Character s = operStack.pop();

					while (s != '(') {
						output += s.toString() + ' ';
						s = operStack.pop();
					}
				} 
				else //If another operator
				{
					if (operStack.size() > 0) //If there are elements in stack
						if (GetPriority(input.charAt(i)) <= GetPriority(operStack.peek())) 
							//And if priority of our operator is less or equal priority of operator in the peek of stack 
							output += operStack.pop().toString() + " "; // Then add last operator from stack to the string-expression
					operStack.push(input.charAt(i)); // if stack is empty or priority of operator is greater -> then add operator to the peek of stack
				}
			}
		}

		// When we processed all symbols -> purge all residual operators to the string
		while (operStack.size() > 0)
			output += operStack.pop() + " ";

		return output;  //Return expression in postfix form!!!
	}

	// This method calculates value of expression (expression is already in postfix form)
	static private Fraction Counting(String input) {
		Fraction result = new Fraction(0);
		// double result = 0; // Result
		// Stack<Double> temp = new Stack<Double>();
		Stack<Fraction> temp = new Stack<Fraction>(); //Stack for answers
		for (int i = 0; i < input.length(); i++) { //For each symbol in the string
			// If the symbol is digit -> read the number and write to the peek of stack
			if (Character.isDigit(input.charAt(i))) {
				String a = "";

				while (!IsDelimeter(input.charAt(i)) && !IsOperator(input.charAt(i))) // Until it is not separator
				{
					a += input.charAt(i); // add
					i++;
					if (i == input.length())
						break;
				}
				temp.push(new Fraction(a)); // Write to the stack
				// temp.push(Double.parseDouble(a));
				i--;
			} else if (IsOperator(input.charAt(i))) // If symbol if operator
			{
				//Take two lastvalues from stack
				// Double a = temp.pop();
				// Double b = temp.pop();
				Fraction a = temp.pop();
				Fraction b = temp.pop();
				switch (input.charAt(i)) // And calculate them according to the current operator
				{
				case '+':
					result = Fraction.sum(b, a);
					break;
				case '-':
					result = Fraction.dif(b, a);
					break;
				case '*':
					result = Fraction.mult(b, a);
					break;
				case '/':
					result = Fraction.div(b, a);
					break;
				/*
				 * case '^': //result =
				 * Double.parseDouble(Math.pow(Double.parseDouble(b.toString()),
				 * Double.parseDouble(a.toString())).toString()); result =
				 * Math.pow(Double.parseDouble(b.toString()),
				 * Double.parseDouble(a.toString())); result =
				 * Double.parseDouble(Double.toString(result)); break;
				 */
				}
				temp.push(result); // New result is written to the stack
				
			}
		}
		return temp.peek(); // We take result of all calculations from stack and return them		
	}

	// small functions:
	// Method returns true if verifiable symbol is separator (space or =)
	static private boolean IsDelimeter(char c) {
		if ((" =".indexOf(c) != -1))
			return true;
		return false;
	}

	// Method returns true if verifiable symbol is separator
	static private boolean IsOperator(char СЃ) {
		if (("+-/*^()".indexOf(СЃ) != -1))
			return true;
		return false;
	}

	// Method returns priority of operator
	static private byte GetPriority(char s) {
		switch (s) {
		case '(':
			return 0;
		case ')':
			return 1;
		case '+':
			return 2;
		case '-':
			return 3;
		case '*':
			return 4;
		case '/':
			return 4;
		/*
		 * case '^': return 5;
		 */
		default:
			return 6;
		}
	}
}