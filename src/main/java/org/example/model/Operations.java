package org.example.model;

public class Operations {
    private Polynomial p1;
    private Polynomial p2;
    private String error = null;

    public Operations(){}

    public Operations(String firstPolynomial, String secondPolynomial) {
        p1 = new Polynomial(firstPolynomial);
        p2 = new Polynomial(secondPolynomial);
        if(p1.getError() != null && p2.getError() != null){
            error = "Invalid polynomial syntax! Please enter valid polynomials";
        } else if (p1.getError() != null){
            error = "The first polynomial is invalid. Please enter valid syntax.";
        } else if (p2.getError() != null){
            error = "The second polynomial is invalid. Please enter valid syntax.";
        }
    }

    public Polynomial addOrSubtractPolynomials(Polynomial firstPolynomial, Polynomial secondPolynomial, OperationType operationType){
        Polynomial result = new Polynomial();
        result.getMonomialList().addAll(firstPolynomial.getMonomialList());
        if (operationType == OperationType.SUBTRACT) { // if we subtract we have to negate the coefficient of all monomials of the second polynomial
            for (Monomial m : secondPolynomial.getMonomialList()) {
                m.setCoefficient(m.getCoefficient() * (-1));
            }
        }
        result.getMonomialList().addAll(secondPolynomial.getMonomialList());
        result.reduce();
        result.reduce();
        if(result.getMonomialList().size()==0){
            result.getMonomialList().add(new Monomial(0,0));
        }
        return result;
    }

    public Polynomial multiplyPolynomial(Polynomial p1, Polynomial p2){
        Polynomial result = new Polynomial();
        for(Monomial m1: p1.getMonomialList()){
            for(Monomial m2: p2.getMonomialList()){
                Monomial toAdd = new Monomial(m1.getCoefficient() * m2.getCoefficient(), m1.getPower() + m2.getPower());
                if(toAdd.getCoefficient() != 0.0){
                    result.getMonomialList().add(toAdd);
                }
            }
        }
        result.reduce();
        result.reduce();
        if(result.getMonomialList().size()==0){
            result.getMonomialList().add(new Monomial(0,0));
        }
        return result;
    }

    public String dividePolynomial(Polynomial p1, Polynomial p2){
        Polynomial remainder = new Polynomial();
        Polynomial result = new Polynomial();
        p1.reduce();
        String exceptionResult = divisionExceptions(p1,p2);
        if (exceptionResult!=null){
            return exceptionResult;
        }
        else {
            while (p1.getMonomialList().size() > 0 && p1.getMonomialList().get(0).getPower() >= p2.getMonomialList().get(0).getPower()) {
                float coefficient = p1.getMonomialList().get(0).getCoefficient() / p2.getMonomialList().get(0).getCoefficient();
                int power = p1.getMonomialList().get(0).getPower() - p2.getMonomialList().get(0).getPower();
                Monomial quotientElement = new Monomial(coefficient, power);
                Polynomial quotient = new Polynomial();
                quotient.getMonomialList().add(quotientElement);
                result.getMonomialList().add(quotientElement);
                Polynomial toSubtract = multiplyPolynomial(quotient, p2);
                remainder = addOrSubtractPolynomials(p1, toSubtract, OperationType.SUBTRACT);
                p1 = remainder;
            }
        }

        return "Q: " + result.toString() + ", R: " + remainder.toString();
    }

    public String divisionExceptions(Polynomial p1, Polynomial p2){
        Polynomial result;
        p1.reduce();
        if (p2.getMonomialList().size() < 1){
            this.error = "Cannot divide by 0";
            return "";
        } else if (p1.getMonomialList().size()<1){
            return "Q: 0, R: 0";
        } else if (p2.getMonomialList().get(0).getPower() == 0){
            for(Monomial m: p1.getMonomialList())
                m.setCoefficient(m.getCoefficient()/p2.getMonomialList().get(0).getCoefficient());
            result = p1;
            return "Q: " + result.toString() + ", R: 0";
        } else if (p1.getMonomialList().get(0).getPower() < p2.getMonomialList().get(0).getPower()) {
            this.error = "Second polynomial has higher degree";
            return "";
        } else {
            return null;
        }
    }

    public Polynomial getP1() {
        return p1;
    }

    public Polynomial getP2() {
        return p2;
    }

    public String getError() {
        return error;
    }

}