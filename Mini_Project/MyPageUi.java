package Mini_Project;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.regex.Pattern;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JSeparator;

public class MyPageUi implements ActionListener {
	
	private JFrame frame;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private JLabel lblNewLabel_4;
	private JLabel label_1; 
	private JLabel label_2;
	private JTextField tfID;
	private JTextField tfTel;
	private JTextField tfAddress;
	private JPasswordField pwf;
	private JPasswordField pwfNewPw;
	private JPasswordField pwfNewPwChk;
	private JButton btnPwConfirm;
	private JButton btnCancel;
	private JButton btnUpdate;
	private JButton btnPwUpdate;
	private JLabel lbMsg;
	private JSeparator separator;
	private JSeparator separator_1;
	private JSeparator separator_2;
	private JSeparator separator_3;
	private JSeparator separator_4;
	private JLabel lbImage;

	private MyPageBiz biz;
	private HashMap<Integer, String> msgCodeHM = Common.getHmMsgCode();
	private LoginDto loginDto;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					MyPageUi2 window = new MyPageUi2();
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
	public MyPageUi() {
		initialize();
		
		HashMap<String, Object> hm = Common.getHm();
		this.loginDto = (LoginDto) hm.get("LoginDto");
		
		biz = new MyPageBiz();
		String memPk = this.loginDto.getMemPk();	//마이페이지 ID default로 불러오기
		String email = biz.myPageInitiate(memPk);
		tfID.setText(email);
		
		MainMenuUi.getMainMenuFrame().setEnabled(false);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {	
		frame = new JFrame();
		frame.getContentPane().setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setTitle("마이페이지");
		frame.setBounds(100, 100, 650, 800);
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);

		lblNewLabel = new JLabel("마이페이지 - 개인정보 조회");
		lblNewLabel.setFont(new Font("맑은 고딕", Font.BOLD, 30));
		lblNewLabel.setBounds(198, 36, 407, 119);
		frame.getContentPane().add(lblNewLabel);
		
		lblNewLabel_1 = new JLabel("* 아이디");
		lblNewLabel_1.setFont(new Font("맑은 고딕", Font.BOLD, 17));
		lblNewLabel_1.setBounds(53, 199, 137, 30);
		frame.getContentPane().add(lblNewLabel_1);
		
		lblNewLabel_2 = new JLabel("* 비밀번호");
		lblNewLabel_2.setFont(new Font("맑은 고딕", Font.BOLD, 17));
		lblNewLabel_2.setBounds(53, 274, 110, 30);
		frame.getContentPane().add(lblNewLabel_2);
		
		lblNewLabel_3 = new JLabel("연락처");
		lblNewLabel_3.setFont(new Font("맑은 고딕", Font.BOLD, 17));
		lblNewLabel_3.setBounds(53, 491, 80, 30);
		frame.getContentPane().add(lblNewLabel_3);
		
		lblNewLabel_4 = new JLabel("주소");
		lblNewLabel_4.setFont(new Font("맑은 고딕", Font.BOLD, 17));
		lblNewLabel_4.setBounds(53, 563, 80, 30);
		frame.getContentPane().add(lblNewLabel_4);
		
		tfID = new JTextField();
		tfID.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		tfID.setEnabled(false);			//setEnabled false로 default
		tfID.setBounds(192, 193, 268, 40);
		frame.getContentPane().add(tfID);
		tfID.setColumns(10);
		
		pwf = new JPasswordField();
		pwf.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		pwf.setBounds(192, 270, 268, 40);
		frame.getContentPane().add(pwf);
		
		tfTel = new JTextField();
		tfTel.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		tfTel.setBounds(192, 487, 393, 40);
		frame.getContentPane().add(tfTel);
		tfTel.setColumns(10);
		tfTel.setEnabled(false);
		
		tfAddress = new JTextField();
		tfAddress.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		tfAddress.setBounds(192, 559, 393, 40);
		frame.getContentPane().add(tfAddress);
		tfAddress.setColumns(10);
		tfAddress.setEnabled(false);
		
		btnPwConfirm = new JButton("확인");
		btnPwConfirm.setFont(new Font("맑은 고딕", Font.BOLD, 13));
		btnPwConfirm.setBounds(472, 270, 110, 40);
		frame.getContentPane().add(btnPwConfirm);
		btnPwConfirm.addActionListener(this);
		
		btnCancel = new JButton("돌아가기");
		btnCancel.setFont(new Font("맑은 고딕", Font.BOLD, 25));
		btnCancel.setBounds(386, 632, 137, 53);
		frame.getContentPane().add(btnCancel);
		btnCancel.addActionListener(this);
		
		btnUpdate = new JButton("변경");
		btnUpdate.setFont(new Font("맑은 고딕", Font.BOLD, 25));
		btnUpdate.setBounds(100, 632, 137, 53);
		frame.getContentPane().add(btnUpdate);
		btnUpdate.setEnabled(false);
		btnUpdate.addActionListener(this);
		
		pwfNewPw = new JPasswordField();
		pwfNewPw.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		pwfNewPw.setEnabled(false);
		pwfNewPw.setBounds(192, 340, 268, 40);
		frame.getContentPane().add(pwfNewPw);
		
		label_1 = new JLabel("새비밀번호");
		label_1.setFont(new Font("맑은 고딕", Font.BOLD, 17));
		label_1.setBounds(53, 340, 110, 30);
		frame.getContentPane().add(label_1);
		
		label_2 = new JLabel("새비밀번호확인");
		label_2.setFont(new Font("맑은 고딕", Font.BOLD, 17));
		label_2.setBounds(53, 414, 127, 30);
		frame.getContentPane().add(label_2);
		
		pwfNewPwChk = new JPasswordField();
		pwfNewPwChk.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		pwfNewPwChk.setEnabled(false);
		pwfNewPwChk.setBounds(192, 410, 268, 40);
		frame.getContentPane().add(pwfNewPwChk);
		
		btnPwUpdate = new JButton("비밀번호변경");
		btnPwUpdate.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		btnPwUpdate.setBounds(472, 410, 110, 40);
		frame.getContentPane().add(btnPwUpdate);
		btnPwUpdate.addActionListener(this);
		btnPwUpdate.setEnabled(false);
		
		lbMsg = new JLabel("");
		lbMsg.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		lbMsg.setBounds(53, 695, 539, 53);
		frame.getContentPane().add(lbMsg);
		
		separator = new JSeparator();
		separator.setBackground(Color.BLACK);
		separator.setForeground(Color.BLACK);
		separator.setBounds(39, 166, 553, 2);
		frame.getContentPane().add(separator);
		
		lbImage = new JLabel("");
		lbImage.setBounds(54, 25, 130, 130);
		frame.getContentPane().add(lbImage);
		lbImage.setIcon(new ImageIcon(MyPageUi.class.getResource("Logo130.png")));
		
		separator_1 = new JSeparator();
		separator_1.setBounds(39, 250, 553, 2);
		frame.getContentPane().add(separator_1);
		
		separator_2 = new JSeparator();
		separator_2.setBounds(39, 470, 553, 2);
		frame.getContentPane().add(separator_2);
		
		separator_3 = new JSeparator();
		separator_3.setBounds(39, 542, 553, 2);
		frame.getContentPane().add(separator_3);
		
		separator_4 = new JSeparator();
		separator_4.setBounds(39, 614, 553, 2);
		frame.getContentPane().add(separator_4);

	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		if( e.getSource() == btnPwConfirm )
		{
			if( pwf.getPassword().length == 0 )
			{
				JOptionPane.showMessageDialog(frame, "회원정보 확인을 위해, 비밀번호를 입력 바랍니다.");	
			}
			else
			{
				confirmPw();
			}
		}
		else if( e.getSource() == btnUpdate )		//회원정보 변경 버튼
		{		
			String tel = tfTel.getText();			
			String pattern = "^[0-9]*$";
			boolean telPtnChk = Pattern.matches(pattern, tel);
			if (!telPtnChk) 
			{
				JOptionPane.showMessageDialog(null, "연락처는 숫자만 입력 하세요.");
			}
			else
			{
				memUpdate();
			}
		}
		else if( e.getSource() == btnPwUpdate )		//pw 변경 버튼
		{
			if( pwfNewPw.getPassword().length == 0 || pwfNewPwChk.getPassword().length == 0 )
			{
				JOptionPane.showMessageDialog(frame, "변경할 비밀번호를 입력바랍니다.");	
			}
			else
			{
				memUpdate();
			}
		}
		else if( e.getSource() == btnCancel)
		{
			MainMenuUi.getMainMenuFrame().setEnabled(true);
			frame.dispose();
		}
	}
	
	public void confirmPw()		//회원정보 확인용 비밀번호 조회
	{
		HashMap<String,Object> hm;
		MemDto memDto = new MemDto();		
		MemDto memDtoDB;
		String isPwSame;
		int msgCode;
		String msg;
		
		String memPk = loginDto.getMemPk();
		String pw	 =  new String(pwf.getPassword());
		memDto.setMemPk(memPk);
		memDto.setPw(pw);
		
		hm = biz.confirmPwMyPage(memDto);
		
		memDtoDB = (MemDto)hm.get("MEMDTO");
	    isPwSame = (String)hm.get("ISPWSAME");
	    msgCode  = (int)   hm.get("MSGCODE");
	    msg	 	 =  msgCodeHM.get(msgCode);
		
	    lbMsg.setText(msg);
	    
		if("Y".equals(isPwSame))
		{
			String tel = memDtoDB.getTel();
			String add = memDtoDB.getAdd();
			
			pwf.setEnabled(false);
			
			tfTel.setText(tel);
			tfTel.setEnabled(true);
			
			tfAddress.setText(add);
			tfAddress.setEnabled(true);
			
			pwfNewPw.setEnabled(true);
			pwfNewPwChk.setEnabled(true);
			btnPwUpdate.setEnabled(true);
			btnUpdate.setEnabled(true);
			btnPwConfirm.setEnabled(false);
		}
	}
	
	public void memUpdate()
	{
		HashMap<String, Object> hm = null;
		MemDto memDto = new MemDto();
		int msgCode;
		String msg;
		
		String memPk 	= loginDto.getMemPk();
		String email 	= tfID.getText();
		String pw 	 	= new String(pwf.getPassword());
		String newPw 	= new String(pwfNewPw.getPassword());
		String newPwChk = new String(pwfNewPwChk.getPassword());
		String tel 	 	= tfTel.getText();
		String add   	= tfAddress.getText();
		
		
		if( newPw.isEmpty() || newPwChk.isEmpty() )	
		{//정보변경
			memDto.setPw(pw);
		}
		else									
		{//pw변경
			if( newPwChk.equals(newPw) )
			{
				memDto.setPw(newPw);
			}		
			else
			{
				JOptionPane.showMessageDialog(frame, "변경할 비밀번호가 일치하지 않습니다.");	
				return;
			}
		}
		
		memDto.setMemPk(memPk);
		memDto.setEmail(email);
		memDto.setTel(tel);
		memDto.setAdd(add);
		
		hm = biz.update(memDto);
		
		int ret = (int) hm.get("RET");
		msgCode = (int) hm.get("MSGCODE");
		
		msg = msgCodeHM.get(msgCode);
		
		if(ret == 1)
		{					
			lbMsg.setForeground(Color.BLACK);
		}
		else if( ret == 0 )
		{
			lbMsg.setForeground(Color.red);
		}	
		
		lbMsg.setText(msg);
		
	}
}
