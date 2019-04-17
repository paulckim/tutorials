package com.snippets.datastructures.trees.bst.unbalanced;

import com.snippets.datastructures.trees.bst.AbstractTree;
import com.snippets.datastructures.trees.bst.TreeNode;

public class NaiveBinaryTree<T extends Comparable<? super T>> extends AbstractTree<T> {

    private TreeNode<T> root;

    @Override
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

    @Override
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

    private TreeNode<T> reorder(TreeNode<T> left, TreeNode<T> right) {
        TreeNode<T> greatestLeftNode = getGreatestNode(left);
        if(null == greatestLeftNode)
            return right;
        greatestLeftNode.setRight(right);
        return left;
    }

    private TreeNode<T> getGreatestNode(TreeNode<T> subtree) {
        TreeNode<T> currNode = subtree;
        while(null != currNode && null != currNode.getRight())
            currNode = currNode.getRight();
        return currNode;
    }
}
