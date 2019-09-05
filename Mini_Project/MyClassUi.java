package Mini_Project;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

public class MyClassUi implements ActionListener, MouseListener, ItemListener {
	private JFrame frame;
	private JLabel lblNewLabel;
	private JLabel lblDiv;
	private JButton btnSelect;
	private JButton btnWatch;
	private JLabel lblMsglbl;
	private JScrollPane scrollPane;
	private JTable table;
	private JLabel lblMsg;
	private JComboBox<String> cmbDiv;
	private JTextField tfSchCdtn;
	private JComboBox<String> cmbCtgry;
	private JTextField tf1;
	private JTextField tf2;
	private JTextField tf3;
	private JTextArea tf4;
	private JButton btnNext;
	private JLabel lbImage;
	private JButton btnCancel;
	private JLabel lbLogo;
	private JSeparator separator_1;
	
	private String[] saTit = new String[] {"강좌명", "튜터별명","카테고리"};
	private int[] iaCwidth = new int[] {10, 10, 10};
	private int[] iaAlm = new int[] {JLabel.LEFT, JLabel.LEFT, JLabel.LEFT, };
	private DefaultTableModel dtModel;
	
	private MyClassBiz biz; 
	private LoginDto loginDto;
	private HashMap<Integer, String> hmMsgCode;
	private String[] ctgryArr;
	private ArrayList<String[]> arrLLecs = new ArrayList<String[]>();
	private int pgNum = 1;
	
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					SjyMyClassUi window = new SjyMyClassUi();
//					window.frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	public MyClassUi() {
		HashMap<String, Object> hm = Common.getHm();
		this.loginDto = (LoginDto) hm.get("LoginDto");	
		hmMsgCode = Common.getHmMsgCode();
		
		CategoryBiz bizC = new CategoryBiz();
		ArrayList<String> arrL = bizC.getCtgryArray();
		arrL.add(0, "선택");
		ctgryArr = arrL.toArray(new String[arrL.size()]);
		
		initialize();
		
		MainMenuUi.getMainMenuFrame().setEnabled(false);
	}

	private void initialize() {
		frame = new JFrame();
	      frame.getContentPane().setBackground(Color.WHITE);
	      frame.setTitle("내 강의실");
	      frame.setBounds(100, 100, 828, 840);
	      frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
	      frame.getContentPane().setLayout(null);
	      frame.setVisible(true);
	      
	      lblNewLabel = new JLabel("내 강의실");
	      lblNewLabel.setFont(new Font("맑은 고딕", Font.BOLD, 40));
	      lblNewLabel.setBounds(130, 28, 231, 63);
	      frame.getContentPane().add(lblNewLabel);
	      
	      lblDiv = new JLabel("*조회구분");
	      lblDiv.setFont(new Font("맑은 고딕", Font.BOLD, 25));
	      lblDiv.setBounds(26, 150, 126, 50);
	      frame.getContentPane().add(lblDiv);
	      
	      cmbDiv = new JComboBox<String>();
	      cmbDiv.setBackground(Color.WHITE);
	      cmbDiv.setFont(new Font("맑은 고딕", Font.BOLD, 25));
	      cmbDiv.setModel(new DefaultComboBoxModel<String>(
	      		new String[] {"전체", "강좌명", "튜터명", "카테고리"}));
	      cmbDiv.setBounds(178, 149, 130, 50);
	      frame.getContentPane().add(cmbDiv);
	      cmbDiv.addItemListener(this);
	       
	      tfSchCdtn = new JTextField();
		  tfSchCdtn.setBackground(Color.WHITE);
		  tfSchCdtn.setFont(new Font("맑은 고딕", Font.BOLD, 25));
		  tfSchCdtn.setEditable(false);
		  tfSchCdtn.setBounds(322, 150, 140, 50);
		  frame.getContentPane().add(tfSchCdtn);
		  tfSchCdtn.setColumns(10);
		  
		  cmbCtgry = new JComboBox<String>();
		  cmbCtgry.setBackground(Color.WHITE);
		  cmbCtgry.setFont(new Font("맑은 고딕", Font.BOLD, 25));
		  cmbCtgry.setModel(new DefaultComboBoxModel<String>(ctgryArr));
		  cmbCtgry.setBounds(474, 149, 157, 50);
		  frame.getContentPane().add(cmbCtgry);
		  cmbCtgry.addItemListener(this);
		  cmbCtgry.setEnabled(false);
		  
		  btnSelect = new JButton("조회");
		  btnSelect.setFont(new Font("맑은 고딕", Font.BOLD, 25));
		  btnSelect.setBounds(689, 147, 90, 50);
		  frame.getContentPane().add(btnSelect);
		  btnSelect.addActionListener(this);
		  
		  btnNext = new JButton("다음");
		  btnNext.setFont(new Font("맑은 고딕", Font.BOLD, 17));
		  btnNext.setBounds(689, 414, 90, 30);
		  frame.getContentPane().add(btnNext);
		  btnNext.addActionListener(this);
	      
		  JLabel lbl1 = new JLabel("강좌명");
		  lbl1.setFont(new Font("맑은 고딕", Font.BOLD, 25));
		  lbl1.setBounds(26, 491, 90, 50);
		  frame.getContentPane().add(lbl1);
		  
		  JLabel lbl2 = new JLabel("튜터명");
		  lbl2.setFont(new Font("맑은 고딕", Font.BOLD, 25));
		    lbl2.setBounds(275, 491, 80, 50);
		    frame.getContentPane().add(lbl2);
		    
		    JLabel lbl4 = new JLabel("커리큘럼");
		    lbl4.setFont(new Font("맑은 고딕", Font.BOLD, 25));
		    lbl4.setBounds(26, 619, 110, 30);
		    frame.getContentPane().add(lbl4);
		    
		    JLabel lbl3 = new JLabel("카테고리");
		    lbl3.setFont(new Font("맑은 고딕", Font.BOLD, 25));
		    lbl3.setBounds(522, 491, 109, 50);
		    frame.getContentPane().add(lbl3);
		    
		    tf1 = new JTextField();
		    tf1.setBorder(new LineBorder(Color.LIGHT_GRAY));
		    tf1.setBackground(Color.WHITE);
		    tf1.setFont(new Font("맑은 고딕", Font.BOLD, 25));
		    tf1.setEditable(false);
		    tf1.setBounds(115, 491, 145, 50);
		    frame.getContentPane().add(tf1);
		    tf1.setColumns(10);
	       
		    tf2 = new JTextField();
		    tf2.setBorder(new LineBorder(Color.LIGHT_GRAY));
		    tf2.setBackground(Color.WHITE);
		    tf2.setFont(new Font("맑은 고딕", Font.BOLD, 25));
		    tf2.setEditable(false);
		    tf2.setBounds(363, 491, 145, 50);
		    frame.getContentPane().add(tf2);
		    tf2.setColumns(10);
		    
		    tf3 = new JTextField();
		    tf3.setBorder(new LineBorder(Color.LIGHT_GRAY));
		    tf3.setBackground(Color.WHITE);
		    tf3.setFont(new Font("맑은 고딕", Font.BOLD, 25));
		    tf3.setEditable(false);
		    tf3.setColumns(10);
		    tf3.setBounds(634, 491, 145, 50);
		    frame.getContentPane().add(tf3);
		    
		    lblMsglbl = new JLabel("메세지 :");
		    lblMsglbl.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		    lblMsglbl.setBounds(26, 733, 80, 30);
		    frame.getContentPane().add(lblMsglbl);
		    
	       lblMsg = new JLabel("회원님이 수강하신 강좌를 들어보세요!");
	       lblMsg.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		    lblMsg.setBounds(95, 733, 459, 30);
		    frame.getContentPane().add(lblMsg);
	       
	       btnWatch = new JButton("강좌보기");
	       btnWatch.setFont(new Font("맑은 고딕", Font.BOLD, 25));
	       btnWatch.setBounds(608, 569, 170, 130);
		    frame.getContentPane().add(btnWatch);
		    btnWatch.addActionListener(this);
		    btnWatch.setEnabled(false);
	       
		    scrollPane = new JScrollPane();
		    scrollPane.setFont(new Font("맑은 고딕", Font.PLAIN, 25));
		    scrollPane.setBounds(26, 226, 753, 176);
		    scrollPane.getViewport().setBackground(Color.WHITE);
		    frame.getContentPane().add(scrollPane);
			
			dtModel = new DefaultTableModel(saTit, 0);
			table = new JTable();
			table.setBackground(Color.WHITE);
			table.setAutoCreateColumnsFromModel(false);
		    table.setModel(dtModel);
		    table.addMouseListener(this);
	       
		    for (int i = 0; i < iaAlm.length; i++)
		      {
		         DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
		         renderer.setHorizontalAlignment(iaAlm[i]);
		         TableColumn column = new TableColumn(i, iaCwidth[i], renderer, null);
		         table.addColumn(column);
		      }
		    
		    table.setFocusable(false);
		    scrollPane.setViewportView(table);
		    
		    tf4 = new JTextArea();
		    tf4.setBorder(new LineBorder(Color.LIGHT_GRAY));
		    tf4.setBackground(Color.WHITE);
		    tf4.setFont(new Font("맑은 고딕", Font.BOLD, 25));
		    tf4.setEditable(false);
		    tf4.setColumns(10);
		    tf4.setBounds(151, 569, 431, 130);
		    frame.getContentPane().add(tf4);
		    
		    lbImage = new JLabel("");
		    lbImage.setBounds(379, 28, 400, 80);
		    frame.getContentPane().add(lbImage);
		    lbImage.setIcon(new ImageIcon(FirstMainUi.class.getResource("Tutor.png")));
		    
		    btnCancel = new JButton("뒤로가기");
		    btnCancel.setFont(new Font("맑은 고딕", Font.BOLD, 17));
		    btnCancel.setBounds(563, 733, 216, 30);
		    frame.getContentPane().add(btnCancel);
		    btnCancel.addActionListener(this);
		    
		    lbLogo = new JLabel("");
		    lbLogo.setBounds(26, 15, 90, 90);
		    frame.getContentPane().add(lbLogo);
		    lbLogo.setIcon(new ImageIcon(FirstMainUi.class.getResource("Logo90.png")));
		    
		    JSeparator separator = new JSeparator();
		    separator.setBackground(Color.BLACK);
			separator.setForeground(Color.BLACK);
		    separator.setBounds(26, 115, 753, 2);
		    frame.getContentPane().add(separator);
		    
		    separator_1 = new JSeparator();
		    separator_1.setForeground(Color.BLACK);
		    separator_1.setBackground(Color.BLACK);
		    separator_1.setBounds(26, 464, 753, 2);
		    frame.getContentPane().add(separator_1);
	}
	
	public void watch()
	{
		String dir = /*tf6.getText();*/ "lecDemo.mp4";
		
		try
        { 
            String externalProgram = "C:\\Program Files (x86)\\Windows Media Player\\wmplayer.exe";
            String fileDir = "D:\\"+ dir;
        	String command = externalProgram + " " + fileDir; 
  
            Runtime run  = Runtime.getRuntime(); 
            run.exec(command); 
        } 
  
        catch (IOException e) 
        { 
            e.printStackTrace(); 
        } 
	}
	
	@SuppressWarnings("unchecked")
	public void myLecs()
	{
		biz = new MyClassBiz(); 
		
		int div = 0;
		int ctgryIdx = 0;
		String memPk;
		String sCondition;
		
		HashMap<String, Object> hm;
		ArrayList<LecListDto> arrL;
		int msgCode;
		String msg = null;
		LecListDto dto = null;
		String isNext;
		String isErr;
		
		div      = cmbDiv.getSelectedIndex();
		ctgryIdx = cmbCtgry.getSelectedIndex();
		memPk    = loginDto.getMemPk();
		sCondition = tfSchCdtn.getText();
		
		if((div == 1 || div == 2) && sCondition.isEmpty())
		{
			JOptionPane.showMessageDialog(frame, "검색조건을 입력하세요.");
		}
		else if(div == 3 && ctgryIdx == 0)
		{
			JOptionPane.showMessageDialog(frame, "강좌 카테고리를 선택하세요.");
		}
		else if(div == 3)
		{
			sCondition = String.valueOf(ctgryIdx);
		}
			
		
		hm = biz.myLecs(div, pgNum, memPk, sCondition);
		System.out.println(sCondition);
		arrL    = (ArrayList<LecListDto>)hm.get("ARRAYLIST");
        msgCode = (int)                hm.get("MSGCODE");
        msg     = 					   hmMsgCode.get(msgCode);
        isNext  = (String)			   hm.get("ISNEXT");
        isErr   = (String)             hm.get("ISERR");
        
        if("Y".equals(isErr))
    	{
    		lblMsg.setForeground(Color.RED);
    	}
        else
        {
        	lblMsg.setForeground(Color.BLACK);
        }
        lblMsg.setText(msg);
        
        if("Y".equals(isNext))
        {
        	btnNext.setEnabled(true);
        }
        else
        {
        	btnNext.setEnabled(false);
        }
        
        if(arrL.size()==0)
        {
			return;
        }
        
        for(int i=0; i<arrL.size(); i++)
        {
           dto = arrL.get(i);
           
           String lecPk = dto.getLecPk();
           String title = dto.getTitle();
           String tutorNick = dto.getTutorNick();
           String crcl = dto.getCrcl();
           String ctgryName = dto.getCtgryName();
           String dir = dto.getDir();
          
           String[] arrData = new String[6];
           arrData[0] = lecPk;
           arrData[1] = title;
           arrData[2] = tutorNick;
           arrData[3] = crcl;
           arrData[4] = ctgryName;
           arrData[5] = dir;
           arrLLecs.add(arrData);
//           System.out.println(Arrays.toString(arrData));
           
           String[] saData = new String[3];
           saData[0] = title;
           saData[1] = tutorNick;
           saData[2] = ctgryName;
           dtModel.addRow(saData);
        }
        
	}
	

	
	@Override
	public void actionPerformed(ActionEvent e) {
		if( e.getSource() == btnSelect )
		{
			dtModel.setRowCount(0);
			arrLLecs.clear();
			pgNum = 1;
			myLecs();
		}
		else if(e.getSource() == btnNext)
		{
			pgNum++;
			myLecs();
		}
		else if(e.getSource() == btnWatch)
		{
			watch();
		}
		else if(e.getSource() == btnCancel)
		{
			MainMenuUi.getMainMenuFrame().setEnabled(true);
			frame.dispose();
		}
		
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		int row;	
		
		if ( e.getSource() == table)
		{
			JTable jtable = (JTable)e.getSource();
			row = jtable.getSelectedRow();	
			
			if( row > -1 )
			{
				String title      = table.getValueAt(row, 0).toString();
				String tutorNick  = table.getValueAt(row, 1).toString();
				String ctgry      = table.getValueAt(row, 2).toString();
				String crcl		  = arrLLecs.get(row)[3];
				
				tf1.setText(title);
				tf2.setText(tutorNick);
				tf3.setText(ctgry);
				tf4.setText(crcl);
				
				btnWatch.setEnabled(true);
			}
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		if(e.getSource() == cmbDiv)
		{
			if(e.getStateChange() == ItemEvent.SELECTED)
			{
				if(cmbDiv.getSelectedIndex() == 0)
				{
					tfSchCdtn.setEditable(false);
					cmbCtgry.setEnabled(false);
				}
				else if(cmbDiv.getSelectedIndex() == 1)
				{
					tfSchCdtn.setEditable(true);
					cmbCtgry.setEnabled(false);
				}
				else if(cmbDiv.getSelectedIndex() == 2)
				{
					tfSchCdtn.setEditable(true);
					cmbCtgry.setEnabled(false);
				}
				else if(cmbDiv.getSelectedIndex() == 3)
				{
					tfSchCdtn.setEditable(false);
					cmbCtgry.setEnabled(true);
				}
			}
		}
		else if(e.getSource() == cmbCtgry)
	      {
	         if(e.getStateChange() == ItemEvent.SELECTED)
	         {
	            String ctgry = String.valueOf(cmbCtgry.getSelectedItem());
	        	tfSchCdtn.setText(ctgry);
	         }
	      }
	}
}
