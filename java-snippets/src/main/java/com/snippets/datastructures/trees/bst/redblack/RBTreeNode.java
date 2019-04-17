package com.snippets.datastructures.trees.bst.redblack;

import com.snippets.datastructures.trees.bst.TreeNode;

public class RBTreeNode<T> extends TreeNode<T> {

    private Color color;

    public RBTreeNode(T value) {
        super(value);
        this.color = Color.RED;
    }

    public RBTreeNode(T value, Color color) {
        super(value);
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public RBTreeNode<T> getLeft() {
        return (RBTreeNode) left;
    }

    @Override
    public RBTreeNode<T> getRight() {
        return (RBTreeNode) right;
    }

}
