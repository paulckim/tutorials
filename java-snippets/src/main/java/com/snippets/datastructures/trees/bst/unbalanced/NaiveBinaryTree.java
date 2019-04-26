package com.snippets.datastructures.trees.bst.unbalanced;

import com.snippets.datastructures.trees.bst.AbstractTree;
import com.snippets.datastructures.trees.bst.TreeNode;

public class NaiveBinaryTree<T extends Comparable<? super T>> extends AbstractTree<T> {

    @Override
    public void add(T value) {
        TreeNode<T> currNode = root;
        if(null == currNode) {
            root = new TreeNode<>(value);
            ++size;
            return;
        }

        while(!isEqual(value, currNode.getValue())) {
            if(isLess(value, currNode.getValue())) {
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
        TreeNode<T> parentNode = root;
        if(null == parentNode)
            return;
        if(isEqual(root.getValue(), value)) {
            root = reorder(root);
            parentNode.setLeft(null);
            parentNode.setRight(null);
            --size;
        }
        while(true) {
            TreeNode<T> currNode = isLess(value, parentNode.getValue())
                    ? parentNode.getLeft() : parentNode.getRight();
            if(null == currNode)
                return;
            if(!isEqual(value, currNode.getValue())) {
                parentNode = currNode;
                continue;
            }
            --size;
            TreeNode<T> subtree = reorder(currNode);
            if(isLess(currNode.getValue(), parentNode.getValue()))
                parentNode.setLeft(subtree);
            else
                parentNode.setRight(subtree);
            currNode.setLeft(null);
            currNode.setRight(null);
            return;
        }
    }

    private TreeNode<T> reorder(TreeNode<T> deleteNode) {
        TreeNode<T> left = deleteNode.getLeft();
        TreeNode<T> right = deleteNode.getRight();
        /**
         * If the node to be deleted has, at most, one child.
         */
        if(null == right) return left;
        if(null == left) return right;
        /**
         * If the node to be deleted has both children:
         * 1) Find the smallest node of the right subtree.
         * 2) Detach the smallest node from the right subtree.
         * 3) return the minNode
         */
        TreeNode<T> minNode = right, parentNode = minNode;
        while(null != minNode.getLeft()) {
            parentNode = minNode;
            minNode = minNode.getLeft();
        }
        if(!isEqual(parentNode.getValue(), minNode.getValue())){
            if(isLess(minNode.getValue(), parentNode.getValue()))
                parentNode.setLeft(null);
            else
                parentNode.setRight(null);
        }
        return minNode;
    }

    private boolean isLess(T left, T right) {
        return 0 > left.compareTo(right);
    }

    private boolean isEqual(T left, T right) {
        return 0 == left.compareTo(right);
    }
}
