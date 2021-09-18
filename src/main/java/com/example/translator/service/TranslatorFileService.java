package com.example.translator.service;

import org.springframework.util.StreamUtils;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

@Service
public class TranslatorFileService {
    private ArrayList<String>stanzas;
    private ArrayList<String>invertedStanzas;

    public TranslatorFileService() {

        stanzas=new ArrayList<String>();
        invertedStanzas=new ArrayList<String>();
    }

    public boolean isInvert() throws IOException {
        String inputFileName = "C:\\Users\\ACER\\Documents\\original.txt";
        String outputFileName = "C:\\Users\\ACER\\Documents\\estrofasEnOrdenInverso.txt";
        File outputFile = new File(outputFileName);
        OutputStream out = new FileOutputStream(outputFileName);
        treadLines(inputFileName);
        invert();
        String invertString = toString(invertedStanzas);
        StreamUtils.copy(invertString, StandardCharsets.UTF_8, out);
        return  outputFile.exists();
    }

    public String toString(ArrayList<String> list){
        String cadena = " ";
        for (String line : list) {
            if(line.equals("empty")){
                cadena+="\n";
            }else{
                cadena+=line+"\n";
            }
        }
        return cadena;
    }

    public void invert(){
        ArrayList<String> listAux = new ArrayList<String>();

        for (int i = stanzas.size()-1; i>=0 ; i--){
            if(stanzas.get(i).equals("empty")){
                for (int j = listAux.size()-1; j>=0; j--){
                    invertedStanzas.add(listAux.get(j));
                }
                invertedStanzas.add("empty");
                listAux.clear();
            }else{
                listAux.add(stanzas.get(i));
            }
        }
        if(listAux.size()>0){
            for (int j = listAux.size()-1 ; j>=0 ; j--){
                invertedStanzas.add(listAux.get(j));
            }
            listAux.clear();
        }
    }

    public void treadLines(String rutaDelArchivo) {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(rutaDelArchivo));
            String linea = br.readLine();
            stanzas.add(linea);
            while(linea != null)
            {
                linea = br.readLine();
                if(!linea.equals("")){
                    stanzas.add(linea);
                }
                else{
                    stanzas.add("empty");
                }
            }
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
        }
        finally {
            try {
                if(br != null)
                    br.close();
            }
            catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

}
