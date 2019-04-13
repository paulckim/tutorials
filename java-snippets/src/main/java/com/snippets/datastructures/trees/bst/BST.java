package com.snippets.datastructures.trees.bst;

public class BST<T extends Comparable<? super T>> {
    private int size;
    private TreeNode<T> root;

    public void add(T value) {
        TreeNode<T> currNode = root;
        if(null == currNode) {
            root = new TreeNode<>(value);
            ++size;
            return;
        }

        while(value.compareTo(currNode.getValue()) != 0) {
            if(value.compareTo(currNode.getValue()) < 0) {
                if(null != currNode.getLeft()) {
                    currNode = currNode.getLeft();
                    continue;
                }
                currNode.setLeft(new TreeNode<>(value));
                ++size;
            }
            else {
                if(null != currNode.getRight()) {
                    currNode = currNode.getRight();
                    continue;
                }
                currNode.setRight(new TreeNode<>(value));
                ++size;
            }
        }
    }

    public void remove(T value) {
        TreeNode<T> currNode = root;
        if(null == currNode)
            return;
        if(root.getValue().compareTo(value) == 0) {
            root = reorder(root.getLeft(), root.getRight());
            --size;
        }
        while(true) {
            TreeNode<T> nextNode = (value.compareTo(currNode.getValue()) < 0)
                    ? currNode.getLeft() : currNode.getRight();
            if(null == nextNode)
                return;
            if(0 != value.compareTo(nextNode.getValue())) {
                currNode = nextNode;
                continue;
            }
            --size;
            TreeNode<T> left = nextNode.getLeft();
            TreeNode<T> right = nextNode.getRight();
            TreeNode<T> subtree = reorder(left, right);
            if(nextNode.getValue().compareTo(currNode.getValue()) < 0)
                currNode.setLeft(subtree);
            else
                currNode.setRight(subtree);
            return;
        }
    }

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

    public TreeNode<T> reorder(TreeNode<T> left, TreeNode<T> right) {
        /**
         * TODO: logic involving getGreatestNode
         */
        TreeNode<T> greatestLeftNode = getGreatestNode(left);
        if(null == greatestLeftNode)
            return right;
        greatestLeftNode.setRight(right);
        return left;
    }

    public TreeNode<T> getGreatestNode(TreeNode<T> subtree) {
        TreeNode<T> currNode = subtree;
        while(null != currNode && null != currNode.getRight())
            currNode = currNode.getRight();
        return currNode;
    }

    public int size() {
        return size;
    }
}
