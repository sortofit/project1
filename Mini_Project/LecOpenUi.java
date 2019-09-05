package Mini_Project;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.HashMap;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.SystemColor;

public class LecOpenUi implements ActionListener, ItemListener {
	
	private JFrame frame;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private JLabel lblNewLabel_4;
	private JLabel label;
	private JLabel lbMsg;
	private JLabel lbImage;
	private JTextField tfLectitle;
	private JTextField tfCrcl;
	private JTextField tfDir;
	private JButton btnRegist;
	private JButton btnCancl;
	private JSeparator separator;
	private JComboBox<String> cmbCtgry;

	private LecOpenBiz biz  = new LecOpenBiz();
	private HashMap<Integer, String> msgCodeHM = Common.getHmMsgCode();
	private LoginDto loginDto;
	
	String cmbCtgr;
   
   /**
    * Launch the application.
    */
//   public static void main(String[] args) {
//      EventQueue.invokeLater(new Runnable() {
//         public void run() {
//            try {
//               LecOpenUi window = new LecOpenUi();
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
   public LecOpenUi() {
      initialize();
      
      HashMap<String, Object> hm = Common.getHm();
      this.loginDto = (LoginDto) hm.get("LoginDto");	//저장된 로그인 정보 꺼내기	
      
      System.out.println("튜터등록 후 :" + loginDto.toString());
      
      String isTutor = loginDto.getIstutor();
		if("N".equals(isTutor))
		{
			JOptionPane.showMessageDialog(frame, "튜터등록을 먼저 진행해 주시기 바랍니다.");
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
      frame.setTitle("강좌등록");
      frame.setBounds(100, 100, 650, 800);
      frame.setLocationRelativeTo(null);
      frame.setResizable(false);
      frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
      frame.getContentPane().setLayout(null);
      frame.setVisible(true);
      
      lblNewLabel = new JLabel("강좌등록");
      lblNewLabel.setFont(new Font("맑은 고딕", Font.BOLD, 55));
      lblNewLabel.setBounds(292, 53, 251, 77);
      frame.getContentPane().add(lblNewLabel);
      
      lblNewLabel_1 = new JLabel("* 강좌명");
      lblNewLabel_1.setFont(new Font("맑은 고딕", Font.BOLD, 25));
      lblNewLabel_1.setBounds(67, 292, 100, 55);
      frame.getContentPane().add(lblNewLabel_1);
      
      lblNewLabel_2 = new JLabel("* 카테고리");
      lblNewLabel_2.setFont(new Font("맑은 고딕", Font.BOLD, 25));
      lblNewLabel_2.setBounds(67, 381, 130, 55);
      frame.getContentPane().add(lblNewLabel_2);
      
      lblNewLabel_3 = new JLabel("* 커리큘럼");
      lblNewLabel_3.setFont(new Font("맑은 고딕", Font.BOLD, 25));
      lblNewLabel_3.setBounds(67, 469, 130, 55);
      frame.getContentPane().add(lblNewLabel_3);
      
      label = new JLabel("* 파일이름");
      label.setFont(new Font("맑은 고딕", Font.BOLD, 25));
      label.setBounds(67, 554, 130, 55);
      frame.getContentPane().add(label);
      
      separator = new JSeparator();
      separator.setBounds(49, 168, 545, 2);
      frame.getContentPane().add(separator);
      
      btnRegist = new JButton("등록");
      btnRegist.setFont(new Font("맑은 고딕", Font.BOLD, 20));
      btnRegist.setBounds(115, 658, 130, 50);
      frame.getContentPane().add(btnRegist);
      btnRegist.addActionListener(this);
      
      btnCancl = new JButton("뒤로가기");
      btnCancl.setFont(new Font("맑은 고딕", Font.BOLD, 20));
      btnCancl.setBounds(358, 658, 130, 50);
      frame.getContentPane().add(btnCancl);
      btnCancl.addActionListener(this);
      
      tfLectitle = new JTextField();
      tfLectitle.setFont(new Font("맑은 고딕", Font.BOLD, 15));
      tfLectitle.setBounds(243, 296, 300, 55);
      frame.getContentPane().add(tfLectitle);
      tfLectitle.setColumns(10);
      
      cmbCtgry = new JComboBox<String>();
      cmbCtgry.setFont(new Font("맑은 고딕", Font.BOLD, 15));
      cmbCtgry.setForeground(Color.BLACK);
      cmbCtgry.setBackground(SystemColor.inactiveCaptionBorder); 
      cmbCtgry.setModel(new DefaultComboBoxModel<String>(
    		  new String[] {"선택", "외국어", "음악", "프로그래밍"}));
      cmbCtgry.setBounds(243, 385, 300, 55);
      frame.getContentPane().add(cmbCtgry);
      cmbCtgry.addItemListener(this);
      
      tfCrcl = new JTextField();
      tfCrcl.setFont(new Font("맑은 고딕", Font.BOLD, 15));
      tfCrcl.setBounds(243, 473, 300, 55);
      frame.getContentPane().add(tfCrcl);
      tfCrcl.setColumns(10);
      
      tfDir = new JTextField();
      tfDir.setFont(new Font("맑은 고딕", Font.BOLD, 15));
      tfDir.setColumns(10);
      tfDir.setBounds(243, 558, 300, 55);
      frame.getContentPane().add(tfDir);
      
      lblNewLabel_4 = new JLabel("");
      lblNewLabel_4.setIcon(new ImageIcon(MainMenuUi.class.getResource("Logo150.png")));
      lblNewLabel_4.setBounds(111, 12, 150, 150);
      frame.getContentPane().add(lblNewLabel_4);
      
      lbMsg = new JLabel("");
      lbMsg.setBounds(67, 718, 476, 44);
      frame.getContentPane().add(lbMsg);
      
      lbImage = new JLabel("");
      lbImage.setIcon(new ImageIcon(MainMenuUi.class.getResource("Tutor.png")));
      lbImage.setBounds(115, 180, 400, 80);
      frame.getContentPane().add(lbImage);
   }

   @Override
   public void actionPerformed(ActionEvent e) 
   {
      if( e.getSource() == btnRegist )		//강좌등록버튼
      {
    	  if(tfLectitle.getText().isEmpty() || cmbCtgr.isEmpty() || tfCrcl.getText().isEmpty() || tfDir.getText().isEmpty())
          {
             JOptionPane.showMessageDialog(frame, "필수항목을 입력해 주시기 바랍니다.");
             return;
          }
    	  else
    	  {
    		  lecOpenDP();
    	  }
      }
      else if( e.getSource() == btnCancl)	//취소버튼
      {
    	  MainMenuUi.getMainMenuFrame().setEnabled(true);
    	  frame.dispose();
      }
   }
   
   @Override
   public void itemStateChanged(ItemEvent e) 
   {
      if(e.getSource() == cmbCtgry)
      {
    	  if(e.getStateChange() == ItemEvent.SELECTED)
    	  {
    		  cmbCtgr = e.getItem().toString();
    	  }
      }
   }
   
   public void lecOpenDP()		//강좌등록
   {
      HashMap<String, Object> hm;
      LecDto lecDto;
      String  msg;
  	  int	  msgCode;
      
      String tutorPk = loginDto.getMemPk();
      String title	 = tfLectitle.getText();
      String ctgry 	 = cmbCtgr;
      String crcl 	 = tfCrcl.getText();
      String dir 	 = tfDir.getText();
      
      lecDto = new LecDto();
      lecDto.setTutorPk(tutorPk);
      lecDto.setTitle(title);
      lecDto.setCtgr(ctgry);
      lecDto.setCrcl(crcl);
      lecDto.setDir(dir);

      hm = biz.lecInsert(lecDto);
          
      msgCode = (int) hm.get("MSGCODE");
      
      msg = msgCodeHM.get(msgCode);

      if( msgCode == 105)
      {
    	  lbMsg.setText(msg);
          lbMsg.setForeground(Color.BLACK);
          frame.dispose();
      }
      else
      {
    	  lbMsg.setText(msg);
          lbMsg.setForeground(Color.RED);
      }
      MainMenuUi.getMainMenuFrame().setEnabled(true);
	  frame.dispose();

   }
   
}