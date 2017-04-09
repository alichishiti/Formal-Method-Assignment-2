package sat_solver;

public class MakeCNF {

    private final char[] binaryOperators;
    private final char unaryOperator;
    private boolean alphabet;
    private boolean binary;
    private boolean unary;
    private char element;
    private int bracket;
    private String expression;
    private String updatedString;

    public MakeCNF() {
        this.unary = true;
        this.binary = false;
        this.alphabet = true;
        this.bracket = 0;
        this.binaryOperators = new char[]{'&', '|', '>', '='};
        this.unaryOperator = '~';
    }

    public MakeCNF(String expression) {
        this.unary = true;
        this.binary = false;
        this.alphabet = true;
        this.bracket = 0;
        this.binaryOperators = new char[]{'&', '|', '>', '='};
        this.unaryOperator = '~';
        this.expression = expression;
        updatedString = expression;
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

    public void Bijection(){
        for (int i = 0; i < expression.length(); i++) {
            if (expression.charAt(i) == '=')
                handleBijection(i);
        }
    }
    public void Implication() {
        for (int i = 0; i < expression.length(); i++) {
            if (expression.charAt(i) == '>')
                handleImplication(i);
        }
    }

    public void handleBijection(int index) {
        String leftSide ="";
        String midLeft ="";
        String rightSide ="";
        String midRight ="";

            for (int i = index-1; i >= 0; i--) {
                if (expression.charAt(i) == ')')
                    this.bracket++;
                else if (expression.charAt(i) == '(')
                    this.bracket--;

                if (this.bracket == 0) {
                    leftSide = expression.substring(0, i);
                    midLeft = expression.substring(i, index);
                    break;
                }
                
            }
            
            for (int i = index+1; i < this.expression.length(); i++) {
                if (expression.charAt(i) == '(') 
                    this.bracket++;
                else if (expression.charAt(i) == ')') 
                    this.bracket--;
                 
                if (this.bracket == 0) {
                    rightSide = expression.substring(i+1, expression.length());
                    midRight = expression.substring(index+1, i+1);
                    break;
                }
            }            
            this.updatedString =  leftSide + '(' + midLeft + ">" + midRight + ")&(" + midRight + ">" + midLeft  + ')' + rightSide;
            this.expression= this.updatedString;
        }
    
    private void handleImplication(int index) {

        String leftSide ="";
        String midLeft ="";
        String rightSide ="";
        String midRight ="";

            for (int i = index-1; i >= 0; i--) {
                if (expression.charAt(i) == ')')
                    this.bracket++;
                else if (expression.charAt(i) == '(')
                    this.bracket--;

                if (this.bracket == 0) {
                    leftSide = expression.substring(0, i);
                    midLeft = expression.substring(i, index);
                    break;
                }
                
            }
            
            for (int i = index+1; i < this.expression.length(); i++) {
                if (expression.charAt(i) == '(') 
                    this.bracket++;
                else if (expression.charAt(i) == ')') 
                    this.bracket--;
                 
                if (this.bracket == 0) {
                    rightSide = expression.substring(i+1, expression.length());
                    midRight = expression.substring(index+1, i+1);
                    break;
                }
            }            
            this.updatedString =  leftSide + "(~" + midLeft + "|" + midRight + ")" + rightSide;
            this.expression= this.updatedString;

    }

    public String makeCNF() {

        System.out.println(this.expression);

        Bijection();
        System.out.println(this.expression);

        Implication();
        System.out.println(this.expression);

        checkNegation();
        System.out.println(this.expression);

        return this.expression;
    }

    private void checkNegation() {
        char nextElement;
        boolean disable = false;
        int applyNegation = 0;
        String result = "";
        this.bracket = 0;

        for (int i = 0; i < this.expression.length(); i++) {
            this.element = this.expression.charAt(i);

            if (disable) {
                disable = false;
                continue;
            }

            if ((this.element != '~') && ((applyNegation % 2) == 0)) {
                if (this.element == ')') {
                    applyNegation--;
                }
                result += this.element;
            } else if ((this.element != '~') && ((applyNegation % 2) == 1)) {
                if (isAlphabet(this.element)) {
                    result += '~';
                    result += this.element;
                } else if (isBinaryOperator(this.element)) {
                    if (this.element == '&') {
                        result += '|';
                    } else {
                        result += '&';
                    }
                } else {
                    if (this.element == ')') {
                        applyNegation--;
                    }
                    result += this.element;
                }
            } else if (this.element == '~') {
                nextElement = this.expression.charAt(i + 1);
                if (nextElement == '~') {
                    disable = true;
                    continue;
                } else if (nextElement == '(') {
                    applyNegation++;
                    continue;
                } else if ((applyNegation % 2) == 1) {
                    result += nextElement;
                    disable = true;
                    continue;
                } else {
                    result += this.element;
                }
            }
        }

        this.expression = result;
    }

}
