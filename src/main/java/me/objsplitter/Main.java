package me.objsplitter;

import java.lang.System;
import java.io.*;
import java.util.Map;
import java.util.Map.Entry;
import de.javagl.obj.*;

public class Main {
  public static void main(String[] args) throws Exception{
    if (args.length < 2) System.out.println("Specify input and output paths!");
    String inputFilePath = args[0];
    String outputDir = args[1];

    InputStream objInputStream = new FileInputStream(inputFilePath);
    Obj inputObj = ObjUtils.convertToRenderable(ObjReader.read(objInputStream));

    OutputStream objOutputStream;

    Map<String, Obj> materialGroups = ObjSplitting.splitByMaterialGroups(inputObj);
    for (Entry<String, Obj> entry : materialGroups.entrySet())
    {
        objOutputStream = new FileOutputStream(outputDir + "/" + entry.getKey() + ".obj");
        System.out.println(entry.getKey() + "-separator-signature-" + outputDir + "/" + entry.getKey() + ".obj");
        ObjWriter.write(entry.getValue(), objOutputStream);
    }
  }
}
