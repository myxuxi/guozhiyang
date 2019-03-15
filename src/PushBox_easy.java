import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.GeneralPath;
import javax.swing.JPanel;

public class PushBox_easy extends JPanel implements  KeyListener{
	private Image pic[] = null; // ͼƬ
	int initX=200,initY=70;
	//map1Ϊ��һ�㣬map2Ϊ�ڶ���
	private int [][]map1={//��һ���ͼ�����ذ��
			{-1,-1,-1,1, 0, 1, 0, 1,-1,-1,-1,-1,-1,-1},
			{-1,-1,-1,0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0},
			{0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 3, 0, 1},
			{1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0},
			{0, 1, 0, 1, 0, 3, 0, 1, 0, 1, 0, 1, 0, 1},
			{1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0},
			{0, 1, 0, 1, 0, 1, 0, 3, 0, 1, 0, 1, 0, 1},
			{1, 0, 3, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0},
			{0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1},
			{1, 0, 3, 0, 1, 0, 1, 0, 1, 0, 1, 2, 1, 0},
			{0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1},
			{1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0},
			{0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0,-1,-1,-1},
			{1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1,-1,-1,-1}
		};
	private int [][]map2={//�ڶ����ͼ�����������
			{-1,-1,-1,2, 2, 2, 2, 2,-1,-1,-1,-1,-1,-1},
			{-1,-1,-1,2, 0, 0, 0, 2, 2, 2, 2, 2, 2, 2},
			{2, 2, 2, 2, 0, 2, 0, 0, 0, 0, 0, 0, 0, 2},
			{2, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 2},
			{2, 0, 0, 0, 1, 0, 2, 0, 0, 2, 0, 0, 0, 2},
			{2, 0, 0, 0, 0, 0, 0, 0, 2, 2, 2, 0, 0, 2},
			{2, 0, 2, 2, 0, 0, 0, 0, 0, 2, 0, 1, 0, 2},
			{2, 0, 0, 0, 1, 2, 2, 0, 0, 0, 1, 0, 0, 2},
			{2, 0, 0, 0, 0, 2, 2, 0, 0, 0, 2, 2, 0, 2},
			{2, 0, 0, 0, 2, 2, 2, 2, 0, 0, 0, 0, 0, 2},
			{2, 0, 0, 0, 0, 2, 2, 0, 0, 0, 0, 0, 0, 2},
			{2, 0, 0, 1, 0, 0, 0, 0, 0, 0, 2, 2, 2, 2},
			{2, 0, 0, 0, 0, 0, 0, 2, 0, 0, 2,-1,-1,-1},
			{2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2,-1,-1,-1}
		};
	// ����һЩ��������Ӧ��ͼ��Ԫ��
	final byte WALL = 1, BOX = 2, BOXONEND = 3, END = 4, MANDOWN = 5,
			MANLEFT = 6, MANRIGHT = 7, MANUP = 8, GRASS = 9, MANDOWNONEND = 10,
			MANLEFTONEND = 11, MANRIGHTONEND = 12, MANUPONEND = 13;
	private int row = 7, column = 7;	//int manx,many;
	// ����ͼƬ
	Image box=Toolkit.getDefaultToolkit().getImage("images\\box.png");
	Image wall =  Toolkit.getDefaultToolkit().getImage("images\\wall.png");
	Image greenBox=  Toolkit.getDefaultToolkit().getImage("images\\greenbox.png");
	Image man =  Toolkit.getDefaultToolkit().getImage("images\\a1.png");//����
	Image background=  Toolkit.getDefaultToolkit().getImage("images\\background.jpg");//����
	public PushBox_easy() {		
		System.out.print("Werewr");	
		// ���ý���
		setFocusable(true);		
		this.addKeyListener(this);
	}

	public void myDrawRect(Graphics g, int x ,int y){//���ƶ����
		Graphics2D g2D=(Graphics2D)g;
		if(g2D==null){
			return;
		}
		GeneralPath path = new GeneralPath();
    	path.moveTo(x+14, y);
    	path.lineTo(x+53, y+10);
    	path.lineTo(x+37, y+37);
    	path.lineTo(x-2, y+26);
    	path.lineTo(x+14, y);
    	g2D.fill(path); //g.draw(myPath);
	}
	// ����Ϸ����
	public void paint(Graphics g) {
		g.clearRect(0,0,this.getWidth(),getHeight());
		g.setColor(Color.BLACK);
		g.drawImage(background, 0, 0,800,800,this);//����Ϸ����
		//���Ƶ�һ��,���ذ��
		for(int i=0; i<map1.length; i++){
			for(int j=0; j<map1[i].length; j++){
                //��������ֵ��������ת��
                int X = initX+36*j-15*i;
                int Y = initY+10*j+25*i;
                if(map1[i][j] == 0){//��ɫ�յ�
                	 /*����paint����ɫ*/  
                	g.setColor(new  Color(255, 220, 220, 220));
                	this.myDrawRect(g, X, Y);
                }
                else if(map1[i][j] == 1){//��ɫ�յ�
                	g.setColor(new  Color(255, 170, 170, 170));
                	this.myDrawRect(g, X, Y);              	
                }
                else if(map1[i][j] == 2){//Ŀ�ĵ�1
                	g.setColor(new  Color(255, 127, 255, 130));
                	this.myDrawRect(g, X, Y);
                }
                else if(map1[i][j] == 3){//Ŀ�ĵ�2
                	g.setColor(new  Color(255, 60, 255, 120));
                	this.myDrawRect(g, X, Y);
                }
			}
		}
		//��ʼ���Ƶڶ���,���������ڲ�
		for(int i=0; i<map2.length; i++){
			for(int j=0; j<map2[i].length; j++){
                //��������ֵ��������ת��
                int X = initX+36*j-15*i;
                int Y = initY+10*j+25*i;			
                if(map2[i][j] == 1){//�ڶ����������Ӵ�
                	g.drawImage(box, X-1, Y-27,this);

                }
                else if(map2[i][j] == 2){//ǽ
                	g.drawImage(wall, X, Y-25,this);
                }
                else if(map2[i][j] == 3){//��ɫ������
                	g.drawImage(greenBox, X-1, Y-27,this);
                }
                //������
                if(i == row && j == column){
                	g.drawImage(man, X-1, Y-27,this);
                }
			} 
		}
	}	

	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			// ����
			moveUp();
		}
		if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			// ����
			moveDown();
		}
		if (e.getKeyCode() == KeyEvent.VK_LEFT) { // ����
			moveLeft();
		}
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) { // ����
			moveRight();
		}
		repaint();		
	}
	private void moveLeft() {
		// TODO Auto-generated method stub
		column--;
	}
	private void moveDown() {
		// TODO Auto-generated method stub
		row++;
	}
	private void moveRight() {
		// TODO Auto-generated method stub
		column++;
	}
	private void moveUp() {
		// TODO Auto-generated method stub
		row--;
	}
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
