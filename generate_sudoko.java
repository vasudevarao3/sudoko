//This code will Generate sudoko according to the required difficulty level

import java.util.*;
public class generate_sudoko{
    static void print(int n,int[][] board)
    {
        for(int i=0;i<n;i++)
        {
            for(int j=0;j<n;j++)
            {
                    System.out.print(board[i][j]+" ");
            }
            System.out.print("\n");
        }
    }
    static boolean check_row(int row,int coloumn,int[][] board,int value)
    {

        for(int c=0;c<9;c++)
        {
            if(board[row-1][c]==value && c!=coloumn-1)
            {
                return false;
            }
        }
        return true;   
    }

    static boolean check_coloumn(int row,int coloumn,int[][] board,int value)
    {

        for(int r=0;r<9;r++)
        {
            if(board[r][coloumn-1]==value && r!=row-1)
            {
                return false;
            }
        }
        return true;   
    }

    static boolean check_submatrix(int row,int coloumn,int[][] board,int value)
    {
        int r=0,c=0;
        if(row>=1 && row<=3)
            r=0;
        else if(row>=4 && row<=6)
            r=3;
        else if(row>=7 && row<=9)
            r=6;
        if(coloumn>=1 && coloumn<=3)
            c=0;
        else if(coloumn>=4 && coloumn<=6)
            c=3;
        else if(coloumn>=7 && coloumn<=9)
            c=6;
        int rflag=0;
        for(int i=r; i%3!=0 || rflag==0;i++)
        {
            int cflag=0;
            for(int j=c;j%3!=0 || cflag==0;j++)
            {
                if((board[i][j]==value)  && (i!=row-1 && j!=coloumn-1))
                    return false;
                cflag=1;
            }
            rflag=1;
        }
        return true;
    }
    public static void main(String[] args)
    {
        Random random=new Random();
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter the no.of places you want to fill in genrated sudoko");        //if n is too low or too high,it is easy to solve
        int n=sc.nextInt();                                                                      //better give n value in between 10 to 20 for difficult level
        int[][] board=new int[9][9];
        int i=0;
        if(n<=81 && n>=0)
        {
            while(i<n)
            {
                int row=random.nextInt(8)+1;
                int coloumn=random.nextInt(8)+1;
                int value=random.nextInt(9)+1;
                if(check_submatrix(row,coloumn,board,value) && check_row(row,coloumn,board,value) && check_coloumn(row,coloumn,board,value))
                {
                    board[row-1][coloumn-1]=value;
                    i++;
                }
            }
            print(9,board);
        }
        else
            System.out.println("Enter the valid Number");
    }   
}
