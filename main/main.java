package main;

import javax.swing.JOptionPane;
import usera.LoginPage;
import usera.Register;
import userb.LoginPageB;
import userb.RegisterPageB;

public class main {
    public static void main(String[] args) {
        String[] options = {
            "User A - Register",
            "User A - Login",
            "User B - Register",
            "User B - Login"
        };

        int choice = JOptionPane.showOptionDialog(
            null,
            "Choose option to start",
            "Chat App Start",
            JOptionPane.DEFAULT_OPTION,
            JOptionPane.INFORMATION_MESSAGE,
            null,
            options,
            options[0]
        );

        switch (choice) {
            case 0:
                new Register();
                break;
            case 1:
                new LoginPage();
                break;
            case 2:
                new RegisterPageB();
                break;
            case 3:
                new LoginPageB();
                break;
            default:
                System.exit(0);
        }
    }
}
