package org.example.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Polynomial {
    private ArrayList<Monomial> monomialList = new ArrayList<>();
    private String error = null;

    public Polynomial(){ }

    public Polynomial(String polynomial){
        if(checkPolynomial(polynomial)) {
            String POLYNOMIAL_PATTERN = "(([+-][0-9]*x\\^[0-9]+)|([+-][0-9]*x)|([+-][0-9]+))";
            Matcher monomialMatcher = Pattern.compile(POLYNOMIAL_PATTERN).matcher(polynomial);
            while (monomialMatcher.find()) {
                Monomial m = new Monomial(monomialMatcher.group());
                this.monomialList.add(m);
            }
            reduce();
        } else {
            error = "Invalid polynomial syntax!";
        }
    }

    public void sortPolynomial(){
        for(int i =0; i < monomialList.size() - 1; i++){
            for(int j = 0; j < monomialList.size() - i - 1; j++){
                if(monomialList.get(j).getPower() < monomialList.get(j+1).getPower()){
                    Collections.swap(monomialList, j, j+1);
                }
            }
        }
    }

    public boolean checkPolynomial(String polynomialString){
        String POLYNOMIAL_PATTERN = "(([[+-][0-9]*x]?\\^[0-9]+)|([+-][0-9]*x)|([+-][1-9][0-9]*))+";
        return polynomialString.matches(POLYNOMIAL_PATTERN);
    }

    @Override
    public String toString(){
        StringBuilder string = new StringBuilder();
        for(Monomial m : monomialList){
            string.append(m.toString());
        }
        return string.toString();
    }

    public Polynomial integrate(){
        for (Monomial monomial : monomialList) {
            monomial.setPower(monomial.getPower() + 1);
            monomial.setCoefficient(monomial.getCoefficient() / monomial.getPower());
        }
        return this;
    }

    public Polynomial derive(){
        for(int i = 0; i < monomialList.size(); i++){
            monomialList.get(i).setCoefficient(monomialList.get(i).getCoefficient() * monomialList.get(i).getPower());
            monomialList.get(i).setPower(monomialList.get(i).getPower() - 1);
            monomialList.removeIf(pol ->(pol.getPower() < 0));
        }
        if(monomialList.size()<1){
            monomialList.add(new Monomial(0,0));
        }
        return this;
    }

    public void reduce(){
        sortPolynomial();
        ArrayList<Monomial> allResults = new ArrayList<>();
        for(int i = 0; i < monomialList.size(); i++){
            Monomial resultAfterSimplification = monomialList.get(i);
            for(int j = i+1; j < monomialList.size(); j++) {
                if (monomialList.get(i).hasSamePowerAs(monomialList.get(j))) {
                    resultAfterSimplification.setCoefficient(resultAfterSimplification.getCoefficient() + monomialList.get(j).getCoefficient());
                    monomialList.remove(monomialList.get(j));
                }
            }
            if(resultAfterSimplification.getCoefficient() != 0.0) {
                allResults.add(resultAfterSimplification);
            }
        }
        this.monomialList = allResults;
    }

    public ArrayList<Monomial> getMonomialList() {
        return monomialList;
    }

    public String getError() {
        return error;
    }

}