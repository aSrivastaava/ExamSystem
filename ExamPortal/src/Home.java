import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Random;

import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JRadioButton;

import java.awt.event.*;
import java.awt.Color;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class Home extends JFrame {

	private final JPanel contentPane;
	private final JTextField stdNameText;
	private final JTextField emailText;
	JButton submitBtn;
	DatabaseCreator db;
	Connection cn;
	Statement st;
	String sql,examName,ans4;
	ResultSet rs;
	JComboBox<String> examCombo;
	JPanel minor,main;
	int timecounter=60,ss=60;
	long idg,eidg,qidg;
	JLabel timeLabel,gg;
	Random random;
	JLabel questionLabel;
	JRadioButton ans4Radio,ans3Radio,ans2Radio,ans1Radio;
	long score=0,questionAttempted=0,correctAttempts=0,wrongAttempts=0,totalMarks=0,achivedMarks=0,correctAns=0;
	long totalQuestions,marksForEach,currentQno=0;
        //long negtiveMarks;
	ButtonGroup Fgroup;
	Timer timer;
	private final JButton admin;
	boolean chk=false;
	boolean firstIncheck=true;
	boolean rchk=false;
	JLabel mainLabel2,mainLabel;
       


	public static void main(String[] args) {

					Home frame = new Home();
					frame.setVisible(true);
	}
  //  private double ExhibitorLogo;


	public Home() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	//	setExtendedState(JFrame.MAXIMIZED_BOTH);
		setBounds(10, 100, 790, 443);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		main = new JPanel();
		main.setBounds(0, 0, 764, 405);
		contentPane.add(main);
		main.setLayout(null);
		
		mainLabel = new JLabel("Home");
		mainLabel.setHorizontalAlignment(SwingConstants.CENTER);
		mainLabel.setFont(new Font("Arial Black", Font.PLAIN, 20));
		mainLabel.setBounds(225, 55, 283, 29);
		main.add(mainLabel);
		
		JLabel examLabel = new JLabel("Exam Name");
		examLabel.setFont(new Font("Arial", Font.PLAIN, 15));
		examLabel.setBounds(195, 148, 238, 32);
		main.add(examLabel);
		
		examCombo = new JComboBox<>();
		examCombo.setBounds(325, 150, 238, 32);
		main.add(examCombo);
		
		JLabel stdNameLabel = new JLabel("Student Name");
		stdNameLabel.setFont(new Font("Arial", Font.PLAIN, 15));
		stdNameLabel.setBounds(175, 205, 163, 32);
		main.add(stdNameLabel);
		
		stdNameText = new JTextField();
		stdNameText.setColumns(10);
		stdNameText.setBounds(105, 235, 238, 29);
		main.add(stdNameText);
		
		JLabel emailLabel = new JLabel("Mobile Number");
		emailLabel.setFont(new Font("Arial", Font.PLAIN, 15));
		emailLabel.setBounds(525, 205, 238, 32);
		main.add(emailLabel);
		
		emailText = new JTextField();
		emailText.setColumns(10);
		emailText.setBounds(432, 235, 238, 29);
		main.add(emailText);
		
	JButton	startBtn = new JButton("Start Exam");
           //     startBtn.setActionCommand("SE");
            
		startBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                            //if(e.getActionCommand()=="SE"){
                            //    System.out.println(onStart());
                            if(onStart())
				{
                                 setTime();
				 displayInst();
                                 
				 main.setVisible(false);
				 minor.setVisible(true);
				 
                                 loadThis();
				 //displayInst();
				 startTime();
				 //progress();
				}
			//}
                            }
                          
		});
	startBtn.addKeyListener(new KeyAdapter() {
                
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode()==KeyEvent.VK_ENTER)
				{
					
					if(onStart())
					{
					 setTime();
					 displayInst();
					 main.setVisible(false);
					 minor.setVisible(true);
					 loadThis();
					 //displayInst();
					 startTime();
					 //progress();
					}
		           
		        }	
			}
		});
	/*	startBtn.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if(onStart())
				{
				 setTime();
				 displayInst();
				 main.setVisible(false);
				 minor.setVisible(true);
				 loadThis();
				 //displayInst();
				 startTime();
				 //progress();
				}
			}
		});*/
		startBtn.setFont(new Font("Arial", Font.PLAIN, 12));
		startBtn.setBounds(215, 288, 126, 41);
		main.add(startBtn);
		
		admin = new JButton("Main");
		admin.setForeground(Color.DARK_GRAY);
		admin.addKeyListener(new KeyAdapter() {
	
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode()==KeyEvent.VK_ENTER)
				{
					new MainPage().setVisible(true);
					kill();
		           
		        }
			}
		});
		admin.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				new MainPage().setVisible(true);
				kill();
				
			}
		});
		admin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		admin.setFont(new Font("Arial", Font.PLAIN, 12));
		admin.setBounds(431, 288, 126, 41);
		main.add(admin);
		
		JLabel lbldev = new JLabel("This project is devloped by Aditya Srivastava and Aniruddh Sarkar ");
		lbldev.setFont(new Font("Arial Narrow", Font.BOLD, 11));
		lbldev.setBounds(210, 386, 425, 14);
		main.add(lbldev);
		//main.setVisible(false);
                JLabel lb2dev = new JLabel("Welcome to MIT School Of Engineering ");
		lb2dev.setFont(new Font("Arial Black", Font.BOLD, 22));
		lb2dev.setBounds(125, 10, 525, 26);
		main.add(lb2dev);
                JLabel lb3dev = new JLabel("Examination Portal ");
		lb3dev.setFont(new Font("Arial Black", Font.BOLD, 22));
		lb3dev.setBounds(255, 35, 425, 26);
		main.add(lb3dev);
		
		minor = new JPanel();
		minor.setBounds(0, 0, 764, 405);
		contentPane.add(minor);
		minor.setLayout(null);
		
		mainLabel2 = new JLabel("Home");
		mainLabel2.setHorizontalAlignment(SwingConstants.CENTER);
		mainLabel2.setFont(new Font("Arial Black", Font.PLAIN, 20));
		mainLabel2.setBounds(10, 0, 764, 42);
		minor.add(mainLabel2);
		
		questionLabel = new JLabel("Question ?");//,
		questionLabel.setFont(new Font("Arial", Font.PLAIN, 15));
		questionLabel.setBounds(11, 53, 617, 32);
		minor.add(questionLabel);
		
		ans1Radio = new JRadioButton("ans ");
		ans1Radio.setFont(new Font("Arial Black", Font.PLAIN, 16));
		ans1Radio.setBounds(20, 92, 534, 41);
		minor.add(ans1Radio);
		
		ans2Radio = new JRadioButton("");
		ans2Radio.setFont(new Font("Arial Black", Font.PLAIN, 16));
		ans2Radio.setBounds(20, 136, 534, 37);
		minor.add(ans2Radio);
		
		ans3Radio = new JRadioButton("");
		ans3Radio.setFont(new Font("Arial Black", Font.PLAIN, 16));
		ans3Radio.setBounds(20, 176, 534, 41);
		minor.add(ans3Radio);
		
		ans4Radio = new JRadioButton("");
		ans4Radio.setFont(new Font("Arial Black", Font.PLAIN, 16));
		ans4Radio.setBounds(20, 220, 534, 42);
		minor.add(ans4Radio);
		
		Fgroup=new ButtonGroup();
		Fgroup.add(ans1Radio);
		Fgroup.add(ans2Radio);
		Fgroup.add(ans3Radio);
		Fgroup.add(ans4Radio);
		
		JButton button = new JButton("Next");
		button.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode()==KeyEvent.VK_ENTER)
				{
                                    System.out.println(rchk + " I'm on line 269");
                                 
					if(rchk){
                                        progress();
					loadThis();
                                        }
                                        
                                        insLoad();
					
		           
		        }	
			}
		});        
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
                            
                
                                 System.out.println(rchk + " I'm on line 286");
					if(rchk){
                                            
                                          System.out.println(rchk+"Action performed--267");  
                                        progress();
					loadThis();
                                        }
                                        
                                         insLoad();
					            
    /*
                            JOptionPane.showMessageDialog(null, "Action");
                            System.out.println(rchk+"Action performed--263");
		//	loadThis();
                
                fload();
                            
                            if(ans1Radio.isSelected()||ans2Radio.isSelected()||ans2Radio.isSelected()||ans2Radio.isSelected()){
				progress();
				loadThis();
                                System.out.println("Action performed--263");
                                }*/
                        }
                            
                            
                            
                            
                });
	JButton SubmitBtn = new JButton("Submit");	
        SubmitBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
           if(!chk)
	            		{
		                 JOptionPane.showMessageDialog(null,"Thanks for your Response.");
		                 finalResults();
		                 homePanel();
	            		}
	            		else
	            		{
	            		 finalResults();
	            		 homePanel();
	            		}
		                 timer.stop();
                        
                        }
		});
		button.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				if(rchk)
				progress();
				loadThis();
				
			}
		});
		button.setFont(new Font("Arial", Font.PLAIN, 12));
		button.setBounds(232, 286, 126, 41);
		minor.add(button);
                
                
		SubmitBtn.setFont(new Font("Arial", Font.PLAIN, 12));
		SubmitBtn.setBounds(232, 350, 126, 41);
		minor.add(SubmitBtn);
		
		JButton clearButton = new JButton("Clear ans");
		clearButton.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode()==KeyEvent.VK_ENTER)
				{
					Fgroup.clearSelection();
		           
		        }
			}
		});
		clearButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Fgroup.clearSelection();
			}
		});
		clearButton.setFont(new Font("Arial", Font.PLAIN, 12));
		clearButton.setBounds(46, 286, 126, 41);
		minor.add(clearButton);
		
		timeLabel = new JLabel("mm:ss");
		timeLabel.setFont(new Font("Arial", Font.PLAIN, 15));
		timeLabel.setBounds(667, 53, 60, 32);
		minor.add(timeLabel);
		
		gg = new JLabel("mm:ss");
		gg.setFont(new Font("Arial", Font.PLAIN, 15));
		gg.setBounds(667, 53, 60, 32);
		minor.add(timeLabel);
		
		JButton skipButton = new JButton("Skip");
		skipButton.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode()==KeyEvent.VK_ENTER)
				{
					firstIncheck=true;
					loadThis();
		           
		        }
			}
		});
		skipButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				firstIncheck=true;
				loadThis();
			}
		});
		skipButton.setFont(new Font("Arial", Font.PLAIN, 12));
		skipButton.setBounds(398, 286, 126, 41);
		minor.add(skipButton);
		minor.setVisible(false);
		random=new Random();
		fload();
	}
	public void fload()  
	{
		//form load//
		score=questionAttempted=correctAttempts=wrongAttempts=totalMarks=achivedMarks=0;correctAns=-1;
		totalQuestions=marksForEach=currentQno=0;ss=60;chk=false;
		
        //        totalQuestions=negtiveMarks=marksForEach=currentQno=0;ss=60;chk=false;
		try
        {
		 db=new DatabaseCreator();
		 cn=db.getConnection();
         st = cn.createStatement();
         ResultSet rs = st.executeQuery("SELECT Exam_Name FROM  Exam_Setup order by Exam_Name");
         examCombo.removeAllItems();
         examCombo.addItem("");
         stdNameText.setText("");//test();
         emailText.setText("");
         while (rs.next())
            {
        	 examCombo.addItem(rs.getString(1));
            }
	    } 
		catch (Exception ex)
        {
              System.out.print("Exception: ");
              System.out.println(ex.getMessage());
        }
	}//end of fload();
	public boolean onStart()
	{
		try
		{
			String msg;
			if(examCombo.getSelectedItem().toString().equals(""))
			{
			 	msg="Please Enter The Exam Type...!";
			 	examCombo.requestFocusInWindow();
			 	JOptionPane.showMessageDialog(null,msg);
			 	return false;
			 	
			}
			else if(stdNameText.getText().equals(""))
			{
				msg="Please Enter Student Name...!";
				stdNameText.requestFocusInWindow();
				JOptionPane.showMessageDialog(null,msg);
			 	return false;
			}
			else if(emailText.getText().equals(""))
			{
				msg="Please Enter Mobile Number.";
				emailText.requestFocusInWindow();
				JOptionPane.showMessageDialog(null,msg);
			 	return false;
			}
			else
		   {
                   //    String regexStr = "^(?:(?:\\+?1\\s*(?:[.-]\\s*)?)?(?:\\(\\s*([2-9]1[02-9]|[2-9][02-8]1|[2-9][02-8][02-9])\\s*\\)|([2-9]1[02-9]|[2-9][02-8]1|[2-9][02-8][02-9]))\\s*(?:[.-]\\s*)?)?([2-9]1[02-9]|[2-9][02-9]1|[2-9][02-9]{2})\\s*(?:[.-]\\s*)?([0-9]{4})(?:\\s*(?:#|x\\.?|ext\\.?|extension)\\s*(\\d+))?$";
                          String ePattern = "^(?:(?:\\+?1\\s*(?:[.-]\\s*)?)?(?:\\(\\s*([2-9]1[02-9]|[2-9][02-8]1|[2-9][02-8][02-9])\\s*\\)|([2-9]1[02-9]|[2-9][02-8]1|[2-9][02-8][02-9]))\\s*(?:[.-]\\s*)?)?([2-9]1[02-9]|[2-9][02-9]1|[2-9][02-9]{2})\\s*(?:[.-]\\s*)?([0-9]{4})(?:\\s*(?:#|x\\.?|ext\\.?|extension)\\s*(\\d+))?$";
                          
		//		String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
		        Pattern p =Pattern.compile(ePattern);
		        Matcher m = p.matcher(emailText.getText());
		      boolean bm = m.matches();
		        if(bm == false)
		        {
		        	msg="Please Enter a valid Mobile Number.";
					emailText.requestFocusInWindow();
					JOptionPane.showMessageDialog(null,msg);
				 	return false;

		        }
		     }
			
			//long id;
		//	st=cn.createStatement();
			sql="SELECT Id FROM Student order by Id DESC limit 1";
			ResultSet rs55=st.executeQuery(sql);
			if(rs55.next())
				idg=rs55.getLong(1)+1;
			else idg=1;
			
			//st.executeUpdate(sql);	
			JOptionPane.showMessageDialog(null,stdNameText.getText().toUpperCase()+"! \nPlease note your ID for further use\nID="+idg);
			mainLabel2.setText(stdNameText.getText()+" Id:"+idg);
			insLoad();
			
		}
		catch (Exception ex)
		{
			System.out.print("Exception: ");
                        System.out.println(ex.getMessage());
        }
		return true;
	}
	public void insLoad()
	{
		try
		{
			long tmp = 0;
			sql="DELETE FROM Buffer";
			st.executeUpdate(sql);
		//	sql="SELECT * FROM Exam_Setup,Exam_Questions where Exam_Setup.Id=Exam_Questions.eId & Exam_Name='"+examCombo.getSelectedItem().toString().toUpperCase()+"'";
                        sql="SELECT * FROM Exam_Setup,Exam_Questions where Exam_Setup.Id=Exam_Questions.eId & Exam_Name='Test'";
			System.out.println(sql + "Hey i'm on line 506");
			ResultSet rs8=st.executeQuery(sql);
			if(rs8.next())
			{
			 tmp=rs8.getLong("Id");
			 eidg=rs8.getInt("Id");
			 marksForEach=rs8.getLong("Marks_p_Qs");
		//	 negtiveMarks=rs.getLong(5);
			 totalQuestions=rs8.getLong("Total_Questions");
			 examName=rs8.getString("Exam_Name");
			 totalMarks=marksForEach*totalQuestions;
			 System.out.println(eidg+" Current Exam details:->Time:"+rs8.getLong("Time")+"Marks Each Questions:"+marksForEach+"Total Questions:"+totalQuestions+"Exam Name: "+examName);
			}
			sql="SELECT eId FROM Exam_Questions where Category_id="+tmp+"";
			System.out.println(sql);
			ResultSet rs7=st.executeQuery(sql);
                        
                        
                        System.out.println(rs7.next() + " I'm on line 524");
                        
                        
			while(rs7.next())
			{
				sql="INSERT INTO Buffer(Id)values("+rs7.getLong(1)+")";
				System.out.println(sql);
				st.executeUpdate(sql);
			}
		}
          
		catch (Exception ex) 
		{
		System.out.print("Exception: ");
	        System.out.println(ex.getMessage());
                System.out.println("I'm on line 458");
        }
                
	}
	public void startTime()
	{
		
	        ActionListener timerListener = new ActionListener()
	        {
	            public void actionPerformed(ActionEvent e)
	            {
                        if(timecounter>0 || ss>0)
	            	{
                            if(ss>0)
                            {
	            		 ss--;
                            }
                            else
                            {
	            		 ss=60;
	            		 timecounter--;
                            }
	            	}
	            	else
	            	{
	            		ss=0;
                            if(!chk)
                            {
		                 JOptionPane.showMessageDialog(null,"Time's up");
		                 finalResults();
		                 homePanel();
                            }
                           else
                            {
	            		 finalResults();
	            		 homePanel();
                            }
		                 timer.stop();
		          
	            	}
	            	
	            	 String s=Integer.toString(timecounter);
	            	 String p=Integer.toString(ss);
	                 timeLabel.setText(s+":"+p);
	            	  
	            }
	        };
		timer = new Timer(1000, timerListener);
        timer.setInitialDelay(0);
        timer.start();
        }
        
	public void setTime()
	{
            System.out.println("Hey I'm inside setTime(), on line 582");
		try
		{
                    
                    System.out.println("I'm on line 513");
			sql="SELECT Time FROM Exam_Setup where Id="+eidg+"";
			System.out.println(sql);
                        
                        System.out.println(st.executeQuery(sql));
                       ResultSet rs11=st.executeQuery(sql);
                        System.out.println(rs11.next());
                        System.out.println("Line 518");
			rs11=st.executeQuery(sql);
			if(rs11.next()){
			timecounter=rs11.getInt(1)-1;
                        }
		}
		catch (Exception ex)
		{
			System.out.print("Exception: ");
                        System.out.println(ex.getMessage());
        }	
		
	}
	public void test()
	{
		
	}
	public void loadThis()
	{System.out.println("loadThis 600");
		try
		{
			if(totalQuestions==currentQno)
			{   
                                System.out.println("I'm on line 536");
				finalResults();
				timecounter=0;
				ss=0;
				chk=true;           //time's done
				return;
			}
			int totalRec=0,ch=0;
			int randomNumber=0;
                        System.out.println(firstIncheck);
                        System.out.println("Line 545");
			System.out.println("firstIncheck");
			if(firstIncheck)
			{
				firstIncheck=false;	
				System.out.println("This is first call, line 551");
			}
			else
			{ 
				rchk=false;
				if(ans1Radio.isSelected())
				 rchk=true;
				if(ans2Radio.isSelected())
					rchk=true;
				if(ans3Radio.isSelected())
					rchk=true;
				if(ans4Radio.isSelected())
					rchk=true;
				if(!rchk)
				{
					JOptionPane.showMessageDialog(null,"Please select an answer...!");
					return;
				}
			}
			Fgroup.clearSelection();
			sql="SELECT COUNT(Id) FROM Buffer";
			ResultSet rs2=st.executeQuery(sql);
			if(rs2.next())
			{
				totalRec=rs2.getInt(1);
				System.out.println("stuff i got-->"+totalRec);
			}
			if(totalRec<=1)
			{
				randomNumber=1;
			}
			else
			randomNumber = random.nextInt(totalRec- 1) + 1;
			System.out.println(randomNumber);
			//loading the current generated stuff//
			sql="SELECT * FROM Buffer";
			ResultSet rs3=st.executeQuery(sql);
			int lop=1;
			while(lop<=randomNumber)
			{
				rs3.next();
				lop++;
			}
		//	sql="SELECT * FROM (SELECT ROW_NUMBER() OVER(ORDER BY Id) AS Rno,* buffer )T WHERE T.Rno = "+randomNumber+"";
                        sql="SELECT * FROM Exam_Setup,Exam_Questions where Exam_Setup.Id=Exam_Questions.eId & Exam_Name='"+examCombo.getSelectedItem().toString().toUpperCase()+"'";
			ResultSet rs4=st.executeQuery(sql);
        /*    		ch=rs4.getInt("eId");
                        System.out.println("-----------"+ ch);
                        System.out.println("-----------"+rs.getInt("eId"));
            		sql="SELECT * FROM Exam_Questions where Id="+ch+"";
                        System.out.println(sql);
	*/	//ResultSet rs5=st.executeQuery(sql);
			if(rs4.next())
			{
				//Question++;
				qidg=rs4.getInt("Category_id");
				correctAns=rs4.getInt("Ans");
				questionLabel.setText(rs4.getString("Question"));
				System.out.println("********************"+rs4.getString("Question"));
                                ans1Radio.setText(rs4.getString("Option1"));
				ans2Radio.setText(rs4.getString("Option2"));
				ans3Radio.setText(rs4.getString("Option3"));
				ans4Radio.setText(rs4.getString("Option4"));
				ans4=rs4.getString("Ans");
				System.out.println("genrated question number"+randomNumber);
				System.out.println("correct ans in progress"+correctAns);
			}
			sql="DELETE FROM Buffer where Id="+ch+"";
			st.executeUpdate(sql);
		}
		catch(Exception ex)
		{
			System.out.println("Exception");
			System.out.println(ex.getMessage());
		}
	}
	
	public void progress()//calculating marks and stuff
	{	
          
            
		try
		{		
                    
               //         sql="SELECT * FROM Exam_Setup,Exam_Questions where Exam_Setup.Id=Exam_Questions.eId & Exam_Name='Test'";
		//	rs=st.executeQuery(sql);
                        
                        
			if(ans1Radio.isSelected() && correctAns==1)
			{
			 score=score+marksForEach;
			 correctAttempts++;
			 System.out.println("my ans 1");
			}
			else if(ans2Radio.isSelected() && correctAns==2)
			{
				 score=score+marksForEach;	
				 correctAttempts++;
				 System.out.println("my ans 2");
			}
			else if(ans3Radio.isSelected() && correctAns==3)
			{
				 score=score+marksForEach;
				 correctAttempts++;
				 System.out.println("my ans 3");
			}
			else if(ans4Radio.isSelected() && correctAns==4)
			{
			 score=score+marksForEach;
			 correctAttempts++;
			 System.out.println("my ans 4");
			}
			else if(ans4Radio.isSelected() && correctAns==5 && ans4.equals("ALL OF ABOVE"))
			{
			 score=score+marksForEach;
			 correctAttempts++;
			 System.out.println("my ans 5");
			}
			else if(ans4Radio.isSelected() && correctAns==6 && ans4.equals("NONE OF ABOVE"))
			{
				 score=score+marksForEach;
				 correctAttempts++;
				 System.out.println("my ans 6");
			}
			else
			{
				wrongAttempts++;
	//			score=score-negtiveMarks;
			}
			System.out.println("score ="+score);
			questionAttempted++;	
		}
		catch(Exception ex)
		{
			System.out.println("Exception");
			System.out.println(ex.getMessage());
		}
	}
	public void finalResults()
	{
		String msg="Result!\n1.Score="+score+"\n2.Question Attempted="+questionAttempted+"\n3.Correct Attempted="+correctAttempts+"\n4.Wrong Attempted="+wrongAttempts+"\n5.Total Marks="+totalMarks+"";
		JOptionPane.showMessageDialog(null,msg);
		System.out.print(msg);

		try
		{
			sql="INSERT INTO Student values("+idg+",'"+examCombo.getSelectedItem().toString().toUpperCase()+"','"+stdNameText.getText().toUpperCase()+"','"+emailText.getText().toUpperCase()+"',"+questionAttempted+","+correctAttempts+","+wrongAttempts+","+totalMarks+","+score+")";
			System.out.println(sql);
			st.executeUpdate(sql);
			
		}
		
		catch (Exception ex)
		{
			System.out.print("Exception: ");
                        System.out.println(ex.getMessage());
        }	
	}
	public void displayInst()
	{    String msg="Important Instructions*\n1.You will have "+(timecounter+1)+" minutes\n2.There will be "+totalQuestions+" question\n3.Every question is mandatory\n4.You will get "+marksForEach+" Mark['s] for each correct attempt\n\n \nALL THE BEST";
        
                System.out.println("I'm Inside displayInst(), on line 791");
		 JOptionPane.showMessageDialog(null,msg);
	}
	public void homePanel()
	{
		int dialogButton = 0;
		int dialogResult = JOptionPane.showConfirmDialog (null, "Click \"Yes\" to take another test...!","Delete",dialogButton);
		if(dialogResult == JOptionPane.YES_OPTION)
		{
		  minor.setVisible(false);
		  main.setVisible(true);
		  fload();
		}
		else
			System.exit(0);
	}
	public void kill()
	{
		this.dispose();
	}
}
