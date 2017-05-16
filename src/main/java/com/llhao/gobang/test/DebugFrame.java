package com.llhao.gobang.test;

import com.llhao.gobang.ai.ResultNode;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.BorderLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Everthing-- on 2017/5/8.
 */
public class DebugFrame extends JFrame{
    private ResultNode[] data;
    private ResultNode root;
    JTextArea area;
    public DebugFrame(ResultNode[] listData) throws HeadlessException {
        this.data = listData;
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1000,500);
        JPanel content = new JPanel();
        final JList<ResultNode> list = new JList<>();
        list.setListData(listData);
        list.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int index = list.getSelectedIndex();
                if(index>=data.length||index<0){
                    return;
                }
                List<ResultNode> cs = data[index].getChildren();
                root = data[index];
                data = asArray(cs);
                list.setListData(data);
                area.setText(tonext(root));
            }
        });
        content.add(list);
        JScrollPane spl = new JScrollPane(content);
        this.add(spl, BorderLayout.CENTER);

        JButton button = new JButton("上一层");
        this.add(button,BorderLayout.SOUTH);
        button.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(root.getParent()!=null){
                    root = root.getParent();
                    data = asArray(root.getChildren());
                    list.setListData(data);
                    area.setText(tonext(root));
                }
            }
        });

        area = new JTextArea();
        this.add(area,BorderLayout.EAST);
    }

    public static ResultNode[] asArray(List<ResultNode> resultNodes){
        Collections.sort(resultNodes, new Comparator<ResultNode>() {
            @Override
            public int compare(ResultNode o1, ResultNode o2) {
                return o1.getScore()-o2.getScore();
            }
        });
        ResultNode[] temp = new ResultNode[resultNodes.size()];
        return  resultNodes.toArray(temp);
    }
    public static String tonext(ResultNode node){
        if(node==null){
            return null;
        }
        StringBuilder builder = new StringBuilder();
        builder.append(node.toString()+"\n");
        builder.append(node.getNext()+"\n");
        return builder.toString();
    }
    public static void main(String[] args) {
    }
}
