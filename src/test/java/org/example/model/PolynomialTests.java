package org.example.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PolynomialTests {


    public boolean equalsPolynomial(Polynomial p1, Polynomial p2){
        if(p1.getMonomialList().size()==p2.getMonomialList().size()) {
            for (int i = 0; i < p1.getMonomialList().size(); i++){
                if(!p1.getMonomialList().get(i).equalsMonomial(p2.getMonomialList().get(i))){
                    return false;
                }
            }
        } else {
            return false;
        }
        return true;
    }

    @Test
    public void equalsTest(){
        Polynomial p1 = new Polynomial();
        p1.getMonomialList().add(new Monomial(2,2));
        p1.getMonomialList().add(new Monomial(1,1));
        p1.getMonomialList().add(new Monomial(3,0));
        Polynomial p2 = new Polynomial();
        p2.getMonomialList().add(new Monomial(2,2));
        p2.getMonomialList().add(new Monomial(1,1));
        p2.getMonomialList().add(new Monomial(3,0));
        assertTrue(equalsPolynomial(p1, p2));
    }


    @Test
    public void sortPolynomialTest(){
        Polynomial p = new Polynomial();
        p.getMonomialList().add(new Monomial(2,3));
        p.getMonomialList().add(new Monomial(-5,1));
        p.getMonomialList().add(new Monomial(7,2));
        p.getMonomialList().add(new Monomial(3,0));
        p.getMonomialList().add(new Monomial(1,4));
        p.sortPolynomial();
        Polynomial expectedResult = new Polynomial();
        expectedResult.getMonomialList().add(new Monomial(1,4));
        expectedResult.getMonomialList().add(new Monomial(2,3));
        expectedResult.getMonomialList().add(new Monomial(7,2));
        expectedResult.getMonomialList().add(new Monomial(-5,1));
        expectedResult.getMonomialList().add(new Monomial(3,0));
        assertTrue(equalsPolynomial(p,expectedResult));

    }

    @Test
    public void addOperationTest(){
        Operations operation = new Operations();
        Polynomial p1 = new Polynomial();
        p1.getMonomialList().add(new Monomial(2,2));
        p1.getMonomialList().add(new Monomial(1,1));
        p1.getMonomialList().add(new Monomial(3,0));
        Polynomial p2 = new Polynomial();
        p2.getMonomialList().add(new Monomial(-1,2));
        p2.getMonomialList().add(new Monomial(3,1));
        p2.getMonomialList().add(new Monomial(2,0));
        Polynomial result = operation.addOrSubtractPolynomials(p1,p2,OperationType.ADD);
        Polynomial expectedResult = new Polynomial();
        expectedResult.getMonomialList().add(new Monomial(1,2));
        expectedResult.getMonomialList().add(new Monomial(4,1));
        expectedResult.getMonomialList().add(new Monomial(5,0));
        assertTrue(equalsPolynomial(result,expectedResult));
    }

    @Test
    public void subtractOperationTest(){
        Operations operation = new Operations();
        Polynomial p1 = new Polynomial();
        p1.getMonomialList().add(new Monomial(2,2));
        p1.getMonomialList().add(new Monomial(1,1));
        p1.getMonomialList().add(new Monomial(3,0));
        Polynomial p2 = new Polynomial();
        p2.getMonomialList().add(new Monomial(-1,2));
        p2.getMonomialList().add(new Monomial(3,1));
        p2.getMonomialList().add(new Monomial(2,0));
        Polynomial result = operation.addOrSubtractPolynomials(p1,p2,OperationType.SUBTRACT);
        Polynomial expectedResult = new Polynomial();
        expectedResult.getMonomialList().add(new Monomial(3,2));
        expectedResult.getMonomialList().add(new Monomial(-2,1));
        expectedResult.getMonomialList().add(new Monomial(1,0));
        assertTrue(equalsPolynomial(result,expectedResult));
    }

    @Test
    public void multiplyOperationTest(){
        Operations operation = new Operations();
        Polynomial p1 = new Polynomial();
        p1.getMonomialList().add(new Monomial(2,2));
        p1.getMonomialList().add(new Monomial(1,1));
        p1.getMonomialList().add(new Monomial(3,0));
        Polynomial p2 = new Polynomial();
        p2.getMonomialList().add(new Monomial(-1,2));
        p2.getMonomialList().add(new Monomial(3,1));
        p2.getMonomialList().add(new Monomial(2,0));
        Polynomial result = operation.multiplyPolynomial(p1,p2);
        Polynomial expectedResult = new Polynomial();
        expectedResult.getMonomialList().add(new Monomial(-2,4));
        expectedResult.getMonomialList().add(new Monomial(5,3));
        expectedResult.getMonomialList().add(new Monomial(4,2));
        expectedResult.getMonomialList().add(new Monomial(11,1));
        expectedResult.getMonomialList().add(new Monomial(6,0));
        assertTrue(equalsPolynomial(result,expectedResult));
    }

    @Test
    public void divideOperationTest(){
        Operations operation = new Operations();
        Polynomial p1 = new Polynomial();
        p1.getMonomialList().add(new Monomial(1,3));
        p1.getMonomialList().add(new Monomial(-2,2));
        p1.getMonomialList().add(new Monomial(6,1));
        p1.getMonomialList().add(new Monomial(-5,0));
        Polynomial p2 = new Polynomial();
        p2.getMonomialList().add(new Monomial(1,2));
        p2.getMonomialList().add(new Monomial(-1,0));
        String result = operation.dividePolynomial(p1,p2);
        assertEquals("Q: +x-2, R: +7x-7", result);
    }

    @Test
    public void deriveOperationTest(){
        Polynomial p= new Polynomial();
        p.getMonomialList().add(new Monomial(1,3));
        p.getMonomialList().add(new Monomial(-2,2));
        p.getMonomialList().add(new Monomial(6,1));
        p.getMonomialList().add(new Monomial(-5,0));
        p.derive();
        Polynomial expectedResult = new Polynomial();
        expectedResult.getMonomialList().add(new Monomial(3,2));
        expectedResult.getMonomialList().add(new Monomial(-4,1));
        expectedResult.getMonomialList().add(new Monomial(6,0));
        assertTrue(equalsPolynomial(p, expectedResult));
    }

    @Test
    public void integrateOperationTest(){
        Polynomial p= new Polynomial();
        p.getMonomialList().add(new Monomial(8,3));
        p.getMonomialList().add(new Monomial(-3,2));
        p.getMonomialList().add(new Monomial(6,1));
        p.getMonomialList().add(new Monomial(-5,0));
        p.integrate();
        Polynomial expectedResult = new Polynomial();
        expectedResult.getMonomialList().add(new Monomial(2,4));
        expectedResult.getMonomialList().add(new Monomial(-1,3));
        expectedResult.getMonomialList().add(new Monomial(3,2));
        expectedResult.getMonomialList().add(new Monomial(-5,1));
        assertTrue(equalsPolynomial(p, expectedResult));
    }

}
