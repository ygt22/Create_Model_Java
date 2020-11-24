package com.hasan;

// Java Program to illustrate reading from text file
// as string in Java

import java.io.*;
import java.nio.file.*;
import java.util.regex.*;
import java.lang.*;

public class Main
{
    public static PrintWriter createFile() throws FileNotFoundException, UnsupportedEncodingException {
        PrintWriter dt = new PrintWriter("C:\\Users\\Hasan\\Desktop\\model.java","UTF-8");
        return dt;
    }

    public static void firstInit(PrintWriter dt){
        dt.println("package com.hasan;");
        dt.println("\nimport java.util.HashMap;\n");

        dt.println("class Vertex  // It identifies any vertex node in the model" +
                "\n{" +
                "\npublic String VertexName; // vertex  name : koff_loff_aoff" +
                "\npublic int    key; // sequence number it  comes from model file" +
                "\n\n};" +
                "\n");

        dt.println("class Edge // It identifies any vertex node in the model" +
                "\n{" +
                "\npublic String EdgeName;  // edge  name : kon" +
                "\npublic Vertex Target; // to this vertex N1" +
                "\n\n};" +
                "\n\n");
        dt.println("public class Main {\n\n\t" +
                "public static void main(String[] args) {\n\t");

        dt.println("\n\tHashMap<Integer, HashMap<String,Edge>> Node ="+
                " new HashMap<Integer,HashMap<String,Edge>>();"+
               "\n" );

        // dt.close();
    }

    public static String readFileAsString(String fileName)throws Exception
    {
        String data = "";
        data = new String(Files.readAllBytes(Paths.get(fileName)));
        return data;
    }

    public static void main(String[] args) throws Exception
    {
        // PrintWriter dt = new PrintWriter("C:\\Users\\Hasan\\Desktop\\model.java","UTF-8");
        PrintWriter dt = createFile();
        firstInit(dt);




        String data;
        int NodeCounter =0;
        data = readFileAsString("C:\\Users\\Hasan\\Desktop\\lazer_kapi.graphml");
        //System.out.println(data);
        //. represents single character
        String patternNode = "(?<=<node id=\")(.*?)(?=\">)";
        String patternNodeName = "(?<=\">)(.*?)(?=<y:)";
        String patternNodeControl = "node id";

        Pattern p= Pattern.compile(patternNode);
        Pattern pNN = Pattern.compile(patternNodeName);
        Pattern pNC= Pattern.compile(patternNodeControl);
        //Pattern p = Pattern.compile("<node id=\"");
        int x ;
        int y ;
        Matcher m = p.matcher(data);
        Matcher m2 =pNN.matcher(data);
        Matcher m3 =pNC.matcher(data);

        while(m3.find()){
            m.find();
            x = m.start();
            y =m.end();
            String node = data.substring(x,y);



            m2.find();
            x = m2.start();
            y =m2.end();
            String nodeName = data.substring(x,y);

            //////////////////////////
            dt.println("\tHashMap<String,Edge> "+node+
                    " = new HashMap<>();"+
                    " ");
            //////////////////////////
            String str = node.toUpperCase();
            dt.println("\tVertex "+str+" = new Vertex();");
            dt.println("\t"+str+".VertexName = \""+nodeName+"\";\n" +
                    "\t"+str+".key = "+str.substring(1)+";\n");
            //////////////////////////


            System.out.println(node+ " " +nodeName);
            data = data.substring(y);
            m = p.matcher(data);
            m2 = pNN.matcher(data);
            //m3 =pNC.matcher(data);
        }

        /**
         * EDGE PrOCESS BEGIN
         *
         * **/
        // System.out.println(data);

        String patternEdge = "(?<=<edge id=\")(.*?)(?=\" )";
        String patternEdgeName = "(?<=\">)(.*?)(?=<y:)";
        String patternEdgeTarget = "(?<=target=\")(.*?)(?=\">)";
        String patternEdgeControl = "edge id";

        Pattern p1= Pattern.compile(patternEdge);
        Pattern pEN = Pattern.compile(patternEdgeName);
        Pattern pET= Pattern.compile(patternEdgeTarget);
        Pattern pEC= Pattern.compile(patternEdgeControl);

        int a ;
        int b ;


        Matcher m4 = p1.matcher(data);
        Matcher m5 =pEN.matcher(data);
        Matcher m6 =pEC.matcher(data);
        Matcher m7 =pET.matcher(data);

        m4.find();
        a= m4.start();
        b =m4.end();
        String edge = data.substring(a,b);

        m7.find();
        a = m7.start();
        b =m7.end();
        String edgeTarget = data.substring(a,b);


        String edgeName = null;

        //////////////////////////
        String UpEdgeName = edge.toUpperCase();
        String UpEdgeTargetName = edgeTarget.toUpperCase();
        dt.println( "\tEdge "+UpEdgeName+
         " = new Edge();"+
        " ");
        dt.println("\n\n\t"+UpEdgeName+".EdgeName = "+edgeName+";\n"+
                "\t"+UpEdgeName+".Target = "+UpEdgeTargetName+";\n"+
                " ");
        //////////////////////////

        System.out.println(edge+ " " +edgeName + " " +edgeTarget);
        data = data.substring(b);
        m4 = p1.matcher(data);
        m5 = pEN.matcher(data);
        m6 =pEC.matcher(data);
        m7 =pET.matcher(data);



        while(m6.find()){
            m4.find();
            a= m4.start();
            b =m4.end();
            edge = data.substring(a,b);

            m7.find();
            a = m7.start();
            b =m7.end();
            edgeTarget = data.substring(a,b);

            m5.find();
            a = m5.start();
            b =m5.end();
            edgeName = data.substring(a,b);

            //////////////////////////
            UpEdgeName = edge.toUpperCase();
            UpEdgeTargetName = edgeTarget.toUpperCase();
            dt.println( "\tEdge "+UpEdgeName+
                    " = new Edge();"+
                    " ");
            dt.println("\t"+UpEdgeName+".EdgeName = \""+edgeName+"\";\n"+
                    "\t"+UpEdgeName+".Target = "+UpEdgeTargetName+";\n"+
                    " ");
            //////////////////////////


            System.out.println(edge+ " " +edgeName + " " +edgeTarget);
            data = data.substring(b);
            m4 = p1.matcher(data);
            m5 = pEN.matcher(data);
            m6 =pEC.matcher(data);
            m7 =pET.matcher(data);
        }





        dt.close();

    }


}


