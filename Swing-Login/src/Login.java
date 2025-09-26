import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class Login implements ActionListener {

    JFrame jFrame= new JFrame();
    JButton lButton= new JButton("login");
    JButton rButton= new JButton("reset");
    JButton signUpButton= new JButton("Sign Up");
    JTextField userIdField= new JTextField();
    JPasswordField password= new JPasswordField();
    JLabel userIdLabel= new JLabel("userId: ");
    JLabel passwordLabel= new JLabel("password: ");
    JLabel messageLabel= new JLabel();

    HashMap<String,String> loginInfo= new HashMap<>();

    Login(HashMap<String,String> loginInfoOriginal){
        loginInfo=loginInfoOriginal;

        userIdLabel.setBounds(50,100,75,25);
        passwordLabel.setBounds(50,150,75,25);

        messageLabel.setBounds(125,250,250,35);
        messageLabel.setFont(new Font(null,Font.ITALIC,25));

        userIdField.setBounds(125,100,200,25);
        password.setBounds(125,150,200,25);

        lButton.setBounds(50,200,90,25);
        lButton.addActionListener(this);

        rButton.setBounds(150,200,90,25);
        rButton.addActionListener(this);

        signUpButton.setBounds(250,200,90,25);
        signUpButton.addActionListener(this);

        jFrame.add(userIdLabel);
        jFrame.add(passwordLabel);
        jFrame.add(messageLabel);
        jFrame.add(userIdField);
        jFrame.add(password);
        jFrame.add(lButton);
        jFrame.add(rButton);
        jFrame.add(signUpButton);


        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setSize(420,420);
        jFrame.setLayout(null);
        jFrame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource()==rButton){
            userIdField.setText("");
            password.setText("");
        }

        if(e.getSource()==lButton){

            String userId=userIdField.getText();
            String pass= String.valueOf(password.getPassword());

            if(loginInfo.containsKey(userId)){
                if(loginInfo.get(userId).equals(pass)){
                    messageLabel.setForeground(Color.GREEN);
                    messageLabel.setText("Login Successful");
                    jFrame.dispose();
                    new WelcomePage(userId);
                }else{
                    messageLabel.setForeground(Color.RED);
                    messageLabel.setText("Wrong password");
                }
            }else {
                messageLabel.setForeground(Color.RED);
                messageLabel.setText("Username not found ");
            }
        }
        if(e.getSource()==signUpButton){
            String userId = userIdField.getText();
            String pass = String.valueOf(password.getPassword());

            if(userId.isEmpty() || pass.isEmpty()){
                messageLabel.setForeground(Color.RED);
                messageLabel.setText("Enter username & password");
                return;
            }

            if(loginInfo.containsKey(userId)){
                messageLabel.setForeground(Color.RED);
                messageLabel.setText("User already exists");
            } else {
                loginInfo.put(userId, pass);
                messageLabel.setForeground(Color.GREEN);
                messageLabel.setText("Sign Up successful! Now login.");
            }
        }
    }
}
