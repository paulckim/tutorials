package com.snippets.datastructures.trees.bst;

public abstract class AbstractTree<T extends Comparable<? super T>> implements ITree<T> {

    protected TreeNode<T> root;
    protected int size;

    @Override
    public boolean contains(T value) {
        TreeNode<T> currNode = root;
        if(null == currNode)
            return false;
        while(value.compareTo(currNode.getValue()) != 0) {
            if(value.compareTo(currNode.getValue()) < 0) {
                if(null == currNode.getLeft())
                    return false;
                currNode = currNode.getLeft();
            }
            else {
                if(null == currNode.getRight())
                    return false;
                currNode = currNode.getRight();
            }
        }
        return true;
    }

    @Override
    public int height() {
        return getHeight(root);
    }

    private int getHeight(TreeNode<T> parent) {
        if(null == parent) return 0;
        int maxLeftHeight = getHeight(parent.getLeft()) + 1;
        int maxRightHeight = getHeight(parent.getRight()) + 1;
        return maxLeftHeight > maxRightHeight ? maxLeftHeight : maxRightHeight;
    }

    @Override
    public int size() {
        return this.size;
    }

}
