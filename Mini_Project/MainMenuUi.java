package Mini_Project;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;

public class MainMenuUi implements ActionListener {

	static private JFrame frame;
	private JMenuBar menuBar;
	private JMenu mnNewMenu;
	private JMenu mnNewMenu_1;
	private JMenu mnNewMenu_2;
	private JMenuItem mnMyInform;
	private JMenuItem mnMyClass;
	private JMenuItem mnTutorReg;
	private JMenuItem mnLecOpen;
	private JMenuItem mnLecReq;
	private JSeparator separator_1;
	private JLabel lblNewLabel;
	private JButton btnLan;
	private JButton btnMusic;
	private JButton btnPro;
	
	@SuppressWarnings("unused")
	private LoginDto loginDto;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					MainMenuUi window = new MainMenuUi();
//					window.frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the application.
	 */
	public MainMenuUi() {		
		HashMap<String, Object> hm = Common.getHm();
		this.loginDto = (LoginDto) hm.get("LoginDto");	//저장된 로그인 정보 꺼내기
		initialize();
		}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setTitle("인생난다 - Main");
		frame.setBounds(100, 100, 1149, 843);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.getContentPane().setLayout(null);
		
		menuBar = new JMenuBar();
		menuBar.setBackground(Color.WHITE);
		menuBar.setBounds(0, 0, 1100, 61);
		frame.getContentPane().add(menuBar);
		
		mnNewMenu = new JMenu("마이페이지                   ");
		mnNewMenu.setFont(new Font("맑은 고딕", Font.BOLD, 30));
		menuBar.add(mnNewMenu);
		
		mnMyInform = new JMenuItem("개인정보조회");
		mnMyInform.setBackground(Color.WHITE);
		mnMyInform.setFont(new Font("맑은 고딕", Font.PLAIN, 27));
		mnNewMenu.add(mnMyInform);
		mnMyInform.addActionListener(this);
		
		mnMyClass = new JMenuItem("내 강의실");
		mnMyClass.setBackground(Color.WHITE);
		mnMyClass.setFont(new Font("맑은 고딕", Font.PLAIN, 27));
		mnNewMenu.add(mnMyClass);
		mnMyClass.addActionListener(this);
		
		mnNewMenu_1 = new JMenu("튜터모드                    ");
		mnNewMenu_1.setFont(new Font("맑은 고딕", Font.BOLD, 30));
		menuBar.add(mnNewMenu_1);
		
		mnTutorReg = new JMenuItem("튜터등록");
		mnTutorReg.setBackground(Color.WHITE);
		mnTutorReg.setFont(new Font("맑은 고딕", Font.PLAIN, 27));
		mnNewMenu_1.add(mnTutorReg);
		mnTutorReg.addActionListener(this);
		
		mnLecOpen = new JMenuItem("강좌등록");
		mnLecOpen.setBackground(Color.WHITE);
		mnLecOpen.setFont(new Font("맑은 고딕", Font.PLAIN, 27));
		mnNewMenu_1.add(mnLecOpen);
		mnLecOpen.addActionListener(this);
		
		mnNewMenu_2 = new JMenu("수강신청                        ");
		mnNewMenu_2.setFont(new Font("맑은 고딕", Font.BOLD, 30));
		menuBar.add(mnNewMenu_2);
		
		mnLecReq = new JMenuItem("수강신청");
		mnLecReq.setBackground(Color.WHITE);
		mnLecReq.setFont(new Font("맑은 고딕", Font.PLAIN, 27));
		mnNewMenu_2.add(mnLecReq);
		mnLecReq.addActionListener(this);
		
		frame.setVisible(true);
		frame.getContentPane().setLayout(null);
		
		separator_1 = new JSeparator();
		separator_1.setBackground(Color.BLACK);
		separator_1.setForeground(Color.BLACK);
		separator_1.setBounds(34, 213, 1066, 2);
		frame.getContentPane().add(separator_1);
		
		lblNewLabel = new JLabel("새로운 인생이 시작되는 곳 『인생난다』");
		lblNewLabel.setBounds(164, 118, 808, 72);
		lblNewLabel.setFont(new Font("맑은 고딕", Font.BOLD, 45));
		frame.getContentPane().add(lblNewLabel);
		
		btnLan = new JButton("");					
		btnLan.setIcon(new ImageIcon(MainMenuUi.class.getResource("FrnLng.jpg")));
		btnLan.setBounds(30, 259, 325, 473);
		frame.getContentPane().add(btnLan);
        btnLan.addActionListener(this);
		
		
		btnMusic = new JButton("");
		btnMusic.setIcon(new ImageIcon(MainMenuUi.class.getResource("Music.jpg")));
		btnMusic.setBounds(413, 259, 317, 473);
		frame.getContentPane().add(btnMusic);
		btnMusic.addActionListener(this);
		
		btnPro = new JButton("");
		btnPro.setIcon(new ImageIcon(MainMenuUi.class.getResource("Prgm.jpg")));
		btnPro.setBounds(775, 259, 325, 473);
		frame.getContentPane().add(btnPro);
		btnPro.addActionListener(this);
	}
	
	static public JFrame getMainMenuFrame()
	{
		return frame;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		if(e.getSource() == mnMyInform)			//마이페이지
		{
			new MyPageUi();
		}
		else if(e.getSource() == mnMyClass)		//내강의실
		{
			new  MyClassUi();
		}
		else if(e.getSource() == mnTutorReg)	//튜터등록
		{
			new TutorRgstUi();
		}
		else if(e.getSource() == mnLecOpen)		//강좌등록
		{
			new LecOpenUi();
		}
		else if(e.getSource() == mnLecReq)		//수강신청
		{
			new LecEnrlUi();
		}
		else if(e.getSource() == btnLan)		//외국어
		{
			new LecEnrlUi().lecs(1);
		}
		else if(e.getSource() == btnMusic)		//음악
		{
			new LecEnrlUi().lecs(2);
		}
		else if(e.getSource() == btnPro)		//프로그래밍
		{
			new LecEnrlUi().lecs(3);
		}
			
		
	
	}
}
