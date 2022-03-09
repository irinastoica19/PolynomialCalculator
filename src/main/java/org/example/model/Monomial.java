package org.example.model;

public class Monomial {
    private float coefficient;
    private int power;
    private String stringForm;

    public Monomial(){}

    public Monomial(String stringFrom){
        this.stringForm = stringFrom;
        convertMonomialFromString();
    }

    public Monomial(float coefficient, int power){
        this.coefficient = coefficient;
        this.power = power;
    }

    public void convertMonomialFromString(){
        int sign;
        if(this.stringForm != null) {
            if (this.stringForm.charAt(0) == '+') {
                sign = 1;
            } else {
                sign = -1;
            }
            String[] elements = this.stringForm.split("\\^|x|[+-]");
            if (this.stringForm.contains("x")) {
                if (this.stringForm.charAt(1) == 'x') {
                    this.coefficient = sign;
                } else {
                    this.coefficient = sign * Integer.parseInt(elements[1]);
                }
                if (this.stringForm.contains("x^")){
                    this.power = Integer.parseInt(elements[3]);
                } else {
                    this.power = 1;
                }
            } else {
                this.coefficient = sign * Integer.parseInt(elements[1]);
                this.power = 0;
            }
        }
    }

    @Override
    public String toString(){
        String coefficientString;
        if(coefficient % (int) coefficient == 0){
            if( (int) coefficient == 1){
                coefficientString = "+";
            } else if ((int) coefficient == -1){
                coefficientString = "-";
            } else if ((int) coefficient > 0) {
                coefficientString = "+" + (int) coefficient;
            } else {
                coefficientString = (int) coefficient + "";
            }
        } else {
            coefficientString = coefficient + "";
        }
        if(power == 0){
            if(coefficient == 1.0 || coefficient == -1.0){
                return coefficientString + "1";
            }
            return coefficientString;
        } else if(power == 1){
            return coefficientString + "x";
        } else {
            return coefficientString + "x^" + power;
        }
    }

    public boolean equalsMonomial(Monomial m){
        return m.power==this.power && m.coefficient==this.coefficient;
    }

    public boolean hasSamePowerAs(Monomial x){
        return this.power == x.power;
    }

    public float getCoefficient() {
        return coefficient;
    }

    public void setCoefficient(float coefficient) {
        this.coefficient = coefficient;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }
}