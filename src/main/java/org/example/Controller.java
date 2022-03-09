package org.example;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.example.model.OperationType;
import org.example.model.Operations;
import org.example.model.Polynomial;

public class Controller {

    @FXML
    public Button additionButton;
    @FXML
    public Button subtractionButton;
    @FXML
    public Button multiplicationButton;
    @FXML
    public Button derivationButton;
    @FXML
    public Button divisionButton;
    @FXML
    public Button integrationButton;
    @FXML
    public Button clearFirstTextbox;
    @FXML
    public Button clearSecondTextbox;
    @FXML
    public TextField firstPolynomialTextField;
    @FXML
    public TextField secondPolynomialTextField;
    @FXML
    public TextField resultTextField;
    @FXML
    public Label errorLabel;
    @FXML
    private javafx.scene.control.Button closeButton;


    private void performOperation(OperationType operationType){
        Polynomial polynomial = new Polynomial(firstPolynomialTextField.getText());
        Operations operation = new Operations(firstPolynomialTextField.getText(), secondPolynomialTextField.getText());
        boolean derivativeAndIntegration = polynomial.getError()==null && (operationType==OperationType.DERIVE || operationType==OperationType.INTEGRATE);
        if(operation.getError() == null || derivativeAndIntegration){
            String result;
            switch (operationType) {
                case ADD:
                    result = operation.addOrSubtractPolynomials(operation.getP1(), operation.getP2(), OperationType.ADD).toString(); break;
                case SUBTRACT:
                    result = operation.addOrSubtractPolynomials(operation.getP1(), operation.getP2(), OperationType.SUBTRACT).toString(); break;
                case MULTIPLY:
                    result = operation.multiplyPolynomial(operation.getP1(), operation.getP2()).toString(); break;
                case DIVIDE:
                    result = operation.dividePolynomial(operation.getP1(), operation.getP2()); break;
                case DERIVE:
                    result = polynomial.derive().toString(); break;
                case INTEGRATE:
                    result = polynomial.integrate().toString(); break;
                default:
                    throw new IllegalStateException("Unexpected value: " + operationType);
            }
            resultTextField.setText(result);
            errorLabel.setText("");
            if(operationType==OperationType.DIVIDE)
                handleDivisionErrors(operation);
        } else {
            handleError(operationType, polynomial, operation);
        }
    }

    private void handleDivisionErrors(Operations operation){
        if(operation.getError()!=null){
            resultTextField.setText("");
            errorLabel.setTextFill(Color.color(1, 0, 0));
            errorLabel.setAlignment(Pos.CENTER);
            errorLabel.setText(operation.getError());
        }
    }

    private void handleError(OperationType operationType, Polynomial polynomial, Operations operation){
        resultTextField.setText("");
        errorLabel.setTextFill(Color.color(1, 0, 0));
        errorLabel.setAlignment(Pos.CENTER);
        if (operationType == OperationType.INTEGRATE || operationType == OperationType.DERIVE)
            errorLabel.setText((operation.getError()));
        else
            errorLabel.setText((operation.getError()));
    }

    @FXML
    public void addPolynomials(ActionEvent event){
        performOperation(OperationType.ADD);
    }

    @FXML
    public void subtractPolynomials(ActionEvent event){
        performOperation(OperationType.SUBTRACT);
    }

    @FXML
    public void multiplyPolynomials(ActionEvent event){
        performOperation(OperationType.MULTIPLY);
    }

    @FXML
    public void dividePolynomials(ActionEvent event){ performOperation(OperationType.DIVIDE); }

    @FXML
    public void derivePolynomial(ActionEvent event) {
        performOperation(OperationType.DERIVE);
    }

    @FXML
    public void integratePolynomial(ActionEvent event) {
        performOperation(OperationType.INTEGRATE);
    }

    @FXML
    public void clearFirstPolynomial(ActionEvent event){
        firstPolynomialTextField.setPromptText("Enter a polynomial");
        firstPolynomialTextField.setText("");
    }

    @FXML
    public void clearSecondPolynomial(ActionEvent event){
        secondPolynomialTextField.setPromptText("Enter a polynomial");
        secondPolynomialTextField.setText("");
    }

    @FXML
    private void closeButtonAction(){
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }

}