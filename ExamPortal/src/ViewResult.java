import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.DriverManager;

public final class ViewResult extends JFrame {

	private final JPanel contentPane;
	private final JTextField viewText;
	DatabaseCreator db;
	Connection cn;
	Statement st;
	String sql;
	ResultSet rs;
	JLabel ename,std,emailLabel,questionAttemtedLabel,correctAttempts,wrongAttempts,totalMarks,achivedMarks;
	private final JButton homeBtn;
	private final JButton insertQuestionBtn;
	private final JButton setupQuestionsBtn;
	public static void main(String[] args) {

					ViewResult frame = new ViewResult();
					frame.setVisible(true);

	}

	/**
	 * Create the frame.
	 */
	public ViewResult() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	//	setExtendedState(JFrame.MAXIMIZED_BOTH);
		setBounds(100, 100, 600, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel mainLabel = new JLabel("Result Viewer");
		mainLabel.setHorizontalAlignment(SwingConstants.CENTER);
		mainLabel.setFont(new Font("Arial Black", Font.PLAIN, 20));
		mainLabel.setBounds(170, 20, 254, 29);
		contentPane.add(mainLabel);
		
		JButton viewBtn = new JButton("Search");
		viewBtn.addKeyListener(new KeyAdapter() {
			 
			public void keyPressed(KeyEvent e)
			{
				if (e.getKeyCode()==KeyEvent.VK_ENTER)
				{
				 loadResult();
				}
			}
		});
		viewBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("calling");
				loadResult();
			}
		});
		viewBtn.setFont(new Font("Arial", Font.PLAIN, 12));
		viewBtn.setBounds(375, 56, 73, 19);
		contentPane.add(viewBtn);
		
		viewText = new JTextField();
		viewText.setColumns(10);
		viewText.setBounds(220, 56, 153, 19);
		contentPane.add(viewText);
                
                JLabel stdidlbl = new JLabel("Student's ID");
		stdidlbl.setBounds(150, 56, 86, 19);
                //stdidlbl.setBounds(435, 56, 86, 19);
		contentPane.add(stdidlbl);
		
		ename = new JLabel("Exam Name");
		ename.setFont(new Font("Arial", Font.PLAIN, 15));
		ename.setBounds(310, 159, 228, 32);
		contentPane.add(ename);
		
		std = new JLabel("Student's Name");
		std.setFont(new Font("Arial", Font.PLAIN, 15));
		std.setBounds(185, 109, 244, 32);
		contentPane.add(std);
		
		emailLabel = new JLabel("Mobile Number");
		emailLabel.setFont(new Font("Arial", Font.PLAIN, 15));
		emailLabel.setBounds(10, 149, 200, 32);
		contentPane.add(emailLabel);
		
		questionAttemtedLabel = new JLabel("Questions Attempted");
		questionAttemtedLabel.setFont(new Font("Arial", Font.PLAIN, 15));
		questionAttemtedLabel.setBounds(130, 285, 228, 32);
		contentPane.add(questionAttemtedLabel);
		
		correctAttempts = new JLabel("Correct Attempts");
		correctAttempts.setFont(new Font("Arial", Font.PLAIN, 15));
		correctAttempts.setBounds(10, 215, 228, 32);
		contentPane.add(correctAttempts);
		
		wrongAttempts = new JLabel("Wrong Attempts");
		wrongAttempts.setFont(new Font("Arial", Font.PLAIN, 15));
		wrongAttempts.setBounds(245, 219, 206, 32);
		contentPane.add(wrongAttempts);
		
		totalMarks = new JLabel("Total Marks");
		totalMarks.setFont(new Font("Arial", Font.PLAIN, 15));
		totalMarks.setBounds(10, 405, 225, 32);
		contentPane.add(totalMarks);
		
		achivedMarks = new JLabel("Achived Marks");
		achivedMarks.setFont(new Font("Arial", Font.PLAIN, 15));
		achivedMarks.setBounds(175, 270, 228, 32);
		contentPane.add(achivedMarks);
		
		homeBtn = new JButton("Logout");
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
		homeBtn.setFont(new Font("Arial", Font.PLAIN, 12));
		homeBtn.setEnabled(true);
		homeBtn.setBounds(250, 0, 73, 19);
		contentPane.add(homeBtn);
		
		insertQuestionBtn = new JButton("Insert Questions");
		insertQuestionBtn.addKeyListener(new KeyAdapter() {
			 
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode()==KeyEvent.VK_ENTER)
				{
		 			 new InsertForm().setVisible(true);
					    kill();
		           
		        }
			}
		});
		insertQuestionBtn.addMouseListener(new MouseAdapter() {
			 
			public void mouseClicked(MouseEvent e) {
				new InsertForm().setVisible(true);
				kill();
			}
		});
		insertQuestionBtn.setFont(new Font("Arial", Font.PLAIN, 12));
		insertQuestionBtn.setEnabled(true);
		insertQuestionBtn.setBounds(325, 0, 125, 19);
		contentPane.add(insertQuestionBtn);
		
		setupQuestionsBtn = new JButton("Setup Question");
		setupQuestionsBtn.addKeyListener(new KeyAdapter() {
			 
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode()==KeyEvent.VK_ENTER)
				{
		 			 new SetupExam().setVisible(true);
					    kill();
		           
		        }
			}
		});
		setupQuestionsBtn.addMouseListener(new MouseAdapter() {
			 
			public void mouseClicked(MouseEvent e) {
				new SetupExam().setVisible(true);
				kill();
			}
		});
		setupQuestionsBtn.setFont(new Font("Arial", Font.PLAIN, 12));
		setupQuestionsBtn.setBounds(123, 0, 125, 19);
		contentPane.add(setupQuestionsBtn);
		fload();
	}
	public void fload()
	{
		try
        {
            Class.forName("java.sql.DriverManager");
	            cn = DriverManager.getConnection("jdbc:mysql://localHost:3306/Exam","root","qwertyuiop!1");
	            st=cn.createStatement();
		 db=new DatabaseCreator();
	    } 
		catch (Exception ex)
        {
              System.err.print("Exception: ");
              System.err.println(ex.getMessage());
        }
	}
	public void loadResult()
	{
		try
        {
		 sql="select * from student where id="+viewText.getText()+"";
		 System.out.println(sql);
		 rs=st.executeQuery(sql);
		 //loading stuff into form;
		 if(rs.next())
		 {
			 ename.setText("Exam Name:"+rs.getString(2));//changes goes here ? ass :) here only its fine.... ;)
			 std.setText("Student Name:"+rs.getString(3));
			 emailLabel.setText("Mobile Number: "+rs.getString(4));
			 questionAttemtedLabel.setText("Question Attempted:"+rs.getString(5));
			 correctAttempts.setText("Correct Attempts:"+rs.getString(6));
			 wrongAttempts.setText("Wrong Attempts:"+rs.getString(7));
			 totalMarks.setText("Total Marks:"+rs.getString(8));
			 achivedMarks.setText("Achived Marks:"+rs.getString(9));
		 }
		 else
			 JOptionPane.showMessageDialog(null,"Student id not found...!");
	    } 
		catch (Exception ex)
        {
			JOptionPane.showMessageDialog(null,"Student id not found...!");
              System.err.print("Exception: ");
              System.err.println(ex.getMessage());
        }
		
	}
	 public void kill()
	{
		this.dispose();
	}
}
