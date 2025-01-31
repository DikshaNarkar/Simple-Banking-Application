import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class BankApp {
private BankAccount account;
private JFrame frame;
private JTextField nameField, depositField, withdrawField, balanceField;
public BankApp() {
frame = new JFrame("Simple Banking Application");
frame.setSize(400, 500);
frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
// Main panel with BorderLayout to center everything
JPanel mainPanel = new JPanel(new BorderLayout());
mainPanel.setBackground(new Color(240, 240, 240));
// Center panel with BoxLayout to place components vertically in the center
JPanel centerPanel = new JPanel();
centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
centerPanel.setBackground(new Color(240, 240, 240));
// Adding account holder name field
centerPanel.add(createLabelFieldPanel("Account Holder Name:", nameField = new
JTextField(15)));
// Adding deposit field
centerPanel.add(createLabelFieldPanel("Deposit Amount:", depositField = new
JTextField(15)));
// Adding withdraw field
centerPanel.add(createLabelFieldPanel("Withdraw Amount:", withdrawField = new
JTextField(15)));
// Adding balance field (non-editable)
centerPanel.add(createLabelFieldPanel("Current Balance:", balanceField = new
JTextField(15)));
balanceField.setEditable(false);
// Buttons panel
JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
buttonPanel.setBackground(new Color(240, 240, 240));
// Adding buttons with color
JButton createAccountButton = createButton("Create Account", new Color(0, 153, 76));
createAccountButton.addActionListener(e -> createAccount());
buttonPanel.add(createAccountButton);
JButton depositButton = createButton("Deposit", new Color(0, 153, 76));
depositButton.addActionListener(e -> depositAmount());
buttonPanel.add(depositButton);
JButton withdrawButton = createButton("Withdraw", new Color(0, 153, 76));
withdrawButton.addActionListener(e -> withdrawAmount());
buttonPanel.add(withdrawButton);
// Adding center panel and button panel to the main panel
mainPanel.add(centerPanel, BorderLayout.CENTER);
mainPanel.add(buttonPanel, BorderLayout.SOUTH);
// Adding main panel to frame
frame.add(mainPanel);
frame.setVisible(true);
}
// Helper method to create a panel with label and text field for each component
private JPanel createLabelFieldPanel(String labelText, JTextField textField) {
JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
panel.setBackground(new Color(240, 240, 240));
JLabel label = new JLabel(labelText);
label.setForeground(new Color(0, 102, 153));
label.setFont(new Font("Arial", Font.BOLD, 14));
panel.add(label);
panel.add(textField);
return panel;
}
// Helper method to create styled buttons
private JButton createButton(String text, Color color) {
JButton button = new JButton(text);
button.setBackground(color);
button.setForeground(Color.WHITE);
button.setFont(new Font("Arial", Font.BOLD, 12));
return button;
}
private void createAccount() {
String name = nameField.getText();
account = new BankAccount(name);
balanceField.setText("0.0");
JOptionPane.showMessageDialog(frame, "Account created for " + name);
}
private void depositAmount() {
if (account != null) {
double amount = Double.parseDouble(depositField.getText());
account.deposit(amount);
balanceField.setText(String.valueOf(account.getBalance()));
} else {
JOptionPane.showMessageDialog(frame, "Please create an account first.");
}
}
private void withdrawAmount() {
if (account != null) {
double amount = Double.parseDouble(withdrawField.getText());
if (account.withdraw(amount)) {
balanceField.setText(String.valueOf(account.getBalance()));
} else {
JOptionPane.showMessageDialog(frame, "Insufficient balance.");
}
} else {
JOptionPane.showMessageDialog(frame, "Please create an account first.");
}
}
public static void main(String[] args) {
SwingUtilities.invokeLater(BankApp::new);
}
private class BankAccount {
private String accountHolderName;
private double balance;
public BankAccount(String accountHolderName) {
this.accountHolderName = accountHolderName;
this.balance = 0.0;
}
public void deposit(double amount) {
if (amount > 0) {
balance += amount;
}
}
public boolean withdraw(double amount) {
if (amount > 0 && amount <= balance) {
balance -= amount;
return true;
}
return false;
}
public double getBalance() {
return balance;
}
}
}
