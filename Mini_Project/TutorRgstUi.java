package Mini_Project;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JSeparator;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.awt.Color;
import javax.swing.ImageIcon;

public class TutorRgstUi implements ActionListener {

	private JFrame frame;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private JLabel lblNewLabel_4;
	private JLabel lblNewLabel_5;
	private JLabel lblNewLabel_6;
	private JLabel lbMSG;
	private JLabel lbMsg;	
	private JButton btnNicknameSlt ;
	private JButton btnRegist;
	private JButton btnCancl;
	private JTextField tfNick;
	private JTextField tfUniv;
	private JTextField tfCert;
	private JSeparator separator;

	private TutorRgstBiz biz = new TutorRgstBiz();
	private HashMap<Integer, String> msgCodeHM = Common.getHmMsgCode();
	private LoginDto loginDto;

	/**
	 * Launch the application.
	 */
//   public static void main(String[] args) {
//      EventQueue.invokeLater(new Runnable() {
//         public void run() {
//            try {
//               TutorRgstUi window = new TutorRgstUi();
//               window.frame.setVisible(true);
//            } catch (Exception e) {
//               e.printStackTrace();
//            }
//         }
//      });
//   }

	/**
	 * Create the application.
	 */
	public TutorRgstUi() {
		initialize();
		
		HashMap<String, Object> hm = Common.getHm();
		this.loginDto = (LoginDto) hm.get("LoginDto"); // 저장된 로그인 정보 꺼내기
		
		System.out.println("튜터등록 전: " + loginDto.toString());
		
		String isTutor = loginDto.getIstutor();
		if("Y".equals(isTutor))
		{
			JOptionPane.showMessageDialog(frame, "이미 튜터로 등록되어있습니다.");
			frame.dispose();
			return;
		}
		MainMenuUi.getMainMenuFrame().setEnabled(false);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setTitle("튜터등록");
		frame.setBounds(100, 100, 650, 800);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		lblNewLabel_5 = new JLabel("");
		lblNewLabel_5.setIcon(new ImageIcon(MainMenuUi.class.getResource("Tutor.png")));
		lblNewLabel_5.setBounds(134, 164, 360, 80);
		frame.getContentPane().add(lblNewLabel_5);

		lblNewLabel = new JLabel("튜터등록");
		lblNewLabel.setFont(new Font("맑은 고딕", Font.BOLD, 50));
		lblNewLabel.setBounds(285, 35, 209, 67);
		frame.getContentPane().add(lblNewLabel);

		lblNewLabel_1 = new JLabel("* 닉네임");
		lblNewLabel_1.setFont(new Font("맑은 고딕", Font.BOLD, 25));
		lblNewLabel_1.setBounds(49, 307, 100, 55);
		frame.getContentPane().add(lblNewLabel_1);

		lblNewLabel_2 = new JLabel("최종학력");
		lblNewLabel_2.setFont(new Font("맑은 고딕", Font.BOLD, 25));
		lblNewLabel_2.setBounds(49, 406, 100, 55);
		frame.getContentPane().add(lblNewLabel_2);

		lblNewLabel_3 = new JLabel("자격증");
		lblNewLabel_3.setFont(new Font("맑은 고딕", Font.BOLD, 25));
		lblNewLabel_3.setBounds(49, 506, 100, 55);
		frame.getContentPane().add(lblNewLabel_3);

		btnRegist = new JButton("등록");
		btnRegist.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		btnRegist.setBounds(125, 606, 130, 50);
		frame.getContentPane().add(btnRegist);
		btnRegist.addActionListener(this);

		btnCancl = new JButton("뒤로가기");
		btnCancl.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		btnCancl.setBounds(376, 606, 130, 50);
		frame.getContentPane().add(btnCancl);
		btnCancl.addActionListener(this);

		separator = new JSeparator();
		separator.setBackground(Color.BLACK);
		separator.setForeground(Color.BLACK);
		separator.setBounds(35, 142, 576, 2);
		frame.getContentPane().add(separator);

		tfNick = new JTextField();
		tfNick.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		tfNick.setBounds(165, 313, 300, 55);
		frame.getContentPane().add(tfNick);
		tfNick.setColumns(10);

		tfUniv = new JTextField();
		tfUniv.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		tfUniv.setBounds(165, 411, 300, 55);
		frame.getContentPane().add(tfUniv);
		tfUniv.setColumns(10);

		tfCert = new JTextField();
		tfCert.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		tfCert.setBounds(165, 506, 300, 55);
		frame.getContentPane().add(tfCert);
		tfCert.setColumns(10);

		lblNewLabel_4 = new JLabel("");
		lblNewLabel_4.setIcon(new ImageIcon(MainMenuUi.class.getResource("Logo120.png")));
		lblNewLabel_4.setBounds(134, 10, 120, 120);
		frame.getContentPane().add(lblNewLabel_4);

		lblNewLabel_6 = new JLabel("멋진 재능으로 튜터 등록하기");
		lblNewLabel_6.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		lblNewLabel_6.setBounds(180, 241, 267, 30);
		frame.getContentPane().add(lblNewLabel_6);
		
		btnNicknameSlt = new JButton("중복조회");
		btnNicknameSlt.setFont(new Font("맑은 고딕", Font.BOLD, 18));
		btnNicknameSlt.setBounds(492, 314, 119, 51);
		frame.getContentPane().add(btnNicknameSlt);
		btnNicknameSlt.addActionListener(this);
		
		lbMSG = new JLabel("");
		lbMSG.setBounds(124, 299, 213, 15);
		frame.getContentPane().add(lbMSG);
		
		lbMsg = new JLabel("");
		lbMsg.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		lbMsg.setBounds(49, 682, 445, 46);
		frame.getContentPane().add(lbMsg);

		frame.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		if(e.getSource() == btnNicknameSlt)	//닉네임중복조회
		{
			if(tfNick.getText().isEmpty()) 
			{
				JOptionPane.showMessageDialog(frame, "별명을 입력해 주세요.");
				return;
			}
			else
			{
				nicknameSlt();
			}
			
		}
		else if (e.getSource() == btnRegist) //튜터등록
		{
			if (tfNick.getText().isEmpty()) 
			{
				JOptionPane.showMessageDialog(frame, "필수항목을 입력해 주시기 바랍니다.");
				return;
			}
			else if(btnNicknameSlt.isEnabled())
			{
				JOptionPane.showMessageDialog(frame, "별명 중복체크 바랍니다.");
				return;
			}
			else
			{
				tutorRgstDP();
			}
		} 
		else if (e.getSource() == btnCancl) //취소
		{
			MainMenuUi.getMainMenuFrame().setEnabled(true);
			frame.dispose();
		}
	}
	
	public void nicknameSlt()	//닉네임중복조회
	{
		HashMap<String, Object> hm = null;
		String msg 	   ;
		int	   msgCode ;
		
		hm = biz.nicknameSlt(tfNick.getText());
		
		msgCode = (int) hm.get("MSGCODE");
		msg = msgCodeHM.get(msgCode);
		
		if (msgCode == 102)
		{
			lbMsg.setText(msg);
			lbMsg.setForeground(Color.RED);
		}
		else
		{
			lbMsg.setText(msg);
			lbMsg.setForeground(Color.BLACK);
			btnNicknameSlt.setEnabled(false);			
		}
	}
	
	public void tutorRgstDP() //튜터등록
	{
		HashMap<String, Object> hm = null;
		TutorDto tutorDto = new TutorDto();
		String msg 	   ;
		int	   msgCode ;
		
		String tutorPk 	 = loginDto.getMemPk();	//memPk = tutorPk
		String tutorNick = 	 tfNick.getText() ;
		String tutorUniv = 	 tfUniv.getText() ;
		String tutorCert =	 tfCert.getText() ;
		tutorUniv = (tutorUniv.isEmpty() ? null : tutorUniv);
		tutorCert = (tutorCert.isEmpty() ? null : tutorCert);
		
		tutorDto.setTutorPk(tutorPk);
		tutorDto.setNickname(tutorNick);
		tutorDto.setUniv(tutorUniv);
		tutorDto.setCert(tutorCert);

		hm = biz.tutorInsert(tutorDto);
		
		String istutor = (String) hm.get("ISTUTOR");
		loginDto.setIstutor(istutor);
		
		msgCode = (int) hm.get("MSGCODE");
		
		msg = msgCodeHM.get(msgCode);
		
		if (msgCode == 199)
		{
			lbMsg.setText(msg);
			lbMsg.setForeground(Color.RED);
		}
		else
		{
			lbMsg.setText(msg);
			lbMsg.setForeground(Color.BLACK);
			btnNicknameSlt.setEnabled(false);			
		}
		
		MainMenuUi.getMainMenuFrame().setEnabled(true);
		frame.dispose();
	}
	
}