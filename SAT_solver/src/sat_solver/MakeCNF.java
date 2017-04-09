package sat_solver;

public class MakeCNF {

    private final char[] binaryOperators;
    private final char unaryOperator;
    private boolean alphabet;
    private boolean binary;
    private boolean unary;
    private char element;
    private int bracket;

    public MakeCNF() {
        this.unary = true;
        this.binary = false;
        this.alphabet = true;
        this.bracket = 0;
        this.binaryOperators = new char[]{'&', '|', '>', '=', '<'};
        this.unaryOperator = '~';
    }

    public boolean syntax(String expression) {

        for (int i = 0; i < expression.length(); i++) {
            element = expression.charAt(i);
            System.out.println("check = " + element);
            if (isAlphabet(element)) {
                if (this.alphabet == false) {
                    return false;
                }
                this.alphabet = false;
                this.binary = true;
                this.unary = false;
            } else if (isBinaryOperator(element)) {
                if (this.binary == false) {
                    return false;
                }
                this.alphabet = true;
                this.binary = false;
                this.unary = true;
            } else if ((element == this.unaryOperator)) {
                if (this.unary == false) {
                    return false;
                }
            } else if (element == '(') {
                if (this.binary == false) {
                    this.bracket += 1;
                } else {
                    return false;
                }
            } else if (element == ')') {
                if ((this.alphabet == false) && (this.bracket >= 0)) {
                    this.bracket -= 1;
                } else {
                    return false;
                }
            } else if (element == ' ') {
                return false;
            }

        }
        return ((this.bracket == 0) && (this.unary == false));
    }

    private boolean isAlphabet(char element) {
        return Character.isLetter(element);
    }

    private boolean isBinaryOperator(char element) {
        for (int i = 0; i < this.binaryOperators.length; i++) {
            if (this.binaryOperators[i] == element) {
                return true;
            }
        }
        return false;
    }

    /*
    public String insertBraces(String expression){
        String s;
        s = negationCheck(expression);
        s = andCheck(s);
        return s;
    }
    
    private String negationCheck(String e){
        String result = "";
        char element2;
        boolean insert = false;
        boolean disable = false;
        int search = 0;
        for (int i = 0; i < e.length(); i++) {
            this.element = e.charAt(i);
            
            if(disable){
                disable = false;
                continue;
            }
            
            if(this.element != '~'){
                result += this.element;
            }
            else
                if(this.element == '~'){
                    result += '(';
                    result += this.element;
                    element2 = e.charAt(i+1);
                    if(element2 != '('){
                        if(element2 == '~'){
                            disable = true;
                            result = result.substring(0, result.length()-2);
                        }
                        else
                            insert = true;
                        continue;
                    }
                    else
                        if(element2 == '(')
                            search += 1;
                            continue;
                }
                        
            if((insert == true) || ((search > 0) && (this.element == ')'))){
                result += ')';
                if(insert)insert = false;
                else search -= 1;
            }
        }
        return result;
    }
     */
    public String makeCNF(String e) {
        String se = checkNegation(e);
        return se;
    }

    private String checkNegation(String e) {
        char nextElement;
        boolean disable = false;
        int applyNegation = 0;
        String result = "";
        this.bracket = 0;

        for (int i = 0; i < e.length(); i++) {
            this.element = e.charAt(i);

            if (disable) {
                disable = false;
                continue;
            }

            if ((this.element != '~') && ((applyNegation % 2) == 0)) {
                if (this.element == ')') 
                        applyNegation--;
                result += this.element;
            } 
            else if ((this.element != '~') && ((applyNegation % 2) == 1)) {
                if (isAlphabet(this.element)) {
                    result += '~';
                    result += this.element;
                }
                else if (isBinaryOperator(this.element)) {
                    if (this.element == '&')
                        result += '|';
                    else
                        result += '&';
                } 
                else {
                    if (this.element == ')')
                        applyNegation--;
                    result += this.element;
                }
            } 
            
            else if (this.element == '~') {
                nextElement = e.charAt(i + 1);
                if (nextElement == '~') {
                    disable = true;
                    continue;
                } else if (nextElement == '(') {
                    applyNegation++;
                    System.out.println(applyNegation);
                    continue;
                }
                else if ((applyNegation % 2) == 1){
                    result += nextElement;
                    disable = true;
                    continue;
                }
                else
                    result += this.element;
            }
        }

        return result;
    }

}
