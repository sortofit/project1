package Mini_Project;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class LoginUi extends JDialog implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField tfId;
	private JPasswordField tfPw;
	private JButton btnLogin;
	private JButton btnCancel;
	private JLabel lblNewLabel;
	private JSeparator separator;
	private LoginBiz biz;
	private LoginDto loginDto;
	private HashMap<Integer, String> msgCodeHM; 
	private JLabel lbMsg;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JLabel lbImage;
	private JLabel label;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		try {
//			LoginUi_Lbw dialog = new LoginUi_Lbw(); 
//			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
//			dialog.setVisible(true);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

	/**
	 * Create the dialog.
	 */
	public LoginUi() {
		
		HashMap<String, Object> hm = Common.getHm();
		this.loginDto = (LoginDto) hm.get("LoginDto");
		msgCodeHM = Common.getHmMsgCode(); 
		setTitle("로그인");
		setVisible(true);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 650, 800);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.WHITE);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		lblNewLabel = new JLabel("로그인");
		lblNewLabel.setFont(new Font("맑은 고딕", Font.BOLD, 50));
		lblNewLabel.setBounds(333, 41, 189, 169);
		contentPanel.add(lblNewLabel);

	
		separator = new JSeparator();
		separator.setBackground(Color.BLACK);
		separator.setBounds(84, 222, 452, 2);
		contentPanel.add(separator);
	
	
		lblNewLabel_1 = new JLabel("아이디");
		lblNewLabel_1.setFont(new Font("맑은 고딕", Font.BOLD, 25));
		lblNewLabel_1.setBounds(72, 264, 105, 55);
		contentPanel.add(lblNewLabel_1);
	
	
		label = new JLabel("비밀번호");
		label.setFont(new Font("맑은 고딕", Font.BOLD, 25));
		label.setBounds(72, 409, 105, 55);
		contentPanel.add(label);
	
	
		tfId = new JTextField();
		tfId.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		tfId.setBounds(202, 269, 320, 50);
		contentPanel.add(tfId);
		tfId.setColumns(10);
	
	
		tfPw = new JPasswordField();
		tfPw.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		tfPw.setBounds(202, 414, 320, 50);
		contentPanel.add(tfPw);
	
	
		btnLogin = new JButton("로그인");
		btnLogin.setFont(new Font("맑은 고딕", Font.BOLD, 27));
		btnLogin.setBounds(107, 540, 160, 70);
		contentPanel.add(btnLogin);
		btnLogin.addActionListener(this);

	
		btnCancel = new JButton("돌아가기");
		btnCancel.setFont(new Font("맑은 고딕", Font.BOLD, 27));
		btnCancel.setBounds(351, 540, 160, 70);
		contentPanel.add(btnCancel);
		
		lbMsg = new JLabel("");
		lbMsg.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		lbMsg.setBounds(69, 651, 473, 70);
		contentPanel.add(lbMsg);
		
		lblNewLabel_2 = new JLabel("아이디는 이메일 형식으로 입력하세요.");
		lblNewLabel_2.setFont(new Font("맑은 고딕", Font.ITALIC, 15));
		lblNewLabel_2.setBounds(202, 323, 334, 34);
		contentPanel.add(lblNewLabel_2);
		
		lbImage = new JLabel("");
		lbImage.setBounds(115, 46, 160, 160);
		contentPanel.add(lbImage);
		btnCancel.addActionListener(this);
		ImageIcon img = new ImageIcon(LoginUi.class.getResource("Logo160.png"));
		lbImage.setIcon(img);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if(e.getSource() == btnLogin)
		{
			LoginDP();
		}
		else if (e.getSource() == btnCancel)
		{
			new FirstMainUi(); 
			this.dispose();
		}
		
	}

	private void LoginDP() {
		biz = new LoginBiz();
		
		HashMap<String, Object> hm = null;
		MemDto memDto = new MemDto();
		MemDto dtoDB;
		
		String email = tfId.getText();
		String pw    = new String(tfPw.getPassword());
		
		if (pw.isEmpty()&&email.isEmpty()) {
			JOptionPane.showMessageDialog(null, "이메일 및 패스워드를 입력하세요");
			return;
		}
		
		else if (email.isEmpty()) {
			JOptionPane.showMessageDialog(null, "이메일을 입력하세요");
			return;
		}
		
		else if (pw.isEmpty()) {
			JOptionPane.showMessageDialog(null, "패스워드를 입력하세요");
			return;
		}
		
		memDto.setEmail(email);
		memDto.setPw(pw);
		
		hm = biz.confirmPW(memDto);
		int msgCode = (Integer)hm.get("MSGCODE");
		String msg = (String)msgCodeHM.get(msgCode);
		
		
        if(msgCode == 1)
        {	
        	lbMsg.setText(msg);	
        	lbMsg.setForeground(Color.RED);
        }
        else if(msgCode == 2)
		{			
			dtoDB = (MemDto) hm.get("MEMDTO");			
			loginDto.setMemPk(dtoDB.getMemPk());		
			loginDto.setIstutor(dtoDB.getIsTutor());
			
			new MainMenuUi();	
			this.dispose();
		}
					
	}	
}
