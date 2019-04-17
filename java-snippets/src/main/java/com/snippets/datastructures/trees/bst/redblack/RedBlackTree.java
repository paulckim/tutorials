package com.snippets.datastructures.trees.bst.redblack;

import com.snippets.datastructures.trees.bst.AbstractTree;

public class RedBlackTree<T extends Comparable<? super T>> extends AbstractTree<T> {

    @Override
    public void add(T value) {
        if(null == this.root) {
            this.root = new RBTreeNode(value, Color.BLACK);
            ++size;
            return;
        }
        RBTreeNode<T> parent = (RBTreeNode) this.root;
        RBTreeNode<T> grandparent = parent;
        balancedAdd(parent, grandparent, value);
    }

    private void balancedAdd(RBTreeNode<T> parent, RBTreeNode<T> grandparent, T value) {
        // value already exists within Tree
        if(value.compareTo(parent.getValue()) == 0) return;
        // attempt to keep searching to insert
        boolean isLeft = value.compareTo(parent.getValue()) < 0;
        RBTreeNode<T> nextNode = isLeft ? parent.getLeft() : parent.getRight();
        if(null == nextNode) {
            if(isLeft) parent.setLeft(new RBTreeNode<>(value));
            else parent.setRight(new RBTreeNode<>(value));
            ++size;
        }
        else balancedAdd(nextNode, parent, value);
        //restructure each grandparent
        recolor(grandparent);
        balance();
    }

    private void recolor(RBTreeNode<T> parent) {
        RBTreeNode<T> left = parent.getLeft();
        RBTreeNode<T> right = parent.getRight();
        if(null == left || null == right) return;
        boolean needsColoring = (left.getColor().equals(Color.RED))
                && (right.getColor().equals(Color.RED));
        if(!needsColoring) return;
        parent.setColor(Color.RED);
        left.setColor(Color.BLACK);
        right.setColor(Color.BLACK);
    }

    private void balance() {
        // TODO: implement later
    }

    @Override
    public void remove(T value) {
        // TODO: implement later
    }

}
