import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea ;
import javafx.scene.control.TextField ;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.CheckBox;
import javafx.scene.control.RadioButton;

public class Controller {

    AccountDatabase database = new AccountDatabase() ;

    @FXML
    private TextArea ocOutput ;

    @FXML
    private ToggleGroup OpenClose, ocAccountType ;

    @FXML
    private TextField firstName, lastName, initialBal, month, day, year ;

    @FXML
    private CheckBox directDeposit, loyalCustomer ;

    @FXML
    public void ocSubmit(ActionEvent event) {

        event.consume() ;

        RadioButton cmd = (RadioButton)OpenClose.getSelectedToggle() ;
        //ocOutput.appendText(cmd.getText() + "\n") ;

        RadioButton type = (RadioButton)ocAccountType.getSelectedToggle() ;
        //ocOutput.appendText(type.getText() + "\n") ;

        if (firstName.getText().isEmpty()) {

            ocOutput.appendText("Please enter a first name!\n") ;
            return ;

        }

        if (lastName.getText().isEmpty()) {

            ocOutput.appendText("Please enter a last name!\n") ;
            return ;

        }

        try {

            Double.valueOf(initialBal.getText()) ;

        } catch (NumberFormatException numberFormatException) {

            ocOutput.appendText("Invalid balance\n") ;
            return ;

        }

        if (month.getText().isEmpty()) {

            ocOutput.appendText("Please enter a month!\n") ;
            return ;

        }

        if (day.getText().isEmpty()) {

            ocOutput.appendText("Please enter a day!\n") ;
            return ;

        }

        if (year.getText().isEmpty()) {

            ocOutput.appendText("Please enter a year!\n") ;
            return ;

        }

        Date date = new Date() ;

        date.setDate(new String[]{month.getText(), day.getText(), year.getText()});

        if (!date.isValid()) {

            ocOutput.appendText("Invalid date!\n") ;
            return ;

        }


    }

    @FXML
    public void open(ActionEvent event) {

        event.consume() ;
        

    }

    @FXML
    public void print(ActionEvent event) {

        event.consume() ;
        if (checkEmptyDatabase(database.getAccounts())) {

            ocOutput.appendText("Database is empty!\n") ;
            return ;
        }

    }

    @FXML
    public void onChecking(ActionEvent event) {

        event.consume() ;
        directDeposit.setDisable(false) ;
        loyalCustomer.setDisable(true) ;
        loyalCustomer.setSelected(false) ;

    }

    @FXML
    public void onSavings(ActionEvent event) {

        event.consume() ;
        directDeposit.setDisable(true) ;
        loyalCustomer.setDisable(false) ;
        directDeposit.setSelected(false) ;

    }

    @FXML
    public void onMoneyMarket(ActionEvent event) {

        event.consume() ;
        directDeposit.setDisable(true) ;
        loyalCustomer.setDisable(true) ;
        directDeposit.setSelected(false) ;
        loyalCustomer.setSelected(false) ;

    }

    private boolean checkEmptyDatabase(Account[] a) {

        for (int i = 0; i < a.length; i++) {

            if (a[i] != null) {

                return false;

            }

        }

        return true;

    }
    
}
