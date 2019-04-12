import java.io.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.*;

import javax.swing.*;
import java.awt.Dimension;
import java.awt.Point;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.text.Caret;

import java.text.SimpleDateFormat;  
import java.util.Date;  
class Notepad extends WindowAdapter implements ActionListener 
{
	int nl,pos1,nls,start,end,flag1,pos;
	Frame f,ff,fr,nf,fnew,frc;
	MenuBar mb;
	Menu m1,m2,m3;
	MenuItem n,o,s,sa,e,fin,finr,del,cut,copy,paste,selall,timeDate,font;
	String font_list[],font_style[]={"BOLD","Italic","Regular"};
	String font_size[]=new String[50];
	JTextArea t=new JTextArea();
	JFrame ffr;
	JList ft,fst,fsz;
	JLabel l11,l22,l33;
	JComboBox cbFontNames,cbFontStyle,cbFontSizes;
	JPanel p1,p2,p3,p4,p5;
	Font allFonts[];
	TextField tf=new TextField(),tf1,tf2;
	Pattern p;
	Dialog d;
	Matcher m;
	Label l3;
	Button bf,cf,fn1,r1,ra1,c1,c2,bd,bs,bc,b5;
	JButton btOK,btCancel;
	String name=null,path=null,str,patt,rep,text,buffer=null;
	public Notepad() throws IOException
	{
		f=new Frame();
		f.setSize(800,500);
		f.setLocation(100,100);
		f.setTitle("Notepad");
		mb=new MenuBar();
		m1=new Menu("File");
		m2=new Menu("Edit");
		m3=new Menu("Format");
		
		n=new MenuItem("New");
		o=new MenuItem("Open");
		s=new MenuItem("Save");
		sa=new MenuItem("Save As");
		e=new MenuItem("Exit");
		
		cut=new MenuItem("Cut");
		copy=new MenuItem("Copy");
		paste=new MenuItem("Paste");
		del=new MenuItem("Delete");
		selall=new MenuItem("Select All");
		timeDate=new MenuItem("Time Date");
		fin=new MenuItem("Find");
		finr=new MenuItem("Find Replace");
		font=new MenuItem("Font");
		
		n.addActionListener(this);
		o.addActionListener(this);
		s.addActionListener(this);
		sa.addActionListener(this);
		e.addActionListener(this);
		cut.addActionListener(this);
		copy.addActionListener(this);
		paste.addActionListener(this);
		del.addActionListener(this);
		timeDate.addActionListener(this);
		selall.addActionListener(this);
		fin.addActionListener(this);
		finr.addActionListener(this);
		font.addActionListener(this);
		f.addWindowListener(this);
		
		f.setMenuBar(mb);
		mb.add(m1);mb.add(m2);mb.add(m3);
		m1.add(n);m1.add(o);m1.add(s);m1.add(sa);m1.addSeparator();m1.add(e);
		m2.add(cut);m2.add(copy);m2.add(paste);m2.add(del);m2.addSeparator();m2.add(selall);m2.add(timeDate);m2.add(fin);m2.add(finr);
		m3.add(font);
		f.setVisible(true);
		
		t.setFont(new Font("Arial", Font.BOLD,20));
		f.add(t);
	}
	public void actionPerformed(ActionEvent e)
	{
		try
		{
			if(e.getActionCommand()=="New")
			{
				if(name==null && !(t.getText()==null ||(t.getText()).equals("")) )
				{
					//System.out.println("inside if");
					fnew=new Frame();
					d=new Dialog(fnew,"Notepad",true);
					d.setLayout(new FlowLayout(FlowLayout.CENTER));
					d.setSize(250,100);
					d.setLocation(300,300);
					
					Label ln=new Label("Do you want to save changes ??");
					d.add(ln);
					
					bs=new Button("Save");
					bs.addActionListener(e13->{try{saveAs();clear();}catch(IOException ee){}exit(fnew);});
					d.add(bs);
					
					bd=new Button("Don't Save");
					bd.addActionListener(e12->{clear();exit(fnew);});
					d.add(bd);
					
					bc=new Button("Cancel");
					bc.addActionListener(e11->exit(fnew));
					d.addWindowListener(this);
					d.add(bc);
					d.setVisible(true);
				}
				else if(name!=null && !isSaved())
				{
					fnew=new Frame();
					d=new Dialog(fnew,"Notepad",true);
					d.setLayout(new FlowLayout(FlowLayout.CENTER));
					d.setSize(250,100);
					d.setLocation(300,300);
					
					Label ln=new Label("Do you want to save changes ??");
					d.add(ln);
					
					bs=new Button("Save");
					bs.addActionListener(e13->{try{save();clear();}catch(IOException ee){}exit(fnew);});
					d.add(bs);
					
					bd=new Button("Don't Save");
					bd.addActionListener(e12->{clear();exit(fnew);});
					d.add(bd);
					
					bc=new Button("Cancel");
					bc.addActionListener(e11->exit(fnew));
					d.addWindowListener(this);
					d.add(bc);
					d.setVisible(true);
				}
				else
					clear();
			}
			else if(e.getActionCommand()=="Open")
			{
				if(name==null && !(t.getText()==null ||(t.getText()).equals("")) )
				{
					fnew=new Frame();
					d=new Dialog(fnew,"Notepad",true);
					d.setLayout(new FlowLayout(FlowLayout.CENTER));
					d.setSize(250,100);
					d.setLocation(300,300);
					
					Label ln=new Label("Do you want to save changes ??");
					d.add(ln);
					
					bs=new Button("Save");
					bs.addActionListener(e13->{try{saveAs();}catch(IOException ee){}exit(fnew);});
					d.add(bs);
					
					bd=new Button("Don't Save");
					bd.addActionListener(e12->{try{open();exit(fnew);}catch(Exception e3){	}});
					d.add(bd);
					
					bc=new Button("Cancel");
					bc.addActionListener(e11->exit(fnew));
					d.addWindowListener(this);
					d.add(bc);
					d.setVisible(true);
				}
				else if(name!=null && !isSaved())
				{
					fnew=new Frame();
					d=new Dialog(fnew,"Notepad",true);
					d.setLayout(new FlowLayout(FlowLayout.CENTER));
					d.setSize(250,100);
					d.setLocation(300,300);
					
					Label ln=new Label("Do you want to save changes ??");
					d.add(ln);
					
					bs=new Button("Save");
					bs.addActionListener(e13->{try{save();open();}catch(IOException ee){}exit(fnew);});
					d.add(bs);
					
					bd=new Button("Don't Save");
					bd.addActionListener(e12->{try{open();exit(fnew);}catch(Exception e3){}});
					d.add(bd);
					
					bc=new Button("Cancel");
					bc.addActionListener(e11->exit(fnew));
					d.addWindowListener(this);
					d.add(bc);
					d.setVisible(true);
				}
				else
					open();
			}
			else if(e.getActionCommand()=="Save")
			{
				if(name==null && !(t.getText()==null ||(t.getText()).equals("")) )
				saveAs();
				else if(name!=null && !isSaved())
				save();
				else
				saveAs();
			}
			else if(e.getActionCommand()=="Save As")
			{
				saveAs();
			}
			else if(e.getActionCommand()=="Exit")
			{
				exit(f);
			}
			else if(e.getActionCommand()=="Copy")
			{
				String temp=t.getSelectedText();
				if(!temp.equals(""))
					buffer=temp;
			}
			else if(e.getActionCommand()=="Paste")
			{
				if(!buffer.equals(null))
				{
					System.out.println("Prince");
					String temp=t.getSelectedText();
					if(temp==""||temp==(null))
					{
						System.out.println("Raj");
						int position = t.getCaretPosition();
						t.insert(buffer,position);
					}
					else
					{
						t.replaceRange(buffer,t.getSelectionStart(),t.getSelectionEnd());
					}
				}
			}
			else if(e.getActionCommand()=="Cut")
			{
				String temp=t.getSelectedText();
				if(!temp.equals(""))
				{
					buffer=temp;
					t.replaceRange("",t.getSelectionStart(),t.getSelectionEnd());
				}
			}
			else if(e.getActionCommand()=="Delete")
			{
				String temp=t.getSelectedText();
				if(!temp.equals(""))
				{
					t.replaceRange("",t.getSelectionStart(),t.getSelectionEnd());
				}
			}
			else if(e.getActionCommand()=="Select All")
			{
				t.selectAll();
			}
			else if(e.getActionCommand()=="Time Date")
			{
				int position=t.getCaretPosition();
				SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy hh:mm a");  
				Date date = new Date();  
				System.out.println(formatter.format(date)); 
				t.insert(formatter.format(date),position);
			}
			else if(e.getActionCommand()=="Find")
			{
				ff=new Frame();
				ff.setTitle("Find");
				ff.setSize(400,200);
				ff.setLocation(300,300);
				ff.setLayout(new GridBagLayout());
				GridBagConstraints gbc=new GridBagConstraints();
				gbc.gridx=gbc.gridy=0;
				gbc.gridwidth=gbc.gridheight=1;
				gbc.ipadx=gbc.ipady=5;
				Label l1=new Label("Find What");
				ff.add(l1,gbc);
				
				tf=new TextField(20);
				gbc.gridx=1;gbc.gridy=0;
				ff.add(tf,gbc);
				
				
				gbc.gridx=0;gbc.gridy=1;
				gbc.gridwidth=3;
				gbc.fill=GridBagConstraints.HORIZONTAL;
				Insets i=new Insets(15,2,5,5);
				gbc.insets=i;
				Panel p=new Panel();
				p.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 10));
				//p.setBackground(Color.red);
				ff.add(p,gbc);
				
				JButton bf=new JButton("Find Next");
				JButton cf=new JButton("Close");
				cf.addActionListener((e1)->{exit(ff);clear1();});
				bf.addActionListener((e2)->{find();});				
				
				p.add(bf);
				p.add(cf);
				
				ff.addWindowListener(this);
				ff.setVisible(true);
			}
			else if(e.getActionCommand()=="Find Replace")
			{
				fr=new Frame();
				fr.setTitle("Find");
				fr.setSize(420,230);
				fr.setLocation(300,300);
				fr.setLayout(new GridBagLayout());
				GridBagConstraints gbc=new GridBagConstraints();
				gbc.gridx=gbc.gridy=0;
				gbc.gridwidth=gbc.gridheight=1;
				gbc.ipadx=gbc.ipady=5;
				Insets i=new Insets(5,5,5,5);
				gbc.insets=i;
				Label l1=new Label("Find ");
				fr.add(l1,gbc);
				
				tf=new TextField(20);
				gbc.gridx=1;gbc.gridy=0;
				fr.add(tf,gbc);
				
				Label l2=new Label("Replace With");
				gbc.gridx=0;gbc.gridy=1;
				fr.add(l2,gbc);
				
				tf2=new TextField(20);
				gbc.gridx=1;gbc.gridy=1;
				fr.add(tf2,gbc);
				
				JButton fn1=new JButton("Find Next");
				gbc.gridx=0;gbc.gridy=2;
				fr.add(fn1,gbc);
				fn1.addActionListener(e4->find());
				
				JButton r1=new JButton("Replace");
				gbc.gridx=1;gbc.gridy=2;
				fr.add(r1,gbc);
				r1.addActionListener(e5->replace());
				
				JButton ra1=new JButton("Replace All");
				gbc.gridx=0;gbc.gridy=3;
				Insets ii=new Insets(10,5,20,5);
				gbc.insets=ii;
				fr.add(ra1,gbc);
				ra1.addActionListener(e6->replaceall());
				
				JButton c1=new JButton("Close");
				gbc.gridx=1;gbc.gridy=3;
				gbc.ipadx=20;
				ii=new Insets(10,5,20,5);
				gbc.insets=ii;
				fr.add(c1,gbc);
				c1.addActionListener((e3)->{exit(fr);clear1();});
				
				fr.addWindowListener(this);
				fr.setVisible(true);
				
			}
			else if(e.getActionCommand()=="Font")
			{
				/*ffr=new JFrame();
				ffr.setTitle("Font");
				ffr.setSize(600,450);
				ffr.setLocation(300,150);
				ffr.setLayout(new GridLayout(4,1,4,4));
				ffr.setTitle("Font");
				
				Panel p1=new Panel(new FlowLayout(FlowLayout.CENTER,4,4));
				ffr.add(p1);
				p1.add(new JLabel("Font"));
				p1.add(new JLabel("Font Style"));
				p1.add(new JLabel("Font Size"));
				p1.setSize(600,50);
				
				Panel p2=new Panel(new GridLayout(1,3,4,4) );
				ffr.add(p2);
				GraphicsEnvironment ge;  
				ge = GraphicsEnvironment.getLocalGraphicsEnvironment();  
				font_list = ge.getAvailableFontFamilyNames();
				ft = new JList(font_list);
				ft.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				ft.setSelectedIndex(0);
				ft.setVisibleRowCount(4);        
				JScrollPane ScrollPane = new JScrollPane(ft);
				//ScrollPane.setPreferredSize(200,100);
				p2.add(ScrollPane);
				
				fst = new JList(font_style);
				fst.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				fst.setSelectedIndex(0);
				fst.setVisibleRowCount(4);        
				ScrollPane = new JScrollPane(fst);
				p2.add(ScrollPane);
				
				for(int tt=0;tt<50;tt++)
					font_size[tt]=""+tt;
				fsz = new JList(font_size);
				fsz.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				fsz.setSelectedIndex(0);
				fsz.setVisibleRowCount(4);        
				ScrollPane = new JScrollPane(fsz);
				p2.add(ScrollPane);
				
				ffr.setVisible(true);*/
				ffr = new JFrame();
				ffr.setLocation(150,280);
				ffr.setSize(500,200);
				ffr.setVisible(true);
				ffr.setResizable(false);
				ffr.setLayout(new BorderLayout());
				//colorText=null;
				ffr.setTitle("Font");
				ffr.setLayout(new GridLayout(4,1));
				
				
				cbFontNames=new JComboBox();
				cbFontSizes=new JComboBox();
				cbFontStyle=new JComboBox();
				btOK=new JButton("OK");  
				btOK.addActionListener(this);

				btCancel=new JButton("Cancel");  
				btCancel.addActionListener(ee->exit(ffr));
				
				p1 = new JPanel();
				p2 = new JPanel();
				p3 = new JPanel();
				p4 = new JPanel();
				//p5 = new JPanel();
				
				p1.add(new JLabel("Font Name"));
				p1.add(cbFontNames);
				ffr.add(p1);
				
				p2.add(new JLabel("Font Style"));
				p2.add(cbFontStyle);
				ffr.add(p2);
				
				p3.setLayout(new FlowLayout());
				p3.add(new JLabel("Font Size"));
				p3.add(cbFontSizes);
				ffr.add(p3);
				
				
				p4.setLayout(new FlowLayout());
				p4.add(btCancel);
				p4.add(btOK);
				ffr.add(p4);
				
				ffr.add(p1);
				ffr.add(p2);
				ffr.add(p3);
				ffr.add(p4);
				
				GraphicsEnvironment ge;  
				ge = GraphicsEnvironment.getLocalGraphicsEnvironment();  
				font_list = ge.getAvailableFontFamilyNames();
				for(String f:font_list)
					cbFontNames.addItem(f);
				for(String f:font_style)
					cbFontStyle.addItem(f);
				//Initialize font sizes
				for(int i=8;i<50;i++)
				cbFontSizes.addItem(i);
			}
			else if(e.getSource() == btOK)
			{
				try
				{
					String text = t.getText();//"BOLD","Italic","Bold Italic","Regular"
					String name = cbFontNames.getSelectedItem().toString();
					String style = cbFontStyle.getSelectedItem().toString();
					int size = (int)cbFontSizes.getSelectedItem();
					
					if(style=="BOLD")
					t.setFont(new Font(name,Font.BOLD,size));
					else if(style=="Italic")
					t.setFont(new Font(name,Font.ITALIC,size));
					else if(style=="Regular")
					t.setFont(new Font(name,Font.PLAIN,size));
					//else
					//t.setFont(new Font(name,Font.MONOSPACED,size));
					t.setText("");
					t.setText(text);
					exit(ffr);
				}
				catch(Exception exc)
				{
					//do nothing
				}
			}
		}
		catch(Exception ex)
		{System.out.println(ex.getMessage());}
	}
	
	public void windowClosing(WindowEvent e)
	{
		boolean bool=false;
		try{bool=isSaved();}catch(Exception ew){}
		if(e.getSource()==f && ( name==null && !(t.getText()==null ||(t.getText()).equals("")) ) )
		{
		
			fnew=new Frame();
			d=new Dialog(fnew,"Notepad",true);
			d.setLayout(new FlowLayout(FlowLayout.CENTER));
			d.setSize(250,100);
			d.setLocation(300,300);
				
			Label ln=new Label("Do you want to save changes ??");
			d.add(ln);
			
			bs=new Button("Save");
			bs.addActionListener(e13->{try{saveAs();}catch(IOException ee){}exit(fnew);});
			d.add(bs);
			
			bd=new Button("Don't Save");
			bd.addActionListener(e12->{try{exit(fnew);exit(f);}catch(Exception e3){}});
			d.add(bd);
			
			bc=new Button("Cancel");
			bc.addActionListener(e11->exit(fnew));
			d.addWindowListener(this);
			d.add(bc);
			d.setVisible(true);
		}
		else if(e.getSource()==f && (name!=null && !bool) )
		{
		
			fnew=new Frame();
			d=new Dialog(fnew,"Notepad",true);
			d.setLayout(new FlowLayout(FlowLayout.CENTER));
			d.setSize(250,100);
			d.setLocation(300,300);
			
			Label ln=new Label("Do you want to save changes ??");
			d.add(ln);
			
			bs=new Button("Save");
			bs.addActionListener(e13->{try{save();}catch(IOException ee){}exit(fnew);});
			d.add(bs);
			
			bd=new Button("Don't Save");
			bd.addActionListener(e12->{try{exit(fnew);exit(f);}catch(Exception e3){}});
			d.add(bd);
			
			bc=new Button("Cancel");
			bc.addActionListener(e11->exit(fnew));
			d.addWindowListener(this);
			d.add(bc);
			d.setVisible(true);
		}
		else
		{
			Window w = e.getWindow();
			w.setVisible(false);
			w.dispose();
			if(e.getSource()==f)
				System.exit(1);
		}
		//start=end=flag1=nl=0;nls=0;
		clear1();
	}
	private void open() throws IOException
	{
		String st="";
		FileDialog fd=new FileDialog(f,"Open",FileDialog.LOAD);
		fd.setVisible(true);
		String name1=fd.getFile();
		String path1=fd.getDirectory();
		if(path1!=null && name1!=null)
		{
			File fn=new File(path1,name1);
			if(!fn.exists())
				notExist();
			else
			{
				path=path1;name=name1;
				st+=readFile(fn);
				t.setText(st);	
				f.setTitle(name);
			}
		}								
	}
	private void saveAs() throws IOException
	{
		FileDialog fd=new FileDialog(f,"SaveAs",FileDialog.SAVE);
		fd.setVisible(true);
		String name1=fd.getFile();
		String path1=fd.getDirectory();
		if(name1!=null && path1!=null)
		{
			name=name1;
			path=path1;
			File fn=new File(path,name);
			fn.createNewFile();//duplicate file
			BufferedWriter fo = new BufferedWriter(new FileWriter(fn));
			fo.write(t.getText());
			fo.close();
			f.setTitle(name);
		}
	}
	private void save() throws IOException
	{
		if(name!=null && path!=null)
		{
			File fn=new File(path,name);
			BufferedWriter fo = new BufferedWriter(new FileWriter(fn));
			fo.write(t.getText());
			fo.close();
			f.setTitle(name);
		}
	}
	private void find()
	{
		nls=nl=0;
		if(flag1==0)
		{
			str=t.getText();
			patt=tf.getText();
			pos=t.getCaretPosition();
			pos1=pos;
			flag1=1;
		}
		start=str.indexOf(patt,pos);
		if(start!=-1)
		{
			nl=0;
			end=start+patt.length();
			pos=end;
			for(int i=0;i<end;i++)
				if(str.charAt(i)=='\n')
					nl++;
			//t.requestFocus();
			t.select(start,end);
		}
		else
		{
			nf=new Frame();
			JOptionPane.showMessageDialog(nf,"Not Found !!");
		}
	}
	private void replace()
	{
		if(flag1==0)
		{
			find();
			flag1=1;
		}
		else
		{
			rep=tf2.getText();
			if(!(t.getSelectedText()).equals(""))
			{
				int len1,len2;
				len1=rep.length();
				len2=(t.getSelectedText()).length();
				t.replaceRange(rep,t.getSelectionStart(),t.getSelectionEnd());
				pos=start+rep.length();
				str=t.getText();
				find();
			}
		}	
	}
	private void replaceall()
	{
		str=t.getText();
		patt=tf.getText();
		rep=tf2.getText();
		t.setText(str.replace(patt,rep));
	}
	private String readFile(File f)throws IOException 
	{
		FileInputStream fis = new FileInputStream(f);
		byte[] data = new byte[(int) f.length()];
		fis.read(data);
		fis.close();
		return(new String(data, "UTF-8"));
	}
	private boolean isSaved()throws Exception
	{
		if(name==null && (t.getText()).equals("") )
			return true;
		else
		{
			File file=new File(path,name);
			String s1 = t.getText();
			String s2 = readFile(file);
			
			if(s1.equals(s2))
				return true;
			else
				return false;
		}
		
	}
	private void clear1()
	{
		start=end=flag1=nl=0;nls=0;
	}
	private void notExist()
	{
		nf=new Frame();
		JOptionPane.showMessageDialog(nf,"File not exist","Error",JOptionPane.ERROR_MESSAGE);
	}
	private void clear()
	{
		t.setText("");
		name=null;
		path=null;
		f.setTitle("Untitled");
	}
	private void exit(Frame f)
	{
		f.setVisible(false);
		f.dispose();
		if(f==this.f)
			System.exit(1);
	}
	public static void main(String args[]) throws Exception
	{
		Notepad n=new Notepad();
		
	}
}