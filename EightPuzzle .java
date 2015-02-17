import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.*;
import java.awt.event.*;
import java.util.*;

/**
 *
 * @author Sunny
 */

// A node represents a state of a game
class node{
    int cor_x,cor_y,moves;
    int A[][];
    node(){
       A=new int[3][];
       for(int i=0;i<3;i++){
           A[i]=new int[3];
       }
    }
}


class Play implements ActionListener{

    final int MAX=181441;
    final int GRIDSIZE=3;

    int Parent[]=new int[MAX];
    int dirx[]=new int[4];
    int diry[]=new int[4];
    int Possible_States;

    node States[]=new node[MAX];
    HashMap HM=new HashMap();
    Scanner sc;
    JPanel panel,panel2;
    JFrame frame;
    JLabel countLabel;
    JButton button[];
    JButton button1,button2,button3;
    node R,Previous;
    int mmoves;
    int key;
    Play(){
        initialise(); //-------------------------variable ------------------------
        createGUI();
        do{
            R=read_given_state();
            key=getkey(R);
        }while(((Integer)HM.get(key)).intValue()==1);
        Previous = R;
        addButton(R);
        String sa=Integer.toString(mmoves);
        countLabel.setText("NO of moves:"+sa);
    }
	// ----------------------------------------------
    public void actionPerformed(ActionEvent e){

        if(e.getSource()==button[1]){
            String s=button[1].getLabel();

		if(button[2].getLabel().equals(" "))
		{
			button[2].setLabel(s); button[1].setLabel(" ");
			button[2].setBackground(Color.BLACK);
			button[1].setBackground(Color.darkGray);
			Previous=R;
			if(check_validity(R,dirx[3],diry[3])) // left
        		{
                   		 R=Get_Next_State(R,dirx[3],diry[3]);
                   		 mmoves++;
            		}
		}
		else if(button[4].getLabel().equals(" "))
		{
			button[4].setLabel(s); button[1].setLabel(" ");
			button[4].setBackground(Color.BLACK);
			button[1].setBackground(Color.darkGray);
			Previous=R;
			if(check_validity(R,dirx[0],diry[0])) // up
                	{
                    		R=Get_Next_State(R,dirx[0],diry[0]);
                    		mmoves++;
			}
		}else{
			JOptionPane.showMessageDialog(frame,"!!!Invalid Move!!!");
		}
	}
	if(e.getSource()==button[3])
	{
		String s=button[3].getLabel();
		if(button[2].getLabel().equals(" "))
		{
			Previous=R;
			button[2].setLabel(s); button[3].setLabel(" ");
			button[2].setBackground(Color.BLACK);
			button[3].setBackground(Color.darkGray);
			button[2].setBackground(Color.BLACK);
			button[3].setBackground(Color.darkGray);
			if(check_validity(R,dirx[1],diry[1]))
		        {
                		R=Get_Next_State(R,dirx[1],diry[1]);  // right
                    		mmoves++;
                	}else{
                    		System.out.println("Invalid Move");
                	}
		}else if(button[6].getLabel().equals(" ")){
			Previous=R;
			button[6].setLabel(s); button[3].setLabel(" ");
			button[6].setBackground(Color.BLACK);
			button[3].setBackground(Color.darkGray);							
        	        if(check_validity(R,dirx[0],diry[0])) // up
			{
                		 R=Get_Next_State(R,dirx[0],diry[0]);
          		         mmoves++;
                	}
		}else{
			JOptionPane.showMessageDialog(frame,"!!!Invalid Move!!!");
		}
	}

	if(e.getSource()==button[2])
	{
		String s=button[2].getLabel();
		if(button[1].getLabel().equals(" "))
		{
			Previous=R;
			button[1].setLabel(s); button[2].setLabel(" ");
			button[1].setBackground(Color.BLACK);
			button[2].setBackground(Color.darkGray);
			if(check_validity(R,dirx[1],diry[1])) // right
        	        {
                		R=Get_Next_State(R,dirx[1],diry[1]);
               			mmoves++;
                	}else{
                    		System.out.println("Invalid Move");
                	}
		}else if(button[3].getLabel().equals(" ")){
			Previous=R;
			button[3].setLabel(s); button[2].setLabel(" ");
			button[3].setBackground(Color.BLACK);
			button[2].setBackground(Color.darkGray);
			if(check_validity(R,dirx[3],diry[3]))// left
                	{
                    		R=Get_Next_State(R,dirx[3],diry[3]);
                    		mmoves++;
                	}
		}else if(button[5].getLabel().equals(" "))
		{
			Previous=R;
			button[5].setLabel(s); button[2].setLabel(" ");
			button[5].setBackground(Color.BLACK);
			button[2].setBackground(Color.darkGray);
			if(check_validity(R,dirx[0],diry[0])) // up
                	{
                    		R=Get_Next_State(R,dirx[0],diry[0]);
                    		mmoves++;
                	}
		}else{
			JOptionPane.showMessageDialog(frame,"!!!Invalid Move!!!");
		}
	}

	if(e.getSource()==button[4]){
		String s=button[4].getLabel();
		if(button[1].getLabel().equals(" "))
		{
			Previous=R;
			button[1].setLabel(s); button[4].setLabel(" ");
			button[1].setBackground(Color.BLACK);
			button[4].setBackground(Color.darkGray);
			if(check_validity(R,dirx[2],diry[2])) // down
	                {
        	        	R=Get_Next_State(R,dirx[2],diry[2]);
                    		mmoves++;
                	}else{
                    		System.out.println("Invalid Move");
                	}
		}else if(button[7].getLabel().equals(" "))
		{
			Previous=R;
			button[7].setLabel(s); button[4].setLabel(" ");
			button[7].setBackground(Color.BLACK);
			button[4].setBackground(Color.darkGray);							
			if(check_validity(R,dirx[0],diry[0])) // up
                	{
                    		R=Get_Next_State(R,dirx[0],diry[0]);
                    		mmoves++;
                	}
		}else if(button[5].getLabel().equals(" "))
		{
			Previous=R;
			button[5].setLabel(s); button[4].setLabel(" ");
			button[5].setBackground(Color.BLACK);
			button[4].setBackground(Color.darkGray);
			if(check_validity(R,dirx[3],diry[3]))// left
                	{
                    		R=Get_Next_State(R,dirx[3],diry[3]);
                    		mmoves++;
                	}
		}else{
			JOptionPane.showMessageDialog(frame,"!!!Invalid Move!!!");
		}
	}//end of if

	if(e.getSource()==button[5])
	{
		Previous=R;
		String s=button[5].getLabel();
		if(button[2].getLabel().equals(" "))
		{
			button[2].setLabel(s); button[5].setLabel(" ");
			button[2].setBackground(Color.BLACK);
			button[5].setBackground(Color.darkGray);
			if(check_validity(R,dirx[2],diry[2])) // down
	                {
        	        	R=Get_Next_State(R,dirx[2],diry[2]);
              			mmoves++;
                	}else{
                 		System.out.println("Invalid Move");
                	}
		}else if(button[4].getLabel().equals(" "))
		{
			Previous=R;
			button[4].setLabel(s); button[5].setLabel(" ");
			button[4].setBackground(Color.BLACK);
			button[5].setBackground(Color.darkGray);
			if(check_validity(R,dirx[1],diry[1])) // right
	                {
        	        	R=Get_Next_State(R,dirx[1],diry[1]);
                    		mmoves++;
                	}else{
                    		System.out.println("Invalid Move");
                	}
		}else if(button[6].getLabel().equals(" ")){
			Previous=R;
			button[6].setLabel(s); button[5].setLabel(" ");
			button[6].setBackground(Color.BLACK);
			button[5].setBackground(Color.darkGray);
			if(check_validity(R,dirx[3],diry[3]))// left
                	{
                    		R=Get_Next_State(R,dirx[3],diry[3]);
                    		mmoves++;
                	}
		}else if(button[8].getLabel().equals(" ")){
			Previous=R;
			button[8].setLabel(s); button[5].setLabel(" ");
			button[8].setBackground(Color.BLACK);
			button[5].setBackground(Color.darkGray);
			if(check_validity(R,dirx[0],diry[0])) // up
                	{
                    		R=Get_Next_State(R,dirx[0],diry[0]);
                    		mmoves++;
                	}
		}else{
			JOptionPane.showMessageDialog(frame,"!!!Invalid Move!!!");
		}
	}

	if(e.getSource()==button[6])
	{

		String s=button[6].getLabel();
		if(button[9].getLabel().equals(" "))
		{
			Previous=R;
			button[9].setLabel(s); button[6].setLabel(" ");
			button[9].setBackground(Color.BLACK);
			button[6].setBackground(Color.darkGray);
			if(check_validity(R,dirx[0],diry[0])) // up
	                {
        	            R=Get_Next_State(R,dirx[0],diry[0]);
                	    mmoves++;
                	}
		}
		else if(button[3].getLabel().equals(" "))
		{
			Previous=R;
			button[3].setLabel(s); button[6].setLabel(" ");
			button[3].setBackground(Color.BLACK);
			button[6].setBackground(Color.darkGray);
			if(check_validity(R,dirx[2],diry[2])) // down
	                {
        	            R=Get_Next_State(R,dirx[2],diry[2]);
                	    mmoves++;
                	}else{
                    		System.out.println("Invalid Move");
                	}
		}else if(button[5].getLabel().equals(" ")){
			Previous=R;
			button[5].setLabel(s); button[6].setLabel(" ");
			button[5].setBackground(Color.BLACK);
			button[6].setBackground(Color.darkGray);
			if(check_validity(R,dirx[1],diry[1])) // right
                	{
                    		R=Get_Next_State(R,dirx[1],diry[1]);
                    		mmoves++;
               		}else{
                    		System.out.println("Invalid Move");
                	}
		}else{
			JOptionPane.showMessageDialog(frame,"!!!Invalid Move!!!");
		}

	}
	if(e.getSource()==button[7])
	{
		String s=button[7].getLabel();
		if(button[4].getLabel().equals(" "))
		{
			Previous=R;
			button[4].setLabel(s); button[7].setLabel(" ");
			button[4].setBackground(Color.BLACK);
			button[7].setBackground(Color.darkGray);
			if(check_validity(R,dirx[2],diry[2])) // down
	                {
        	            R=Get_Next_State(R,dirx[2],diry[2]);
                	    mmoves++;
                	}else{
                    	    System.out.println("Invalid Move");
                	}
		}else if(button[8].getLabel().equals(" ")){
			button[8].setLabel(s); button[7].setLabel(" ");
			button[8].setBackground(Color.BLACK);
			button[7].setBackground(Color.darkGray);
			if(check_validity(R,dirx[3],diry[3]))// left
                	{
				Previous=R;
                	        R=Get_Next_State(R,dirx[3],diry[3]);
                    		mmoves++;
                	}
		}else{
			JOptionPane.showMessageDialog(frame,"!!!Invalid Move!!!");
		}

	}//end of if

	if(e.getSource()==button[8])
	{
		String s=button[8].getLabel();
		if(button[7].getLabel().equals(" "))
		{
			Previous=R;
			button[7].setLabel(s); button[8].setLabel(" ");
			button[7].setBackground(Color.BLACK);
			button[8].setBackground(Color.darkGray);
			if(check_validity(R,dirx[1],diry[1])) // right
                	{
                    		R=Get_Next_State(R,dirx[1],diry[1]);
                    		mmoves++;
                	}else{
                    		System.out.println("Invalid Move");
                	}
		}else if(button[9].getLabel().equals(" "))
		{
			Previous=R;
			button[9].setLabel(s); button[8].setLabel(" ");
			button[9].setBackground(Color.BLACK);
			button[8].setBackground(Color.darkGray);
			if(check_validity(R,dirx[3],diry[3]))// left
                	{
                    		R=Get_Next_State(R,dirx[3],diry[3]);
                    		mmoves++;
                	}
		}else if(button[5].getLabel().equals(" ")){
			Previous=R;
			button[5].setLabel(s); button[8].setLabel(" ");
			button[5].setBackground(Color.BLACK);
			button[8].setBackground(Color.darkGray);
			if(check_validity(R,dirx[2],diry[2])) // down
        	        {
                		R=Get_Next_State(R,dirx[2],diry[2]);
                    		mmoves++;
                	}else{
                    		System.out.println("Invalid Move");
                	}
		}else{
			JOptionPane.showMessageDialog(frame,"!!!Invalid Move!!!");
		}
	}

	if(e.getSource()==button[9])
	{
		String s=button[9].getLabel();
		if(button[6].getLabel().equals(" "))
		{
			Previous=R;
			button[6].setLabel(s);
			button[9].setLabel(" ");
			button[6].setBackground(Color.BLACK);
			button[9].setBackground(Color.darkGray);
			if(check_validity(R,dirx[2],diry[2])) // down
	                {
        	                R=Get_Next_State(R,dirx[2],diry[2]);
                	        mmoves++;
                	}else{
                    		System.out.println("Invalid Move");
                        }
		}else if(button[8].getLabel().equals(" ")){
			Previous=R;
			button[8].setLabel(s); button[9].setLabel(" ");
			button[8].setBackground(Color.BLACK);
			button[9].setBackground(Color.darkGray);
			if(check_validity(R,dirx[1],diry[1]))
                	{
                    		R=Get_Next_State(R,dirx[1],diry[1]);
                    		mmoves++;
                	}else{
                    		System.out.println("Invalid Move");
                	}
		}else{
			JOptionPane.showMessageDialog(frame,"!!!Invalid Move!!!");
		}
		if(button[1].getLabel().equals("1")&&button[2].getLabel().equals("2")&&button[3].getLabel().equals("3")&&button[4].getLabel().equals("4")&&button[5].getLabel().equals("5")&&button[6].getLabel().equals("6")&&button[7].getLabel().equals("7")&&button[8].getLabel().equals("8")&&button[9].getLabel().equals(" "))
		{
			JOptionPane.showMessageDialog(frame,"!!!you won!!!");
		}
	}
	if(e.getActionCommand().equals("Minimum Moves")){
		key=getkey(R);
		JOptionPane.showMessageDialog(frame,"Minimum Moves Required "+States[((Integer)HM.get(key)).intValue()].moves);
	}
	if(e.getActionCommand().equals("Get Next State")){
		key=getkey(R);
		int index = ((Integer)HM.get(key)).intValue();
		if(index!=1){
			int P=Parent[index];
			R = States[P];
			updateButton(R);
		}else{
			JOptionPane.showMessageDialog(frame,"GAME SOLVED");
		}
	}
	if(e.getActionCommand().equals("Undo")){
		if(mmoves==0){
			JOptionPane.showMessageDialog(frame,"MAKE ATLEAST 1 MOVE");
		}else if(getkey(Previous)!=getkey(R)){
			mmoves--;
			updateButton(Previous);
			R=Previous;
		}
	}
	String sa=Integer.toString(mmoves);
	countLabel.setText("NO of moves:"+sa);
	}
	// -----------------------------------------------

	void addButton(node R){

		for(int i=1;i<=9;i++)
		{
			int idx=i-1;
			String sa=Integer.toString(R.A[idx/3][idx%3]);
			if(R.A[idx/3][idx%3]==0){
				button[i]=new JButton(" ");
				button[i].setBackground(Color.darkGray);
			}
			else{
				button[i]=new JButton(sa);
				button[i].setFocusPainted(false);
				button[i].setBackground(Color.BLACK);
			}
			//button[i].setBorderPainted(false);
			button[i].setFont(new Font("Arial", Font.BOLD, 50));
			button[i].setForeground(Color.WHITE);
			button[i].addActionListener(this); /* Add actionListeners to buttons */
			panel.add(button[i]);
		}
	}

	// ------------------------------------------------
	void updateButton(node R){
		for(int i=1;i<=9;i++){
			int idx = i-1;
			String sa=Integer.toString(R.A[idx/3][idx%3]);
			if(R.A[idx/3][idx%3]==0){
				button[i].setLabel(" ");
				button[i].setBackground(Color.darkGray);
 			}else{
				button[i].setLabel(sa);
				button[i].setBackground(Color.BLACK);
			}
		}
	}
	//
	void createGUI(){

		frame=new JFrame("Hello Game");
		panel=new JPanel();
		countLabel=new JLabel();
		panel2=new JPanel();
		button1=new JButton("Minimum Moves");
		button2=new JButton("Undo");
		button3=new JButton("Get Next State");
		button=new JButton[10];
		GridLayout grid=new GridLayout(3,3);
		panel.setLayout(grid);
		button1.addActionListener(this);
		button2.addActionListener(this);
		button3.addActionListener(this);
		panel2.add(button1);
		panel2.add(button2);
		panel2.add(button3);
		frame.add(BorderLayout.NORTH,countLabel);
		frame.add(BorderLayout.SOUTH,panel2);
		frame.add(panel);
		frame.setBackground(Color.BLACK);
		frame.setSize(330,350);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
    // ---------------------------------------------------
	void initialise(){

        	sc=new Scanner(System.in);
       	 	for(int i=0;i<MAX;i++)
              		States[i]=new node();

 	        dirx[0]=-1;dirx[1]=0;dirx[2]=1;dirx[3]=0;
        	diry[0]=0;diry[1]=1;diry[2]=0;diry[3]=-1;

	        All_states();
	}
    // ---------------------------------------------------
    	boolean check_validity(node curr,int x,int y){
        	int nx=curr.cor_x+x;
        	int ny=curr.cor_y+y;
        	if((nx<GRIDSIZE)&&(ny<GRIDSIZE)&&(nx>=0)&&(ny>=0))
        		return true;
       	 	return false;
    	}

  
  // read state from user
   	 node read_given_state(){
	
	        node S=new node();
		int random_state[] = new int[9];
		for(int i=0;i<9;i++)
			random_state[i] = i;
        	while(true)
        	{
			shuffleArray(random_state);
            		for(int i=0;i<9;i++){
			
				S.A[i/3][i%3]=random_state[i];
				if(S.A[i/3][i%3] == 0){
					S.cor_x=i/3;
					S.cor_y=i%3;
				}
			
			}
          		 if(check_solvability(S))
				break;
       		}
     		return S;
    	}

    // check solvability of the state
	boolean check_solvability(node curr){
        	boolean inv[]=new boolean[9];
        	int Cinv=0;
        	for(int i=0;i<GRIDSIZE;i++){
         		for(int j=0;j<GRIDSIZE;j++){
             			inv[curr.A[i][j]]=true;
             				for(int k=curr.A[i][j];k>0;k--){
               					  if(inv[k]==false)
                     					Cinv++;
             				}
          		}
        	}
    	 	return Cinv%2==1?false:true;
    	}


	void All_states(){

	        node S=Get_Solved_State();
        	Queue Q = new LinkedList<node>();
        	Q.add(S);

	        int key=getkey(S);
        	HM.put(key,1);
        	Parent[1]=-1;
        	int pc=1,sc=0;

	        while(!Q.isEmpty()){

	            S=(node) Q.remove();
        	    sc++;
            	    for(int i=0;i<GRIDSIZE;i++){
                	for(int j=0;j<GRIDSIZE;j++){
                    		States[sc].A[i][j]=S.A[i][j];
                	}
            	    }
            	    States[sc].cor_x=S.cor_x;
            	    States[sc].cor_y=S.cor_y;
                    States[sc].moves=S.moves;

	            for(int i=0;i<4;i++){
        	        int nx=S.cor_x+dirx[i];
	                int ny=S.cor_y+diry[i];

	                if((nx<GRIDSIZE)&&(ny<GRIDSIZE)&&(nx>=0)&&(ny>=0))
        	        {
                	    node NS=Get_Next_State(S,dirx[i],diry[i]);
                    	    int NSK=getkey(NS);
                    	    if(HM.containsKey(NSK)==false)
                    	    {
                     		Q.add(NS);
                     		pc++;
                     		HM.put(NSK,pc); // marking it in visited array
                    		Parent[pc]=sc; // setting parent pointer
                    	    }
             		}
         	   }
        	}
     		Possible_States=sc;
       }

	int[] shuffleArray(int[] array)
    	{
    		 int index;
   	   	 Random random = new Random();
    	  	 for(int i = array.length - 1; i > 0; i--)
    	 	 {
        	 	index = random.nextInt(i + 1);
       		 	if (index != i)
      		 	{
          		  array[index] ^= array[i];
           		  array[i] ^= array[index];
            		  array[index] ^= array[i];
        		 }
    	 	}
		return array;
    	}

        // print state
	void Print_States(node T){

	        for(int i=0;i<GRIDSIZE;i++){
        	    for(int j=0;j<GRIDSIZE;j++){
        	       	System.out.print(T.A[i][j]+" ");
            		}
            		System.out.println();
       	 	}
       }


    // getkey my hash function

    int getkey(node curr){
        int key=0;
        for(int i=0;i<GRIDSIZE;i++){
         for(int j=0;j<GRIDSIZE;j++){
           key=key*10+(curr.A[i][j]);
         }
        }
        return key;
    }

    // Get_Solved_State
    node Get_Solved_State(){

        node S=new node();
        int c=1;
        for(int i=0;i<GRIDSIZE;i++){
            for(int j=0;j<GRIDSIZE;j++){
               S.A[i][j]=c;
               c++;
            }
        }
        S.A[GRIDSIZE-1][GRIDSIZE-1]=0;
        S.cor_x=GRIDSIZE-1;
        S.cor_y=GRIDSIZE-1;
        return S;
    }

    // Get_next_state from current state
    node Get_Next_State(node curr,int x,int y){
        node next=new node();
        for(int i=0;i<GRIDSIZE;i++){
            for(int j=0;j<GRIDSIZE;j++){
                next.A[i][j]=curr.A[i][j];
            }
        }
        int nx=curr.cor_x+x;
        int ny=curr.cor_y+y;
        x=curr.cor_x;
        y=curr.cor_y;
        int temp=next.A[nx][ny];
        next.A[nx][ny]=next.A[x][y];
        next.A[x][y]=temp;
        next.cor_x=nx;
        next.cor_y=ny;
        next.moves=curr.moves+1;
        return next;
    }
}

class EightPuzzle{
    public static void main(String[] args) {
       Play A=new Play();
    }
}
