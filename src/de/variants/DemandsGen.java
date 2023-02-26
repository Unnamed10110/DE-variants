/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.variants;

import static java.lang.System.out;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jgrapht.GraphPath;
import org.jgrapht.alg.KShortestPaths;
import org.jgrapht.graph.*;

/**
 *
 * @author USER- ACER
 */
public class DemandsGen {

    public static void main(String args[]) throws IOException {
        int cantNodos = 14;
        //int cantDemands= (cantNodos*(cantNodos-1))/2;
        int cantDemands = 200;
        for (int ij = 50; ij <= 50; ij += 50) {
            int cc = 0;
            List<Integer[]> lista = new ArrayList();
            while (cc < ij) {
                int a = getRandomNumberInRange(0, 13);
                int b = getRandomNumberInRange(0, 13);
                int c2 = getRandomNumberInRange(1, 100);
                if (a != b) {
                    cc++;
                    Integer[] dem = new Integer[3];
                    dem[0] = a;
                    dem[1] = b;
                    dem[2] = c2;
                    lista.add(dem);
                }
            }

            BufferedWriter listaFile = new BufferedWriter(new FileWriter("E:\\iin\\eon-tesis\\rcsa\\DE-variants\\src\\de\\files\\nsf\\solicitudes\\solicitudes-nsf-3-" + ij + ".txt"));
            for (int i = 0; i < lista.size(); i++) {
                System.out.println(i + ":" + lista.get(i)[0] + " " + lista.get(i)[1] + " " + lista.get(i)[2]);
                listaFile.write(lista.get(i)[0] + "	" + lista.get(i)[1] + "	" + lista.get(i)[2] + "\n");
            }
            listaFile.close();
        }

    }

    private static int cargarGrafo(SimpleDirectedGraph<String, DefaultWeightedEdge> directedGraph, String path) {

        int cantEnlaces = 0;
        try {
            FileReader fr = new FileReader(path + "\\edges.txt");
            String[] edgeText;
            DefaultWeightedEdge ed;

            try (BufferedReader entrada = new BufferedReader(fr)) {
                String line;
                while ((line = entrada.readLine()) != null) {
                    cantEnlaces++;
                    edgeText = line.split("\t");
                    //System.out.println("split:"+ edgeText[0]);
                    //System.out.println("split:"+ edgeText[1]);
                    //System.out.println("split:"+ edgeText[2]);                    
                    //System.in.read();
                    directedGraph.addVertex(edgeText[0]);
                    directedGraph.addVertex(edgeText[1]);
                    ed = directedGraph.addEdge(edgeText[0], edgeText[1]);
                    directedGraph.setEdgeWeight(ed, Double.parseDouble(edgeText[2]));

                    //bidereccion
                    //ed = directedGraph.addEdge(edgeText[1], edgeText[0]);
                    //directedGraph.setEdgeWeight(ed, Double.parseDouble(edgeText[2]));
                    //System.out.println("edge::"+ed);
                    //System.in.read();
                }
            }
        } catch (FileNotFoundException ex) {
            System.out.println("Archivo no encontrado: " + ex);
        } catch (IOException ex) {

        }

        return cantEnlaces;
    }

    private static void kcaminos(SimpleDirectedGraph<String, DefaultWeightedEdge> directedGraph, String origen, String destino, int k) {

        //obtener k rutas
        KShortestPaths caminosCandidatos = new KShortestPaths(directedGraph, origen, k);
        List<GraphPath<String, DefaultWeightedEdge>> paths = caminosCandidatos.getPaths(destino);

        //listar caminos(nodos) y costos(km)
        if (paths != null) {
            int cc = 0;
            while (cc < paths.size()) {

                System.out.println("path #" + cc + "->" + paths.get(cc));
                System.out.println("costo->" + paths.get(cc).getWeight());
                cc++;
            }

        }

        //System.out.println("path 0:" + caminosCandidatos.getPaths(destino));
    }

    private static int getRandomNumberInRange(int min, int max) {

        Random r = new Random();
        return r.ints(min, (max + 1)).findFirst().getAsInt();
    }

}
