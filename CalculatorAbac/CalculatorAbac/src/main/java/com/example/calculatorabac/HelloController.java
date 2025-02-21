package com.example.calculatorabac;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;

public class HelloController {
    public GridPane gpCalculator;
    public TextField tfInput;
    public Button btnSeven;
    public Button btnEight;
    public Button btnNine;
    public Button btnFour;
    public Button btnFive;
    public Button btnSIx;
    public Button btnOne;
    public Button btnTwo;
    public Button btnThree;
    public Button btnZero;
    public Button btnPeriod;
    public Button btnEquals;
    public Button btnMultiply;
    public Button btnSubtract;
    public Button btnAdd;
    public Button btnDivide;
    public Button btnClearAll;
    public Button btnClear;
    private double res = 0;
    private String op = "";
    private boolean bago = false;

    @FXML
    public void initialize(){
        tfInput.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                String keyText = keyEvent.getText();

                if(keyText == null || keyText.isEmpty()){
                    return;
                }

                if (keyEvent.isShiftDown()) {
                    switch (keyEvent.getCode()) {
                        case DIGIT8:
                            keyText = "*";
                            break;
                        case EQUALS:
                            keyText = "+";
                            break;
                        default:
                            break;
                    }
                }

                if (keyText.matches("[0-9+\\-*/.]")) {
                    switch(keyText){
                        case "+":
                            operationToUse("+");
                            break;
                        case "-":
                            operationToUse("-");
                            break;
                        case "*":
                            operationToUse("x");
                            break;
                        case "/":
                            operationToUse("/");
                            break;
                        case ".":
                            Platform.runLater(()->onPeriodButtonClicked());
                            break;
                        case "0", "1", "2", "3", "4", "5", "6", "7", "8", "9":
                            addDigit("");
                            break;
                        default:
                            tfInput.setText("Error");
                            break;
                    }
                }else if (keyEvent.getCode() == KeyCode.BACK_SPACE) {
                    Platform.runLater(() -> onClearButtonClicked());
                }else if (keyEvent.getCode() == KeyCode.ESCAPE) {
                    Platform.runLater(() -> onClearButtonClicked());
                }else{
                    tfInput.clear();
                    tfInput.setText("Error");
                    Platform.runLater(()->tfInput.requestFocus());
                }
                keyEvent.consume();
                Platform.runLater(() -> tfInput.positionCaret(tfInput.getText().length()));
            }
        });
    }

    private void operationToUse(String operator){
        if(!tfInput.getText().isEmpty() && !tfInput.getText().equals("Error")){
            try{
                res = Double.parseDouble(tfInput.getText());
                op = operator;
//                tfSpare.setText(tfInput.getText() + " " + op);

                bago = true;
            }catch(NumberFormatException e){
                tfInput.setText("Error");
            }
        }
    }


    @FXML
    public void onAddButtonClicked(){
        operationToUse("+");

    }

    @FXML
    public void onMinusButtonClicked(){
        operationToUse("-");
    }

    @FXML
    public void onTimesButtonClicked(){
        operationToUse("*");
    }

    @FXML
    public void onDivideButtonClicked(){
        operationToUse("/");
    }

    @FXML
    public void onOneButtonClicked(){
        tfInput.setText(tfInput.getText() + "1");
    }

    private void addDigit(String digit){
        if (!bago) {
            tfInput.setText(tfInput.getText() + digit);
        } else {
            bago = false;
            tfInput.setText(digit);
        }
        tfInput.positionCaret(tfInput.getText().length());
    }


    @FXML
    public void onClearAllButtonClicked(){

        tfInput.clear();
        res = 0;
        op = "";
        bago = false;
    }

    @FXML
    public void onTwoButtonClicked(){
        addDigit( "2");
    }

    @FXML
    public void onThreeButtonClicked(){
        addDigit("3");
    }

    @FXML
    public void onFourButtonClicked(){
        addDigit("4");
    }

    @FXML
    public void onFiveButtonClicked(){
        addDigit("5");
    }

    @FXML
    public void onSixButtonClicked(){
        addDigit("6");
    }

    @FXML
    public void onSevenButtonClicked(){
        addDigit( "7");
    }

    @FXML
    public void onEightButtonClicked(){
        addDigit("8");
    }

    @FXML
    public void onNineButtonClicked(){
        addDigit( "9");
    }

    @FXML
    public void onPeriodButtonClicked(){
        if(!tfInput.getText().contains(".")){
            tfInput.setText(tfInput.getText() + ".");
        }
    }

    @FXML
    public void onZeroButtonClicked(){
        addDigit( "0");
    }

    @FXML
    public void onClearButtonClicked(){
        StringBuilder bob = new StringBuilder();
        for(int i = 0; i < tfInput.getLength()-1; i++){
            bob.append(tfInput.getText().charAt(i));
        }
        tfInput.setText(bob.toString());
    }

    @FXML
    public void onEqualsButtonClicked(){
        double result = 0;
        if(!op.isEmpty() && !tfInput.getText().isEmpty()){
            try{
                double spare = Double.parseDouble(tfInput.getText());
                switch (op){
                    case "+":
                        res += spare;
                        break;
                    case "-":
                        res -= spare;
                        break;
                    case "x":
                        res *= spare;
                        break;
                    case "/":
                        if(spare == 0){
                            tfInput.setText("Undefined");
                            op = "";
                            return;
                        }else{
                            res /= spare;
                        }
                        break;
                    default:
                        res = 0;
                }
                result = res;

                if(res % 1 == 0){
                    tfInput.setText(String.valueOf((int)result));
                }else{
                    tfInput.setText(String.valueOf(result));
                }

                op = "";
                bago = true;

                tfInput.requestFocus();
                tfInput.positionCaret(tfInput.getText().length());
            }catch (NumberFormatException e){
                tfInput.setText("Error");
                tfInput.requestFocus();
                tfInput.positionCaret(tfInput.getText().length());
            }
        }
        tfInput.setText(result + "");
    }
}