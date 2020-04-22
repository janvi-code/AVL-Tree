package tree2;

class Node     //create node structure
{
   int data;
   Node left;
   Node right;
   int height;
   Node(int val)
   {
	   data=val;
	   left=right=null;
	   height=1;
   }
}

class myAvl               
{
	   int height(Node N)  
	    {  
	        if (N == null)  
	            return 0;  
	        return N.height;  
	    }  
	public int getBalanceFactor(Node y)         //return balance factor it should be {-1,0,1} for no rotation
	{
		if(y==null)
			return 0;
		return (height(y.left))-(height(y.right));
	}
   public Node insert(Node root,int val)          //insert an element
   {
	   if(root==null)
		    return new Node(val);
	   if(val<root.data)
		   root.left=insert(root.left,val);
	   else if(val>root.data)
		   root.right=insert(root.right,val);
	   else
		   return root;
	   root.height=1+Math.max(height(root.left),height(root.right));
	   
	   int balance=getBalanceFactor(root);
	   
	   if(balance>1 && root.left.data>val)
		   return rotateRight(root);        //RR rotation
	   
	   if(balance<-1 && root.right.data<val)
	       return rotateLeft(root);         //RL rotation
	   
	   if(balance>1 && root.left.data<val)
	   {
		   root.left=rotateLeft(root.left);   //LR rotation
		   return rotateRight(root);
	   }
	   
	   if(balance<-1 && root.right.data>val)
	   {
		   root.right=rotateRight(root.right);   //RL rotation
		   return rotateLeft(root);
	   }
	   
	   return root;
   }
public Node rotateRight(Node root)     //RR Rotation
{
   Node x=root.left;
   Node k=x.right;
   x.right=root;
   root.left=k;
   
   root.height=1+Math.max(height(root.left),height(root.right));
   x.height=1+Math.max(height(x.left),height(x.right));
	 
   
   return x;
}
public Node rotateLeft(Node root)    //RL rotation
{
	Node x=root.right;
	Node k=x.left;
	
	x.left=root;
	root.right=k;
	
	x.height=1+Math.max(height(x.left),height(x.right));
	root.height=1+Math.max(height(root.left),height(root.right));
    
	   
	return x;
}
public void inOrder(Node root)              //print elements of tree in inOrder fashion
{
    if(root==null) 
    	 return;
	inOrder(root.left);
	System.out.println(root.data);
    inOrder(root.right);	
}

public void preOrder(Node root)         //prints elements of tree in preOrder fashion
{
	if(root==null)
		 return;
    System.out.print(root.data+" ");
    preOrder(root.left);
    preOrder(root.right);
}

public void postOrder(Node root)        //prints elements of tree on postOrder fashion
{
	if(root==null)
		  return;
	postOrder(root.left);
	postOrder(root.right);
	System.out.println(root.data);
}
public int min(Node root)                //returns minimum value from tree
{
	Node t=root;
	while(t.left!=null)
	{
		t=t.left;
	}
	return t.data;
}
public Node delete(Node root,int val)
{
	if(root==null)  return null;
	if(val<root.data)
		root.left=delete(root.left,val);
	else if(val>root.data)
		root.right=delete(root.right,val);
	else
	{
		if(root.left==null && root.right==null)              //leaf node that is no child
		{
			return null;
		}
		else if((root.left==null && root.right!=null)||(root.left!=null && root.right==null))   //one child
		{
			if(root.left!=null)
				return root.left;
			else
				return root.right;
		}
		else                               //two child
		{
			root.data=min(root.right);
			root.right=delete(root.right,root.data);
		}
	}
	root.height=1+Math.max(height(root.left),height(root.right));
	   
	   int balance=getBalanceFactor(root);
	   
	   if(balance>1 && root.left.data>val)
		   return rotateRight(root);        //RR rotation
	   
	   if(balance<-1 && root.right.data<val)
	       return rotateLeft(root);         //RL rotation
	   
	   if(balance>1 && root.left.data<val)
	   {
		   root.left=rotateLeft(root.left);   //LR rotation
		   return rotateRight(root);
	   }
	   
	   if(balance<-1 && root.right.data>val)
	   {
		   root.right=rotateRight(root.right);   //RL rotation
		   return rotateLeft(root);
	   }
	return root;
}

}

