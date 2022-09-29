import java.util.Scanner;
public class sudocode
{
    static boolean solve(int n,int row,int coloumn,int[][] board)
    {
        if(row==n-1 && coloumn==n)
            return true;
        if(coloumn>n-1)
        {
            row++;
            coloumn=0;
        }
        if(board[row][coloumn]>0)
        {
            return solve(n,row,coloumn+1,board);
        }
        for(int i=1;i<n+1;i++)
        {
            if(check_submatrix(row+1,coloumn+1,board,i) && check_row(row+1,coloumn+1,board,i) && check_coloumn(row+1,coloumn+1,board,i))
            {
                board[row][coloumn]=i;
                if(solve(n,row,coloumn+1,board))     // Checking for next possibility with nex  column
                    return true;
            }
            board[row][coloumn]=0;                  
        }
        return false;
    }
    static void print(int n,int[][] board)         //print board
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
    static void reading_values(int n,int[][] board)
    {
        char flag;
        try (Scanner sc = new Scanner(System.in)) 
        {
            do{
               
                System.out.println("Enter the row values:");
                int row=sc.nextInt();
                System.out.println("Enter the coloumn values:");
                int coloumn=sc.nextInt();
                if(row<=n && coloumn<=n && row>0 && coloumn>0)
                {
                    System.out.println("Enter the value at "+row+" row and "+coloumn+" coloumn:");
                    int value=sc.nextInt();//char value=in.next().charAt(0);
                    if(value<=n && value>0)
                    {
                        if(check_submatrix(row,coloumn,board,value))
                            if(check_row(row,coloumn,board,value))
                                if(check_coloumn(row,coloumn,board,value))
                                    board[row-1][coloumn-1]=value;
                                else
                                    System.out.println(value +" is already exist in coloumn"); 
                            else
                                System.out.println(value +" is already exist in row"); 
                        else
                            System.out.println(value +" is already exist in the 3*3 matix");
                    }
                    else
                    {
                        System.out.println(value +" should not exceed "+n);
                    }
                }
                else
                {
                    System.out.println("you entered rows or coloums are beyond the size");
                }
                print(n,board);
                System.out.println("Do you want to Enter another Number(yes/no):");
                flag=sc.next().charAt(0);
            }while(flag == 'y');
        }
        print(n,board);
    }
    public static void main(String[]  a){
        //Scanner in=new Scanner(System.in);
         System.out.print("Enter the size:9\n");
        int n=9;
        int[][] board=new int[n][n];
            {0,1,0,0,0,9,0,0,0}}; 
       for(int i=0;i<n;i++)
            for(int j=0;j<n;j++)
                board[i][j]=0;
        reading_values(n,board);
        if(solve(9,0,0,board))   
        {
            System.out.println("Solved sudoco:\n");
            print(n,board);
        } 
        else
        {
            System.out.println("Sudoko not possible");
        }
    }
}

          /*{{0,0,0,6,0,0,0,4,0},
            {6,8,0,0,0,4,1,3,0},
            {4,0,0,0,1,0,8,0,0},
            {0,0,0,0,0,0,0,5,8},
            {9,4,8,5,0,2,7,6,1},
            {7,5,0,0,0,0,0,0,0},
            {0,0,2,0,0,0,0,0,7},
            {0,7,9,1,0,0,0,8,5},*/
