import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.DriverManager;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public final class Admin extends JFrame {

	private final JPanel contentPane;
	private final JTextField userText;
	private final JPasswordField passText;
	DatabaseCreator db;
	Connection cn;
	Statement st;
	String sql,examName,ans4;
	ResultSet rs;
	JButton loginBtn;
	private final JLabel passlbl;
	private final JButton homeBtn;


	public static void main(String[] args) {

					Admin frame = new Admin();
					frame.setVisible(true);

	}

	public Admin() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 344, 258);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel main = new JPanel();
		main.setBounds(4, 3, 328, 215);
		contentPane.add(main);
		main.setLayout(null);
		
		JLabel mainlbl = new JLabel("Admin Login");
		mainlbl.setBounds(75, 10, 185, 45);
                mainlbl.setFont(new Font("Arial", Font.BOLD, 25));
		main.add(mainlbl);
		
		JLabel userlbl = new JLabel("UserName");
		userlbl.setBounds(60, 65, 86, 25);
		main.add(userlbl);
		
		userText = new JTextField();
		userText.setBounds(150, 65, 86, 30);
		main.add(userText);
		userText.setColumns(10);
		//userlbl.setLabel()
		
		loginBtn = new JButton("Login");
                loginBtn.setActionCommand("Login");
                
	//	               loginBtn.addActionListener(new ActionListener(){
             /*      public void actionPerformed(ActionEvent ae){
                       
                    
                        login();
                   }
                    
                }); */
        
        loginBtn.addKeyListener(new KeyAdapter() {
			 
			public void keyPressed(KeyEvent e) {

					if (e.getKeyCode()==KeyEvent.VK_ENTER)
					{
					login();
			           
			        }
				}
		});
		loginBtn.addMouseListener(new MouseAdapter() {
			 
			public void mouseClicked(MouseEvent e) {
				login();
			}
		});
		loginBtn.setBounds(55, 155, 89, 23);
		main.add(loginBtn);
		
		passlbl = new JLabel("Password");
		passlbl.setBounds(58, 100, 65, 25);
		main.add(passlbl);
		
		passText = new JPasswordField();
		passText.setBounds(150, 102, 86, 30);
		passText.setEchoChar('*');
		main.add(passText);
		passText.setColumns(10);
		
		homeBtn = new JButton("Main");
		homeBtn.addKeyListener(new KeyAdapter() {
			 
			public void keyPressed(KeyEvent e) {

					if (e.getKeyCode()==KeyEvent.VK_ENTER)
					{
						 new MainPage().setVisible(true);
						    kill();

			           
			        }
				}
		});
		homeBtn.addMouseListener(new MouseAdapter() {
			 
			public void mouseClicked(MouseEvent e) {
				new MainPage().setVisible(true);
				kill();
			}
		});
		homeBtn.setBounds(150, 155, 89, 23);
		main.add(homeBtn);
		
            
            JButton databaseBtn = new JButton("Database");
		databaseBtn.addKeyListener(new KeyAdapter() {
			 
			public void keyPressed(KeyEvent e) {

					if (e.getKeyCode()==KeyEvent.VK_ENTER)
					{
						 new DatabaseCreator().setVisible(true);
						    kill();

			           
			        }
				}
		});
		databaseBtn.addMouseListener(new MouseAdapter() {
			 
			public void mouseClicked(MouseEvent e) {
				new DatabaseCreator().setVisible(true);
				kill();
			}
		});
		databaseBtn.setBounds(105, 185, 89, 23);
		main.add(databaseBtn);
		
	    
                fload();
	    
                fload();
	}
	public void fload()
	{
		
		try
                    
        {
            System.out.println("Inside fload");
            Class.forName("java.sql.DriverManager");
            Connection cn =(Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/Exam","root","qwertyuiop!1");
           System.out.println("Connection");
            
		 db=new DatabaseCreator();
		cn=db.getConnection();
                 System.out.println("Connected successfuly");
         //st = cn.createStatement(); 
        }
		catch (Exception ex)
        {
                    //  System.out.print("Exception: ");
                    //   System.out.println(ex.getMessage());
                    
        }
	}
	public void login()
	{
            System.out.println("in login");
		try
        {   Class.forName("java.sql.DriverManager");
            Connection cn =(Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/Exam","root","qwertyuiop!1");
           
            //Class.forName("java.sql.DriverManager");
            //Connection cn =(Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/Exam","root","qwertyuiop!1");
            String s1=new String(passText.getPassword());
		sql="select * from user where Username='"+userText.getText()+"' and Password='"+s1+"'";
		System.out.println("Database me ghuss gaya..!!");
                System.out.println(sql);
                Statement st=cn.createStatement();
		ResultSet rs=st.executeQuery(sql);
                //rs.next();
                //String adn=rs.getString(1);
                //System.out.println(adn);
                //System.out.println(rs.next());
		 if(rs.next())
		 {
                     String adp,adn;
                     adn=rs.getString(1);
                     adp=rs.getString(2);
                     if(adn.equals(userText.getText())&&adp.equals(s1)){
                    System.out.println("Kya ker rha hai..??");
			 System.out.println("Login success");
			 JOptionPane.showMessageDialog(null,"Login success..!");
			 new AdminHome().setVisible(true);
                     }
			 kill();
		 }
		 else
		 {
                       System.out.println("Nhi hua kya abhi tak..??");
			
			 System.out.println("retry");
			 JOptionPane.showMessageDialog(null,"User name or password is wrong...!");
			 
		 }
        }
		catch (Exception ex)
        {
			JOptionPane.showMessageDialog(null,"User name or password is wrong...!\n Don't Dare to break in...!!");
        }
		
	}
	public void kill()
	{
		this.dispose();
	}
}
