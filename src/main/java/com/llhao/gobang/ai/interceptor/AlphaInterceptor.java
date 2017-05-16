package com.llhao.gobang.ai.interceptor;

import com.llhao.gobang.ai.ResultNode;
import com.llhao.gobang.ai.util.ResultNodeUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by llhao on 2017/4/24.
 */
public class AlphaInterceptor implements Interceptor {
    @Override
    public boolean intercept(ResultNode node, int i, int j) {
        if (node.getParent() == null) {
            return true;
        }
        int p = node.isMax() ? -1 : 1;
        ResultNode parent = node.getParent();
        //�ȼ����ֵܽڵ�����(С)ֵ
        List<ResultNode> sisters = parent.getChildren();
        if (sisters.size() == 0) {
            return true;
        }
        ResultNode sisterMost = ResultNodeUtils.serchOneNode(sisters, p);

        //�ټ����ӽڵ����С(��)ֵ
        List<ResultNode> children = node.getChildren();
        if (children.size() == 0) {
            return true;
        }
        ResultNode childMost = ResultNodeUtils.serchOneNode(children, -p);
        if (sisterMost.getScore() * p > childMost.getScore() * p) {
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        ResultNode root = new ResultNode();
        root.setMax(true);
        addChildren(root, 4, 4);
        System.out.println(ResultNodeUtils.countChildren(root));
        AlphaInterceptor interceptor = new AlphaInterceptor();
        interceptor.intercept(root, 0, 0);
        System.out.println(ResultNodeUtils.countChildren(root));
    }

    public static void addChildren(ResultNode root, int count, int deep) {
        if (deep == 0) {
            return;
        }
        List<ResultNode> cs = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            ResultNode node = new ResultNode();
            node.setMax(!root.isMax());
            node.setScore((int) (Math.random() * 1000));
            addChildren(node, count, deep - 1);
            cs.add(node);
        }
        root.setChildren(cs);
    }
}
