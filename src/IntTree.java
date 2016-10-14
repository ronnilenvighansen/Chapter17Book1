public class IntTree {
    private IntTreeNode overallRoot;

    public IntTree(int max) {
        if (max <= 0) {
            throw new IllegalArgumentException("max: " + max);
        }
        overallRoot = buildTree(1, max);
    }

    private IntTreeNode buildTree(int n, int max) {
        if (n > max) {
            return null;
        } else {
            return new IntTreeNode(n, buildTree(2 * n, max),
                    buildTree(2 * n + 1, max));
        }
    }

    public void printPreorder() {
        System.out.print("preorder:");
        printPreorder(overallRoot);
        System.out.println();
    }

    private void printPreorder(IntTreeNode root) {
        if (root != null) {
            System.out.print(" " + root.data);
            printPreorder(root.left);
            printPreorder(root.right);
        }
    }

    public void printInorder() {
        System.out.print("inorder:");
        printInorder(overallRoot);
        System.out.println();
    }

    private void printInorder(IntTreeNode root) {
        if (root != null) {
            printInorder(root.left);
            System.out.print(" " + root.data);
            printInorder(root.right);
        }
    }

    public void printPostorder() {
        System.out.print("postorder:");
        printPostorder(overallRoot);
        System.out.println();
    }

    private void printPostorder(IntTreeNode root) {
        if (root != null) {
            printPostorder(root.left);
            printPostorder(root.right);
            System.out.print(" " + root.data);
        }
    }

    public void printSideways() {
        printSideways(overallRoot, 0);
    }

    private void printSideways(IntTreeNode root, int level) {
        if (root != null) {
            printSideways(root.right, level + 1);
            for (int i = 0; i < level; i++) {
                System.out.print("    ");
            }
            System.out.println(root.data);
            printSideways(root.left, level + 1);
        }
    }

    public IntTreeNode getRoot() {
        return overallRoot;
    }

    public int countLeftNodes() {
        return countLeftNodes(getRoot());
    }

    private int countLeftNodes(IntTreeNode root) {
        if (root == null) {
            return 0;
        }

        int countLeft = 0;
        if (root.left != null) {
            countLeft += 1 + countLeftNodes(root.left);
        }
        if (root.right != null) {
            countLeft += countLeftNodes(root.right);
        }
        return countLeft;
    }

    public int countEmpty() {
        return countEmpty(getRoot());
    }

    private int countEmpty(IntTreeNode root) {
        int count = 0;
        if (root == null) {
            return count;
        }
        if (root.left == null) {
            count += 1 + countEmpty(root.left);
        } else {
            count += countEmpty(root.left);
        }
        if (root.right == null) {
            count += 1 + countEmpty(root.right);
        } else {
            count += countEmpty(root.right);
        }
        return count;
    }

    public int depthSum() {
        int depth = 1;
        return depthSum(getRoot(), depth);
    }

    private int depthSum(IntTreeNode root, int depth)
    {
        int sum = 0;
        if (root != null)
        {
            sum += depth * root.data;
        }
        if (root.left != null)
        {
            sum += depthSum(root.left, depth+1);
        }
        if (root.right != null)
        {
            sum += depthSum(root.right, depth+1);
        }
        return sum;
    }

    public void printLevel(int index)
    {
        if(index < 1)
        {
            throw new IllegalArgumentException();
        }
        else
        {
            int count = 0;
            printLevel(overallRoot, index, count);
        }
    }

    private void printLevel(IntTreeNode root, int index, int count)
    {
        count++;
        if (count == index)
        {
            System.out.println(root.data);
        }
        else
        {
            if(root.left != null)
            {
                printLevel(root.left, index, count);
            }
            if(root.right != null)
            {
                printLevel(root.right, index, count);
            }
        }
    }

    public void printLeaves()
    {
        printLeaves(getRoot());
    }

    private void printLeaves(IntTreeNode root)
    {
        if (root.left == null && root.right == null)
        {
            System.out.println(root.data);
        }
        else
        {
            if(root.left != null)
            {
                printLeaves(root.left);
            }
            if(root.right != null)
            {
                printLeaves(root.right);
            }
        }
    }


    public int countEvenBranches()
    {
        return countEvenBranches(getRoot());
    }

    private int countEvenBranches(IntTreeNode root)
    {
        int count = 0;
        if(root == null)
        {
            return 0;
        }
        else if(root.left == null && root.right == null)
        {
            return 0;
        }
        else
        {
            if(root.data % 2 == 0)
            {
                count += 1 + countEvenBranches(root.left) + countEvenBranches(root.right);
            }
            count += countEvenBranches(root.left) + countEvenBranches(root.right);
        }
        return count;
    }

    public boolean isFull()
    {
        return isFull(getRoot());
    }

    private boolean isFull(IntTreeNode root)
    {
        if(root == null)
        {
            return true;
        }
        if(root.left == null && root.right == null)
        {
            return true;
        }
        if(root.left != null && root.right != null)
        {
            return isFull(root.left) && isFull(root.right);
        }
        return false;
    }
}