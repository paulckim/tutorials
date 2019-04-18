package com.snippets.datastructures.trees.bst.redblack;

import com.snippets.datastructures.trees.bst.AbstractTree;

public class RecursiveRedBlackTree<T extends Comparable<? super T>> extends AbstractTree<T> {

    @Override
    public void add(T value) {
        // EDGE-CASE 1:
        RBTreeNode<T> grandparent = (RBTreeNode) this.root;
        if(null == grandparent) {
            this.root = new RBTreeNode(value, Color.BLACK);
            ++size;
            return;
        }
        // EDGE-CASE 2:
        if(areValueEqual(value, grandparent.getValue())) return;
        // EDGE-CASE 3:
        boolean isLeft = isValueLess(value, grandparent.getValue());
        RBTreeNode<T> parent = isLeft ? grandparent.getLeft() : grandparent.getRight();
        if(null == parent) {
            if(isLeft) grandparent.setLeft(new RBTreeNode<>(value));
            else grandparent.setRight(new RBTreeNode<>(value));
            ++size;
            return;
        }
        // BASE-CASE:
        this.root = balancedAdd(grandparent, value);
        Color rootColor = ((RBTreeNode<T>) this.root).getColor();
        if(rootColor.equals(Color.RED))
            ((RBTreeNode<T>) this.root).setColor(Color.BLACK);
    }

    @Override
    public void remove(T value) {
        // TODO: implement later
    }

    private RBTreeNode<T> balancedAdd(RBTreeNode<T> grandparent, T value) {
        // Get the parent node of current subtree scope
        RBTreeNode<T> parent = isValueLess(value, grandparent.getValue())
                ? grandparent.getLeft() : grandparent.getRight();
        // If an existing descendant node matches <value>:
        if(areValueEqual(value, parent.getValue())) return grandparent;
        // Check if child node exists:
        RBTreeNode<T> child = isNodeLeft(parent, grandparent)
                ? parent.getLeft() : parent.getRight();
        if(null == child)
            return addNewNode(parent, grandparent, value);
        // subtreeRoot is the new parent:
        RBTreeNode<T> subtreeRoot = balancedAdd(parent, value);
        if(isNodeLeft(subtreeRoot, grandparent))
            grandparent.setLeft(subtreeRoot);
        else
            grandparent.setRight(subtreeRoot);

        RBTreeNode<T> subtreeChild;
        if(null != subtreeRoot.getLeft() && subtreeRoot.getLeft().getColor().equals(Color.RED))
            subtreeChild = subtreeRoot.getLeft();
        else if(null != subtreeRoot.getRight() && subtreeRoot.getRight().getColor().equals(Color.RED))
            subtreeChild = subtreeRoot.getRight();
        else
            return grandparent;
        RBTreeNode<T> balancedSubtree = balance(subtreeChild, subtreeRoot, grandparent);
        if(null == balancedSubtree) return grandparent;
        return balancedSubtree;
    }

    private RBTreeNode<T> addNewNode(RBTreeNode<T> parent, RBTreeNode<T> grandparent, T value) {
        RBTreeNode<T> child = new RBTreeNode<>(value);
        if(isNodeLeft(parent, grandparent)) parent.setLeft(child);
        else parent.setRight(child);
        recolor(grandparent);
        ++size;
        RBTreeNode<T> subtreeRoot = balance(child, parent, grandparent);
        if(null == subtreeRoot) return grandparent;
        return subtreeRoot;
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

    private RBTreeNode<T> balance(RBTreeNode<T> child,
                                  RBTreeNode<T> parent,
                                  RBTreeNode<T> grandparent) {
        if(child.getColor().equals(Color.BLACK)
                || parent.getColor().equals(Color.BLACK))
            return null;
        RBTreeNode<T> subtreeRoot;
        if(isNodeLeft(parent, grandparent)) {
            if(isNodeLeft(child, parent)) {
                subtreeRoot = parent;
                grandparent.setLeft(parent.getRight());
                parent.setRight(grandparent);
            }
            else {
                subtreeRoot = child;
                parent.setRight(child.getLeft());
                grandparent.setLeft(child.getRight());
                child.setLeft(parent);
                child.setRight(grandparent);
            }
        }
        else {
            if(isNodeLeft(child, parent)) {
                subtreeRoot = child;
                grandparent.setRight(child.getLeft());
                parent.setLeft(child.getRight());
                child.setLeft(grandparent);
                child.setRight(parent);
            }
            else {
                subtreeRoot = parent;
                grandparent.setRight(parent.getLeft());
                parent.setLeft(grandparent);
            }
        }
        subtreeRoot.setColor(Color.BLACK);
        if(null != subtreeRoot.getLeft())
            subtreeRoot.getLeft().setColor(Color.RED);
        if(null != subtreeRoot.getRight())
            subtreeRoot.getRight().setColor(Color.RED);
        return subtreeRoot;
    }

    private boolean areValueEqual(T left, T right) {
        return left.compareTo(right) == 0;
    }

    private boolean isValueLess(T left, T right) {
        return left.compareTo(right) < 0;
    }

    private boolean isNodeLeft(RBTreeNode<T> child, RBTreeNode<T> parent) {
        return child.getValue().compareTo(parent.getValue()) < 0;
    }

}
