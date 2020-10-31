import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.Scanner;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea ;
import javafx.scene.control.TextField ;
import javafx.scene.control.ToggleGroup;
import javafx.stage.FileChooser;
import javafx.scene.control.CheckBox;
import javafx.scene.control.RadioButton;

public class Controller {

    AccountDatabase database = new AccountDatabase() ;

    @FXML
    private TextArea ocOutput, dwOutput, adOutput;

    @FXML
    private ToggleGroup OpenClose, ocAccountType, DepositWithdraw, dwAccountType, adCommand;

    @FXML
    private TextField firstName, lastName, initialBal, month, day, year, firstName1, lastName1, amount1;

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
        
        boolean condition = false ;

        if (directDeposit.isSelected() || loyalCustomer.isSelected()) {
            
            condition = true ;
            
        }

        if (cmd.getText().equals("Open")) {

            openAccount(firstName.getText(), lastName.getText(), Double.valueOf(initialBal.getText()), date, type.getText(), condition) ;

        } else {

            closeAccount(firstName.getText(), lastName.getText(), type.getText()) ;
            
        }


    }

    @FXML
    public void dwSubmit(ActionEvent event){

        event.consume() ;

        RadioButton cmd = (RadioButton)DepositWithdraw.getSelectedToggle() ;
        
        RadioButton type = (RadioButton)dwAccountType.getSelectedToggle() ;

        if (firstName1.getText().isEmpty()) {

            dwOutput.appendText("Please enter a first name!\n") ;
            return ;

        }

        if (lastName1.getText().isEmpty()) {

            dwOutput.appendText("Please enter a last name!\n") ;
            return ;

        }

        try {

            Double.valueOf(amount1.getText()) ;

        } catch (NumberFormatException numberFormatException) {

            dwOutput.appendText("Invalid amount input!\n") ;
            return ;

        }

        if(cmd.getText().equals("Deposit")){
            deposit(firstName1.getText(), lastName1.getText(), Double.valueOf(amount1.getText()), type.getText());

        }else if(cmd.getText().equals("Withdraw")){
            withdraw(firstName1.getText(), lastName1.getText(), Double.valueOf(amount1.getText()), type.getText());
        }else{
            dwOutput.appendText("No command selected!");
            return ;
        }
        


    }


    @FXML
    public void adSubmit(ActionEvent event) {

        event.consume() ;

        RadioButton cmd = (RadioButton)adCommand.getSelectedToggle() ;

        if(cmd.getText().equals("Print All Accounts")){
            
            adOutput.appendText(database.printAccounts());

        }else if(cmd.getText().equals("Print All Accounts + fees + sorted by date")){

            adOutput.appendText(database.printByDateOpen());

        }else if(cmd.getText().equals("Print All Accounts + fees + sorted by last names")){
        
            adOutput.appendText(database.printByLastName()) ;

        }else{
            adOutput.appendText("No Command Selected!\n");
            return;
        }

        adOutput.appendText("-------------------------------------\n") ;

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
    
    @FXML
    public void importFile(ActionEvent event) {

        event.consume() ;
        FileChooser fileChooser = new FileChooser() ;
        fileChooser.setTitle("Open account database") ;
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text documents (*.txt)", "*.txt")) ;
        File file = fileChooser.showOpenDialog(ocOutput.getScene().getWindow()) ;

        try  {

            Scanner scanner = new Scanner(file) ;

            while (scanner.hasNextLine()) {

                String line = scanner.nextLine() ;
                String[] split = line.split(",") ;

                Profile profile = new Profile() ;
                profile.setFirstName(split[1]) ;
                profile.setLastName(split[2]) ;

                String[] d = split[4].split("/") ;
                Date date = new Date() ;
                date.setDate(d);

                if (split[0].equals("C")) { // Checking

                    Checking checking = new Checking() ;
                    checking.setHolder(profile) ;
                    checking.setBalance(Double.parseDouble(split[3]));
                    checking.setOpenDate(date);
                    checking.setDirectDeposit(Boolean.valueOf(split[5]));
                    database.add(checking) ;

                } else if (split[0].equals("S")) { // Savings

                    Savings savings = new Savings() ;
                    savings.setHolder(profile) ;
                    savings.setBalance(Double.parseDouble(split[3])) ;
                    savings.setOpenDate(date) ;
                    savings.setLoyal(Boolean.valueOf(split[5])) ;
                    database.add(savings) ;

                } else if (split[0].equals("M")) { // Money Market

                    MoneyMarket moneyMarket = new MoneyMarket() ;
                    moneyMarket.setHolder(profile) ;
                    moneyMarket.setBalance(Double.parseDouble(split[3])) ;
                    moneyMarket.setOpenDate(date) ;
                    moneyMarket.setWithdrawals(Integer.valueOf(split[5])) ;
                    database.add(moneyMarket) ;

                } else { // error?


                }

            }

            scanner.close() ;

        } catch (FileNotFoundException e) {


        }

    }

    @FXML
    public void exportFile(ActionEvent event) {

        event.consume() ;
        FileChooser fileChooser = new FileChooser() ;
        fileChooser.setTitle("Save account database") ;
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text documents (*.txt)", "*.txt")) ;
        File file = fileChooser.showSaveDialog(ocOutput.getScene().getWindow()) ;

        Account[] accounts = database.getAccounts() ;

        try {

            FileWriter writer = new FileWriter(file) ;

            for (int i = 0; i < accounts.length ; i++) {
    
                if (accounts[i] == null) {
    
                    continue;
    
                }
    
                String output = "," + accounts[i].getHolder().getFirstName() + "," + accounts[i].getHolder().getLastName() + "," + accounts[i].getBalance() + "," + accounts[i].getOpenDate().toString() + "," ;

                if (accounts[i] instanceof Checking) {

                    Checking checking = (Checking)accounts[i] ;
                    output = "C" + output + checking.getDirectDeposit() + "\n" ;

                } else if (accounts[i] instanceof Savings) {

                    Savings savings = (Savings)accounts[i] ;
                    output = "S" + output + savings.getLoyal() + "\n" ;


                } else { // MoneyMarket

                    MoneyMarket moneyMarket = (MoneyMarket)accounts[i] ;
                    output = "M" + output + moneyMarket.getWithdrawals() +"\n" ;

                }

                writer.write(output) ;
    
            }

            writer.close() ;

        } catch (Exception e) {

        }


    }

    private void openAccount(String fname, String lname, double bal, Date date, String type, boolean condition) {

        Profile profile = new Profile() ;
        profile.setFirstName(fname);
        profile.setLastName(lname) ;
        
        if (type.equals("Checking")) {

            Checking checking = new Checking() ;
            checking.setHolder(profile) ;
            checking.setBalance(bal) ;
            checking.setOpenDate(date) ;
            checking.setDirectDeposit(condition) ;
            
            if (!database.add(checking)) {

                ocOutput.appendText("Account is already in the database.\n") ;
                return ;

            }


        } else if (type.equals("Savings")) {

            Savings savings = new Savings() ;
            savings.setHolder(profile) ;
            savings.setBalance(bal) ;
            savings.setOpenDate(date) ;
            savings.setLoyal(condition) ;

            if (!database.add(savings)) {
                
                ocOutput.appendText("Account is already in the database.\n") ;
                return ;
                
            }
 

        } else { // MoneyMarket

            MoneyMarket moneyMarket = new MoneyMarket() ;
            moneyMarket.setHolder(profile) ;
            moneyMarket.setBalance(bal) ;
            moneyMarket.setOpenDate(date) ;
            moneyMarket.setWithdrawals(0);

            if (!database.add(moneyMarket)) {
                
                ocOutput.appendText("Account is already in the database.\n") ;
                return ;
                
            }


        }
        
        ocOutput.appendText("Account opened and added to the database.\n");
        
    }

    private void closeAccount(String fname, String lname, String type) {

        Profile profile = new Profile() ;
        profile.setFirstName(fname);
        profile.setLastName(lname) ;

        if (type.equals("Checking")) {

            Checking checking = new Checking() ;
            checking.setHolder(profile) ;

            if (!database.remove(checking)) {

                ocOutput.appendText("Account does not exist.\n") ;
                return ;

            }


        } else if (type.equals("Savings")) {

            Savings savings = new Savings() ;
            savings.setHolder(profile) ;

            if (!database.remove(savings)) {

                ocOutput.appendText("Account does not exist.\n") ;
                return ;

            }


        } else { // MoneyMarket

            MoneyMarket moneyMarket = new MoneyMarket() ;
            moneyMarket.setHolder(profile) ;

            if (!database.remove(moneyMarket)) {

                ocOutput.appendText("Account does not exist.\n") ;
                return ;

            }

        }

        ocOutput.appendText("Account closed and removed from the database.\n") ;

    }

    public void deposit(String fname, String lname, double amount, String type){

        Profile tempProf = new Profile();
        tempProf.setFirstName(fname);
        tempProf.setLastName(lname);

        if(type.equals("Checking")){
            Checking acc = new Checking();
            acc.setHolder(tempProf);
            
            if (!database.deposit(acc, amount)) {

                dwOutput.appendText("Account not found!\n") ;
                return ;

            }

             
        }else if(type.equals("Savings")){
            Savings acc = new Savings();
            acc.setHolder(tempProf);
             
            if (!database.deposit(acc, amount)) {

                dwOutput.appendText("Account not found!\n") ;
                return ;

            }

            
        }else{
            MoneyMarket acc = new MoneyMarket();
            acc.setHolder(tempProf);
             
            if (!database.deposit(acc, amount)) {

                dwOutput.appendText("Account not found!\n") ;
                return ;

            }
            
        }


        dwOutput.appendText("Deposit was successful!\n");
        

        
    }

    public void withdraw(String fname, String lname, double amount, String type){

        //MoneyMarket's withdrawls need to be updated
        Profile tempProf = new Profile();
        tempProf.setFirstName(fname);
        tempProf.setLastName(lname);

        if(type.equals("Checking")){
            Checking acc = new Checking();
            acc.setHolder(tempProf);

            int result = database.withdrawal(acc, amount) ;
            
            if (result == -1) {

                dwOutput.appendText("Account not found!\n") ;
                return ;

            }else if(result == 1){
                dwOutput.appendText("Insufficent Funds!\n");
                return;
            }

             
        }else if(type.equals("Savings")){
            Savings acc = new Savings();
            acc.setHolder(tempProf);

            int result = database.withdrawal(acc, amount) ;
             
            if (result == -1){

                dwOutput.appendText("Account not found!\n") ;
                return ;

            }else if(result == 1){
                dwOutput.appendText("Insufficent Funds!\n");
                return;
            }

            
        }else{
            MoneyMarket acc = new MoneyMarket();
            acc.setHolder(tempProf);

            int result = database.withdrawal(acc, amount) ;
             
            if (result == -1){

                dwOutput.appendText("Account not found!\n") ;
                return ;

            }else if(result == 1){
                dwOutput.appendText("Insufficent Funds!\n");
                return;
            }else{
                database.incrementWithdrawals(acc);
                
            }
        }


        dwOutput.appendText("Withdrawal was successful!\n");

        
        

        
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
