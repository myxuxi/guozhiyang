import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.GeneralPath;

import javax.swing.JOptionPane;
import javax.swing.JPanel;


public class PushBox extends JPanel implements  KeyListener{
	private Image pic[] = null; // ͼƬ
	int initX=200,initY=70;
	//map1Ϊ��һ�㣬map2Ϊ�ڶ���
	private int [][]map1={//��һ���ͼ�����ذ��
			{-1,-1,-1,1, 0, 1, 0, 1,-1,-1,-1,-1,-1,-1},
			{-1,-1,-1,0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0},
			{0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 2, 0, 1},
			{1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0},
			{0, 1, 0, 1, 0, 2, 0, 1, 0, 1, 0, 1, 0, 1},
			{1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0},
			{0, 1, 0, 1, 0, 1, 0, 2, 0, 1, 0, 1, 0, 1},
			{1, 0, 2, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0},
			{0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1},
			{1, 0, 2, 0, 1, 0, 1, 0, 1, 0, 1, 2, 1, 0},
			{0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1},
			{1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0},
			{0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0,-1,-1,-1},
			{1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1,-1,-1,-1}
		};
	private int [][]map2={//�ڶ����ͼ��������
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
	final byte WALL = 2, BOX = 1, BOXONEND = 3, END = 2,
	        WhiteGRASS = 0, BlackGRASS = 1;	        
	private int row = 7, column = 7;	
	// ����ͼƬ
	Image box=Toolkit.getDefaultToolkit().getImage("images\\box.png");
	Image wall =  Toolkit.getDefaultToolkit().getImage("images\\wall.png");
	Image greenBox=  Toolkit.getDefaultToolkit().getImage("images\\greenbox.png");
	Image man =  Toolkit.getDefaultToolkit().getImage("images\\a1.png");//����	
	Image background=  Toolkit.getDefaultToolkit().getImage("images\\background.jpg");//����

	public PushBox() {		

		// ���ý���
		setFocusable(true);		
		this.addKeyListener(this);

	}

	public void myDrawRect(Graphics g, int x, int y) {// ���ƶ����
		Graphics2D g2D = (Graphics2D) g;
		if (g2D == null) {
			return;
		}
		GeneralPath path = new GeneralPath();
		path.moveTo(x + 14, y);
		path.lineTo(x + 53, y + 10);
		path.lineTo(x + 37, y + 37);
		path.lineTo(x - 2, y + 26);
		path.lineTo(x + 14, y);
		g2D.fill(path); // g.draw(myPath);
	}
	// ����Ϸ����
	public void paint(Graphics g) {
		g.clearRect(0,0,this.getWidth(),getHeight());
		g.setColor(Color.BLACK);
		g.drawImage(background, 0, 0,800,800,this);//����Ϸ����
		//���Ƶ�һ��,���ذ��
		//WhiteGRASS = 0, END = 2,BlackGRASS = 1;
		for(int i=0; i<map1.length; i++){
			for(int j=0; j<map1[i].length; j++){
                //��������ֵ��������ת��
                int X = initX+36*j-15*i;
                int Y = initY+10*j+25*i;
                if(map1[i][j] == WhiteGRASS){//��ɫ�յ�
                	 /*����paint����ɫ*/  
                	g.setColor(new  Color(255, 220, 220, 220));
                	this.myDrawRect(g, X, Y);
                }
                else if(map1[i][j] == BlackGRASS){//��ɫ�յ�
                	g.setColor(new  Color(255, 170, 170, 170));
                	this.myDrawRect(g, X, Y);              	
                }
                else if(map1[i][j] == END){//Ŀ�ĵ�
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
                if(map2[i][j] == BOX){//�ڶ����������Ӵ�
                	g.drawImage(box, X-1, Y-27,this);

                }
                else if(map2[i][j] == WALL){//ǽ
                	g.drawImage(wall, X, Y-25,this);
                }
                else if(map2[i][j] == BOXONEND){//Ŀ�ĵص���ɫ����
                	g.drawImage(greenBox, X-1, Y-27,this);
                }
                //������
                if(i == row && j == column){
                	g.drawImage(man, X-1, Y-27,this);
                }
			} 
		} 
	}	
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
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
		if (isWin()) {
			JOptionPane.showMessageDialog(this, "��ϲ��ͨ��һ�أ�����");
		}
	}
	private void moveLeft() {
		// TODO Auto-generated method stub
		// ��һλp1ΪWALL
		if (map2[row][column - 1] == WALL)
			return;
		// ��һλp1Ϊ BOX
		if (map2[row][column - 1] == BOX || map2[row][column - 1] == BOXONEND) {
			if (map2[row][column - 2] == WALL)
				return;
			if (map2[row][column - 2] == BOX)
				return;
			if (map2[row][column - 2] == BOXONEND)
				return;
			// ��2λp2Ϊ END,GRASS������һ��
			if (map1[row][column - 2] == END
					|| map1[row][column - 2] == WhiteGRASS
					|| map1[row][column - 2] == BlackGRASS) {
				// ����һλp2Ϊ END
				if (map1[row][column - 2] == END) // ����һλp2Ϊ END
					map2[row][column - 2] = BOXONEND;
				if (map1[row][column - 2] == WhiteGRASS // ����һλp2ΪGRASS
						|| map1[row][column - 2] == BlackGRASS)
					map2[row][column - 2] = BOX;
				map2[row][column - 1] = -1;// ԭ�����ӱ��Ƶ�
				// ���뿪���޸��˵�����
				man = Toolkit.getDefaultToolkit().getImage("images\\b1.png");// ��������				
				column--;
			}
		} else {
			// ��һλΪ GRASS,END,����������ô���
			if (map1[row][column - 1] == WhiteGRASS
					|| map1[row][column - 1] == BlackGRASS
					|| map1[row][column - 1] == END) {
				// ���뿪���޸��˵�����
				man = Toolkit.getDefaultToolkit().getImage("images\\b1.png");// ����
				column--;
			}
		}
	}

	private void moveRight() {
		// TODO Auto-generated method stub
		// ��һλp1ΪWALL
		if (map2[row][column + 1] == WALL)
			return;
		// ��һλp1Ϊ BOX
		if (map2[row][column + 1] == BOX || map2[row][column + 1] == BOXONEND) {
			if (map2[row][column + 2] == WALL)
				return;
			if (map2[row][column + 2] == BOX)
				return;
			if (map2[row][column + 2] == BOXONEND)
				return;
			// ��2λp2Ϊ END,GRASS������һ��
			if (map1[row][column + 2] == END
					|| map1[row][column + 2] == WhiteGRASS
					|| map1[row][column + 2] == BlackGRASS) {
				// ��2λp2Ϊ END
				if (map1[row][column + 2] == END) // ����һλp2Ϊ END
					map2[row][column + 2] = BOXONEND;
				if (map1[row][column + 2] == WhiteGRASS // ����һλp2ΪGRASS
						|| map1[row][column + 2] == BlackGRASS)
					map2[row][column + 2] = BOX;
				map2[row][column + 1] = -1;// ԭ�����ӱ��Ƶ�
				// ���뿪���޸��˵�����
				man = Toolkit.getDefaultToolkit().getImage("images\\d1.png");// ��������
				column++;
			}
		} else {
			// ��һλΪ GRASS,END,����������ô���
			if (map1[row][column + 1] == WhiteGRASS
					|| map1[row][column + 1] == BlackGRASS
					|| map1[row][column + 1] == END) {
				// ���뿪���޸��˵�����
				man = Toolkit.getDefaultToolkit().getImage("images\\d1.png");// ����
				column++;
			}
		}
	}

	private void moveUp() {
		// TODO Auto-generated method stub
		// ��һλp1ΪWALL
		if (map2[row - 1][column] == WALL)
			return;
		// ��һλp1Ϊ BOX,�뿼��P2
		if (map2[row - 1][column] == BOX || map2[row - 1][column] == BOXONEND) {
			if (map2[row - 2][column] == WALL)
				return;
			if (map2[row - 2][column] == BOX)
				return;
			if (map2[row - 2][column] == BOXONEND)
				return;
			// ��2λp2Ϊ END,GRASS������һ��
			if (map1[row - 2][column] == END
					|| map1[row - 2][column] == WhiteGRASS
					|| map1[row - 2][column] == BlackGRASS) {
				// ��2λp2Ϊ END
				if (map1[row - 2][column] == END) // ����һλp2Ϊ END
					map2[row - 2][column] = BOXONEND;
				if (map1[row - 2][column] == WhiteGRASS // ����һλp2ΪGRASS
						|| map1[row - 2][column] == BlackGRASS)
					map2[row - 2][column] = BOX;
				map2[row - 1][column] = -1;// ԭ�����ӱ��Ƶ�
				// ���뿪���޸��˵�����
				man = Toolkit.getDefaultToolkit().getImage("images\\c1.png");// ��������
				row--;
			}
		} else {
			// ��һλΪ GRASS,END,���뿼��P2��
			if (map1[row - 1][column] == WhiteGRASS
					|| map1[row - 1][column] == BlackGRASS
					|| map1[row - 1][column] == END) {
				// ���뿪���޸��˵�����
				man = Toolkit.getDefaultToolkit().getImage("images\\c1.png");// ����
				row--;
			}
		}
	}

	private void moveDown() {
		// TODO Auto-generated method stub
		// ��һλp1ΪWALL
		if (map2[row + 1][column] == WALL)
			return;
		// ��һλp1Ϊ BOX,�뿼��P2
		if (map2[row + 1][column] == BOX || map2[row + 1][column] == BOXONEND) {
			if (map2[row + 2][column] == WALL)
				return;
			if (map2[row + 2][column] == BOX)
				return;
			if (map2[row + 2][column] == BOXONEND)
				return;
			// ��2λp2Ϊ END,GRASS������һ��
			if (map1[row + 2][column] == END
					|| map1[row + 2][column] == WhiteGRASS
					|| map1[row + 2][column] == BlackGRASS) {
				// ��2λp2Ϊ END
				if (map1[row + 2][column] == END) // ����һλp2Ϊ END
					map2[row + 2][column] = BOXONEND;
				if (map1[row + 2][column] == WhiteGRASS // ����һλp2ΪGRASS
						|| map1[row + 2][column] == BlackGRASS)
					map2[row + 2][column] = BOX;
				map2[row + 1][column] = -1;// ԭ�����ӱ��Ƶ�
				// ���뿪���޸��˵�����
				man = Toolkit.getDefaultToolkit().getImage("images\\e1.png");// ��������
				row++;
			}
		} else {
			// ��һλΪ GRASS,END,���뿼��P2��
			if (map1[row + 1][column] == WhiteGRASS
					|| map1[row + 1][column] == BlackGRASS
					|| map1[row + 1][column] == END) {
				// ���뿪���޸��˵�����
				man = Toolkit.getDefaultToolkit().getImage("images\\e1.png");//����
				row++;
			}
		}
	}
	/**
	 * �жϵ�ǰ�Ƿ��Ѿ�ʤ��
	 * ֻ���鵱ǰ�����Ƿ񻹴���û�б��̵����Ӽ���
	 */
    public boolean isWin(){
    	for(int i=0; i<map2.length; i++ ){
    		for(int j=0; j<map2[i].length; j++){
    			if(map2[i][j] == BOX){//�в�����ɫ������
    				return false;
    			}
    		}
    	}
    	return true;
    }
}
