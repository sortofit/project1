package Mini_Project;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.regex.Pattern;

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


public class MemberJoinUi extends JDialog implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JSeparator separator;
	private JTextField tfId;
	private JTextField tfTel;
	private JTextField tfAddress;
	private JPasswordField tfPw;
	private JButton btnJoin;
	private JButton btnCancel;
	private JLabel label_3;
	private JPasswordField tfPw1;
	private JButton btnCheck;
	private JLabel lbImage;
	private JLabel lblNewLabel_2;
	private JSeparator separator_1;
	private JSeparator separator_2;
	private JSeparator separator_3;
	private JSeparator separator_4;
	private JLabel lblNewLabel_3;
	private JLabel lbMsg;
	private JLabel lblNewLabel;
    private JLabel lblNewLabel_1;
    private JLabel label;
    private JLabel label_1;
    private JLabel label_2;
	
	private MemberJoinBiz biz;
	private HashMap<Integer, String> hmMsgCode;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		try {
//			MenberJoin dialog = new MenberJoin();
//			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
//			dialog.setVisible(true); 
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

	/**
	 * Create the dialog.
	 */
	public MemberJoinUi() {
		hmMsgCode = Common.getHmMsgCode();
		
		setTitle("회원가입");
		setVisible(true);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 650, 800);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.WHITE);
		contentPanel.setSize(new Dimension(50, 50));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		lblNewLabel = new JLabel("회원가입");
		lblNewLabel.setFont(new Font("맑은 고딕", Font.BOLD, 50));
		lblNewLabel.setBounds(316, 53, 285, 142);
		contentPanel.add(lblNewLabel);

		separator = new JSeparator();
		separator.setBackground(Color.BLACK);
		separator.setSize(new Dimension(300, 300));
		separator.setBounds(24, 228, 566, 2);
		contentPanel.add(separator);

		lblNewLabel_1 = new JLabel("* 아이디");
		lblNewLabel_1.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		lblNewLabel_1.setBounds(34, 249, 100, 39);
		contentPanel.add(lblNewLabel_1);

		label = new JLabel("* 비밀번호 확인");
		label.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		label.setBounds(35, 386, 161, 39);
		contentPanel.add(label);

		label_1 = new JLabel("연락처");
		label_1.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		label_1.setBounds(35, 452, 100, 39);
		contentPanel.add(label_1);

		label_2 = new JLabel("주소");
		label_2.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		label_2.setBounds(35, 533, 100, 39);
		contentPanel.add(label_2);

		tfId = new JTextField();
		tfId.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		tfId.setBackground(Color.WHITE);
		tfId.setBounds(212, 249, 231, 34);
		contentPanel.add(tfId);
		tfId.setColumns(10);

		tfTel = new JTextField();
		tfTel.setFont(new Font("맑은 고딕", Font.BOLD, 15)); 
		tfTel.setBackground(Color.LIGHT_GRAY); 
		tfTel.setColumns(10);
		tfTel.setBounds(212, 452, 370, 34);
		contentPanel.add(tfTel);
		tfTel.setEnabled(false); // 추가 기능 disable

		tfAddress = new JTextField();
		tfAddress.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		tfAddress.setBackground(Color.LIGHT_GRAY);
		tfAddress.setColumns(10);
		tfAddress.setBounds(212, 538, 370, 34);
		contentPanel.add(tfAddress);
		tfAddress.setEnabled(false);

		tfPw = new JPasswordField();
		tfPw.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		tfPw.setBackground(Color.LIGHT_GRAY);
		tfPw.setBounds(212, 342, 370, 34);
		contentPanel.add(tfPw);
		tfPw.setEnabled(false);

		btnJoin = new JButton("확인");
		btnJoin.setForeground(Color.BLACK);
		btnJoin.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		btnJoin.setBounds(144, 607, 138, 49);
		contentPanel.add(btnJoin);
		btnJoin.addActionListener(this);

		btnCancel = new JButton("돌아가기");
		btnCancel.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		btnCancel.setBounds(350, 607, 138, 49);
		contentPanel.add(btnCancel);
		btnCancel.addActionListener(this);

		label_3 = new JLabel("* 비밀번호");
		label_3.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		label_3.setBounds(35, 337, 109, 39);
		contentPanel.add(label_3);

		tfPw1 = new JPasswordField();
		tfPw1.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		tfPw1.setBackground(Color.LIGHT_GRAY);
		tfPw1.setBounds(212, 391, 370, 34);
		contentPanel.add(tfPw1);
		tfPw1.setEnabled(false);

		lbImage = new JLabel("");
		lbImage.setIcon(new ImageIcon(MemberJoinUi.class.getResource("Logo160.png")));
		lbImage.setBounds(114, 42, 160, 160);
		contentPanel.add(lbImage);

		btnCheck = new JButton("중복확인");
		btnCheck.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		btnCheck.setBounds(455, 249, 127, 34);
		contentPanel.add(btnCheck);
		btnCheck.addActionListener(this);

		lblNewLabel_2 = new JLabel("아이디는 이메일 형식으로 입력하세요.");
		lblNewLabel_2.setFont(new Font("맑은 고딕", Font.ITALIC, 13));
		lblNewLabel_2.setBounds(212, 287, 305, 19);
		contentPanel.add(lblNewLabel_2);

		separator_1 = new JSeparator();
		separator_1.setBounds(35, 330, 566, 2);
		contentPanel.add(separator_1);

		separator_2 = new JSeparator();
		separator_2.setBounds(35, 435, 566, 2);
		contentPanel.add(separator_2);

		separator_3 = new JSeparator();
		separator_3.setBounds(35, 521, 566, 2);
		contentPanel.add(separator_3);

		separator_4 = new JSeparator();
		separator_4.setBounds(35, 582, 566, 2);
		contentPanel.add(separator_4);

		lblNewLabel_3 = new JLabel("( - or . )제외 숫자만 입력하세요.");
		lblNewLabel_3.setFont(new Font("맑은 고딕", Font.ITALIC, 13));
		lblNewLabel_3.setBounds(212, 484, 370, 27);
		contentPanel.add(lblNewLabel_3);
		
		lbMsg = new JLabel("");
		lbMsg.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		lbMsg.setBounds(35, 684, 539, 39);
		contentPanel.add(lbMsg);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnJoin) 
		{
			joinDP();
		}
		else if (e.getSource() == btnCancel) 
		{
			new FirstMainUi();
			this.dispose();
		} 
		else if (e.getSource() == btnCheck) {
			confirmEmail();
		}

	}

	private void confirmEmail() {
		biz = new MemberJoinBiz();
		
		HashMap<String, Object> hm = null;
		String pattern;
		String email;
		boolean emailPtnChk;
		String isEmailOK = "N";
		String msg;
		
		email = tfId.getText();

		if (email.isEmpty()) 
		{
			JOptionPane.showMessageDialog(null, "이메일을 입력하세요");
			return;
		}

		pattern = "^[a-zA-Z0-9]*+@[a-z]*+\\.[a-z]*$";
		emailPtnChk = Pattern.matches(pattern, email);
		if (!emailPtnChk) {
			JOptionPane.showMessageDialog(null, "아이디는 e메일 형식으로 입력 하세요.");
			return;
		}
				
		hm = biz.confirmEmail(email); // 입력된 이메일을 넘겨 biz에 메소드 실행
        
		int msgCode = (Integer)hm.get("MSGCODE");
		msg =  (String)hmMsgCode.get(msgCode);
		isEmailOK = (String)hm.get("ISEMAILOK");
        lbMsg.setText(msg);		
        
        if (msgCode == 3 )
        {
        	lbMsg.setText(msg);
        	lbMsg.setForeground(Color.RED);
        }
        
        else if (msgCode == 4)
        {
        	lbMsg.setText(msg);
        	lbMsg.setForeground(Color.BLACK);
        }
      
		
		if ("Y".equals(isEmailOK)) {			
			clear();							
		} 		
	}
	
	private void clear() {
		tfPw.setBackground(Color.WHITE);
		tfPw1.setBackground(Color.WHITE);
		tfTel.setBackground(Color.WHITE);
		tfAddress.setBackground(Color.WHITE);
		tfPw.setEnabled(true);
		tfPw1.setEnabled(true);
		tfTel.setEnabled(true);
		tfAddress.setEnabled(true);
	}

	private void joinDP() {
		biz = new MemberJoinBiz();
		HashMap<String, Object> hm = null;
		
		String email = tfId.getText();
		String pw = new String(tfPw.getPassword());
		String pw1 = new String(tfPw1.getPassword());
		String tel = tfTel.getText();
		String address = tfAddress.getText();
		
		MemDto dto = new MemDto();

		if (email.isEmpty()) {
			JOptionPane.showMessageDialog(null, "이메일을 입력하세요");
			return;
		}
		if (pw.isEmpty() || pw1.isEmpty()) {
			JOptionPane.showMessageDialog(null, "비밀번호를 입력하세요");
			return;
		}
		if(!pw.equals(pw1))
		{
			JOptionPane.showMessageDialog(null, "비밀번호가 일치하지 않습니다.");
			return;
		}
		
		String pattern = "^[0-9]*$";
		boolean telPtnChk = Pattern.matches(pattern, tel);
		if (!telPtnChk) 
		{
			JOptionPane.showMessageDialog(null, "연락처는 숫자만 입력 하세요.");
			return;
		}
		
		address = (address.isEmpty() ? null : address);
		tel = (tel.isEmpty() ? null : tel);
		
		dto.setEmail(email);
		dto.setPw(pw);
		dto.setTel(tel);
		dto.setAdd(address);
		
		hm = biz.memJoin(dto);

        int msgCode = (Integer)hm.get("MSGCODE");
		String msg = (String)hmMsgCode.get(msgCode);
        lbMsg.setText(msg);
        
        
        if(msgCode == 6)
        {
        	tfAddress.setEnabled(false);
        	tfId.setEnabled(false);
        	tfPw.setEnabled(false);
        	tfPw1.setEnabled(false);
        	tfTel.setEnabled(false);
        	btnCheck.setEnabled(false);
        	btnJoin.setEnabled(false);
        }
        
        new FirstMainUi();
		this.dispose();
        
	}
}
