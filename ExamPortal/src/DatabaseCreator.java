import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import javax.swing.JOptionPane;
import java.sql.DriverManager;
import java.sql.ResultSet;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Rahul
 */
public class DatabaseCreator extends javax.swing.JFrame {

    /**
     * Creates new form DatabaseCreator
     */
    java.sql.Connection cn = null;
	String sblank="";
	String sstar="";
	java.sql.Statement st;
	String sql;
	ResultSet rs;
    public DatabaseCreator() {
        {
        try {
	            Class.forName("java.sql.DriverManager");
	            cn = DriverManager.getConnection("jdbc:mysql://localHost:3306/Exam","root","qwertyuiop!1");
	            st=cn.createStatement();
	            System.out.println("connection established...1");

	        } 
        catch (Exception e) {
	            System.out.println("inside get connection catch ");
	            System.out.println("Exception : " + e);
	            e.printStackTrace();
	        } 
    }
        initComponents();
    }
    public java.sql.Connection getConnection()
	{
       
        try {
            Class.forName("java.sql.DriverManager");
            cn = DriverManager.getConnection("jdbc:mysql://localHost:3306/Exam","root","qwertyuiop!1");
            System.out.println("connection established...");

        } catch (Exception e) {
            System.out.println("inside get connection catch ");
            System.out.println("Exception : " + e);
            e.printStackTrace();
        }
        return cn;

    }
    public ResultSet selectCols(String cols,String table,String condtion)
	{
		if(cols.equals(sblank))
			cols="*";
		if(!condtion.equals(sblank))
			condtion=" Where "+condtion;
		String sql="SELETE "+cols+" FROM "+table+"  "+condtion+"";
		System.out.println(sql);
		try
		{
			rs=st.executeQuery(sql);
		} 
		catch (Exception e) 
		{
			System.out.println("inside get connection catch ");
			System.out.println("Exception : " + e);
			e.printStackTrace();
		}
		return rs;
	}
	//select_cols ends here 
	public void insertData(String cols,String table,String values)
	{
		
		try
		{
			if(!cols.isEmpty())
			{
				sql="INSERT INTO "+table+"("+cols+") values("+values+") ";
			}
			else
			{
				sql="INSERT INTO "+table+" values("+values+")";
			}
			System.out.println(sql);
			st.execute(sql);
		}
		catch (Exception e) 
		{
			System.out.println("inside get connection catch ");
			System.out.println("Exception : " + e);
			e.printStackTrace();
		}
	}

   

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 48)); // NOI18N
        jButton1.setText("CREATE DATABASE");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("<html>App will create a database with \"Exam\" as name is MySql and delete if any with same name<br> Make sure your MySql username is \"root\" and MySql port number is \"3306\" </html>");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton1)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addGap(41, 41, 41))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(44, 44, 44)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(47, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {

            Class.forName("java.sql.DriverManager");
            Connection cn =(Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/Exam","root","qwertyuiop!1");
            Statement st=(Statement)cn.createStatement();

            st.executeUpdate("drop database if exists Exam;");

            st.executeUpdate("create database Exam;");
            st.executeUpdate("use Exam;");

            String qry="create table Buffer(Id int(20));";
            String qry1="create table Category(Category_id int(9),Category_Name varchar(20));";
            String qry2="create table Exam_Questions(eId int(20) primary key,Category_id int(9),Question varchar(150),Option1 varchar(80),Option2 varchar(80),Option3 varchar(80),Option4 varchar(80),Ans int(5));";
            String qry3="create table Exam_Setup(Id int(20) primary key,Category_id int(9),Time int(255),Marks_p_Qs int(80),Total_Questions int(200),Exam_Name varchar(800),eId int(20), FOREIGN KEY(eId) REFERENCES Exam_Questions(eId));";
       //     String qry3="create table Exam_Setup(eId int(20) primary key, FOREIGN KEY(eId) REFERENCES Exam_Questions(eId),Category_id int(9),Time int(255),Marks_p_Qs int(80),Total_Questions int(200),Exam_Name varchar(800));";
       
       //     String qry4="ALTER Table Exam_Setup add FOREIGN KEY(Identity) REFERENCES Exam_Questions;";
            String qry5="create table Student(Id int(20) primary key,Exam_Name varchar(900),Student_Name varchar(1500),Mobile varchar(800),Questions_Attempted int(200),Correct_Attempt int(200),Wrong_Attempt int(200),Total_Marks int(200),Achieved_Marks int(200));";
            String qry6="create table User(Username varchar(50),Password varchar(50));";
            String qry7="insert into User values('admin','Admin$1913');";
       //     String qry7="insert into Exam_Setup values('1','1','30','1','30','JAVA Genie');";
            
            st.executeUpdate(qry);
            st.executeUpdate(qry1);
            st.executeUpdate(qry2);
            st.executeUpdate(qry3);
          //  st.executeUpdate(qry4);
            st.executeUpdate(qry5);
            st.executeUpdate(qry6);
            st.executeUpdate(qry7);
            JOptionPane.showMessageDialog(this,"Database Successully created.");
            
            new Admin().setVisible(true);
             kill();
            

        }
        catch(ClassNotFoundException e) {
            JOptionPane.showMessageDialog(this,e.getMessage());
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(this,e.getMessage());
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(DatabaseCreator.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DatabaseCreator.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DatabaseCreator.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DatabaseCreator.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DatabaseCreator().setVisible(true);
            }
        });
    }
    public void kill()
	{
		this.dispose();
	}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel2;
    // End of variables declaration//GEN-END:variables
}
