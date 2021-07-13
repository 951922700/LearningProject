package com.lyl;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class DOMTest {
    public static void main(String args[])
    {
        DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder db = dbf.newDocumentBuilder();
            //导入org.w3c.dom.Document下的Document
            Document document=db.parse("src/books.xml");
            //获取所有book节点的集合
            NodeList booklist=document.getElementsByTagName("book");
            //通过nodelist的getLength()方法取得booklist的长度
            System.out.println("一共有"+booklist.getLength()+"本书");
            //遍历每一个book节点
            for(int i=0;i<booklist.getLength();i++)
            {
                //通过item方法获取集合的节点
                Node book=booklist.item(i);
                //获取book节点的所有属性的集合
                NamedNodeMap attrs=book.getAttributes();
                for(int j=0;j<attrs.getLength();j++)
                {
                    Node attr=attrs.item(j);
                    //获取属性名
                    System.out.println("属性名："+attr.getNodeName());
                    //获取属性值
                    System.out.println("--属性值"+attr.getNodeValue());
                }

            }
        }catch(Exception ex){
         ex.printStackTrace();
        }
    }
}
