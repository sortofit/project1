package Mini_Project;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Point;

public class FirstMainUi implements ActionListener {

	private JFrame frame;
	private JButton btnLogin;
	private JButton btnJoin;
	private JLabel lbImage;
	
	private LoginDto loginDto;
	private HashMap<String, Object> hm;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) { 
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					/*FirstMainUi window = */new FirstMainUi();
				} catch (Exception e) {
					e.printStackTrace(); 
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public FirstMainUi() {
		initialize();
		
		loginDto = new LoginDto();
		hm = Common.getHm();
		hm.put("LoginDto", loginDto);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setBounds(100, 100, 650, 800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setTitle("인생난다"); 
		frame.setVisible(true);
		
		btnLogin = new JButton("로그인");
		btnLogin.setForeground(Color.BLACK); 
		btnLogin.setLocation(new Point(160, 70));
		btnLogin.setBounds(74, 615, 160, 70);
		btnLogin.setFont(new Font("맑은 고딕", Font.BOLD, 27));
		frame.getContentPane().add(btnLogin);
		btnLogin.addActionListener(this);	
		
		btnJoin = new JButton("회원가입");
		btnJoin.setBounds(370, 615, 160, 70);
		btnJoin.setFont(new Font("맑은 고딕", Font.BOLD, 27));
		frame.getContentPane().add(btnJoin);
		btnJoin.addActionListener(this);
		
		lbImage = new JLabel("");
		lbImage.setBounds(12, 10, 596, 595);
		lbImage.setHorizontalAlignment(SwingConstants.CENTER);
		lbImage.setIcon(new ImageIcon(FirstMainUi.class.getResource("Logo.png")));
		frame.getContentPane().add(lbImage);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnLogin)
		{
			new LoginUi();
			frame.dispose();
		}
		else if (e.getSource() == btnJoin) 
		{			
			new MemberJoinUi(); 
			frame.dispose();
		}
	}
}
