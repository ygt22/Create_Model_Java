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
        /*
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
                */
        dt.println("public class model {\n\t" +
                " ");

        dt.println("\n\tstatic HashMap<Integer, HashMap<String,Edge>> Node ="+
                " new HashMap<Integer,HashMap<String,Edge>>();"+
               "\n" );
        dt.println("\n\tstatic void setMethod(){ "+
                "\n"
                     );

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


           // System.out.println(node+ " " +nodeName);
            data = data.substring(y);
            m = p.matcher(data);
            m2 = pNN.matcher(data);
            m3 =pNC.matcher(data);
            NodeCounter++;
        }

        /**
         * EDGE PrOCESS BEGIN
         *
         * **/
        // System.out.println(data);
        String data2 = data;


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
        // for secend it data copy to another data only from edge part

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
                "\t"+UpEdgeName+".Target = "+UpEdgeTargetName+";"+
                " ");
        //////////////////////////

        //System.out.println(edge+ " " +edgeName + " " +edgeTarget);
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


            //System.out.println(edge+ " " +edgeName + " " +edgeTarget);
            data = data.substring(b);
            m4 = p1.matcher(data);
            m5 = pEN.matcher(data);
            m6 =pEC.matcher(data);
            m7 =pET.matcher(data);
        }

         ////////////////////////////////////////
        ////////////////////////////////////////
        ////////////////////////////////////////

        String Edgep = "(?<=<edge id=\")(.*?)(?=\" )";
        String EdgeNamep = "(?<=\">)(.*?)(?=<y:)";
        String EdgeTargetp = "(?<=target=\")(.*?)(?=\">)";
        String EdgeSourcep = "(?<=\" source=\")(.*?)(?=\" target=\")";
        String EdgeControlp = "edge id";

        Pattern p12= Pattern.compile(Edgep);
        Pattern pEN2 = Pattern.compile(EdgeNamep);
        Pattern pET2= Pattern.compile(EdgeTargetp);
        Pattern pES2= Pattern.compile(EdgeSourcep);
        Pattern pEC2= Pattern.compile(EdgeControlp);

        int k ;
        int l ;
        // for secend it data copy to another data only from edge part

        Matcher en = p12.matcher(data2); // en e2 e5
        Matcher esn =pEN2.matcher(data2); // kon aoff
        Matcher ec =pEC2.matcher(data2); // edge id
        Matcher et =pET2.matcher(data2); // target en  e5 e10
        Matcher es =pES2.matcher(data2); // Source en  e5 e10
       // finding e0
        en.find();
        k= en.start();
        l =en.end();
        String edge2 = data2.substring(k,l);
        // source  e0
        es.find();
        k= es.start();
        l =es.end();
        String edgeSName2 = data2.substring(k,l);

        //////////////////////////
        String UpEdgeTargetName2 = edge2.toUpperCase();
        dt.println( "\t"+edgeSName2+ ".put("+ UpEdgeTargetName2 +
                ".EdgeName," + UpEdgeTargetName2 +");"+
                "\n");

        //////////////////////////
       // System.out.println(data2.substring(l));

        data2 = data2.substring(l);


        en = p12.matcher(data2); // en e2 e5
        esn =pEN2.matcher(data2); // kon aoff
        ec =pEC2.matcher(data2); // edge id
        et =pET2.matcher(data2); // target en  e5 e10
        es =pES2.matcher(data2); // Source en  e5 e10


        while(ec.find()){

            en.find();
            k= en.start();
            l =en.end();
             edge2 = data2.substring(k,l);
            // source  e0
            es.find();
            k= es.start();
            l =es.end();
             edgeSName2 = data2.substring(k,l);

            //////////////////////////
             UpEdgeTargetName2 = edge2.toUpperCase();
            dt.println( "\t"+edgeSName2+ ".put("+ UpEdgeTargetName2 +
                    ".EdgeName," + UpEdgeTargetName2 +");"+
                    "\n");

            //////////////////////////

            data2 = data2.substring(l);

            en = p12.matcher(data2); // en e2 e5
            esn =pEN2.matcher(data2); // kon aoff
            ec =pEC2.matcher(data2); // edge id
            et =pET2.matcher(data2); // target en  e5 e10
            es =pES2.matcher(data2); // Source en  e5 e10

        }

        // System.out.println(NodeCounter);
         for (int i = 0; i <NodeCounter; i++){
             dt.println(
                "\tNode.put(N"+i+".key, n"+i+");\n"
             );

         }

         dt.println("}");
         dt.println("\tstatic HashMap<Integer, HashMap<String, Edge>> getnode(){"+
                 "\n\treturn Node;"+
                 "\n}"
         );
         dt.println("\n}");


         dt.close();
    }


}


